package com.appmain.appmain.model;

import java.util.List;
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
public class ItemPedido{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    private int quantidade;
    //@Column(nullable = true, precision = 10, scale = 2)
    @Column(nullable = true)
    private Double preco;

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
