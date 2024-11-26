package com.sistema_academico.services;

import com.sistema_academico.entities.Avaliacao;
import com.sistema_academico.repositories.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public Avaliacao salvar(Avaliacao avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }

    public List<Avaliacao> listarTodas() {
        return avaliacaoRepository.findAll();
    }

    public Optional<Avaliacao> buscarPorId(Integer id) {
        return avaliacaoRepository.findById(id);
    }

    public void deletar(Integer id) {
        avaliacaoRepository.deleteById(id);
    }

    public Avaliacao atualizar(Avaliacao avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }
}
