package com.appmain.appmain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.appmain.appmain.model.Acompanhamento;
import com.appmain.appmain.model.Pedido;
import com.appmain.appmain.model.Status;
import com.appmain.appmain.model.repositorys.AcompanhamentoRepository;

@Service
public class AcompanhamentoService {
    
    @Autowired
    AcompanhamentoRepository repository;

    public Acompanhamento salva(Acompanhamento acom){
        return this.repository.save(acom);
    }

    public Acompanhamento seleciona(Long id ){
        Acompanhamento acom = this.repository.findById(id).get();
        if(acom != null){
            return acom;
        }
        return null;
    }

    public List<Acompanhamento> listaTodas(){
        return this.repository.findAll(Sort.by(Sort.Direction.ASC,"id", "acompanhamento", "itemPedidos"));
    }

    public List<Acompanhamento> buscar(String termo){
        List<Acompanhamento> todosAcompanhamentos = this.listaTodas();
        List<Acompanhamento> acompanhamentosEncontrados = new ArrayList<>();
        for(Acompanhamento acom : todosAcompanhamentos){
            if(acom.getId().toString().contains(termo) || acom.getItemPedidos().toString().contains(termo) || acom.getItemPedidos().contains(termo)){
                acompanhamentosEncontrados.add(acom);
            }
        }
        return acompanhamentosEncontrados;
    }

}
