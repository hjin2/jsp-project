package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.UserDTO;


@WebServlet("/join")
public class JoinController extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 사용자 redirect
		 HttpSession hs = request.getSession();
	      String loginUser = (String) hs.getAttribute("loginUser"); //get은 항상 set이 있어서 생김
	      
	      
	      if(loginUser != null) {//로그인을 하지 않은 경우
	         response.sendRedirect("/main");
	         return;
	      }
	      
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/join.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// data를 받고
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		System.out.println("id :: " + id);
		System.out.println("pw :: " + pw);
		
		UserDTO userDTO = new UserDTO();
		userDTO.setUserID(id);
		userDTO.setUserPW(pw);
		
		// db에 넣는다.
		// mvc패턴..하는일에 따라서 따로따로 만들어서 쓰자
		int result = UserDAO.doJoin(userDTO);
		
		String msg = "가입에 성공했습니다.";
		String url = "/login";
		if(result !=1) {
			msg = "가입에 실패했습니다.";
			url="/join";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/commonMsg.jsp");
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		rd.forward(request, response);
	}

}
