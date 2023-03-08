package com.example.pi_project.entities;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class StripeUtil {

	@Value("${stripe.api.key}")
	String stripeKey;

	public CustomerData getCustomer(String id) throws StripeException {
		Stripe.apiKey = stripeKey;

		Customer customer = Customer.retrieve(id);
		CustomerData data = setCustomerData(customer);
		return data;
	}
	
	public CustomerData setCustomerData(Customer customer) {
		CustomerData customerData = new CustomerData();
		customerData.setCustomerId(customer.getId());
		customerData.setName(customer.getName());
		customerData.setEmail(customer.getEmail());
		
		return customerData;
	}
}
