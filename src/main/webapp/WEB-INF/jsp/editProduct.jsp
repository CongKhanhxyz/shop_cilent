<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>Thêm sách</title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script>
        function numberWithCommas(x) {
            return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        }
        function readIMG(input) {
            if (input.files[0]) {
                var reader = new FileReader(); // hệ thống

                reader.onload = function (e) {
                    $('#product-img').attr("src", e.target.result);
                }
                reader.readAsDataURL(input.files[0]);// hàm hệ thống
            }
        }
        function format(){
            var currentInput = $("#oldPrice").val();
            currentInput = currentInput.replace(/,/g, "");
            if(currentInput != ""){
                currentInput = parseInt(currentInput);
            }
            val = numberWithCommas(currentInput);
            $("#oldPrice").val(val);
        }
        $(document).ready(function(){

            $(".menu-bar").click(function(event){
                const locate = $(event.delegateTarget).children("span.menu-txt").text();
                if(locate == "Bán hàng"){
                    window.open("/receipt/add", '_blank');
                }
                let x = "Thông tin đã nhập sẽ không lưu. Xác nhận thoát?";
                if(confirm(x) == false){
                    e.preventDefault();// hàm hệ thống
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
                    window.location.href="booklist";
                }
            });
            $(".but-save").click(function(){
                $("#product-name").attr("required","required");
                $("#oldPrice").attr("required","required");
                $("#quantity").attr("required","required");
                if($("#product-name").val() != "" && $("#quantity").val() != "" && $("#oldPrice").val() != "" && $("#num-republish").val() != ""){
                    alert("Thêm thành công");
                    $("#submit").click();
                }
            });
            $(".selector").click(function(){
                $(".box-dropdown").slideToggle(300);
            });

            $(".option").click(function(event){
                const textType = $("#type-value").text($(event.delegateTarget).text());
                $("#categoryType").val($(event.delegateTarget).text());
            });

            $(".checkbox").click(function(){
                $(".tai-ban").slideToggle(500);
                if($(".checkbox").prop("checked")){
                    $("#num-republish").attr("required","required");
                }else{
                    $("#num-republish").removeAttr("required");
                }
                $("#num-republish").val(0);
            });
            $(".add-type").click(function(){
                $(".sub-background").show();
            });
            $(".close-icon").click(function(){
                $("#type-new").val(null);
                $(".sub-background").hide();
            });
            $("#choose-img").click(function(){
                $("#hidden-input").click();
            });
            $("#hidden-input").change(function(){
                $(".choose-img").hide();
                readIMG(this);
                $("#product-img").show();
                $(".cancel-but-red").show();
            });

            $(".cancel-but-red").click(function(){
                $("#product-img").hide();
                $(".cancel-but-red").hide();
                $(".choose-img").show();
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
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/productAdd.css"/>

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
            <a class="material-symbols-outlined back-ico back" href="booklist" >
                chevron_left
            </a>
            <a class="link-back back" href="/">
                Quay lại danh sách sản phẩm
            </a>
        </div>
        <div class="head-block-3">
            <button class="but-save">
                Sửa
            </button>
        </div>
        <div class="head-block-2">
            <button class="but-cancel">
                Hủy
            </button>
        </div>
    </div>
    <div class="body">
        <form action="/products/edit"  enctype="multipart/form-data"  method="post" >
            <button id="submit" style="display:none">submit</button>
            <div class="general-info">
                <div class="block-info-1 title box-shadow">
                    Thông tin chung
                </div>
                <div class="block-info-2 box-shadow">
                    <div>
                        <label for="product-name" class="label-color bold-title">Tên sản phẩm</label>
                        <p class="star-require">*</p>
                        <input class="input-general" value="${product.productName}" id="product-name" name="productName"/>
                        <span class="error-label"></span>
                    </div>
                    <div>
                        <label for="shortDescription" class="label-color bold-title">Mô tả ngắn</label>
                        <input class="input-general" value="${product.shortDescription}" id="shortDescription" name="shortDescription"/>


                    </div>
                    <p class="label-color bold-title">Ảnh sản phẩm</p>
                    <input id="hidden-input" style="display:none" name="urlImage"
                           value="${product.urlImage}"  type="file" accept="image/gif, image/jpeg, image/png" />
                    <div class="choose-img" id="choose-img" >
                        +
                    </div>
                    <div class ="div-product-img">
						<span class="material-symbols-outlined cancel-but-red">
							close
						</span>
                        <img id="product-img" src="${product.urlImage}" alt="error"/>
                    </div>
                </div>
            </div>
            <div class="sale-info">
                <div class="block-info-1 title box-shadow">
                    Thông tin bán hàng
                </div>
                <div class="block-info-2 box-shadow">
                    <div id="select" class="selector input-general">
                        <p id="type-value">Chọn loại hàng</p>
                        <input  type="hidden" name="categoryName" id="categoryType"/>
                        <span class="material-symbols-outlined arrow-drop">
							arrow_drop_down
						</span>
                        <div class="box-dropdown box-shadow">
                            <c:forEach items="${categoryList}" var="category" >
                                <div class="line-select option">${category.categoryName}</div>
                            </c:forEach>
                        </div>
                    </div>
                    <div>
                        <label for="oldPrice" class="label-color bold-title">Giá bán</label>
                        <input onkeyup="format()" type="text" value="${product.oldPrice}" class="input-general" id="oldPrice" name="oldPrice"
                               required="required" onkeypress="return (event.charCode !=8 && event.charCode ==0 || (event.charCode >= 48 && event.charCode <= 57))" />
                        <span class="error-label"></span>
                    </div>
                    <div>
                        <label for="quantity" class="label-color bold-title">Số lượng</label>
                        <input type="number" class="input-general" id="quantity" name="quantity" value="${product.quantity}"
                               onkeydown="javascript: return ['Backspace','Delete','ArrowLeft','ArrowRight'].includes(event.code) ? true : !isNaN(Number(event.key)) && event.code!=='Space'"/>
                        <span class="error-label"></span>
                    </div>
                    <div>
                        <label for="percent" class="label-color bold-title">% Giảm giá</label>
                        <input class="input-general" value="${product.percentDiscount}" id="percent" style="margin-bottom: 6px;" name="percentDiscount"/>
                        <span class="error-label"></span>
                    </div>
                    <div>
                        <label for="newPrice" class="label-color bold-title">Giá mới</label>
                        <input class="input-general" value="${product.newPrice}" id="newPrice" style="margin-bottom: 6px;" name="newPrice"/>
                        <span class="error-label"></span>
                    </div>
                </div>
            </div>
            <input type="text" name="productId" id="productId">
        </form>
    </div>
</div>
<script>
    $(document).ready(() => {
        var url = $(location).attr("href");
        var productId = url.substring(url.indexOf("=") + 1, url.length)
        console.log(productId)
        $('#productId').val(productId);
    })
</script>
</body>
</html>