package com.ecommerce.shopcart.service;

import com.ecommerce.shopcart.dto.OrderDTO;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Value("${BASE_URL}")
    private String baseUrl;
    @Value("${STRIPE_SECRET_KEY}")
    private String apiKey;
    public Session createSession(List<OrderDTO> orderDTOList) throws StripeException {
        String successUrl = baseUrl+"/payment/success";
        String failureURL = baseUrl+"/payment/failed";
        Stripe.apiKey = apiKey;
        List<SessionCreateParams.LineItem> sessionCreateParams = new ArrayList<>();
        for (OrderDTO orderDTO : orderDTOList){
            sessionCreateParams.add(createSessionItem(orderDTO));
        }
        SessionCreateParams params = SessionCreateParams.builder().addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl(failureURL)
                .setSuccessUrl(successUrl)
                .addAllLineItem(sessionCreateParams)
                .build();
        return Session.create(params);
    }

    private SessionCreateParams.LineItem createSessionItem(OrderDTO orderDTO) {
        return SessionCreateParams.LineItem.builder().setPrice(String.valueOf(createPriceData(orderDTO))).
                setQuantity(Long.parseLong(String.valueOf(orderDTO.getQuantity()))).build();
    }

    private SessionCreateParams.LineItem.PriceData createPriceData(OrderDTO orderDTO) {
        return SessionCreateParams.LineItem.PriceData.builder().setCurrency("inr").setUnitAmount((long)orderDTO.getTotalPrice()).
                setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder().setName(orderDTO.getProductName()).build()).build();
    }
}
