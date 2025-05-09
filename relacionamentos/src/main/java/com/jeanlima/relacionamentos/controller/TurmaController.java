package com.jeanlima.relacionamentos.controller;



import java.util.List;
import java.util.Optional;

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

import com.jeanlima.relacionamentos.model.Turma;
import com.jeanlima.relacionamentos.repository.TurmaRepository;

@RestController
@RequestMapping("/turmas")
public class TurmaController {


    @Autowired
    private TurmaRepository turmaRepository;

    // Criar nova turma
    @PostMapping
    public ResponseEntity<Turma> createTurma(@RequestBody Turma turma) {
        Turma nova = turmaRepository.save(turma);
        return ResponseEntity.ok(nova);
    }

    // Listar todas as turmas
    @GetMapping
    public ResponseEntity<List<Turma>> listTurmas() {
        List<Turma> turmas = turmaRepository.findAll();
        return ResponseEntity.ok(turmas);
    }

    // Buscar turma por ID
    @GetMapping("/{id}")
    public ResponseEntity<Turma> buscarTurmaPorId(@PathVariable Long id) {
        Optional<Turma> turma = turmaRepository.findById(id);
        return turma.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualizar turma
    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizarTurma(@PathVariable Long id, @RequestBody Turma turmaAtualizada) {
        Optional<Turma> turmaOptional = turmaRepository.findById(id);
        if (turmaOptional.isPresent()) {
            Turma turma = turmaOptional.get();
            turma.setCodigo(turmaAtualizada.getCodigo());
            // Se quiser permitir atualização de alunos, inclua aqui:
            // turma.setAlunos(turmaAtualizada.getAlunos());
            Turma salva = turmaRepository.save(turma);
            return ResponseEntity.ok(salva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar turma
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTurma(@PathVariable Long id) {
        if (turmaRepository.existsById(id)) {
            turmaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
