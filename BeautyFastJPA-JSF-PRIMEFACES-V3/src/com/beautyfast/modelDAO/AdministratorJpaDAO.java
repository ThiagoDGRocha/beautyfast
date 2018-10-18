package com.beautyfast.modelDAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.beautyfast.modelEntidade.Administrator;

/**
 * Esta classe segue o padrao de projeto Singleton que garante que apenas uma
 * instancia dessa classe sera criada durante toda a aplicacacao. Classe
 * utilizada para persistir o objeto User no banco de dados MySQL a partir dos
 * mecanismos do JPA-Hibernate
 * 
 * @author Thiago Guimaraes
 *
 */
public class AdministratorJpaDAO {
	private static AdministratorJpaDAO instance;
	protected EntityManager entityManager;

	public static AdministratorJpaDAO getInstance() {
		if (instance == null) {
			instance = new AdministratorJpaDAO();
		}

		return instance;
	}

	public AdministratorJpaDAO() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("BeautyFastJPA-JSF-PRIMEFACES-V2");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}

	public Administrator getById(final int id) {
		return entityManager.find(Administrator.class, id);
	}

	/**
	 * Este método utiliza a API Criteria do JPA
	 * 
	 * @return List<Administrator>
	 */
	public List<Administrator> findAll() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Administrator> criteria = builder.createQuery(Administrator.class);
		criteria.from(Administrator.class);
		return entityManager.createQuery(criteria).getResultList();
	}

	/**
	 * método para persistir um administrador.
	 * 
	 * @param administrador
	 */
	public void persist(Administrator administrator) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(administrator);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	/**
	 * método para atualizar um administrador.
	 * 
	 * @param administrador
	 */
	public void merge(Administrator administrator) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(administrator);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	/**
	 * método para remover um administrador.
	 * 
	 * @param administrador
	 */
	public void remove(Administrator administrator) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(administrator);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	/**
	 * método para remover um administrator por id.
	 * 
	 * @param administrator
	 */
	public void removeById(final int id) {
		try {
			Administrator administrator = getById(id);
			remove(administrator);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Este método utiliza a API Criteria do JPA
	 * 
	 * @return List<Administrator>
	 */
	public List<Administrator> findByTxt(final String adminName) {
		List<Administrator> listAdministrator = null;
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Administrator> criteria = builder.createQuery(Administrator.class);
			Root<Administrator> u = criteria.from(Administrator.class);
			criteria.where(builder.equal(u.get("adminName"), builder.parameter(String.class, "adminName")));
			TypedQuery<Administrator> query = entityManager.createQuery(criteria);
			query.setParameter("adminName", "%" + adminName + "%");// pode dar erro nesta linha ao fazer a busca com %.
			listAdministrator = query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return listAdministrator;
	}
}
