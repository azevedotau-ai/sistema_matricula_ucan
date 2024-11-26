package com.sistema_academico.services;

import com.sistema_academico.entities.Precedencia;
import com.sistema_academico.repositories.PrecedenciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrecedenciaService
{

    private final PrecedenciaRepository precedenciaRepository;

    public PrecedenciaService(PrecedenciaRepository precedenciaRepository)
    {
        this.precedenciaRepository = precedenciaRepository;
    }

    public Precedencia save(Precedencia precedencia)
    {
        return this.precedenciaRepository.save(precedencia);
    }

    public List<Precedencia> findAll()
    {
        return this.precedenciaRepository.findAll();
    }
}