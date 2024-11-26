package com.sistema_academico.services;


import com.sistema_academico.entities.Localidade;
import com.sistema_academico.models.Sitio;
import com.sistema_academico.repositories.LocalidadeRepository;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author aires
 */
@Service
public class LocalidadeService
{

    public static boolean localidadesInitialized = false;

    private static HashMap<Integer, Localidade> localidadesByPkLocalidadeCache;

    // para implementar findAll()
    private static List<Localidade> localidades;

    private static Comparator comparadorLocalidades, comparadorLocalidadesIndefinidas;

    public final String INDEFINIDO = "Indefinido";
    public final String INDEFINIDA = "Indefinida";

    @Autowired
    private LocalidadeRepository localidadeRepository;

    public LocalidadeService(LocalidadeRepository localidadeRepository)
    {
        this.localidadeRepository = localidadeRepository;
    }

    // executa automaticamente após o construtor
    @PostConstruct
    public void init()
    {
        System.out.println("Running startup task...");
        // Place your logic here
        initializeLocalidades();
        initLocalidadesCache();
        localidadesInitialized = true;
    }

    private void initializeLocalidades()
    {
        List<Sitio> sitios = Arrays.asList(
                // continentes
                new Sitio("Africa"),
                new Sitio("America"),
                new Sitio("Europa"),
                new Sitio("Asia"),
                // continente indefinido
                new Sitio("Indefinido"),
                // paises
                new Sitio("Angola", "Africa"),
                new Sitio("Mocambique", "Africa"),
                new Sitio("Africa do Sul", "Africa"),
                new Sitio("Nigeria", "Africa"),
                new Sitio("Brasil", "America"),
                // provincias de Angola
                new Sitio("Luanda", "Angola", "Africa"),
                new Sitio("Cabinda", "Angola", "Africa"),
                new Sitio("Zaire", "Angola", "Africa"),
                new Sitio("Uige", "Angola", "Africa"),
                new Sitio("Lunda-Norte", "Angola", "Africa"),
                new Sitio("Lunda-Sul", "Angola", "Africa"),
                new Sitio("Malange", "Angola", "Africa"),
                new Sitio("Bengo", "Angola", "Africa"),
                new Sitio("Kwanza-Norte", "Angola", "Africa"),
                new Sitio("Kwanza-Sul", "Angola", "Africa"),
                new Sitio("Huambo", "Angola", "Africa"),
                new Sitio("Bie", "Angola", "Africa"),
                new Sitio("Moxico", "Angola", "Africa"),
                new Sitio("Kuando Kubango", "Angola", "Africa"),
                new Sitio("Benguela", "Angola", "Africa"),
                new Sitio("Huila", "Angola", "Africa"),
                new Sitio("Cunene", "Angola", "Africa"),
                new Sitio("Namibe", "Angola", "Africa"),
                // provincia indefinida de Angola
                new Sitio("Indefinida", "Angola", "Africa"),
            /*
                Municios de Luanda:
                Belas, Cacuaco, Cazenga, Icolo e Bengo,
                Luanda, Kissama, Kilamba Kiaxi, Talatona, Viana
             */
                new Sitio("Belas", "Luanda", "Angola"),
                new Sitio("Cacuaco", "Luanda", "Angola"),
                new Sitio("Cazenga", "Luanda", "Angola"),
                new Sitio("Icolo e Bengo", "Luanda", "Angola"),
                new Sitio("Luanda", "Luanda", "Angola"),
                new Sitio("Kissama", "Luanda", "Angola"),
                new Sitio("Kilamba Kiaxi", "Luanda", "Angola"),
                new Sitio("Talatona", "Luanda", "Angola"),
                new Sitio("Viana", "Luanda", "Angola"),
                // municipio indefinido da provincia de Luanda
                new Sitio("Indefinido", "Luanda", "Angola"),
                // pais indefinido de Africa
                new Sitio("Indefinido", "Africa"),
                // pais indefinido da America
                new Sitio("Indefinido", "America"),
                // pais indefinido da Europa
                new Sitio("Indefinido", "Europa"),
                // pais indefinido da Asia
                new Sitio("Indefinido", "Asia"),
                // provincia indefinida de Mocambique
                new Sitio("Indefinida", "Mocambique", "Africa"),
                // provincia indefinida de Africa do Sul
                new Sitio("Indefinida", "Africa do Sul", "Africa"),
                // estado indefinido de Nigeria
                new Sitio("Indefinido", "Nigeria", "Africa"),
                // estado indefinido de Brasil
                new Sitio("Indefinido", "Brasil", "America"),
                // municipio indefinidos da provincia de Cabinda
                new Sitio("Indefinido", "Cabinda", "Angola"),
                // municipio indefinidos da provincia de Zaire
                new Sitio("Indefinido", "Zaire", "Angola"),
                // municipio indefinidos da provincia de Uige
                new Sitio("Indefinido", "Uige", "Angola"),
                // municipio indefinidos da provincia de Lunda-Norte
                new Sitio("Indefinido", "Lunda-Norte", "Angola"),
                // municipio indefinidos da provincia de Lunda-Sul
                new Sitio("Indefinido", "Lunda-Sul", "Angola"),
                // municipio indefinidos da provincia de Malange
                new Sitio("Indefinido", "Malange", "Angola"),
                // municipio indefinidos da provincia de Bengo
                new Sitio("Indefinido", "Bengo", "Angola"),
                // municipio indefinidos da provincia de Kwanza-Norte
                new Sitio("Indefinido", "Kwanza-Norte", "Angola"),
                // municipio indefinidos da provincia de Kwanza-Sul
                new Sitio("Indefinido", "Kwanza-Sul", "Angola"),
                // municipio indefinidos da provincia de Huambo
                new Sitio("Indefinido", "Huambo", "Angola"),
                // municipio indefinidos da provincia de Bie
                new Sitio("Indefinido", "Bie", "Angola"),
                // municipio indefinidos da provincia de Moxico
                new Sitio("Indefinido", "Moxico", "Angola"),
                // municipio indefinidos da provincia de Kuando Kubango
                new Sitio("Indefinido", "Kuando Kubango", "Angola"),
                // municipio indefinidos da provincia de Benguela
                new Sitio("Indefinido", "Benguela", "Angola"),
                // municipio indefinidos da provincia de Huila
                new Sitio("Indefinido", "Huila", "Angola"),
                // municipio indefinidos da provincia de Cunene
                new Sitio("Indefinido", "Cunene", "Angola"),
                // municipio indefinidos da provincia de Namibe
                new Sitio("Indefinido", "Namibe", "Angola")
        );

        this.saveAll(sitios);
    }

