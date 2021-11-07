package com.baeldung.stripe;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

@Service
public class StripeService {

  @Value("${STRIPE_SECRET_KEY}")
  String secretKey;

  public Charge charge(final ChargeRequest chargeRequest) throws StripeException {
    final Map<String, Object> chargeParams = new HashMap<>();
    chargeParams.put("amount", chargeRequest.amount());
    chargeParams.put("currency", chargeRequest.currency());
    chargeParams.put("description", chargeRequest.description());
    chargeParams.put("source", chargeRequest.stripeToken());
    return Charge.create(chargeParams);
  }

  @PostConstruct
  public void init() {
    Stripe.apiKey = secretKey;
  }
}
