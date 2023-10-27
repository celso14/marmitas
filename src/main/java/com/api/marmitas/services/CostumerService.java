package com.api.marmitas.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.api.marmitas.dtos.CostumerDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.api.marmitas.entities.Costumer;
import com.api.marmitas.exceptions.GeoCodeException;
import com.api.marmitas.exceptions.NotFoundException;
import com.api.marmitas.repositories.CostumerRepository;

@Service
public class CostumerService {

    private final CostumerRepository costumerRepository;
    private final ModelMapper modelMapper;
    private final GeoContextService geoContextService;

    public CostumerService(CostumerRepository costumerRepository,
            ModelMapper modelMapper, GeoContextService geoContextService) {
        this.costumerRepository = costumerRepository;
        this.modelMapper = modelMapper;
        this.geoContextService = geoContextService;
    }

    public List<CostumerDTO> getAll() {
        return this.costumerRepository.findAll().stream()
                .map(costumer -> this.modelMapper.map(costumer, CostumerDTO.class))
                .collect(Collectors.toList());
    }

    public CostumerDTO findById(Long id) {
        Costumer costumer = this.costumerRepository.findById(id).orElseThrow(
                () -> new NotFoundException(id));
        return this.modelMapper.map(costumer, CostumerDTO.class);
    }

    public CostumerDTO create(CostumerDTO costumerDTO) {
        Costumer costumer = this.modelMapper.map(costumerDTO, Costumer.class);

        costumer.getAddresses().forEach(address -> {
            try {
                Map<String, Double> location = this.geoContextService.getLatLng(address);
                address.setLat(location.get("lat"));
                address.setLng(location.get("lng"));
                System.out.println(location);
            } catch (Exception e) {
                throw new GeoCodeException(e.getMessage());
            }
            address.setCostumer(costumer);
        });

        return this.modelMapper.map(this.costumerRepository.save(costumer), CostumerDTO.class);
    }

    public CostumerDTO update(Long id, CostumerDTO costumerDTO) {
        Costumer costumer = this.costumerRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

        if (costumerDTO.getFirstName() != null
                && !costumerDTO.getFirstName().equals(costumer.getFirstName())) {
            costumer.setFirstName(costumerDTO.getFirstName());
        }

        if (costumerDTO.getLastName() != null
                && !costumerDTO.getLastName().equals(costumer.getLastName())) {
            costumer.setLastName(costumerDTO.getLastName());
        }

        if (costumerDTO.getNickName() != null
                && !costumerDTO.getNickName().equals(costumer.getNickName())) {
            costumer.setNickName(costumerDTO.getNickName());
        }

        if (costumerDTO.getPhoneNumber() != null
                && !costumerDTO.getPhoneNumber().equals(costumer.getPhoneNumber())) {
            costumer.setPhoneNumber(costumerDTO.getPhoneNumber());
        }

        return this.modelMapper.map(this.costumerRepository.save(costumer), CostumerDTO.class);
    }

    public void delete(Long id) {
        this.costumerRepository.findById(id).map(costumer -> {
            this.costumerRepository.deleteById(id);
            return true;
        }).orElseThrow(() -> new NotFoundException(id));
    }
}
