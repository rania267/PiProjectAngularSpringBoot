package com.example.pi_project.config;
import com.example.pi_project.entities.Customer;
import com.example.pi_project.services.CsvExportService;
import org.springframework.batch.item.ItemProcessor;

public class CustomerProcessor implements ItemProcessor<Customer,Customer> {
    CsvExportService csvExportService;
    @Override
    public Customer process(Customer customer) throws Exception {
        double c = 0;
        double c1 = 0;
        double c2 = 0;
        double c3 = 0;
        if (customer.getEtat_civil().equals("celibataire")) {
            c = c + 0.2;
        } else if (customer.getEtat_civil().equals("divorce")) {
            c = c + 0.3;
        } else if (customer.getEtat_civil().equals("Marie")) {
            c = c + 0.4;
        }


        if (customer.getNombre_enfance().equals("plus de 3")) {
            c1 = c1 + 0.4;

        } else if (customer.getNombre_enfance().equals("moins de 3")) {
            c1 = c1 + 0.2;

        } else if (customer.getNombre_enfance().equals("aucune")) {
            c1 = c1 + 0.5;
        }

        if (customer.getBourse().equals("Bource d'étude")) {
            c2 = c2 + 0.1;


        } else if (customer.getBourse().equals("aucun")) {
            c2 = c2 + 0.5;

        } else if (customer.getBourse().equals("allocation de chomage")) {
            c2 = c2 + 0.2;

        }
        customer.setScore(c + c1 + c2);
        return customer;
    }

}
