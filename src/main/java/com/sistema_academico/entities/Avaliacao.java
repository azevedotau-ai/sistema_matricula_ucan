package com.sistema_academico.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Avaliacao
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pkAvaliacao;
    private String nome;

    @ManyToOne()
    @JoinColumn(name = "fkMatricula")
    private Matricula matricula;

    @ManyToOne
    @JoinColumn(name = "fkDisciplina")
    private Disciplina disciplina;
    private Double nota;

    public Avaliacao()
    {
    }

}
