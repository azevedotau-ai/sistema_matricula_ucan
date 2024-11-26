package com.sistema_academico.controllers;

import com.sistema_academico.entities.dtos.PlanoCursoDTO;
import com.sistema_academico.services.PlanoCursoService;
import com.sistema_academico.entities.PlanoCurso;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plano_curso")
public class PlanoCursoController
{

    @Autowired
    private PlanoCursoService planoCursoService;

    public PlanoCursoController(PlanoCursoService planoCursoService)
    {
        this.planoCursoService = planoCursoService;
    }

    //Create
    @PostMapping
    public PlanoCurso save(@RequestBody PlanoCursoDTO planoCursoDTO)
    {
        System.out.println("PlanoCursoController.save()\tplanoCursoDTO: " + planoCursoDTO);
        return this.planoCursoService.save(planoCursoDTO);
    }

    //Read
    @GetMapping
    public List<PlanoCurso> findAll()
    {
        return this.planoCursoService.findAll();
    }

    //Show
    @GetMapping("/{id}")
    public PlanoCurso findById(@PathVariable Integer id)
    {
        return this.planoCursoService.findByID(id);
    }

    //Update
    @PutMapping("/{id}")
    public PlanoCurso update(@PathVariable Integer id, @RequestBody PlanoCursoDTO planoCursoDTO)
    {
        return this.planoCursoService.update(id, planoCursoDTO);
    }

    //Delete
    @DeleteMapping("/{id}")
    public List<PlanoCurso> delete(@PathVariable Integer id)
    {
        return this.planoCursoService.delete(id);
    }

}