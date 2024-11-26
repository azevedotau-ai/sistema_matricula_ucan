package com.sistema_academico.repositories;

import com.sistema_academico.entities.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer>
{
}