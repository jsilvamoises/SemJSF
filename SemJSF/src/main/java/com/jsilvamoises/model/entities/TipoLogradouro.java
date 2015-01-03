/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsilvamoises.model.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author Moises
 */
@Entity
@Table(name = "tbl_tipo_logradouro")
public class TipoLogradouro implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "til_id")
    private Long id;

    @Column(name = "til_descricao_tipo_logradouro", unique = true, nullable = false, length = 50)
    private String descricaoTipoLogradouro;
    
    @OneToMany(mappedBy = "tipoLogradouro",fetch = FetchType.LAZY)
    @ForeignKey(name = "FK_TP_LOGRADOURO")
    private List<Endereco> enderecos;

    public TipoLogradouro() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricaoTipoLogradouro() {
        return descricaoTipoLogradouro;
    }

    public void setDescricaoTipoLogradouro(String descricaoTipoLogradouro) {
        this.descricaoTipoLogradouro = descricaoTipoLogradouro;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final TipoLogradouro other = (TipoLogradouro) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return id.toString();
    }

}
