package com.ecommerce.shopcart.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name ="tokens")
public class AuthenticationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String token;
    private Date createdDate;
    @OneToOne(targetEntity = User.class,fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name = "user_id")
    private User user;

    public AuthenticationToken(User user) {
        this.createdDate = new Date();
        this.token = UUID.randomUUID().toString();
        this.user = user;
    }

}
