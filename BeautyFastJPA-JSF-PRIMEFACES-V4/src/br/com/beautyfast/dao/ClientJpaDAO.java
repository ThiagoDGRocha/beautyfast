package br.com.beautyfast.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.beautyfast.entidade.Client;

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
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("BeautyFastV4");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}
		return entityManager;
	}

	/**
	 * Este método obtem um cliente por id.
	 * 
	 * @param idClient
	 * @return Professional
	 */
	public Client getById(final int idClient) {
		return entityManager.find(Client.class, idClient);
	}

	/**
	 * Este metodo lista todos os clientes.
	 * 
	 * @return List<Client>
	 */
	public List<Client> findAll() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Client> criteria = builder.createQuery(Client.class);
		criteria.from(Client.class);
		return entityManager.createQuery(criteria).getResultList();
	}

	/**
	 * Este método persisti um Cliente.
	 * 
	 * @param client
	 * @throws Exception
	 */
	public void persist(Client client) throws Exception {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(client);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			entityManager.getTransaction().rollback();
			throw new Exception(ex.getMessage());
		}
	}

	/**
	 * Este método atualizar um Cliente.
	 * 
	 * @param client
	 * @throws Exception
	 */
	public void merge(Client client) throws Exception {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(client);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			entityManager.getTransaction().rollback();
			throw new Exception(ex.getMessage());
		}
	}

	/**
	 * Este método remove um Cliente.
	 * 
	 * @param client
	 * @throws Exception
	 */
	public void remove(Client client) throws Exception {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(client);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			entityManager.getTransaction().rollback();
			throw new Exception(ex.getMessage());
		}
	}

	/**
	 * Este método remove um cliente por id.
	 * 
	 * @param idClient
	 * @throws Exception
	 */
	public void removeById(final int idClient) throws Exception {
		try {
			Client client = getById(idClient);
			remove(client);
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}

	/**
	 * Este método busca um cliente por parte do nome baseada na API Criteria do
	 * JPA.
	 *
	 * @return List<Client>
	 * @throws Exception
	 */
	public List<Client> findByTxt(final String clientName) throws Exception {
		List<Client> listClient = null;
		try {// qual está funcionando? findByTxt do cliente ou do administrador?
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Client> criteria = builder.createQuery(Client.class);
			Root<Client> from = criteria.from(Client.class);
			Predicate predicate = builder.and();
			predicate = builder.and(predicate, builder.like(from.<String>get("clientName"), "%" + clientName + "%"));
			TypedQuery<Client> query = entityManager
					.createQuery(criteria.select(from).where(predicate).orderBy(builder.asc(from.get("clientName"))));
			listClient = query.getResultList();
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return listClient;
	}
}
