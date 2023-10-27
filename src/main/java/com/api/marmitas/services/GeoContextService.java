package com.api.marmitas.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.maps.*;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;
import org.aspectj.weaver.ast.Instanceof;
import org.springframework.stereotype.Service;

import com.api.marmitas.entities.Address;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;


@Service
public class GeoContextService {

    private final GeoApiContext context;

    public GeoContextService(GeoApiContext context) {
        this.context = context;
    }

    public Map<String, Double> getLatLng(Address address) {
        StringBuilder addressBuilder = new StringBuilder();
        addressBuilder.append(address.getAddressType())
        .append(" ").append(address.getName())
        .append(", ").append(address.getNumber())
        .append("- ").append(address.getNeighborhood())
        .append(", Belém - PA");

        return getGeoCodingData(addressBuilder.toString());
    }

    public int[] getRoutePlanner(String[] locations) {
        int[] waypoints = new int[locations.length];
        try {
            DirectionsResult result = DirectionsApi.newRequest(context)
                    .mode(TravelMode.DRIVING)
                    .origin("-1.467237,-48.485901")
                    .optimizeWaypoints(true)
                    .waypoints(locations)
                    .destination("-1.467237,-48.485901")
                    .await();
            waypoints = result.routes[0].waypointOrder;
        }
        catch (ApiException | InterruptedException | IOException e){
            e.printStackTrace();
        }
        return waypoints;
    }

    private Map<String, Double> getGeoCodingData(String address) {
        Map<String, Double> location = null;

        try {
            GeocodingResult[] results = GeocodingApi.geocode(this.context, address).await();
            location = Map.of("lat", results[0].geometry.location.lat, "lng",
                    results[0].geometry.location.lng);
        } catch (ApiException | InterruptedException | IOException e) {
            e.printStackTrace();
        }

        // Invoke .shutdown() after your application is done making requests
        this.context.shutdown();

        return location;
    }
}
