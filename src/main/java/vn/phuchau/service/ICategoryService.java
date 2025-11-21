package vn.phuchau.service;

import java.util.List;

import vn.phuchau.entity.Category;

public interface ICategoryService {

	void create(Category category);

	void update(Category category);

	void delete(Long id);

	Category findById(Long id);

	List<Category> findAll();
}
