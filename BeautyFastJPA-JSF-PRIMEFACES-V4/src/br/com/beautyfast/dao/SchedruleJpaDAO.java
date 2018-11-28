/**
 * 
 */
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

import br.com.beautyfast.entidade.Schedrule;

/**
 * Esta classe segue o padrao de projeto Singleton que garante que apenas uma
 * instancia dessa classe sera criada durante toda a aplicacacao. Classe
 * utilizada para persistir o objeto User no banco de dados MySQL a partir dos
 * mecanismos do JPA-Hibernate
 * 
 * @author Thiago Guimarães
 *
 */
public class SchedruleJpaDAO {
	private static SchedruleJpaDAO instance;
	protected EntityManager entityManager;

	public static SchedruleJpaDAO getInstance() {
		if (instance == null) {
			instance = new SchedruleJpaDAO();
		}
		return instance;
	}

	private SchedruleJpaDAO() {
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
	 * Este método obtem uma agenda por id.
	 * 
	 * @param idSchedrule
	 * @return Schedrule
	 */
	public Schedrule getById(final int idSchedrule) {
		return entityManager.find(Schedrule.class, idSchedrule);
	}

	/**
	 * 
	 * Este método lista todas as agendas.
	 * 
	 * @return List<Schedrule>
	 */
	public List<Schedrule> findAll() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Schedrule> criteria = builder.createQuery(Schedrule.class);
		criteria.from(Schedrule.class);
		return entityManager.createQuery(criteria).getResultList();
	}

	/**
	 * Este método persisti uma agenda.
	 * 
	 * @param schedrules
	 * @throws Exception
	 */
	public void persist(Schedrule schedrules) throws Exception {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(schedrules);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			entityManager.getTransaction().rollback();
			throw new Exception(ex.getMessage());
		}
	}

	/**
	 * Este método atualiza uma agenda.
	 * 
	 * @param schedrules
	 * @throws Exception
	 */
	public void merge(Schedrule schedrules) throws Exception {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(schedrules);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			entityManager.getTransaction().rollback();
			throw new Exception(ex.getMessage());
		}
	}

	/**
	 * Este método remove uma agenda por id.
	 * 
	 * @param idSchedrule
	 * @throws Exception
	 */
	public void removeById(int idSchedrule) throws Exception {
		try {
			Schedrule schedrules = getById(idSchedrule);
			remove(schedrules);
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}

	/**
	 * Este método remove uma agenda.
	 * 
	 * @param schedrules
	 * @throws Exception
	 */
	public void remove(Schedrule schedrules) throws Exception {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(schedrules);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			entityManager.getTransaction().rollback();
			throw new Exception(ex.getMessage());
		}
	}

	/**
	 * 
	 * Este método busca uma agenda por parte do nome baseada na API Criteria do JPA.
	 * 
	 * @return List<Schedrule>
	 * @throws Exception
	 */
	public List<Schedrule> findByTxt(final String txt) throws Exception {
		List<Schedrule> listSchedrule = null;
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Schedrule> criteria = builder.createQuery(Schedrule.class);
			Root<Schedrule> from = criteria.from(Schedrule.class);
			Predicate predicate = builder.and();
			predicate = builder.and(predicate, builder.like(from.<String>get("nameSchedrule"), "%" + txt + "%"));
			TypedQuery<Schedrule> query = entityManager.createQuery(
					criteria.select(from).where(predicate).orderBy(builder.asc(from.get("nameSchedrule"))));
			listSchedrule = query.getResultList();
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return listSchedrule;
	}
}