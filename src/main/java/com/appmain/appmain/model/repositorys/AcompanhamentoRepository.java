package com.appmain.appmain.model.repositorys;

import com.appmain.appmain.model.Acompanhamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcompanhamentoRepository extends JpaRepository<Acompanhamento, Long>{
    
}
