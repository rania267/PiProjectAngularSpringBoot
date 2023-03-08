package com.example.pi_project.services;



import com.example.pi_project.entities.Invoice;
import com.example.pi_project.entities.Tax;
import com.example.pi_project.repositories.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class TaxServiceImp implements  TaxService{
    @Autowired
    TaxRepository taxRepository;

    private JavaMailSender g;

    public void sendMailWithAttachment(String toEmail,
                                       String body,
                                       String subject ) throws MessagingException {
        MimeMessage mimeMessage=g.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("ramzi.ferjani@esprit.tn");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject(subject);


        g.send(mimeMessage);
        System.out.printf("Mail with attachment sent successfully..");

    }
    @Override
    public List<Tax> getAllTax() {
        return taxRepository.findAll();
    }

    @Override
    public Tax addTax(Tax tax) {
        return taxRepository.save(tax);
    }

    @Override
    public Tax updateInvoice(Tax tax) {
        return taxRepository.save(tax);
    }

    @Override
    public void deleteTax(int idTax) {
      taxRepository.deleteById(idTax);

    }

    public double calculateMean(List<Invoice> numbers) {
        double sum1 = 0.0;
        for (Invoice num : numbers) {
            sum1 += sum1;
        }
        return sum1 / numbers.size();
    }



    }


