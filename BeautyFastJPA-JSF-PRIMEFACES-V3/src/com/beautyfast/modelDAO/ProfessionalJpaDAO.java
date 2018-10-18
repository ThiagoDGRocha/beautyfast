package com.beautyfast.modelDAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.beautyfast.modelEntidade.Professional;

/**
 * Esta classe segue o padrao de projeto Singleton que garante que apenas uma
 * instancia dessa classe sera criada durante toda a aplicacacao. Classe
 * utilizada para persistir o objeto User no banco de dados MySQL a partir dos
 * mecanismos do JPA-Hibernate
 * 
 * @author Thiago Guimaraes
 *
 */
public class ProfessionalJpaDAO {

	private static ProfessionalJpaDAO instance;
	protected EntityManager entityManager;

	public static ProfessionalJpaDAO getInstance() {
		if (instance == null) {
			instance = new ProfessionalJpaDAO();
		}

		return instance;
	}

	public ProfessionalJpaDAO() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("BeautyFastJPA-JSF-PRIMEFACES-V2");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}
	
	public Professional getById(final int id) {
		return entityManager.find(Professional.class, id);
	}
	/**
	 * Este metodo utiliza a API Criteria do JPA
	 * 
	 * @return List<Professional>
	 */
	public List<Professional> findAll() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Professional> criteria = builder.createQuery(Professional.class);
		criteria.from(Professional.class);
		return entityManager.createQuery(criteria).getResultList();
	}
	
	/**
	 * Metodo para persistir um professional.
	 * 
	 * @param professional
	 */
	public void persist(Professional professional) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(professional);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	/**
	 * Metodo para atualizar um professional.
	 * 
	 * @param professional
	 */
	public void merge(Professional professional) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(professional);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	/**
	 * Metodo para remover um professional.
	 * 
	 * @param professional
	 */
	public void remove(Professional professional) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(professional);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	/**
	 * Metodo para remover um professional por id.
	 * 
	 * @param professional
	 */
	public void removeById(final int id) {
		try {
			Professional professional = getById(id);
			remove(professional);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Este metodo utiliza a API Criteria do JPA
	 * 
	 * @return List<Administrator>
	 */
	public List<Professional> findByTxt(final String profName) {
		List<Professional> listProf = null;
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Professional> criteria = builder.createQuery(Professional.class);
			Root<Professional> u = criteria.from(Professional.class);
			criteria.where(builder.equal(u.get("profName"), builder.parameter(String.class, "profName")));
			TypedQuery<Professional> query = entityManager.createQuery(criteria);
			query.setParameter("profName", "%" + profName + "%");// pode dar erro nesta linha ao fazer a busca com %.
			listProf = query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return listProf;
	}
}
