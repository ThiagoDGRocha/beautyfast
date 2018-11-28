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

import br.com.beautyfast.entidade.Specialty;

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

	private SpecialtyJpaDAO() {
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
	 * Este método obtem uma especialidade por id.
	 * 
	 * @param idSpecialty
	 * @return Administrator
	 */
	public Specialty getById(final int idSpecialty) {
		return entityManager.find(Specialty.class, idSpecialty);
	}

	/**
	 * 
	 * Este método lista todas as especialidades.
	 * 
	 * @return List<Specialty>
	 */
	public List<Specialty> findAll() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Specialty> criteria = builder.createQuery(Specialty.class);
		criteria.from(Specialty.class);
		return entityManager.createQuery(criteria).getResultList();
	}

	/**
	 * Este método persisti uma especialidade.
	 * 
	 * @param specialty
	 * @throws Exception
	 */
	public void persist(Specialty specialty) throws Exception {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(specialty);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			entityManager.getTransaction().rollback();
			throw new Exception(ex.getMessage());
		}
	}

	/**
	 * Este método atualiza uma especialidade.
	 * 
	 * @param specialty
	 * @throws Exception
	 */
	public void merge(Specialty specialty) throws Exception {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(specialty);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			entityManager.getTransaction().rollback();
			throw new Exception(ex.getMessage());
		}
	}

	/**
	 * Este método remove uma especialidade por id.
	 * 
	 * @param idSpecialty
	 * @throws Exception
	 */
	public void removeById(int idSpecialty) throws Exception {
		try {
			Specialty specialty = getById(idSpecialty);
			remove(specialty);
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}

	/**
	 * Este método remove uma especialidade.
	 * 
	 * @param specialty
	 * @throws Exception
	 */
	public void remove(Specialty specialty) throws Exception {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(specialty);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			entityManager.getTransaction().rollback();
			throw new Exception(ex.getMessage());
		}
	}

	/**
	 * 
	 * Este método busca uma especialidade por parte do nome baseada na API Criteria do JPA.
	 * 
	 * @return List<Specialty>
	 * @throws Exception
	 */
	public List<Specialty> findByTxt(final String txt) throws Exception {
		List<Specialty> listSpecialty = null;
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Specialty> criteria = builder.createQuery(Specialty.class);
			Root<Specialty> from = criteria.from(Specialty.class);
			Predicate predicate = builder.and();
			predicate = builder.and(predicate, builder.like(from.<String>get("nameSpecialty"), "%" + txt + "%"));
			TypedQuery<Specialty> query = entityManager.createQuery(
					criteria.select(from).where(predicate).orderBy(builder.asc(from.get("nameSpecialty"))));
			listSpecialty = query.getResultList();
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return listSpecialty;
	}
}