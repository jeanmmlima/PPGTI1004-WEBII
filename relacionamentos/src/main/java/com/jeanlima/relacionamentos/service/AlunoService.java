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

}
