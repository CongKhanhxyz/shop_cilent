<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chi tiết khuyến mại</title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script>
        function numberWithCommas(x) {
            return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        }
        function appendBook(barcode,name){
            var tr = document.createElement("tr");
            var td_barcode = document.createElement("td");
            var td_img = document.createElement("td");
            var td_name = document.createElement("td");
            td_name.setAttribute("title",name);
            td_name.innerHTML = name;
            tr.append(td_barcode,td_img,td_name,);
            $("#content-table").append(tr);
        }
        function getTimeRemaining(endtime){
            const total = endtime.getTime() - (new Date()).getTime() + 61200000;
            const seconds = Math.floor( (total/1000) % 60 );
            const minutes = Math.floor( (total/1000/60) % 60 );
            const hours = Math.floor( (total/(1000*60*60)) % 24 );
            const days = Math.floor( total/(1000*60*60*24) );

            return {
                total,
                days,
                hours,
                minutes,
                seconds
            };
        }
        function getTimeStart(starttime){
            const total = starttime.getTime() - (new Date()).getTime() - 25200000;
            const seconds = Math.floor( (total/1000) % 60 );
            const minutes = Math.floor( (total/1000/60) % 60 );
            const hours = Math.floor( (total/(1000*60*60)) % 24 );
            const days = Math.floor( total/(1000*60*60*24) );

            return {
                total,
                days,
                hours,
                minutes,
                seconds
            };
        }
        function initClockStart(starttime){
            const timeinterval = setInterval(() => {
                const t = getTimeStart(starttime);
                $("#remain-day").text( t.days + " ngày " + t.hours + " giờ " + t.minutes + " phút " + t.seconds+" giây");
                if (t.total <= 0) {
                    clearInterval(timeinterval);
                }
            },1000);
        }
        function initializeClock(endtime) {
            const timeinterval = setInterval(() => {
                const t = getTimeRemaining(endtime);
                $("#remain-day").text( t.days + " ngày " + t.hours + " giờ " + t.minutes + " phút " + t.seconds+" giây");
                if (t.total <= 0) {
                    clearInterval(timeinterval);
                }
            },1000);
        }
        $(document).ready(function(){
            if($("#unit").text() == "VNĐ"){
                var val = parseInt($("#value").text());
                val = numberWithCommas(val);
                $("#value").text(val);
            }
            var date1 = new Date();
            var date2 = new Date($("#day-end").text());
            var date_start = new Date($("#day-start").text());
            var milli_secs = date2.getTime() - date1.getTime();
            if((date_start.getTime() - date1.getTime() - 25200000) <= 0){
                $("#remain-text").text("Thời gian còn lại");
                if((date2.getTime() - date1.getTime() + 61200000) <= 0){
                    $("#remain-day").text("Hết hạn");
                    $("#remain-day").attr("style","color:red");
                }else{
                    const t = getTimeRemaining(date2);
                    $("#remain-day").text( t.days + " ngày " + t.hours + " giờ " + t.minutes + " phút " + t.seconds+" giây");
                    $("#remain-day").attr("style","color:#0088ff");
                    initializeClock(date2);
                }
            }else{
                const t = getTimeStart(date_start);
                $("#remain-day").text( t.days + " ngày " + t.hours + " giờ " + t.minutes + " phút " + t.seconds+" giây");
                $("#remain-text").text("Bắt đầu sau");
                $("#remain-day").attr("style","color:#ff9900");
                initClockStart(date_start);
            }

            if($("#method").text() == "Khuyến mại theo đầu sách"){
                $("#book").show();
            }else{
                $("#type").show();
            }
            if($("#allBook").val() == "true"){
                $("#content-table").find("td").remove();
                appendBook(null,"Tất cả đầu sách");
            }
            $(".but-del").click(function(e){
                if(confirm("Xác nhận xóa?")==true){
                    $("#submit").click();
                    alert("Đã xóa");
                }
            });
            $(".but-edit").click(function(){
                const a = "/discount/edit/" + $("#maKM").val();
                window.location.href= a;
            });
            $(".menu-bar").click(function(event){
                const locate = $(event.delegateTarget).children("span.menu-txt").text();
                switch(locate){
                    case "Trang chủ":
                        window.location.href="/";
                        break;
                    case "Sản phẩm":
                        window.location.href="/products";
                        break;
                    case "Doanh thu":
                        window.location.href="/report/view";
                        break;
                    case "Khuyến mại":
                        window.location.href="/discounts";
                        break;
                    case "Đăng xuất":
                        window.location.href="/account/sign-out";
                        break;
                }
            });
        });
    </script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"/>
    <style>
        *{
            margin:0;
        }
        #header{
            height:52px;
            width:100%;
            box-shadow: 0px 2px 4px rgb(168 168 168/25%);
            z-index: 1;
            position: sticky;
            min-width: 442px;
            background:white;
        }
        a{
            text-decoration: none;
        }
        .back-ico{
            position: absolute;
            color: #747C87;
            top:13px;
            cursor: pointer;
        }
        .link-back{
            text-decoration:none;
            font-weight:bold;
            margin-left:30px;
            font-size:16px;
            color:#747C87;
            cursor: pointer;
        }
        .head-block-1{
            float:left;
            padding: 15px 0px 15px 32px;
            width:40%;
        }

        .head-block-2{
            float:right;
            min-width:80px;
            padding: 6px 5px 0px 0px;
        }
        .head-block-3{
            float:right;
            min-width:141px;
            padding-top:6px;
            width:12%;
        }
        .but-del{
            border:1px solid rgb(255, 77, 77);
            border-radius:3px;
            background: white;
            color:rgb(255, 77, 77);
            font-weight:bold;
            font-size:14px;
            padding:11px 20px 10px 20px;
            cursor: pointer;
        }
        .but-del:hover{
            background: rgb(255 231 231);
        }
        .but-edit{
            border:1px solid rgb(51, 160, 255);
            border-radius:3px;
            font-weight:bold;
            font-size:14px;
            padding:11px 16px 10px 16px;
            color: white;
            background:rgb(0, 136, 255);
            cursor: pointer;
            text-overflow: ellipsis;
            overflow: hidden;
            white-space: nowrap;
        }
        .but-edit:hover{
            background:rgb(51, 160, 255);
        }
        .body{
            background: #f0f1f1;
            width:auto;
            margin-top:0px;
            padding: 0px 4% 16px 4%;
            min-width: 420px;
        }
        .box-shadow{
            box-shadow: 0px 2px 4px rgb(168 168 168/25%);
        }
        .book-name-title{
            text-transform: capitalize;
            font-size: 20px;
            font-family:sans-serif;
            line-height: 24px;
            padding: 30px 0px 30px 0px;
        }
        .title{
            font-weight: bold;
            font-size: 16px;
        }
        .block-info-1{
            background:white;
            padding: 20px 10px 20px 30px;
            border-bottom: 1px solid #E8EAEB;
        }
        .block-info-2{
            background: white;
            padding: 20px 10px 20px 30px;
            height:150px;
        }
        .img{
            height:150px;
            width:95px;
            float:left;
        }
        .info{
            float:left;
            width:40%;
            margin-left:7%;
            margin-top:10px;
        }
        .div-txt-info{
            width:auto;
            height:40px;
            min-width:120px;
            overflow:hidden;
            align-items: center;
            display: flex;
        }
        .info-label{
            width:55%;
            color:#747C87;
            text-overflow: ellipsis;
            overflow: hidden;
            white-space: nowrap;
        }
        .info-data{
            padding:5px;
            min-width: 105px;
            text-overflow: ellipsis;
            overflow: hidden;
            white-space: nowrap;
        }
        .block-sale{
            width:40%;
        }
        .div-sale{
            width:50%;
            float:left;
            min-width: 200px;
        }
        .width-80{
            width: 80px;
        }
        .inline{
            display:inline;
        }
        .sale-line-2{
            width:50%;
            min-width:200px;
            margin-top:40px;
        }
        .menu{
            width: 200px;
            height: 100%;
            position: fixed;
            background: #5197ff;
            padding: 52px 0px 0px 0px;
            z-index: 2;
            border-right: 1px solid #e8eaeb;
        }
        .menu-bar{
            margin: 0px 3px 0px 3px;
            border-radius: 3px;
            color: #F3F4F5;
            font-size: 18px;
            padding: 25px 5px 25px 5px;
            border-top: 1px solid #E8EAEB;
        }
        .menu-bar:hover{
            background: #e5e5e542;
            color: white;
            cursor: pointer;
        }
        .menu-icon{
            float: left;
        }
        .menu-txt{
            display: inline-block;
            margin:3px 0px 0px 13px;
        }
        #table-book th, #table-type th{
            padding:10px 10px 10px 30px;
            border-bottom: 1px solid #E8EAEB;
        }
        #table-book td, #table-type td{
            padding:15px 10px 15px 30px;
            border-bottom: 1px solid #E8EAEB;
        }
        table{
            border-collapse: collapse;
        }
        #type, #book{
            display: none;
        }
        #table-type{
            height:auto;
            padding:0px;
            width: 40%;
            background: white;
            min-width: 420px;
        }
        #table-book{
            height:auto;
            padding:0px;
            width: 50%;
            background: white;
            min-width: 420px;
        }
        .f-book-img{
            height:55px;
            width:40px;
        }
        .tb-book-title{
            padding:20px 0px 20px 0px;
            width:50%;
            background: white;
            min-width: 420px;
            border-bottom: 1px solid #E8EAEB;
        }
        .tb-type-title{
            padding:20px 0px 20px 0px;
            width: 40%;
            background: white;
            border-bottom: 1px solid #E8EAEB;
            min-width: 420px;
        }
        #content-table{
            max-height: 449px;
            overflow-y: auto;
        }
    </style>
