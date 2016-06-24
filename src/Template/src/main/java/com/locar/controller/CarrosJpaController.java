/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.locar.controller;

import com.locar.CRUD.Carros;
import com.locar.controller.exceptions.NonexistentEntityException;
import com.locar.controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Fernando
 */
public class CarrosJpaController implements Serializable {

    public CarrosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Carros carros) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(carros);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCarros(carros.getPlaca()) != null) {
                throw new PreexistingEntityException("Carros " + carros + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Carros carros) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            carros = em.merge(carros);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = carros.getPlaca();
                if (findCarros(id) == null) {
                    throw new NonexistentEntityException("The carros with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Carros carros;
            try {
                carros = em.getReference(Carros.class, id);
                carros.getPlaca();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The carros with id " + id + " no longer exists.", enfe);
            }
            em.remove(carros);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Carros> findCarrosEntities() {
        return findCarrosEntities(true, -1, -1);
    }

    public List<Carros> findCarrosEntities(int maxResults, int firstResult) {
        return findCarrosEntities(false, maxResults, firstResult);
    }

    private List<Carros> findCarrosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Carros.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Carros findCarros(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Carros.class, id);
        } finally {
            em.close();
        }
    }

    public int getCarrosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Carros> rt = cq.from(Carros.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
