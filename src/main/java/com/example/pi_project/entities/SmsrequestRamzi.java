package com.example.pi_project.entities;




public class SmsrequestRamzi {

	private String number;
	private String message;
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public SmsrequestRamzi(String number, String message) {
		super();
		this.number = number;
		this.message = message;
	}
	
}
