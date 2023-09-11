package com.api.marmitas.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.api.marmitas.dtos.input.create.CreateCostumerInputDTO;
import com.api.marmitas.dtos.input.update.UpdateCostumerInputDTO;
import com.api.marmitas.dtos.output.CostumerOutputDTO;
import com.api.marmitas.entities.Costumer;
import com.api.marmitas.exceptions.NotFoundException;
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

    public CostumerOutputDTO findById(Long id) {
        Costumer costumer = this.costumerRepository.findById(id).orElseThrow(
                () -> new NotFoundException(id));
        return modelMapper.map(costumer, CostumerOutputDTO.class);
    }

    public CostumerOutputDTO create(CreateCostumerInputDTO createCostumerDTO) {
        if (createCostumerDTO.getId() != null) {
            throw new RuntimeException("Id não pode ser informado para criação de um registro.");
        }

        Costumer costumer = this.modelMapper.map(createCostumerDTO, Costumer.class);
        return modelMapper.map(this.costumerRepository.save(costumer), CostumerOutputDTO.class);
    }

    public CostumerOutputDTO update(Long id, UpdateCostumerInputDTO updateCostumerDTO) {
        Costumer costumer = this.costumerRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

        if (updateCostumerDTO.getFirstName() != null && !updateCostumerDTO.getFirstName().equals(costumer.getFirstName())) {
            costumer.setFirstName(updateCostumerDTO.getFirstName());
        }
    
        if (updateCostumerDTO.getLastName() != null && !updateCostumerDTO.getLastName().equals(costumer.getLastName())) {
            costumer.setLastName(updateCostumerDTO.getLastName());
        }
    
        if (updateCostumerDTO.getNickName() != null && !updateCostumerDTO.getNickName().equals(costumer.getNickName())) {
            costumer.setNickName(updateCostumerDTO.getNickName());
        }
    
        if (updateCostumerDTO.getPhoneNumber() != null && !updateCostumerDTO.getPhoneNumber().equals(costumer.getPhoneNumber())) {
            costumer.setPhoneNumber(updateCostumerDTO.getPhoneNumber());
        }

        return modelMapper.map(this.costumerRepository.save(costumer), CostumerOutputDTO.class);
    }

    public void delete(Long id) {
        this.costumerRepository.findById(id).map(costumer -> {
            this.costumerRepository.deleteById(id);
            return true;
        }).orElseThrow(() -> new NotFoundException(id));
    }
}
