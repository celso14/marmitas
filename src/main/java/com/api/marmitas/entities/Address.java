package com.api.marmitas.entities;

import java.io.Serial;
import java.io.Serializable;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "enderecos")
@SQLDelete(sql = "UPDATE adresses SET status = false WHERE id = ?")
@Where(clause = "status = true")
public class Address implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 20, nullable = false)
    private String number;

    @Column(length = 30, nullable = false)
    private String addressType;

    @Column(length = 150, nullable = false)
    private String name;

    @Column(length = 30, nullable = false)
    private String addressLocation;

    @Column(length = 100, nullable = false)
    private String reference;

    @Column(length = 50, nullable = false)
    private String neighborhood;

    @Column(length = 150, nullable = true)
    private Double lat;

    @Column(length = 150, nullable = true)
    private Double lng;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "costumer_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Costumer costumer;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean status = true;

    public Address() {
    }

    public Address(String number, String addressType, String name, String addressLocation, String reference, String neighborhood, Double lat, Double lng, Costumer costumer) {
        this.number = number;
        this.addressType = addressType;
        this.name = name;
        this.addressLocation = addressLocation;
        this.reference = reference;
        this.neighborhood = neighborhood;
        this.lat = lat;
        this.lng = lng;
        this.costumer = costumer;
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

    public Costumer getCostumer() {
        return costumer;
    }

    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        return id.equals(address.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return new StringBuilder("Address{")
        .append("id=").append(id)
        .append(", number='").append(number).append('\'')
        .append(", addressType='").append(addressType).append('\'')
        .append(", name='").append(name).append('\'')
        .append(", addressLocation='").append(addressLocation).append('\'')
        .append(", reference='").append(reference).append('\'')
        .append(", neighborhood='").append(neighborhood).append('\'')
        .append(", lat=").append(lat)
        .append(", lng=").append(lng)
        .append(", costumer=").append(costumer)
        .append(", status=").append(status)
        .append('}').toString();
    }
}
