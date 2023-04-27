package com.example.pi_project.services;


import com.example.pi_project.entities.Location;

import java.util.List;

public interface LocationService {

        public List<Location> getAllLocations();

        public Location addLocation(Location location);


        public Location updateLocation(Location location);
        public void deleteLocation(int idLocation);
        public String getMostFrequentState(List<Location> locations);
        public List<Location> getNearestLocations(Location location, List<Location> allLocations);
        public Location getNearestLocation(double latitude, double longitude);
    }

