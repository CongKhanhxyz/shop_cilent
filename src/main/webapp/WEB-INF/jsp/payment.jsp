<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/payment2.css"/>

    <title>Thông tin thanh toán</title>
</head>

<body>
<div class="header">
    <div class="logo">
        <img src="image/ghost-castle (1).png" alt="">
    </div>
    <div class="status">
        <div class="title">
            <span>Đăng Nhập</span> <span>Địa chỉ giao hàng</span> <span>Thanh
					toán và đặt mua</span>
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
<div class="message">

</div>
<form class="payments">
    <h1>1. Chọn hình thức giao hàng</h1>
    <div class="delivery">
        <div class="delivery-main">
            <div class="delivery-form">
                <div class="order">
                    <div class="wrap-product">
                        <c:forEach items="${carts}" var="item">
                            <div style="display: flex; align-items: center; justify-content: flex-start; margin-bottom: 5px" class="">
                                <img class="urlImage" style="margin-right: 10px" src="${item.urlImage}" alt="">
                                <div class="name">
                                    <p>${item.productName}</p>
                                    SL: x<span class="quantity${item.productId}">${item.quantity}</span>
                                    <input class="productId${item.productId}" type="hidden" name="productId" value="${item.productId}">
                                    <span class="product-price${item.productId}">${item.newPrice}</span>
                                    <span style="margin-left: 5px">Tổng</span>
                                    <span
                                        style="margin-left: 20px !important;" class="money${item.productId}">
                                        ${item.quantity * item.newPrice}
                                    </span>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="ship" style="width: 20%">
                        <p>Được giao bởi TikiNOW Smart Logistics (giao từ Hà Nội)</p>
                        <p>Nhận hàng vào ngày: ${date}</p>
                        <a href=""><span>TIKI</span><span>FAST</span></a>
                    </div>
                    <div class="free" style="width: 20%">
                        <p>Phí giao hàng</p>
                        <span>15,000</span>
                    </div>
                </div>
            </div>
            <h1 style="margin-top: 5px">2.Chọn hình thức thanh toán</h1>
            <div class="container">
                <div class="container-item">
                    <label><input type="radio" name="methodPayment"
                                  value="tiền mặt" id="pay"><span></span></label>
                    <a href=""> <img src="https://frontend.tikicdn.com/_desktop-next/static/img/icons/checkout/icon-payment-method-cod.svg" alt="">
                    </a>
                    <p>Thanh toán khi nhận hàng</p>
                </div>
                <div class="container-item">
                    <label><input type="radio" name="methodPayment"
                                  value="tài khoản" id=""><span></span></label>
                    <a href=""> <img style="width: 30px; height: auto;"
                                     src="https://salt.tikicdn.com/ts/upload/76/80/08/62e0faf2af2869ba93da5f79a9dc4c4b.png"
                                     alt="">
                    </a>
                    <p>Thanh toán bằng ViettelPay</p>
                </div>
            </div>
            <div class="btn-pay">
                <button type="submit">Đặt hàng</button>
            </div>
        </div>
        <div class="customer-info">
            <div class="customer-info-item">
                <div>
                    <span>Địa chỉ giao hàng</span> <a>Sửa</a>
                </div>
                <h3>${sessionScope.address.fullnameRecive}</h3>
                <p style="margin-bottom: 8px;">${sessionScope.address.detailAddress}</p>
                <p>Điện thoại: ${sessionScope.address.detailAddress}</p>
            </div>
            <div class="customer-info-item">
                <div>
                    <span>Shop khuyến mại</span> <span>Có thể chọn 1 trong hai</span>
                </div>
                <a class="free" href="#"><em class="fas fa-percentage"></em>Chọn
                    hoặc nhập khuyến mãi</a>
                <div id="discount-tag">
					<span style="display: flex;background: #e6effa;padding:10px">
						<span id="discountSelectId">KM1:</span>
						<span style="margin-left:20px" id="discountSelectValue">10%</span>
					</span>
                    <span class="material-symbols-outlined cancel-but-red">
						close
					</span>
                </div>
            </div>
            <div class="customer-info-item order">
                <div>
                    <div class="amount">
                        <p>Đơn hàng</p>
                        <span><span>${total}</span> sản phẩm<a href="">Xem thông tin</a></span>
                        <span class="drop"></span>
                    </div>
                    <a>Sửa</a>
                </div>
                <div class="detail">
                </div>
                <div class="price">
                    <span>Tạm tính</span> <span id="totalFirst">0</span>
                </div>
                <div class="price">
                    <span><em class="fas fa-truck"></em> Phí vận chuyển</span> <span id="ship">15,000</span>
                </div>
                <div class="price">
                    <span>Giảm giá</span> <span id="discount-value" style="color: #fd6872">-${sessionScope.freeShipValue}</span>
                </div>
                <div class="price bottom">
                    <span>Thành tiền</span> <span id="last-total-price">${total + 1 - free}</span>
                </div>
            </div>
        </div>
    </div>
    <input type="text" name="listProductId" value="${sessionScope.listProductIds}">
    <input type="hidden" name="userId" value="${sessionScope.jwtResponse.id}">
    <input type="hidden" name="addressId" value="${sessionScope.address.addressId}">

