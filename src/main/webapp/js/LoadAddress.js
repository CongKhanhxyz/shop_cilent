(function ($) {
    'use strict';
    function readIMG(input) {
        if (input.files[0]) {
            var reader = new FileReader(); // hệ thống

            reader.onload = function (e) {
                $('#avatar-img').attr("src", e.target.result);
            }
            reader.readAsDataURL(input.files[0]);// hàm hệ thống
        }
    }
    $(document).ready(function() {
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
            $("#avatar-img").show();
            $(".cancel-but-red").show();
        });

        $(".cancel-but-red").click(function(){
            $("#avatar-img").hide();
            $(".cancel-but-red").hide();
            $(".choose-img").show();
        });
    })
    $('#province').on('change', () => {

        const provinceId = $("#province").find(":selected").data('id');
        let html
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: 'http://localhost:8080/api/district?provinceId='+provinceId,
            success: (data) => {
                console.log(data)
                $('#district option').remove();
                for (var i = 0; i < data.length; i++)
                {
                    html += `<option data-id="${data[i].id}" <c:if ></c:if>${data[i].name}</option>`
                }
                $('#district').append(html)
            }
        })
    })
    $('#province').on('click', () => {

        const provinceId = $("#province").find(":selected").data('id');
        let html
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: 'http://localhost:8080/api/district?provinceId='+provinceId,
            success: (data) => {
                console.log(data)
                $('#district option').remove();
                for (var i = 0; i < data.length; i++)
                {
                    html += `<option data-id="${data[i].id}" <c:if ></c:if>${data[i].name}</option>`
                }
                $('#district').append(html)
            }
        })
    })
    $('#district').on('change', () => {

        const districtId = $("#district").find(":selected").data('id');
        let html
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: 'http://localhost:8080/api/addresses/ward?districtId='+districtId,
            success: (data) => {
                console.log(data)
                $('#ward option').remove();
                for (var i = 0; i < data.length; i++)
                {
                    html += `<option data-id="${data[i].id}">${data[i].name}</option>`
                }
                $('#ward').append(html)
            }
        })
    })
    $('#district').on('click', () => {

        const districtId = $("#district").find(":selected").data('id');
        let html
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: 'http://localhost:8080/api/addresses/ward?districtId='+districtId,
            success: (data) => {
                console.log(data)
                $('#ward option').remove();
                for (var i = 0; i < data.length; i++)
                {
                    html += `<option data-id="${data[i].id}">${data[i].name}</option>`
                }
                $('#ward').append(html)
            }
        })
    })
    // $('#saveProfile').on('click', () => {
    //     const province = $("#province").find(":selected").text();
    //     const district = $("#district").find(":selected").text();
    //     const ward = $("#ward").find(":selected").text();
    //     const address = ward + ' ' + province + ' ' + district;
    //     const userId = $('#user-id').data('id')
    //     const json = {
    //         'userId': userId,
    //         'firstname' : $('#firstname').val(),
    //         'lastname' : $('#lastname').val(),
    //         'phone' : $('#phone').val(),
    //         'address' : address,
    //         'urlImageAvatar': $('#avatar-img').text()
    //     }
    //     $.ajax({
    //         type: 'PUT',
    //         contentType: 'application/json',
    //         url: 'http://localhost:8080/api/users',
    //         data: JSON.stringify(json),
    //         success: (data) => {
    //             alert(data)
    //         }
    //     })
    // })
})(jQuery);