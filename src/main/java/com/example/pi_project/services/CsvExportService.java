package com.example.pi_project.services;

import com.example.pi_project.entities.Affectation;
import com.example.pi_project.entities.Customer;
import com.example.pi_project.entities.Donation;

import com.example.pi_project.repositories.AffectationRepository;
import com.example.pi_project.repositories.CustomerRepository;
import com.example.pi_project.repositories.DonationRepository;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

@Service

public class CsvExportService implements CustmerInterface {


    private static final Logger log = getLogger(CsvExportService.class);
    @Autowired
    DonationRepository donationRepository;
    @Autowired
    AffectationRepository affectationRepository;
    private final CustomerRepository customerRepository;

    public CsvExportService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public void writeCustpmerToCsv(Writer writer) {

        List<Customer> Customers = customerRepository.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("id", "firstName", "lastName", "email", "gender", "CIN", "Age", "date", "nombre_enfance", "Etat_civil", "Bourse", "Score", "type");
            for (Customer customer : Customers) {
                csvPrinter.printRecord(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getGender(), customer.getCIN(), customer.getAge(), customer.getDate(), customer.getNombre_enfance(), customer.getEtat_civil(), customer.getBourse(), customer.getScore(), customer.getType());
            }
        } catch (IOException e) {
            log.error("Error While writing CSV ", e);
        }
    }
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);


    }
    public Affectation updateAffectation(Affectation affectation) {
        return affectationRepository.save(affectation);
    }

    /*public void add(int CUSTOMER_ID ,int id_donation){
            affectationRepository.addAffectation(CUSTOMER_ID,id_donation);}*/
    public List<Donation> getdan(String type){
        return  donationRepository.getDonationByType(type);}



    @Override
    public Donation addDonation(Donation donation) {
        return donationRepository.save(donation);
    }

    @Override
    public Donation UpdateDonation(Donation donation) {
        return donationRepository.save(donation);
    }

    @Override
    public void deleteDonation(int id_donation) {
        donationRepository.deleteById(id_donation);
    }

    @Override
    public Donation getDonationById(int id_donation) {
        return donationRepository.findById(id_donation).orElse(null);
    }





    public static List<Customer> findOldCustomer(List<Customer> customer) {
        List<Customer> oldcustomers = new ArrayList<>();
        int currentMaxAge = Integer.MIN_VALUE;

        for (Customer cust : customer) {
            int currentAge = cust.getAge();
            if (currentAge > currentMaxAge) {
                oldcustomers.clear();
                oldcustomers.add(cust);
                currentMaxAge = currentAge;
            } else if (currentAge == currentMaxAge) {
                oldcustomers.add(cust);
            }
        }
        return oldcustomers;
    }


    public void updateScore() {

        List<Customer> listCustomer = customerRepository.getMaxScoree();
        System.out.println(listCustomer);
        List<Customer> oldCustomers = findOldCustomer(listCustomer);
        if (oldCustomers.size() == 1) {
            oldCustomers.get(0).setScore(oldCustomers.get(0).getScore() + 0.3);
            updateCustomer(oldCustomers.get(0));
            List<Donation> donations = donationRepository.getDonationByType(oldCustomers.get(0).getType());

            if (!donations.isEmpty() && donations.get(0).getAffected().equals(Boolean.FALSE)){
                affectationRepository.addAffectation(oldCustomers.get(0).getId(),donations.get(0).getId_donation());
                donationRepository.markDonationAsAffected(donations.get(0).getId_donation());
            }

        } else if (oldCustomers.size() > 1) {
            List<Customer> sortCustomers = oldCustomers.stream().sorted(Comparator.comparing(Customer::getDate))
                    .collect(Collectors.toList());
            sortCustomers.get(0).setScore(sortCustomers.get(0).getScore() + 0.5);
            updateCustomer(sortCustomers.get(0));
            List<Donation> donations = donationRepository.getDonationByType(sortCustomers.get(0).getType());
            if (!donations.isEmpty() && donations.get(0).getAffected().equals(Boolean.FALSE)){
                affectationRepository.addAffectation(sortCustomers.get(0).getId(),donations.get(0).getId_donation());
                donationRepository.markDonationAsAffected(donations.get(0).getId_donation());



            }

        }

    }








}