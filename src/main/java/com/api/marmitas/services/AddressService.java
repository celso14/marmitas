package com.api.marmitas.services;

import java.util.*;

import com.api.marmitas.dtos.AddressDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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

    public AddressDTO create(Long id, AddressDTO addressDTO) {
        Costumer costumer = this.costumerRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        Address address = this.modelMapper.map(addressDTO, Address.class);

        try {
            this.geoContextService.getLatLng(address);
        } catch (Exception e) {
            throw new GeoCodeException(e.getMessage());
        }

        address.setCostumer(costumer);

        return this.modelMapper.map(this.addressRepository.save(address), AddressDTO.class);
    }

    public AddressDTO update(Long id, AddressDTO addressDTO) {
        Address address = this.addressRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

        boolean newGeoCodeRequestIsRequired =
            (addressDTO.getName() == null) || (addressDTO.getNumber() == null) || (addressDTO.getAddressType() == null);

        if (newGeoCodeRequestIsRequired) {
            try {
                Map<String, Double> location = this.geoContextService.getLatLng(address);
                address.setLat( location.get("lat"));
                address.setLng( location.get("lng"));
            } catch (Exception e) {
                throw new GeoCodeException(e.getMessage());
            }
        }

        if (addressDTO.getName() != null) address.setName(addressDTO.getName());
        if (addressDTO.getNumber() != null) address.setNumber(addressDTO.getNumber());
        if (addressDTO.getAddressType() != null) address.setAddressType(addressDTO.getAddressType());
        if (addressDTO.getReference() != null) address.setReference(addressDTO.getReference());
        if (addressDTO.getNeighborhood() != null) address.setNeighborhood(addressDTO.getNeighborhood());
        if (addressDTO.getAddressLocation() != null) address.setAddressLocation(addressDTO.getAddressLocation());

        return this.modelMapper.map(this.addressRepository.save(address), AddressDTO.class);
    }

    public List<HashMap<String, String>> routePlanner(List<AddressDTO> addressDTOS) {
        String[] listLatLng = addressDTOS.stream().map(address -> address.getLat() + "," + address.getLng()).toList().toArray(new String[addressDTOS.size()]);

        var routeOrder = this.geoContextService.getRoutePlanner(listLatLng);

        List<HashMap<String, String>> addressOrder = new ArrayList<>();

        for (int i = 0; i < routeOrder.length; i++) {
            addressOrder.add(new HashMap<>());
            var address = addressDTOS.get(i);

            addressOrder.get(i).put("Address", address.getAddressType() + " - " + address.getAddressLocation() + " " +
                    address.getName() + " - " + address.getNumber() + " - " + address.getNeighborhood() + " -" + address.getReference());
            addressOrder.get(i).put("Position", String.valueOf(routeOrder[i]));
        }

        return addressOrder;
    }
}
