package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class ActionModel {
  
	UserAction ob;
	
	public ActionModel(UserAction ob){
		this.ob=ob;
	}
	public String Undo(int uid,int chinID,String type){
		return ob.Undo(uid,chinID,type);
	}
	public static  void addAction(int userID,int chID ,String type){
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Insert into actions (userid,checkinid,type) VALUES  ("+userID+","+chID+",'"+type+"')";
			//System.out.println(sql);
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.executeUpdate();
			ResultSet res=stmt.getGeneratedKeys();
			if(res.next()){
				System.out.println("added to actions");
			}
			
			
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("sql error in actions");
		}
		System.out.println("added to actions");
	}

	public static ArrayList<ArrayList<String>> getMyActions(int userid){
		try {
		    ArrayList<String>checkinsID=new ArrayList();
		    ArrayList<String>type=new ArrayList();
		    ArrayList<ArrayList<String>> result=new ArrayList();
		    Checkin checkin;
		    int chinid;
		    String ty;
			Connection conn = DBConnection.getActiveConnection();
            String sql="Select * from actions where userid=" + userid +";";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				chinid= rs.getInt("checkinid");
				ty=rs.getString("type");
				checkin=Checkin.getCheckinByID(chinid);
				checkinsID.add(chinid+"");
				type.add(ty);
				 
			}
			result.add(checkinsID);
			result.add(type);
			return result;
	       }catch (SQLException e) {
	  	// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("errrrrrrrrrrrror");
		return null;
	
	}
		
	}
	public static  void removeAction(int userID,int chID ,String type){
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = " Delete from actions where userID =" +userID+" and checkinid = "+chID+" and type = '"+type+"';";
			//System.out.println(sql);
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.executeUpdate();
			ResultSet res=stmt.getGeneratedKeys();
			if(res.next()){
				System.out.println("Deleted from  actions");
			}
			
			
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("sql error in actions");
		}
		System.out.println("Deleted from  actions");
	}	
	
}

