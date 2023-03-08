package com.example.pi_project.services;


import com.example.pi_project.entities.Location;

import java.util.List;

public interface LocationService {
    public List<Location> getAllLocations();

    public Location addLocation(Location location);


    public Location updateLocation(Location location);
    public void deleteLocation(int idLocation);
}
