package vn.phuchau.controller;

import static vn.phuchau.utils.Constant.UPLOAD_DIRECTORY;

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
import vn.phuchau.dao.IUserDao;
import vn.phuchau.dao.impl.UserDao;
import vn.phuchau.entity.User;

@WebServlet("/profile")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class UpdateProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserDao userDAO = new UserDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		if (username == null) {
			resp.sendRedirect("login.jsp");
			return;
		}

		User user = userDAO.get(username);
		req.setAttribute("user", user);
		req.getRequestDispatcher("/profile.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		if (username == null) {
			resp.sendRedirect("login.jsp");
			return;
		}

		User user = userDAO.get(username);

		// Lấy dữ liệu form
		String fullname = req.getParameter("fullname");
		String phone = req.getParameter("phone");

		user.setFullname(fullname);
		user.setPhone(phone);

		String uploadPath = UPLOAD_DIRECTORY;
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists())
			uploadDir.mkdir();

		try {
			Part part = req.getPart("images");
			if (part != null && part.getSize() > 0) {
				String submittedFileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
				int index = submittedFileName.lastIndexOf(".");
				String ext = (index > 0) ? submittedFileName.substring(index + 1) : "png";
				String fname = System.currentTimeMillis() + "." + ext;

				part.write(uploadPath + "/" + fname);

				user.setImages(fname);
			} else {

				if (user.getImages() == null || user.getImages().trim().isEmpty()) {
					user.setImages("avata.png");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		userDAO.update(user);

		session.setAttribute("fullname", fullname);
		if (user.getImages() != null) {
			session.setAttribute("images", user.getImages());
		}

		req.setAttribute("message", "Cập nhật hồ sơ thành công!");
		req.setAttribute("user", user);
		req.getRequestDispatcher("/profile.jsp").forward(req, resp);
	}
}