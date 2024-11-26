package com.sistema_academico.entities;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Matricula
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pkMatricula;

    @OneToOne
    @JoinColumn(name = "fkEstudante")
    private Estudante estudante;

    @OneToOne
    @JoinColumn(name = "fkCurso")
    private Curso curso;

    @ManyToMany
    @JoinTable(
            name = "matricula_disciplina",
            joinColumns = @JoinColumn(name = "fkMatricula"),
            inverseJoinColumns = @JoinColumn(name = "fkDisciplina")
    )

    private List<Disciplina> disciplinas;

    private int anoLetivo;
    private int semestre;

    public Matricula()
    {
    }

    public Matricula(Integer pkMatricula, Estudante estudante, Curso curso, List<Disciplina> disciplinas, int anoLetivo, int semestre)
    {
        this.pkMatricula = pkMatricula;
        this.estudante = estudante;
        this.curso = curso;
        this.disciplinas = disciplinas;
        this.anoLetivo = anoLetivo;
        this.semestre = semestre;
    }
}