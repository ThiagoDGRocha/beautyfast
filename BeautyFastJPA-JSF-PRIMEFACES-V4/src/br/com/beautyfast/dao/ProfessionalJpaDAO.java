package br.com.beautyfast.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.beautyfast.entidade.Professional;

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
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("BeautyFastV4");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}

	/**
	 * Este método obtem um profissional por id.
	 * 
	 * @param idProfissional
	 * @return Professional
	 */
	public Professional getById(final int idProfissional) {
		return entityManager.find(Professional.class, idProfissional);
	}
	
	/**
	 * Este metodo lista todos os profissionais.
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
	 * Este método persisti um professional.
	 * 
	 * @param professional
	 * @throws Exception 
	 */
	public void persist(Professional professional) throws Exception {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(professional);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			entityManager.getTransaction().rollback();
			throw new Exception(ex.getMessage());
		}
	}

	/**
	 * Este metodo atualiza um professional.
	 * 
	 * @param professional
	 * @throws Exception 
	 */
	public void merge(Professional professional) throws Exception {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(professional);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			entityManager.getTransaction().rollback();
			throw new Exception(ex.getMessage());
		}
	}

	/**
	 * Este metodo remove um professional.
	 * 
	 * @param professional
	 * @throws Exception 
	 */
	public void remove(Professional professional) throws Exception {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(professional);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			entityManager.getTransaction().rollback();
			throw new Exception(ex.getMessage());
		}
	}
	
	/**
	 * Este método remove um professional por id.
	 * 
	 * @param idProfessional
	 * @throws Exception 
	 */
	public void removeById(final int idProfessional) throws Exception {
		try {
			Professional professional = getById(idProfessional);
			remove(professional);
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}

	/**
	 * 
	 * Este método busca um profissional por parte do nome baseada na API Criteria do JPA.
	 * 
	 * @return List<Specialty>
	 * @throws Exception
	 */
	public List<Professional> findByTxt(final String profName) throws Exception {
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
			throw new Exception(ex.getMessage());
		}
		return listProf;
	}
}
