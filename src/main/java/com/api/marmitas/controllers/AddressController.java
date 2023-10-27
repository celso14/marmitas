package com.api.marmitas.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.api.marmitas.dtos.AddressDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.marmitas.services.AddressService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/enderecos")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/rota")
    @ResponseStatus(code = HttpStatus.OK)
    public List<HashMap<String, String>> routePlanner(@RequestBody @Valid List<AddressDTO> addressDTOS) {
        return this.addressService.routePlanner(addressDTOS);
    }

//    @PostMapping("/{id}")
//    @ResponseStatus(code = HttpStatus.CREATED)
//    public AddressDTO create(@PathVariable @NotNull @Positive Long id
//            , @RequestBody @Validated AddressDTO addressDTO) {
//        return this.addressService.create(id, addressDTO);
//    }

    @PatchMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public AddressDTO update(@PathVariable @NotNull @Positive Long id,
            @RequestBody @Validated AddressDTO addressDTO) {
        return this.addressService.update(id, addressDTO);
    }
}
