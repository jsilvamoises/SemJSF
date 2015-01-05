/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsilvamoises.controller;

import com.jsilvamoises.dao.HibernateDAO;
import com.jsilvamoises.dao.InterfaceDAO;
import com.jsilvamoises.model.entities.TipoEndereco;
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
@ManagedBean(name = "mbTipoEndereco")
@ViewScoped
public class MBTipoEndereco implements Serializable {

    private static final Long serialVersionUID = 1L;
    private int aba = 1;
    private TipoEndereco tipoEndereco = new TipoEndereco();
    private TipoLogradouro tipoLogradouro = new TipoLogradouro();  
    private List<TipoEndereco> tipoEnderecos = new ArrayList<>();
    
    private List<TipoEndereco> tipoEnderecosFiltrada = new ArrayList<>();

    public MBTipoEndereco() {

    }

    private InterfaceDAO<TipoEndereco> tipoEnderecoDAO() {
        InterfaceDAO<TipoEndereco> tipoEnderecoDao = new HibernateDAO<>(TipoEndereco.class);
        return tipoEnderecoDao;
    }

    public String editTipoEndereco() {
        return "/restrict/cadastrarTipoEndereco";//verificar
    }

    public String limpar() {
        tipoEndereco = new TipoEndereco();
        return "/restrict/cadastrarTipoEndereco";//verificar
    }

    public void addTipoEndereco() {
        if (tipoEndereco != null) {
            System.err.println(tipoEndereco.getDescricaoTipoEndereco());
            if (tipoEndereco.getId() == null || tipoEndereco.getId() == 0) {
                salvarTipoEndereco();

            } else {
                updateTipoEndereco();
            }
        }

    }

    private void salvarTipoEndereco() {

        //System.err.println(FacesContext.getCurrentInstance().getAttributes());
        boolean result = tipoEnderecoDAO().merge(tipoEndereco);
        System.err.println(result);
        if (result) {

            FacesUtil.addInfoMessage("Dado salvo com sucesso (=D");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao salvar");
        }
    }

    public void delete() {
        deleteTipoEndereco();
    }

    private void deleteTipoEndereco() {
        boolean result = tipoEnderecoDAO().remove(tipoEndereco);
        if (result) {
            FacesUtil.addInfoMessage("Registro excluído com sucesso!");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao excluir");
        }
    }

    public void updateTipoEndereco() {
        boolean result = tipoEnderecoDAO().updade(tipoEndereco);
        if (result) {

            FacesUtil.addInfoMessage("Dado salvo com sucesso");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao atualizar");
        }
    }

    public TipoEndereco getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(TipoEndereco tipoEndereco) {
        aba = 1;
        this.tipoEndereco = tipoEndereco;
    }

    public List<TipoEndereco> getTipoEnderecos() {
        tipoEnderecos = tipoEnderecoDAO().getEntities();
        return tipoEnderecos;
    }

    public void setTipoEnderecos(List<TipoEndereco> tipoEnderecos) {
        this.tipoEnderecos = tipoEnderecos;
    }

    public List<TipoEndereco> getTipoEnderecosFiltrada() {
        return tipoEnderecosFiltrada;
    }

    public void setTipoEnderecosFiltrada(List<TipoEndereco> tipoEnderecosFiltrada) {
        this.tipoEnderecosFiltrada = tipoEnderecosFiltrada;
    }

    public int getAba() {
        return aba;
    }

    public void setAba(int aba) {
        this.aba = aba;
    }

    public TipoLogradouro getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }
    
      public TipoEndereco getTipoEnderecoById(Long id){
        return tipoEnderecoDAO().getEntity(id);
    }

}
