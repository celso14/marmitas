package com.api.marmitas.controllers;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.marmitas.dtos.output.CostumerOutputDTO;
import com.api.marmitas.services.CostumerService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/costumers")
@Validated
@AllArgsConstructor
public class CostumerController {

    private final CostumerService costumerService;

    @GetMapping
    public List<CostumerOutputDTO> list() {
        return this.costumerService.getAll();
    }
}
