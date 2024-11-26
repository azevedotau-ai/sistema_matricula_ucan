package com.sistema_academico.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author aires
 */
@Entity
@Table(name = "localidade")
@Getter
@Setter
public class Localidade implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_localidade")
    private Integer pkLocalidade;

    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;

    @OneToMany(mappedBy = "fkLocalidadePai")
    private List<Localidade> localidadeList;

    @JoinColumn(name = "fk_localidade_pai", referencedColumnName = "pk_localidade")
    @ManyToOne
    private Localidade fkLocalidadePai;

    public Localidade()
    {
    }

    public Localidade(Integer pkLocalidade)
    {
        this.pkLocalidade = pkLocalidade;
    }

    public Localidade(Integer pkLocalidade, String nome)
    {
        this.pkLocalidade = pkLocalidade;
        this.nome = nome;
    }

    public Localidade(String nome)
    {
        this.nome = nome;
    }

    @Override
    public String toString()
    {
        return "[pkLocalidade: " + pkLocalidade +
                ", nome: " + nome + "]";
    }

}