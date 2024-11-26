package com.sistema_academico.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author afoperes
 */
@Entity
@Table(name = "curso")
@Setter
@Getter
public class Curso
{

    @Id
    @Column(name = "pkCurso", insertable = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pkCurso;

    @Column(name = "nome", nullable = true)
    private String nome;

    public Curso()
    {

    }

    public Curso(String nome)
    {
        this.nome = nome;
    }

    @Override
    public String toString()
    {
        return "Curso{"
                + "pkCurso: " + pkCurso
                + ", nome: '" + nome + '\''
                + '}';
    }
}