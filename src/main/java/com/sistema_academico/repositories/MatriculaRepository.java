package com.sistema_academico.repositories;


import com.sistema_academico.entities.Estudante;
import com.sistema_academico.entities.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer>
{

    Matricula findByEstudante(Estudante estudante);
}