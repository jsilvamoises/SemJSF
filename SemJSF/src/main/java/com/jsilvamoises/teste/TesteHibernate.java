/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsilvamoises.teste;

import com.jsilvamoises.util.HibernateUtil;



import org.hibernate.Session;

/**
 *
 * @author Moises
 */
public class TesteHibernate {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        new TesteHibernate().conectar();
    }
  
   private void conectar(){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.getTransaction().begin();
        s.close();
        System.exit(0);
    }
    
}
