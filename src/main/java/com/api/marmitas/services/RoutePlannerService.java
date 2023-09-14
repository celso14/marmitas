package com.api.marmitas.services;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.api.marmitas.entities.Address;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

import io.github.cdimascio.dotenv.Dotenv;

@Service
public class RoutePlannerService {

    public Map<String, Object> getLatLng(Address address) {
        StringBuilder addressBuilder = new StringBuilder();
        addressBuilder.append(address.getNumber()).append(" ").append(address.getAddressType())
                .append(address.getName()).append(", Belém, PA");

        return getGeoCodingData(addressBuilder.toString());
    }

    private Map<String, Object> getGeoCodingData(String address) {
        Dotenv dotenv = Dotenv.load();
        String key = dotenv.get("API_KEY_GEOLOCATION");

        Map<String, Object> location = null;

        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(key)
                .build();
        GeocodingResult[] results;

        try {
            results = GeocodingApi.geocode(context,
                    address).await();

            location = Map.of("lat", results[0].geometry.location.lat, "lng",
                    results[0].geometry.location.lng);
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Invoke .shutdown() after your application is done making requests
        context.shutdown();

        return location;
    }
}
