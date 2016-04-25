package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class CommentAction implements NotificationAction{

	public void Notify(int TO,int chID,int from){
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Insert into commentNotefication (toUserID,fromUserID,chINID) VALUES  ("+TO+","+from+","+chID+")";
			System.out.println(sql);
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.executeUpdate();
			ResultSet res=stmt.getGeneratedKeys();
			if(res.next()){
				System.out.println("added to comment noti");
			}
			
			
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("sql error in noti");
		}
		System.out.println("added to comment noti");
	}

	
	
}

