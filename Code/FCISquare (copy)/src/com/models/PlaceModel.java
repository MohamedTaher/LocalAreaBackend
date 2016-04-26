package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class PlaceModel {
	private int id;
	private String name;
	private String description;
	private double lng, lat;
	private int userID;
	private int numberOfCheckins;
	private int rateSum;
	private int userNum;
	



	



	public PlaceModel(String name, String description, double lng, double lat) {
		super();
		this.name = name;
		this.description = description;
		this.lng = lng;
		this.lat = lat;
		this.rateSum=0;
		this.userNum=0;
		this.numberOfCheckins=0;
	}
	
	
	
	public PlaceModel() {
		// TODO Auto-generated constructor stub
	}


	public int getRateSum() {
		return rateSum;
	}



	public void setRateSum(int rateSum) {
		this.rateSum = rateSum;
	}



	public int getUserNum() {
		return userNum;
	}
	
	
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public double getLng() {
		return lng;
	}



	public void setLng(double lng) {
		this.lng = lng;
	}



	public double getLat() {
		return lat;
	}



	public void setLat(double lat) {
		this.lat = lat;
	}



	public int getNumberOfCheckins() {
		return numberOfCheckins;
	}



	public void setNumberOfCheckins(int numberOfCheckins) {
		this.numberOfCheckins = numberOfCheckins;
	}



	public int getUserID() {
		return userID;
	}



	public void setUserID(int userID) {
		this.userID = userID;
	}



	public static String addPlace(String name, String description, double lng, double lat, int userID){
		String temp = "";
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Insert into places (name,description,lng,lat,userID)"
					+ "VALUES  ('"+name+"','"+description+"',"+lng+","+lat+","+userID+");";
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
	
	public static ArrayList<PlaceModel> searchPlace(String name)
	{
		ArrayList<PlaceModel> places=new ArrayList<PlaceModel>();
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Select * from places where name ='"+name+"';";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			//stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				PlaceModel place = new PlaceModel();
				place.id = rs.getInt("id");
				place.name=rs.getString("name");
				place.description=rs.getString("description");
				place.lng = rs.getDouble("lng");
				place.lat = rs.getDouble("lat");
				place.userID = rs.getInt("userID");
				place.numberOfCheckins = rs.getInt("numberOfCheckins");
				place.rateSum = rs.getInt("rateSum");
				place.userNum = rs.getInt("userNum");
				places.add(place);
				
			}
			return places;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void ratePlace(int rate)
	{
		
	}



	public static PlaceModel getPlaceByID(int id) {
		try {
			PlaceModel place = new PlaceModel();
			Connection con = DBConnection.getActiveConnection();
			String sql = "select * from places where id=" + id +";";
//			System.out.println(sql);
			PreparedStatement stmt;
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				place.id = rs.getInt("id");
				place.name=rs.getString("name");
				place.description=rs.getString("description");
				place.lng = rs.getDouble("lng");
				place.lat = rs.getDouble("lat");
				place.userID = rs.getInt("userID");
				place.numberOfCheckins = rs.getInt("numberOfCheckins");
				place.rateSum = rs.getInt("rateSum");
				place.userNum = rs.getInt("userNum");
			}
			return place;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String  incrementCheckinNumber(int placeID){
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "update places set numberOfCheckins = numberOfCheckins + 1 where id = "+placeID+";";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
			return "Success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error";
		}
	}
	
	
	public static String ratePlace(int rate, int id) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "update places set rateSum = rateSum + " + rate +", userNum = userNum + 1 where id = "+id+";";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
			return "Success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error";
		}
	}

}
