package com.example.pi_project.controllers;

import com.example.pi_project.entities.*;
import com.example.pi_project.services.IPiService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PiRestController {
    @Autowired
    IPiService piService;



    @PostMapping("/addOrder")
    @ResponseBody
    public Ordeer addOrder(@RequestBody Ordeer order) {
        return piService.addOrder(order);
    }
    @PutMapping("/updateOrder")
    @ResponseBody
    public Ordeer updateOrder(@RequestBody Ordeer order ){
        return piService.updateOrder(order);
    }
    @DeleteMapping("/deleteOrder/{id}")
    @ResponseBody
    public void deleteOrder(@PathVariable("id")int id){
        piService.deleteOrder(id);
    }
    @GetMapping("/AllOrder")
    @ResponseBody
    public List<Ordeer> getAllOrder(){
        return piService.getAllOrder();
    }

    @PostMapping("/addCartShopping")
    @ResponseBody
    public CartShopping addCart (@RequestBody  CartShopping cartShopping) {
        return piService.addOCart(cartShopping);
    }

    @PutMapping("/updateCart")
    @ResponseBody
    public CartShopping updateCartShopping(@RequestBody CartShopping cartShopping ){
        return piService.updateCartShopping(cartShopping);
    }
    @DeleteMapping("/deleteCartShopping/{id}")
    @ResponseBody
    public void deleteCartShopping(@PathVariable("id")int id){
        piService.deleteCartShopping(id);
    }
    @GetMapping("/AllCartShopping")
    @ResponseBody
    public List<CartShopping> getAllCartShopping(){
        return piService.getAllCartShopping();
    }




    @PostMapping("/addDonation")
    @ResponseBody
    public Donation addDonation (@RequestBody  Donation donation) {
        return piService.addODonation(donation);
    }

    @PutMapping("/updateDonation")
    @ResponseBody
    public Donation updateDonation(@RequestBody Donation donation ){
        return piService.updateDonation(donation);
    }
    @DeleteMapping("/deleteDonation/{id}")
    @ResponseBody
    public void deleteDonation(@PathVariable("id")int id){
        piService.deleteDonation(id);
    }
    @GetMapping("/AllDonation")
    @ResponseBody
    public List<Donation> getAllDonation(){
        return piService.getAllDonation();
    }


}

