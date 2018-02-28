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



@WebServlet("/UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserDeleteServlet() {
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

    	//id取得
		String id = request.getParameter("id");


		UserDao userDao = new UserDao();
	    User UserDeleteServlet = userDao.findByUserInfo(id);



	    request.setAttribute("detailUser", UserDeleteServlet);

	HttpSession session = request.getSession();

	User user = (User)session.getAttribute("userInfo");


	if(user.getName() == null) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}


	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userDelete.jsp");
	dispatcher.forward(request, response);


}
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	request.setCharacterEncoding("UTF-8");


	String id = (String)request.getParameter("id");


	UserDao userDao = new UserDao();
    boolean check = userDao.userDelete(id);


    if(check) {
    	response.sendRedirect("UserListServlet");


       return;


    }else {
    	   request.setAttribute("check","NG");
    	   RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userSub2.jsp");
       dispatcher.forward(request,response);
       return;
    }


}
}