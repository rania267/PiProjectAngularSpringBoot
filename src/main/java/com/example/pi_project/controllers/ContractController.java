package com.example.pi_project.controllers;

import com.example.pi_project.entities.Contract;
import com.example.pi_project.entities.ContractStatistics;
import com.example.pi_project.repositories.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contract")
public class ContractController {
    @Autowired
    private ContractRepository contractRepository;
    //generating statistics with a dynamic variable for delivery contracts
//http://localhost:8081/delivery-contracts/duration/statistics
    @GetMapping("/delivery-contracts/{variable}/statistics")
    public ResponseEntity<ContractStatistics> getDeliveryContractStatistics(@PathVariable String variable) {
        List<Contract> contracts = contractRepository.findAll();

        double sum = 0.0;
        int count = 0;
        for (Contract contract : contracts) {
            switch (variable) {
                case "price":
                    sum += contract.getPrice();
                    break;
                case "duration":
                    sum += contract.getDuration();
                    break;
                case "distance":
                    sum += contract.getDistance();
                    break;
                default:
                    // handle invalid variable input
                    return ResponseEntity.badRequest().build();
            }
            count++;
        }

        double average = (count > 0) ? sum / count : 0;
        double min = contracts.stream()
                .mapToDouble(c -> {
                    switch (variable) {
                        case "price":
                            return c.getPrice();
                        case "duration":
                            return c.getDuration();
                        case "distance":
                            return c.getDistance();
                        default:
                            return 0.0;
                    }
                })
                .min()
                .orElse(0.0);
        double max = contracts.stream()
                .mapToDouble(c -> {
                    switch (variable) {
                        case "price":
                            return c.getPrice();
                        case "duration":
                            return c.getDuration();
                        case "distance":
                            return c.getDistance();
                        default:
                            return 0.0;
                    }
                })
                .max()
                .orElse(0.0);

        return ResponseEntity.ok(new ContractStatistics(average, min, max));
    }

}
