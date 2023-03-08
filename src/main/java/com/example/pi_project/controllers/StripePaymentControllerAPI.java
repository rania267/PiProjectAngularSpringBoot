package com.example.pi_project.controllers;

import com.example.pi_project.entities.CustomerData;
import com.example.pi_project.entities.PaymentRequest;
import com.example.pi_project.entities.StripeUtil;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.CustomerCollection;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class StripePaymentControllerAPI {

	@Value("${stripe.api.key}")
	String stripeKey;

 @Autowired
 StripeUtil stripeUtil;
	
	@RequestMapping("/createCustomer")
	public CustomerData createCustomer(@RequestBody CustomerData data) throws StripeException {
		Stripe.apiKey = stripeKey;
		Map<String, Object> params = new HashMap<>();
		params.put("name", data.getName());
		params.put("email", data.getEmail());
		params.put("paiment", data.getPaiment());

		Customer customer = Customer.create(params);
		data.setCustomerId(customer.getId());
		return data;
	}

	@RequestMapping("/getAllCustomer")
	public List<CustomerData> getAllCustomer() throws StripeException {
		Stripe.apiKey = stripeKey;

		Map<String, Object> params = new HashMap<>();
		params.put("limit", 3);

		CustomerCollection customers = Customer.list(params);
		List<CustomerData> allCustomer = new ArrayList<CustomerData>();
		for (int i = 0; i < customers.getData().size(); i++) {
			CustomerData customerData = new CustomerData();
			customerData.setCustomerId(customers.getData().get(i).getId());
			customerData.setName(customers.getData().get(i).getName());
			customerData.setEmail(customers.getData().get(i).getEmail());
			allCustomer.add(customerData);
		}
		return allCustomer;
	}

	@RequestMapping("/deleteCustomer/{id}")
	public String deleteCustomer(@PathVariable("id") String id) throws StripeException {
		Stripe.apiKey = stripeKey;

		Customer customer = Customer.retrieve(id);

		Customer deletedCustomer = customer.delete();
		return "successfully deleted";
	}
	
	@RequestMapping("/getCustomer/{id}")
	public CustomerData getCustomer(@PathVariable("id") String id) throws StripeException {
		
		CustomerData output =stripeUtil.getCustomer(id);
		return output;
	}
	@PostMapping("/create-payment-intent")
	public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentRequest paymentRequest) {

		Stripe.apiKey = stripeKey;

		try {
			PaymentIntentCreateParams params = new PaymentIntentCreateParams.Builder()
					.setCurrency("usd")
					.setAmount(paymentRequest.getAmount())
					.build();

			PaymentIntent paymentIntent = PaymentIntent.create(params);

			return new ResponseEntity<>(paymentIntent.toJson(), HttpStatus.OK);
		} catch (StripeException e) {
			return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}






