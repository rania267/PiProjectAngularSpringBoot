package com.example.pi_project.services;


import com.example.pi_project.entities.Customer;
import com.example.pi_project.entities.Donation;

public interface CustmerInterface  {
    public Customer addCustomer(Customer customer);
    public Donation addDonation(Donation donation);
    public Donation UpdateDonation(Donation donation);
    public void deleteDonation(int id_donation);
    public Donation getDonationById(int id_donation);
    ;




}
