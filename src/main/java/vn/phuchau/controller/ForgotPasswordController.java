package vn.phuchau.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.phuchau.service.IUserService;
import vn.phuchau.service.impl.UserService;

@WebServlet("/forgot-password")
public class ForgotPasswordController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	IUserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/views/forgot-password.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email");
		String alertMsg = "";

		if (email.isEmpty()) {
			alertMsg = "Email is not empty!";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
			return;
		}
		if (!userService.isEmailExist(email)) {
			alertMsg = "Email is not exist in DB!";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
		} else {
			HttpSession session = req.getSession(true);
			session.setAttribute("resetEmail", email);
			resp.sendRedirect(req.getContextPath() + "/reset-password");
		}

	}

}
