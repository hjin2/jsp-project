package model;

public class UserDTO { // Data Transfer Object DB에 넣고빼는 데이터
	private String userID;
	private String userPW;
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPW() {
		return userPW;
	}
	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}
	

}
