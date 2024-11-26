package com.sistema_academico.services;


import com.sistema_academico.entities.PlanoCurso;
import com.sistema_academico.entities.dtos.PlanoCursoDTO;
import com.sistema_academico.repositories.PlanoCursoRepository;
import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanoCursoService
{

    public static boolean planosCursosInitialized = false;
    private static List<PlanoCurso> planosCursos;

    // Flyway
    @Autowired
    private PlanoCursoRepository planoCursoRepository;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private DisciplinaService disciplinaService;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @PostConstruct
    public void init()
    {
        scheduler.schedule(this::executarAcao, 10, TimeUnit.MILLISECONDS); // Executa após 10 milissegundos
    }

    public void executarAcao()
    {
        if (!inicializePlanosCursos())
        {
            System.out.println("Planos de Cursos não inicializados!");
            return;
        }
        System.out.println("Planos de Cursos inicializados!");
        scheduler.shutdown(); // Desativa o agendamento após a execução
    }

    public boolean inicializePlanosCursos()
    {
        if (planosCursosInitialized
                || !CursoService.isCursosInitialized()
                || !DisciplinaService.isDisciplinaInitialized())
        {
            return false;
        }
        planosCursos = Arrays.asList(
                new PlanoCurso(disciplinaService.findByNome("Sistemas Distribuídos e Paralelos I"),
                        cursoService.findByNome("Engenharia Informática"),
                        6),
                new PlanoCurso(disciplinaService.findByNome("Sistemas Distribuídos e Paralelos II"),
                        cursoService.findByNome("Engenharia Informática"),
                        7),
                new PlanoCurso(disciplinaService.findByNome("Comércio Electrônico"),
                        cursoService.findByNome("Engenharia Informática"),
                        6),
                new PlanoCurso(disciplinaService.findByNome("Direito Penal"),
                        cursoService.findByNome("Direito"),
                        3)
        );

        planoCursoRepository.saveAll(planosCursos);
        planosCursosInitialized = true;
        return true;
    }

    //Create
    public PlanoCurso save(PlanoCursoDTO planoCursoDTO)
    {
        PlanoCurso planoCurso = createPlanoCurso(planoCursoDTO);
        System.out.println("DisciplinaService.save()\tplanoCurso: " + planoCurso);
        return this.planoCursoRepository.save(planoCurso);
    }

    private PlanoCurso createPlanoCurso(PlanoCursoDTO planoCursoDTO)
    {
        PlanoCurso planoCurso = new PlanoCurso();
        planoCurso.setCurso(this.cursoService.findByNome(planoCursoDTO.getCurso()));
        planoCurso.setDisciplina(this.disciplinaService.findByNome(planoCursoDTO.getDisciplina()));
        planoCurso.setSemestre(planoCursoDTO.getSemestre());
        return planoCurso;
    }

    //Read
    public List<PlanoCurso> findAll()
    {
        return this.planoCursoRepository.findAll();
    }

    //FindById
    public PlanoCurso findByID(Integer id)
    {
        Optional<PlanoCurso> optionalDisciplina = this.planoCursoRepository.findById(id);
        return optionalDisciplina.orElse(null);
    }

    //Update
    public PlanoCurso update(Integer id, PlanoCursoDTO planoCursoDTO)
    {
        Optional<PlanoCurso> optionalPlanoCurso = this.planoCursoRepository.findById(id);
        if (optionalPlanoCurso.isPresent())
        {
            PlanoCurso planoCursoAlterar = optionalPlanoCurso.get();
            planoCursoAlterar.getCurso().setNome(planoCursoDTO.getCurso());
            planoCursoAlterar.getDisciplina().setNome(planoCursoDTO.getDisciplina());
            return this.planoCursoRepository.save(planoCursoAlterar);
        }
        return null;
    }

    //Delete
    public List<PlanoCurso> delete(Integer id)
    {
        Optional<PlanoCurso> optionalDisciplina = this.planoCursoRepository.findById(id);
        if (optionalDisciplina.isPresent())
        {
            this.planoCursoRepository.delete(optionalDisciplina.get());
            return planoCursoRepository.findAll();
        }
        return null;
    }

}
