package com.Priyanshu.Ticket_Booking;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

	private String name;
	private int id;
	private String number;
	private int ticketId;

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public String getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", id=" + id + ", number=" + number + "]";
	}

	public void setNumber(String number) {

		this.number = number;
	}

}
