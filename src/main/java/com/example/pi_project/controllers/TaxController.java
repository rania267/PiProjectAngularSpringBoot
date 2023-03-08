package com.example.pi_project.controllers;


import com.example.pi_project.entities.Tax;
import com.example.pi_project.services.StripeService;
import com.example.pi_project.services.TaxService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
public class TaxController {
    @Autowired
    private StripeService stripeService;
    TaxService taxService;

    @GetMapping("/AllTax")
    @ResponseBody
    public List<Tax> getAllInvoice(){
        return taxService.getAllTax();
    }
    @PostMapping("/addTax")
    @ResponseBody
    public Tax addTax (@RequestBody Tax  tax) {
        return taxService.addTax(tax);
    }
    @PutMapping("/updateTax")
    @ResponseBody
    public Tax updateTax(@RequestBody Tax  tax){
        return taxService.updateInvoice(tax);
    }
    @DeleteMapping("/deleteTax/{idTax}")
    @ResponseBody
    public void deleteTax(@PathVariable int idTax){
        taxService.deleteTax(idTax);
    }
    @PostMapping("/charge")
    public ResponseEntity<Charge> chargeCreditCard(@RequestParam("token") String token,
                                                   @RequestParam("amount") BigDecimal amount) throws StripeException {
        Charge charge = stripeService.chargeCreditCard(token, amount);
        return ResponseEntity.ok(charge);
    }
}
