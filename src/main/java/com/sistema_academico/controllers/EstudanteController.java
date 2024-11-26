package com.sistema_academico.controllers;

import java.util.List;

import com.sistema_academico.entities.Estudante;
import com.sistema_academico.services.EstudanteService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estudante")
public class EstudanteController
{

    private EstudanteService estudanteService;

    public EstudanteController(EstudanteService estudanteService)
    {
        this.estudanteService = estudanteService;
    }

    @PostMapping
    public Estudante save(@RequestBody Estudante estudante)
    {
        return this.estudanteService.save(estudante);
    }

    @GetMapping
    public List<Estudante> findAll()
    {
        return this.estudanteService.findAll();
    }

    @GetMapping("/{id}")
    public Estudante findById(@PathVariable Integer id)
    {
        return this.estudanteService.findByID(id);
    }

    @PutMapping("/{id}")
    public Estudante update(@PathVariable Integer id, @RequestBody Estudante estudante)
    {
        return this.estudanteService.update(id, estudante);
    }

    @DeleteMapping("/{id}")
    public List<Estudante> delete(@PathVariable Integer id)
    {
        return this.estudanteService.delete(id);
    }

}