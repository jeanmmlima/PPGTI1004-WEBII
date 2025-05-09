package com.jeanlima.relacionamentos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanlima.relacionamentos.model.Turma;
import com.jeanlima.relacionamentos.repository.TurmaRepository;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;


    public Turma saveTurma(Turma turma) {
        return turmaRepository.save(turma);
    }

    public List<Turma> listTurmas() {
        return turmaRepository.findAll();
    }

    public Optional<Turma> findTurmaById(Long id) {
        return turmaRepository.findById(id);
    }

    public Turma updateTurma(Long id, Turma turmaAtualizada) {
        return turmaRepository.findById(id).map(turma -> {
            turma.setCodigo(turmaAtualizada.getCodigo());
            // Se quiser permitir atualização de alunos:
            // turma.setAlunos(turmaAtualizada.getAlunos());
            return turmaRepository.save(turma);
        }).orElseThrow(() -> new RuntimeException("Turma não encontrada"));
    }

    public void deleteTurma(Long id) {
        if (turmaRepository.existsById(id)) {
            turmaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Turma não encontrada para exclusão");
        }
    }
    
}
