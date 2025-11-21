package vn.phuchau.service.impl;

import java.util.List;

import vn.phuchau.dao.ICategoryDao;
import vn.phuchau.dao.impl.CategoryDao;
import vn.phuchau.entity.Category;
import vn.phuchau.service.ICategoryService;

public class CategoryService implements ICategoryService {

	private ICategoryDao categoryDao = new CategoryDao();

	@Override
	public void create(Category category) {
		categoryDao.insert(category);
	}

	@Override
	public void update(Category category) {
		categoryDao.update(category);
	}

	@Override
	public void delete(Long id) {
		categoryDao.delete(id);
	}

	@Override
	public Category findById(Long id) {
		return categoryDao.findById(id);
	}

	@Override
	public List<Category> findAll() {
		return categoryDao.findAll();
	}
}
