package vn.phuchau.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.phuchau.config.JPAConfig;
import vn.phuchau.dao.ICategoryDao;
import vn.phuchau.entity.Category;

public class CategoryDao implements ICategoryDao {
	@Override
	public void insert(Category category) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(category);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
	}

	@Override
	public void update(Category category) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.merge(category);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
	}

	@Override
	public void delete(Long id) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			Category c = em.find(Category.class, id);
			if (c != null)
				em.remove(c);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
	}

	@Override
	public Category findById(Long id) {
		EntityManager em = JPAConfig.getEntityManager();
		return em.find(Category.class, id);
	}

	@Override
	public List<Category> findAll() {
		EntityManager em = JPAConfig.getEntityManager();
		String jpql = "SELECT c FROM Category c";
		return em.createQuery(jpql, Category.class).getResultList();
	}
}
