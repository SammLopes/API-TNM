package com.appmain.appmain.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.appmain.appmain.model.Usuarios;
import com.appmain.appmain.model.repositorys.UsuariosRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuariosRepository repository;

    public Usuarios salva(Usuarios usuarios){
        return this.repository.save(usuarios);
    }

    public Usuarios seleciona(Long id){
        try {
            Usuarios usuarios =  this.repository.findById(id).get();
            if(usuarios != null){
                return usuarios;
            }    
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Usuário não encontrado "+e.getMessage());
        }
        
        return null;
    }

    public boolean existUser(Long id){
        boolean userExists = this.repository.existsById(id);
        if(userExists == true){
            return true;
        }else{
            return false; 
        }   
    }

    public Usuarios exclui(Long id){
        Usuarios usuarios  = this.seleciona(id);

        if(usuarios != null){
            this.repository.delete(usuarios);
        }
        return usuarios;
    }

    public List<Usuarios> listaTodas(){
        return this.repository.findAll(Sort.by(Sort.Direction.ASC, 
        "usuarioId", "nome", "senha", "telefone", "email", "caixas"));
    }

    public List<Usuarios> buscar(String termo){
        List<Usuarios> todosUsuarios = this.listaTodas();
        List<Usuarios> usuariosEncontrados =  new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        for(Usuarios usuarios : todosUsuarios){
            String dataFormatada = usuarios.getData().format(formatter);
            if(usuarios.getUsuarioId().toString().contains(termo) ||
               usuarios.getCaixas().toString().contains(termo) ||
               usuarios.getNome().contains(termo) ||
               usuarios.getTelefone().contains(termo) ||
               usuarios.getEmail().contains(termo) ||
               dataFormatada.contains(termo)
               ){
                usuariosEncontrados.add(usuarios);
            }
        }
        return usuariosEncontrados;
    }
}
