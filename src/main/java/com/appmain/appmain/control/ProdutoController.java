package com.appmain.appmain.control;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.appmain.appmain.service.ProdutoService;
import com.appmain.appmain.model.Produto;
import com.appmain.appmain.model.repositorys.ProdutoRepository;


@CrossOrigin
@RestController
@RequestMapping("/produto")
public class ProdutoController {
    
    @Autowired
    ProdutoService service;

    @GetMapping("/list/{id}")
    public ResponseEntity<?> buscaProduto(@PathVariable("id") Long id ){
        boolean tableIsEmpty =  this.service.tabelaVazia();
        if(tableIsEmpty == false){
            if(id != null){
                Boolean existsId = this.service.existProdutoId(id);
                if(existsId == true){
                    Produto produto  =  this.service.seleciona(id);
                    return ResponseEntity.ok(produto);
                }else{
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não econtrado");
                }
            }else{
                return ResponseEntity.badRequest().body("O ID é nulo");
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sem registros cadastre um produto");
        }
    }
        
    @GetMapping("/list")
    public ResponseEntity<?>  listagem(){
        boolean tableIsEmpty =  this.service.tabelaVazia();
        if(tableIsEmpty == false){
            List<Produto> produtos = this.service.listaTodas();
            return ResponseEntity.ok(produtos);
        }else{ 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sem registros cadastre um produto");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> adiciona(@RequestBody Produto produto){
        if(produto !=  null){
            Long id =  produto.getProdutoId();
            boolean idExists = this.service.existProdutoId(id);
            String nome = produto.getName();
            boolean nomeExists =  this.service.buscarNome(nome);
            if(idExists != true && nomeExists != true){   
                Produto p = this.service.salva(produto);
                return ResponseEntity.ok(p);
            }else{
                return ResponseEntity.status(HttpStatus.FOUND).body("Produto já cadastrado");
            }
        }else{
            return ResponseEntity.badRequest().body("O ID é nulo");       
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> atualiza(@RequestBody Produto produto){
        boolean tableIsEmpty =  this.service.tabelaVazia();
        if(tableIsEmpty == false){
            Long id = produto.getProdutoId();
            boolean existsId = this.service.existProdutoId(id);
            if(existsId == true){
                Produto oldProduto = this.service.seleciona(id);
                oldProduto.setDescricao(produto.getDescricao());
                oldProduto.setPreco(produto.getPreco());
                oldProduto.setName(produto.getName());
                Produto p = this.service.salva(oldProduto);
                return ResponseEntity.ok(p);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não cadastrado");
            }
        }else{
            return ResponseEntity.badRequest().body("Sem registros, cadastre um produto");
        }
    }

    @PutMapping("/update/two")
    public ResponseEntity<?> _atualiza(@RequestBody Produto produto){
        boolean tableIsEmpty = this.service.tabelaVazia();
        if(tableIsEmpty != true){
            Long id = produto.getProdutoId();
            if(id != null){
                boolean idExists = this.service.existProdutoId(id);
                if(idExists == true){
                    Produto p = this.service.seleciona(id);
                    return ResponseEntity.ok(this.service.salva(p));
                }else{
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
                }
            }else{
                return ResponseEntity.badRequest().body("O ID é nulo");
            }
        }else{
            return ResponseEntity.badRequest().body("Sem registros, cadastre um produto");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        boolean tableIsEmpty = this.service.tabelaVazia();
        if(tableIsEmpty == false){
            if(id != null){
                boolean idExists = this.service.existProdutoId(id);
                if(idExists == true){
                    Produto p = this.service.seleciona(id);
                    this.service.exclui(id);
                    return ResponseEntity.ok(p);
                }else{
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse produto não esta cadastrado");
                }
            }else{
                return ResponseEntity.badRequest().body("O ID é nulo");
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sem registros, cadastre um produto");
        }
    }
}
