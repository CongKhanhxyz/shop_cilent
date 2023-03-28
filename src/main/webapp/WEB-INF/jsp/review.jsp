<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="css/reviews.css">
    <title>Đánh giá sản phẩm</title>
</head>
<body>
<h2 id="fh2">Chúng tôi đánh giá cao phản hồi của bạn</h2>

<form id="feedback" action="/reviews/save" method="post"  enctype="multipart/form-data"  >
    <div class="pinfo">Thông tin cá nhân </div>

    <div class="form-group">
        <div class="col-md-4 inputGroupContainer">
            <div class="input-group">
                <input type="hidden" id="orderId" name="orderId" value="">
            </div>
        </div>
    </div>


    <div class="pinfo">Đánh giá sản phẩm .</div>


    <div class="form-group">
        <div class="col-md-4 inputGroupContainer">
            <div class="input-group">
                <span class="input-group-addon"><em class="fa fa-heart"></em></span>
                <input name="vote" placeholder="Nhập vào số sao">
            </div>
        </div>
    </div>

    <div class="pinfo"> Để lại phải hồi của bạn.</div>
    <div class="form-group">
        <div class="col-md-4 inputGroupContainer">
            <div class="input-group">
                <span class="input-group-addon"><em class="fa fa-pencil"></em></span>
                <textarea class="form-control" id="review" rows="3" name="content"></textarea>
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-4 inputGroupContainer">
            <div class="input-group">
                <div class="file-upload">
                    <button class="file-upload-btn" type="button" onclick="$('.file-upload-input').trigger( 'click' )">
                        Chọn 1 ảnh
                    </button>
                    <div class="image-upload-wrap">
                        <input class="file-upload-input" name="urlImage" id="file" type='file' onchange="readURL(this);"
                               accept="image/gif, image/jpeg, image/png" multiple />
                        <div class="drag-text">
                            <i class="fa fa-download" aria-hidden="true"></i>
                            <h3>Kéo vả thả một file hoặc chọn một ảnh</h3>
                        </div>
                    </div>
                    <div class="file-upload-content">
                        <img class="file-upload-image" src="#" alt="your image"/>
                        <div class="image-title-wrap">
                            <button type="button" onclick="removeUpload()" class="remove-image">Xóa <span
                                    class="image-title">Uploaded Image</span></button>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <input type="text" name="productId">

    <button id="submit"  class="btn btn-primary">Gửi</button>
</form>
<script class="jsbin" src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script>
    function readURL(input) {
        if (input.files && input.files[0]) {

            const reader = new FileReader();

            const formData = new FormData()

            reader.onload = function (e) {
                $('.image-upload-wrap').hide();

                $('.file-upload-image').attr('src', e.target.result);
                $('.file-upload-content').show();

                $('.image-title').html(input.files[0].name);
            };

            reader.readAsDataURL(input.files[0]);
        } else {
            removeUpload();
        }
    }

    function removeUpload() {
        $('.file-upload-input').replaceWith($('.file-upload-input').clone());
        $('.file-upload-content').hide();
        $('.image-upload-wrap').show();
    }

    $('.image-upload-wrap').bind('dragover', function () {
        $('.image-upload-wrap').addClass('image-dropping');
    });
    $('.image-upload-wrap').bind('dragleave', function () {
        $('.image-upload-wrap').removeClass('image-dropping');
    });


    $(document).ready(()=> {
        var url   = $(location).attr('href')
        var orderId = url.substring(url.indexOf('=') + 1, url.indexOf('&'))
        $('#orderId').val(orderId);
        var productId = url.substring(url.indexOf('&') + 1, url.length)
        var productIdLast = productId.substring(productId.indexOf('=') + 1, productId.length)
        $('input[name="productId"]').val(productIdLast)
    })
</script>
</body>
</html>