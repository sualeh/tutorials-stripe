package com.baeldung.stripe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

@Controller
public class ChargeController {

  @Autowired StripeService paymentsService;

  @PostMapping("/charge")
  public String charge(final ChargeRequest chargeRequest, final Model model)
      throws StripeException {

    final Charge charge = paymentsService.charge(chargeRequest);

    model.addAttribute("id", charge.getId());
    model.addAttribute("status", charge.getStatus());
    model.addAttribute("balance_transaction", charge.getBalanceTransaction());
    model.addAttribute("charge_request", chargeRequest);

    return "result";
  }

  @ExceptionHandler(StripeException.class)
  public String handleError(final Model model, final StripeException ex) {
    model.addAttribute("error", ex.getUserMessage());
    model.addAttribute("request_id", ex.getRequestId());
    model.addAttribute("status_code", ex.getStatusCode());
    model.addAttribute("charge_id", ex.getStripeError().getCharge());
    model.addAttribute("decline_code", ex.getStripeError().getDeclineCode());

    return "result";
  }
}
