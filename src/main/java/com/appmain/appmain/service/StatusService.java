package com.appmain.appmain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.appmain.appmain.model.Status;
import com.appmain.appmain.model.repositorys.StatusRepository;

@Service
public class StatusService {
    
    @Autowired
    StatusRepository repository;

    public Status salva(Status status){
        return this.repository.save(status);
    }

    public Status seleciona(Long id){
        Status status =  this.repository.findById(id).get();
        if(status != null){
            return status;
        }
        return null;
    }

    public Status exclui(Long id){
        Status status = this.seleciona(id);
        if(status != null){
            this.repository.delete(status);
        }
        return status;
    }

    public List<Status> listTodas(){
        return this.repository.findAll(Sort.by(Sort.Direction.ASC, "statusID", "status", "pedidos"));
    }

    public List<Status> buscar(String termo){
        List<Status> todosStatus =  this.listTodas();
        List<Status> statusEncontrados = new ArrayList<>();
        for(Status status : todosStatus){
            if(status.getPedidos().toString().contains(termo) || status.getStatus().toString().contains(termo) || status.getStatusId().toString().contains(termo)){
                statusEncontrados.add(status);
            }
        }
        return statusEncontrados;
    }
}
