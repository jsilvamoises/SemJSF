/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsilvamoises.controller;

import com.jsilvamoises.dao.HibernateDAO;
import com.jsilvamoises.dao.InterfaceDAO;
import com.jsilvamoises.model.entities.TipoLogradouro;
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
@ManagedBean(name = "mbTipoLogradouro")
@ViewScoped
public class MBTipoLogradouro implements Serializable {

    private static final Long serialVersionUID = 1L;
    private int aba = 1;
    private TipoLogradouro tipoLogradouro = new TipoLogradouro();
    private List<TipoLogradouro> tipoLogradouros = new ArrayList<>();
    ;
    private List<TipoLogradouro> tipoLogradourosFiltrada = new ArrayList<>();

    public MBTipoLogradouro() {

    }

    private InterfaceDAO<TipoLogradouro> tipoLogradouroDAO() {
        InterfaceDAO<TipoLogradouro> tipoLogradouroDao = new HibernateDAO<>(TipoLogradouro.class);
        return tipoLogradouroDao;
    }

    public String editTipoLogradouro() {
        return "/restrict/cadastrarTipoLogradouro";//verificar
    }

    public String limpar() {
        tipoLogradouro = new TipoLogradouro();
        return "/restrict/cadastrarTipoLogradouro";//verificar
    }

    public void addTipoLogradouro() {
        if (tipoLogradouro != null) {
            System.err.println(tipoLogradouro.getDescricaoTipoLogradouro());
            if (tipoLogradouro.getId() == null || tipoLogradouro.getId() == 0) {
                salvarTipoLogradouro();

            } else {
                updateTipoLogradouro();
            }
        }

    }

    private void salvarTipoLogradouro() {

        //System.err.println(FacesContext.getCurrentInstance().getAttributes());
        boolean result = tipoLogradouroDAO().merge(tipoLogradouro);
        System.err.println(result);
        if (result) {

            FacesUtil.addInfoMessage("Dado salvo com sucesso (=D");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao salvar");
        }
    }

    public void delete() {
        deleteTipoLogradouro();
    }

    private void deleteTipoLogradouro() {
        boolean result = tipoLogradouroDAO().remove(tipoLogradouro);
        if (result) {
            FacesUtil.addInfoMessage("Registro excluído com sucesso!");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao excluir");
        }
    }

    public void updateTipoLogradouro() {
        boolean result = tipoLogradouroDAO().updade(tipoLogradouro);
        if (result) {

            FacesUtil.addInfoMessage("Dado salvo com sucesso");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao atualizar");
        }
    }

    public TipoLogradouro getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
        aba = 1;
        this.tipoLogradouro = tipoLogradouro;
    }

    public List<TipoLogradouro> getTipoLogradouros() {
        tipoLogradouros = tipoLogradouroDAO().getEntities();
        return tipoLogradouros;
    }

    public void setTipoLogradouros(List<TipoLogradouro> tipoLogradouros) {
        this.tipoLogradouros = tipoLogradouros;
    }

    public List<TipoLogradouro> getTipoLogradourosFiltrada() {
        return tipoLogradourosFiltrada;
    }

    public void setTipoLogradourosFiltrada(List<TipoLogradouro> tipoLogradourosFiltrada) {
        this.tipoLogradourosFiltrada = tipoLogradourosFiltrada;
    }

    public int getAba() {
        return aba;
    }

    public void setAba(int aba) {
        this.aba = aba;
    }

}
