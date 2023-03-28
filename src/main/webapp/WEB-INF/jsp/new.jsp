
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ChangeAddress.css"/>
    <title>Change Address</title>
</head>
<body>


   <form class="form" id="form" action="/addresses">
    <div class="item two" id="name">
        <label for="" class="">Name</label>
        <div>

            <input type="text" name = "fullnameRecive" class="form-control"  placeholder="Họ và Tên">
        </div>
    </div>
    <div class="item two">
        <label for="">Điện thoại di động</label>
        <input class="phone" id="phone" type="text" name="phone" placeholder="Nhập vào số điện thoại" >
    </div>
    <div class="item two">
        <label for="">Tỉnh/Thành phố</label>
        <select class="province" name="" id="province" style="width: 200px;">
            <c:forEach items="${provinces}" var="pro">
                <option data-id="${pro.id}" value="${pro.name}" >${pro.name}</option>
            </c:forEach>
        </select>
    </div>

    <div class="item two">
        <label for="">Quận/Huyện</label>
        <select class="district" name="" id="district" style="width: 200px;">
            <option>${sessionScope.address.districtName}</option>

        </select>
    </div>

    <div class="item two">
        <label for="">Phường/Xã</label>
        <select class="ghost" name="" id="ward" style="width: 200px;">
            <option>${sessionScope.address.wardName}</option>

        </select>
    </div>

    <div class="item two">
        <label for="">Địa chỉ cụ thể</label>
        <input type="hidden" name="userId" value="${sessionScope.jwtResponse.id}">
        <textarea class="item-addressDetails" name="detailAddress" id="detailAddress" cols="30" rows="3"></textarea>
    </div>
    <div class="radio">
        <label for="type">Loại địa chỉ</label>
        <input type="radio" name="typeAddress" id="type" value="1">
        <label for="type">Nhà riêng/chung cư</label>
        <input type="radio" name="typeAddress" value="2">
        <label for="type">Cơ quan/ công ty</label>
    </div>
    <div class="bottom">
        <input type="checkbox" name="" id="">
        <label for="">Sử dụng địa chỉ này làm mặc định</label>
        <input type="checkbox" name="defaultAddress" value="1">
    </div>
    <div class="btn">
        <a class="cancel-address" href="">Huỷ bỏ</a>
        <a class="update-address" href="">Cập nhật</a>
    </div>
</form>form>


</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/LoadAddress.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ChangeAddress.js"></script>

</body>
</html>