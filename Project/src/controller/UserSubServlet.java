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


@WebServlet("/UserSubServlet")
public class UserSubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public UserSubServlet() {
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



		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");

		request.setAttribute("check","");

	    //loginUserのセッションが残っているか
	    Object loginUser = session.getAttribute("userInfo");
	    if(loginUser!=null) {

	       RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userSub.jsp");
           dispatcher.forward(request,response);
           return;
	    }

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

	    //新規登録
        String loginId = (String)request.getParameter("loginId");
        String pass =(String)request.getParameter("pass");
        String passConfirm =(String)request.getParameter("passConfirm");
        String name =(String)request.getParameter("name");
        String birthDate =(String)request.getParameter("birthDate");


        boolean check = UserDao.userInsert(loginId,pass,passConfirm,name,birthDate);


        if(check) {
        	response.sendRedirect("UserListServlet");

           return;
        }else {
        	   request.setAttribute("check","NG");
        	   request.setAttribute("errMsg", "入力した内容は正しくありません");
        	   RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userSub.jsp");
           dispatcher.forward(request,response);
           return;
        }

	}

}
