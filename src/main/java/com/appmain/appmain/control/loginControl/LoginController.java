package com.appmain.appmain.control.loginControl;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpServletRequest;
import com.appmain.appmain.model.repositorys.UsuariosRepository;
import com.appmain.appmain.util.JWTUtil;
import com.appmain.appmain.model.Usuarios;
import com.appmain.appmain.model.Autorizacao;


@RestController
public class LoginController {
    
    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    UsuariosRepository usuarioRepository;

    @PostMapping("/login")
    public String logar(String senha, String usuario){
        if(usuario != null && !usuario.isEmpty() && senha != null && !senha.isEmpty()){
            Usuarios u = usuarioRepository.findByLogin(usuario);
            if(u != null && u.getSenha().equals(senha)){
                Map<String, Object> claims = new HashMap<>();
                //Add login no token
                claims.put("LOGIN", u.getLogin());
                //Add campo de permissao no token
                claims.put("PERMISSOES", u.getPermissoes());
                String token = jwtUtil.geraTokenUsuario(usuario, claims);
                return token;
            }else{
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "USUÁRIO OU SENHA INVÁLIDOS");
            }
        }else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "CREDENCIAIS INVÁLIDAS");
        }
    }

}
