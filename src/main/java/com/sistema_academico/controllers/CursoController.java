package com.sistema_academico.controllers;


import java.util.List;

import com.sistema_academico.entities.Curso;
import com.sistema_academico.services.CursoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/curso")
public class CursoController {

    private CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping
    public Curso save(@RequestBody Curso curso) {
        return this.cursoService.save(curso);
    }

    @GetMapping
    public List<Curso> findAll() {
        return this.cursoService.findAll();
    }

    @GetMapping("/{id}")
    public Curso findById(@PathVariable Integer id) {
        return this.cursoService.findById(id);
    }
}