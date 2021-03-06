package com.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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

import com.models.*;

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
		if (user != null) {
			json.put("id", user.getId());
			json.put("name", user.getName());
			json.put("email", user.getEmail());
			json.put("pass", user.getPass());
			json.put("lat", user.getLat());
			json.put("long", user.getLon());
		}
		return json.toJSONString();
	}

	@POST
	@Path("/updatePosition")
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePosition(@FormParam("id") String id,
			@FormParam("lat") String lat, @FormParam("long") String lon) {
		Boolean status = UserModel.updateUserPosition(Integer.parseInt(id),
				Double.parseDouble(lat), Double.parseDouble(lon));
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
	public String followUser(@FormParam("followerID") String followerID,
			@FormParam("followedEmail") String followedEmail) {
		String status = UserModel.follow(Integer.parseInt(followerID),
				followedEmail);
		JSONObject obj = new JSONObject();
		obj.put("status", status);
		return obj.toJSONString();
	}

	@POST
	@Path("/unfollowUser")
	@Produces(MediaType.TEXT_PLAIN)
	public String unfollowUser(@FormParam("followerID") String followerID,
			@FormParam("followedEmail") String followedEmail) {
		String status = UserModel.unfollow(Integer.parseInt(followerID),
				followedEmail);
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
		for (int i = 0; i < user.size(); i++) {
			JSONObject jso = new JSONObject();
			jsonn.add(jso);
			jsonn.get(i).put("id", user.get(i).getId());
			// json.put("name", user.get(i).getName());
			// json.put("email", user.get(i).getEmail());;
		}
		JSONObject js = new JSONObject();
		js.put("list", jsonn);
		return js.toJSONString();
	}

	// @POST
	// @Path("/getmyfollwers")
	// @Produces(MediaType.TEXT_PLAIN)
	// public String getmyfollwers(@FormParam("ID") String id) {
	// ArrayList<UserModel> user = UserModel.Getfollowers(id);
	// ArrayList<JSONObject> jsonn = new ArrayList<JSONObject>();
	// for(int i=0;i<user.size();i++){
	// JSONObject jso =new JSONObject();
	// jsonn.add(jso);
	// jsonn.get(i).put("id", user.get(i).getId());
	// //json.put("name", user.get(i).getName());
	// //json.put("email", user.get(i).getEmail());;
	// }
	// JSONObject js=new JSONObject();
	// js.put("list", jsonn);
	// return js.toJSONString();
	// }

	/*
	 * @POST
	 * 
	 * @Path("/addfriend")
	 * 
	 * @Produces(MediaType.TEXT_PLAIN) public String
	 * addFriend(@FormParam("IDf")String fromid,
	 * 
	 * @FormParam("IDt")String toid){ String
	 * status=UserModel.addFriend(fromid,toid); JSONObject jso =new
	 * JSONObject(); jso.put("status", status); return jso.toJSONString();
	 * 
	 * }
	 */
	/*
	 * @POST
	 * 
	 * @Path("/accept")
	 * 
	 * @Produces(MediaType.TEXT_PLAIN) public String add(@FormParam("uID")int
	 * userId,
	 * 
	 * @FormParam("rID")int reqFromID){ String
	 * status=UserModel.accept(userId,reqFromID); JSONObject jso =new
	 * JSONObject(); jso.put("status", status); return jso.toJSONString();
	 * 
	 * }
	 */

	@POST
	@Path("/search")
	@Produces(MediaType.TEXT_PLAIN)
	public String search(@FormParam("id") int id) {
		UserModel user = UserModel.search(id);
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
		for (int i = 0; i < user.size(); i++) {
			JSONObject jso = new JSONObject();
			jsonn.add(jso);
			jsonn.get(i).put("id", user.get(i).getId());
			jsonn.get(i).put("name", user.get(i).getName());
			jsonn.get(i).put("email", user.get(i).getEmail());
			jsonn.get(i).put("lat", user.get(i).getLat());
			jsonn.get(i).put("long", user.get(i).getLon());

		}
		JSONObject js = new JSONObject();
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

	@POST
	@Path("/addPlace")
	@Produces(MediaType.TEXT_PLAIN)
	public String addPlace(@FormParam("name") String name,
			@FormParam("description") String description,
			@FormParam("lng") String lng, @FormParam("lat") String lat,
			@FormParam("userID") String userID) {
		String state = PlaceModel.addPlace(name, description,
				Double.parseDouble(lng), Double.parseDouble(lat),
				Integer.parseInt(userID));

		return state;
	}

	@POST
	@Path("/searchPlaceByName")
	@Produces(MediaType.TEXT_PLAIN)
	public String searchPlace(@FormParam("name") String name) {
		ArrayList<PlaceModel> place = PlaceModel.searchPlace(name);
		if (place == null)return "Error !!";
		ArrayList<JSONObject> jsonn = new ArrayList<JSONObject>();
		for (int i = 0; i < place.size(); i++) {
			JSONObject jso = new JSONObject();
			jsonn.add(jso);
			jsonn.get(i).put("id", place.get(i).getId());
			jsonn.get(i).put("name", place.get(i).getName());
			jsonn.get(i).put("description", place.get(i).getDescription());
			jsonn.get(i).put("lat", place.get(i).getLat());
			jsonn.get(i).put("lng", place.get(i).getLng());
			jsonn.get(i).put("userID", place.get(i).getUserID());
		}
		JSONObject js = new JSONObject();
		js.put("list", jsonn);
		return js.toJSONString();
	}
	
	
	
	/*
	 * 
	 * private int id;
	private String name;
	private String description;
	private double lng, lat;
	private int userID;
	private int numberOfCheckins;
	 */
	
	@POST
	@Path("/getPlaceByID")
	@Produces(MediaType.TEXT_PLAIN)
	public String getPlaceByID(@FormParam("placeID")int id) {
		PlaceModel place = PlaceModel.getPlaceByID(id);
		if(place == null)return "Error!";
		JSONObject jplace = new JSONObject();
		jplace.put("id", place.getId());
		jplace.put("name", place.getName());
		jplace.put("description", place.getDescription());
		jplace.put("lng", place.getLng());
		jplace.put("lat", place.getLat());
		jplace.put("userID", place.getUserID());
		jplace.put("numberOfCheckins", place.getNumberOfCheckins());
		return jplace.toJSONString();
	}
	
	
	
	@POST
	@Path("/getCheckinsFromUser")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCheckinsFromUser(@FormParam("userID")int id, @FormParam("type")int type) { 
		Set<Integer>checkins = new HashSet<Integer>();
		//get users checkins
		ArrayList<Integer>ids = Checkin.getCheckinsByID(id);
		for(int i = 0;i < ids.size();i++) {
			checkins.add(ids.get(i));
		}
		System.out.println("DONE1 ");
		//get followers checkins
		ids = Checkin.getFollwersCheckins(id);
		for(int i = 0;i < ids.size();i++) {
			checkins.add(ids.get(i));
		}
		System.out.println("DONE2");
		ArrayList<Checkin> ret = new ArrayList<Checkin>();
		
		for(Integer i : checkins) {
			ret.add(Checkin.getCheckinByID(i));
		}
		System.out.println("DONE3");
		//sort then return
		Sort sorter = null;
		if(type == 1) {
			sorter = new SortByCheckin();
		} else if(type == 2) {
			sorter = new SortByRate();
		} else {
			sorter = new SortByNearest();
		}
		System.out.println("DONE4");
		ArrayList<Checkin> sorted = Checkin.sortCheckins(ret, sorter);
		System.out.println("FINAL");
		JSONObject jcheckins = new JSONObject();
		ArrayList<JSONObject> jarray = new ArrayList<JSONObject>();
		for(int i = 0;i < sorted.size();i++) {
			JSONObject jcheckin = new JSONObject();
			Checkin checkintemp = sorted.get(i);
			jcheckin.put("description", checkintemp.getDescription());
			jcheckin.put("date", checkintemp.getDate());
			jcheckin.put("placeID", checkintemp.getPlaceID());
			jcheckin.put("userID", checkintemp.getUserID());
			jcheckin.put("id", checkintemp.getId());
			jcheckin.put("likes", checkintemp.getLikes());
			jcheckin.put("comments", checkintemp.getComments());
			jarray.add(jcheckin);
		}
		JSONObject jret = new JSONObject();
		jret.put("checkins", jarray);
		return jret.toJSONString();
	}
	
	
	@POST
	@Path("/createCheckin")
	@Produces(MediaType.TEXT_PLAIN)
	public String createCheckin(@FormParam("description") String description,
			@FormParam("userID") String userID,
			@FormParam("placeID") String placeID) {
		String state = Checkin.createCheckin(description, Integer.parseInt(userID)
				, Integer.parseInt(placeID));

		JSONObject json=new JSONObject();
		json.put("status", state);
		return json.toJSONString();
	}
	
	@POST
	@Path("/ratePlace")
	@Produces(MediaType.TEXT_PLAIN)
	public String ratePlace(@FormParam("id") String id,@FormParam("rate") String rate){
		String state = PlaceModel.ratePlace(Integer.parseInt(rate), Integer.parseInt(id));
		JSONObject json=new JSONObject();
		json.put("status", state);
		return json.toJSONString();
	}
	
	
	@POST
	@Path("/Comment")
	@Produces(MediaType.TEXT_PLAIN)
	public String comment(@FormParam("uID") int userID,@FormParam("checkInID") int chID,@FormParam("desc") String desc){
		String status=Comment.Do(userID, chID,desc);
		JSONObject json=new JSONObject();
		json.put("status", status);
		return json.toJSONString();
		
	}
	
	@POST
	@Path("/Like")
	@Produces(MediaType.TEXT_PLAIN)
	public String Like(@FormParam("uID") int userID,@FormParam("checkInID") int chID){
		String status=Like.Do(userID, chID);
		JSONObject json=new JSONObject();
		json.put("status", status);
		return json.toJSONString();
		
	}
	
	
	@POST
	@Path("/UnLike")
	@Produces(MediaType.TEXT_PLAIN)
	public String UnLike(@FormParam("uID") int userID,@FormParam("checkInID") int chID){
		String status=Like.Undo(userID, chID);
		JSONObject json=new JSONObject();
		json.put("status", status);
		return json.toJSONString();
		
	}
	
}
