package com.example.pi_project.services;


import com.example.pi_project.entities.SmsrequestRamzi;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsRamziservice {

	private final Twilioproperties twilioproperties;
	
	@Autowired
	public SmsRamziservice(Twilioproperties twilioproperties)
	{
		this.twilioproperties=twilioproperties;
	}
	
	//send message to number
	public String sendsms(SmsrequestRamzi smsrequest)
	{
     
		Message message=Message.creator(new PhoneNumber(smsrequest.getNumber()), new PhoneNumber(twilioproperties.getFromNumber()), smsrequest.getMessage()).create();
        return message.getStatus().toString();
        
	
	}
}
