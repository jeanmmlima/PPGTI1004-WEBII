package com.jeanlima.relacionamentos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jeanlima.relacionamentos.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno,Long>{

    /*
     * Trabalhando com @Query
     */

    //sql nativo
    @Query(value = " select * from alunos a where a.nome like %:nome% ",nativeQuery = true)
    List<Aluno> findByNomeAluno(@Param("nome") String nome);

    @Query(value = " delete from Aluno c where c.nome =:nome")
    @Modifying //pois não é só consulta - transactional 
    void deletarPorNome(String nome);

    @Query(value = " select e.* from aluno e where e.curso_id = ?1",nativeQuery = true)
    List<Aluno> findAllByIdCurso(Integer id);
}
