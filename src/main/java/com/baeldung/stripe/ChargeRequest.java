package com.baeldung.stripe;

public record ChargeRequest(
    String description,
    int amount, // cents
    String currency,
    String stripeToken) {}
