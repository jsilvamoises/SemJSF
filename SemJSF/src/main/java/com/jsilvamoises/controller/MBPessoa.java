/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsilvamoises.controller;

import com.jsilvamoises.dao.HibernateDAO;
import com.jsilvamoises.dao.InterfaceDAO;
import com.jsilvamoises.model.entities.Pessoa;
import com.jsilvamoises.util.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Moises
 */
@ManagedBean(name = "mbPessoa")
@ViewScoped
public class MBPessoa implements Serializable {

    private static final Long serialVersionUID = 1L;
    private int aba = 1;
    private Pessoa pessoa = new Pessoa();
    private List<Pessoa> pessoas = new ArrayList<>();
    ;
    private List<Pessoa> pessoasFiltrada = new ArrayList<>();

    public MBPessoa() {

    }

    private InterfaceDAO<Pessoa> pessoaDAO() {
        InterfaceDAO<Pessoa> pessoaDao = new HibernateDAO<>(Pessoa.class);
        return pessoaDao;
    }

    public String editPessoa() {
        return "/restrict/cadastrarPessoa";//verificar
    }

    public String limpar() {
        pessoa = new Pessoa();
        return "/restrict/cadastrarPessoa";//verificar
    }

    public void addPessoa() {
        if (pessoa != null) {
            System.err.println(pessoa.getNome());
            if (pessoa.getId() == null || pessoa.getId() == 0) {
                salvarPessoa();

            } else {
                updatePessoa();
            }
        }

    }

    private void salvarPessoa() {

        //System.err.println(FacesContext.getCurrentInstance().getAttributes());
        boolean result = pessoaDAO().merge(pessoa);
        System.err.println(result);
        if (result) {

            FacesUtil.addInfoMessage("Dado salvo com sucesso (=D");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao salvar");
        }
    }

    public void delete() {
        deletePessoa();
    }

    private void deletePessoa() {
        boolean result = pessoaDAO().remove(pessoa);
        if (result) {
            FacesUtil.addInfoMessage("Registro excluído com sucesso!");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao excluir");
        }
    }

    public void updatePessoa() {
        boolean result = pessoaDAO().updade(pessoa);
        if (result) {

            FacesUtil.addInfoMessage("Dado salvo com sucesso");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao atualizar");
        }
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        aba = 1;
        this.pessoa = pessoa;
    }

    public List<Pessoa> getPessoas() {
        pessoas = pessoaDAO().getEntities();
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public List<Pessoa> getPessoasFiltrada() {
        return pessoasFiltrada;
    }

    public void setPessoasFiltrada(List<Pessoa> pessoasFiltrada) {
        this.pessoasFiltrada = pessoasFiltrada;
    }

    public int getAba() {
        return aba;
    }

    public void setAba(int aba) {
        this.aba = aba;
    }

}
