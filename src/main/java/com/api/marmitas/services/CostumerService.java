package com.api.marmitas.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.api.marmitas.dtos.input.create.CostumerCreateDTO;
import com.api.marmitas.dtos.input.update.CostumerUpdateDTO;
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

    public CostumerOutputDTO create(CostumerCreateDTO costumerCreateDTO) {
        Costumer costumer = this.modelMapper.map(costumerCreateDTO, Costumer.class);

        costumer.getAdresses().forEach(address -> {
            address.setCostumer(costumer);
        });

        return modelMapper.map(this.costumerRepository.save(costumer), CostumerOutputDTO.class);
    }

    public CostumerOutputDTO update(Long id, CostumerUpdateDTO costumerUpdateDTO) {
        Costumer costumer = this.costumerRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

        if (costumerUpdateDTO.getFirstName() != null && !costumerUpdateDTO.getFirstName().equals(costumer.getFirstName())) {
            costumer.setFirstName(costumerUpdateDTO.getFirstName());
        }
    
        if (costumerUpdateDTO.getLastName() != null && !costumerUpdateDTO.getLastName().equals(costumer.getLastName())) {
            costumer.setLastName(costumerUpdateDTO.getLastName());
        }
    
        if (costumerUpdateDTO.getNickName() != null && !costumerUpdateDTO.getNickName().equals(costumer.getNickName())) {
            costumer.setNickName(costumerUpdateDTO.getNickName());
        }
    
        if (costumerUpdateDTO.getPhoneNumber() != null && !costumerUpdateDTO.getPhoneNumber().equals(costumer.getPhoneNumber())) {
            costumer.setPhoneNumber(costumerUpdateDTO.getPhoneNumber());
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
