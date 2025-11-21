package vn.phuchau.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.phuchau.config.JPAConfig;
import vn.phuchau.dao.IVideoDao;
import vn.phuchau.entity.Video;

public class VideoDao implements IVideoDao {

	@Override
	public void insert(Video video) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(video);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
	}

	@Override
	public void update(Video video) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(video);
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
			Video v = em.find(Video.class, id);
			if (v != null)
				em.remove(v);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
	}

	@Override
	public Video findById(Long id) {
		EntityManager em = JPAConfig.getEntityManager();
		return em.find(Video.class, id);
	}

	@Override
	public List<Video> findAll() {
		EntityManager em = JPAConfig.getEntityManager();
		String jpql = "SELECT v FROM Video v";
		return em.createQuery(jpql, Video.class).getResultList();
	}

	@Override
	public List<Video> searchByTitle(String keyword) {
		EntityManager em = JPAConfig.getEntityManager();
		String jpql = "SELECT v FROM Video v WHERE v.title LIKE :kw";
		return em.createQuery(jpql, Video.class).setParameter("kw", "%" + keyword + "%").getResultList();
	}
}
