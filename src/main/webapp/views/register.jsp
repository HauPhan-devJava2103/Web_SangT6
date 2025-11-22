<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký</title>

<link rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"/>

</head>
<body class="bg-light">

<div class="container" style="margin-top: 60px; margin-bottom: 60px;">
    <div class="row justify-content-center">
        <div class="col-md-6">

            <div class="card shadow-lg border-0 rounded-4">
                <div class="card-body p-4">

                    <h3 class="text-center mb-4 fw-bold">Đăng ký tài khoản</h3>

                    <!-- Hiển thị lỗi -->
                    <c:if test="${not empty alert}">
                        <div class="alert alert-danger">${alert}</div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/register" method="post">

                        <!-- Username -->
                        <div class="mb-3">
                            <label class="form-label">Tài khoản *</label>
                            <input type="text"
                                   name="username"
                                   class="form-control"
                                   value="${username}">
                        </div>

                        <!-- Full Name -->
                        <div class="mb-3">
                            <label class="form-label">Họ và tên *</label>
                            <input type="text"
                                   name="fullName"
                                   class="form-control"
                                   value="${fullName}">
                        </div>

                        <!-- Email -->
                        <div class="mb-3">
                            <label class="form-label">Email *</label>
                            <input type="text"
                                   name="email"
                                   class="form-control"
                                   value="${email}">
                        </div>

                        <!-- Phone -->
                        <div class="mb-3">
                            <label class="form-label">Số điện thoại</label>
                            <input type="text"
                                   name="phone"
                                   class="form-control"
                                   value="${phone}">
                        </div>

                        <!-- Password -->
                        <div class="mb-3">
                            <label class="form-label">Mật khẩu *</label>
                            <input type="password"
                                   name="password"
                                   class="form-control">
                        </div>

                        <!-- Confirm password -->
                        <div class="mb-4">
                            <label class="form-label">Xác nhận mật khẩu *</label>
                            <input type="password"
                                   name="confirmPassword"
                                   class="form-control">
                        </div>

                        <button type="submit"
                                class="btn btn-primary w-100"
                                style="background:#e84d1c;border-color:#e84d1c;">
                            Đăng ký
                        </button>

                        <div class="text-center mt-3">
                            <a href="${pageContext.request.contextPath}/login"
                               class="text-decoration-none">
                                Đã có tài khoản? Đăng nhập
                            </a>
                        </div>

                    </form>
                </div>
            </div>

        </div>
    </div>
</div>

</body>
</html>
