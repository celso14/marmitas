package com.api.marmitas.controllers;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.marmitas.entities.Address;
import com.api.marmitas.services.RoutePlannerService;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/rotas")
@Validated
@AllArgsConstructor
public class RoutePlannerController {

    private final RoutePlannerService routePlannerService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Map<String, Object> planner() {
        Address a = new Address();
        a.setName("São Miguel");
        a.setAddressLocation("Rua");
        a.setNumber("573");
        a.setNeighborhood("Jurunas");
        a.setAddressType("Casa");
        a.setReference("Entre Apinagés e Tupinanbás, apt 1501");
        return routePlannerService.getLatLng(a);
    }
}
