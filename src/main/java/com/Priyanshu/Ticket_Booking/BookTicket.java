package com.Priyanshu.Ticket_Booking;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BookTicket {
	private String userName;
	private String showTiming;
	private String phoneNumber;
	private int ticketId;
	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "BookTicket [userName=" + userName + ", showTiming=" + showTiming + ", phoneNumber=" + phoneNumber + "]";
	}
	
}
