package com.sistema_academico.controllers;

import java.util.List;

import com.sistema_academico.entities.Matricula;
import com.sistema_academico.services.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matricula")
public class MatriculaController
{

    @Autowired
    private MatriculaService matriculaService;

    @GetMapping
    public List<Matricula> findAll()
    {
        return this.matriculaService.findAll();
    }

    @GetMapping("/{id}")
    public Matricula findById(@PathVariable Integer id)
    {
        return this.matriculaService.findById(id);
    }

    @GetMapping("/estudante/{id}")
    public Matricula findByEstudanteId(@PathVariable Integer id)
    {
        return null;
    }

    @PostMapping("/matricular")
    public Matricula matricular(@RequestBody Matricula matricula)
    {
        return null;
    }

    @GetMapping("/disciplina-estudante")
    public String disciplinaByEstudante()
    {
        return "Lista de Discplina por Estudante";
    }

}