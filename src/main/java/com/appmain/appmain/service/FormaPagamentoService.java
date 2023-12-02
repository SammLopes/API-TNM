package com.appmain.appmain.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.appmain.appmain.model.FormaPagamento;
import com.appmain.appmain.model.repositorys.FormaPagamentoRepository;

@Service
public class FormaPagamentoService {
    
    @Autowired
    private FormaPagamentoRepository repository;

    public FormaPagamento salva(FormaPagamento formaPagamento){
        return this.repository.save(formaPagamento);
    }

    public FormaPagamento seleciona(Long id){
        FormaPagamento forma = this.repository.findById(id).get();
        if(forma != null){
            return forma;
        }
        return null;
    }

    public FormaPagamento exclui(Long id){
        FormaPagamento forma = this.seleciona(id);
        if(forma != null){
            this.repository.delete(forma);
        }
        return forma;
    }

    public List<FormaPagamento> listaTodas(){
        return this.repository.findAll(Sort.by(Sort.Direction.ASC, "formaPagamentoId", "nome", "pedidos"));
    }

    public List<FormaPagamento> buscar(String termo){
        List<FormaPagamento> listaFormas = this.listaTodas();
        List<FormaPagamento> formasEncotradas = new ArrayList<>();
        for(FormaPagamento forma : listaFormas){
            if(forma.getFormaPagamentoId().toString().contains(termo) || 
            forma.getNome().contains(termo) || 
            forma.getPedidos().toString().contains(termo)){
             formasEncotradas.add(forma);
            }
        }
        return formasEncotradas;
    }
}
