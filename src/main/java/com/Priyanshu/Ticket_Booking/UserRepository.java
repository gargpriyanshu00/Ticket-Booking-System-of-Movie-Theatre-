package com.Priyanshu.Ticket_Booking;

import java.io.InputStream;
import java.sql.Connection;
//import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

//import com.mysql.jdbc.Statement;
public class UserRepository {

	Connection con = null;

	public UserRepository() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Properties properties =new Properties();
			InputStream inputStream=getClass().getClassLoader().getResourceAsStream("application.properties");
			properties.load(inputStream);
			String userName=properties.getProperty("userName");
			String Password=properties.getProperty("password");
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket_booking", userName,
					Password);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public List<User> getUsers() {
//		Class.forName("com.mysql.jdbc.Driver"); 
//		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket_booking","root","priyanshu");  
		List<User> users = new ArrayList<>();
		String sql = "select * from users";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				User u = new User();
				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setNumber(rs.getString(3));
				users.add(u);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return users;
	}

	public User getUser(int id) {
		String sql = "select b.id, b.userName, b.Phone_Number, a.Ticket_id from Ticket_Details a join users b on b.id =a.User_id where a.Ticket_id="
				+ id;
		User u = new User();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {

				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setNumber(rs.getString(3));
				u.setTicketId(rs.getInt(4));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return u;
	}

	public void create(User u1) {
		String sql = "insert into users values(?,?,?)";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, u1.getId());
			st.setString(2, u1.getName());
			st.setString(3, u1.getNumber());
			st.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String bookTicket(BookTicket bookTicket) {
		String result = null;
		String sql = "select * from Shows where Show_Timing=\"" + bookTicket.getShowTiming() + "\"";

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				if (rs.getInt(4) < 20) {
					String userId = "select id from users where userName=\"" + bookTicket.getUserName()
							+ "\" and Phone_Number=\"" + bookTicket.getPhoneNumber() + "\"";
					Statement userIdSt = con.createStatement();
					ResultSet userIdRs = userIdSt.executeQuery(userId);
					String query = "insert into Ticket_Details (Ticket_Timing,User_id,Status) values(?,?,?)";
					userIdRs.next();
					try {

						PreparedStatement querySt = con.prepareStatement(query);
						// querySt.setInt(1, );
						querySt.setString(1, bookTicket.getShowTiming().toString());
						querySt.setInt(2, userIdRs.getInt(1));
						querySt.setString(3, "active");
						querySt.executeUpdate();
						result = "ticket generated successfully";
						int count = rs.getInt(4) + 1;
						String updateTicketCount = "update Shows set TicketCount=" + count + " where Show_id="
								+ rs.getInt(1);
						st.executeUpdate(updateTicketCount);
						//
					} catch (Exception e) {
						System.out.println(e);
					}
				} else {
					result = "Show is full at this time";
				}

			} else {
				result = "No Show is available at this timimng";
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

	public String UpdateTicketTiming(BookTicket updateTicketTiming) {
		String result = null;
		String sql = "select * from Ticket_Details where Ticket_id =" + updateTicketTiming.getTicketId();

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				String ticketTiming = "select * from Shows where Show_Timing =\"" + updateTicketTiming.getShowTiming()
						+ "\" ";
				Statement ticketSt = con.createStatement();
				ResultSet ticketRs = ticketSt.executeQuery(ticketTiming);
				if (ticketRs.next()) {

					try {
						String oldTicketTiming = "select * from Shows where Show_Timing =\"" + rs.getString(2) + "\" ";
						ResultSet oldTicketRs = st.executeQuery(oldTicketTiming);
						oldTicketRs.next();
						int reduceCount = oldTicketRs.getInt(4) - 1;
						String reduceTicketCount = "update Shows set TicketCount=" + reduceCount + " where Show_id="
								+ oldTicketRs.getInt(1);
						st.executeUpdate(reduceTicketCount);

						String updateTicketDetail = "update Ticket_Details set Ticket_Timing=\""
								+ updateTicketTiming.getShowTiming() + "\" where  Ticket_id ="
								+ updateTicketTiming.getTicketId();
						st.executeUpdate(updateTicketDetail);
						result = "Ticket timing is updated";
						int count = ticketRs.getInt(4) + 1;
						String updateTicketCount = "update Shows set TicketCount=" + count + " where Show_id="
								+ ticketRs.getInt(1);
						st.executeUpdate(updateTicketCount);

						//
					} catch (Exception e) {
						System.out.println(e);
					}
				} else {
					result = "There is no Show available at the time you want to update the Ticket";
				}

			} else {
				result = "No Ticket is available at this id";
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

	public List<TicketDetailsTO> ViewAllTickets(TicketDetailsTO viewAllTickets) {
		List<TicketDetailsTO> ticketDetails = new ArrayList<>();
		String sql = "select a.Ticket_id,a.Ticket_Timing,a.Status,b.userName,c.Show_Name from Ticket_Details a join users b on b.id =a.User_id join Shows c on c.Show_Timing=a.Ticket_Timing where a.Ticket_Timing=\""
				+ viewAllTickets.getShowTiming() + "\"";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				TicketDetailsTO u = new TicketDetailsTO();
				u.setTicketId(rs.getInt(1));
				u.setShowTiming(rs.getString(2));
				u.setStatus(rs.getString(3));
				u.setUserName(rs.getString(4));
				u.setShowName(rs.getString(5));
				ticketDetails.add(u);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return ticketDetails;

	}

	public String deleteTicket(BookTicket deleteTicket) {
		String result = null;
		String sql = "select * from Ticket_Details where Ticket_id=" + deleteTicket.getTicketId();
		// String sql = "DELETE FROM Ticket_Details WHERE Ticket_id=" +
		// deleteTicket.getTicketId();

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				String ticketTiming = "select * from Shows where Show_Timing =\"" + rs.getString(2) + "\" ";
				ResultSet ticketRs = st.executeQuery(ticketTiming);
				ticketRs.next();
				int reduceCount = ticketRs.getInt(4) - 1;
				String reduceTicketCount = "update Shows set TicketCount=" + reduceCount + " where Show_id="
						+ ticketRs.getInt(1);
				st.executeUpdate(reduceTicketCount);

				String delete = "DELETE FROM Ticket_Details WHERE Ticket_id=" + deleteTicket.getTicketId();
				st.executeUpdate(delete);

				result = "Ticket is deleted at this Id";
			}

			else {
				result = "No Ticket is available to delete at this Id";
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

	public String updateTicketStatus() {
		String result = null;
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// remove next line if you're always using the current time.
		//cal.setTime(currentDate);
		cal.add(Calendar.HOUR, -8);
		String eightHourBack = dateFormat.format(cal.getTime());
		String sql = "select * from Ticket_Details where Status =\"active\" ";
		

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				String s = rs.getString(2);
				int c=eightHourBack.compareTo(s);
				if(c==1||c==0)
				{try {
					String updateTicketDetail = "update Ticket_Details set Status =\"expired\" where Ticket_id="+rs.getInt(1);
					Statement resultSt = con.createStatement();
					resultSt.executeUpdate(updateTicketDetail);
				}
				catch (Exception e) {
					System.out.println(e);
				}
				}
				else {}
			}
			result = "All Ticket Status is updated";
			return result;
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return result;
		
	}

	public String deleteExpiredStatus() {
		String result = null;
		try {
		Statement st = con.createStatement();
		String delete = "DELETE from Ticket_Details WHERE Status =\"expired\"";
		st.executeUpdate(delete);
		
		result = "Expired Tickets deleted successfully";
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}
}
