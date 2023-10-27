package com.api.marmitas.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.io.Serial;
import java.io.Serializable;

@Validated
public class AddressDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String number;

    private String addressType;

    private String name;

    private String addressLocation;

    private String reference;

    private String neighborhood;

    private Double lat;

    private Double lng;

    public AddressDTO() {
    }

    public AddressDTO(Long id, String number, String addressType, String name, String addressLocation, String reference, String neighborhood, Double lat, Double lng) {
        this.id = id;
        this.number = number;
        this.addressType = addressType;
        this.name = name;
        this.addressLocation = addressLocation;
        this.reference = reference;
        this.neighborhood = neighborhood;
        this.lat = lat;
        this.lng = lng;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressLocation() {
        return addressLocation;
    }

    public void setAddressLocation(String addressLocation) {
        this.addressLocation = addressLocation;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressDTO that = (AddressDTO) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return new StringBuilder("AddressDTO{")
        .append("id=").append(id)
        .append(", number='").append(number).append('\'')
        .append(", addressType='").append(addressType).append('\'')
        .append(", name='").append(name).append('\'')
        .append(", addressLocation='").append(addressLocation).append('\'')
        .append(", reference='").append(reference).append('\'')
        .append(", neighborhood='").append(neighborhood).append('\'')
        .append(", lat=").append(lat)
        .append(", lng=").append(lng)
        .append('}').toString();
    }
}
