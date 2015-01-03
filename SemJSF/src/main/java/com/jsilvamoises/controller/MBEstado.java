/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsilvamoises.controller;

import com.jsilvamoises.dao.HibernateDAO;
import com.jsilvamoises.dao.InterfaceDAO;
import com.jsilvamoises.model.entities.Estado;
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
@ManagedBean(name = "mbEstado")
@ViewScoped
public class MBEstado implements Serializable {

    private static final Long serialVersionUID = 1L;
    private int aba = 1;
    private Estado estado = new Estado();
    private List<Estado> estados = new ArrayList<>();
    ;
    private List<Estado> estadosFiltrada = new ArrayList<>();

    public MBEstado() {

    }

    private InterfaceDAO<Estado> estadoDAO() {
        InterfaceDAO<Estado> estadoDao = new HibernateDAO<>(Estado.class);
        return estadoDao;
    }

    public String editEstado() {
        return "/restrict/cadastrarEstado";//verificar
    }

    public String limpar() {
        estado = new Estado();
        return "/restrict/cadastrarEstado";//verificar
    }

    public void addEstado() {
        if (estado != null) {
            System.err.println(estado.getNome());
            if (estado.getId() == null || estado.getId() == 0) {
                salvarEstado();

            } else {
                updateEstado();
            }
        }

    }

    private void salvarEstado() {

        //System.err.println(FacesContext.getCurrentInstance().getAttributes());
        boolean result = estadoDAO().merge(estado);
        System.err.println(result);
        if (result) {

            FacesUtil.addInfoMessage("Dado salvo com sucesso (=D");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao salvar");
        }
    }

    public void delete() {
        deleteEstado();
    }

    private void deleteEstado() {
        boolean result = estadoDAO().remove(estado);
        if (result) {
            FacesUtil.addInfoMessage("Registro excluído com sucesso!");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao excluir");
        }
    }

    public void updateEstado() {
        boolean result = estadoDAO().updade(estado);
        if (result) {

            FacesUtil.addInfoMessage("Dado salvo com sucesso");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao atualizar");
        }
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        aba = 1;
        this.estado = estado;
    }

    public List<Estado> getEstados() {
        estados = estadoDAO().getEntities();
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public List<Estado> getEstadosFiltrada() {
        return estadosFiltrada;
    }

    public void setEstadosFiltrada(List<Estado> estadosFiltrada) {
        this.estadosFiltrada = estadosFiltrada;
    }

    public int getAba() {
        return aba;
    }

    public void setAba(int aba) {
        this.aba = aba;
    }

}
