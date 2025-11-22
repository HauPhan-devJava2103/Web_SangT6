package vn.phuchau.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import vn.phuchau.entity.User;
import vn.phuchau.service.IUserService;
import vn.phuchau.service.impl.UserService;
import vn.phuchau.utils.Constant;

@WebServlet("/profile")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class UpdateProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		User sessionUser = (User) session.getAttribute("account");

		if (sessionUser == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		String username = sessionUser.getUsername();

		if (username == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		User user = userService.findByUsername(username);
		req.setAttribute("user", user);
		req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		User sessionUser = (User) session.getAttribute("account");

		if (sessionUser == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		String username = sessionUser.getUsername();
		User user = userService.findByUsername(username);

		// Lấy dữ liệu form
		String fullname = req.getParameter("fullname");
		String phone = req.getParameter("phone");

		user.setFullname(fullname);
		user.setPhone(phone);

		String oldImage = req.getParameter("oldImage");
		Part filePart = req.getPart("imageFile");
		String fileName = oldImage;

		if (filePart != null && filePart.getSize() > 0) {

			fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

			File uploadDir = new File(Constant.UPLOAD_DIRECTORY);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}

			filePart.write(Constant.UPLOAD_DIRECTORY + File.separator + fileName);
		}

		user.setImages(fileName);
		userService.updateProfile(username, fullname, phone, fileName);
		sessionUser.setFullname(fullname);
		sessionUser.setImages(fileName);
		session.setAttribute("account", sessionUser);

		session.setAttribute("fullname", fullname);
		session.setAttribute("images", fileName);

		req.setAttribute("message", "Cập nhật hồ sơ thành công!");
		req.setAttribute("user", user);
		req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
	}
}