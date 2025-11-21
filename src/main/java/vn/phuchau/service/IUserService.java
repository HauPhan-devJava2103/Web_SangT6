package vn.phuchau.service;

import vn.phuchau.entity.User;

public interface IUserService {

	User findByUsername(String username);

	void register(User user);

	boolean isEmailExist(String email);

	boolean isUsernameExist(String username);

	boolean changePassword(String email, String newPassword);

	void updateProfile(String username, String fullname, String phone, String imagePath);

	User login(String username, String password);

}
