package com.jeanlima.relacionamentos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jeanlima.relacionamentos.model.Aluno;

import jakarta.transaction.Transactional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    /*
     * Trabalhando com @Query
     */

    List<Aluno> findByNome(String nome);

    @Query(value = "SELECT * FROM alunos a WHERE a.nome LIKE %:nome%", nativeQuery = true)
    List<Aluno> findByNomeAluno(@Param("nome") String nome);

    @Modifying
    @Transactional
    @Query("DELETE FROM Aluno a WHERE a.nome = :nome")
    void deleteByNome(@Param("nome") String nome);

    @Query(value = "SELECT * FROM alunos a WHERE a.curso_id = ?1", nativeQuery = true)
    List<Aluno> findAllByCursoId(Long cursoId);

    @Query("SELECT a FROM Aluno a LEFT JOIN FETCH a.turmas")
    List<Aluno> findAlunosWithTurmas();

    @Query("SELECT a FROM Aluno a WHERE a.curso.descricao = :nomeCurso")
    List<Aluno> buscarPorNomeDoCurso(@Param("nomeCurso") String nomeCurso);
}
