package com.example.pi_project.services;



import com.example.pi_project.entities.Invoice;
import com.example.pi_project.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImp implements  InvoiceService{
    @Autowired
    InvoiceRepository invoiceRepository;
    @Override
    public List<Invoice> getAllInvoices() {
        return  invoiceRepository.findAll();
    }

    @Override
    public Invoice addInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public void deleteInvoice(int id) {
        invoiceRepository.deleteById(id);

    }
}
