/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsilvamoises.controller;

import com.jsilvamoises.dao.HibernateDAO;
import com.jsilvamoises.dao.InterfaceDAO;
import com.jsilvamoises.model.entities.Endereco;
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
@ManagedBean(name = "mbEndereco")
@ViewScoped
public class MBEndereco implements Serializable {

    private static final Long serialVersionUID = 1L;
    private int aba = 1;
    private Endereco endereco = new Endereco();
   
    private List<Endereco> enderecos = new ArrayList<>();
   
   
    private List<Endereco> enderecosFiltrada = new ArrayList<>();

    public MBEndereco() {

    }

    private InterfaceDAO<Endereco> enderecoDAO() {
        InterfaceDAO<Endereco> enderecoDao = new HibernateDAO<>(Endereco.class);
        return enderecoDao;
    }

    public String editEndereco() {
        return "/restrict/cadastrarEndereco";//verificar
    }

    public String limpar() {        
        endereco = new Endereco();
        return "/restrict/cadastrarEndereco";//verificar
    }

    public void addEndereco() {
        if (endereco != null) {
            System.err.println(endereco.getNomeLogradouro());
            if (endereco.getId() == null || endereco.getId() == 0) {
                salvarEndereco();

            } else {
                updateEndereco();
            }
        }

    }

    private void salvarEndereco() {

        //System.err.println(FacesContext.getCurrentInstance().getAttributes());
        boolean result = enderecoDAO().merge(endereco);
        System.err.println(result);
        if (result) {

            FacesUtil.addInfoMessage("Dado salvo com sucesso (=D");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao salvar");
        }
    }

    public void delete() {
        deleteEndereco();
    }

    private void deleteEndereco() {
        boolean result = enderecoDAO().remove(endereco);
        if (result) {
            FacesUtil.addInfoMessage("Registro excluído com sucesso!");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao excluir");
        }
    }

    public void updateEndereco() {
        boolean result = enderecoDAO().updade(endereco);
        if (result) {

            FacesUtil.addInfoMessage("Dado salvo com sucesso");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao atualizar");
        }
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        aba = 1;
        this.endereco = endereco;
    }

    public List<Endereco> getEnderecos() {
        enderecos = enderecoDAO().getEntities();
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Endereco> getEnderecosFiltrada() {
        return enderecosFiltrada;
    }

    public void setEnderecosFiltrada(List<Endereco> enderecosFiltrada) {
        this.enderecosFiltrada = enderecosFiltrada;
    }

    public int getAba() {
        return aba;
    }

    public void setAba(int aba) {
        this.aba = aba;
    }

     public Endereco getEnderecoById(Long id){
        return enderecoDAO().getEntity(id);
    }

}
