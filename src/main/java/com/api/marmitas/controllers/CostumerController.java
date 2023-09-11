package com.api.marmitas.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.marmitas.dtos.input.create.CreateCostumerInputDTO;
import com.api.marmitas.dtos.input.update.UpdateCostumerInputDTO;
import com.api.marmitas.dtos.output.CostumerOutputDTO;
import com.api.marmitas.services.CostumerService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/costumers")
@Validated
@AllArgsConstructor
public class CostumerController {

    private final CostumerService costumerService;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<CostumerOutputDTO> list() {
        return this.costumerService.getAll();
    }

    @GetMapping("/{id}")
    public CostumerOutputDTO findById(@PathVariable @NotNull @Positive Long id) {
        return this.costumerService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CostumerOutputDTO create(@RequestBody @Validated CreateCostumerInputDTO costumerInputDTO) {
        return this.costumerService.create(costumerInputDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public CostumerOutputDTO update(@PathVariable @NotNull @Positive Long id, @RequestBody @Validated UpdateCostumerInputDTO costumerInputDTO) {
        return this.costumerService.update(id, costumerInputDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        this.costumerService.delete(id);
    }
}
