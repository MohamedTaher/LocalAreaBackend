package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class Comment {
	int checkInID;
	int userID;
	int id;
	String desc;
	String userName;

	
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCheckInID(int CheckInID){
		this.checkInID=CheckInID;
	}
	public int getCheckInID(){
		return this.checkInID;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public static String Do(int userID,int CheckinID,String desc){
		try {
			int id=6;
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Insert into comments (userID,checkinID,description) VALUES  ("+userID+","+CheckinID+",'"+desc+"');";
			System.out.println(sql);
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//stmt.setString(4, desc);
			stmt.executeUpdate();
			ResultSet res=stmt.getGeneratedKeys();
			if(res.next()){
				return "done";
			}
			
			
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "SQL error";
		}
		return "done";
	}
	
	public static String UnDo(int commentID){
		try {
			Connection con = DBConnection.getActiveConnection();
			String sql = "DELETE FROM comments WHERE id = " + commentID +";";
			PreparedStatement stmt = con.prepareStatement(sql);
			//stmt.setInt(1, userID);
			//stmt.setInt(2, CheckinID);
			//stmt.executeUpdate();
			//ResultSet res=stmt.getGeneratedKeys();
			int state = stmt.executeUpdate(sql);
			if(state != 0) {
				return "done";
			} else {
				return "error";
			}
			
			
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "SQL error";
		}
		//return null;
	}
	
	
	
	public static ArrayList<Comment> getComments(int checkinId){
		ArrayList<Comment> comments=new ArrayList<Comment>();
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Select * from comments, users where `checkinID` = "+checkinId+" and users.id=userID;";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				 Comment comment = new Comment();
				 comment.setDesc(rs.getString("description"));
				 comment.setCheckInID(rs.getInt("id"));
				 comment.setUserID(rs.getInt("userID"));
				 comment.setUserName(rs.getString("name"));
				 comments.add(comment);
			}
			return comments;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
	
	
	}

}
