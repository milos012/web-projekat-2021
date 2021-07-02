package controllers;

import models.Ticket;
import models.User;
import services.TicketService;
import services.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/tickets")
public class TicketController {
    @Context
    ServletContext servletContext;

    @Context
    HttpServletRequest httpServletRequest;

    private TicketService getTicketService() {
        TicketService ticketService = (TicketService) httpServletRequest.getAttribute("TicketService");
        if (ticketService == null) {
            ticketService = new TicketService(httpServletRequest.getRealPath("."));
            httpServletRequest.setAttribute("TicketService", ticketService);
        }
        return ticketService;
    }

    private UserService getUserService() {
        UserService userService = (UserService) httpServletRequest.getAttribute("UserService");
        if (userService == null) {
            userService = new UserService(httpServletRequest.getRealPath("."));
            httpServletRequest.setAttribute("UserService", userService);
        }
        return userService;
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ticket> getAllTickets() {
        TicketService ticketService = getTicketService();
        List<Ticket> tickets;
        User activeUser = (User) httpServletRequest.getSession().getAttribute("user");
        if (activeUser == null) {
            return null;
        }

        if (activeUser.equals(getUserService().getByUsername(activeUser.getUsername()))) {
            tickets = ticketService.getTickets( activeUser.getRole(), activeUser.getUsername());
        } else {
            return null;
        }

        httpServletRequest.getSession().setAttribute("activeTickets", tickets);
        return tickets;

    }
}
