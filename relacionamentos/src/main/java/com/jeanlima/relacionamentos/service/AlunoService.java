package com.jeanlima.relacionamentos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jeanlima.relacionamentos.model.Aluno;

@Service
public interface AlunoService {

    public Aluno saveAluno(Aluno aluno);
    public void deleteAluno(Aluno aluno);
    public Aluno getAlunoById(Long id);
    public List<Aluno> getListAluno();

    List<Aluno> findAlunosByNome(String nome);

    void deleteByNome(String nome);

    List<Aluno> findAlunosByCursoId(Long cursoId);

    List<Aluno> findAlunosWithTurmas();

}
