package modeloDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import modeloEntidade.User;

public class UserJpaDAO {
	private static UserJpaDAO instance;
	protected EntityManager entityManager;

	public static UserJpaDAO getInstance() {
		if (instance == null) {
			instance = new UserJpaDAO();
		}

		return instance;
	}

	public UserJpaDAO() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("versaoTresJPA-Hibernate-Web");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}

	/**
	 * Este metodo utiliza a API Criteria do JPA em conjuto ao TypedQuery.
	 * 
	 * @param userName
	 * @return
	 */
	public User setLogin(final String userName) {
		User user = null;
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<User> criteria = builder.createQuery(User.class);
			Root<User> u = criteria.from(User.class);
			criteria.where(builder.equal(u.get("userName"), builder.parameter(String.class, "userName")));
			TypedQuery<User> query = entityManager.createQuery(criteria);
			query.setParameter("userName", userName);
			user = query.getSingleResult();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return user;
	}
}
