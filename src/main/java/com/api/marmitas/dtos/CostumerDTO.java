package com.api.marmitas.dtos;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class CostumerDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    private Long id;


    private String firstName;


    private String lastName;


    private String nickName;


    private String phoneNumber;

    private List<AddressDTO> addresses;

    public CostumerDTO() {
    }

    public CostumerDTO(Long id, String firstName, String lastName, String nickName, String phoneNumber, List<AddressDTO> addresses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.addresses = addresses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<AddressDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDTO> addresses) {
        this.addresses = addresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CostumerDTO that = (CostumerDTO) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return new StringBuilder("CostumerDTO{")
        .append("id=").append(id)
        .append(", firstName='").append(firstName).append('\'')
        .append(", lastName='").append(lastName).append('\'')
        .append(", nickName='").append(nickName).append('\'')
        .append(", phoneNumber='").append(phoneNumber).append('\'')
        .append(", addresses=").append(addresses)
        .append('}').toString();
    }
}