</form>
<div class="sub-background">
    <div class="sub-tab ">
        <div>
            <h2>Chọn khuyến mại</h2>
            <span class="material-symbols-outlined close-icon">
                          close
                        </span>
            <div class="box-shadow div-discount" style="display:block">
                <table id="table-discount">
                    <tr style="background-color: #f4f6f8;border-bottom:none">
                        <th style = "width:1%"></th>
                        <th style = "width:10%;margin-left: 20px" align="left">Tên khuyến mại</th>
                        <th style = "width:15%" align="left">Phương thức khuyến mại</th>
                        <th style = "width:25%" align="left">Đối tượng</th>
                        <th style = "width:10%">Giảm giá</th>
                    </tr>
                    <c:forEach items="${discounts}" var="discount">
                        <tr class="discount-line">
                            <td><input type="radio" value="${discount.discountId}" class="radio" name="discount"/></td>
                            <td align="left">${discount.name}</td>
                            <td align="left">${discount.method}</td>
                            <td align="left">
                                <input value="${discount.listProductId}" id="listProductId">
                            </td>
                            <td align="center">
                                <span class="discountValue">${discount.value}</span>
                                <span class="discountUnit">${discount.unit}</span>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
    $('.btn-pay button').on('click', (e) => {
        e.preventDefault();
        const data = $('.payments').serializeArray();
        const object = {}
        $.each(data, (index, value) => {
            console.log(index)
            object[value.name] = value.value
        })
        if (object.size < 5)
        {
            alert("Vui lòng chọn phương thức thanh toán");
        }
        if (object['addressId'].value = "")
        {
            alert("Vui lòng chọn địa chỉ");
        }
        const json = JSON.stringify(object)
        console.log(json)
        $.ajax({
            type: "POST",
            url: 'http://localhost:8080/api/orders/buy',
            contentType: 'application/json',
            data: json,
            success: (data) => {
                alert("Cập nhật thành công")
                window.location.href = "/orders"
            }
        });
    })



    $(document).ready(()=> {

        const checkAllCarts = $('.container-item-two .check input');
        const userId = $('input[name = "userId"]').val();
        const addressId = $('#addressId').val();


        function numberWithCommas(x) {
            return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        }
        // loadDiscount();
        $("#discount-tag").hide();
        $('.free').on('click',(e)=> {
            e.preventDefault();
            $(".sub-background").show();
        })
        $('.close-icon').click(function(){
            $(".sub-background").hide();
        });

        $(".cancel-but-red").click(function(){
            $("#discount-tag").hide();
            removeDiscount();
            $("input.radio").removeAttr("checked");
        });
        $('.discount-line').on('click', function(){
            removeDiscount();
            appendDiscount(this);
        });
        function loadPrice(){
            let total =  0
                let listProductId = $('input[name="productId"]');
                for (var i = 0; i < listProductId.length; i++) {
                    let productId = $(listProductId[i]).val();
                    console.log($('.quantity' + productId).text())
                    var price = Number($('.product-price' + productId).text().replace(/,/g, ""))
                    $('.money' + productId).text(
                        price
                        *($('.quantity' + productId).text())
                    )


                }
                for (var i = 0; i < listProductId.length; i++) {
                    let productId = $(listProductId[i]).val();
                    console.log(parseInt(Number($('.money' + productId).text().replace(/,/g, ""))))
                    total += parseInt(Number($('.money' + productId).text().replace(/,/g, "")));
                }
            console.log(total)

            $('#totalFirst').text(numberWithCommas(total));
        }
        loadPrice();
        function loadLastPrice()
        {
            let total =  Number($('#totalFirst').text().replace(/,/g, ""))
            let discount = Number($('#free-discount').text().replace(/,/g, ""))
            let ship = Number($("#ship").text().replace(/,/g, ""))
            let total1 = total - ship;
            $('#last-total-price').text(numberWithCommas(total1 + discount));

        }
        loadLastPrice();

        function appendDiscount(event){
            const discount_line = $(event);
            const td = discount_line.children("td")
            const radio = td.children("input.radio");
            const value = td.children("span.discountValue").text();
            const unit = td.children("span.discountUnit").text();
            const method = td.eq(2).text();
            var discountName = discount_line.find("td").eq(1).text();
            var discount = value + unit;
            // remvove check
            $("input.radio").removeAttr("checked");
            radio.attr("checked","checked");
            var maKM = radio.val();
            // them text
            $("#discountSelectId").text(discountName + ":");
            $("#discountSelectValue").text(discount);
            $(".sub-background").hide();
            $("#discount-tag").show();
            let  objectAmount =  td.children("#listProductId").val();
            const objectAmountModify = objectAmount.toString().substring(1, (objectAmount.toString().length - 1));
            objectAmountModify.split(",").forEach(
                obj =>
                {
                    let listProductId = $('input[name="productId"]');
                    for (var i = 0; i < listProductId.length; i++)
                    {
                        let productId = $(listProductId[i]).val();
                        // console.log(productId)
                        if (productId == parseInt(obj.trim()))
                        {
                            // if ($('input[name="productId"]').val() == productId)
                            // {
                            console.log($('.productId' + productId))
                            console.log($('.productId' + productId).find("span.product-price"))
                            let productIdAt = $('.productId' + productId).find("span.product-price")
                                var oldPrice =
                                    $(".product-price" + productId).text();
                                var discount;
                                if( unit == "%" ){
                                    discount = (Number(oldPrice) * Number(value)) / 100;
                                }else{
                                    discount = Number(value);
                                }
                                console.log(oldPrice)
                                console.log(discount)
                                $(".product-price" + productId).text(
                                    numberWithCommas(oldPrice - discount)
                                );
                                loadPrice()
                                loadLastPrice();

                        }
                    }
                }
            )
            loadLastPrice();
        }
        function removeDiscount(){
            $('#free-discount').text(0);
            loadLastPrice();
        }


    })

</script>
</body>
</html>