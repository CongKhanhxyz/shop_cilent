
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm khuyến mại</title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script>
        const d = new Date();
        const year = d.getFullYear();
        let month,day;
        if(d.getMonth()+1 > 9){
            month = x.toString();
        }else{
            month = "0" + (d.getMonth()+1) ;
        }
        if(d.getDate() > 9){
            day = d.getDate();
        }else{
            day = "0" + d.getDate();
        }
        let today = year + "-" + month + "-" + day;
        function numberWithCommas(x) {
            return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        }
        function format(){
            check();
            var currentInput = $("#discount").val();
            currentInput = currentInput.replace(/,/g, "");
            if(currentInput != ""){
                currentInput = parseInt(currentInput);
            }
            val = numberWithCommas(currentInput);
            $("#discount").val(val);
        }
        function checkPercent(){
            check();
            let value = $("#discount").val();
            if(value>100){
                $("#error-discount").text("Không thể giảm quá 100%");
                $("#error-discount").attr("style","display:block");
                $("#div-discount").attr("style","margin-bottom:11.6px");
                $("#discount").val(100);
            }
        }
        function appendBook(productId,name,image){
            var tr = document.createElement("tr");
            var td_barcode = document.createElement("td");
            td_barcode.innerHTML = productId;
            td_barcode.setAttribute("class","barcode");
            var td_img = document.createElement("td");
            td_img.setAttribute("style","padding-left:16px");
            var img = document.createElement("img");
            img.setAttribute("class","f-book-img");
            img.setAttribute("src",image);
            td_img.append(img);
            var td_name = document.createElement("td");
            td_name.setAttribute("title",name);
            var book_name = document.createElement("span");
            book_name.setAttribute("class","choosed-book-name");
            book_name.innerHTML = name;
            td_name.append(book_name);
            var td_delete = document.createElement("td");
            var delete_icon = document.createElement("span");
            delete_icon.setAttribute("class","material-symbols-outlined del-icon");
            delete_icon.innerHTML = "delete";
            td_delete.append(delete_icon);
            if(name == "Tất cả  Sản phẩm"){
                tr.append(td_barcode,td_img,td_name);
            }else{
                tr.append(td_barcode,td_img,td_name,td_delete);
            }

            $("#table-book").append(tr);
        }
        function appendType(type){
            var tr = document.createElement("tr");
            var td_type = document.createElement("td");
            td_type.innerHTML = type;
            td_type.setAttribute("class","type");
            var td_discount = document.createElement("td");
            td_discount.setAttribute("class","choosed-book-discount");
            var discount_input = document.createElement("input");
            discount_input.setAttribute("type","number");
            discount_input.setAttribute("style","width:70px");
            discount_input.setAttribute("min","1");
            var discount_value = document.createElement("div");
            discount_value.setAttribute("class","unit-block discount-value");
            discount_value.innerHTML = "Giá trị";
            discount_value.setAttribute("style","background-color:#08f;color:white");
            var discount_percent = document.createElement("div");
            discount_percent.setAttribute("class","unit-block discount-percent");
            discount_percent.innerHTML = "%";
            td_discount.append(discount_input,discount_value,discount_percent);
            var td_delete = document.createElement("td");
            var delete_icon = document.createElement("span");
            delete_icon.setAttribute("class","material-symbols-outlined del-icon");
            delete_icon.innerHTML = "delete";
            td_delete.append(delete_icon);
            tr.append(td_type,td_delete);
            $("#table-type").append(tr);
        }
        function check(){
            let value = $("#discount").val();
            if(value == ""){
                $("#error-discount").text("Hãy nhập mức giảm giá");
                $("#error-discount").attr("style","display:block");
                $("#div-discount").attr("style","margin-bottom:11.6px");
            }
            else if(value == 0){
                $("#error-discount").text("Mức giảm giá phải > 0");
                $("#error-discount").attr("style","display:block");
                $("#div-discount").attr("style","margin-bottom:11.6px");
            }
            else{
                $("#error-discount").hide();
                $("#div-discount").attr("style","margin-bottom:30px");
            }
        }
        $(document).ready(function(){
            $(".menu-bar").click(function(event){
                const locate = $(event.delegateTarget).children("span.menu-txt").text();
                if(locate == "Bán hàng"){
                    window.open("/receipt/add", '_blank');
                }
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
            $(".back").click(function(e){
                let x = "Thông tin đã nhập sẽ không lưu. Xác nhận thoát?";
                if(confirm(x) == false){
                    e.preventDefault();// hàm hệ thống
                }
            });

            $(".but-cancel").click(function(e){
                let x = "Thông tin đã nhập sẽ không lưu. Xác nhận thoát?";
                if( confirm(x) == false){
                    e.preventDefault();// hàm hệ thống
                }
                else{
                    window.location.href="list";
                }
            });
            $("#discount-start").change(function(){
                $("#discount-end").attr("min",$("#discount-start").val());
            });
            $("#discount-end").change(function(){
                $("#discount-start").attr("max",$("#discount-end").val());
            });
            $(".selector").click(function(){
                $("#drop-down").hide();
                $("#drop-down-type").hide();
                $(".box-dropdown").slideToggle(200);
            });
            $(".line-select").click(function(event){
                $("#error-type").hide();
                $("#select").attr("style","border:1px solid rgb(211 213 215);margin-bottom:20px");
                let option = $(event.delegateTarget).text();
                $("#method").val(option);
                if( $("#type-value").text() == "Chọn phương thức" || $("#type-value").text() == option){
                    $("#type-value").text(option);
                    if(option == "Khuyến mại theo từng sản phẩm"){
                        $("#method-product").show();
                        $("#method-type").hide();
                    }else{
                        $("#method-product").hide();
                        $("#method-type").show();
                    }
                }else{
                    let y = "Thay đổi phương thức khuyến mại sẽ xóa các sản phẩm khuyến mại đã chọn. Xác nhận thay đổi?";

                    if( confirm(y) == false){
                        e.preventDefault();// hàm hệ thống
                    }
                    else{
                        $("#type-value").text(option);
                        if(option == "Khuyến mại theo từng sản phẩm"){
                            $("#list-type").val("");
                            $("#table-type").find("td").remove();
                            $("#method-product").show();
                            $("#method-type").hide();
                        }else{
                            $("#list-barcode").val("");
                            $("#table-book").find("td").remove();
                            if($("#check-book").prop("checked")){
                                $("#check-book").click();
                            }
                            $("#method-product").hide();
                            $("#method-type").show();
                        }
                    }
                }
            });
            $("#check-book").change(function(event){
                $(".box-dropdown").hide();
                if($("#check-book").prop("checked")){
                    $("#list-barcode").val("all");
                    $("#drop-down").hide();
                    $("#book-search").attr("disabled","disabled");
                    $("#table-book").find("td").remove();
                    appendBook(null,"Tất cả đầu Sản phẩm");
                    $("#table-book").find("td").find("img").remove();
                    $(".del-icon").click(function(event){
                        $(event.delegateTarget).parents("tr").remove();
                        $("#check-book").prop("checked",false);
                        $("#book-search").attr("placeholder","Chọn Sản phẩm");
                        $("#book-search").attr("disabled",false);
                    });
                    $("#book-search").attr("placeholder","Tất cả đầu Sản phẩm");
                }else{
                    $("#list-barcode").val("");
                    $("#book-search").attr("disabled",false);
                    $("#book-search").attr("placeholder","Chọn Sản phẩm");
                    $("#table-book").find("td").remove();
                }
            });

            $("#discount-start").attr("min",today);
            $("#discount-end").attr("min",today);
            $(".but-save").click(function(){
                $(".input-value").attr("required","required");
                $("#product-name").attr("required","required");
                $("#discount-end").attr("required","\d{4}-\d{2}-\d{2}");
                $("#discount-start").attr("required","\d{4}-\d{2}-\d{2}");
                switch ($("#type-value").text()){
                    case "Chọn phương thức":
                        $("#error-type").show();
                        $("#select").attr("style","border:1px solid red;margin-bottom:0px");
                        break;
                    case "Khuyến mại theo từng sản phẩm":
                        const a = $("#table-book").children().text().substr(63).length;
                        if(a == 0){
                            alert("Hãy chọn đầu Sản phẩm khuyến mại");
                        }
                        break;
                    case "Khuyến mại theo thể loại sản phẩm":
                        const b = $("#table-type").children().text().substr(38).length;
                        if(b == 0){
                            alert("Hãy chọn loại Sản phẩm khuyến mại");
                        }
                        break;
                }
                if( $("#discount").val().replace(/,/g, "") > 0){
                    alert("Tạo thành công");
                    $(".date-but").click();
                }
            });
            $(".search-bar").click(function(){
                if($("#book-search").attr("disabled") != "disabled"){
                    $(".box-dropdown").hide();
                    $("#drop-down").slideToggle(200);
                }
            });
            $(".search-bar-type").click(function(){
                if($("#type-search").attr("disabled") != "disabled"){
                    $(".box-dropdown").hide();
                    $("#drop-down-type").slideToggle(200);
                }
            });
            $(".f-product").click(function(event1){
                let name = $(event1.delegateTarget).children("div.f-product-block-2").children("p.f-book-name").text();
                let price = $(event1.delegateTarget).children("div.f-book-block-3").children("p.f-book-price").text();
                let amount = $(event1.delegateTarget).children("div.f-book-block-3").children("span.f-book-amount").text();
                let barcode = $(event1.delegateTarget).children("div.f-product-block-2").children("p.f-book-barcode").text();
                let img = $(event1.delegateTarget).children("div.f-product-block-1").children("img").attr("src");
                const a = $("#table-book").children().text().substr(63);
                const str = barcode + name;
                const arr = a.split("delete");
                let exist = false;
                if(arr.length > 1){
                    for (let i = 0; i < arr.length; i++){
                        if(arr[i] == str){
                            exist = true;
                            break;
                        }
                    }
                }
                if(exist){
                    alert("Đã có trong danh Sản phẩm khuyến mại");
                }
                else if(price == 0){
                    alert("Sản phẩm có giá bán = 0. Không thể bán");
                }
                else if(amount == 0){
                    alert("Sản phẩm có số lượng = 0. Không thể bán");
                }else{
                    appendBook(barcode,name,img);
                    let list_barcode = $("#list-barcode").val();
                    if(list_barcode == ""){
                        $("#list-barcode").val(barcode);
                    }
                    else{
                        list_barcode = list_barcode + "," + barcode;
                        $("#list-barcode").val(list_barcode);
                    }
                }
                $("#drop-down").hide();
                $(".discount-value").click(function(event){
                    $(event.delegateTarget).attr("style","background-color:#08f;color:white");
                    $(event.delegateTarget).siblings("div").attr("style","background-color:white;color:black");
                });

                $(".discount-percent").click(function(event){
                    $(event.delegateTarget).attr("style","background-color:#08f;color:white");
                    $(event.delegateTarget).siblings("div").attr("style","background-color:white;color:black");
                });
                $(".del-icon").click(function(event){
                    $(event.delegateTarget).parents("tr").remove();
                    let remove_barcode = $(event.delegateTarget).parents("tr").children("td.barcode").text();
                    let arr_barcode = $("#list-barcode").val().split(",");
                    let new_list = "";
                    let n = arr_barcode.length;
                    if(arr_barcode.length > 0){
                        for (let i = 0; i < n; i++){
                            let x = arr_barcode.pop();
                            if(x != remove_barcode){
                                if(new_list == ""){
                                    new_list = x;
                                }
                                else{
                                    new_list = new_list + "," + x;
                                }
                            }
                        }
                    }
                    $("#list-barcode").val(new_list);
                });
            });
            $(".line-type").click(function(event1){
                let type = $(event1.delegateTarget).children("span.type-txt").text();
                const a = $("#table-type").children().text().substr(38);
                const arr = a.split("delete");
                let exist = false;
                if(arr.length > 0){
                    for (let i = 0; i < arr.length; i++){
                        if(arr[i] == type){
                            exist = true;
                            break;
                        }
                    }
                }
                if(exist){
                    alert("Đã có trong danh Sản phẩm khuyến mại");
                }
                else if($(event1.delegateTarget).children("span.type-amount").text() == 0){
                    alert("Không có sản phẩm thuộc thể loại này. Hãy chọn lại");
                }else{
                    let list_type = $("#list-type").val();
                    if(list_type == ""){
                        $("#list-type").val(type);
                    }else{
                        list_type = list_type + ";" + type;
                        $("#list-type").val(list_type);
                    }
                    appendType(type);
                }
                $("#drop-down-type").hide();

                $(".del-icon").click(function(event){
                    $(event.delegateTarget).parents("tr").remove();
                    let type_remove = $(event.delegateTarget).parents("tr").children("td.type").text();
                    let arr_type = $("#list-type").val().split(";");
                    let new_list = "";
                    let n = arr_type.length;
                    if(arr_type.length > 0){
                        for (let i = 0; i < n; i++){
                            let x = arr_type.pop();
                            if(x != type_remove){
                                if(new_list == ""){
                                    new_list = x;
                                }
                                else{
                                    new_list = new_list + ";" + x;
                                }
                            }
                        }
                    }
                    $("#list-type").val(new_list);
                });
            });
            $(".discount-value").click(function(event){
                $(event.delegateTarget).attr("style","background-color:#08f;color:white");
                $(event.delegateTarget).siblings("div").attr("style","background-color:white;color:black");
                $("#unit").val($(event.delegateTarget).text());
                $("#discount").attr("onkeyup","format()");
                $("#discount").attr("type","text");
                $("#discount").removeAttr("max");
                $("#error-discount").hide();
                $("#div-discount").attr("style","margin-bottom:30px");
                $("#discount").val("1");
            });
            $(".discount-percent").click(function(event){
                $(event.delegateTarget).attr("style","background-color:#08f;color:white");
                $(event.delegateTarget).siblings("div").attr("style","background-color:white;color:black");
                $("#unit").val($(event.delegateTarget).text());
                $("#error-discount").hide();
                $("#div-discount").attr("style","margin-bottom:30px");
                $("#discount").val("1");
                $("#discount").attr("onkeyup","checkPercent()");
                $("#discount").attr("type","number");
                $("#discount").attr("max","100");

            });
        });

    </script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/discountAdd.css"/>

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
<div style="margin-left:200px">
    <div id="header">
        <div class="head-block-1">
            <a class="material-symbols-outlined back-ico back" href="list" >
                chevron_left
            </a>
            <a class="link-back back" href="list">
                Quay lại danh Sản phẩm khuyến mại
            </a>
        </div>
        <div class="head-block-3">
            <button class="but-save" type="submit" >
                Tạo
            </button>
        </div>
        <div class="head-block-2">
            <button class="but-cancel">
                Hủy
            </button>
        </div>

    </div>
    <div class="body">
        <form action="/discounts/create" method="post" >
            <p><button class="date-but" style="display:none">Submit</button></p>
            <div class="general-info">
                <div class="block-info-1 title box-shadow">
                    Thông tin chung
                </div>
                <div class="block-info-2 box-shadow">
                    <div>
                        <label for="product-name" class="label-color bold-title">Tên khuyến mại</label>
                        <p class="star-require">*</p>
                        <input type="text" class="input-general" id="product-name" name="discountName"/>
                        <span class="error-label"></span>
                    </div>
                    <div style="margin-bottom:30px" id="div-discount">
                        <label for="discount" class="label-color bold-title">Giảm giá</label>
                        <br>
                        <input type="text" class="input-general" id="discount" value="1" onkeyup="format()" required="required"
                               name = "discountValue" style="margin-bottom:0px" onkeydown="javascript: return ['Backspace','Delete','ArrowLeft','ArrowRight'].includes(event.code) ? true : !isNaN(Number(event.key)) && event.code!=='Space'"/>
                        <div class="unit-block discount-value" style="background-color:#08f;color:white">VNĐ</div>
                        <div class="unit-block discount-percent" align="center">%</div>
                        <input type="hidden" id="unit" name="discountUnit" value="VNĐ"/>
                        <br>
                        <span class="error-label" id="error-discount" style="display:none"></span>
                    </div>
                    <div>
                        <input type="hidden" id="method" name="discountMethod"/>
                        <div class="bold-title" style="display:inline-block">Phương thức khuyến mại</div>
                        <p class="star-require">*</p>
                        <div id="select" class="selector input-general">
                            <p id="type-value">Chọn phương thức</p>
                            <span class="material-symbols-outlined arrow-drop">
								arrow_drop_down
							</span>
                            <div class="box-dropdown box-shadow">
                                <div class="line-select">Khuyến mại theo từng sản phẩm</div>
                                <div class="line-select">Khuyến mại theo loại sản phẩm</div>
                            </div>
                        </div>
                        <span class="error-label" id="error-type" style="display:none">Chọn phương thức</span>
                    </div>
                </div>
                <div id="method-product">
                    <div class="method-choose-product">
                        <div class="search-bar">
                            <span class="material-symbols-outlined search-icon">search</span>
                            <input type ="text" class = "search-input" id ="product-search" placeholder = "Chọn Sản phẩm"/>
                        </div>
                        <div id="drop-down" style="z-index:1">
                            <c:forEach  items="${products}" var="product">
                            <div class="f-product" >
                                <div class="f-product-block-1">
                                    <img src="${product.urlImage}" class="f-product-img" >
                                </div>
                                <div class="f-product-block-2">
                                    <p class="f-book-name">${product.productName}</p>
                                    <p class="f-book-barcode">${product.id}</p>
                                </div>
                                <div class="f-book-block-3">
                                    <p class="f-book-price">${product.newPrice}</p>
                                    <span style="margin: 5px 0px 0px 0px;color: #8797af">Số lượng: </span>
                                    <span class="f-book-amount">${product.quantity}</span>
                                </div>
                            </div>
                            </c:forEach>
                            <input type="text" id="list-barcode" name="discountProducts" required="required"/>
                        </div>
                        <div class="choose-all-div">
                            <input type="checkbox" class="all-book" id="check-book"/>
                            <span class="choose-all-txt"> Tất cả đầu Sản phẩm</span>
                        </div>
                    </div>
                    <table class="method-table" id="table-book">
                        <tr class="table-header">
                            <th style="width:0.5%" align="left">Số thứ tự</th>
                            <th style="width:7.3%" align="left">Ảnh</th>
                            <th style="width:50%" align="left">Tên Sản phẩm</th>
                            <th style="width:2%;padding:0px;"></th>
                        </tr>
                    </table>
                </div>
                <div id="method-type">
                    <div class="method-choose-type">
                        <div class="search-bar-type">
                            <span class="material-symbols-outlined search-icon">search</span>
                            <input class = "search-input" id="type-search" placeholder = "Chọn thể loại"/>
                        </div>
                        <div id="drop-down-type" style="z-index:1">
                            <c:forEach items="${productByCategorys}" var="category" >
                                <div class="line-type">
                                    <input type="hidden" value="${category.categoryId}">
                                    <span class="type-txt">${category.categoryName}</span>
                                    <span class="type-amount">${category.size}</span>
                                    <span  style="color: #8797af;float: right;">SL:</span>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <table class="method-table" id="table-type">
                        <tr class="table-header">
                            <th style="width:98%" align="left">Thể loại</th>
                            <th style="width:2%;padding:0px;"></th>
                        </tr>
                    </table>
                    <input type="text" name="discountTypes" id="list-type"/>

                </div>
            </div>

            <div class="sale-info">
                <div class="block-info-1 title box-shadow">
                    Thời gian áp dụng
                </div>
                <div class="block-info-2 box-shadow">
                    <div>
                        <label for="price" class="label-color bold-title">Ngày bắt đầu</label>
                        <p class="star-require">*</p>
                        <input type="date" class="input-general" id="discount-start" max="2050-01-01" name="discountStart"/>
                        <span class="error-label"></span>
                    </div>
                    <div>
                        <label for="price" class="label-color bold-title">Ngày kết thúc</label>
                        <p class="star-require">*</p>
                        <input type="date" class="input-general" id="discount-end" max="2050-01-01" name="discountEnd"/>
                        <span class="error-label"></span>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>