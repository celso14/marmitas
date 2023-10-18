package com.api.marmitas.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.api.marmitas.dtos.input.create.AddressCreateDTO;
import com.api.marmitas.dtos.input.update.AddressUpdateDTO;
import com.api.marmitas.dtos.output.AddressOutputDTO;
import com.api.marmitas.entities.Address;
import com.api.marmitas.entities.Costumer;
import com.api.marmitas.exceptions.GeoCodeException;
import com.api.marmitas.exceptions.NotFoundException;
import com.api.marmitas.repositories.AddressRepository;
import com.api.marmitas.repositories.CostumerRepository;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final CostumerRepository costumerRepository;
    private final GeoContextService geoContextService;
    private final ModelMapper modelMapper;

    public AddressService(AddressRepository addressRepository,
            ModelMapper modelMapper, CostumerRepository costumerRepository, GeoContextService geoContextService) {
        this.modelMapper = modelMapper;
        this.addressRepository = addressRepository;
        this.costumerRepository = costumerRepository;
        this.geoContextService = geoContextService;
    }

    public AddressOutputDTO create(Long id, AddressCreateDTO addressCreateDTO) {
        Costumer costumer = this.costumerRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        Address address = this.modelMapper.map(addressCreateDTO, Address.class);

        try {
            this.geoContextService.getLatLng(address);
        } catch (Exception e) {
            throw new GeoCodeException(e.getMessage());
        }

        address.setCostumer(costumer);

        return this.modelMapper.map(this.addressRepository.save(address), AddressOutputDTO.class);
    }

    public AddressOutputDTO update(Long id, AddressUpdateDTO addressUpdateDTO) {
        Address address = this.addressRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        
        boolean newGeoCodeRequestIsRequired = 
            (addressUpdateDTO.getName() == null) || (addressUpdateDTO.getNumber() == null) || (addressUpdateDTO.getAddressType() == null);
    
        if (newGeoCodeRequestIsRequired) {
            try {
                Map<String, Double> location = this.geoContextService.getLatLng(address);
                address.setLat( location.get("lat"));
                address.setLng( location.get("lng"));
            } catch (Exception e) {
                throw new GeoCodeException(e.getMessage());
            }
        }

        if (addressUpdateDTO.getName() != null) address.setName(addressUpdateDTO.getName());
        if (addressUpdateDTO.getNumber() != null) address.setNumber(addressUpdateDTO.getNumber());
        if (addressUpdateDTO.getAddressType() != null) address.setAddressType(addressUpdateDTO.getAddressType());
        if (addressUpdateDTO.getReference() != null) address.setReference(addressUpdateDTO.getReference());
        if (addressUpdateDTO.getNeighborhood() != null) address.setNeighborhood(addressUpdateDTO.getNeighborhood());
        if (addressUpdateDTO.getAddressLocation() != null) address.setAddressLocation(addressUpdateDTO.getAddressLocation());
    
        return this.modelMapper.map(this.addressRepository.save(address), AddressOutputDTO.class);
    }

    public List<Map<String, Double>> routePlanner(List<Long> listID) {
        List<Address> addresses = this.addressRepository.findAllById(listID);
        List<Map<String, Double>> locationList = null;

        if (addresses.isEmpty()) {
            throw new GeoCodeException("Endereços não encontrados");
        }

        locationList = addresses.stream().map(address -> Map.of("lat", address.getLat(), "lng", address.getLng())).toList();

        
        
        return locationList;
    }
}
