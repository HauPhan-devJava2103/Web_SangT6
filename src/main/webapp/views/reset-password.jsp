<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đặt lại mật khẩu</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-4">
            <div class="card shadow-sm">
                <div class="card-body p-4">
                    <h3 class="text-center mb-4">Đặt lại mật khẩu</h3>

                    <c:if test="${not empty alert}">
                        <div class="alert ${alert.contains('thành công') ? 'alert-success' : 'alert-danger'} alert-dismissible fade show">
                            ${alert}
                            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                        </div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/reset-password" method="post">
                        <div class="mb-3">
                            <label>Mật khẩu mới</label>
                            <input type="password" name="password" class="form-control" required minlength="6">
                        </div>
                        <div class="mb-3">
                            <label>Nhập lại mật khẩu</label>
                            <input type="password" name="confirmPassword" class="form-control" required>
                        </div>
                        <button type="submit" class="btn btn-success w-100">
                            Cập nhật mật khẩu
                        </button>
                    </form>

                    <div class="text-center mt-3">
                        <a href="${pageContext.request.contextPath}/login">Quay lại đăng nhập</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>