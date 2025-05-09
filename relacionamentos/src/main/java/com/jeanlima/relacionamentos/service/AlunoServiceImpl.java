package com.jeanlima.relacionamentos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jeanlima.relacionamentos.model.Aluno;
import com.jeanlima.relacionamentos.model.Curso;
import com.jeanlima.relacionamentos.repository.AlunoRepository;
import com.jeanlima.relacionamentos.repository.CursoRepository;


@Component
public class AlunoServiceImpl implements AlunoService{

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    CursoRepository cursoRepository;
 

    @Override
    public Aluno saveAluno(Aluno aluno) {

        Long cursoId = aluno.getCurso().getId();

        Curso curso = cursoRepository.findById(cursoId)
        .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        aluno.setCurso(curso);

        return alunoRepository.save(aluno);
        
    }

    @Override
    public void deleteAluno(Aluno aluno) {
        alunoRepository.delete(aluno);
    }

    @Override
    public Aluno getAlunoById(Long id) {
        return alunoRepository.findById(id).map(aluno -> {
            return aluno;
        }).orElseThrow(() -> null);
    }

    @Override
    public List<Aluno> getListAluno() {
        return alunoRepository.findAll();
    }

    
}
