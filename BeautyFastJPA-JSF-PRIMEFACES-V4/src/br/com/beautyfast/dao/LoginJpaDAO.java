package br.com.beautyfast.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.beautyfast.entidade.User;

/**
 * Esta classe segue o padrao de projeto Singleton que garante que apenas uma
 * instancia dessa classe sera criada durante toda a aplicacacao. Classe
 * utilizada para persistir o objeto User no banco de dados MySQL a partir dos
 * mecanismos do JPA-Hibernate
 * 
 * @author Thiago Guimarães
 *
 */
public class LoginJpaDAO {
	private static LoginJpaDAO instance;
	protected EntityManager entityManager;

	public static LoginJpaDAO getInstance() {
		if (instance == null) {
			instance = new LoginJpaDAO();
		}

		return instance;
	}

	public LoginJpaDAO() {
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
	 * Este método faz uma busca no bd a partir do email do usuário.
	 * 
	 * @param email
	 * @return User
	 * @throws Exception
	 */
	public User userLogin(String email) throws Exception {
		User user = null;
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<User> criteria = builder.createQuery(User.class);
			Root<User> u = criteria.from(User.class);
			criteria.where(builder.equal(u.get("userName"), builder.parameter(String.class, "userName")));
			TypedQuery<User> query = entityManager.createQuery(criteria);
			query.setParameter("userName", email);
			user = query.getSingleResult();
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return user;
	}
}