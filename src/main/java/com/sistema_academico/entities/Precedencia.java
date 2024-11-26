package com.sistema_academico.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Precedencia
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pkPrecedencia;

    @ManyToOne
    @JoinColumn(name = "fkDisciplina")
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "fkDisciplinaPrecedente")
    private Disciplina disciplinaPrecedente;

    public Precedencia()
    {
    }

    public Precedencia(Disciplina disciplina, Disciplina disciplinaPrecedente)
    {
        this.disciplina = disciplina;
        this.disciplinaPrecedente = disciplinaPrecedente;
    }
}