package com.example.pi_project.services;


import com.example.pi_project.entities.Invoice;
import org.springframework.ui.Model;

import java.util.List;

public interface InvoiceService {

    public List<Invoice> getAllInvoices();

    public Invoice addInvoice(Invoice invoice);
    public String showDeadline(Model model, int id );

    public Invoice updateInvoice(Invoice invoice);
    public void deleteInvoice(int id);
    public List<Invoice> getTopStudentsByRanking(int n);
    public List<Invoice> rankStudentsByGpa();

}
