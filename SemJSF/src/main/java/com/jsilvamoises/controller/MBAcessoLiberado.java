/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsilvamoises.controller;

import com.jsilvamoises.dao.HibernateDAO;
import com.jsilvamoises.dao.InterfaceDAO;
import com.jsilvamoises.model.entities.AcessoLiberado;
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
@ManagedBean(name = "mbAcessoLiberado")
@ViewScoped
public class MBAcessoLiberado implements Serializable {

    private static final Long serialVersionUID = 1L;
    private int aba = 1;
    private AcessoLiberado acessoLiberado = new AcessoLiberado();
    private List<AcessoLiberado> acessoLiberados = new ArrayList<>();
    ;
    private List<AcessoLiberado> acessoLiberadosFiltrada = new ArrayList<>();

    public MBAcessoLiberado() {

    }

    private InterfaceDAO<AcessoLiberado> acessoLiberadoDAO() {
        InterfaceDAO<AcessoLiberado> acessoLiberadoDao = new HibernateDAO<>(AcessoLiberado.class);
        return acessoLiberadoDao;
    }

    public String editAcessoLiberado() {
        return "/restrict/cadastrarAcessoLiberado";//verificar
    }

    public String limpar() {
        acessoLiberado = new AcessoLiberado();
        return "/restrict/cadastrarAcessoLiberado";//verificar
    }

    public void addAcessoLiberado() {
        if (acessoLiberado != null) {
           
            if (acessoLiberado.getId() == null || acessoLiberado.getId() == 0) {
                salvarAcessoLiberado();

            } else {
                updateAcessoLiberado();
            }
        }

    }
    
    public AcessoLiberado getAcessoLiberadoById(Long id){
        return acessoLiberadoDAO().getEntity(id);
    }

    private void salvarAcessoLiberado() {

        //System.err.println(FacesContext.getCurrentInstance().getAttributes());
        boolean result = acessoLiberadoDAO().merge(acessoLiberado);
        System.err.println(result);
        if (result) {

            FacesUtil.addInfoMessage("Dado salvo com sucesso (=D");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao salvar");
        }
    }

    public void delete() {
        deleteAcessoLiberado();
    }

    private void deleteAcessoLiberado() {
        boolean result = acessoLiberadoDAO().remove(acessoLiberado);
        if (result) {
            FacesUtil.addInfoMessage("Registro excluído com sucesso!");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao excluir");
        }
    }

    public void updateAcessoLiberado() {
        boolean result = acessoLiberadoDAO().updade(acessoLiberado);
        if (result) {

            FacesUtil.addInfoMessage("Dado salvo com sucesso");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao atualizar");
        }
    }

    public AcessoLiberado getAcessoLiberado() {
        return acessoLiberado;
    }

    public void setAcessoLiberado(AcessoLiberado acessoLiberado) {
        aba = 1;
        this.acessoLiberado = acessoLiberado;
    }

    public List<AcessoLiberado> getAcessoLiberados() {
        acessoLiberados = acessoLiberadoDAO().getEntities();
        return acessoLiberados;
    }

    public void setAcessoLiberados(List<AcessoLiberado> acessoLiberados) {
        this.acessoLiberados = acessoLiberados;
    }

    public List<AcessoLiberado> getAcessoLiberadosFiltrada() {
        return acessoLiberadosFiltrada;
    }

    public void setAcessoLiberadosFiltrada(List<AcessoLiberado> acessoLiberadosFiltrada) {
        this.acessoLiberadosFiltrada = acessoLiberadosFiltrada;
    }

    public int getAba() {
        return aba;
    }

    public void setAba(int aba) {
        this.aba = aba;
    }

}
