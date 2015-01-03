/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsilvamoises.controller;

import com.jsilvamoises.dao.HibernateDAO;
import com.jsilvamoises.dao.InterfaceDAO;
import com.jsilvamoises.model.entities.Cidade;
import com.jsilvamoises.util.FacesContextUtil;
import com.jsilvamoises.util.FacesUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Moises
 */
@ManagedBean(name = "mbCidade")
@ViewScoped
public class MBCidade implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Cidade cidade = new Cidade();
    private List<Cidade> cidades;
    private List<Cidade> cidadesFiltrada;
    
     public MBCidade() {
    }

    private InterfaceDAO<Cidade> cidadeDAO() {
        InterfaceDAO<Cidade> cidadeDao = new HibernateDAO<>(Cidade.class, FacesContextUtil.getRequestSession());
        return cidadeDao;
    }

    public String editCidade() {
        return "faces/restrict/cadastrarCidade.xhtml";
    }

    private String limpar() {
        cidade = new Cidade();
        return null;//verificar
    }

    public String addCidade() {
        if (cidade.getId() == null || cidade.getId() == 0) {
            salvarCidade();
        } else {
            updateCidade();
        }
        return null;
    }
    
    public void salvarCidade() {
        boolean result = cidadeDAO().save(cidade);
        if (result) {

            FacesUtil.addInfoMessage("Dado salvo com sucesso");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao salvar");
        }
    }
    
     public void deleteCidade() {
        boolean result = cidadeDAO().remove(cidade);
        if (result) {
            FacesUtil.addInfoMessage("Registro excluído com sucesso!");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao excluir");
        }
    }

    public void updateCidade() {
        boolean result = cidadeDAO().updade(cidade);
        if (result) {

            FacesUtil.addInfoMessage("Dado salvo com sucesso");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao atualizar");
        }
    }


   

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Cidade> getCidades() {
        cidades = cidadeDAO().getEntities();
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public List<Cidade> getCidadesFiltrada() {
        return cidadesFiltrada;
    }

    public void setCidadesFiltrada(List<Cidade> cidadesFiltrada) {
        this.cidadesFiltrada = cidadesFiltrada;
    }

    
}
