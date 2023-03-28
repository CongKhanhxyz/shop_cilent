<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
    <link rel="stylesheet" href="css/orderSuccess.css">
    <title>Order Success</title>
</head>
<body>
    <div class="header">
        <div class="logo">
            <img src="image/ghost-castle (1).png" alt="">
        </div>
        <div class="status">
            <div class="title">
                <span>Đăng Nhập</span>
                <span>Địa chỉ giao hàng</span>
                <span>Thanh toán và đặt mua</span>
            </div>
            <div class="processbar">
                <div class="percent"></div>
            </div>
        </div>
        <div class="contact">
            <a href=""><em class="fas fa-phone animate__tada"></em></a>
            <div>
                <p>0824701828</p>
                <span>8h-21h, cả T7 và CN</span>
            </div>
        </div>
    </div>
    <div class="main">
        <div class="container">
            <div class="icons">
                <img src="icons/online-order.png" alt="">
            </div>
            <div class="container-item">
                <p>cảm ơn quý khách đã đặt hàng</p>
                <p>Mã hoá đơn của bạn:</p>
                <p>${orderID}</p>
                <p>Đơn hàng đang được vận chuyển .</p>
                <div class="img">
                    <img src="icons/cargo.png" alt="">
                    <p>Giao vào ${order.shipDate}</p>
                </div>
            </div>
        </div>
        <a class="order-continue" href="/">Order Continue</a>
    </div>
</body>
</html>