package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import model.BoardDTO;


@WebServlet("/board/del")
public class BoardDelContorller extends HttpServlet {
//location 무조건 get, ?주고하는거 무조건 get

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String seq = request.getParameter("seq");
		//System.out.println("seq : " + seq);
		//dao객체에서 db대푯값을 가지고 특정행을 삭제한 후에 삭제가 성공하면 1리턴, 아니면 -1리턴, 리턴값에 따라 if문으로 결과반환
		
		BoardDTO param = new BoardDTO();
		param.setSeq(Integer.parseInt(seq));
		
		int result = BoardDAO.deleteBoard(param);
		System.out.println("result : "+result);
		//성공하면1, 실패시-1 or 0
		
		//page보낼때
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/commonMsg.jsp");
		String msg = "삭제에 성공했습니다.";
		String url = "/main";
		if(result !=1) {//실패
			msg="삭제에 실패했습니다.";
		}
		request.setAttribute("url", url);
		request.setAttribute("msg", msg);
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
