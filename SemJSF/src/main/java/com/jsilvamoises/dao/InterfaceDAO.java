/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsilvamoises.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;

/**
 *
 * @author Moises
 * @param <T>
 */
public interface InterfaceDAO<T> {
    boolean save(T Entity);
    boolean updade(T Entity);
    boolean remove(T Entity);
    boolean merge(T Entity);
    T getEntity(Serializable id);
    T getEntityByDetachedCriteria(DetachedCriteria detachedCriteria);
    List<T> getEntities();
    List<T> getEntitiesByDetachedCriteria(DetachedCriteria detachedCriteria);
}
