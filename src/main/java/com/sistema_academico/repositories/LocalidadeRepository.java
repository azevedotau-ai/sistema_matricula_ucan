package com.sistema_academico.repositories;

import com.sistema_academico.entities.Localidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author aires
 */

@Repository
public interface LocalidadeRepository extends JpaRepository<Localidade, Integer>
{

    // JPQL Query Method
    @Query("SELECT l FROM Localidade l WHERE (l.nome LIKE :nome) AND (l.fkLocalidadePai is NULL)")
    public Localidade findByNome(String nome);

    // JPQL Query Method
    @Query("SELECT l FROM Localidade l WHERE (l.nome LIKE :nome) AND (l.fkLocalidadePai.nome LIKE :pai)")
    public Localidade findByNomeByPai(String nome, String pai);
}