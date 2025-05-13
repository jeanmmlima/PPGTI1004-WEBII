package com.jeanlima.relacionamentos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeanlima.relacionamentos.model.Aluno;
import com.jeanlima.relacionamentos.service.AlunoService;


@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Aluno> createAluno(@RequestBody Aluno aluno) {

        Aluno novoAluno = alunoService.saveAluno(aluno);
        return ResponseEntity.ok(novoAluno);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> findAlunoById(@PathVariable Long id) {
        try {
            Aluno aluno = alunoService.getAlunoById(id);

            return ResponseEntity.ok(aluno);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar todos os alunos
    @GetMapping
    public ResponseEntity<List<Aluno>> listAlunos() {
        List<Aluno> alunos = alunoService.getListAluno();
        return ResponseEntity.ok(alunos);
    }

    // Atualizar aluno
    @PutMapping("/{id}")
    public ResponseEntity<Aluno> updateAluno(@PathVariable Long id, @RequestBody Aluno alunoAtualizado) {
        try {
            Aluno alunoExistente = alunoService.getAlunoById(id);
            alunoAtualizado.setId(alunoExistente.getId());
            Aluno salvo = alunoService.saveAluno(alunoAtualizado);
            return ResponseEntity.ok(salvo);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Excluir aluno
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable Long id) {
        try {
            Aluno aluno = alunoService.getAlunoById(id);
            alunoService.deleteAluno(aluno);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/porCurso/{id}")
    public ResponseEntity<List<Aluno>> findAlunosByCurso(@PathVariable Long id){
        List<Aluno> alunosByCurso = alunoService.findAlunosByCursoId(id);
        return ResponseEntity.ok(alunosByCurso);
    }

    @GetMapping("/comTurmas")
    public ResponseEntity<List<Aluno>> findAlunoWithTurmas(){
        List<Aluno> alunosWithTurmas = alunoService.findAlunosWithTurmas();
        return ResponseEntity.ok(alunosWithTurmas);
    }

    
    
}
