package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Conn {
//	public static void main(String[] args) {
//		try {
//			Conn.getCon();
//			System.out.println("연결성공");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	//연결용 //final된거는 대문자로 적어야됨
	public static Connection getCon() throws Exception {
		final String DB_URL = "jdbc:mysql://localhost:4406/mento?serverTimezone=UTC";
		final String DB_ID = "root";
		final String DB_PASSWORD = "hj1206";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(DB_URL,DB_ID,DB_PASSWORD);
		return con;
	}
	
	// 자원 회수용
	
	//Connection -> db연결용
	//PreparedStatement -> db쿼리 실행용
	//ResultSet -> 쿼리결과 있다면 반환하는용(조회에만 사용됨, select문에 사용된다.)
	
	public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

