<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Admin</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="css/homeAdmin.css">
</head>
<body>
<div class="functional">
    <div class="nav">
        <ul>
            <li>
                <a href="">
                        <span class="icons">
                            <em class="fas fa-ghost"></em>
                        </span>
                    <span class="content">
                            Admin
                        </span>
                </a>
            </li>
            <li>
                <a href="">
                        <span class="icons">
                            <em class="fas fa-home"></em>
                        </span>
                    <span class="content">
                            Home
                        </span>
                </a>
            </li>
            <li>
                <a href="/products">
                        <span class="icons">
                            <em class="far fa-plus"></em>
                        </span>
                    <span class="content">
                        Thêm sản phẩm
                        </span>
                </a>
            </li>
            <li>
                <a href="Controller">
                        <span class="icons">
                            <em class="fas fa-store"></em>
                        </span>
                    <span class="content">
                            Store
                        </span>
                </a>
            </li>
            <li>
                <a href="">
                        <span class="icons">
                            <em class="far fa-envelope"></em>
                        </span>
                    <span class="content">
                            Message
                        </span>
                </a>
            </li>
            <li>
                <a href="">
                        <span class="icons">
                            <em class="fas fa-cog"></em>
                        </span>
                        <span class="content">
                            Setting
                        </span>
                </a>
            </li>
            <li>
                <a href="LogOut">
                        <span class="icons">
                            <em class="fas fa-sign-out-alt"></em>
                        </span>
                    <span class="content">
                            Sign out
                        </span>
                </a>
            </li>
        </ul>
    </div>
    <div class="home">
        <div class="home-item">
            <a class="home-item-icon" href="">
                <em class="fas fa-bars"></em>
            </a>
            <div class="home-item-search">
                <a href=""><em class="fas fa-search"></em></a>
                <input type="text" placeholder="search here">
            </div>
            <div class="home-item-img">
                <img src="image/anh-gai-cute.jpg" alt="">
            </div>
        </div>
        <div class="home-item">
            <div class="home-item-statistical">
                <div class="statistical-number">
                    <h3>1,504</h3>
                    <a href=""><em class="fas fa-eye"></em></a>
                </div>
                <p>Daily Views</p>
            </div>
            <div class="home-item-statistical">
                <div class="statistical-number">
                    <h3>${orderOfDay}</h3>
                    <a href=""><em class="fas fa-shopping-cart"></em></a>
                </div>
                <p>Orders</p>
            </div>
            <div class="home-item-statistical">
                <div class="statistical-number">
                    <h3>284</h3>
                    <a href=""><em class="far fa-comments"></em></a>
                </div>
                <p>Comments</p>
            </div>
            <div class="home-item-statistical">
                <div class="statistical-number">
                    <h3>$${revenueOfDay}</h3>
                    <a href=""><em class="fas fa-money-check-alt"></em></a>
                </div>
                <p>Earning</p>
            </div>
        </div>
        <div class="home-item last">
            <div class="home-item-recent-order">
                <div class="recent-order-title">
                    <p>Recent Orders</p>
                    <a href="">View All</a>
                </div>
                <table>
                    <thead>
                    <tr>
                        <td>Name</td>
                        <td>Price</td>
                        <td>Payment</td>
                        <td><span>Status</span></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${recentOrders}" var="item">
                        <tr>
                            <td>${item.name}</td>
                            <td>$${item.price}</td>
                            <td>Agribank</td>
                            <td><span style="background-color: ${item.colorStatus} !important;">${item.status}</span>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="home-item-recent-customer">
                <p>Recent Customers</p>
                <c:forEach items="${recentOrders}" var="item">
                        <div class="recent-customer-content">
                    		<div class="image">
                        		<img src="${item.avatar }" alt="">
                    		</div>
                    		<p>${item.name }</p>
                		</div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<script src="https://unpkg.com/ionicons@4.5.10-0/dist/ionicons.js"></script>
<script src="js/homeAdmin.js"></script>
</body>
</html>