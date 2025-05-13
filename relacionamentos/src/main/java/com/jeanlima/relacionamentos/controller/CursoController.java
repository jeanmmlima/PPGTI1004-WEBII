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

import com.jeanlima.relacionamentos.model.Curso;
import com.jeanlima.relacionamentos.service.CursoService;

@RestController
@RequestMapping("/cursos")
public class CursoController {


    @Autowired
    private CursoService cursoService;

    // Criar curso
    @PostMapping
    public ResponseEntity<Curso> criarCurso(@RequestBody Curso curso) {
        Curso novoCurso = cursoService.saveCurso(curso);
        return ResponseEntity.ok(novoCurso);
    }

    // Buscar todos os cursos
    @GetMapping
    public ResponseEntity<List<Curso>> listarCursos() {
        List<Curso> cursos = cursoService.getCursos();
        return ResponseEntity.ok(cursos);
    }

    // Buscar curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarCursoPorId(@PathVariable Long id) {
        try {
            Curso curso = cursoService.getCursoById(id);
            return ResponseEntity.ok(curso);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Atualizar curso
    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizarCurso(@PathVariable Long id, @RequestBody Curso cursoAtualizado) {
        try {
            Curso cursoExistente = cursoService.getCursoById(id);
            cursoAtualizado.setId(cursoExistente.getId());
            Curso salvo = cursoService.saveCurso(cursoAtualizado);
            return ResponseEntity.ok(salvo);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Excluir curso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCurso(@PathVariable Long id) {
        try {
            cursoService.deleteCurso(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
}
