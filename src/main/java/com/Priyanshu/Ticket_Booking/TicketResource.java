package com.Priyanshu.Ticket_Booking;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("users")
public class TicketResource {
	UserRepository repo = new UserRepository();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() {
		System.out.println("getUser called...");

		return repo.getUsers();
	}


	@POST
	@Path("user")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public User createUser(User u1) {
		System.out.println(u1);
		repo.create(u1);
		return u1;
	}

	@POST
	@Path("bookTicket")
	@Produces( MediaType.APPLICATION_JSON)
	public String bookTicket(BookTicket bookTicket) {
		System.out.println(bookTicket);
		String result = repo.bookTicket(bookTicket);
		return result;
	}

	@POST
	@Path("updateTicketTiming")
	@Produces( MediaType.APPLICATION_JSON)
	public String updateTicketTiming(BookTicket updateTicketTiming) {
		System.out.println(updateTicketTiming);
		String result = repo.UpdateTicketTiming(updateTicketTiming);
		return result;
	}
	
	@POST
	@Path("viewAllTickets")
	@Produces( MediaType.APPLICATION_JSON)
	public List<TicketDetailsTO> viewAllTickets(TicketDetailsTO viewAllTickets) {
		System.out.println(viewAllTickets);
		return repo.ViewAllTickets(viewAllTickets);
		
	}
	
	@POST
	@Path("deleteTicket")
	@Produces( MediaType.APPLICATION_JSON)
	public String deleteTicket(BookTicket deleteTicket) {
		System.out.println(deleteTicket);
		String result = repo.deleteTicket(deleteTicket);
		return result;
	}
	

	@POST
	@Path("userDetail/{ticketId}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("ticketId") int ticketId) {
		return repo.getUser(ticketId);
	}
	
	@POST
	@Path("updateTicketStatus")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateTicketStatus() {
		return repo.updateTicketStatus();
	}
	
	@POST
	@Path("deleteExpiredStatus")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteExpiredStatus() {
		return repo.deleteExpiredStatus();
	}
}
