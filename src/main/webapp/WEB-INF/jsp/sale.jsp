<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      layout:decorate="default">
<head>
    <title>Danh sách hóa đơn</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sale.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/sale.js"></script>
</head>
<body style="background: #f0f1f1">
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
<div id="index">
    <div id="header">
        <h2 style="padding: 11px 0px 0px 32px;display: inline-block">Báo cáo doanh thu</h2>
    </div>
    <div class="content">
        <form action="/report/view" method="post">
        <form action="/report/view" method="post">
            <div>
                <button style="display:none" id="print">print</button>
                <div class="print-receipt">
						<span class="material-symbols-outlined" id="print-icon">
							print
						</span>
                    <h4 class="print-txt">In báo cáo</h4>
                </div>
            </div>
            <div style="height:80px">
                <button style="display:none;width:30%">submit</button>
                <div class="div-start">
                    <label for="price" class="label-color bold-title">Từ ngày: </label>
                    <input type="date" class="input-general" id="report-start" name="startDate" value="${startDate}"/>
                    <span class="error-label" style="padding-left: 67px;display: table-cell;" id="error-start"></span>
                </div>
                <div class="div-end">
                    <label for="price" class="label-color bold-title">Đến ngày: </label>
                    <input type="date" class="input-general" id="report-end"  name="endDate" value="${endDate}"/>
                    <span class="error-label" style="padding-left: 75px;display: table-cell;" id="error-end"></span>
                </div>
                <button class="view-but">Xem</button>
            </div>
        </form>
        <input type = "hidden" value="${productReports}" id = "size"/>
        <div class="main-content">
            <table class="box-shadow">
                <tr style="background-color: #f4f6f8;">
                    <th style = "width:10%;margin-left: 20px">Mã sản phẩm</th>
                    <th style = "width:30%;" align="left">Tên sản phẩm</th>
                    <th style = "width:15%">SL bán</th>
                    <th style = "width:15%">Doanh thu</th>
                </tr>
            </table>
            <table class="box-shadow">
                <c:forEach items="${productReports}" var="report">
                    <tr class="detail-line" align="center">
                            <td style = "width:10%;margin-left: 20px">${report.productId}</td>
                            <td align="left" style = "width:30%;">${report.productName}</td>
                            <td style = "width:15%" class="amount">${report.quantity}</td>
                            <td class="sale" style = "width:15%">${report.totalPrice}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div id="line-null" class="box-shadow" align="center"></div>
        <div id="total-table" class="box-shadow">
            <table class="box-shadow">
                <tr style="background-color: #f4f6f8;display:none">
                    <th style = "width:10%;margin-left: 20px"></th>
                    <th style = "width:30%;" align="left"></th>
                    <th style = "width:15%"></th>
                </tr>
                <tr>
                    <td style = "width:46%;font-weight:bold" colspan=2 align="center">Tổng cộng:</td>
                    <td id="totalAmount" style = "width:13.7%"></td>
                    <td id="totalValue" style = "width:15.3%"></td>
                </tr>
            </table>
        </div>
    </div>
</div>
<script>

</script>
</body>
</html>
