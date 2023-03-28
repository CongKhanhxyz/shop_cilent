<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Order</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
            crossorigin="anonymous">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="css/profile.css">
    <link rel="stylesheet" href="css/myOrder.css">
    <link rel="stylesheet" href="/css/font-awesome.min.css">
    <script>

        // $(document).ready(function(){



        // });
    </script>
    <style>
        img{
            height:150px;
            width:120px;
        }
        #avatar-img{
            display: none;
        }
        .label-color{
            color:#46515F;
        }

        .choose-img{
            margin-top: 10px;
            margin-left: 40%;
            padding: 40px 40px 40px 46px;
            border: 1px dashed black;
            width: 18px;
            cursor: pointer;
        }
        .div-avatar-img{
            position: relative;
            width: fit-content;
            margin-left: 40%;
        }
        .cancel-but-red{
            position: absolute;
            cursor: pointer;
            top: -5px;
            right: -7px;
            color: white;
            background: red;
            border-radius: 20px;
            font-size: 14px;
            padding: 3px 2px 2px 2px;
            display: none;
        }
        .filter-info{
            float: left;
            width: 30%;
            min-width: 300px;
            margin-right: 2%;
            margin-bottom: 30px;
        }
        .bold-title{
            font-weight:bold;
            margin-bottom: 10px;
            color: #46515F;
        }

    </style>
</head>

<body>
<div class="header"></div>
<div class="main">
    <div class="main-left">
        <ul>
            <li><img src="icons/resume.png" alt=""><a href="/">Home
            </a></li>
            <li><img src="icons/resume.png" alt=""><a href="/users">Tài
                khoản của tôi</a></li>
            <li><img src="icons/package.png" alt=""><a style="color: #ee4d2d;" href="/orders/view">Đơn
                mua</a></li>
            <li><img src="icons/bell.png" alt=""><a href="/discount">Thông
                báo</a></li>
            <li><img src="icons/log-out.png" alt=""><a href="/acccount/sign-out">Đăng
                xuất</a></li>
        </ul>
    </div>
    <div class="main-right">
        <div class="container rounded bg-white">
            <form method="post" action="/users/update"  id="form" class="row" enctype="multipart/form-data">
                <div class="col-md-3 border-right">
                    <input type="hidden"  name="userId" id="user-id"
                           value="${user.userId }">
                    <div
                            class="d-flex flex-column align-items-center text-center p-3 py-5">
                        <img id="avatar" class="rounded-circle mt-5" width="150px"
                             src="${user.urlImageAvatar}"><span class="font-weight-bold">${user.lastname}</span><span
                            class="text-black-50">${user.username }</span><span> </span>
                    </div>
                </div>
                <div class="col-md-5 border-right">
                    <div class="p-3 py-5">
                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <h4 class="text-right">Thông tin cá nhân</h4>
                        </div>
                        <div class="row mt-2">
                            <div class="col-md-6">
                                <label class="labels">Họ</label><input type="text" id="firstname"
                                                                         class="form-control" name="firstname"
                                                                         placeholder="Nhập vào họ"
                                                                         value="${user.firstname }">
                            </div>
                            <div class="col-md-6">
                                <label class="labels">Tên</label><input type="text" id="lastname"
                                                                            class="form-control" name="lastname"
                                                                            value="${user.lastname }"
                                                                            placeholder="Nhập vào tên">
                            </div>
                        </div>
                        <div class="row mt-3">
                            <div class="col-md-12">
                                <label class="labels">Số điện thoại</label><input type="text" id="phone"
                                                                                  class="form-control" name="phone"
                                                                                  placeholder="Nhập vào số điện thoại"
                                                                                  value="${user.phone }">
                            </div>

                            <div class="col-md-12">



                                <label class="labels">Tỉnh/Thành Phố</label>
                            <select name="province"
                                    class="selectpicker show-tick form-control" id="province">
                                <c:forEach items="${provinces}" var="province">
                                    <option data-id="${province.id}">${province.name}</option>
                                </c:forEach>
                            </select>
                            </div>
                            <div class="col-md-12">
                                <label class="labels">Huyện/Quận</label> <select name="district"
                                    class="selectpicker show-tick form-control" id="district">
                            </select>
                            </div>
                            <div class="col-md-12">
                                <label class="labels">Xã/Phường</label> <select name="ward"
                                    class="selectpicker show-tick form-control" id="ward">

                            </select>
                            </div>
                        </div>
                        <div class="mt-5 text-center">
                            <input type="submit"  value="Lưu Thông Tin"
                            >
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="p-3 py-5">
                        <div class="mb-3">
                            <p class="label-color bold-title">Ảnh Đại Diện</p>
                            <input id="hidden-input" style="display:none" name="urlImageAvatar"
                                   type="file" accept="image/gif, image/jpeg, image/png" />
                            <div class="choose-img" id="choose-img" >
                                +
                            </div>
                            <div class ="div-avatar-img">
                                <span class="material-symbols-outlined cancel-but-red">
                                    close
                                </span>
                                        <img id="avatar-img" alt="error"/>
                            </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/LoadAddress.js"></script>


</body>
</html>