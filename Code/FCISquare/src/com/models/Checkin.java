package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Checkin {
	private String description;
	private int placeID;
	private int userID;
	private int likes;
	
	public static String createCheckin(String description,int userID, int placeID){
		String temp = "";
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Insert into checkins (description,userID,placeID,likes)"
					+ "VALUES  ('"+description+"',"+userID+","+placeID+",0);";
			temp += sql;
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
			return "Success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "SQL ERROR !!\n" + temp;
		}
	}
	
	/*
	 * class diagram
	 * Inheritance relation between userAction check in class 
	 * */
	
	/*
	 * do();
	 * undo();
	 * */
}
