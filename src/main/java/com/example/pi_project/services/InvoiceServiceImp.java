package com.example.pi_project.services;



import com.example.pi_project.entities.Invoice;
import com.example.pi_project.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImp implements  InvoiceService{
    @Autowired
    EmailService e;
    private JavaMailSender javaMailSender;
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
    private static String formatDuration(Duration duration) {
        long days = duration.toDays();
        long hours = duration.toHours() % 24;
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;

        StringBuilder sb = new StringBuilder();
        if (days > 0) {
            sb.append(days).append(" day").append(days > 1 ? "s" : "").append(" ");
        }
        if (hours > 0) {
            sb.append(hours).append(" hour").append(hours > 1 ? "s" : "").append(" ");
        }
        if (minutes > 0) {
            sb.append(minutes).append(" minute").append(minutes > 1 ? "s" : "").append(" ");
        }
        if (seconds > 0) {
            sb.append(seconds).append(" second").append(seconds > 1 ? "s" : "");
        }

        return sb.toString().trim();
    }
@Override
    public String showDeadline(Model model, int id ) {
        Invoice i = invoiceRepository.findById(id).orElse(null);
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(now, i.getDeadline());
        long seconds = duration.getSeconds();

        if (seconds > 0) {
            model.addAttribute("timeLeft", formatDuration(duration));
        } else {
            model.addAttribute("timeLeft", "Le temps est écoulé !");
            e.sendEmail("ramzi.ferjani@esprit.tn","le temps de deadline est écoulé ","la facture de description"+i.getDesciption());


        }
        return     "il vous reste " +formatDuration(duration) +" avant l'expiration" ;

    }

    @Override
    public List<Invoice> getTopStudentsByRanking(int n) {
        return rankStudentsByGpa().stream().limit(n).collect(Collectors.toList());
    }
    @Override
    public List<Invoice> rankStudentsByGpa() {
        return invoiceRepository.findAll().stream()
                .sorted((s1, s2) -> Double.compare(s2.getTotal(), s1.getTotal()))
                .collect(Collectors.toList());
    }
}
