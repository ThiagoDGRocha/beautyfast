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

import com.beautyfast.modelEntidade.Client;

/**
 * Esta classe segue o padrao de projeto Singleton que garante que apenas uma
 * instancia dessa classe sera criada durante toda a aplicacacao. Classe
 * utilizada para persistir o objeto User no banco de dados MySQL a partir dos
 * mecanismos do JPA-Hibernate
 * 
 * @author Thiago Guimarães
 *
 */
public class ClientJpaDAO {
	private static ClientJpaDAO instance;
	protected EntityManager entityManager;

	public static ClientJpaDAO getInstance() {
		if (instance == null) {
			instance = new ClientJpaDAO();
		}

		return instance;
	}

	public ClientJpaDAO() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("BeautyFastJPA-JSF-PRIMEFACES-V2");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}

	public Client getById(final int id) {
		return entityManager.find(Client.class, id);
	}

	public List<Client> findAll() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Client> criteria = builder.createQuery(Client.class);
		criteria.from(Client.class);
		return entityManager.createQuery(criteria).getResultList();
	}

	/**
	 * Método para persistir um Cliente.
	 * 
	 * @param client
	 */
	public void persist(Client client) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(client);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	/**
	 * Método para atualizar um Cliente.
	 * 
	 * @param client
	 */
	public void merge(Client client) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(client);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	/**
	 * Método para remover um Cliente.
	 * 
	 * @param client
	 */
	public void remove(Client client) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(client);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	/**
	 * Método para remover um cliente por id.
	 * 
	 * @param client
	 */
	public void removeById(final int id) {
		try {
			Client client = getById(id);
			remove(client);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Este metodo utiliza a API Criteria do JPA
	 * 
	 * @return List<Client>
	 */
	public List<Client> findByTxt(final String clientName) {
		List<Client> listClient = null;
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Client> criteria = builder.createQuery(Client.class);
			Root<Client> from = criteria.from(Client.class);
			Predicate predicate = builder.and();
			predicate = builder.and(predicate, builder.like(from.<String>get("nameClient"), "%" + clientName + "%"));// client.nameClient
																														// like
																														// %%
			TypedQuery<Client> query = entityManager
					.createQuery(criteria.select(from).where(predicate).orderBy(builder.asc(from.get("clientName"))));
			listClient = query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return listClient;
	}
}
