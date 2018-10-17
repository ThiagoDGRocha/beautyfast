package modeloDAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modeloEntidade.Administrator;

/**
 * Esta classe segue o padrão de projeto Singleton que garante que apenas uma
 * instância dessa classe será criada durante toda a aplicação. Classe utilizada
 * para persistir o objeto User no banco de dados MySQL a partir dos mecanismos
 * do JPA-Hibernate
 * 
 * @author penks
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
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("versaoTresJPA-Hibernate-Web");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}

	public Administrator getById(final int id) {
		return entityManager.find(Administrator.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Administrator> findAll() {
		return entityManager.createQuery("FROM " + Administrator.class.getName()).getResultList();
	}

	/**
	 * M�toto para persistir um administrador.
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
	 * M�toto para atualizar um administrador.
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
	 * M�toto para remover um administrador.
	 * 
	 * @param administrador
	 */
	public void remove(Administrator administrator) {
		try {
			entityManager.getTransaction().begin();
			administrator = entityManager.find(Administrator.class, administrator.getAdminId()); /*user como parametro, pode gerar um erro, pois este metodo precisa do id do User.*/
			entityManager.remove(administrator);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	/**
	 * M�toto para remover um administrator por id.
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
}