</head>
<body style="background:#f0f1f1">
<div class="menu">
    <div class="menu-bar">
				<span class="material-symbols-outlined menu-icon">
					home
				</span>
        <span class="menu-txt">Trang chủ</span>
    </div>
    <div class="menu-bar">
				<span class="material-symbols-outlined menu-icon" >
					storefront
				</span>
        <span class="menu-txt">Sản phẩm</span>
    </div>
    <div class="menu-bar">
				<span class="material-symbols-outlined menu-icon" >
					bar_chart
				</span>
        <span class="menu-txt">Doanh thu</span>
    </div>
    <div class="menu-bar">
				<span class="material-symbols-outlined menu-icon" >
					redeem
				</span>
        <span class="menu-txt">Khuyến mại</span>
    </div>
    <div class="menu-bar">
				<span class="material-symbols-outlined menu-icon" >

				</span>
        <span class="menu-txt">Đăng xuất</span>
    </div>
</div>
<div style="margin-left:200px">
    <div id="header">
        <div class="head-block-1">
            <a class="material-symbols-outlined back-ico back" href="/discount/list" >
                chevron_left
            </a>
            <a class="link-back" href="/discount/list">
                Quay lại danh sách khuyến mại
            </a>
        </div>
        <div class="head-block-3">
            <button class="but-edit">
                Sửa khuyến mại
            </button>
        </div>
        <div class="head-block-2">
            <form action="/discount/delete">
                <button id="delete" style="display:none">Xóa</button>
            </form>
            <button class="but-del">
                Xóa
            </button>
        </div>
    </div>
    <div class="body">
        <input type="hidden" id="allBook" th:value="${allBook}"/>
        <h4 class="book-name-title" th:text="${discount.getName()}"></h4>
        <div class="box-shadow">
            <div class="block-info-1 title">
                Thông tin chi tiết
            </div>
            <div class="block-info-2">
                <div class="info" style="margin-left:0px">
                    <form action="/discount/delete">
                        <input type="hidden" value="${discount.discountId}" name="maKM"/>
                        <button style="display:none" id="submit"></button>
                        <div class="div-txt-info" >
                            <span class="info-label">Mã khuyến mại</span>
                            <span style="display:inline">:</span>
                            <span class="info-data">${discount.discountId}</span>
                        </div>
                    </form>
                    <div class="div-txt-info" >
                        <span class="info-label">Giảm giá</span>
                        <span style="display:inline">:</span>
                        <div class="info-data">
                            <span id="value">${discount.value}</span>
                            <span id="unit">${discount.unit}</span>
                        </div>
                    </div>
                    <div class="div-txt-info" >
                        <span class="info-label">Phương thức khuyến mại</span>
                        <span style="display:inline">:</span>
                        <span class="info-data" id="method">${discount.method}</span>
                    </div>
                </div>
                <div class="info">
                    <div class="div-txt-info" >
                        <span class="info-label">Ngày bắt đầu</span>
                        <span style="display:inline">:</span>
                        <span class="info-data" id="day-start">${discount.startDate}</span>
                    </div>
                    <div class="div-txt-info" >
                        <span class="info-label">Ngày kết thúc</span>
                        <span style="display:inline">:</span>
                        <span class="info-data" id="day-end">${discount.endDate}</span>
                    </div>
                    <div class="div-txt-info" >
                        <span class="info-label" id="remain-text">Thời gian còn lại</span>
                        <span style="display:inline">:</span>
                        <span class="info-data" id="remain-day"></span>
                    </div>
                </div>
                <input type="hidden" id="maKM" value="${discount.discountId}"/>
            </div>
        </div>
        <div id="type" style="margin-top:40px">
            <div class="tb-type-title title">
                <span style="padding-left: 30px;">Đối tượng khuyến mại</span>
            </div>
            <div  id="table-type">
                <table style="width:100%">
                    <tr style="background: #f9fafc;">
                        <th align="left">Thể loại</th>
                    </tr>
                </table>
                <div style="max-height:215px;overflow-y:auto">
                    <table style="width:100%">
                        <tr th:each="type: ${types}">
                            <td align="left" th:text="${type}"></td>
                        </tr>
                    </table>
                </div>
            </div>

        </div>
        <div id="book" style="margin-top:40px">
            <div class="tb-book-title title">
                <span style="padding-left: 30px;">Đối tượng khuyến mại</span>
            </div>
            <div id="table-book" class="block-info-2 block-sale">
                <table style="width:100%;" id="tableBook">
                    <tr style="background: #f9fafc;">
                        <th style="width:10%" align="left">Barcode</th>
                        <th style="width:1%" align="left">Ảnh</th>
                        <th style="width:80%" align="left">Tên sách</th>
                    </tr>
                </table>
                <div style="max-height:300px;overflow-y:auto">
                    <table style="width:100%;" id="content-table">
                        <tr th:each="book: ${books}">
                            <td th:text="${book.getBarcode()}" style="width:10%" align="left"></td>
                            <td style="padding-left:27px;width: 1%" align="left">
                                <img class="f-book-img" th:src="${book.getPhotosImagePath()}">
                            </td>
                            <td th:text="${book.getName()}" style="width:80%" align="left"></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
</html>