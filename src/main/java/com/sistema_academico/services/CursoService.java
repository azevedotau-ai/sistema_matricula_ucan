package com.sistema_academico.services;


import com.sistema_academico.entities.Curso;
import com.sistema_academico.repositories.CursoRepository;
import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService
{

    public static boolean cursosInitialized = false;

    private static HashMap<Integer, Curso> cursosByPkCursoCache;

    // assumindo-se que não existem 2 cursos com o mesmo nome
    private static HashMap<String, Curso> cursosByNomeCache;

    // para implementar findAll()
    private static List<Curso> cursos;

    private static Comparator comparator;

    @Autowired
    private CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository)
    {
        this.cursoRepository = cursoRepository;
    }

    // executa automaticamente após o construtor
    @PostConstruct
    public void init()
    {
        System.out.println("Running startup task...");
        // Place your logic here
        initializeCursos();
        initCursosCache();
        cursosInitialized = true;
    }

    public static boolean isCursosInitialized()
    {
        return cursosInitialized;
    }

    public void initializeCursos()
    {
        List<Curso> cursos = Arrays.asList(
                new Curso("Engenharia Informática"),
                new Curso("Direito"),
                new Curso("Economía e Gestão")
        );

        this.cursoRepository.saveAll(cursos);
    }

    private void initCursosCache()
    {
        cursosByPkCursoCache = new HashMap<>();
        cursosByNomeCache = new HashMap<>();
        cursos = this.cursoRepository.findAll();

        for (Curso curso : cursos)
        {
            cursosByPkCursoCache.put(curso.getPkCurso(), curso);
            cursosByNomeCache.put(curso.getNome(), curso);
        }

        // ordenador das instancias de Curso à partir do campo nome
        comparator = (o1, o2) ->
        {
            Curso c1 = (Curso) o1;
            Curso c2 = (Curso) o2;
            return c1.getNome().compareToIgnoreCase(c2.getNome());
        };

        // ordenação da lista cursos
        Collections.sort(cursos, comparator);
    }

    public List<Curso> findAll()
    {
        return cursosInitialized ? cursos : this.cursoRepository.findAll();
    }

    public Curso findById(Integer pkCuso)
    {
        if (cursosInitialized)
        {
            return cursosByPkCursoCache.get(pkCuso);
        }
        else
        {
            Optional<Curso> curso = cursoRepository.findById(pkCuso);
            return curso.orElse(null);
        }
    }

    public Curso findByNome(String nomeCurso)
    {
        if (cursosInitialized)
        {
            return cursosByNomeCache.get(nomeCurso);
        }
        else
        {
            Optional<Curso> cursoOpt = cursoRepository.findByNome(nomeCurso);
            return cursoOpt.orElse(null);
        }

    }

    public Curso save(Curso curso)
    {
        curso = this.cursoRepository.save(curso);
        cursosByPkCursoCache.put(curso.getPkCurso(), curso);
        cursosByNomeCache.put(curso.getNome(), curso);
        cursos.add(curso);
        Collections.sort(cursos, comparator);
        return curso;
    }

    public Curso update(Integer id, Curso curso)
    {
        Optional<Curso> novoSalvo = this.cursoRepository.findById(id);

        if (novoSalvo.isPresent())
        {
            Curso cursoAlterar = novoSalvo.get();
            cursoAlterar.setNome(curso.getNome());

            return save(cursoAlterar);
        }

        return null;
    }
}
