package com.appmain.appmain.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.ObjIntConsumer;

import javax.crypto.SecretKey;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.integration.IntegrationProperties.RSocket.Client;
import org.springframework.stereotype.Component;

@Component
public class JWTUtil implements Serializable{
    
    //Validadção do Token 
    public static final long VALIDADE_TOKEN_JWT = 5 * 60 * 60 * 1000;

    private final String SEGREDO = "SEGREDOsegreDAO45622__)()(asasdsdsds___sXXSDS!|7454545ddsddsDDDD";

    /*
     * Retorna o valor do segredo que é a verficação a ser usada para seruializar o token 
     */
    private SecretKey CHAVE(){
        return Keys.hmacShaKeyFor(SEGREDO.getBytes(StandardCharsets.UTF_8));
    }

    /*
     * Extrai dados do token chamados de Claim 
     */ 
    public Claims estraiTodosDados(String token){
        return Jwts.parserBuilder().setSigningKey(CHAVE()).build().parseClaimsJws(token).getBody();
    }

    /*
     * Extrai um dado especifico de um Token
     */
    public <T> T extraiDado(String token, Function<Claims, T> claimsResolver){
        final Claims claims = this.estraiTodosDados(token);
        return claimsResolver.apply(claims);
    }
    
    /*
     * Recupera o nome do usuario que consta no token
     * 
     */
    public String getUsuarioNoToken(String token){
        if(token == null){
            return null;
        }
        return extraiDado(token, Claims::getSubject);
    }

    /*
     * Recupera a data de validade do token
     */

     public Date getDataValidadeToken(String token){
        return extraiDado(token, Claims::getExpiration);
     }

     /*
      * Verifica se o token esta espirado
      */

     public Boolean tokenExpirado(String token){
        if(token == null){
            return null;
        }
        final Date expiration = getDataValidadeToken(token);
        return expiration.before(new Date());
      }

      /*
       * Gera um token com um usuario associado  ele sem outra propriedade
       */
      public String geraTokenUsuario(String nome){
        Map<String, Object> claims = new HashMap<>();
        return geraToken(claims, nome);
      }

      /*
       * Gera um token com um usuario associado a ele e com outras propriedades
       */

       public String geraTokenUsuario(String nome, Map<String, Object> claims){
        return geraToken(claims, nome);
       }

       /*
        * Gera um token com propriedades associadas
        */
        private String geraToken(Map<String, Object> claims, String nome) {
        JwtBuilder builder = Jwts.builder()
        .setSubject(nome)
        .setExpiration(new Date(System.currentTimeMillis() + VALIDADE_TOKEN_JWT))
        .signWith(CHAVE(), SignatureAlgorithm.HS256);

        builder.addClaims(claims);

        String jwtToken = builder.compact();

        return jwtToken;
      }

      /*
       * Valida token em duas propriedades nome, verifica se esta expirado
       */
      public Boolean validaToken(String token, String nome){
        final String _nome =  getUsuarioNoToken(token);
        return (nome.equals(_nome) && !tokenExpirado(token));
      }

      /*
       * Verifica se o token esta expirado
       */
      public Boolean validaToken(String token){
        return (!tokenExpirado(token));
      }

      /*
       * Recupera o token proveniente de um requisição. 
       * A requisição deve ter um HEADER = "Authorization"
       * e o valor dessa chave (HEADER) deve iniciar com "Bearer"
       */
      public String recuperaTokenRequisicao(HttpServletRequest request){
        String requestTokenHeader = request.getHeader("Authorization");
        if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")){
            return requestTokenHeader.substring(7);
        }
        return null;
      }

      /*
       *Verifica se um token tem permissão
       */
      public boolean verificaAccesso(String token, String permissao) {
        try {
         Claims c = this.estraiTodosDados(token);
         if (c.containsKey("PERMISSOES")) {
          String permissoes = (String) c.get("PERMISSOES");
          if (permissoes.contains(permissao)) {
           return true;
          }
         }
        } catch (Exception e) {
         throw new RuntimeException("Token inválido");
        }
        return false;
       }
    
}
