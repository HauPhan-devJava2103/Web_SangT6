<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row" style="margin-top: 40px; margin-bottom: 60px;">
	<div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">

		<div class="panel panel-default" style="border-radius: 6px;">
			<div class="panel-body">

				<div class="text-center" style="margin-bottom: 20px;">
					<h3 style="margin-top: 0; font-weight: 600;">ĐĂNG NHẬP</h3>
					<p class="text-muted">Chào mừng bạn quay trở lại 👋</p>
				</div>

				<!-- Alert lỗi -->
				<c:if test="${alert != null}">
					<div class="alert alert-danger" role="alert">${alert}</div>
				</c:if>

				<form action="${pageContext.request.contextPath}/login"
					method="post">

					<!-- Username -->
					<div class="form-group">
						<label>Tài khoản</label> <input type="text" name="username"
							class="form-control" placeholder="Nhập tài khoản">
					</div>

					<!-- Password -->
					<div class="form-group">
						<label>Mật khẩu</label> <input type="password" name="password"
							class="form-control" placeholder="Nhập mật khẩu">
					</div>

					<div class="form-group" style="margin-top: 10px;">
						<label class="control-label"> <input type="checkbox"
							name="remember" style="margin-right: 5px;"> Remember me
						</label>
					</div>

					<button type="submit" class="btn btn-primary btn-block"
						style="background: #e84d1c; border-color: #e84d1c;">ĐĂNGNHẬP</button>

					<div class="text-center" style="margin-top: 15px;">
						<a href="${pageContext.request.contextPath}/forgot-password"
							class="text-muted" style="font-size: 12px;"> Quên mật khẩu? </a>
					</div>

					<div class="text-center" style="font-size: 12px; margin-top: 5px;">
						Chưa có tài khoản? <a
							href="${pageContext.request.contextPath}/register"
							style="color: #e84d1c; font-weight: 600;"> Đăng ký ngay </a>
					</div>

				</form>

			</div>
		</div>

	</div>
</div>
