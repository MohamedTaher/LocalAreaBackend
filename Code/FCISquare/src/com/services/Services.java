package com.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;

import com.models.DBConnection;
import com.models.UserModel;

@Path("/")
public class Services {

	/*
	 * @GET
	 * 
	 * @Path("/signup")
	 * 
	 * @Produces(MediaType.TEXT_HTML) public Response signUp(){ return
	 * Response.ok(new Viewable("/Signup.jsp")).build(); }
	 */

	@POST
	@Path("/signup")
	@Produces(MediaType.TEXT_PLAIN)
	public String signUp(@FormParam("name") String name,
			@FormParam("email") String email, @FormParam("pass") String pass) {
		UserModel user = UserModel.addNewUser(name, email, pass);
		JSONObject json = new JSONObject();
		json.put("id", user.getId());
		json.put("name", user.getName());
		json.put("email", user.getEmail());
		json.put("pass", user.getPass());
		json.put("lat", user.getLat());
		json.put("long", user.getLon());
		return json.toJSONString();
	}

	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_PLAIN)
	public String login(@FormParam("email") String email,
			@FormParam("pass") String pass) {
		UserModel user = UserModel.login(email, pass);
		JSONObject json = new JSONObject();
		json.put("id", user.getId());
		json.put("name", user.getName());
		json.put("email", user.getEmail());
		json.put("pass", user.getPass());
		json.put("lat", user.getLat());
		json.put("long", user.getLon());
		return json.toJSONString();
	}
	
	@POST
	@Path("/updatePosition")
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePosition(@FormParam("id") String id,
			@FormParam("lat") String lat, @FormParam("long") String lon) {
		Boolean status = UserModel.updateUserPosition(Integer.parseInt(id), Double.parseDouble(lat), Double.parseDouble(lon));
		JSONObject json = new JSONObject();
		json.put("status", status ? 1 : 0);
		return json.toJSONString();
	}
	
	
	
	@POST
	@Path("/getUserLastPosition")
	@Produces(MediaType.TEXT_PLAIN)
	public String getUserLastPosition(@FormParam("email") String email) {
		UserModel user = UserModel.getPosition(email);
		JSONObject json = new JSONObject();
		json.put("lat", user.getLat());
		json.put("long", user.getLon());
		return json.toJSONString();
	}
	
	
	@POST
	@Path("/followUser")
	@Produces(MediaType.TEXT_PLAIN)
	public String followUser(@FormParam("followerID") String followerID, @FormParam("followedEmail") String followedEmail) {
		String status = UserModel.follow(Integer.parseInt(followerID), followedEmail);
		JSONObject obj = new JSONObject();
		obj.put("status", status);
		return obj.toJSONString();
	}
	
	
	@POST
	@Path("/unfollowUser")
	@Produces(MediaType.TEXT_PLAIN)
	public String unfollowUser(@FormParam("followerID") String followerID, @FormParam("followedEmail") String followedEmail) {
		String status = UserModel.unfollow(Integer.parseInt(followerID), followedEmail);
		JSONObject obj = new JSONObject();
		obj.put("status", status);
		return obj.toJSONString();
	}
	
	
	
	@POST
	@Path("/getfollwers")
	@Produces(MediaType.TEXT_PLAIN)
	public String getfollwers(@FormParam("ID") String id) {
		ArrayList<UserModel> user = UserModel.Getfollowers(id);	
		ArrayList<JSONObject> jsonn = new ArrayList<JSONObject>();
		for(int i=0;i<user.size();i++){
		JSONObject jso =new JSONObject();
		jsonn.add(jso);
		jsonn.get(i).put("id", user.get(i).getId());
		//json.put("name", user.get(i).getName());
		//json.put("email", user.get(i).getEmail());;
		}
		JSONObject js=new JSONObject();
		js.put("list", jsonn);
		return js.toJSONString();
	}
	/*@POST
	@Path("/addfriend")
	@Produces(MediaType.TEXT_PLAIN)
	public String addFriend(@FormParam("IDf")String fromid,
			@FormParam("IDt")String toid){
		String status=UserModel.addFriend(fromid,toid);
		JSONObject jso =new JSONObject();
		jso.put("status", status);
		return jso.toJSONString();
		
	}*/
	/*@POST
	@Path("/accept")
	@Produces(MediaType.TEXT_PLAIN)
	public String add(@FormParam("uID")int userId,
			@FormParam("rID")int reqFromID){
		String status=UserModel.accept(userId,reqFromID);
		JSONObject jso =new JSONObject();
		jso.put("status", status);
		return jso.toJSONString();
		
	}*/
	
	@POST
	@Path("/search")
	@Produces(MediaType.TEXT_PLAIN)
	public String search(@FormParam("id")int id){
		UserModel user=UserModel.search(id);
		JSONObject json = new JSONObject();
		json.put("name", user.getName());
		json.put("email", user.getEmail());
		json.put("lat", user.getLat());
		json.put("long", user.getLon());
		System.out.println("DONE\n\n\n");
		return json.toJSONString();
		
		
	}
	@POST
	@Path("/getuserbyname")
	@Produces(MediaType.TEXT_PLAIN)
	public String ayhaga(@FormParam("name") String name) {
		ArrayList<UserModel> user = UserModel.searchByName(name);	
		ArrayList<JSONObject> jsonn = new ArrayList<JSONObject>();
		for(int i=0;i<user.size();i++){
		JSONObject jso =new JSONObject();
		jsonn.add(jso);
		jsonn.get(i).put("id", user.get(i).getId());
		jsonn.get(i).put("name", user.get(i).getName());
		jsonn.get(i).put("email", user.get(i).getEmail());
		jsonn.get(i).put("lat", user.get(i).getLat());
		jsonn.get(i).put("long", user.get(i).getLon());
		
		}
		JSONObject js=new JSONObject();
		js.put("list", jsonn);
		return js.toJSONString();
	}
	

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String getJson() {
		return "Hello after editing";
		// Connection URL:
		// mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/
	}
}
