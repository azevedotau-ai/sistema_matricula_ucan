package com.sistema_academico.controllers;

import com.sistema_academico.entities.Precedencia;
import com.sistema_academico.services.PrecedenciaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/precedencia")
public class PrecedenciaController
{

    private PrecedenciaService precedenciaService;

    public PrecedenciaController(PrecedenciaService precedenciaService)
    {
        this.precedenciaService = precedenciaService;
    }

    @PostMapping
    public Precedencia save(@RequestBody Precedencia precedencia)
    {
        return this.precedenciaService.save(precedencia);

    }

    @GetMapping
    public List<Precedencia> findAll()
    {
        return this.precedenciaService.findAll();

    }

    @GetMapping("/disciplina-proibida")
    public String disciplinaByEstudante()
    {
        return null;
    }

}