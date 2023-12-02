package com.appmain.appmain.service.auth;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.appmain.appmain.model.Autorizacao;
import com.appmain.appmain.model.repositorys.AutorizacaoRepository;
import com.appmain.appmain.model.Usuarios;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;


//@Service
public class AuthFilter implements Filter{
    
    @Autowired
    AutorizacaoRepository autorizacaoRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)   throws IOException, ServletException{

        String context =  request.getServletContext().getContextPath();// Caminho do contexto da aplicação 

        String url = request instanceof HttpServletRequest ? ((HttpServletRequest) request).getRequestURL().toString()
        : "N/A";

        try {

            URL _url = new URI(url).toURL();
            url =  _url.getFile();
            url = url.replace(context, "");
        } catch (URISyntaxException e){
            e.printStackTrace();
        }

        System.out.println("processando a url:"+url);

        var usuLogado =  request instanceof HttpServletRequest 
                ? ((HttpServletRequest) request).getSession().getAttribute("usuario")
                : null; 


        System.out.println("usuário logado: "+usuLogado);

        //Caso não precise de verificação
        if (!autorizacaoRepository.precisaVerificar(url)) {
            chain.doFilter(request, response);//Permite que a requisição continue normalmente
            return;
        }

        //se precisar de permissão vai verificar se tem usuário logado 
        if(usuLogado != null){
            Usuarios u = (Usuarios) usuLogado;
            if(u.getAutorizacoes() != null){
                for(Autorizacao a : u.getAutorizacoes()){
                    System.out.println(a.getPattern());
                    
                    Pattern pattern = Pattern.compile(a.getPattern(), Pattern.UNICODE_CASE);
                    Matcher matcher =  pattern.matcher(url);
                    boolean matchFound =  matcher.find();

                    if(matchFound){
                        System.out.println("OK !!!!");
                        chain.doFilter(request, response);
                        return;
                    }
                
                }   
            }
            request.getRequestDispatcher("/naoautorizado");
            return;// Caso não tenha lista de autorização
        }else{
            request.getRequestDispatcher("/login").forward(request, response);
            return;// caso nã esteja logado e é redirecinado para url login.
        }
    }
  
}
