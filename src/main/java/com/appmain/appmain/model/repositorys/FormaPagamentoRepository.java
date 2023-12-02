package com.appmain.appmain.model.repositorys;

import com.appmain.appmain.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {
    
}
