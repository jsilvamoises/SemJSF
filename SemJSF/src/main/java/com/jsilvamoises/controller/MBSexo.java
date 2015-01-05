/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsilvamoises.controller;

import com.jsilvamoises.dao.HibernateDAO;
import com.jsilvamoises.dao.InterfaceDAO;
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
@ManagedBean(name = "mbSexo")
@ViewScoped
public class MBSexo implements Serializable {

    private static final Long serialVersionUID = 1L;
    private int aba = 1;
    private Sexo sexo = new Sexo();
    private List<Sexo> sexos = new ArrayList<>();
    ;
    private List<Sexo> sexosFiltrada = new ArrayList<>();

    public MBSexo() {

    }

    private InterfaceDAO<Sexo> sexoDAO() {
        InterfaceDAO<Sexo> sexoDao = new HibernateDAO<>(Sexo.class);
        return sexoDao;
    }

    public String editSexo() {
        return "/restrict/cadastrarSexo";//verificar
    }

    public String limpar() {
        sexo = new Sexo();
        return "/restrict/cadastrarSexo";//verificar
    }

    public void addSexo() {
        if (sexo != null) {
            System.err.println(sexo.getDescricao());
            if (sexo.getId() == null || sexo.getId() == 0) {
                salvarSexo();

            } else {
                updateSexo();
            }
        }

    }
    
    public Sexo getSexoById(Long id){
        return sexoDAO().getEntity(id);
    }

    private void salvarSexo() {

        //System.err.println(FacesContext.getCurrentInstance().getAttributes());
        boolean result = sexoDAO().merge(sexo);
        System.err.println(result);
        if (result) {

            FacesUtil.addInfoMessage("Dado salvo com sucesso (=D");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao salvar");
        }
    }

    public void delete() {
        deleteSexo();
    }

    private void deleteSexo() {
        boolean result = sexoDAO().remove(sexo);
        if (result) {
            FacesUtil.addInfoMessage("Registro excluído com sucesso!");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao excluir");
        }
    }

    public void updateSexo() {
        boolean result = sexoDAO().updade(sexo);
        if (result) {

            FacesUtil.addInfoMessage("Dado salvo com sucesso");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao atualizar");
        }
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        aba = 1;
        this.sexo = sexo;
    }

    public List<Sexo> getSexos() {
        sexos = sexoDAO().getEntities();
        return sexos;
    }

    public void setSexos(List<Sexo> sexos) {
        this.sexos = sexos;
    }

    public List<Sexo> getSexosFiltrada() {
        return sexosFiltrada;
    }

    public void setSexosFiltrada(List<Sexo> sexosFiltrada) {
        this.sexosFiltrada = sexosFiltrada;
    }

    public int getAba() {
        return aba;
    }

    public void setAba(int aba) {
        this.aba = aba;
    }

}
