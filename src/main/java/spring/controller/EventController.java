package spring.controller;


import java.util.Random;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.model.Event;

@Controller
public class EventController {
	
	

	@RequestMapping("/create")
	public String showForm() {
		return "create";
	}
	
	@RequestMapping("/signup")
	public String signup() {
		return "signup";
	}
	
	public String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }
	
	@RequestMapping(path= "/processCreate", method = RequestMethod.POST)
	public String processCreate(@ModelAttribute Event event) {
	
		System.out.println(event);
		
		try {
			Class.forName( "com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventsocialmedia","root","Ketan17547@");
			
			PreparedStatement statement=connection.prepareStatement("INSERT INTO eventspring (`eventId`, `eventName`, `eventDescription`, `userId`) VALUES (?,?,?,?);");
			
			Random random = new Random();
			String randomString = generateRandomString(10);
			
			statement.setString(1, randomString);
			statement.setString(2, event.getEventName());
			statement.setString(3, event.getEventDescription());
			statement.setString(4, event.getUserId());
			
			boolean rs = statement.execute();
			
			System.out.println(rs);
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	
		return "redirect:/";
	}
	
//	@RequestMapping(path= "/processCreate", method = RequestMethod.POST)
//	public String processCreate(@ModelAttribute Event event) {
//	
//		System.out.println(event);
//		
//		return "index";
//	}
	
//	This can also be user
//	@RequestParam("eventName") String eventName, @RequestParam("eventDescription") String eventDesc,@RequestParam("userId") String userId
}
