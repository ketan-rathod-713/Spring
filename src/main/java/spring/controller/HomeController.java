package spring.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.model.Event;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String home(Model model) {
		System.out.println("home controller called");

		
		try {
			Class.forName( "com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventsocialmedia","root","Ketan17547@");
			
			Statement statement=connection.createStatement();
			Statement statement2=connection.createStatement();
			ResultSet rs=statement.executeQuery("select * from eventspring");
			ResultSet rs2 = statement2.executeQuery("select count(*) from eventspring");
			int count = 0;
			while(rs2.next()) {
				System.out.println(rs2.getString(1));
				count = Integer.parseInt(rs2.getString(1));
			}
			
			Event[] events = new Event[count];
			int i = 0;
			while(rs.next())
			{
				String eventId = rs.getString(1);
				String eventName = rs.getString(2);
				String eventDescription = rs.getString(3);
				String userId = rs.getString(4);
				events[i] = new Event(eventId, eventName, eventDescription, userId);
				i++;
			}
			
			
			model.addAttribute("events", events);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "index";
	}
	
	@RequestMapping("/events")
	public String events() {
		System.out.println("events controller called");
		return "events";
	}
	
	@RequestMapping("/deleteEvent")
	public String deleteEvent(@RequestParam("eventId") String eventId) {
		
		System.out.println(" deleted event with event id "+eventId);
		
		
		try {
			Class.forName( "com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventsocialmedia","root","Ketan17547@");
			
			PreparedStatement statement=connection.prepareStatement("delete from eventspring where eventId = ?");
			statement.setString(1, eventId);
			boolean rs = statement.execute();
			
			System.out.println(rs);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "redirect:/";
	}
}
