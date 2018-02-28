<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ユーザ新規登録</title>
	<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="./css/common.css">
	<!-- Jqeryの読み込み -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js">
    <!-- BootstrapのJS読み込み -->
    <script src="js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
<c:if test="${errMsg != null}" >
		    <div class="alert alert-danger" role="alert">
			  ${errMsg}
			</div>

	</c:if>

	<ul class="nav justify-content-end">
	  <li class="nav-item">
	    <a class="nav-link active" href="userInfo.html">${userInfo.name}さん</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link disabled" href="LogoutServlet">ログアウト</a>
	  </li>
	</ul>

	<h2 class="text-center">ユーザ新規登録</h2>

 <form  action="UserSubServlet" method="post">
  <div class="form-group">
    <label for="formGroupExampleInput">ログインID</label>
    <input type="text" name="loginId"  class="form-control" id="formGroupExampleInput" placeholder="">
  </div>
  <div class="form-group">
    <label for="formGroupExampleInput2">パスワード</label>
    <input type="password" name="pass" class="form-control" id="formGroupExampleInput2" placeholder="">
  </div>
  <div class="form-group">
    <label for="formGroupExampleInput2">パスワード（確認）</label>
    <input type="password" name="passConfirm" class="form-control" id="formGroupExampleInput3" placeholder="">
  </div>
  <div class="form-group">
    <label for="formGroupExampleInput2">ユーザ名</label>
    <input type="text"name="name"  class="form-control" id="formGroupExampleInput4" placeholder="">
  </div>
  <div class="form-group">
    <label for="formGroupExampleInput2">生年月日</label>
    <input type="date" name="birthDate" class="form-control" id="formGroupExampleInput5" placeholder="">
  </div>

	<br>
	<div class="button_center">
	<button class="btn btn-primary"  type="submit">登録</button>
	</div>
 </form>

	<ul class="nav justify-content-end">
	  <li class="nav-item">
	    <a class="nav-link active" href="UserListServlet">戻る</a>
	  </li>
	</ul>

</div>

</body>
</html>