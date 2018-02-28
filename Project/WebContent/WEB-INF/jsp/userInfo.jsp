<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="date" class="java.util.Date"/>


<!DOCTYPE html>
<html lang="ja">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>ユーザ詳細</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

  </head>
  <body>
  <header>

	<div class="container">
	<ul class="nav justify-content-end">
	  <li class="nav-item">
	    <a class="nav-link active" href="userInfo.html">${userInfo.name}さん</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link disabled" href="LogoutServlet">ログアウト</a>
	  </li>
	</ul>
  </header>

    <div class="" style="padding:40px 20px 0">
      <div class="text-center">
        <h1>ユーザ情報詳細参照</h1>
      </div>
    </div>


    <div id="main" class="main">
      <div class="container" style="padding:20px 0">
        <div class="" style="padding:20px 0">
        </div>
        <form class="login form-horizontal" action="index.html" method="post">

          <div class="form-group container">
            <label class="control-ravel col-sm-2" for="id">ログインID</label>
            <div class="col-sm-4">
              ${detailUser.loginId}
            </div>
          </div>


          <div class="form-group container">
            <label class="control-ravel col-sm-2" >ユーザ名</label>
            <div class="col-sm-4">
              ${detailUser.name}
            </div>
          </div>

          <div class="form-group container">
            <label class="control-ravel col-sm-2" >生年月日</label>
            <div class="col-sm-4">
              <fmt:formatDate value="${detailUser.birthDate}" pattern="yyyy年MM月dd日" />
            </div>
          </div>

          <div class="form-group container">
            <label class="control-ravel col-sm-2">登録日時</label>
            <div class="col-sm-4">
              <fmt:parseDate  var="createDate" value="${detailUser.createDate}" pattern="yyyy-MM-dd HH:mm:ss.SSS" />
              <fmt:formatDate value="${createDate}" pattern="yyyy年MM月dd日" />
            </div>
          </div>

          <div class="form-group container">
            <label class="control-ravel col-sm-2">更新日時</label>
            <div class="col-sm-4">
              <fmt:parseDate  var="updateDate" value="${detailUser.updateDate}" pattern="yyyy-MM-dd HH:mm:ss.SSS" />
              <fmt:formatDate value="${updateDate}" pattern="yyyy年MM月dd日" />
            </div>
          </div>

        </form>


    <ul class="nav justify-content-end">
	  <li class="nav-item">
	    <a class="nav-link active" href="UserListServlet">戻る</a>
	  </li>
	  </ul>
	  </div>
	  </div>



    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/bootstrap.min.js"></script>

  </body>
</html>