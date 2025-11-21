package vn.phuchau.dao;

import java.util.List;

import vn.phuchau.entity.Category;

public interface ICategoryDao {
	void insert(Category category);

	void update(Category category);

	void delete(Long id);

	Category findById(Long id);

	List<Category> findAll();
}
