/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsilvamoises.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

/**
 *
 * @author Moises
 * @param <T>
 */
public class HibernateDAO<T> implements InterfaceDAO<T> {

    private static final Long SerialVersionUID = 1l;
    private Class<T> classe;
    private Session session;

    public HibernateDAO(Class<T> classe, Session Session) {
        super();
        this.classe = classe;
        this.session = Session;
    }

    @Override
    public boolean save(T Entity) {
        try {
           
            session.save(Entity);
           
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean updade(T Entity) {
        try {
            
            session.update(Entity);
            
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean remove(T Entity) {
        try {
           
            session.delete(Entity);
            
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean merge(T Entity) {
        try {
           
            session.merge(Entity);
          
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public T getEntity(Serializable id) {
        try {
            T entity = (T) session.get(classe, id);
            return entity;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public T getEntityByDetachedCriteria(DetachedCriteria detachedCriteria) {
        try {
            T entity = (T) detachedCriteria.getExecutableCriteria(session).uniqueResult();
            return entity;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<T> getEntities() {
        try {
            List<T> entities = (List<T>) session.createCriteria(classe).list();
            return entities;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<T> getEntitiesByDetachedCriteria(DetachedCriteria detachedCriteria) {
        try {
            return detachedCriteria.getExecutableCriteria(session).list();
        } catch (Exception e) {
            return null;
        }
    }

}
