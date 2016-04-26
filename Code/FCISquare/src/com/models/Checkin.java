package com.models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Checkin {
	private String description;
	private String date;
	private int placeID;
	private int userID;
	private int id;
	private int likes;
	private int comments;
	private PlaceModel checkinPlace;
	
	public PlaceModel getCheckinPlace() {
		return checkinPlace;
	}

	public void setCheckinPlace(PlaceModel checkinPlace) {
		this.checkinPlace = checkinPlace;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getPlaceID() {
		return placeID;
	}

	public void setPlaceID(int placeID) {
		this.placeID = placeID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}


	
	
	public String createCheckin(String description, Date date, int placeID){
		String temp = "";
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Insert into checkins (description,checkintime,userID,placeID)"
					+ "VALUES  ('"+description+"',"+date+","+userID+","+placeID+");";
			temp += sql;
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
			PlaceModel.incrementCheckinNumber(placeID);
			return "Success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "SQL ERROR !!\n" + temp;
		}
	}
	/*
	 * auto increment 
	 * add place id column
	 * */
	
	/*
	 * class diagram
	 * Inheritance relation between userAction check in class 
	 * */
	
	public static ArrayList<Integer>getCheckinsByID(int id) {
		ArrayList<Integer>checkins = null;
		try{
			checkins = new ArrayList<Integer>();
			Connection con = DBConnection.getActiveConnection();
			String sql = "select * from checkins where userID=" + id +";";
			PreparedStatement stmt;
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int tmp = rs.getInt("id");
				checkins.add(tmp);
			}
			return checkins;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static ArrayList<Integer> getFollwersCheckins(int id) {
		ArrayList<Integer> checkins = null;
		try{
			checkins = new ArrayList<Integer>();
			Connection con = DBConnection.getActiveConnection();
			String sql = "select id from checkins,follow where userID=followed and follower=" + id +";";
			PreparedStatement stmt;
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int tmp = rs.getInt("id");
				checkins.add(tmp);
			}
			return checkins;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static Checkin getCheckinByID(int id) { 
		Checkin checkin = null;
		try{
			checkin = new Checkin();
			Connection con = DBConnection.getActiveConnection();
			String sql = "select * from checkins where id=" + id + ";";
//			String sql = "select * from checkins, places where checkins.userID=" + id +" and places.id=placeID;";
			PreparedStatement stmt;
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				checkin.setComments(rs.getInt("comments"));
				checkin.setLikes(rs.getInt("likes"));
				checkin.setId(rs.getInt("id"));
				checkin.setDescription(rs.getString("description"));
				checkin.setDate(rs.getString("checkintime"));
				checkin.setPlaceID(rs.getInt("placeID"));
				checkin.setUserID(rs.getInt("userID"));
				checkin.setCheckinPlace(PlaceModel.getPlaceByID(checkin.getPlaceID()));
				return checkin;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<Checkin> sortCheckins(ArrayList<Checkin>checkins, Sort sorter) {
		return sorter.sort(checkins);
	}
	
	
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
	
	
	
	
}
