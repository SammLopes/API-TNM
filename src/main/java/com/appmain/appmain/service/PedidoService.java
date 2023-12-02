package com.appmain.appmain.service;

//import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import com.appmain.appmain.model.Pedido;
import com.appmain.appmain.model.repositorys.PedidoRepository;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository repository;

    public Pedido salva(Pedido pedido){
        return this.repository.save(pedido);
    }

    public Pedido seleciona(Long id){
        Pedido pedido = this.repository.findById(id).get();
        if(pedido != null){
            return pedido;
        }
        return null;
    }

    public Pedido exclui(Long id){
        Pedido pedido = this.seleciona(id);
        if(pedido != null){
            this.repository.delete(pedido);
        }
        return pedido;
    }

    public List<Pedido> listaTodas(){
        return this.repository.findAll(Sort.by(Sort.Direction.ASC,"pedidoId", "dataPedido", "observacao","itens", "cliente","status","caixa", "formaPagamento", "total"));
    }

    public List<Pedido> buscar(String termo){
        List<Pedido> todosPedidos = this.listaTodas();
        List<Pedido> pedidosEncontrados = new ArrayList<>();

        for(Pedido pedido : todosPedidos){
            if(
            pedido.getPedidoId().toString().contains(termo) ||
            pedido.getDataPedido().toString().contains(termo) ||
            pedido.getCliente().toString().contains(termo) ||
            pedido.getItens().toString().contains(termo) ||
            pedido.getFormaPagamento().toString().contains(termo) ||
            pedido.getObservacao().toString().contains(termo) ||
            pedido.getStatus().toString().contains(termo) ||
            pedido.getTotal().toString().contains(termo)
            ){
                pedidosEncontrados.add(pedido);
            }
        }
        return pedidosEncontrados;
    }
}
