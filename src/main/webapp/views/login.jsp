<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>

<!-- Bootstrap CSS -->
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
    crossorigin="anonymous">

<!-- Bootstrap Icons (nếu muốn icon đẹp hơn) -->
<link rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
</head>

<body class="bg-light">

    <!-- Wrapper full height để canh giữa màn hình -->
    <div class="d-flex align-items-center justify-content-center" style="min-height: 100vh;">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6 col-lg-5">

                    <!-- Card login -->
                    <div class="card shadow-lg border-0 rounded-4">
                        <div class="card-body p-4 p-md-5">

                            <!-- Tiêu đề -->
                            <div class="text-center mb-4">
                                <h2 class="fw-bold mb-1">Đăng nhập</h2>
                                <p class="text-muted mb-0">Chào mừng bạn quay trở lại 👋</p>
                            </div>

                            <!-- Alert lỗi -->
                            <c:if test="${alert != null}">
                                <div class="alert alert-danger mb-4" role="alert">
                                    ${alert}
                                </div>
                            </c:if>

                            <!-- Form -->
                            <form action="login" method="post" class="vstack gap-3">

                                <!-- Username -->
                                <div>
                                    <label class="form-label fw-semibold">Tài khoản</label>
                                    <div class="input-group">
                                        <span class="input-group-text">
                                            <i class="bi bi-person"></i>
                                        </span>
                                        <input type="text"
                                               name="username"
                                               class="form-control"
                                               placeholder="Nhập tài khoản">
                                    </div>
                                </div>

                                <!-- Password -->
                                <div>
                                    <label class="form-label fw-semibold">Mật khẩu</label>
                                    <div class="input-group">
                                        <span class="input-group-text">
                                            <i class="bi bi-lock"></i>
                                        </span>
                                        <input type="password"
                                               name="password"
                                               class="form-control"
                                               placeholder="Nhập mật khẩu">
                                    </div>
                                </div>
                                
                                <label>
                                <input type="checkbox" checked="checked" name="remember"> Remember me
                                </label>

                                <!-- Nút đăng nhập -->
                                <button type="submit"
                                        class="btn btn-primary w-100 py-2 fw-semibold mt-2">
                                    Đăng nhập
                                </button>

                                <!-- Quên mật khẩu -->
                                <div class="text-center mt-3">
                                    <a href="${pageContext.request.contextPath}/forgot-password"
                                       class="text-decoration-none text-muted small">
                                        Quên mật khẩu?
                                    </a>
                                </div>

                                <!-- Đăng ký -->
                                <div class="text-center">
                                    <p class="mb-0 small text-muted">
                                        Chưa có tài khoản?
                                        <a href="${pageContext.request.contextPath}/register"
                                           class="text-decoration-none fw-semibold">
                                            Đăng ký ngay
                                        </a>
                                    </p>
                                </div>
                            </form>

                        </div>
                    </div>
                    <!-- /Card -->

                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script
        src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.min.js"
        integrity="sha384-G/EV+4j2dNv+tEPo3++6LCgdCROaejBqfUeNjuKAiuXbjrxilcCdDz6ZAVfHWe1Y"
        crossorigin="anonymous"></script>
</body>
</html>
