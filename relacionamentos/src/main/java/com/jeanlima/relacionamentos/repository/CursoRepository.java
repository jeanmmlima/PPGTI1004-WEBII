package com.jeanlima.relacionamentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeanlima.relacionamentos.model.Curso;



public interface CursoRepository extends JpaRepository<Curso,Long>{
    
}
