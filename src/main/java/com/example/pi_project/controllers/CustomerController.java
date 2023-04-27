package com.example.pi_project.controllers;



import com.example.pi_project.entities.Affectation;
import com.example.pi_project.entities.Customer;
import com.example.pi_project.entities.Donation;
import com.example.pi_project.repositories.CustomerRepository;
import com.example.pi_project.services.CsvExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@RestController

public class CustomerController {

    private final CsvExportService csvExportService;
    @Autowired
    CustomerRepository customerRepository;




    public CustomerController(CsvExportService csvExportService) {
        this.csvExportService = csvExportService;
    }

    @GetMapping(path = "/Customer")
    public void getAllEmployeesInCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"customerstest.csv\"");
        csvExportService.writeCustpmerToCsv(servletResponse.getWriter());
    }
    @PostMapping("/addCustomer")
    @ResponseBody
    public Customer addDepartement(@RequestBody Customer customer){
        return csvExportService.addCustomer(customer);
    }


    @PostMapping("/api/addDonation")
    @Transactional
    @ResponseBody
    public Donation addDonation(@RequestBody Donation donation) {
        return csvExportService.addDonation(donation);
    }

    @PutMapping("/updateDonation")
    @ResponseBody
    @Transactional
    public Donation updateDonation(@RequestBody Donation donation){
        return csvExportService.UpdateDonation(donation);
    }

    @PostMapping ("/updateAffecation")
    @ResponseBody
    @Transactional
    public Affectation updateAffectation(@RequestBody Affectation affectation){
        return csvExportService.updateAffectation(affectation);
    }

    @PutMapping("/updateCustomer")
    @ResponseBody
    public Customer updateCustomer(@RequestBody Customer customer){
        return csvExportService.updateCustomer(customer);
    }






    @DeleteMapping("/deleteDonation/{id_donation}")
    @ResponseBody
    public void deleteDonation(@PathVariable("id_donation")int id_donation){
        csvExportService.deleteDonation(id_donation);
    }

    @GetMapping("/getDonationById/{id_donation}")
    @ResponseBody
    public Donation getDonationById(@PathVariable("id_donation")int id_donation){
        return csvExportService.getDonationById(id_donation);
    }


    @GetMapping("/getDonation/{type}")
    @ResponseBody
    public List<Donation> getDonationBy(@PathVariable("type")String type){
        return csvExportService.getdan(type);
    }

    @PostMapping("/updatescore")
    @Transactional
    public void updateScore() {
        csvExportService.updateScore();
    }

   /* @PostMapping("/update/{CUSTOMER_ID}/{id_donation}")
    @Transactional
    @ResponseBody
    public void update(@PathVariable("CUSTOMER_ID")int CUSTOMER_ID,@PathVariable("id_donation")int id_donation) {
        csvExportService.add(CUSTOMER_ID,id_donation);
    }*/

}