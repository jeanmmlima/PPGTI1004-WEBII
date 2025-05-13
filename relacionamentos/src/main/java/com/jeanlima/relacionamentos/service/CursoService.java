package com.jeanlima.relacionamentos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jeanlima.relacionamentos.model.Curso;
import com.jeanlima.relacionamentos.repository.CursoRepository;

@Component
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;

    public List<Curso> getCursos(){
        return cursoRepository.findAll();
    }

    public void deleteCurso(Long id){
        cursoRepository.deleteById(id);
    }

    public Curso getCursoById(Long id){
        return cursoRepository.findById(id).map(curso -> {
            return curso;
        }).orElseThrow(() -> null);
    }

    public Curso saveCurso(Curso curso){
        return cursoRepository.save(curso);
    }
    
}
