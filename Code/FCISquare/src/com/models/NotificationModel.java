package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NotificationModel {
	
	NotificationAction ob;
	
	public NotificationModel(NotificationAction ob){
		this.ob=ob;
		
	}
	
	public void Notify(int TO,int chID,int from){
		ob.Notify(TO,chID,from);
	}
	
   public static ArrayList<ArrayList<Integer>> getMyLikeNotification(int id){
	   try {
		    ArrayList<Integer>checkinsID=new ArrayList();
		    ArrayList<Integer>fromids=new ArrayList();
		    ArrayList<ArrayList<Integer>> result=new ArrayList();
		    Checkin checkin;
		    int fromid,chinid;
			Connection conn = DBConnection.getActiveConnection();
            String sql="Select * from likeNotefication where toUserID=" + id +";";
			PreparedStatement stmt;
			String s="";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				fromid= rs.getInt("fromUserID");
				chinid= rs.getInt("chINID");
				checkin=Checkin.getCheckinByID(chinid);
				checkinsID.add(chinid);
				fromids.add(fromid);
				s+=fromid+"Like your checkin number"+checkin;
				 
			}
			result.add(checkinsID);
			result.add(fromids);
			return result;
	       }catch (SQLException e) {
	  	// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("errrrrrrrrrrrror");
		return null;
	}
	   
   }
	
   public static ArrayList<ArrayList<Integer>> getMyCommentsNotification(int id){
	   try {
		    ArrayList<Integer>checkinsID=new ArrayList();
		    ArrayList<Integer>fromids=new ArrayList();
		    ArrayList<ArrayList<Integer>> result=new ArrayList();
		    Checkin checkin;
		    int fromid,chinid;
			Connection conn = DBConnection.getActiveConnection();
            String sql="Select * from commentNotefication where toUserID=" + id +";";
			PreparedStatement stmt;
			String s="";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				fromid= rs.getInt("fromUserID");
				chinid= rs.getInt("chINID");
				checkin=Checkin.getCheckinByID(chinid);
				checkinsID.add(chinid);
				fromids.add(fromid);
				s+=fromid+"comment on your checkin number"+checkin;
				 
			}
			result.add(checkinsID);
			result.add(fromids);
			return result;
	       }catch (SQLException e) {
	  	// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("errrrrrrrrrrrror");
		return null;
	}
	   
   }

}
