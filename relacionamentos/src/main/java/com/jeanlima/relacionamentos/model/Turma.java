package com.jeanlima.relacionamentos.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "turmas")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

    @JsonIgnore
    @ManyToMany(mappedBy = "turmas", fetch = FetchType.LAZY)
    private Set<Aluno> alunos;

    public Turma(Long id, String codigo, Set<Aluno> alunos) {
        this.id = id;
        this.codigo = codigo;
        this.alunos = alunos;
    }

    

    public Turma(String codigo) {
        this.codigo = codigo;
    }



    public Turma() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }

    
    
}
