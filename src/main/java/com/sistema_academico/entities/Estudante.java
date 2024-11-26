package com.sistema_academico.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Estudante
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pkEstudante;
    private String nome;

    public Estudante()
    {
    }

    public Estudante(String nome)
    {
        this.nome = nome;
    }

}