<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ユーザー一覧</title>
	<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="./css/common.css">
    <!-- Jqeryの読み込み -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js">
    <!-- BootstrapのJS読み込み -->
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">

<ul class="nav justify-content-end">
  <li class="nav-item">
    <a class="nav-link active" href="">${userInfo.name}さん</a>
  </li>
  <li class="nav-item">
    <a class="nav-link disabled" href="LogoutServlet">ログアウト</a>
  </li>
</ul>


<h2 class="text-center">ユーザー一覧</h2>

<div class="button_right">
   <a class="btn btn-success" href="UserSubServlet">新規登録</a><br><br>
</div>

<form  action="UserListServlet" method="post">

  <div class="form-group">
    <label for="formGroupExampleInput">ログインID</label>
    <input type="text" class="form-control" name ="loginId" id="loginId" placeholder="">
  </div>
  <div class="form-group">
    <label for="formGroupExampleInput2">ユーザ名</label>
    <input type="text" class="form-control" name = "userName" id="userName" placeholder="">
  </div>
  <div class="form-group">
    <label for="formGroupExampleInput2">生年月日</label>
    <input type="date" class="form-control" name = "birthDate" id="birthDate" placeholder="">
  </div>


<br>
<div class="button_center">
<button class="btn btn-primary" type="submit">検索</button><br><br>
</div>
</form>

<br>
<div class="table-responsive">
             <table class="table table-striped table-bordered" class="table table-bordered">
               <thead>
                 <tr>
                   <th>ログインID</th>
                   <th>ユーザ名</th>
                   <th>生年月日</th>
                   <th></th>
                 </tr>
               </thead>
               <tbody>
               <c:forEach var="user" items="${userList}" >
                 <tr>
                   <td>${user.loginId}</td>
                   <td>${user.name}</td>
                   <td><fmt:formatDate value="${user.birthDate}" pattern="yyyy年MM月dd日"/></td>
                   <c:if test="${userInfo.loginId == 'admin'}">
                   <td>
                     <a class="btn btn-primary" href="UserInfoServlet?id=${user.id}">詳細</a>
                     <a class="btn btn-success" href="UserSub2Servlet?id=${user.id}">更新</a>
                     <a class="btn btn-danger" href ="UserDeleteServlet?id=${user.id}">削除</a>
                   </td>
                   </c:if>

                   <c:if test="${userInfo.loginId !='admin'}">
                   <td>
                     <a class="btn btn-primary" href="UserInfoServlet?id=${user.id}">詳細</a>
                     <c:if test="${userInfo.loginId == user.loginId}">
					 <a class="btn btn-success" href="UserSub2Servlet?id=${user.id}">更新</a>
					 </c:if>
					</td>
					</c:if>

                 </tr>
				</c:forEach>
               </tbody>
             </table>
           </div>

</div>
</body>
</html>