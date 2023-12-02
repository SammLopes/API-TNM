package com.appmain.appmain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(indexes = {
    @Index(columnList = "login", unique = true, name = "IDX_USUARIO_LOGIN")
})
public class Usuarios implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long usuarioId;
    private LocalDateTime data;
    @Column(length = 255)
    private String nome;
    @Column(length = 255)
    private String senha; 
    private String telefone;
    @Column(length = 255)
    private String email;
    @OneToMany
    @JoinColumn(name="usuarios")
    private List<Caixa> caixas;
    @ManyToMany
    @JoinTable(name= "autorizacoes_usuarios", joinColumns = {@JoinColumn(name="usuario_id")},  inverseJoinColumns = {
        @JoinColumn(name="aut_id") })
    private List<Autorizacao> autorizacoes;
    @Column(name = "login", nullable = false, length = 100)
    private String login;
    private String permissoes;
}
