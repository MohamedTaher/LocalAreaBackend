package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificationModel {
	
	NotificationAction ob;
	
	public NotificationModel(NotificationAction ob){
		this.ob=ob;
		
	}
	
	public void Notify(int TO,int chID,int from){
		ob.Notify(TO,chID,from);
	}
	
   public static String getMyLikeNotification(int id){
	   try {
			Connection conn = DBConnection.getActiveConnection();
            String sql="Select * from likeNotefication where toUserID=" + id +";";
			PreparedStatement stmt;
			String s="";
			int fromid;
			
			stmt = conn.prepareStatement(sql);
			//stmt.setString(1, id+"");
			//stmt.executeUpdate();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				 id= rs.getInt("fromUserID");
				 s+=id+"Like your checkin number";
				 
			}
			return s;
	       }catch (SQLException e) {
	  	// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	   
   }
	
   public static String getMyCommentsNotification(int id){
	   try {
			Connection conn = DBConnection.getActiveConnection();
            String sql="Select * from commentNotefication where toUserID=" + id +";";
			PreparedStatement stmt;
			String s="";
			int fromid;
			
			stmt = conn.prepareStatement(sql);
			//stmt.setString(1, id+"");
			//stmt.executeUpdate();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				 id= rs.getInt("fromUserID");
				 s+=id+"comment in your checkin";
				 
			}
			return s;
	       }catch (SQLException e) {
	  	// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	   
   }
   

}
