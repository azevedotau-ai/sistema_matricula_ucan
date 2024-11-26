package com.sistema_academico.services;

import  com.sistema_academico.entities.Disciplina;
import com.sistema_academico.repositories.CursoRepository;
import com.sistema_academico.repositories.DisciplinaRepository;
import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisciplinaService
{

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public static boolean disciplinaInitialized = false;

    @PostConstruct
    public void init()
    {
        this.inicializeDisciplina();
    }

    public void inicializeDisciplina()
    {

        // Agora cria as disciplinas associadas ao curso encontrado ou criado
        List<Disciplina> disciplinas = Arrays.asList(
                new Disciplina("Sistemas Distribuídos e Paralelos I"),
                new Disciplina("Sistemas Distribuídos e Paralelos II"),
                new Disciplina("Comércio Electrônico"),
                new Disciplina("Projecto Final I"),
                new Disciplina("Projecto Final II"),
                new Disciplina("Introdução Direito"),
                new Disciplina("Direito Penal"),
                new Disciplina("Economia 1"),
                new Disciplina("Matematica")
        );

        this.disciplinaRepository.saveAll(disciplinas);
        disciplinaInitialized = true;
    }

    //Create
    public Disciplina save(Disciplina disciplina)
    {
        return this.disciplinaRepository.save(disciplina);
    }

    //Read
    public List<Disciplina> findAll()
    {
        return this.disciplinaRepository.findAll();
    }

    //FindById
    public Disciplina findByID(Integer id)
    {
        Optional<Disciplina> optionalDisciplina = this.disciplinaRepository.findById(id);
        return optionalDisciplina.orElse(null);
    }

    //Update
    public Disciplina update(Integer id, Disciplina disciplina)
    {
        Optional<Disciplina> optionalDisciplina = this.disciplinaRepository.findById(id);
        if (optionalDisciplina.isPresent())
        {
            Disciplina disciplinaBD = optionalDisciplina.get();
            disciplinaBD.setNome(disciplina.getNome());
            return this.disciplinaRepository.save(disciplinaBD);
        }
        return null;
    }

    //Delete
    public List<Disciplina> delete(Integer id)
    {
        Optional<Disciplina> optionalDisciplina = this.disciplinaRepository.findById(id);
        if (optionalDisciplina.isPresent())
        {
            this.disciplinaRepository.delete(optionalDisciplina.get());
            return disciplinaRepository.findAll();
        }
        return null;
    }

    public Disciplina findByNome(String disciplina)
    {
        Optional<Disciplina> disciplinaOpt = disciplinaRepository.findByNome(disciplina);
        return disciplinaOpt.orElse(null);
    }

    // Getters
    public static boolean isDisciplinaInitialized()
    {
        return disciplinaInitialized;
    }

}
