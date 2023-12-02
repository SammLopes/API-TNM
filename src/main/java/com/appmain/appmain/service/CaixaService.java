package com.appmain.appmain.service;

import java.util.List;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.appmain.appmain.model.Caixa;
import com.appmain.appmain.model.repositorys.CaixaRepository;

@Service
public class CaixaService {

    @Autowired
    private CaixaRepository repository;

    public Caixa salva(Caixa caixa){
        return this.repository.save(caixa);
    }

    public Caixa seleciona(Long id){
        Caixa caixa =  this.repository.findById(id).get();
        if(caixa != null){
            return caixa;
        }
        return null;
    }

    public Caixa exclui(Long id){
        Caixa caixa  = this.seleciona(id);

        if(caixa != null){
            this.repository.delete(caixa);
        }
        return caixa;
    }

    public List<Caixa> listaTodas(){
        return this.repository.findAll(Sort.by(Sort.Direction.ASC, "idCaixa", "dataFechamento", "valorTotalVendas", "total", "pedidos", "usuario"));
    }

    public List<Caixa> buscar(String termo){
        List<Caixa> todosCaixas = this.listaTodas();
        List<Caixa> caixasEncontrados =  new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        for(Caixa caixa : todosCaixas){
            String dataFormatada =  caixa.getDataFechamento().format(formatter);
            if(dataFormatada.contains(termo) ||
               caixa.getIdCaixa().toString().contains(termo) ||
               caixa.getUsuario().toString().contains(termo) 
               ){
                caixasEncontrados.add(caixa);
            }
        }
        return caixasEncontrados;
    }
}
