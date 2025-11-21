package vn.phuchau.service.impl;

import vn.phuchau.dao.IUserDao;
import vn.phuchau.dao.impl.UserDao;
import vn.phuchau.entity.User;
import vn.phuchau.service.IUserService;

public class UserService implements IUserService {

	private IUserDao userDao = new UserDao();

	@Override
	public User findByUsername(String username) {
		return userDao.get(username);
	}

	@Override
	public void register(User user) {
		if (userDao.checkExistUsername(user.getUsername())) {
			throw new RuntimeException("Username đã tồn tại");
		}
		if (userDao.checkExistEmail(user.getEmail())) {
			throw new RuntimeException("Email đã tồn tại");
		}
		userDao.insert(user);
	}

	@Override
	public boolean isEmailExist(String email) {
		return userDao.checkExistEmail(email);
	}

	@Override
	public boolean isUsernameExist(String username) {
		return userDao.checkExistUsername(username);
	}

	@Override
	public boolean changePassword(String email, String newPassword) {
		return userDao.updatePassword(email, newPassword);
	}

	@Override
	public void updateProfile(String username, String fullname, String phone, String imagePath) {
		userDao.updateProfile(username, fullname, phone, imagePath);
	}

	@Override
	public User login(String username, String password) {
		User user = userDao.get(username);
		if (user == null)
			return null;

		if (user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}
}
