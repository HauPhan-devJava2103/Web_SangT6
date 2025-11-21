package vn.phuchau.dao;

import vn.phuchau.entity.User;

public interface IUserDao {

	User get(String username);

	void insert(User user);

	void update(User user);

	boolean checkExistEmail(String email);

	boolean checkExistUsername(String username);

	boolean updatePassword(String email, String password);

	void updateProfile(String username, String fullname, String phone, String imagePath);
}
