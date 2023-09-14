package com.api.marmitas.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.marmitas.dtos.input.update.AddressUpdateDTO;
import com.api.marmitas.dtos.output.AddressOutputDTO;
import com.api.marmitas.services.AddressService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/enderecos")
@Validated
@AllArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PatchMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public AddressOutputDTO update(@PathVariable @NotNull @Positive Long id,
            @RequestBody @Validated AddressUpdateDTO addressUpdateDTO) {
        return this.addressService.update(id, addressUpdateDTO);
    }
}
