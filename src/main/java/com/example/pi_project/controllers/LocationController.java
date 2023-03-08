package com.example.pi_project.controllers;

import com.example.pi_project.entities.Location;
import com.example.pi_project.services.LocationServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class LocationController {










   LocationServiceImp LocationService;
    @GetMapping("/AllLocation")
    @ResponseBody
    public List<Location> getAllLocation(){
        return LocationService.getAllLocations();
    }
    @PostMapping("/addLocation")
    @ResponseBody
    public Location addLocation (@RequestBody Location Location) {
        return LocationService.addLocation(Location);
    }
    @PutMapping("/updateLocation")
    @ResponseBody
    public Location updateLocation(@RequestBody Location  Location){
        return LocationService.updateLocation(Location);
    }
    @DeleteMapping("/deleteLocation/{idLocation}")
    @ResponseBody
    public void deleteLocation(@PathVariable int idLocation){
        LocationService.deleteLocation(idLocation);
    }

}
