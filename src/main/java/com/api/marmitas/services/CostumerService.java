package com.api.marmitas.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.api.marmitas.dtos.output.CostumerOutputDTO;
import com.api.marmitas.entities.Costumer;
import com.api.marmitas.repositories.CostumerRepository;

@Service
public class CostumerService {

    private final CostumerRepository costumerRepository;
    private final ModelMapper modelMapper;

    public CostumerService(CostumerRepository costumerRepository, ModelMapper modelMapper) {
        this.costumerRepository = costumerRepository;
        this.modelMapper = modelMapper;
    }

    public List<CostumerOutputDTO> getAll() {
        return this.costumerRepository.findAll().stream()
                .map(costumer -> modelMapper.map(costumer, CostumerOutputDTO.class))
                .collect(Collectors.toList());
    }
}
