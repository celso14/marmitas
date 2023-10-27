package com.api.marmitas.controllers;

import java.util.List;

import com.api.marmitas.dtos.CostumerDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.marmitas.services.CostumerService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/clientes")
public class CostumerController {

    private final CostumerService costumerService;

    public CostumerController(CostumerService costumerService) {
        this.costumerService = costumerService;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<CostumerDTO> list() {
        return this.costumerService.getAll();
    }

    @GetMapping("/{id}")
    public CostumerDTO findById(@PathVariable @NotNull @Positive Long id) {
        return this.costumerService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CostumerDTO create(@RequestBody @Valid CostumerDTO costumerDTO) {
        return this.costumerService.create(costumerDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public CostumerDTO update(@PathVariable @NotNull @Positive Long id,
            @RequestBody @Valid CostumerDTO costumerDTO) {
        return this.costumerService.update(id, costumerDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        this.costumerService.delete(id);
    }
}
