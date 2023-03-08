package com.example.pi_project.services;


import com.example.pi_project.entities.Invoice;

import java.util.List;

public interface InvoiceService {

    public List<Invoice> getAllInvoices();

    public Invoice addInvoice(Invoice invoice);


    public Invoice updateInvoice(Invoice invoice);
    public void deleteInvoice(int id);

}
