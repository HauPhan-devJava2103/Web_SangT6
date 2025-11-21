package vn.phuchau.dao;

import java.util.List;

import vn.phuchau.entity.Video;

public interface IVideoDao {

	void insert(Video video);

	void update(Video video);

	void delete(Long id);

	Video findById(Long id);

	List<Video> findAll();

	List<Video> searchByTitle(String keyword);
}
