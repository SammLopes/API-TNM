package com.appmain.appmain.service;

import java.util.List;
import java.util.ArrayList;
import com.appmain.appmain.model.Produto;
import com.appmain.appmain.model.repositorys.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto salva(Produto produto) {
        return this.repository.save(produto);
    }

    public boolean existProdutoId(Long id) {
        boolean produtoExists = this.repository.existsById(id);
        if (produtoExists == true) {
            return true;
        } else {
            return false;
        }
    }

    public boolean tabelaVazia() {
        return this.repository.count() == 0;
    }

    public Produto seleciona(Long id) {
        Produto produto = this.repository.findById(id).get();
        if (produto != null) {
            return produto;
        }
        return null;
    }

    public Produto exclui(Long id) {
        Produto produto = this.seleciona(id);
        Produto produtoExcluido = produto;
        if (produto != null) {
            this.repository.delete(produto);
        }
        return produtoExcluido;
    }

    public List<Produto> listaTodas() {
        return this.repository
                .findAll(Sort.by(Sort.Direction.ASC, "produtoId", "name", "descricao", "preco", "itemPedido"));
    }

    public List<Produto> buscar(String termo) {
        List<Produto> todosProdutos = this.listaTodas();
        List<Produto> produtosEncontrados = new ArrayList<>();
        for (Produto produto : todosProdutos) {
            if (produto.getName().contains(termo) ||
                    produto.getProdutoId().toString().contains(termo) ||
                    produto.getPreco().toString().contains(termo) ||
                    produto.getDescricao().contains(termo) ||
                    produto.getItemPedido().toString().contains(termo)) {
                produtosEncontrados.add(produto);
            }
        }
        return produtosEncontrados;
    }

    public boolean buscarNome(String nome){
         List<Produto> todosProdutos = this.listaTodas();
         for(Produto produto : todosProdutos){
            if(produto.getName().contains(nome)){
                return true;
            }
         }
         return false;
    }
}
