package com.api.marmitas.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.api.marmitas.dtos.input.update.AddressUpdateDTO;
import com.api.marmitas.dtos.output.AddressOutputDTO;
import com.api.marmitas.entities.Address;
import com.api.marmitas.exceptions.NotFoundException;
import com.api.marmitas.repositories.AddressRepository;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    public AddressService(AddressRepository addressRepository,
            ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.addressRepository = addressRepository;
    }

    public AddressOutputDTO update(Long id, AddressUpdateDTO addressUpdateDTO) {
        Address address = this.addressRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

        if (addressUpdateDTO.getName() != null) {
            address.setName(addressUpdateDTO.getName());
        }

        if (addressUpdateDTO.getNumber() != null) {
            address.setNumber(addressUpdateDTO.getNumber());
        }

        if (addressUpdateDTO.getAddressType() != null) {
            address.setAddressType(addressUpdateDTO.getAddressType());
        }

        if (addressUpdateDTO.getReference() != null) {
            address.setReference(addressUpdateDTO.getReference());
        }

        if (addressUpdateDTO.getNeighborhood() != null) {
            address.setNeighborhood(addressUpdateDTO.getNeighborhood());
        }

        if (addressUpdateDTO.getAddressLocation() != null) {
            address.setAddressLocation(addressUpdateDTO.getAddressLocation());
        }

        return this.modelMapper.map(this.addressRepository.save(address), AddressOutputDTO.class);
    }
}
