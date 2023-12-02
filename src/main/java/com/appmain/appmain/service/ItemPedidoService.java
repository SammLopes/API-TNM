package com.appmain.appmain.service;

import com.appmain.appmain.model.repositorys.ItemPedidoRepository;
import com.appmain.appmain.model.ItemPedido;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemPedidoService {
    
    @Autowired
    private ItemPedidoRepository repository;

    //CRUD
    //Create
    public ItemPedido salva(ItemPedido itemPedido){
        return this.repository.save(itemPedido);
    }

    //Seleciona
    public ItemPedido seleciona(Long id ){
        ItemPedido itemPedido = this.repository.findById(id).get();
        if(itemPedido != null){
            return itemPedido;
        }
        return null;
    }

    //Exclui
    public ItemPedido exclui(Long id){
        ItemPedido itemPedido = this.seleciona(id);
        if(itemPedido != null){
            this.repository.delete(itemPedido);
        }
        return itemPedido;
    }

    //Lista Clientes
    public List<ItemPedido> listaTodas(){
        return this.repository.findAll(Sort.by(Sort.Direction.ASC, "quantidade", "preco", "produtos", "acompanhamentos", "pedido"));
    }
    
    //Busca por termo
    public List<ItemPedido> buscar(String termo){
        List<ItemPedido> todosItens = this.listaTodas();
        List<ItemPedido> itensEncontrados = new ArrayList<>();
        for(ItemPedido itenPedido : todosItens){
            if(itenPedido.getProdutos().toString().contains(termo) || itenPedido.getPreco().toString().contains(termo) || itenPedido.getAcompanhamentos().contains(termo) ||itenPedido.getPedido().toString().contains(termo)){
                itensEncontrados.add(itenPedido);
            }
        }
        return itensEncontrados;
    }

}
