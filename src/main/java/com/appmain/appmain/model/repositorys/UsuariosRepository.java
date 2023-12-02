package com.appmain.appmain.model.repositorys;

import com.appmain.appmain.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Long>{
    public Usuarios findByLoginAndSenha(String login, String senha);
    public Usuarios findByLogin(String usarios);
}
