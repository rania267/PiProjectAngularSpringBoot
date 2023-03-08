package com.example.pi_project.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class Twilioinitializer {


	public static final String ACCOUNT_SID = "AC01e660586d6e1873ed98e7f96bc27873";
	public static final String AUTH_TOKEN = "c2899e9e60901af84ffda65adefb9e67";

	// From number provided by Twilio
	public static final String FROM_NUMBER = "+12765319057";

	public void hello(String c ) {
		// Initialize the Twilio API client
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

		// Send an SMS message
		Message message = Message.creator(
						new PhoneNumber("+21628559457"),  // To number in E.164 format
						new PhoneNumber(FROM_NUMBER),     // From number provided by Twilio
						"Hello from Twilio using Spring Boot!"+ c) // Message content
				.create();

		// Print the message SID
		System.out.println("Message SID: " + message.getSid());

	}
}

