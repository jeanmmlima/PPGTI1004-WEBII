package com.jeanlima.relacionamentos.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jeanlima.relacionamentos.model.Aluno;
import com.jeanlima.relacionamentos.model.Curso;
import com.jeanlima.relacionamentos.model.Turma;
import com.jeanlima.relacionamentos.repository.AlunoRepository;
import com.jeanlima.relacionamentos.repository.CursoRepository;
import com.jeanlima.relacionamentos.repository.TurmaRepository;


@Component
public class AlunoServiceImpl implements AlunoService{

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    TurmaRepository turmaRepository;
 

    @Override
    public Aluno saveAluno(Aluno aluno) {

        Long cursoId = aluno.getCurso().getId();

        Curso curso = cursoRepository.findById(cursoId)
        .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        aluno.setCurso(curso);

        Set<Turma> turmas = aluno.getTurmas();
    
    Set<Turma> turmasValidadas = turmas.stream()
        .map(t -> turmaRepository.findById(t.getId())
                .orElseThrow(() -> new RuntimeException("Turma não encontrada: id=" + t.getId())))
        .collect(Collectors.toSet());

        aluno.setTurmas(turmasValidadas);

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

    @Override
    public List<Aluno> findAlunosByNome(String nome) {
        return alunoRepository.findByNomeAluno(nome);
    }

    @Override
    public void deleteByNome(String nome) {
        alunoRepository.deleteByNome(nome);
    }

    @Override
    public List<Aluno> findAlunosByCursoId(Long cursoId) {
        return alunoRepository.findAllByCursoId(cursoId);
    }

    @Override
    public List<Aluno> findAlunosWithTurmas() {
        return alunoRepository.findAlunosWithTurmas();
    }


    
}
