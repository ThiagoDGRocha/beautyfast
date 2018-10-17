package modeloDAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modeloEntidade.Client;

/**
 * Esta classe segue o padrão de projeto Singleton que garante que apenas uma
 * instância dessa classe será criada durante toda a aplicação. Classe utilizada
 * para persistir o objeto User no banco de dados MySQL a partir dos mecanismos
 * do JPA-Hibernate
 * 
 * @author penks
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
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("versaoTresJPA-Hibernate-Web");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}

	public Client getById(final int id) {
		return entityManager.find(Client.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Client> findAll() {
		return entityManager.createQuery("FROM " + Client.class.getName()).getResultList();
	}

	/**
	 * Métoto para persistir um Cliente.
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
	 * Métoto para atualizar um Cliente.
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
	 * Métoto para remover um Cliente.
	 * 
	 * @param client
	 */
	public void remove(Client client) {
		try {
			entityManager.getTransaction().begin();
			client = entityManager.find(Client.class, client.getClientId()); /*user como parametro, pode gerar um erro, pois este metodo precisa do id do User.*/
			entityManager.remove(client);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	/**
	 * Métoto para remover um cliente por id.
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
}
