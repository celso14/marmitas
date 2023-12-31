package com.api.marmitas.entities;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@SQLDelete(sql = "UPDATE costumers SET status = false WHERE id = ?")
@Where(clause = "status = true")
@Table(name = "clientes")
public class Costumer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50, nullable = false)
    private String firstName;

    @Column(length = 50, nullable = false)
    private String lastName;

    @Column(length = 50, nullable = true)
    private String nickName;

    @Column(length = 21, nullable = false)
    private String phoneNumber;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean status = true;

    // @JoinColumn(name = "costumer_id") não recomendo pelo problema sql n+1
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "costumer")
    private List<Address> addresses = new ArrayList<>();

    public Costumer() {
    }

    public Costumer(String firstName, String lastName, String nickName, String phoneNumber, List<Address> addresses) {
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Costumer costumer = (Costumer) o;

        return id.equals(costumer.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return new StringBuilder("Costumer{")
        .append("id=").append(id)
        .append(", firstName='").append(firstName).append('\'')
        .append(", lastName='").append(lastName).append('\'')
        .append(", nickName='").append(nickName).append('\'')
        .append(", phoneNumber='").append(phoneNumber).append('\'')
        .append(", status=").append(status)
        .append(", addresses=").append(addresses)
        .append('}').toString();
    }
}
