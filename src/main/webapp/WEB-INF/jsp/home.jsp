<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
    <link rel="stylesheet" href="/css/base.css">
    <link rel="stylesheet" href="./css/grid.css">
    <link rel="stylesheet" href="./css/main.css">
    <link rel="stylesheet" href="./css/pagination.css">

<%--    <link rel="stylesheet" href="/css/bootstrap.min.css">--%>
    <script src="assets/bootstrap-4.5.2-dist/js/jquery.min.js"></script>
    <script src="assets/bootstrap-4.5.2-dist/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript" src="displayProduct.js"></script>
    <script>
        var totalProducts = "${totalProducts}"
        function fetchData(display_Start) {
            var data;
            $.ajax({
                type: "GET",
                <%--headers: {"Authorization": "Bearer " +'${sessionScope.jwtResponse.accessToken}'},--%>
                url: "http://localhost:8080/api/products/" + (display_Start - 1),
                success: function (htmldata) {
                    data = htmldata;
                    var formProduct = document.querySelector(".row.sm-gutter.form-product")
                    formProduct.innerHTML = ""
                    $("row.sm-gutter.form-product").append("");
                    for (var i = 0; i < data.length; i++) {

                        var html =
                            "<div class='col l-2-4 m-4 c-6 product-form'>"
                            + "<a class='home-product-item' href='/products/detail?productId=" + data[i].id + "'>"
                            + "<div class='home-product-item__img' style='background-image: url("+data[i].urlImage+");'>"
                            // +"<img src='"+data[i].urlImage+"' alt=''>"
                            + "</div>"
                            + "<h4 class='home-product-item__name'>" + data[i].productName + "</h4>"
                            + "<div class='home-product-item__price'>"
                            + "<span class='home-product-item__price-old'>"
                            + "<span>" + data[i].oldPrice + "</span>"
                            + "<span>đ</span>"
                            + "</span>"
                            + "<span class='home-product-item__price-current'>"
                            + "<span>" + data[i].newPrice + "</span>"
                            + "<span>đ<span>"
                            + "</span>"
                            + "</div>"
                            + "<div class='home-product-item__action'>"
                            + "<span class='home-product-item__like home-product-item__like--liked'>"
                            + "<i class='home-product-item__like-icon-empty far fa-heart'></i>"
                            + "<i class='home-product-item__like-icon-fill fas fa-heart'></i>"
                            + " </span>"
                            // + "<span class='home-product-item__sold'>"<span>sol<span>"</span>"
                            + "</div>"
                            + "<div class='home-product-item__origin'>"
                            + "<span class='home-product-item__brand'>" + data[i].category + "</span>"
                            + "<div class='home-product-item__origin-name'>"
                            + "<span style='padding-right: 3px'>Đã bán</span>"
                            + "<span >" + data[i].soldAmount + "</span>"
                            + "</div>"
                            + "</div>"
                            + "<div class='home-product-item__favourite'>"
                            + "<i class='fas fa-check'></i>"
                            + "<span>Yêu thích</span>"
                            + "</div>"
                            + "<div class='home-product-item__sale-off'>"
                            + "<span class='home-product-item__sale-off-percent'>"+data[i].percentDiscount+"</span>"
                            // + "<span>%</span>"
                            + "<span class='home-product-item__sale-off-label'>GIẢM</span>"
                            + "</div>"
                            + "</a>"
                            + "</div>"
                        $(".row.sm-gutter.form-product").append(html)
                    }
                },
                error: function (errorThrown) {
                    alert("something went wrong");
                }
            })
        }
            function searchData(name, display_Start) {
                var data;
                $.ajax({
                    type: "GET",
                    url: "http://localhost:8080/api/products?name=" + name + "&currentPage=" + (display_Start - 1),
                    <%--headers: {"Authorization": "Bearer " +'${sessionScope.jwtResponse.accessToken}'},--%>
                    success: function (htmldata) {
                        data = htmldata;
                        var formProduct = document.querySelector(".row.sm-gutter.form-product")
                        formProduct.innerHTML = ""
                        $("row.sm-gutter.form-product").append("");
                        for (var i = 0; i < data.length; i++) {

                            var html =
                                "<div class='col l-2-4 m-4 c-6 product-form'>"
                                + "<a class='home-product-item' href='/product/detail?productId=" + data[i].id + "'>"
                                + "<div class='home-product-item__img' style='background-image: url("+data[i].urlImage+");'></div>"
                                + "<h4 class='home-product-item__name'>" + data[i].productName + "</h4>"
                                + "<div class='home-product-item__price'>"
                                + "<span class='home-product-item__price-old'>"
                                + "<span>" + data[i].oldPrice + "</span>"
                                + "<span>đ</span>"
                                + "</span>"
                                + "<span class='home-product-item__price-current'>"
                                + "<span>" + data[i].newPrice + "</span>"
                                + "<span>đ<span>"
                                + "</span>"
                                + "</div>"
                                + "<div class='home-product-item__action'>"
                                + "<span class='home-product-item__like home-product-item__like--liked'>"
                                + "<i class='home-product-item__like-icon-empty far fa-heart'></i>"
                                + "<i class='home-product-item__like-icon-fill fas fa-heart'></i>"
                                + " </span>"
                                // + "<span class='home-product-item__sold'>"<span>sol<span>"</span>"
                                + "</div>"
                                + "<div class='home-product-item__origin'>"
                                + "<span class='home-product-item__brand'>" + data[i].category + "</span>"
                                + "<div class='home-product-item__origin-name'>"
                                + "<span style='padding-right: 3px'>Đã bán</span>"
                                + "<span >" + data[i].soldAmount + "</span>"
                                + "</div>"
                                + "</div>"
                                + "<div class='home-product-item__favourite'>"
                                + "<i class='fas fa-check'></i>"
                                + "<span>Yêu thích</span>"
                                + "</div>"
                                + "<div class='home-product-item__sale-off'>"
                                + "<span class='home-product-item__sale-off-percent'>43%</span>"
                                + "<span class='home-product-item__sale-off-label'>GIẢM</span>"
                                + "</div>"
                                + "</a>"
                                + "</div>"
                            $(".row.sm-gutter.form-product").append(html)
                        }
                    },
                    error: function (errorThrown) {
                        alert("có lỗi xảy ra");
                    }
                })
            }
    </script>
    <style>
        .pagination>li>a, .pagination>li>span {
            position: relative;
            float: left;
            padding: 6px 12px;
            line-height: 1.42857143;
            text-decoration: none;
            color: #337ab7;
            border: 1px solid #ddd;
            margin-left: -1px;
        }
    </style>
    <title>Home</title>

