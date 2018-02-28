<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>


	<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="./css/common.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js">
    <!-- BootstrapのJS読み込み -->
    <script src="js/bootstrap.min.js"></script>
    <!-- レイアウトカスタマイズ用個別CSS -->

</head>
<body>

	<div class="container">

	<c:if test="${errMsg != null}" >
		    <div class="alert alert-danger" role="alert">
			  ${errMsg}
			</div>

	</c:if>

	<br>

	<h2 class="text-center">ログイン画面</h2>
	<form  action="LoginServlet" method="post">
  <div class="form-group">
    <label for="formGroupExampleInput">ログインID</label>
    <input type="text" name="loginId" id="inputLoginId" class="form-control" placeholder="ログインID" required autofocus>
  </div>
  <div class="form-group">
    <label for="formGroupExampleInput2">パスワード</label>
    <input type="password" name="password" id="inputPassword" class="form-control" placeholder="パスワード" required>
  </div>

	<br>

	<div class="button_center">
	<button class="btn btn-lg btn-primary  btn-signin" type="submit">ログイン</button>
	</div>
	</form>

</div>
</body>
</html>