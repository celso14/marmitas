package com.api.marmitas.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.api.marmitas.dtos.input.CostumerInputDTO;
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

    public CostumerOutputDTO create(CostumerInputDTO costumerInputDTO) {
        if (costumerInputDTO.getId() != null) {
            throw new RuntimeException("Id não pode ser informado para criação de um registro.");
        }

        Costumer costumer = this.modelMapper.map(costumerInputDTO, Costumer.class);
        return modelMapper.map(this.costumerRepository.save(costumer), CostumerOutputDTO.class);
    }

    public CostumerOutputDTO update(Long id, CostumerInputDTO costumerInputDTO) {
        Costumer costumer = this.costumerRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

        if (costumerInputDTO.getFirstName() != null && !costumerInputDTO.getFirstName().equals(costumer.getFirstName())) {
            costumer.setFirstName(costumerInputDTO.getFirstName());
        }
    
        if (costumerInputDTO.getLastName() != null && !costumerInputDTO.getLastName().equals(costumer.getLastName())) {
            costumer.setLastName(costumerInputDTO.getLastName());
        }
    
        if (costumerInputDTO.getNickName() != null && !costumerInputDTO.getNickName().equals(costumer.getNickName())) {
            costumer.setNickName(costumerInputDTO.getNickName());
        }
    
        if (costumerInputDTO.getPhoneNumber() != null && !costumerInputDTO.getPhoneNumber().equals(costumer.getPhoneNumber())) {
            costumer.setPhoneNumber(costumerInputDTO.getPhoneNumber());
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
