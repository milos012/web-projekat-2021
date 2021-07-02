package models;

import java.time.LocalDate;
import java.util.ArrayList;

import enums.Gender;
import enums.Role;
import enums.UserTypeName;

public class Buyer extends User{
	private ArrayList<String> tickets;
	private double points;
	private UserTypeName userTypeName;
	
	
	public Buyer(String username, String password, String firstName, String lastName, Gender gender,
			LocalDate dateOfBirth, Role role, Boolean deleted, ArrayList<String> tickets, double points,
			UserTypeName userType) {
		super(username, password, firstName, lastName, gender, dateOfBirth, role, deleted);
		this.tickets = tickets;
		this.points = points;
		this.userTypeName = userType;
	}
	
	public ArrayList<String> getTickets() {
		return tickets;
	}
	public void setTickets(ArrayList<String> tickets) {
		this.tickets = tickets;
	}
	public double getPoints() {
		return points;
	}
	public void setPoints(double points) {
		this.points = points;
	}
	public UserTypeName getUserType() {
		return userTypeName;
	}
	public void setUserType(UserTypeName userType) {
		this.userTypeName = userType;
	}
	
	
}
