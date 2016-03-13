<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page
	import="java.net.URI ,
java.io.*, 
javax.ws.rs.client.Client , 
javax.ws.rs.client.ClientBuilder , 
javax.ws.rs.client.WebTarget , 
javax.ws.rs.core.MediaType , 
javax.ws.rs.core.Response ,
javax.ws.rs.core.UriBuilder , 
org.glassfish.jersey.client.ClientConfig ,
com.services.Services , org.json.simple.parser.*"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
Follow result:
<%
	String followerID = request.getParameter("followerID");
	String followedID = request.getParameter("followedID");
	ClientConfig configg = new ClientConfig();
	Client client = ClientBuilder.newClient(configg);
	System.out.println("YES");
	WebTarget target = client.target(UriBuilder.fromUri("http://localhost:8080/FCISquare").build());
	JSONParser parser = new JSONParser();
	System.out.println("YES");
	Object obj = parser.parse(target.path("rest")
			.path("followUser").path(followerID)
			.path(followedID).request()
			.accept(MediaType.TEXT_PLAIN).get(String.class)
			.toString());
	System.out.println("YES");
	JSONObject jsonObj = (JSONObject) obj;
	System.out.println("YES");
	String status = jsonObj.get("status").toString();
	System.out.println("YES");
%>
The result of following request is: <%=status%><br>
</body>
</html>