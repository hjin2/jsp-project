package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDAO;
import model.BoardDTO;


@WebServlet("/board/detail")
public class BoardDetailController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		String id = (String)hs.getAttribute("loginUser");
		
		if(id==null) {
			response.sendRedirect("/login");
			return;
		}
		
		//////
	     String seq = request.getParameter("seq");
	     System.out.println("seq: "+seq);
	     
	     // 
	     int intSeq = Integer.parseInt(seq);
	     
	     BoardDTO param = new BoardDTO();
	     param.setSeq(intSeq);
	     
	     int updResult = BoardDAO.updateViewCnt(param);
	     BoardDTO result = BoardDAO.selectBoardOne(param);
	    
	     System.out.println(result);
	     
	     RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/boardDetail.jsp");
	     request.setAttribute("one", result);
	     
	     rd.forward(request, response); //114처럼 자체적으로 연결시켜주는거, 건네주기만해서 주소가 board
	     
	     

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
