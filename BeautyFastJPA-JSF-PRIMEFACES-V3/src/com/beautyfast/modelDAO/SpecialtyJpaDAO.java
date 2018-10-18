package com.beautyfast.modelDAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.beautyfast.modelEntidade.Specialty;

/**
 * Esta classe segue o padrao de projeto Singleton que garante que apenas uma
 * instancia dessa classe sera criada durante toda a aplicacacao. Classe
 * utilizada para persistir o objeto User no banco de dados MySQL a partir dos
 * mecanismos do JPA-Hibernate
 * 
 * @author Thiago Guimarães
 *
 */
public class SpecialtyJpaDAO {
	private static SpecialtyJpaDAO instance;
	protected EntityManager entityManager;

	public static SpecialtyJpaDAO getInstance() {
		if (instance == null) {
			instance = new SpecialtyJpaDAO();
		}
		return instance;
	}

	public SpecialtyJpaDAO() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("BeautyFastJPA-JSF-PRIMEFACES-V2");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}
		return entityManager;
	}

	public Specialty getById(final int id) {
		return entityManager.find(Specialty.class, id);
	}	
	
	public void persist(Specialty specialty) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(specialty);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void merge(Specialty specialty) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(specialty);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}	
	}

	public void removeById(int idSpecialty) {
		try {
			Specialty specialty = getById(idSpecialty);
			remove(specialty);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void remove(Specialty specialty) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(specialty);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	public List<Specialty> findAll() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Specialty> criteria = builder.createQuery(Specialty.class);
		criteria.from(Specialty.class);
		return entityManager.createQuery(criteria).getResultList();
	}

	public List<Specialty> findByTxt(final String txt) {
		List<Specialty> listSpecialty = null;
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Specialty> criteria = builder.createQuery(Specialty.class);
			Root<Specialty> from = criteria.from(Specialty.class);
			Predicate predicate = builder.and();
			predicate = builder.and(predicate, builder.like(from.<String>get("nameSpecialty"), "%" + txt + "%"));// client.nameClient
																														// like
																														// %%
			TypedQuery<Specialty> query = entityManager
					.createQuery(criteria.select(from).where(predicate).orderBy(builder.asc(from.get("nameSpecialty"))));
			listSpecialty = query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return listSpecialty;
	}

}
