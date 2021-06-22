package controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import enums.Role;
import models.User;
import services.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@Path("/users")
public class UserController {
	
	@Context
	ServletContext ctx;
	
	@Context
	HttpServletRequest request;
	
	private UserService getUserService() {
		UserService userService = (UserService) ctx.getAttribute("UserService");
		if (userService == null) {
			userService = new UserService(ctx.getRealPath("."));
			ctx.setAttribute("UserService", userService);
		}
		return userService;
	}
	
	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public User login(@QueryParam("username") String username, @QueryParam("password") String password) {
		User user = getUserService().login(username, password);
		
		if(user != null)
			request.getSession().setAttribute("user", user);
		
		return user;
	}
	
	@GET
	@Path("/logout")
	public void logout() {
		User u = (User)request.getSession().getAttribute("user");
		if(u != null)
			request.getSession().setAttribute("user", null);
	}
	
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User register(User k) {
		User trenutni = (User) request.getSession().getAttribute("user");
		if(trenutni == null) {
			User user = getUserService().register(k);
			
			if(user != null) {
				request.getSession().setAttribute("user", user);
			}
			return user;
		}
		
//		if(trenutni.equals(getUserService().getByUsername(trenutni.getUsername())) && trenutni.getRole() == Role.ADMIN) {
//			k.setRole(Role.SELLER);
//			User user = getUserService().register(k);
//			return user;
//		}
		
		return null;
	}

}
