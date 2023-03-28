<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Order</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myOrder.css"/>

</head>
<body>
	<div class="header"></div>
	<div class="main">
		<div class="main-left">
			<ul>
				<li><img src="" alt=""><a href="/">Home
				</a></li>
				<li><img src="icons2/resume.png" alt=""><a href="/users">Tài
						khoản của tôi</a></li>
				<li><img src="icons2/package.png" alt=""><a style="color: #ee4d2d;" href="/orders/view">Đơn
						mua</a></li>
				<li><img src="icons2/bell.png" alt=""><a href="/discount">Thông
						báo</a></li>
				<li><img src="icons2/log-out.png" alt=""><a href="/acccount/sign-out">Đăng
						xuất</a></li>
			</ul>
		</div>
		<div class="main-right">
			<ul>
				<li><a href="">Tất cả</a></li>
				<li><a href="">Chờ xác nhận</a></li>
				<li><a href="">Chờ lấy hàng</a></li>
				<li><a href="">Đang giao<span id="count" style="color: #ee4d2d">(${count})</span></a></li>
				<li><a href="">Đã giao</a></li>
				<li><a href="">Đã huỷ</a></li>
			</ul>
			<div class="search">
				<em class="fas fa-search"></em> <input type="text" name="search"
					placeholder="tìm kiếm theo tên shop ID hoặc tên sản phẩm...">
			</div>
			<div class="product" id="product">
				<c:forEach items="${orders}" var="item">
						<div class="product-item">
							<img src="${item.urlImage}" alt="">
							<div class="product-detail">
								<p>${item.productName}</p>
								<span>x${item.quantity}</span>
							</div>
							<p  class="price">${item.quantity * item.newPriceProduct}</p>
						</div>
					<div class="pay">
<%--						// 0 là hủy--%>
<%--						// 1 là chờ xác nhận--%>
<%--						// 2 là chờ lấy hàng--%>
<%--						// 3 là đang giao--%>
<%--						// 4 là đẫ giao--%>
						<div class="pay-function">
							<c:if test="${item.status == 1}">
								<a href="">Chờ</a>
								<a href="">Liên hệ người bán</a>
								<a href="">Hủy đơn hàng</a>
							</c:if>
							<c:if test="${item.status == 4}">
								<a style="background-color: #ee4d2d !important; opacity: 1"
									href="/reviews?orderId=${item.orderId}&productId=${item.productId}">Đánh giá</a>
								<a href="">Liên hệ người bán</a>
								<a href="">Mua lại</a>
							</c:if>
							<c:if test="${item.status == 5}">
								<a style="background-color: #ee4d2d !important; opacity: 1"
								   href="/reviews">Mua lại</a>
								<a href="">Liên hệ người bán</a>
								<a href="">Xem đánh giá</a>
							</c:if>
							<c:if test="${item.status == 0}">
								<a style="background-color: #ee4d2d !important; opacity: 1"
								   href="">Mua lại</a>
								<a href="">Đã hủy</a>
								<a href="">Chi tiết đơn hàng</a>
							</c:if>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="js/myOrder.js"></script>	
</body>
</html>