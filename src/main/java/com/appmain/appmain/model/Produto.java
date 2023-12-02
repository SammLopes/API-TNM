package com.appmain.appmain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long produtoId;
    @Column(length= 150, nullable = true)
    private String name;
    @Column(length = 255, nullable = true)
    private String descricao;
    @Column(nullable = true)
    private Double preco; 
    @ManyToOne
    private ItemPedido itemPedido ;
    
    
}
