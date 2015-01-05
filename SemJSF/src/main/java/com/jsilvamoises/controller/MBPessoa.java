/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsilvamoises.controller;

import com.jsilvamoises.dao.HibernateDAO;
import com.jsilvamoises.dao.InterfaceDAO;
import com.jsilvamoises.model.entities.AcessoLiberado;
import com.jsilvamoises.model.entities.Endereco;
import com.jsilvamoises.model.entities.Pessoa;
import com.jsilvamoises.model.entities.TipoAcesso;
import com.jsilvamoises.util.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Moises
 */
@ManagedBean(name = "mbPessoa")
@ViewScoped
public class MBPessoa implements Serializable {

    private static final Long serialVersionUID = 1L;
    private int aba = 1;
    private Pessoa pessoa = new Pessoa();
    private Endereco endereco = new Endereco();
   
    private List<Endereco> enderecos = new ArrayList<>();
    private List<Pessoa> pessoas = new ArrayList<>();
    
    private List<TipoAcesso> tipoAcessos = new ArrayList<>();
    private TipoAcesso tipoAcesso = new TipoAcesso();
    private List<AcessoLiberado> acessoLiberados = new ArrayList<>();
    private AcessoLiberado acessoSelecionado = new AcessoLiberado();
    private List<Pessoa> pessoasFiltrada = new ArrayList<>();

    public MBPessoa() {

    }
    
    public void init(){
        System.out.println("Passou aqui");
        tipoAcessos = new MBTipoAcesso().getTipoAcessos();
    }

    private InterfaceDAO<Pessoa> pessoaDAO() {
        InterfaceDAO<Pessoa> pessoaDao = new HibernateDAO<>(Pessoa.class);
        return pessoaDao;
    }

    public String editPessoa() {
        return "/restrict/cadastrarPessoa";//verificar
    }

    public String limpar() {
        pessoa = new Pessoa();
        endereco = new Endereco();
       
        return "/restrict/cadastrarPessoa";//verificar
    }
    
    
    public void addAcesso(){
        System.err.println("DDDD");
        acessoSelecionado.setTipoAcesso(tipoAcesso);
        System.err.println("Adicionou"+ acessoSelecionado.getTipoAcesso().getDescricao());
        acessoSelecionado.setPessoa(pessoa); 
        acessoLiberados.add(acessoSelecionado);
        
    }
    
    public List<Pessoa> listByNomeLike(String nome){
        DetachedCriteria criteria = DetachedCriteria.forClass(Pessoa.class) ;
        criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
        return pessoaDAO().getEntitiesByDetachedCriteria(criteria);
    }

    public String addPessoa() {
        if (pessoa != null) {
            System.err.println(pessoa.getNome());
            if (pessoa.getId() == null || pessoa.getId() == 0) {
                salvarPessoa();
                return "/restrict/cadastrarPessoa";//verificar
            } else {
                updatePessoa();
                return "/restrict/cadastrarPessoa";//verificar
            }
            
        }
      return null;
    }

    private void salvarPessoa() {
        pessoa.setEndereco(endereco);
        pessoa.setDataCadastro(Calendar.getInstance().getTime());
        endereco.setPessoa(pessoa);      
       
        boolean result = pessoaDAO().merge(pessoa);
        System.err.println(result);
        if (result) {

            FacesUtil.addInfoMessage("Dado salvo com sucesso (=D");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao salvar");
        }
    }

    public void delete() {
        deletePessoa();
    }

    private void deletePessoa() {
        boolean result = pessoaDAO().remove(pessoa);
        if (result) {
            FacesUtil.addInfoMessage("Registro excluído com sucesso!");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao excluir");
        }
    }

    public void updatePessoa() {
        boolean result = pessoaDAO().updade(pessoa);
        if (result) {

            FacesUtil.addInfoMessage("Dado salvo com sucesso");
            limpar();
        } else {
            FacesUtil.addInfoMessage("Erro ao atualizar");
        }
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        aba = 1;
        this.pessoa = pessoa;
    }

    public List<Pessoa> getPessoas() {
        pessoas = pessoaDAO().getEntities();
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public List<Pessoa> getPessoasFiltrada() {
        return pessoasFiltrada;
    }

    public void setPessoasFiltrada(List<Pessoa> pessoasFiltrada) {
        this.pessoasFiltrada = pessoasFiltrada;
    }

    public int getAba() {
        return aba;
    }

    public void setAba(int aba) {
        this.aba = aba;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

     public Pessoa getCidadeById(Long id){
        return pessoaDAO().getEntity(id);
    }

    public List<TipoAcesso> getTipoAcessos() {
        return tipoAcessos;
    }

    public void setTipoAcessos(List<TipoAcesso> tipoAcessos) {
        this.tipoAcessos = tipoAcessos;
    }

    public AcessoLiberado getAcessoSelecionado() {
        return acessoSelecionado;
    }

    public void setAcessoSelecionado(AcessoLiberado acessoSelecionado) {
        this.acessoSelecionado = acessoSelecionado;
    }

    public List<AcessoLiberado> getAcessoLiberados() {
        return acessoLiberados;
    }

    public void setAcessoLiberados(List<AcessoLiberado> acessoLiberados) {
        this.acessoLiberados = acessoLiberados;
    }

    public TipoAcesso getTipoAcesso() {
        return tipoAcesso;
    }

    public void setTipoAcesso(TipoAcesso tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }

}
