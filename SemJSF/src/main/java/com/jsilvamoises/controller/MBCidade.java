/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsilvamoises.controller;

import com.jsilvamoises.dao.HibernateDAO;
import com.jsilvamoises.dao.InterfaceDAO;
import com.jsilvamoises.model.entities.Cidade;
import com.jsilvamoises.model.entities.Sexo;
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
@ManagedBean(name = "mbCidade")
@ViewScoped
public class MBCidade implements Serializable {

    private static final Long serialVersionUID = 1L;
    private int aba = 1;
    private Cidade cidade = new Cidade();
    private List<Cidade> cidades = new ArrayList<>();
    ;
    private List<Cidade> cidadesFiltrada = new ArrayList<>();

    public MBCidade() {

    }

    private InterfaceDAO<Cidade> cidadeDAO() {
        InterfaceDAO<Cidade> cidadeDao = new HibernateDAO<>(Cidade.class);
        return cidadeDao;
    }

    public String editCidade() {
        return "/restrict/cadastrarCidade";//verificar
    }

    public String limpar() {
        cidade = new Cidade();
        return "/restrict/cadastrarCidade";//verificar
    }

    public void addCidade() {
        if (cidade != null) {
            System.err.println(cidade.getNome());
            if (cidade.getId() == null || cidade.getId() == 0) {
                salvarCidade();

            } else {
                updateCidade();
            }
        }

    }

    private void salvarCidade() {

        //System.err.println(FacesContext.getCurrentInstance().getAttributes());
        boolean result = cidadeDAO().merge(cidade);
        System.err.println(result);
        if (result) {

            FacesUtil.addInfoMessage("Dado salvo com sucesso (=D");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao salvar");
        }
    }

    public void delete() {
        deleteCidade();
    }

    private void deleteCidade() {
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
        aba = 1;
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

    public int getAba() {
        return aba;
    }

    public void setAba(int aba) {
        this.aba = aba;
    }
    
    
     public Cidade getCidadeById(Long id){
        return cidadeDAO().getEntity(id);
    }

}