</head>
<body>
<jsp:include page="header.jsp"/>
<div class="app__container">
    <div class="grid wide">
        <div class="row sm-gutter app__content">
            <div class="col l-2 m-0 c-0">
                <nav class="category">
                    <h3 class="category__heading">Danh mục</h3>
                    <ul class="category-list">
                        <li class="category-item category-item--active">
                            <a href="#" class="category-item__link">Trang điểm mặt</a>
                        </li>
                        <li class="category-item">
                            <a href="#" class="category-item__link">Trang điểm môi</a>
                        </li>
                        <li class="category-item">
                            <a href="#" class="category-item__link">Trang điểm mắt</a>
                        </li>
                    </ul>
                </nav>
            </div>
            <div class="col l-10 m-12 c-12">

                <nav class="mobile-category">
                    <ul class="mobile-category__list">
                    </ul>
                </nav>
                <div class="home-product">
                    <table class="row sm-gutter form-product">

                        </table>

                    </div>
                <ul class="pagination home-product__pagination">
                </ul>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
<script type="text/javascript"
        src="js/jquery.bootpag.min.js"></script>

<script>
    $(document).ready(function() {
        /* Add your logic here */
        set_paging(Math.ceil(totalProducts / 4));
        fetchData(1);
        $(".header__search-input").on("keyup",function () {
            searchData(document.querySelector(".header__search-input").value, 1)
        })
        $(".header__search-input").on("keypress",function (event) {
            if (event.key === "Enter") {
                // Cancel the default action, if needed
                event.preventDefault();
                searchData(document.querySelector(".header__search-input").value, 1)
            }
        })
    });
    $('.pagination.home-product__pagination').bootpag({
        total : Math.ceil(totalProducts / 4),
        maxVisible : 5,
        page : 1
    });

    function set_paging(total_pages) {
        $('.pagination.home-product__pagination').html('');
        $('.pagination.home-product__pagination').bootpag({
            total : total_pages,
            page : 1,
            maxVisible : 5,
            leaps : true,
            firstLastUse : true,
            first : '<span aria-hidden="true">&larr;</span>',
            last : '<span aria-hidden="true">&rarr;</span>',
            wrapClass : 'pagination',
            activeClass : 'active',
            disabledClass : 'disabled',
            nextClass : 'next',
            prevClass : 'prev',
            lastClass : 'last',
            firstClass : 'first'
        }).on("page", function(event, num) {
            display_Start = num;
            fetchData(display_Start);
        }).find('.pagination');
    }
</script>
</body>
</html>