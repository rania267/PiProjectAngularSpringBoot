package com.example.pi_project.controllers;


import com.example.pi_project.entities.Invoice;
import com.example.pi_project.services.InvoiceService;
import com.example.pi_project.services.TaxServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class InvoiceController {


  InvoiceService piService;
  TaxServiceImp piService1;
    @GetMapping("/AllInvoices")
    @ResponseBody
    public List<Invoice> getAllInvoice(){
        return piService.getAllInvoices();
    }
    @PostMapping("/addInvoices")
    @ResponseBody
    public Invoice addDelivery (@RequestBody Invoice invoice) {

        return piService.addInvoice(invoice);
    }
  @PutMapping("/updateInvoice")
  @ResponseBody
  public Invoice updateDelivery(@RequestBody Invoice invoice){
    return piService.updateInvoice(invoice);
  }
  @DeleteMapping("/deleteInvoice/{id}")
  @ResponseBody
  public void deleteDelivery(@PathVariable int id){
    piService.deleteInvoice(id);
  }
  @PostMapping("/mean")
  @ResponseBody
  public ResponseEntity<Double> calculateMean(@RequestBody List<Invoice> numbers) {
    double mean = piService1.calculateMean(numbers);
    return new ResponseEntity<>(mean, HttpStatus.OK);
  }


}
