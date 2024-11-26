package com.sistema_academico.services;


import com.github.javafaker.Faker;
import com.sistema_academico.entities.Estudante;
import com.sistema_academico.repositories.EstudanteRepository;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstudanteService
{

    @Autowired
    private EstudanteRepository estudanteRepository;

    private static final Faker faker = new Faker();

    public EstudanteService(EstudanteRepository estudanteRepository)
    {
        this.estudanteRepository = estudanteRepository;
    }

    // executa automaticamente após o construtor
    @PostConstruct
    public void init()
    {
        System.out.println("Running startup Estudantes ...");
        initializeEstudantes();
    }

    public void initializeEstudantes()
    {
        List<Estudante> estudantes = new ArrayList<>();
        for (int i = 0; i < 100; i++)
            estudantes.add(new Estudante(faker.name().fullName()));
        estudanteRepository.saveAll(estudantes);
    }

    //Create
    public Estudante save(Estudante estudante)
    {
        return this.estudanteRepository.save(estudante);
    }

    //Read
    public List<Estudante> findAll()
    {
        return this.estudanteRepository.findAll();
    }

    //FindById
    public Estudante findByID(Integer id)
    {
        Optional<Estudante> optionalEstudante = this.estudanteRepository.findById(id);
        return optionalEstudante.orElse(null);
    }

    //Update
    public Estudante update(Integer id, Estudante estudante)
    {
        Optional<Estudante> optionalEstudante = this.estudanteRepository.findById(id);
        if (optionalEstudante.isPresent())
        {
            Estudante estudanteBD = optionalEstudante.get();
            estudanteBD.setNome(estudante.getNome());
            return this.estudanteRepository.save(estudanteBD);
        }
        return null;
    }

    //Delete
    public List<Estudante> delete(Integer id)
    {
        Optional<Estudante> optionalEstudante = this.estudanteRepository.findById(id);
        if (optionalEstudante.isPresent())
        {
            this.estudanteRepository.delete(optionalEstudante.get());
            return estudanteRepository.findAll();
        }
        return null;
    }

}