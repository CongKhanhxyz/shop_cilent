
$(document).ready(() => {

    function numberWithCommas(x) {
        return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
    function loadPrice(){
        if ($('.check.all input').is(":checked")) {
            let total =  0
            count = checkAllCarts.length;
            const moneys = $(`.listCartItems p.money`);
            for (let i = 0; i < moneys.length; ++i) {
                total += parseInt($(moneys[i]).text());
            }

            if (total > freeTwo) {
                freeShip = 30000;
                $('.processbar .one').css({
                    'width': '100%',
                    'background-color': '#5ed084'
                })
                $('.pay-item .frees span').text(`-${numberWithCommas(freeShip)}`);
                $('.processbar p span').text(`+ ${numberWithCommas(freeShip)}`);
            }
            else if (total > freeOne) {
                freeShip = 10000;
                $('.processbar .one').css({
                    'width': '50%',
                    'background-color': '#5ed084'
                })
                $('.pay-item .frees span').text(`-${numberWithCommas(freeShip)}`);
                $('.processbar p span').text(`+ ${numberWithCommas(freeShip)}`);
            }
            $('.btn .count').text(`${count}`);
            $('.pay .totalMoney').text(`${total}`);
            $('.pay .lastPrice').text(`${total - freeShip}`);
        }
        else {

            count = 0;
            $('.processbar .one').css({
                'width': '0%',
                'background-color': '#ccc'
            })
            total = 0;
            freeShip = 0;
            $('.processbar p span').text(`+ ${freeShip}`);
            $('.pay-item .frees span').text(`${freeShip}`);
            $('.btn .count').text(`${count}`);
            $('.pay .totalMoney').text(`${total}`);
            $('.pay .lastPrice').text(`Vui lòng chọn sản phẩm`);
        }
    }
    const freeOne = 100000;
    const freeTwo = 300000;
    let freeShip = 0;
    // khách hàng tuỳ chỉnh số lượng hàng
    const add = $('.amount .add');
    const sub = $('.amount .sub');
    const quantity = $('.amount .quantity');
    for (let i = 0; i < add.length; ++i) {
        let amount = $(quantity[i]).text();
        // check và xử lý khi tăng số lượng
        $(add[i]).on('click', () => {
            amount++;
            const productId = $(add[i]).data('id');
            const userId = $('input[name = "userId"]').val();
            $(quantity[i]).text(`${amount}`);
            const price = parseInt($($('.right .prices')[i]).text());
            $.ajax({
                method: "PUT",
                url: 'http://localhost:8080/api/carts?userId=' + userId + '&productId=' + productId
                    +'&amount=' + amount,
                success: (data) => {
                    // alert(data)
                    $($('.right p.money')[i]).text(`${amount * price}`);
                    loadPrice();
                }
            })
        });
        // check và xử lý khi giảm số lượng
        $(sub[i]).on('click', () => {
            amount--;
            if (amount < 0)
                amount = 0;
            const productId = $(add[i]).data('id');
            const userId = $('input[name = "userId"]').val();
            $(quantity[i]).text(`${amount}`);
            const price = parseInt($($('.right .prices')[i]).text());
            $.ajax({
                method: "PUT",
                url: 'http://localhost:8080/api/carts?userId=' + userId + '&productId=' + productId
                    +'&amount=' + amount,
                success: (data) => {
                    $($('.right p.money')[i]).text(`${amount * price}.0`);
                    loadPrice();

                }
            })

        });
    }

    // kiểm tra chọn từng phần tử một tính tiền cần phải thanh toán
    const checkAllCarts = $('.container-item-two .check input');
    let total = 0;
    let count = 0;
    for (let i = 0; i < checkAllCarts.length; ++i) {
        $(checkAllCarts[i]).on('change', () => {
            $('.check.all input').prop('checked', false);
            if ($(checkAllCarts[i]).is(":checked")) {
                let removeCart = $(checkAllCarts[i]).children("removeCart").val()
                if (i == 1) {
                    total += parseInt($($(`.listCartItems :nth-child(${i + 1}) p.money`)[1]).text());
                }
                else {
                    total += parseInt($(`.listCartItems :nth-child(${i + 1}) p.money`).text());
                }
                count++;
                if (total > freeTwo) {
                    freeShip = 30000;
                    $('.processbar .one').css({
                        'width': '100%',
                        'background-color': '#5ed084'
                    })
                    $('.pay-item .frees span').text(`-${numberWithCommas(freeShip)}`);
                }
                else if (total > freeOne) {
                    freeShip = 10000;
                    $('.processbar .one').css({
                        'width': '50%',
                        'background-color': '#5ed084'
                    })
                    $('.pay-item .frees span').text(`-${numberWithCommas(freeShip)}`);
                }
                else {
                    freeship = 0;
                }
                $('.processbar p span').text(`+ ${numberWithCommas(freeShip)}`);
                $('.btn .count').text(`${count}`);
                $('.pay .totalMoney').text(`${total}`);
                $('.pay .lastPrice').text(`${total - freeShip}`);
            }
            else {
                if (i == 1) {
                    total -= parseInt($($(`.listCartItems :nth-child(${i + 1}) p.money`)[1]).text());
                }
                else {
                    total -= parseInt($(`.listCartItems :nth-child(${i + 1}) p.money`).text());
                }
                count--;
                if (total > freeTwo) {
                    freeShip = 30000;
                    $('.processbar .one').css({
                        'width': '100%',
                        'background-color': '#5ed084'
                    })
                    $('.pay-item .frees span').text(`-${freeship}`);
                }
                else if (total > freeOne) {
                    freeShip = 10000;
                    $('.processbar .one').css({
                        'width': '50%',
                        'background-color': '#5ed084'
                    })
                    $('.pay-item .frees span').text(`-${numberWithCommas(freeShip)}`);
                }
                else {
                    freeShip = 0;
                    $('.processbar .one').css({
                        'width': '0%',
                        'background-color': '#ccc'
                    })
                    $('.pay-item .frees span').text(`${numberWithCommas(freeShip)}`);
                }
                $('.processbar p span').text(`+ ${numberWithCommas(freeShip)}`);
                $('.btn .count').text(`${count}`);
                $('.pay .totalMoney').text(`${total}`);
                $('.pay .lastPrice').text(`${total - freeShip}`);
            }
            if (total == 0) {
                $('.pay .lastPrice').text(`Vui lòng chọn sản phẩm`);
            }
        });
    }
    // kiểm tra check tất cả cartItems tính toán số tiền cần trả
    $('.check.all input').on('change',  () => {
        if ($('.check.all input').is(":checked")) {
            total = 0;
            count = checkAllCarts.length;
            const moneys = $(`.listCartItems p.money`);
            for (let i = 0; i < moneys.length; ++i) {
                total += parseInt($(moneys[i]).text());
            }
            for (let i = 0; i < checkAllCarts.length; ++i) {
                $(checkAllCarts[i]).prop('checked', true);
            }
            if (total > freeTwo) {
                freeShip = 30000;
                $('.processbar .one').css({
                    'width': '100%',
                    'background-color': '#5ed084'
                })
                $('.pay-item .frees span').text(`-${numberWithCommas(freeShip)}`);
                $('.processbar p span').text(`+ ${numberWithCommas(freeShip)}`);
            }
            else if (total > freeOne) {
                freeShip = 10000;
                $('.processbar .one').css({
                    'width': '50%',
                    'background-color': '#5ed084'
                })
                $('.pay-item .frees span').text(`-${numberWithCommas(freeShip)}`);
                $('.processbar p span').text(`+ ${numberWithCommas(freeShip)}`);
            }
            $('.btn .count').text(`${count}`);
            $('.pay .totalMoney').text(`${total}`);
            $('.pay .lastPrice').text(`${total - freeShip}`);
        }
        else {
            for (let i = 0; i < checkAllCarts.length; ++i) {
                $(checkAllCarts[i]).prop('checked', false);
            }
            count = 0;
            $('.processbar .one').css({
                'width': '0%',
                'background-color': '#ccc'
            })
            total = 0;
            freeShip = 0;
            $('.processbar p span').text(`+ ${numberWithCommas(freeShip)}`);
            $('.pay-item .frees span').text(`${numberWithCommas(freeShip)}`);
            $('.btn .count').text(`${count}`);
            $('.pay .totalMoney').text(`${total}`);
            $('.pay .lastPrice').text(`Vui lòng chọn sản phẩm`);
        }
    })



    //xử lý khi người dùng thanh toán Purchase(mua hàng)
    let cartItemsID = [];
    const purchase = $('div.btn button.btn-pay');
    // $(purchase).on('mouseleave', () => {
    //     // $('div.btn a.btn-pay').attr('href', 'PayMent?id=');
    // })
    $(purchase).on('mouseover', (e) => {
        e.preventDefault();
        cartItemsID = [];// reset lại mảng id trước mỗi lần click
        //kiểm tra xem những phần tử nào đc check
        for (let i = 0; i < checkAllCarts.length; ++i) {
            if ($(checkAllCarts[i]).is(":checked")) {
                cartItemsID.push($(removeCartItem[i]).data('id'));
            }
        }
        if(cartItemsID.length > 0)
        {
            console.log(cartItemsID)
            $('input[name = "productIds"]').val(cartItemsID)

            if(parseInt($('.pay-item .frees span').text().substring(1)) > 0)
            {
                // href += '&free=';
                // href += $('.pay-item .frees span').text().substring(1);
                // $('input[name = "freeShip"]').val($('.pay-item .frees span').text();
                let x = Number($('.pay-item .frees span').text().replace(/,/g, "")).toString().length;
                $('input[name = "freeShip"]').val(
                    Number($('.pay-item .frees span').text().replace(/,/g, "")).toString().substring(1, x));

            }
            // $('input[name = "ogrinalPrice"]').val($('.totalMoney').text()
            // $('div.btn a.btn-pay').attr('href', href);

        }
    });
    $(purchase).on('click', (e) => {
        e.preventDefault();
        cartItemsID = [];// reset lại mảng id trước mỗi lần click
        //kiểm tra xem những phần tử nào đc check
        for (let i = 0; i < checkAllCarts.length; ++i) {
            if ($(checkAllCarts[i]).is(":checked")) {
                cartItemsID.push($(removeCartItem[i]).data('id'));
            }
        }
        if(cartItemsID.length > 0)
        {
            $('#btn-pay').click();
        }
        else {
            alert("Yêu cầu chọn ít nhất 1 sản phẩm")
        }
    })
    // delete cart
    let removeCartItem = $('.removeCart');

    $('.check.all span').text(`${removeCartItem.length}`);
    for (let i = 0; i < removeCartItem.length; ++i) {
        $(removeCartItem[i]).on('click', (e) => {
            e.preventDefault();
            const userId = $('input[name = "userId"]').val();
            const productId = $(removeCartItem[i]).data('id');
            const addressId = $('#addressId').val();
            $.ajax({
                method: "DELETE",
                url: 'http://localhost:8080/api/carts?userId=' + userId + '&productId=' + productId,
                success: (data) => {
                    window.location.href = "/carts/"+ addressId;
                }
            })
        })
    }
});