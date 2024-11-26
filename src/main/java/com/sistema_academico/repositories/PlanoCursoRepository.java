package com.sistema_academico.repositories;

import com.sistema_academico.entities.PlanoCurso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanoCursoRepository extends JpaRepository<PlanoCurso, Integer>
{
}