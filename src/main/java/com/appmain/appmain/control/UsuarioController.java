package com.appmain.appmain.control;

import java.util.List;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.appmain.appmain.model.repositorys.UsuariosRepository;
import com.appmain.appmain.service.UsuarioService;
import com.appmain.appmain.model.Usuarios;

@CrossOrigin
@RestController
@RequestMapping("/admin/usuario")
public class UsuarioController {
 
    
  @Autowired
  UsuariosRepository repository;


    @Autowired
    UsuarioService service;
  
  @GetMapping("/list")
  public List<Usuarios> getUsuarios(){
      return this.service.listaTodas();
  }

  @PostMapping("/add")
  public Usuarios insere(@RequestBody Usuarios usuario){
        Long id = usuario.getUsuarioId();
        boolean user = this.service.existUser(id);
        if(user == false){
            return this.service.salva(usuario);
        }else{
          return usuario;
        }
  }

  
  @PutMapping("/update")
  public String atualiza(@RequestBody Usuarios usuario){
    Long id = usuario.getUsuarioId();
    boolean userExists = this.service.existUser(id);
    if(usuario.getUsuarioId() != null){
      if(userExists == true){
        Usuarios user = this.service.salva(usuario);
        return "Usuario alterado\n"+ user.toString();
      }else{
        return "Usuário não encontrado";
      }
    }
    return null;
  }

  
  @DeleteMapping("/delete/{id}")
  public String atualiza(@PathVariable("id") Long id){
    if(id != null){
      boolean idExits = this.service.existUser(id);
      if(idExits == true){
        this.service.exclui(id);
        return "Excluido";
      }else{
        return "Usuario não existe";
      }
        
    }
    return null;
  }

}
