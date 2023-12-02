package com.appmain.appmain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Caixa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCaixa;
    
    private LocalDateTime dataFechamento;
    @Column(precision = 10, scale = 2)
    private BigDecimal valorTotalVendas;
    @Column(precision = 10, scale = 2)
    private BigDecimal total;
    @OneToMany(mappedBy = "caixa")
    private List<Pedido> pedidos;
    @ManyToOne
    @JoinColumn(name = "usuraio_id")
    private Usuarios usuario;



}
