package com.appmain.appmain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pedidoId;
    private LocalDateTime dataPedido;
    @Column(length = 255)
    private String observacao;
    @Column(precision = 10, scale = 2)
    private BigDecimal total;
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens =  new ArrayList<ItemPedido>();
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
    @ManyToOne
    @JoinColumn(name="caixa_id")
    private Caixa caixa;
    @ManyToOne
    @JoinColumn(name="formaPagamento_id")
    private FormaPagamento formaPagamento;
    
}   
