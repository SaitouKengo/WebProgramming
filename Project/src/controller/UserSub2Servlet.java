package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.User;


@WebServlet("/UserSub2Servlet")
public class UserSub2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public UserSub2Servlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//：ログインセッションがない場合、ログイン画面に遷移させる
		HttpSession sessions = request.getSession();

		User users = (User)sessions.getAttribute("userInfo");


		if(users == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("LoginServlet");
			dispatcher.forward(request, response);
		}



		 String detailId = (String)request.getParameter("id");

		UserDao userDao = new UserDao();
		    User UserSub2Servlet = userDao.findByUserInfo(detailId);



		    request.setAttribute("detailUser", UserSub2Servlet);

		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("userInfo");


		if(user.getName() == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}

		String id = (String) request.getAttribute("id");
		if(id == null) {
			id = request.getParameter("id");
		}

		User userInfo = userDao.loginIdData(id);

		request.setAttribute("userInfo", userInfo);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userSub2.jsp");
		dispatcher.forward(request, response);


	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



		request.setCharacterEncoding("UTF-8");

	    //更新
		String id = (String)request.getParameter("id");
        String loginId = (String)request.getParameter("loginId");
        String pass =(String)request.getParameter("pass");
        String passConfirm =(String)request.getParameter("passConfirm");
        String name =(String)request.getParameter("name");
        String birthDate =(String)request.getParameter("birthDate");


        boolean check = UserDao.userUpdate(id,loginId,pass,passConfirm,name,birthDate);


        if(check) {
        	response.sendRedirect("UserListServlet");


           return;


        }else {
        	   request.setAttribute("check","NG");
        	   request.setAttribute("errMsg", "入力した内容は正しくありません");
        	   RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userSub2.jsp");
           dispatcher.forward(request,response);

           return;
        }
	}
}
