package com.bus.demo.paypal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class PayPalClient {

	String clientId = "ASKMEdSFnZqdBAf7vuLLSol3SddkBlPm-0BsYE09sC2WvpXFoPp34RAdviCXcle1-3b2-hcrFFdOWIsq";
	String clientSecret = "EJFZfFXubUNOCm1SS3AwGD3tZl8nWgs7eyVPr-85huA-7Mogl5E8MB3mRCfM41iri_xRP9-S6I3ky1CW";

//    @Autowired
//    PayPalClient(){}

	public Map<String, Object> createPayment(String sum) {
		Map<String, Object> response = new HashMap<String, Object>();
		Amount amount = new Amount();
		amount.setCurrency("VND");
		amount.setTotal(sum);
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);

		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");

		Payment payment = new Payment();
		payment.setIntent("sale");
		payment.setPayer(payer);
		payment.setTransactions(transactions);

		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl("http://localhost:4200/cancel");
		redirectUrls.setReturnUrl("http://localhost:4200");
		payment.setRedirectUrls(redirectUrls);
		Payment createdPayment;
		try {
			String redirectUrl = "";
			APIContext context = new APIContext(clientId, clientSecret, "sandbox");
			createdPayment = payment.create(context);
			if (createdPayment != null) {
				List<Links> links = createdPayment.getLinks();
				for (Links link : links) {
					if (link.getRel().equals("approval_url")) {
						redirectUrl = link.getHref();
						break;
					}
				}
				response.put("status", "success");
				response.put("redirect_url", redirectUrl);
			}
		} catch (PayPalRESTException e) {
			System.out.println("Error happened during payment creation!");
		}
		return response;
	}

	public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
		APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox"); // Hoặc "live" cho môi trường thực

		Payment payment = new Payment();
		payment.setId(paymentId);

		PaymentExecution paymentExecution = new PaymentExecution();
		paymentExecution.setPayerId(payerId);

		return payment.execute(apiContext, paymentExecution);
	}
}
