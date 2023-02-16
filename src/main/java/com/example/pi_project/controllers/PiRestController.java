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


    @GetMapping("/AllContracts")
    @ResponseBody
    public List<Contract> getAllContracts(){
        return piService.getAllContracts();
    }
    @GetMapping("/getContractById/{id}")
    @ResponseBody
    public Contract getContractById(@PathVariable("id")int id){
        return piService.getContractById(id);
    }

    @GetMapping("/getDeliveryById/{id}")
@ResponseBody
public Delivery getDeliveryById(@PathVariable("id")int id){
    return piService.getDeliveryById(id);
}
    @GetMapping("/AllDeliveries")
    @ResponseBody
    public List<Delivery> getAllDeliveries(){
        return piService.getAllDeliveries();
    }
    @PostMapping("/addDelivery")
    @ResponseBody
    public Delivery addDelivery (@RequestBody Delivery delivery) {
        return piService.addDelivery(delivery);
    }
    @PostMapping("/addContract")
    public Contract addContract (@RequestBody Contract contract) {
        return piService.addContract(contract);
    }
    @PutMapping("/updateDelivery")
    @ResponseBody
    public Delivery updateDelivery(@RequestBody Delivery delivery){
        return piService.updateDelivery(delivery);
    }
    @PutMapping("/updateContract")
    @ResponseBody
    public Contract updateContract(@RequestBody Contract contract){
        return piService.updateContract(contract);
    }
    @DeleteMapping("/deleteDelivery/{id}")
    @ResponseBody
    public void deleteDelivery(@PathVariable("id")int id){
        piService.deleteDelivery(id);
    }


    @DeleteMapping("/deleteContract/{id}")
    @ResponseBody
    public void deleteContract(@PathVariable("id")int id){
        piService.deleteContract(id);
    }
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
    @GetMapping("/AllOffers")
    @ResponseBody
    public List<Offer> getAllOffers(){
        return piService.getAllOffers();
    }

    @GetMapping("/AllRequests")
    @ResponseBody
    public List<Request> getAllRequests(){return piService.getAllRequests();}

    @PostMapping("/addRequest")
    @ResponseBody
    public Request addDelivery (@RequestBody Request request) {
        return piService.addRequest(request);
    }
    @PostMapping("/addOffer")
    public Offer addContract (@RequestBody Offer offer) {
        return piService.addOffer(offer);
    }
    @PutMapping("/updateRequest")
    @ResponseBody
    public Request updateRequest(@RequestBody Request request){
        return piService.updateRequest(request);
    }
    @PutMapping("/updateOffer")
    @ResponseBody
    public Offer updateOffer(@RequestBody Offer offer){
        return piService.updateOffer(offer);
    }
    @DeleteMapping("/deleteRequest/{id}")
    @ResponseBody
    public void deleteRequest(@PathVariable("id")int  id){
        piService.deleteRequest(id);
    }


    @DeleteMapping("/deleteOffer/{id}")
    @ResponseBody
    public void deleteOffer(@PathVariable("id")int id){
        piService.deleteOffer(id);
    }


}

