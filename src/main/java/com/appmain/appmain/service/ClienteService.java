package com.appmain.appmain.service;

import com.appmain.appmain.model.repositorys.ClienteRepository;
import com.appmain.appmain.model.Cliente;
 
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    // CRUD
    // Create 
    public Cliente salva(Cliente cliente){
        return this.repository.save(cliente);
    }

    //Seleciona
    public Cliente seleciona(Long id){
        Cliente cliente =  this.repository.findById(id).get();
        if(cliente != null){
            return cliente;
        }
        return null;
    }

    //Exclui
    public Cliente exclui(Long id){
        Cliente cliente =  this.seleciona(id);
        if(cliente != null){
            this.repository.delete(cliente);
        }
        return cliente;
    }

    //Lista Clientes
    public List<Cliente> listaTodas(){
        return this.repository.findAll(Sort.by(Sort.Direction.ASC, "nome", "email", "telefone", "pedidos"));
    }
    
    //Pesquisa por termo 
    public List<Cliente> buscar(String termo){
        //Lsita todos os clientes
        List<Cliente> todosClientes = this.listaTodas();
        //Clientes encontrados
        List<Cliente> clientesEncontrados = new ArrayList<>();
        for(Cliente cliente : todosClientes){
            if(cliente.getNome().contains(termo) || cliente.getEmail().contains(termo) || cliente.getTefone().toString().contains(termo) || cliente.getPedidos().contains(termo)){
                clientesEncontrados.add(cliente);
            }
        }
        return clientesEncontrados;
    }

}