package com.sistema_academico.services;


import com.sistema_academico.entities.Estudante;
import com.sistema_academico.entities.Matricula;
import com.sistema_academico.repositories.CursoRepository;
import com.sistema_academico.repositories.EstudanteRepository;
import com.sistema_academico.repositories.MatriculaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatriculaService
{

    @Autowired
    private MatriculaRepository matriculaRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private EstudanteRepository estudanteRepository;

    public List<Matricula> findAll()
    {
        return this.matriculaRepository.findAll();
    }

    public Matricula findById(Integer id)
    {
        Optional<Matricula> matricula = this.matriculaRepository.findById(id);
        return matricula.orElse(null);
    }


    /*public Matricula save(Matricula matricula) {

        //Verificar se Já existe uma matricula para o estudante
       // Estudante estudante = this.findByEstudanteId(matricula.getEstudante().getId());
    }*/
    public Matricula findByEstudanteId(Integer id)
    {
        Estudante estudante = this.estudanteRepository.findById(id).orElse(null);
        if (estudante == null)
        {
            return null;
        }
        return this.matriculaRepository.findByEstudante(estudante);
    }
}