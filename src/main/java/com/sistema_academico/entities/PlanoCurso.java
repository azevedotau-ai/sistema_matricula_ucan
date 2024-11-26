package com.sistema_academico.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PlanoCurso
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pkPlanoCurso;

    @ManyToOne
    @JoinColumn(name = "fkDisciplina")
    private Disciplina disciplina;

    @ManyToOne()
    @JoinColumn(name = "fkCurso")
    private Curso curso;
    private int semestre;

    public PlanoCurso()
    {
    }

    public PlanoCurso(Disciplina disciplina, Curso curso, int semestre)
    {
        this.disciplina = disciplina;
        this.curso = curso;
        this.semestre = semestre;
    }

    @Override
    public String toString()
    {
        return "PlanoCurso{"
                + "pkPlanoCurso: " + pkPlanoCurso
                + ", disciplina: " + disciplina
                + ", curso: " + curso
                + ", semestre: " + semestre
                + '}';
    }
}