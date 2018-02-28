<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ情報更新</title>
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./css/common.css">
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
	    <a class="nav-link active" href="">${userInfo.name}さん</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link disabled" href="LogoutServlet">ログアウト</a>
	  </li>
	</ul>
	<h2 class="text-center">ユーザ情報更新</h2>


	<form  action="UserSub2Servlet" method="post">

	<input type="hidden" name="id" value="${detailUser.id}">

  <div class="form-group">
    <label for="formGroupExampleInput">ログインID</label>
    <input type="text"  name="loginId"  class="form-control" id="formGroupExampleInput" placeholder="" value="${detailUser.loginId}">
  </div>
  <div class="form-group">
    <label for="formGroupExampleInput2">パスワード</label>
    <input type="password" name="pass" class="form-control" id="formGroupExampleInput2" placeholder="" >
  </div>
  <div class="form-group">
    <label for="formGroupExampleInput2">パスワード（確認）</label>
    <input type="password" name="passConfirm" class="form-control" id="formGroupExampleInput3" placeholder="">
  </div>
  <div class="form-group">
    <label for="formGroupExampleInput2">ユーザ名</label>
    <input type="text" name="name" class="form-control" id="formGroupExampleInput4" placeholder="" value="${detailUser.name}">
  </div>
  <div class="form-group">
    <label for="formGroupExampleInput2">生年月日</label>
    <input type="date" name="birthDate" class="form-control" id="formGroupExampleInput5" placeholder="" value="${detailUser.birthDate}">
  </div>


	<br>
	<div class="button_center">
	<button class="btn btn-success"  type="submit">更新</button>
	</div>

	<ul class="nav justify-content-end">
	  <li class="nav-item">
	    <a class="nav-link active" href="UserListServlet">戻る</a>
	  </li>

	</ul>
</form>
</div>

</body>
</html>