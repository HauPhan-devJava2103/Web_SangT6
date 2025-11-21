package vn.phuchau.service;

import java.util.List;

import vn.phuchau.entity.Video;

public interface IVideoService {

	void create(Video video);

	void update(Video video);

	void delete(Long id);

	Video findById(Long id);

	List<Video> findAll();

	List<Video> searchByTitle(String keyword);
}
