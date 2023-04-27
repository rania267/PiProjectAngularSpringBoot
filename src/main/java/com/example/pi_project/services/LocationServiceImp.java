package com.example.pi_project.services;


import com.example.pi_project.entities.Location;
import com.example.pi_project.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public String getMostFrequentState(List<Location> locations) {
        Map<String, Integer> regionCounts = new HashMap<>();

        // compter le nombre d'occurrences de chaque région
        for (Location loc : locations) {
            String state = loc.getState();
            if (regionCounts.containsKey(state)) {
                regionCounts.put(state, regionCounts.get(state) + 1);
            } else {
                regionCounts.put(state, 1);
            }
        }

        // trouver la région la plus fréquente
        String mostFrequentState = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : regionCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostFrequentState = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        return mostFrequentState;
    }




    @Override
    // méthode pour trouver les trois localisations les plus proches
    public List<Location> getNearestLocations(Location location, List<Location> allLocations) {
        List<Location> nearestLocations = new ArrayList<>();

        // calculer les distances entre la localisation entrante et toutes les autres localisations
        for (Location loc : allLocations) {
            double distance = calculateDistance(location, loc);
            loc.setDistance(distance);
        }

        // trier la liste de toutes les localisations par ordre croissant de distance
        List<Location> sortedLocations = allLocations.stream()
                .sorted(Comparator.comparing(Location::getDistance))
                .collect(Collectors.toList());

        // ajouter les trois localisations les plus proches à la liste de résultat
        for (int i = 0; i < 3; i++) {
            if (i < sortedLocations.size()) {
                nearestLocations.add(sortedLocations.get(i));
            }
        }

        return nearestLocations;
    }

    // méthode pour calculer la distance entre deux localisations (en km)
    private double calculateDistance(Location loc1, Location loc2) {
        double lat1 = loc1.getLatitude();
        double lon1 = loc1.getLongitude();
        double lat2 = loc2.getLatitude();
        double lon2 = loc2.getLongitude();

        double theta = lon1 - lon2;
        double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344; // convertir en kilomètres

        return dist;
    }


    private List<Location> locations = new ArrayList<>();

    public LocationServiceImp() {
        locations.add(new Location("New York", 40.748817, -73.985428));
        locations.add(new Location("London", 51.509865, -0.118092));
        locations.add(new Location("Paris", 48.858844, 2.294351));
    }
    Location loc;
    @Override
    public Location getNearestLocation(double latitude, double longitude) {
        double minDistance = Double.MAX_VALUE;
        Location nearestLocation =null;
        for (Location location : locations) {
            double distance = LocationUtils.distance(latitude, longitude, location.getLatitude(), location.getLongitude());
            if (distance < minDistance) {
                minDistance = distance;
                nearestLocation = location;
            }
        }
        return nearestLocation;
    }
}