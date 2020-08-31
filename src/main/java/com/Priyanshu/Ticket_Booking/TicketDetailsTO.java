package com.Priyanshu.Ticket_Booking;

public class TicketDetailsTO {
	private String userName;
	private String showTiming;
	private String showName;
	private int ticketId;
	private String status;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getShowTiming() {
		return showTiming;
	}
	public void setShowTiming(String showTiming) {
		this.showTiming = showTiming;
	}
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
