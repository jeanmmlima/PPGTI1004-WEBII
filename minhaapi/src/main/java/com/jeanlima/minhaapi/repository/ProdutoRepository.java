package com.jeanlima.minhaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeanlima.minhaapi.model.Produto;


public interface ProdutoRepository extends JpaRepository<Produto,Integer>{
    
}