    private void saveAll(List<Sitio> sitios)
    {
        Localidade loc;
        for (Sitio s : sitios)
        {
            System.err.println("s: " + s);
            loc = generateLocalidade(s);
//            System.err.println("loc1: " + loc);
            loc = this.localidadeRepository.save(loc);
//            System.err.println("loc4: " + loc);
        }
    }

    public Localidade generateLocalidade(Sitio s)
    {
        Localidade loc = new Localidade(s.getNome());
        String pai = s.getPai(),
                avo = s.getAvo();

        loc.setFkLocalidadePai(
                pai == null ? null : (avo == null
                        ? this.localidadeRepository.findByNome(s.getPai())
                        : this.localidadeRepository.findByNomeByPai(s.getPai(), s.getAvo()))
        );
        return loc;
    }

    private void initLocalidadesCache()
    {
        localidadesByPkLocalidadeCache = new HashMap<>();
        localidades = this.localidadeRepository.findAll();

        for (Localidade l : localidades)
        {
            localidadesByPkLocalidadeCache.put(l.getPkLocalidade(), l);
        }

// Comparador de duas Localidades à partir do campo nome
        comparadorLocalidades = (o1, o2) ->
        {
            Localidade l1 = (Localidade) o1;
            Localidade l2 = (Localidade) o2;
            return l1.getNome().compareToIgnoreCase(l2.getNome());
        };

        // Comparador de duas Localidades Indefinidas
        comparadorLocalidadesIndefinidas = (o1, o2) ->
        {
            Localidade s1 = (Localidade) o1;
            Localidade s2 = (Localidade) o2;
            if (s1.getFkLocalidadePai() == null)
            {
                return 1;
            }
            if (s2.getFkLocalidadePai() == null)
            {
                return -1;
            }
            return s1.getFkLocalidadePai().getNome().
                    compareToIgnoreCase(s2.getFkLocalidadePai().getNome());
        };

        // ordenação da lista localidades
        Collections.sort(localidades, comparadorLocalidades);
    }

