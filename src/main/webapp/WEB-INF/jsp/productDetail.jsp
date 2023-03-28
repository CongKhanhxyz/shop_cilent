<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết sản phẩm</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/productDetail.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css"/>
    <link rel="stylesheet" href="/css/base.css">
    <link rel="stylesheet" href="./css/grid.css">
    <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="container-product">
        <div class="image">
            <div class="main-img">
                <img src="${product.urlImage}" alt="">
            </div>
        </div>
        <div class="product-detail">
            <h3><span>Yêu thích</span> ${product.productName}</h3>
            <div class="vote">
                <a href="">
                    <span>${percentage}</span>
                    <c:if test="${product.starCount > 0}">
                        <c:forEach begin="1" end="${product.starCount}" step="1">
                            <i class="fas fa-star"></i>
                        </c:forEach>
                    </c:if>
                    <c:if test="${product.isHalfCount > 0}">
                        <i class="fas fa-star-half-alt"></i>
                    </c:if>
                </a>
                <a href=""><span>${product.reviewAmount}${total}<c:if test="${total >= 1000 }">k</c:if></span> Đánh giá</a>
                <a href=""><span>${product.soldAmount}${sold}<c:if test="${sold >= 1000 }">k</c:if></span> Đã bán</a>
            </div>
            <div class="price">
                <p><span class="last-price">${product.oldPrice}</span> <span> ${product.newPrice}</span><span
                        class="free">${product.percentDiscount}%</span></p>
            </div>
            <div class="ship">
                <span style="margin-right: 10px;">Vận chuyển</span>
                <div class="ship-detail">
                    <div class="content">
                        <img src="image/1cdd37339544d858f4d0ade5723cd477.png" alt="">
                    </div>
                    <div class="ship-address">
                        <div class="ship-address-detail">
                            <%--                            <span style="opacity: .6;">Vận chuyển tới</span>--%>
                            <%--                            <span class="address">${CUSTOMER.address}</span>--%>
                            <span id="icon" class="icon"></span>
                            <div class="chose-address">
                                <div class="search">
                                    <input type="hidden" id="customerID" name="" value="${CUSTOMER.customerID}">
                                    <input placeholder="Tìm" type="text" name="" id="search">
                                </div>
                                <div id="address" class="drop-address"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="quantity">
                <span class="quantity-title">Số lượng</span>
                <div class="update">
                    <span id="sub" style="cursor: pointer">-</span>
                    <span id="quantity">1</span>
                    <span id="add" style="cursor: pointer">+</span>
                </div>
                <span style="opacity: .7;">${product.quantity} sản phẩm có sẵn</span>
            </div>
            <div class="function" id="userId" data-id="${sessionScope.jwtResponse.id}">

                <c:if test="${sessionScope.jwtResponse.role eq 'ADMIN'}">
                    <a href="" id="delete" data-toggle="tooltip"
                       title="Xóa" data-id="${product.id}">Xóa</a>
                    <a href="/products/editView?productId=${product.id}">Sửa</a>
                </c:if>
                <c:if test="${!sessionScope.jwtResponse.role eq 'ADMIN'}">
                    <c:choose>
                        <c:when test="${sessionScope.jwtResponse eq null}">
                            <a href="/login" class="add-to-cart" id="add-to-cart" data-toggle="tooltip"
                               title="Thêm vào giỏ hàng!" data-id="${product.id}">
                                <em class="fas fa-cart-plus"></em>Thêm vào giỏ hàng</a>
                            <a href="" data-id="${product.id}" class="buy-now">Mua ngay</a>
                        </c:when>
                        <c:otherwise>
                            <a href="" class="add-to-cart" id="add-to-cart" data-toggle="tooltip"
                               title="Thêm vào giỏ hàng!" data-id="${product.id}">
                                <em class="fas fa-cart-plus"></em>Thêm vào giỏ hàng</a>
                            <a href="" data-id="${product.id}" class="buy-now">Mua ngay</a>
                        </c:otherwise>
                    </c:choose>
                </c:if>
            </div>
        </div>
    </div>
    <div class="reviews">
        <h1>Đánh giá sản phẩm</h1>
        <div class="vote">
            <div class="vote-left">
                <p><span>${product.starCount}.${product.starCountTail}</span> trên 5</p>
                <a href="">
                    <c:if test="${product.starCount > 0}">
                        <c:forEach begin="1" end="${product.starCount}" step="1">
                            <i class="fas fa-star"></i>
                        </c:forEach>
                    </c:if>
                    <c:if test="${product.isHalfCount > 0}">
                        <i class="fas fa-star-half-alt"></i>
                    </c:if>
                </a>
            </div>
            <div class="vote-right">
                <a href="">Tất cả</a>

                <a data-id="5" class="star" id="five">5 sao (<span>${count0}</span>)</a>
                <a data-id="4" class="star" id="four">4 sao (<span>${count1}</span>)</a>
                <a data-id="3" class="star" id="three">3 sao (<span>${count2}</span>)</a>
                <a data-id="2" class="star" id="two">2 sao (<span>${count3}</span>)</a>
                <a data-id="1" class="star" id="one">1 sao (<span>${count4} </span>)</a>
            </div>
        </div>
        <div id="reviews-wrap">
            <c:forEach items="${reviews}" var="item">
                <div class="reviews-detail">
                    <div class="reviews-detail-avatar">
                        <img src="${item.urlImageAvatar}" alt="">
                    </div>
                    <div class="reviews-detail-content">
                        <p class="name">${item.lastname}</p>
                        <span class="star">
                        <c:forEach begin="1" end="${item.star}" step="1">
                            <em class="fas fa-star"></em>
                        </c:forEach>
                        </span>
                        <p class="content">${item.reviewText}</p>
                        <div class="images">
                            <img src="${item.urlImage}" alt="">
                            <img src="${item.urlImage}" alt="">
                            <img src="${item.urlImage}" alt="">
                        </div>
                        <p class="date" style="padding: 10px 0px;font-weight: 550">${item.createdDate}</p>
                        <div class="like-item">
                                    <span class="like${item.reviewID}"
                                          <c:if test="${item.isLike == 1}">style="color: #ee4d2d;cursor: pointer"</c:if>
                                          <c:if test="${item.isLike == 0}">style="color: #ccc;cursor: pointer"</c:if>><i
                                            id="like" class="fas fa-thumbs-up">
                                    <input type="hidden" id="ID" name="reviewId" value="${item.reviewID}">
                                    </i>
                                    </span>
                            <span style="color: #ccc" class="total-like${item.reviewID}">
                            <c:if test="${item.totalLike >= 1}">${item.totalLike}</c:if>
                            <c:if test="${item.totalLike < 1}"> ? Hữu ích</c:if>
                            </span>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <input type="hidden" value="${reviewSize}" name="reviewSize">
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/productDetail.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/StarPagination.js"></script>


