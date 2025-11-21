package vn.phuchau.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import vn.phuchau.config.JPAConfig;
import vn.phuchau.dao.IUserDao;
import vn.phuchau.entity.User;

public class UserDao implements IUserDao {

	@Override
	public User get(String username) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			String jpql = "SELECT u FROM User u WHERE u.username = :username";
			return em.createQuery(jpql, User.class).setParameter("username", username).getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}

	@Override
	public void insert(User user) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			em.persist(user);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	@Override
	public void update(User user) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			em.merge(user);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	@Override
	public boolean checkExistEmail(String email) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			String jpql = "SELECT COUNT(u) FROM User u WHERE u.email = :email";
			Long count = em.createQuery(jpql, Long.class).setParameter("email", email).getSingleResult();
			return count > 0;
		} finally {
			em.close();
		}
	}

	@Override
	public boolean checkExistUsername(String username) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			String jpql = "SELECT COUNT(u) FROM User u WHERE u.username = :username";
			Long count = em.createQuery(jpql, Long.class).setParameter("username", username).getSingleResult();
			return count > 0;
		} finally {
			em.close();
		}
	}

	@Override
	public boolean updatePassword(String email, String password) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			String jpql = "UPDATE User u SET u.password = :password WHERE u.email = :email";

			int updated = em.createQuery(jpql).setParameter("password", password).setParameter("email", email)
					.executeUpdate();
			trans.commit();
			return updated > 0;
		} catch (Exception e) {
			if (trans.isActive())
				trans.rollback();
			return false;
		} finally {
			em.close();
		}
	}

	@Override
	public void updateProfile(String username, String fullname, String phone, String imagePath) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			User user = em.find(User.class, username);
			if (user == null)
				return;

			user.setFullname(fullname);
			user.setPhone(phone);

			if (imagePath != null && !imagePath.isEmpty()) {
				user.setImages(imagePath);
			}

			em.merge(user);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}
}
