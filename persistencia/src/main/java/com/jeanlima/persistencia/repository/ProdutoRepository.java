package com.jeanlima.persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeanlima.persistencia.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

    
}