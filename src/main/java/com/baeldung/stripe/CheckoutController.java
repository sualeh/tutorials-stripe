package com.baeldung.stripe;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckoutController {

  @Value("${STRIPE_PUBLIC_KEY}")
  private String stripePublicKey;

  @RequestMapping("/checkout")
  public String checkout(final Model model) {
    model.addAttribute("description", "Example purchase for Stripe tutorial");
    model.addAttribute("amount", 50 * 100); // in cents
    model.addAttribute("stripePublicKey", stripePublicKey);
    model.addAttribute("currency", Currency.USD);
    return "checkout";
  }
}
