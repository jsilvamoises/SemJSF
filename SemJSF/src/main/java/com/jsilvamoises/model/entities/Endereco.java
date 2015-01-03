/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsilvamoises.model.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author Moises
 */
@Entity
@Table(name = "tbl_endereco")
public class Endereco implements Serializable {

    @Id
    @Column(name = "end_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "end_bairro", nullable = false, length = 50)
    private String bairro;

    @Column(name = "end_cep", nullable = false, length = 9)
    private String cep;

    @Column(name = "end_complemento", length = 50)
    private String complemento;
    private String nomeLogradouro;
    private String numero;

    @ManyToOne
    @JoinColumn(name = "end_cidade_id", nullable = false, referencedColumnName = "cid_id")
    @ForeignKey(name = "FK_CIDADE")
    private Cidade cidade;

    @ManyToOne
    @JoinColumn(name = "end_estado_id", nullable = false, referencedColumnName = "est_id")
    @ForeignKey(name = "FK_ESTADO")
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "end_pessoa_id", nullable = false, referencedColumnName = "pes_id")
    @ForeignKey(name = "FK_PESSOA")
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "end_tipo_endereco_id", nullable = false, referencedColumnName = "tie_id")
    @ForeignKey(name = "FK_TP_ENDERECO")
    private TipoEndereco tipoEndereco;

    @ManyToOne
    @JoinColumn(name = "end_tipo_logradouro_id", nullable = false, referencedColumnName = "til_id")
    @ForeignKey(name = "FK_TP_LOGRADOURO")
    private TipoLogradouro tipoLogradouro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getNomeLogradouro() {
        return nomeLogradouro;
    }

    public void setNomeLogradouro(String nomeLogradouro) {
        this.nomeLogradouro = nomeLogradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public TipoEndereco getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(TipoEndereco tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public TipoLogradouro getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Endereco other = (Endereco) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id.toString();
    }
    
    
    
    

}