<script type="text/javascript">
    $(document).ready(() => {
        $('#add-to-cart').on('click', (e) => {
                e.preventDefault()
                const quantity = $('#quantity').text()
                const userId = $('#userId').data('id')
                const id = $('#add-to-cart').data('id')
                console.log(userId)
                console.log(quantity)
                console.log(id)
                const json = {
                    'userId': userId,
                    'productId': id,
                    'quantity': quantity
                }
                $.ajax({
                    type: 'POST',
                    contentType: 'application/json',
                    url: 'http://localhost:8080/api/carts',
                    data: JSON.stringify(json),
                    success: (data) => {
                        alert(data)
                    }
                })
            }
        )
        // })
        $('.buy-now').on('click', (e) => {
            console.log('a')
            e.preventDefault()
            const quantity = $('#quantity').text()
            const userId = $('#userId').data('id')
            const id = $('#add-to-cart').data('id')
            console.log(userId)
            console.log(quantity)
            const json = {
                'userId': userId,
                'productId': id,
                'quantity': quantity
            }
            $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: 'http://localhost:8080/api/carts',
                data: JSON.stringify(json),
                success: (data) => {
                    window.location.href = 'http://localhost:8081/carts'
                }
            })
        })
        $('#delete').on('click', (e) => {
            e.preventDefault();
            let idDelete = $('#delete').data('id')
            $.ajax({
                type: 'DELETE',
                url: 'http://localhost:8080/api/products/delete?productId=' + idDelete,
                success: (data) => {
                    window.location.href = 'http://localhost:8081/'
                }
            })
        })
        const star = $('.star')
        for (var i = 0; i < star.length; i++) {
            $(star[i]).on('click', (e) => {
                console.log($(e).data('id'));
            })
        }
    })
</script>
</body>
</html>