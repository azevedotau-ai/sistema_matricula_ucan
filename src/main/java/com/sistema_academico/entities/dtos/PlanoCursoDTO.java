package com.sistema_academico.entities.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlanoCursoDTO
{

    private String curso, disciplina;
    private int semestre;

    @Override
    public String toString()
    {
        return "PlanoCursoDTO{"
                + "curso: '" + curso + '\''
                + ", disciplina: '" + disciplina + '\''
                + ", semestre: " + semestre
                + '}';
    }
}
