/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsilvamoises.dao;

import com.jsilvamoises.util.FacesUtil;
import com.jsilvamoises.util.HibernateUtil;
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

    public HibernateDAO(Class<T> classe) {
        super();
        this.classe = classe;

    }

    @Override
    public boolean save(T Entity) {
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.getTransaction().begin();
            session.saveOrUpdate(Entity);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            FacesUtil.addErrorMessage("Erro .: " + e.toString());
            return false;
        } finally {

        }
    }

    @Override
    public boolean updade(T Entity) {
        try {

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.getTransaction().begin();
            session.saveOrUpdate(Entity);
            session.getTransaction().commit();

            return true;
        } catch (Exception e) {
            FacesUtil.addErrorMessage("Erro.: " + e);
            session.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean remove(T Entity) {
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.getTransaction().begin();
            session.delete(Entity);
            session.getTransaction().commit();

            return true;
        } catch (Exception e) {
            FacesUtil.addErrorMessage("Erro.: " + e);
            session.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean merge(T Entity) {
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.getTransaction().begin();
            session.merge(Entity);
            session.getTransaction().commit();

            return true;
        } catch (Exception e) {
            FacesUtil.addErrorMessage("Erro.: " + e);
            session.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public T getEntity(Serializable id) {
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.getTransaction().begin();
            T entity = (T) session.get(classe, id);
            return entity;
        } catch (Exception e) {
            return null;
        } finally {
            session.getTransaction().commit();
        }

    }

    @Override
    public T getEntityByDetachedCriteria(DetachedCriteria detachedCriteria) {
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.getTransaction().begin();
            T entity = (T) detachedCriteria.getExecutableCriteria(session).uniqueResult();
            return entity;
        } catch (Exception e) {
            return null;
        } finally {
            session.getTransaction().commit();
        }
    }

    @Override
    public List<T> getEntities() {
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.getTransaction().begin();
            List<T> entities = (List<T>) session.createCriteria(classe).list();
            return entities;
        } catch (Exception e) {
            return null;
        } finally {
            session.getTransaction().commit();
        }
    }

    @Override
    public List<T> getEntitiesByDetachedCriteria(DetachedCriteria detachedCriteria) {
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.getTransaction().begin();
            return detachedCriteria.getExecutableCriteria(session).list();
        } catch (Exception e) {
            return null;
        } finally {
            session.getTransaction().commit();
        }
    }

}
