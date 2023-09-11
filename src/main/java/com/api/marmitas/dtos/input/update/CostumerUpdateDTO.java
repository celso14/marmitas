package com.api.marmitas.dtos.input.update;

import java.io.Serializable;

import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Data
@Validated
public class CostumerUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    
    private String firstName;

    private String lastName;

    private String nickName;

    private String phoneNumber;
}
