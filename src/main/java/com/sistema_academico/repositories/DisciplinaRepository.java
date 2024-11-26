package com.sistema_academico.repositories;

import java.util.Optional;

import com.sistema_academico.entities.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer>
{

    boolean existsByNome(String nome);

    Optional<Disciplina> findByNome(String disciplina);
}
