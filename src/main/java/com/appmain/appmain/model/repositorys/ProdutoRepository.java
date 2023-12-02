package com.appmain.appmain.model.repositorys;

import com.appmain.appmain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProdutoRepository  extends JpaRepository<Produto, Long>{
    
}
