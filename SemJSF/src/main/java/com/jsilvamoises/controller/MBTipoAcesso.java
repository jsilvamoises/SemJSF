/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsilvamoises.controller;

import com.jsilvamoises.dao.HibernateDAO;
import com.jsilvamoises.dao.InterfaceDAO;
import com.jsilvamoises.model.entities.TipoAcesso;
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
@ManagedBean(name = "mbTipoAcesso")
@ViewScoped
public class MBTipoAcesso implements Serializable {

    private static final Long serialVersionUID = 1L;
    private int aba = 1;
    private TipoAcesso tipoAcesso = new TipoAcesso();
    private List<TipoAcesso> tipoAcessos = new ArrayList<>();
    ;
    private List<TipoAcesso> tipoAcessosFiltrada = new ArrayList<>();

    public MBTipoAcesso() {

    }

    private InterfaceDAO<TipoAcesso> tipoAcessoDAO() {
        InterfaceDAO<TipoAcesso> tipoAcessoDao = new HibernateDAO<>(TipoAcesso.class);
        return tipoAcessoDao;
    }

    public String editTipoAcesso() {
        return "/restrict/cadastrarTipoAcesso";//verificar
    }

    public String limpar() {
        tipoAcesso = new TipoAcesso();
        return "/restrict/cadastrarTipoAcesso";//verificar
    }

    public void addTipoAcesso() {
        if (tipoAcesso != null) {
            System.err.println(tipoAcesso.getDescricao());
            if (tipoAcesso.getId() == null || tipoAcesso.getId() == 0) {
                salvarTipoAcesso();

            } else {
                updateTipoAcesso();
            }
        }

    }
    
    public TipoAcesso getTipoAcessoById(Long id){
        return tipoAcessoDAO().getEntity(id);
    }

    private void salvarTipoAcesso() {

        //System.err.println(FacesContext.getCurrentInstance().getAttributes());
        boolean result = tipoAcessoDAO().merge(tipoAcesso);
        System.err.println(result);
        if (result) {

            FacesUtil.addInfoMessage("Dado salvo com sucesso (=D");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao salvar");
        }
    }

    public void delete() {
        deleteTipoAcesso();
    }

    private void deleteTipoAcesso() {
        boolean result = tipoAcessoDAO().remove(tipoAcesso);
        if (result) {
            FacesUtil.addInfoMessage("Registro excluído com sucesso!");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao excluir");
        }
    }

    public void updateTipoAcesso() {
        boolean result = tipoAcessoDAO().updade(tipoAcesso);
        if (result) {

            FacesUtil.addInfoMessage("Dado salvo com sucesso");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao atualizar");
        }
    }

    public TipoAcesso getTipoAcesso() {
        return tipoAcesso;
    }

    public void setTipoAcesso(TipoAcesso tipoAcesso) {
        aba = 1;
        this.tipoAcesso = tipoAcesso;
    }

    public List<TipoAcesso> getTipoAcessos() {
        tipoAcessos = tipoAcessoDAO().getEntities();
        return tipoAcessos;
    }

    public void setTipoAcessos(List<TipoAcesso> tipoAcessos) {
        this.tipoAcessos = tipoAcessos;
    }

    public List<TipoAcesso> getTipoAcessosFiltrada() {
        return tipoAcessosFiltrada;
    }

    public void setTipoAcessosFiltrada(List<TipoAcesso> tipoAcessosFiltrada) {
        this.tipoAcessosFiltrada = tipoAcessosFiltrada;
    }

    public int getAba() {
        return aba;
    }

    public void setAba(int aba) {
        this.aba = aba;
    }

}
