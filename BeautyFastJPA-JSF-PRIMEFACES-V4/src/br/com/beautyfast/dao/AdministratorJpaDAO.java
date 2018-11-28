package br.com.beautyfast.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.beautyfast.entidade.Administrator;

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
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("BeautyFastV4");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}
		return entityManager;
	}

	/**
	 * Este método obtem um administrador por id.
	 * 
	 * @param idAdmin
	 * @return Administrator
	 */
	public Administrator getById(final int idAdmin) {
		return entityManager.find(Administrator.class, idAdmin);
	}

	/**
	 * Este método lista todos os administradores baseado na API Criteria do JPA.
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
	 * Este método persisti um administrador.
	 * 
	 * @param administrador
	 * @throws Exception
	 */
	public void persist(Administrator administrator) throws Exception {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(administrator);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			entityManager.getTransaction().rollback();
			throw new Exception(ex.getMessage());
		}
	}

	/**
	 * Este método atualiza um administrador.
	 * 
	 * @param administrador
	 * @throws Exception
	 */
	public void merge(Administrator administrator) throws Exception {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(administrator);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			entityManager.getTransaction().rollback();
			throw new Exception(ex.getMessage());
		}
	}

	/**
	 * Este método remove um administrador por id.
	 * 
	 * @param administrator
	 * @throws Exception
	 */
	public void removeById(final int idAdmin) throws Exception {
		try {
			Administrator administrator = getById(idAdmin);
			remove(administrator);
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}

	/**
	 * Este método remove um administrador.
	 * 
	 * @param administrador
	 * @throws Exception
	 */
	public void remove(Administrator administrator) throws Exception {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(administrator);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			entityManager.getTransaction().rollback();
			throw new Exception(ex.getMessage());
		}
	}

	/**
	 * 
	 * Este método busca um administrador por parte do nome baseada na API Criteria
	 * do JPA.
	 * 
	 * @return List<Specialty>
	 * @throws Exception
	 */
	public List<Administrator> findByTxt(final String adminName) throws Exception {
		List<Administrator> listAdministrator = null;
		try {// qual está funcionando? findByTxt do cliente ou do administrador?
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Administrator> criteria = builder.createQuery(Administrator.class);
			Root<Administrator> from = criteria.from(Administrator.class);
			criteria.where(builder.equal(from.get("adminName"), builder.parameter(String.class, "adminName")));
			TypedQuery<Administrator> query = entityManager.createQuery(criteria);
			query.setParameter("adminName", "%" + adminName + "%");
			listAdministrator = query.getResultList();
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return listAdministrator;
	}
}