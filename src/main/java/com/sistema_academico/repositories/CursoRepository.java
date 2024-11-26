package com.sistema_academico.repositories;


import java.util.Optional;

import com.sistema_academico.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer>
{

    boolean existsByNome(String nome);

    Optional<Curso> findByNome(String nomeCurso);
}