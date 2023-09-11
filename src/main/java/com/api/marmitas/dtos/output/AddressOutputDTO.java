package com.api.marmitas.dtos.output;

import java.io.Serializable;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Validated
public class AddressOutputDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank
    @NotNull
    private String number;

    @NotBlank
    @NotNull
    private String addressType;

    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private String addressLocation;

    @NotBlank
    @NotNull
    private String reference;

    @NotBlank
    @NotNull
    private String neighborhood;
}
