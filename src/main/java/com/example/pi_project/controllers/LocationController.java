package com.example.pi_project.controllers;

import com.example.pi_project.entities.Location;
import com.example.pi_project.services.LocationService;
import com.example.pi_project.services.LocationServiceImp;
import com.example.pi_project.services.LocationUtils;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
public class LocationController {


    com.example.pi_project.services.LocationService LocationService;

    @GetMapping("/AllLocation")
    @ResponseBody
    public List<Location> getAllLocation() {
        return LocationService.getAllLocations();
    }

    @PostMapping("/addLocation")
    @ResponseBody
    public Location addLocation(@RequestBody Location Location) {
        return LocationService.addLocation(Location);
    }

    @PutMapping("/updateLocation")
    @ResponseBody
    public Location updateLocation(@RequestBody Location Location) {
        return LocationService.updateLocation(Location);
    }

    @DeleteMapping("/deleteLocation/{idLocation}")
    @ResponseBody
    public void deleteLocation(@PathVariable int idLocation) {
        LocationService.deleteLocation(idLocation);
    }

    @GetMapping("/GetMostFrequentState")
    @ResponseBody
    public String getMostFrequentState(@RequestBody List<Location> locations) {
        return LocationService.getMostFrequentState(locations);
    }
    @GetMapping("/getNearestLocation")
    public String getNearestLocation(
            @RequestParam(name = "latitude") double latitude,
            @RequestParam(name = "longitude") double longitude) {

        Location nearestLocation = LocationService.getNearestLocation(latitude, longitude);

        // Return nearest location and distance
        return  "the nearest distance is " +
                LocationUtils.distance(latitude, longitude, nearestLocation.getLatitude(), nearestLocation.getLongitude()) + " km.";
    }
}