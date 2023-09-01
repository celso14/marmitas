package com.api.marmitas.entities;

import java.io.Serializable;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
@SQLDelete(sql = "UPDATE Course SET status = false WHERE id = ?")
@Where(clause = "status = true")
public class Costumer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50, nullable = false)
    private String firstName;

    @Column(length = 50, nullable = false)
    private String lastName;

    @Column(length = 50, nullable = false)
    private String nickName;

    @Column(length = 21, nullable = false)
    private String phoneNumber;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean status = true;
}
