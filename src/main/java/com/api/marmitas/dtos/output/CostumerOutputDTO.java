package com.api.marmitas.dtos.output;

import java.io.Serializable;
import java.util.List;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Validated
public class CostumerOutputDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @NotBlank
    private Long id;

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @NotNull
    @NotBlank
    private String nickName;

    @NotNull
    @NotBlank
    private String phoneNumber;

    List<AddressOutputDTO> adresses;
}