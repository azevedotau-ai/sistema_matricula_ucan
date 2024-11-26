package com.sistema_academico.controllers;
import java.util.List;

import com.sistema_academico.entities.Disciplina;
import com.sistema_academico.services.DisciplinaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/disciplina")
public class DisciplinaController
{

    private DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService disciplinaService)
    {
        this.disciplinaService = disciplinaService;
    }

    //Create
    @PostMapping
    public Disciplina save(@RequestBody Disciplina disciplina)
    {
        return this.disciplinaService.save(disciplina);

    }

    //Read
    @GetMapping
    public List<Disciplina> findAll()
    {
        return this.disciplinaService.findAll();
    }

    //Show
    @GetMapping("/{id}")
    public Disciplina findById(@PathVariable Integer id)
    {
        return this.disciplinaService.findByID(id);
    }

    //Update
    @PutMapping("/{id}")
    public Disciplina update(@PathVariable Integer id, @RequestBody Disciplina disciplina)
    {
        return this.disciplinaService.update(id, disciplina);
    }

    //Delete
    @DeleteMapping("/{id}")
    public List<Disciplina> delete(@PathVariable Integer id)
    {
        return this.disciplinaService.delete(id);
    }

}