    public List<Localidade> findAllFilhos(int pkLocalidadePai)
    {
        System.err.println("0: LocalidadeServices.findAllFilhos(int)\tpkLocalidadePai: " + pkLocalidadePai);
//        List<Localidade> filhos = localidadeRepository.findAllFilhos(codigo);
/*@Query("SELECT l FROM Localidade l WHERE l.fkLocalidadePai IS NOT NULL AND
                    l.fkLocalidadePai.pkLocalidade = :pkLocalidade ORDER BY l.nome")*/
        List<Localidade> filhos = new ArrayList();
        for (Localidade l : localidades)
        {
            if (l.getFkLocalidadePai() == null)
            {
                continue;
            }
            if (l.getFkLocalidadePai().getPkLocalidade() == pkLocalidadePai)
            {
                filhos.add(l);
            }
        }
        Collections.sort(localidades, comparadorLocalidades);
        System.err.println("1: LocalidadeServices.findAllFilhos(int)\tfilhos1: " + filhos);
        filhos = colocarLocalidadesIndefinidasNoFim(filhos);
        System.err.println("2: LocalidadeServices.findAllFilhos(int)\tfilhos2: " + filhos);

        return filhos;
    }

    public List<Localidade> findAllFilhos(String nomePai)
    {
        System.err.println("0: LocalidadeServices.findAllFilhos(String)\tnomePai: " + nomePai);
        /*@Query("SELECT l FROM Localidade l WHERE (l.fkLocalidadePai IS NOT NULL) AND
        (l.fkLocalidadePai.fkLocalidadePai IS NULL) AND
        (l.fkLocalidadePai.nome LIKE :nomePai) ORDER BY l.nome")*/
//        List<Localidade> filhos = localidadeRepository.findAllFilhos(nomePai);
        List<Localidade> filhos = new ArrayList();
        for (Localidade l : localidades)
        {
            if (l.getFkLocalidadePai() == null)
            {
                continue;
            }
            if (l.getFkLocalidadePai().getFkLocalidadePai() != null)
            {
                continue;
            }
            if (l.getFkLocalidadePai().getNome().equalsIgnoreCase(nomePai))
            {
                filhos.add(l);
            }
        }
        System.err.println("1: LocalidadeServices.findAllFilhos(String)\tfilhos1: " + filhos);
        if (filhos.isEmpty())
        {
            List<Localidade> pais = findAllByNome(nomePai);
            System.err.println("2: LocalidadeServices.findAllFilhos(String)\tpais: " + pais);
            if (pais.isEmpty() || (pais.size() != 1))
            {
                return new ArrayList();
            }
            filhos = findAllFilhos(pais.get(0).getPkLocalidade());
            System.err.println("3: LocalidadeServices.findAllFilhos(String)\tfilhos3: " + filhos);
        }
        Collections.sort(filhos, comparadorLocalidades);
        System.err.println("4: LocalidadeServices.findAllFilhos(String)\tfilhos4: " + filhos);
        filhos = colocarLocalidadesIndefinidasNoFim(filhos);
        System.err.println("5: LocalidadeServices.findAllFilhos(String)\tfilhos5: " + filhos);
        return filhos;
    }

