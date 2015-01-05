/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsilvamoises.controller;

import com.jsilvamoises.dao.HibernateDAO;
import com.jsilvamoises.dao.InterfaceDAO;
import com.jsilvamoises.model.entities.TipoCodigoBarra;
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
@ManagedBean(name = "mbTipoCodigoBarra")
@ViewScoped
public class MBTipoCodigoBarra implements Serializable {

    private static final Long serialVersionUID = 1L;
    private int aba = 1;
    private TipoCodigoBarra tipoCodigoBarra = new TipoCodigoBarra();
    private List<TipoCodigoBarra> tipoCodigoBarras = new ArrayList<>();
    ;
    private List<TipoCodigoBarra> tipoCodigoBarrasFiltrada = new ArrayList<>();

    public MBTipoCodigoBarra() {

    }

    private InterfaceDAO<TipoCodigoBarra> tipoCodigoBarraDAO() {
        InterfaceDAO<TipoCodigoBarra> tipoCodigoBarraDao = new HibernateDAO<>(TipoCodigoBarra.class);
        return tipoCodigoBarraDao;
    }

    public String editTipoCodigoBarra() {
        return "/restrict/cadastrarTipoCodigoBarra";//verificar
    }

    public String limpar() {
        tipoCodigoBarra = new TipoCodigoBarra();
        return "/restrict/cadastrarTipoCodigoBarra";//verificar
    }

    public void addTipoCodigoBarra() {
        if (tipoCodigoBarra != null) {
            System.err.println(tipoCodigoBarra.getDescricao());
            if (tipoCodigoBarra.getId() == null || tipoCodigoBarra.getId() == 0) {
                salvarTipoCodigoBarra();

            } else {
                updateTipoCodigoBarra();
            }
        }

    }

    private void salvarTipoCodigoBarra() {

        //System.err.println(FacesContext.getCurrentInstance().getAttributes());
        boolean result = tipoCodigoBarraDAO().merge(tipoCodigoBarra);
        System.err.println(result);
        if (result) {

            FacesUtil.addInfoMessage("Dado salvo com sucesso (=D");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao salvar");
        }
    }

    public void delete() {
        deleteTipoCodigoBarra();
    }

    private void deleteTipoCodigoBarra() {
        boolean result = tipoCodigoBarraDAO().remove(tipoCodigoBarra);
        if (result) {
            FacesUtil.addInfoMessage("Registro excluído com sucesso!");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao excluir");
        }
    }

    public void updateTipoCodigoBarra() {
        boolean result = tipoCodigoBarraDAO().updade(tipoCodigoBarra);
        if (result) {

            FacesUtil.addInfoMessage("Dado salvo com sucesso");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao atualizar");
        }
    }

    public TipoCodigoBarra getTipoCodigoBarra() {
        return tipoCodigoBarra;
    }

    public void setTipoCodigoBarra(TipoCodigoBarra tipoCodigoBarra) {
        aba = 1;
        this.tipoCodigoBarra = tipoCodigoBarra;
    }

    public List<TipoCodigoBarra> getTipoCodigoBarras() {
        tipoCodigoBarras = tipoCodigoBarraDAO().getEntities();
        return tipoCodigoBarras;
    }

    public void setTipoCodigoBarras(List<TipoCodigoBarra> tipoCodigoBarras) {
        this.tipoCodigoBarras = tipoCodigoBarras;
    }

    public List<TipoCodigoBarra> getTipoCodigoBarrasFiltrada() {
        return tipoCodigoBarrasFiltrada;
    }

    public void setTipoCodigoBarrasFiltrada(List<TipoCodigoBarra> tipoCodigoBarrasFiltrada) {
        this.tipoCodigoBarrasFiltrada = tipoCodigoBarrasFiltrada;
    }

    public int getAba() {
        return aba;
    }

    public void setAba(int aba) {
        this.aba = aba;
    }
    
    
     public TipoCodigoBarra getTipoCodigoBarraById(Long id){
        return tipoCodigoBarraDAO().getEntity(id);
    }

}
