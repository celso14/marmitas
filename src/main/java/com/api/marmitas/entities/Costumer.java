package com.api.marmitas.entities;

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
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@SQLDelete(sql = "UPDATE costumers SET status = false WHERE id = ?")
@Where(clause = "status = true")
@Table(name = "costumers")
@ToString(exclude = {"adresses"}) // exclui o atributo adresses do toString por causa do problema de recursão 
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

    // @JoinColumn(name = "costumer_id") não recomendo pelo problema sql n+1
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "costumer")
    private List<Address> adresses = new ArrayList<>();
}
