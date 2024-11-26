package com.sistema_academico.controllers;

import com.sistema_academico.entities.Avaliacao;
import com.sistema_academico.services.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController
{

    @PostMapping("/lancar-nota")
    public String lancarNota()
    {
        return "Matriculado com sucesso!";
    }

    @PostMapping("/consultar-nota")
    public String consultarNota()
    {
        return "Matriculado com sucesso!";
    }

    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping
    public ResponseEntity<Avaliacao> criar(@RequestBody Avaliacao avaliacao) {
        Avaliacao novaAvaliacao = avaliacaoService.salvar(avaliacao);
        return ResponseEntity.ok(novaAvaliacao);
    }

    @GetMapping
    public ResponseEntity<List<Avaliacao>> listarTodas() {
        List<Avaliacao> avaliacoes = avaliacaoService.listarTodas();
        return ResponseEntity.ok(avaliacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> buscarPorId(@PathVariable Integer id) {
        return avaliacaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> atualizar(@PathVariable Integer id, @RequestBody Avaliacao avaliacao) {
        avaliacao.setPkAvaliacao(id);
        Avaliacao avaliacaoAtualizada = avaliacaoService.atualizar(avaliacao);
        return ResponseEntity.ok(avaliacaoAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        avaliacaoService.deletar(id);
        return ResponseEntity.ok().build();
    }

}