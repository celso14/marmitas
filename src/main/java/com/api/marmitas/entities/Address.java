package com.api.marmitas.entities;

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
import lombok.Data;

@Entity
@Data
@Table(name = "adresses")
@SQLDelete(sql = "UPDATE adresses SET status = false WHERE id = ?")
@Where(clause = "status = true")
public class Address implements Serializable {

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
}
