package com.example.pi_project.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {
    @Value("${stripe.api.key}")
    private String apiKey;

    public Charge chargeCreditCard(String token, BigDecimal amount) throws StripeException {
        Stripe.apiKey = apiKey;

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", amount.multiply(BigDecimal.valueOf(100)).intValueExact());
        chargeParams.put("currency", "usd");
        chargeParams.put("source", token);

        return Charge.create(chargeParams);
    }
}
