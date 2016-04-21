package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;

import com.mysql.jdbc.Statement;

public class Like {
int checkInID;
int userID;

public void setCheckInID(int CheckInID){
	this.checkInID=CheckInID;
}
public void setuserID(int userID){
	this.userID=userID;
}
public int getCheckInID(){
	return this.checkInID;
}
public int getuserID(){
	return this.userID;
}
public static String Do(int userID,int CheckinID){
	try {
		Connection conn = DBConnection.getActiveConnection();
		String sql = "Insert into likes (userID,checkinID) VALUES  ("+userID+","+CheckinID+")";
		System.out.println(sql);
		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
public static String Undo(int userID,int CheckinID){
	try {
		Connection con = DBConnection.getActiveConnection();
		String sql = "DELETE FROM likes WHERE userID = " + userID + " and checkinID = " + CheckinID + ";";
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
}