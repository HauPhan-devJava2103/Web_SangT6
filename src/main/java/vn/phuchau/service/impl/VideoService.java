package vn.phuchau.service.impl;

import java.util.List;

import vn.phuchau.dao.IVideoDao;
import vn.phuchau.dao.impl.VideoDao;
import vn.phuchau.entity.Video;
import vn.phuchau.service.IVideoService;

public class VideoService implements IVideoService {

	private IVideoDao videoDao = new VideoDao();

	@Override
	public void create(Video video) {
		videoDao.insert(video);
	}

	@Override
	public void update(Video video) {
		videoDao.update(video);
	}

	@Override
	public void delete(Long id) {
		videoDao.delete(id);
	}

	@Override
	public Video findById(Long id) {
		return videoDao.findById(id);
	}

	@Override
	public List<Video> findAll() {
		return videoDao.findAll();
	}

	@Override
	public List<Video> searchByTitle(String keyword) {
		if (keyword == null || keyword.trim().isEmpty()) {
			return videoDao.findAll();
		}
		return videoDao.searchByTitle(keyword.trim());
	}
}
