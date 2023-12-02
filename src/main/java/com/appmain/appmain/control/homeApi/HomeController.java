package com.appmain.appmain.control.homeApi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    //é utilizada para injetar o valor da propriedade server.servlet.context-path diretamente em uma variável.
    @Value("${server.servlet.context-path}")
    private String contextPath;


    /*
    * esse método maracado com requestmapping sem definição de verbo HTTP é capaz
    * de receber qualquer
    * requisição (GET, POST etc.) e retorna uma mensagem
    *
    */

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/html")
    public ResponseEntity<String> index() {
      String swaggerLink = contextPath.isEmpty() ? "swagger-ui.html" : contextPath + "swagger-ui.html";
      return ResponseEntity.ok().body("API de Pedidos rodando - <a href='" + swaggerLink + "'>Documentação</a>"); 
    }   
}