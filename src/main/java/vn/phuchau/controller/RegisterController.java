package vn.phuchau.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.phuchau.entity.User;
import vn.phuchau.service.IUserService;
import vn.phuchau.service.impl.UserService;
import vn.phuchau.utils.Constant;

@WebServlet("/register")

public class RegisterController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	IUserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher rd = req.getRequestDispatcher(Constant.REGISTER);
		rd.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String username = req.getParameter("username");
		String fullName = req.getParameter("fullName");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		String phone = req.getParameter("phone");
		String password = req.getParameter("password");
		String confirmPassword = req.getParameter("confirmPassword");
		String alertMsg = "";

		if (username.isEmpty() || email.isEmpty() || password.isEmpty() || fullName.isEmpty()) {
			alertMsg = "Không được để rỗng";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher(Constant.REGISTER).forward(req, resp);
			return;
		}

		// confirm password
		if (!password.equals(confirmPassword)) {
			alertMsg = "Mật khẩu không khớp";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher(Constant.REGISTER).forward(req, resp);
		}

		// Email
		if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
			alertMsg = "Email không đúng định dạng";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher(Constant.REGISTER).forward(req, resp);
		}

		if (userService.isEmailExist(email)) {
			alertMsg = "Email đã tồn tại trong hệ thống";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher(Constant.REGISTER).forward(req, resp);
		}

		if (userService.isUsernameExist(username)) {
			alertMsg = "Username đã tồn tại trong hệ thống";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher(Constant.REGISTER).forward(req, resp);
		}

		User user = new User();
		user.setUsername(username);
		user.setFullname(fullName);
		user.setEmail(email);
		user.setPhone(phone);
		user.setPassword(password);
		user.setActive(true);
		user.setAdmin(false);
		user.setImages("avata.png");

		try {
			userService.register(user);

			alertMsg = "Đăng ký thành công! Vui lòng đăng nhập.";
			resp.sendRedirect(req.getContextPath() + "/login");
			return;

		} catch (Exception e) {
			alertMsg = "Lỗi đăng ký! Vui lòng thử lại.";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher(Constant.REGISTER).forward(req, resp);
		}

	}

}
