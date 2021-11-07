package com.baeldung.stripe;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

@Service
public class StripeService {

  @Value("${STRIPE_SECRET_KEY}")
  private String secretKey;

  public Charge charge(final ChargeRequest chargeRequest) throws StripeException {
    return Charge.create(
        ofEntries(
            entry("amount", chargeRequest.amount()),
            entry("currency", chargeRequest.currency()),
            entry("description", chargeRequest.description()),
            entry("source", chargeRequest.stripeToken())));
  }

  @PostConstruct
  public void init() {
    Stripe.apiKey = secretKey;
  }
}
