package com.sistema_academico.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "disciplina")
@Getter
@Setter
public class Disciplina
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pkDisciplina;

    @Column(name = "nome")
    private String nome;

    public Disciplina()
    {
    }

    public Disciplina(String nome)
    {
        this.nome = nome;
    }

    @Override
    public String toString()
    {
        return "Disciplina{"
                + "pkDisciplina: " + pkDisciplina
                + ", nome: '" + nome + '\''
                + '}';
    }
}