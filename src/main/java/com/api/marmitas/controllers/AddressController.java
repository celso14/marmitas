package com.api.marmitas.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.marmitas.dtos.input.create.AddressCreateDTO;
import com.api.marmitas.dtos.input.update.AddressUpdateDTO;
import com.api.marmitas.dtos.output.AddressOutputDTO;
import com.api.marmitas.services.AddressService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
@RequestMapping("/api/enderecos")
@Validated
@AllArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/rota")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Map<String, Double>> routePlanner(@RequestParam List<Long> id) {
        return this.addressService.routePlanner(id);
    }

    @PostMapping("/{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public AddressOutputDTO create(@PathVariable @NotNull @Positive Long id
            , @RequestBody @Validated AddressCreateDTO addressCreateDTO) {
        return this.addressService.create(id, addressCreateDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public AddressOutputDTO update(@PathVariable @NotNull @Positive Long id,
            @RequestBody @Validated AddressUpdateDTO addressUpdateDTO) {
        return this.addressService.update(id, addressUpdateDTO);
    }
}
