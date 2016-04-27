package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.plaf.synth.SynthSeparatorUI;

import sun.net.www.protocol.mailto.MailToURLConnection;

import com.mysql.jdbc.Statement;

public class UserModel {

	
	private String name;
	private String email;
	private String pass;
	private Integer id;
	private Double lat;
	private Double lon;
	
	
	public String getPass(){
		return pass;
	}
	
	public void setPass(String pass){
		this.pass = pass;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public static UserModel addNewUser(String name, String email, String pass) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Insert into users (`name`,`email`,`password`) VALUES  (?,?,?)";
		    

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, name);
			stmt.setString(2, email);
			stmt.setString(3, pass);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				UserModel user = new UserModel();
				user.id = rs.getInt(1);
				user.email = email;
				user.pass = pass;
				user.name = name;
				user.lat = 0.0;
				user.lon = 0.0;
				return user;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	
	public static UserModel login(String email, String pass) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Select * from users where email = ? and password = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, pass);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				UserModel user = new UserModel();
				user.id = rs.getInt(1);
				user.email = rs.getString("email");
				user.pass = rs.getString("password");
				user.name = rs.getString("name");
				user.lat = rs.getDouble("lat");
				user.lon = rs.getDouble("long");
				return user;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static boolean updateUserPosition(Integer id, Double lat, Double lon) {
		try{
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Update users set lat = ? , long = ? where id = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, lat);
			stmt.setDouble(2, lon);
			stmt.setInt(3, id);
			stmt.executeUpdate();
			return true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public static UserModel getPosition(String email) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Select * from users where email ='" +email + "';";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
//			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				UserModel user = new UserModel();
				user.id = rs.getInt(1);
				user.email = rs.getString("email");
				user.pass = rs.getString("password");
				user.name = rs.getString("name");
				user.lat = rs.getDouble("lat");
				user.lon = rs.getDouble("long");
				return user;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	
	public static Integer emailToID(String email) {
		try {
			Connection con = DBConnection.getActiveConnection();
			String sql = "select id from users where email='" + email+"';";
			PreparedStatement stmt;
			stmt = con.prepareStatement(sql);
//			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				return Integer.parseInt(rs.getString("id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public static String follow(Integer followerID, String followedEmail) {
		try {
			Integer followedID = emailToID(followedEmail);
			Connection con = DBConnection.getActiveConnection();
			String sql = "INSERT INTO follow VALUES("+followerID+", "+followedID+");";
			PreparedStatement stmt = con.prepareStatement(sql);
//			stmt.setInt(1, followerID);
//			stmt.setInt(2, followedID);
			int rs = stmt.executeUpdate(sql);
			if(rs == 1) {
				return "OK";
			} else {
				return "NO";
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return "SQL Error";
		}
	}

	
	
	public static String unfollow(Integer followerID, String followedEmail) {
		try {
			Integer followedID = emailToID(followedEmail);
			Connection con = DBConnection.getActiveConnection();
			//DELETE FROM follow where follower = ? and followedID = ?;
			String sql = "DELETE FROM follow WHERE follower = " + followerID + " and followed = " + followedID + ";";
			PreparedStatement stmt = con.prepareStatement(sql);
//			stmt.setInt(1, (int)followerID);
//			stmt.setInt(2, (int)followedID);
			System.out.println(stmt.toString());
			int state = stmt.executeUpdate(sql);
			System.out.println("state " + state);
//			ResultSet rs = stmt.executeQuery();
//			System.out.println(rs.toString());
			if(state != 0) {
				return "OK";
			} else {
				return "NO";
			}
		} catch(SQLException e) {
			e.printStackTrace();
//			Integer followedID = emailToID(followedEmail);
//			System.out.println(followedID  + " " + followedEmail);
			return "SQL Error";
		}
	}
	
	
        public static ArrayList<UserModel> Getfollowers(String id) {
		ArrayList<UserModel> follwers=new ArrayList<UserModel>();
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Select * from follow where `followed` = "+id;///
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
//			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				UserModel user = new UserModel();
				user.id = rs.getInt(1);
				//user.email = rs.getString("email");
				follwers.add(user);
				
			}
			return follwers;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
        
//    public static ArrayList<UserModel> Getmyfollowers(String id) {
//		ArrayList<UserModel> follwers=new ArrayList<UserModel>();
//		try {
//			Connection conn = DBConnection.getActiveConnection();
//			String sql = "Select * from follow where `follower` = "+id;///
//			PreparedStatement stmt;
//			stmt = conn.prepareStatement(sql);
////			stmt.setString(1, id);
//			ResultSet rs = stmt.executeQuery(sql);
//			while (rs.next()) {
//				UserModel user = new UserModel();
//				user.id = rs.getInt(1);
//				//user.email = rs.getString("email");
//				follwers.add(user);
//				
//			}
//			return follwers;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
        
    


	
	

	public static UserModel  search(int id){
		try {
			Connection conn = DBConnection.getActiveConnection();
            String sql="Select * from users where id=" + id +";";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			//stmt.setString(1, id+"");
			//stmt.executeUpdate();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				UserModel user = new UserModel();
				user.id = rs.getInt(1);
				user.email =rs.getString("email");
				user.pass = null;
				user.name = rs.getString("name");
				user.lat = rs.getDouble("lat");
				user.lon = rs.getDouble("long");
				return user;
			}
			return null;
	       }catch (SQLException e) {
	  	// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
		
	}
    public static ArrayList<UserModel> searchByName(String name) {
	ArrayList<UserModel> users=new ArrayList<UserModel>();
	try {
		Connection conn = DBConnection.getActiveConnection();
		String sql = "Select * from users where name ='"+name+"';";
		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql);
		//stmt.setString(1, name);
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			UserModel user = new UserModel();
			user.id = rs.getInt("id");
			user.email = rs.getString("email");
			user.name=rs.getString("name");
			user.lat = rs.getDouble("lat");
			user.lon = rs.getDouble("long");
			
			
			users.add(user);
			
		}
		return users;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	//return null;
}

public static String savePlace(int userID,int placeID){
    	try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Insert into userPlaces(userID,plaseID) values ("+userID+","+placeID+");";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			//stmt.setInt(1, userID);
			//stmt.setInt(2,placeID);
			
			
			int rs = stmt.executeUpdate(sql);
			if(rs == 1) {
				return "done";
			} else {
				return "erorr";
			}
		       } catch(SQLException e) {
			        e.printStackTrace();
			        return "SQL Error";
		}		
			
    }
}
