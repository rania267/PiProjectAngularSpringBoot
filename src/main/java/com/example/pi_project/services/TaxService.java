package com.example.pi_project.services;



import com.example.pi_project.entities.Tax;

import javax.mail.MessagingException;
import java.util.List;

public interface TaxService {
    public List<Tax> getAllTax();

    public Tax addTax(Tax tax);


    public Tax updateInvoice(Tax tax);
    public void deleteTax(int idTax);
    public void sendMailWithAttachment(String toEmail,
                                       String body,
                                       String subject )throws MessagingException;
}
