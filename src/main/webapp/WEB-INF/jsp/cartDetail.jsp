<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cart Detail</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cartdetails.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css"/>
    <link rel="stylesheet" href="/css/base.css">
    <link rel="stylesheet" href="./css/grid.css">
    <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>

<body>
<jsp:include page="header.jsp"/>
<div class="cartdetails">
    <p>GIỎ HÀNG</p>
    <c:set var="amount" value="0"></c:set>
    <c:forEach items="${carts}" var="a">
        <c:set var="amount" value="${total}"></c:set>
    </c:forEach>
    <form method="post" action="/payment" class="container">
        <div class="container-item one">
            <div class="container-item-one">
                <div class="check all">
                    <input type="checkbox" name="check" id=""> <label
                        for="check">Tất cả (<span class="amount">${total}</span> sản phẩm)
                </label>
                </div>
                <div class="right">
                    <a href="">Đơn giá</a> <a href="">Số lượng</a> <a href="">Thành
                    tiền</a> <a href=""><em class="fas fa-trash-alt removeAll"></em></a>
                </div>
            </div>
            <div class="freeship">
                <div class="processbar-content">
                    <span>......</span>
                    <span>-10,000</span>
                    <span>-30,000</span>
                </div>
                <div class="processbar">
                    <div class="one"></div>
                    <div class="processbar-content">
                        <span>Mua</span>
                        <span>100,000</span>
                        <span>300,000</span>
                    </div>
                    <p>FREESHIP<span>+</span></p>
                </div>
            </div>
            <div class="listCartItems">
                <c:forEach items="${carts}" var="item">
                    <div class="container-item-two">
                        <div class="check">
                            <input type="checkbox" name="check" id="check">
                            <img src="${item.urlImage}" alt="">
                        </div>
                        <div class="right">
                            <p>${item.productName}</p>
                            <p class="prices">${item.newPrice}<span></span>
                            </p>
                            <div class="amount">
                                <input type="hidden" name="userId" value="${sessionScope.jwtResponse.id}">
                                <span data-id="${item.productId}" class="sub" style="border-radius: 2px 0px 0 2px;">-</span>
                                <span class="quantity">${item.quantity}</span>
                                <span data-id="${item.productId}" class="add" style="border-radius: 0 2px 2px 0; border-right: .5px solid black;">+</span>
                            </div>
                            <p class="money">${item.quantity * item.newPrice}</p>
                            <a class="removeCart" data-id="${item.productId}" href="#"><em class="fas fa-trash-alt"></em></a>
                        </div>
                    </div>
                    <c:set var="amount" value="${amount + 1}"></c:set>
                </c:forEach>
            </div>
        </div>
        <div class="container-item">
            <div class="address">
                <div class="address-one">
                    <p style="width: 150px !important;">Giao tới</p>
                    <a href="/addresses" id="update-address">Thay đổi</a>
                </div>
                <p>
                    <input type="hidden" id="addressId" value="${sessionScope.address.addressId}">
                    ${sessionScope.address.fullnameRecive}
                </p>
                <p><span>${sessionScope.address.phone}</span></p>
                <p class="address-detail">
                    <c:if test="${sessionScope.address == null}">
                        <a href="/addresses" id="add-address"> Vui lòng nhập địa chỉ giao hàng</a>
                    </c:if>
                    ${sessionScope.address.detailAddress}
                </p>
            </div>
<%--            <div class="free">--%>
<%--                <div class="free-one">--%>
<%--                    <p>Khuyến Mãi</p>--%>
<%--                    <a href="">Có thể chọn <span id="amount-discount"></span></a>--%>
<%--                </div>--%>
<%--                <a href="" id="choose-discount"><em class="fas fa-percentage"></em>Chọn hoặc nhập--%>
<%--                    Khuyến mãi</a>--%>
<%--            </div>--%>
            <div class="pay">
                <div class="pay-item">
                    <p>Tạm tính</p>
                    <p>
                        <span class="totalMoney">0</span>
                    </p>
                </div>
                <div class="pay-item">
                    <p>Giảm giá</p>
                    <p class="frees">
                        <span>0</span>
                    </p>
                </div>
                <div class="pay-item">
                    <p>Tổng cộng</p>
                    <p>
                        <span class="lastPrice">Vui lòng chọn sản phẩm</span>
                    </p>
                </div>
                <p class="tax">Đã bao gồm VAT nếu có</p>
            </div>
            <input type="text" name="productIds">
            <input type="text" name="freeShip">
            <input type="hidden" name="ogrinalPrice">
            <div class="btn">
                <button class="btn-pay" >Mua hàng(<span class="count"></span>)</button>
                <button type="submit" id="btn-pay" style="display: none" ></button>
            </div>
        </div>
    </form>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/chooseCart.js"></script>

<script>


</script>
</body>
</html>