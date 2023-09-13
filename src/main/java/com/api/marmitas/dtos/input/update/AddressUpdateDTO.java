package com.api.marmitas.dtos.input.update;

import java.io.Serializable;

import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Data
@Validated
public class AddressUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String number;

    private String addressType;

    private String name;

    private String addressLocation;

    private String reference;

    private String neighborhood;
}
