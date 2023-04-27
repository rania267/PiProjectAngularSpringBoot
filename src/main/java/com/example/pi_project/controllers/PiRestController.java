package com.example.pi_project.controllers;

import com.example.pi_project.entities.Contract;
import com.example.pi_project.entities.Delivery;
import com.example.pi_project.entities.Ordeer;
import com.example.pi_project.repositories.ContractRepository;
import com.example.pi_project.services.DeliveryService;
import com.example.pi_project.services.IPiService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/rania")
public class PiRestController {
    @Autowired
    IPiService piService;

    @Autowired
    ContractRepository contractRepository;


    @GetMapping("/getDeliveryById/{id}")
@ResponseBody
public Delivery getDeliveryById(@PathVariable("id")int id){
    return piService.getDeliveryById(id);
}
    //http://localhost:8081/rania/AllDeliveries
    @GetMapping("/AllDeliveries")
    @ResponseBody
    public List<Delivery> getAllDeliveries(){
        return piService.getAllDeliveries();
    }
    //http://localhost:8081/rania/addDelivery
    /*{ "address":" Sousse Tunisie",
            "unitPrice":113.122,
            "speed":"express",
            "size":12.3,
            "deliveryState":"received",
            "totalHT":133.12
    }
    */
    @PostMapping("/addDelivery")
    @ResponseBody
    public Delivery addDelivery (@RequestBody Delivery delivery) {
        return piService.addDelivery(delivery);
    }

    //http://localhost:8081/rania/updateDelivery
    @PutMapping("/updateDelivery")
    @ResponseBody
    public Delivery updateDelivery(@RequestBody Delivery delivery){
        return piService.updateDelivery(delivery);
    }


    //http://localhost:8081/rania/deleteDelivery/1
    @DeleteMapping("/deleteDelivery/{id}")
    @ResponseBody
    public void deleteDelivery(@PathVariable("id")int id){
        piService.deleteDelivery(id);
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


   @Autowired
   private DeliveryService deliveryService;

    @GetMapping("/getContractById/{id}")
    @ResponseBody
    public Contract getContractById(@PathVariable("id")int id){
        return piService.getContractById(id);
    }

    @GetMapping("/AllContracts")
    @ResponseBody
    public List<Contract> getAllContracts(){
        return piService.getAllContracts();
    }

    @PostMapping("/addContract")
    @ResponseBody
    public Contract addContract (@RequestBody Contract contract) {
        return piService.addContract(contract);
    }


    @PutMapping("/updateContract")
    @ResponseBody
    public Contract updateContract(@RequestBody Contract contract){
        return piService.updateContract(contract);
    }



    @DeleteMapping("/deleteContract/{id}")
    @ResponseBody
    public void deleteContract(@PathVariable("id")int id){
        piService.deleteContract(id);
    }







}



