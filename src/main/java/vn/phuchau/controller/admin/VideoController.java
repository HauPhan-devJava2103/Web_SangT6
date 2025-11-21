package vn.phuchau.controller.admin;

import static vn.phuchau.utils.Constant.UPLOAD_DIRECTORY;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.phuchau.entity.Category;
import vn.phuchau.entity.Video;
import vn.phuchau.service.ICategoryService;
import vn.phuchau.service.IVideoService;
import vn.phuchau.service.impl.CategoryService;
import vn.phuchau.service.impl.VideoService;

@WebServlet("/admin/video")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 20, maxRequestSize = 1024 * 1024 * 50)
public class VideoController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final IVideoService videoService = new VideoService();
	private final ICategoryService categoryService = new CategoryService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		if (action == null)
			action = "list";

		if ("create".equals(action) || "edit".equals(action)) {

			List<Category> categories = categoryService.findAll();
			request.setAttribute("categories", categories);

			if ("edit".equals(action)) {
				String idStr = request.getParameter("id");
				if (idStr == null || idStr.isEmpty()) {
					response.sendRedirect("video?action=list");
					return;
				}
				Video video = videoService.findById(Long.parseLong(idStr));
				if (video == null) {
					response.sendRedirect("video?action=list");
					return;
				}
				request.setAttribute("video", video);
			} else {
				request.setAttribute("video", new Video());
			}

			request.getRequestDispatcher("/WEB-INF/views/admin/video-form.jsp").forward(request, response);

		} else if ("delete".equals(action)) {
			String idStr = request.getParameter("id");
			if (idStr != null && !idStr.isEmpty()) {
				try {
					videoService.delete(Long.parseLong(idStr));
				} catch (Exception ignored) {
				}
			}
			response.sendRedirect("video?action=list");

		} else {
			List<Video> list = videoService.findAll();
			request.setAttribute("videos", list);
			request.getRequestDispatcher("/WEB-INF/views/admin/video-list.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		File uploadDir = new File(UPLOAD_DIRECTORY);
		if (!uploadDir.exists())
			uploadDir.mkdirs();

		String action = request.getParameter("action");
		boolean isCreate = "create".equals(action);

		Video video;
		if (isCreate) {
			video = new Video();
		} else {
			String idStr = request.getParameter("videoId");
			if (idStr == null || idStr.isEmpty()) {
				response.sendRedirect("video?action=list");
				return;
			}
			video = videoService.findById(Long.parseLong(idStr));
			if (video == null) {
				response.sendRedirect("video?action=list");
				return;
			}
		}

		video.setTitle(request.getParameter("title"));
		video.setDescription(request.getParameter("description"));

		String viewsStr = request.getParameter("views");
		long views = 0;
		if (viewsStr != null && !viewsStr.trim().isEmpty()) {
			try {
				views = Long.parseLong(viewsStr.trim());
			} catch (NumberFormatException ignored) {
			}
		}
		video.setViews(views);

		video.setActive("on".equals(request.getParameter("active")));

		String categoryIdStr = request.getParameter("categoryId");
		if (categoryIdStr != null && !categoryIdStr.isEmpty()) {
			Category category = categoryService.findById(Long.parseLong(categoryIdStr));
			video.setCategory(category);
		}

		try {
			Part part = request.getPart("posterFile");
			if (part != null && part.getSize() > 0) {
				String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
				int dot = fileName.lastIndexOf('.');
				String ext = (dot > 0) ? fileName.substring(dot + 1).toLowerCase() : "jpg";
				if (!ext.matches("jpg|jpeg|png|gif|webp"))
					ext = "jpg";

				String newFileName = System.currentTimeMillis() + "." + ext;
				part.write(UPLOAD_DIRECTORY + File.separator + newFileName);

				video.setPoster(newFileName);
			} else if (isCreate) {
				video.setPoster("default-video.jpg");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (isCreate) {
			videoService.create(video);
		} else {
			videoService.update(video);
		}

		response.sendRedirect("video?action=list");
	}
}