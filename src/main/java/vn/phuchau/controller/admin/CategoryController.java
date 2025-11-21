package vn.phuchau.controller.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.phuchau.entity.Category;
import vn.phuchau.service.ICategoryService;
import vn.phuchau.service.impl.CategoryService;

@WebServlet("/admin/category")
public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final ICategoryService categoryService = new CategoryService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		if (action == null)
			action = "list";

		if ("create".equals(action)) {
			request.getRequestDispatcher("/WEB-INF/views/admin/category-form.jsp").forward(request, response);

		} else if ("edit".equals(action)) {
			String idStr = request.getParameter("id");
			if (idStr == null || idStr.isEmpty()) {
				response.sendRedirect("category?action=list");
				return;
			}
			Category c = categoryService.findById(Long.parseLong(idStr));
			if (c == null) {
				response.sendRedirect("category?action=list");
				return;
			}
			request.setAttribute("category", c);
			request.getRequestDispatcher("/WEB-INF/views/admin/category-form.jsp").forward(request, response);

		} else if ("delete".equals(action)) {
			String idStr = request.getParameter("id");
			if (idStr != null && !idStr.isEmpty()) {
				try {
					categoryService.delete(Long.parseLong(idStr));
				} catch (Exception ignored) {
				}
			}
			response.sendRedirect("category?action=list");

		} else {
			List<Category> list = categoryService.findAll();
			request.setAttribute("categories", list);
			request.getRequestDispatcher("/WEB-INF/views/admin/category-list.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		Category c;
		boolean isCreate = "create".equals(action);

		if (isCreate) {
			c = new Category();
		} else {
			String idStr = request.getParameter("categoryId");
			if (idStr == null || idStr.isEmpty()) {
				response.sendRedirect("category?action=list");
				return;
			}
			c = categoryService.findById(Long.parseLong(idStr));
			if (c == null) {
				response.sendRedirect("category?action=list");
				return;
			}
		}

		c.setCategoryName(request.getParameter("categoryName"));
		c.setCategoryCode(request.getParameter("categoryCode"));
		c.setImages(request.getParameter("images"));

		String statusStr = request.getParameter("status");
		c.setStatus("on".equals(statusStr) || "true".equalsIgnoreCase(statusStr));

		if (isCreate) {
			categoryService.create(c);
		} else {
			categoryService.update(c);
		}

		response.sendRedirect("category?action=list");
	}
}