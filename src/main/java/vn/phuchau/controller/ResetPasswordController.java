package vn.phuchau.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.phuchau.service.IUserService;
import vn.phuchau.service.impl.UserService;

@WebServlet("/reset-password")
public class ResetPasswordController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	IUserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("resetEmail") == null) {
			resp.sendRedirect(req.getContextPath() + "/forgot-password");

			return;
		}
		req.getRequestDispatcher("/views/reset-password.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("resetEmail") == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		String email = (String) session.getAttribute("resetEmail");
		String password = req.getParameter("password");
		String confirmPassword = req.getParameter("confirmPassword");
		String alertMsg = "";

		if (!password.equals(confirmPassword)) {
			alertMsg = "Password do not match!";
		} else if (password.length() < 8) {
			alertMsg = "Password at least 8 characters!";
		} else {
			boolean success = userService.changePassword(email, password);
			if (success) {
				alertMsg = "Resert Password Success!";
				session.removeAttribute("resetEmail");
				req.setAttribute("alert", alertMsg);
				req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
				return;
			} else {
				alertMsg = "System is Failed!";
			}
		}
		req.setAttribute("alert", alertMsg);
		req.getRequestDispatcher("/views/reset-password.jsp").forward(req, resp);

	}

}
