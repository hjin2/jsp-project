package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.Conn;
import model.UserDTO;

public class UserDAO { // DAO : Data Access Object (=db에 접근하는 객체)
	// 로그인 메서드
	public static int dologin(UserDTO param) {
		// 성공1 실패-1 알수없는에러0 쿼리문실패-2
		int result = 0;
		//select문
		Connection con = null; //db연결용
		PreparedStatement ps = null; //쿼리연결용
		ResultSet rs = null; //쿼리결과 반환용
		String sql = "SELECT * FROM user_tb WHERE user_id = ? AND user_pw = PASSWORD(?)";
		try {
			con=Conn.getCon();
			ps = con.prepareStatement(sql);//쿼리연결
			ps.setString(1, param.getUserID());
			ps.setString(2, param.getUserPW());
			rs = ps.executeQuery();//쿼리 실행결과 반환
			//rs안에는 db안에있는 행들이 있음
			//저번에 list뽑는 쿼리뽑는경우 while문으로 확인
			// 지금은 무조건 한개기때문에 if문으로 써서 깔끔
			if(rs.next()) {
				result = 1; //로그인 성공 - 최초 로그인 성공시점
			}else {
				result = -1; //로그인 실패(아이디 or 비밀번호가 불일치) 로그인실패가 에러가 아님
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = -2; // 로그인 에러
		}finally {
			Conn.close(con, ps, rs); //자원반환
		}
		
		return result;
	}
	
	
	//회원가입 메서드 (DB에 데이터 삽입 INSERT)
	public static int doJoin(UserDTO param) {
		// 성공1, 실패 -1, 알수없는에러 0
		int result = 0;
		
		Connection con = null; // db연결용
		PreparedStatement ps = null; // sql 쿼리용
		//ResultSet rs = null; // 반환용
		
		String sql = "INSERT INTO user_tb(USER_ID,USER_PW) VALUES(?, PASSWORD(?))";
		
	
		try {
			con = Conn.getCon();//db연결 성공
			ps = con.prepareStatement(sql);//쿼리매핑
			ps.setString(1, param.getUserID());
			ps.setString(2, param.getUserPW());
			result=ps.executeUpdate();//쿼리실행
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = -1;
		}finally {
			Conn.close(con, ps, null);
		}
		return result;
	
	
	
	}
	

}
