$(document).ready(function () {
    $('.change').on('click', (e) => {
        e.preventDefault();
        var i = $(e.delegateTarget).children("input.addressId").data("id")
        // const addressId = $('#addressId' + i).data("id")
        $('.changeAddress').css({
            'display': 'flex'
        });
        $('.header').css({
            'background-color': '#efeeee'
        })
        $('.container').css({
            'background-color': '#efeeee'
        })

        $.ajax({
            type: "GET",
            url: 'http://localhost:8080/api/addresses/detail?addressId=' + parseInt(i),
            contentType: 'application/json',
            success: (data) => {
                // alert(data)

                var inputAddress = $("input[name='addressId']")
                inputAddress.val(inputAddress.val() + i);
                var inputname = $("input[name='fullnameRecive']");
                inputname.val(inputname.val() + data.fullnameRecive);
                var inputphone = $("input[name='phone']");
                inputphone.val(inputphone.val() + data.phone);
                // $("input[name='fullnameRecive']").value += data.fullnameRecive;
                // $("input[name='phone']").value += data.fullnameRecive;
                $("#province").append('<option>data.provinceName</option>')
                $("#district").append('<option>data.districtName</option>')
                $("#ward").append('<option>data.wardName</option>')
            }
        });
    });
    $('#add').on('click', (e) => {
        e.preventDefault();
        $('.changeAddress').css({
            'display': 'flex'
        });
        $('.header').css({
            'background-color': '#efeeee'
        })
        $('.container').css({
            'background-color': '#efeeee'
        })

    });

    const updateAddress = $('.btn .update-address');
    $(updateAddress).on('click', (e) => {
        e.preventDefault();
        const data = $('#form').serializeArray();

        const object = {}
        $.each(data, (index, value) => {
            console.log(index)
            object[value.name] = value.value
        })
        const json = JSON.stringify(object)
        console.log(json)
        $.ajax({
            type: "PUT",
            url: 'http://localhost:8080/api/addresses',
            contentType: 'application/json',
            data: json,
            success: (data) => {
                alert("Cập nhật thành công")
            }

        });
    })
    const saveAddress = $('.btn .save-address');
    $(saveAddress).on('click', (e) => {
        e.preventDefault();
        const data = $('#form').serializeArray();
        const object = {}
        $.each(data, (index, value) => {
            console.log(index)
            object[value.name] = value.value
        })
        const json = JSON.stringify(object)
        console.log(json)
        $.ajax({
            type: "POST",
            url: 'http://localhost:8080/api/addresses',
            contentType: 'application/json',
            data: json,
            success: (data) => {
                '<div class="address">'
                ' <div class="address-name">' +
                '  <h3>'
                + '<c:if test="${not empty addr.fullnameRecive}">' +data.fullnameRecive +'</c:if>' +
                '</h3>\n' +
                '  <p style="border: none !important;color: black;">Mặc định</p>\n' +
                '  </div>'
                '   <p class="content-add" id="address-detail">\n' +
                '                <span>' + '<c:if test="${not empty addr.detailAddress}">' +data.detailAddress +'</c:if>'
                + '</span>\n' +
                '                <c:if test="${ empty addr.detailAddress}">Vui lòng cập nhập địa chỉ giao hàng</c:if>\n' +
                '            </p>'
                ' <p>'+ "Điện thoại:" +'<c:if test="${not empty addr.phone}">\n' +
                data.phone + '</c:if>' +
                '</p>' +
                ' <a href="">Giao đến địa chỉ này</a>\n' +
                '            <a class="change" href="#"  >'+ Sửa +
                '                <input type="hidden" data-id="'+data.addressId+'" class="addressId">'+'</a>\n' +
                '            <a href="" style="margin-left: 110px;">Thiết lập mặc định</a>'
                + '</div>'
            }
        });
    })
    const addressDetails = $('.item.two .item-addressDetails');
    $(addressDetails).on('blur', () => {
        const proName = $('.item.two .province').val();
        const disName = $('.item.two .district').val();
        const wardName = $('.item.two .ghost').val();
        if (proName == null || disName == null || wardName == null) {
            // sử lý lỗi j đó trong đây
        } else {
            const addDetail = $(addressDetails).val() + wardName + ', ' + disName + ', ' + proName;
            $(addressDetails).val(`${addDetail}`);
        }
    })
})
