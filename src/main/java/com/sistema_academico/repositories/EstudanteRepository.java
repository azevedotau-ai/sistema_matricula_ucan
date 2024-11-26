package com.sistema_academico.repositories;

import com.sistema_academico.entities.Estudante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudanteRepository extends JpaRepository<Estudante, Integer>
{

    boolean existsByNome(String nome);  // Método criado para verificar existência pelo nome

}