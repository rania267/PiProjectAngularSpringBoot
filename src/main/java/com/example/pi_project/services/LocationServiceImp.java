package com.example.pi_project.services;


import com.example.pi_project.entities.Location;
import com.example.pi_project.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImp implements  LocationService{
    @Autowired
    LocationRepository locationRepository;
    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll()   ;
    }

    @Override
    public Location addLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Location updateLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public void deleteLocation(int idLocation) {
locationRepository.deleteById(idLocation);
    }
}
