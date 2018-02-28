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


@WebServlet("/UserInfoServlet")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public UserInfoServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//：ログインセッションがない場合、ログイン画面に遷移させる
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("userInfo");


		if(user == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("LoginServlet");
			dispatcher.forward(request, response);
		}



		request.setAttribute("check","");
		request.setCharacterEncoding("UTF-8");
    	    String detailId = (String)request.getParameter("id");


	    //loginUserのセッションが残っているか
	    Object loginUser = session.getAttribute("userInfo");
	    if(loginUser!=null&&detailId!=null) {

	    	//DBからユーザーの情報を取得
		    UserDao userDao = new UserDao();
		    User detailUser = userDao.findByUserInfo(detailId);



		    request.setAttribute("detailUser", detailUser);

	        RequestDispatcher dispatcher = request.getRequestDispatcher(
				      "/WEB-INF/jsp/userInfo.jsp");
            dispatcher.forward(request,response);
            return;
	    }

	    response.sendRedirect("UserListServlet");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
