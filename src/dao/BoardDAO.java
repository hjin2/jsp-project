package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.Conn;
import model.BoardDTO;

public class BoardDAO {
	// 조회수 증가
	public static int updateViewCnt(BoardDTO param) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "UPDATE board_tb SET view_cnt = view_cnt + 1 WHERE seq = ?";
		
		try {
			con = Conn.getCon();//db연결
			ps = con.prepareStatement(sql);//쿼리연결
			ps.setInt(1, param.getSeq());//동적 쿼리 완성
			result = ps.executeUpdate(); //수행된 행을 리턴
			
		} catch (Exception e) {
			e.printStackTrace();
			result = -1;
		}
		
		return result;
	}
	
	
	
   // 게시물 삭제
	public static int deleteBoard(BoardDTO param) {
		// 성공1, 실패-1, 알수없는에러0
		int result=0;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "DELETE FROM board_tb WHERE SEQ=?";
		try {
			con = Conn.getCon(); //db연결
			ps = con.prepareStatement(sql); //쿼리연결
			ps.setInt(1, param.getSeq());//동적sql 완성
			result = ps.executeUpdate();//실행하는 부분
		} catch (Exception e) {
			e.printStackTrace();
			result = -1;
		}finally {
			Conn.close(con, ps, null);//반환..?
		}
		
		
		return result;
	}
   // 다오는 무조건 db랑 통신하는 객체
   // 게시물 수정
   public static int updateBoard(BoardDTO param) {
      //알 수 없는 에러0, 실패 -1, 성공 1
      int result = 0;
      Connection con = null; // db연결용
      PreparedStatement ps = null; // 쿼리 진행용
      String sql = "UPDATE board_tb SET title=?, writer=?, content=? WHERE SEQ = ?;";
      
      try {
         //db는 외부에 있는거라 통신하기위해서 자바 특성상 예외처리
         con = Conn.getCon();//db 연결
         ps = con.prepareStatement(sql); //쿼리연결
         ps.setString(1, param.getTitle());
         ps.setString(2, param.getWriter());
         ps.setString(3, param.getContent());
         ps.setInt(4, param.getSeq());
         // 위에 4개는 동적쿼리문에 param으로 받은거 연결 / ?에 채워넣음
         
         result = ps.executeUpdate();
         
      } catch (Exception e) {
         e.printStackTrace();
         result = -1;
      }finally {
         Conn.close(con, ps, null);
      }
      return result;
   }
   
   // 게시물 하나 가져오기
   // 리턴되는행이 Board dto 객체랑 똑같은 형태
   public static BoardDTO selectBoardOne(BoardDTO param) { // int param 해도  되는데 객체로 넣는게 좋음
      BoardDTO boardDto = new BoardDTO();
      
      Connection con = null; //db연결용
      PreparedStatement ps = null; // 쿼리 진행용
      ResultSet rs = null; //쿼리 결과 리턴용
      
      String sql = "SELECT * FROM board_tb WHERE SEQ = ?";
   
      
      try {
         con = Conn.getCon(); //db연결
         ps = con.prepareStatement(sql); //쿼리진행
         ps.setInt(1, param.getSeq());
         rs = ps.executeQuery(); // 쿼리 결과값 리턴
   
         if(rs.next()) {
            boardDto.setSeq(rs.getInt("seq"));
            boardDto.setTitle(rs.getString("title"));
            boardDto.setWriter(rs.getString("writer"));
            boardDto.setRegDt(rs.getString("reg_dt"));
            boardDto.setContent(rs.getString("content"));
            boardDto.setViewCnt(rs.getInt("view_cnt"));
         }
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
         Conn.close(con, ps, rs);
      }
      return boardDto;
   }
   
   // 게시글 가져오기
   public static List<BoardDTO> selectBoardList(){
      List<BoardDTO> list = new ArrayList<BoardDTO>(); // 그릇만들고
      
      
      // DB에서 가져옴
      Connection con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM board_tb ORDER BY SEQ DESC;";
      
      try {
         con = Conn.getCon(); // db연결용
         ps = con.prepareStatement(sql); // sql 진행용
         rs = ps.executeQuery(); // 쿼리결과 리턴
         while(rs.next()) {//rs객체의 투플의 수만큼 while이 온다.
            // 그릇선언
            BoardDTO boardDTO = new BoardDTO();
            // 데이터싣기
            boardDTO.setTitle(rs.getString("title"));
            boardDTO.setContent(rs.getString("content"));
            boardDTO.setRegDt(rs.getString("reg_dt")); // db컬럼명대로 적어줌
            boardDTO.setWriter(rs.getString("writer"));
            boardDTO.setSeq(rs.getInt("seq"));
            boardDTO.setViewCnt(rs.getInt("view_cnt"));
            
            list.add(boardDTO);
         }
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
         Conn.close(con, ps, rs);
      }

      // 그릇에 채우고
      return list; // 리턴하면 서버단으로 날라감
   }
   
   
   // 게시글 작성
   public static int insertBoard(BoardDTO param) { // static 원래 ㄴㄴ, 성공1 실패-1 알수없는에러 0
      int result = 0;
      Connection con = null; // db연결
      PreparedStatement ps = null; // 쿼리진행용
      
      //ResultSet rs = null; // 조회용이라 사용 안함
      
      String sql = "INSERT INTO board_tb(title,content,writer, reg_dt)VALUES(?,?,?,now())";
      
      try {
         con = Conn.getCon();
         ps = con.prepareStatement(sql);
         ps.setString(1, param.getTitle());
         ps.setString(2, param.getContent());
         ps.setString(3, param.getWriter());
         
         result = ps.executeUpdate();
      } catch (Exception e) {
         result = -1;
         e.printStackTrace();
      } finally {
         Conn.close(con, ps, null);
      }
      
      return result;
   }

}