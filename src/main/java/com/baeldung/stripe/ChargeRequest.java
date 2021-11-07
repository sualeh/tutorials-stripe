package com.baeldung.stripe;

public record ChargeRequest(
    String description,
    int amount, // in cents
    String currency,
    String stripeToken, // Stripe payment token
    // created from the first call to Stripe, returned from the
    // Stripe Payment Element overlay
    String stripeTokenType, // token type returned by Stripe Payment Element overlay
    String stripeEmail // email returned by Stripe Payment Element overlay
    ) {}
