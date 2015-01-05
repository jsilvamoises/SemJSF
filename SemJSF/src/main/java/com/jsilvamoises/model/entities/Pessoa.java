/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsilvamoises.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author Moises
 */
@Entity
@Table(name = "tbl_pessoa")
public class Pessoa  implements Serializable{
    
    private static final Long serialVersionUID = 1L;

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pes_id")
    private Long id;
    
    @Column(name = "pes_nome",nullable = false,length = 90)
    private String nome;
    
    @Email
    @Column(name = "pes_email",nullable = false,length = 120)
    private String email;
    
    @Column(name = "pes_telefone",length = 40)
    private String telefone;
    
    @CPF
    @Column(name = "pes_cpf",length = 14)
    private String cpf;
    
    @Column(name = "pes_data_nasc")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataNascimento;
    
    @Column(name = "pes_data_cad")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCadastro;
    
    @ForeignKey(name = "FK_SEXO")
    @JoinColumn(name = "pes_sexo_id", referencedColumnName = "sex_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)    
    private Sexo sexo;
    
    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @ForeignKey(name = "FK_END_PESSOA")
    private Endereco endereco;
    
    @Column(name = "pes_login", nullable = false, length = 20, unique = true)
    private String login;
    
    @Column(name = "pes_senha", nullable = false, length = 20)
    private String senha;
    
    @Column(name = "pes_permissao", nullable = false, length = 20)
    private String permissao;
    
    @Column(name = "pes_observacao")
    private String observacao;
    
    
    
    public Pessoa() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
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
        final Pessoa other = (Pessoa) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return id.toString();
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    
    
}
