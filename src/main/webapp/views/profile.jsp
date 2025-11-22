<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row" style="margin-top: 40px; margin-bottom: 60px;">
    <div class="col-md-6 col-md-offset-3">

        <h3 style="margin-bottom: 25px; font-weight: 600;">Hồ sơ cá nhân</h3>

        <c:if test="${not empty message}">
            <div class="alert alert-success">${message}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/profile"
              method="post"
              enctype="multipart/form-data">

            <!-- Lưu tên ảnh cũ để sử dụng lại nếu không chọn ảnh mới -->
            <input type="hidden" name="oldImage" value="${user.images}" />

            <!-- Avatar -->
            <div class="form-group">
                <label>Ảnh đại diện</label>
                <div class="row">
                    <div class="col-sm-3">

                        <c:choose>
                            <c:when test="${not empty user.images}">
                                <img src="${pageContext.request.contextPath}/image?fname=${user.images}"
                                     class="img-responsive img-thumbnail"
                                     alt="Avatar" style="width:120px; height:120px;">
                            </c:when>
                            <c:otherwise>
                                <img src="${pageContext.request.contextPath}/image?fname=avata.png"
                                     class="img-responsive img-thumbnail"
                                     alt="Avatar" style="width:120px; height:120px;">
                            </c:otherwise>
                        </c:choose>

                    </div>

                    <div class="col-sm-9" style="margin-top: 10px;">
                        <!-- tên phải trùng req.getPart("imageFile") -->
                        <input type="file" name="imageFile" class="form-control">
                        <p class="help-block">Chọn ảnh mới nếu muốn thay đổi.</p>
                    </div>
                </div>
            </div>

            <!-- Username (readonly) -->
            <div class="form-group">
                <label>Tài khoản</label>
                <input type="text" class="form-control"
                       value="${user.username}" readonly="readonly">
            </div>

            <!-- Fullname -->
            <div class="form-group">
                <label>Họ và tên</label>
                <input type="text" name="fullname"
                       class="form-control"
                       value="${user.fullname}">
            </div>

            <!-- Phone -->
            <div class="form-group">
                <label>Số điện thoại</label>
                <input type="text" name="phone"
                       class="form-control"
                       value="${user.phone}">
            </div>

            <button type="submit"
                    class="btn btn-primary"
                    style="background:#e84d1c;border-color:#e84d1c;">
                Lưu thay đổi
            </button>

            <a href="${pageContext.request.contextPath}/"
               class="btn btn-default">
                Quay lại trang chủ
            </a>
        </form>

    </div>
</div>