    public List<Localidade> findAllFilhos(String nomePai, String nomeAvo)
    {
        /*
        @Query("SELECT l FROM Localidade l WHERE (l.fkLocalidadePai IS NOT NULL) AND
        (l.fkLocalidadePai.fkLocalidadePai IS NOT NULL) AND
        (l.fkLocalidadePai.nome LIKE :nomePai)  AND
        (l.fkLocalidadePai.fkLocalidadePai.nome LIKE :nomeAvo) ORDER BY l.nome")
         */
//        List<Localidade> filhos = localidadeRepository.findAllFilhos(nomePai, nomeAvo);
        List<Localidade> filhos = new ArrayList();
        for (Localidade l : localidades)
        {
            if (l.getFkLocalidadePai() == null)
            {
                continue;
            }
            if (l.getFkLocalidadePai().getFkLocalidadePai() == null)
            {
                continue;
            }
            if (!l.getFkLocalidadePai().getNome().equalsIgnoreCase(nomePai))
            {
                continue;
            }
            if (l.getFkLocalidadePai().getFkLocalidadePai().getNome().equalsIgnoreCase(nomeAvo))
            {
                filhos.add(l);
            }
        }
        Collections.sort(filhos, comparadorLocalidades);
        filhos = colocarLocalidadesIndefinidasNoFim(filhos);

        return filhos;
    }

    public Localidade findFilhoIndefinido(int pkLocalidade)
    {
        /*
        @Query("SELECT l FROM Localidade l WHERE (l.fkLocalidadePai IS NOT NULL) AND
        (l.fkLocalidadePai.pkLocalidade = :pkLocalidade) AND
        ((l.nome LIKE 'Indefinido') OR (l.nome LIKE 'Indefinida'))")
         */
//        return localidadeRepository.findFilhoIndefinido(pkLocalidade);
        for (Localidade l : localidades)
        {
            if (l.getFkLocalidadePai() == null)
            {
                continue;
            }
            if (l.getFkLocalidadePai().getPkLocalidade() != pkLocalidade)
            {
                continue;
            }
            if (l.getNome().equalsIgnoreCase(INDEFINIDO) || l.getNome().equalsIgnoreCase(INDEFINIDA))
            {
                return l;
            }
        }
        return null;
    }

    public List<Localidade> findAllContinentes()
    {
        /*
        @Query("SELECT l FROM Localidade l WHERE (l.fkLocalidadePai IS NULL) ORDER BY l.nome")
         */
//        List<Localidade> continentes = localidadeRepository.findAllContinentes();
        List<Localidade> continentes = new ArrayList();
        for (Localidade l : localidades)
        {
            if (l.getFkLocalidadePai() != null)
            {
                continue;
            }
            continentes.add(l);
        }
        Collections.sort(continentes, comparadorLocalidades);
        return colocarLocalidadesIndefinidasNoFim(continentes);
    }

    public List<Localidade> findAllPaises()
    {
        List<Localidade> continentes = findAllContinentes();
        List<Localidade> paises = new ArrayList();

        for (Localidade continente : continentes)
        {
            paises.addAll(findAllFilhos(continente.getPkLocalidade()));
        }
        sort(paises);
        return colocarLocalidadesIndefinidasNoFim(paises);
    }

    public List<Localidade> colocarLocalidadesIndefinidasNoFim(List<Localidade> lista)
    {
        List<Localidade> indefinidas = new ArrayList<>();
        List<Localidade> result = new ArrayList<>();

        for (Localidade loc : lista)
        {
            if (loc.getNome().equals(INDEFINIDO)
                    || loc.getNome().equals(INDEFINIDA))
            {
                indefinidas.add(loc);
            }
            else
            {
                result.add(loc);
            }
        }

        if (!indefinidas.isEmpty())
        {
            indefinidas = sortIndefinidas(indefinidas);

            result.addAll(indefinidas);
        }
        return result;
    }

    public List<Localidade> sort(List<Localidade> lista)
    {
        Collections.sort(lista, comparadorLocalidades);
        return lista;
    }

    public List<Localidade> sortIndefinidas(List<Localidade> indefinidas)
    {
        Collections.sort(indefinidas, comparadorLocalidadesIndefinidas);
        return indefinidas;
    }

    public List<Localidade> findAllByNome(String nome)
    {
        List<Localidade> lista = new ArrayList();
        for (Localidade l : localidades)
        {
            if (l.getNome().equalsIgnoreCase(nome))
            {
                lista.add(l);
            }
        }
        Collections.sort(lista, comparadorLocalidades);
        return lista;
    }

}