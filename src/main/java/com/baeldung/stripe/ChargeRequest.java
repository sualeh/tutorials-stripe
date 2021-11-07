package com.baeldung.stripe;

public record ChargeRequest(
    String description,
    int amount, // cents
    Currency currency,
    String stripeEmail,
    String stripeToken) {}
