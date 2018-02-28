<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ削除確認</title>
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./css/common.css">
</head>
<body>
<div class="container">
	<ul class="nav justify-content-end">
	  <li class="nav-item">
	    <a class="nav-link active" href="UserInfoServlet">${userInfo.name}さん</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link disabled" href="LogoutServlet">ログアウト</a>
	  </li>
	</ul>

<h2 class="text-center">ユーザ削除確認</h2>

ログインID：${detailUser.loginId} を<br>
本当に削除してよろしいでしょうか。

<br>
<br>
<br>


<div class="button_center">
    <form  action="UserDeleteServlet" method="post">
	<input type="hidden" name="id" value="${detailUser.id}">
	<button class="btn btn-danger" type="submit">OK</button>
	</form>
	<br>
    <a class="btn btn-primary" href ="UserListServlet">キャンセル</a>


</div>

    <div class="col-sm">
    </div>
</div>

</body>
</html>