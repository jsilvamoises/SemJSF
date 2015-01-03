/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsilvamoises.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import org.hibernate.Session;

/**
 *
 * @author Moises
 */
public class PhaseListener implements javax.faces.event.PhaseListener {

    /**
     * Antes da Fase
     *
     * @param pe
     */
    @Override
    public void beforePhase(PhaseEvent fase) {
        if (fase.getPhaseId().equals(PhaseId.RESTORE_VIEW)) {
            System.out.println("Antes da fase "+getPhaseId().getName());
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            FacesContextUtil.setRequestSession(session);
        }
    }

    /**
     * Depois da fase
     *
     * @param pe
     */
    @Override
    public void afterPhase(PhaseEvent fase) {
        if (fase.getPhaseId().equals(PhaseId.RENDER_RESPONSE)) {
             System.out.println("Depois da fase "+getPhaseId().getName());
            Session session = FacesContextUtil.getRequestSession();
            try {
                session.getTransaction().commit();
            } catch (Exception e) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }

            }finally{
                session.close();
            }
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

}
