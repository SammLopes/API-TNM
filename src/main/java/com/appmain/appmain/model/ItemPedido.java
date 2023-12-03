package com.appmain.appmain.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

//import java.util.ArrayList;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import com.appmain.appmain.model.Produto;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ItemPedido{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    private int quantidade;
    @Column(nullable = true)
    private Double preco;
    @JsonIgnore
    @OneToMany(mappedBy = "itemPedido")
    private List<Produto> produtos;

    @ManyToMany
    @JoinTable(
        name = "itempedido_acompanhamento",
        joinColumns = @JoinColumn(name = "itempedido_id"),
        inverseJoinColumns = @JoinColumn(name = "acompanhamento_id")
    )
    private List<Acompanhamento> acompanhamentos;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

}
