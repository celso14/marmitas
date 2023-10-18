package com.api.marmitas.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.api.marmitas.entities.Address;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
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

    // public void getRoutePlannerList(List<Map<String, Double>> locationsList) {
    //     LatLng[] locations = new LatLng[locationsList.size()];
    //     LatLng origin = new LatLng(-1.467237, -48.485901);
    //     locations = locationsList.stream().map(location -> new LatLng(location.get("lat"), location.get("lng"))).toArray(LatLng[]::new);
    //     var result = DistanceMatrixApi.getDistanceMatrix(context, origin, locations);
    //     System.out.println(result);
    // }

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
