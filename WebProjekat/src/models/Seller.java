package models;

import java.time.LocalDate;
import java.util.ArrayList;

import enums.Gender;
import enums.Role;

public class Seller extends User{
	
	private ArrayList<Integer> manifestations;  // Manifestation IDs
	private ArrayList<Integer> tickets;  // ticket IDs
	

	public Seller(String username, String password, String firstName, String lastName, Gender gender,
			LocalDate dateOfBirth, Role role, Boolean deleted, ArrayList<Integer> manifestations,
			ArrayList<Integer> tickets) {
		super(username, password, firstName, lastName, gender, dateOfBirth, role, deleted);
		this.manifestations = manifestations;
		this.tickets = tickets;
	}


	public ArrayList<Integer> getManifestations() {
		return manifestations;
	}


	public void setManifestations(ArrayList<Integer> manifestations) {
		this.manifestations = manifestations;
	}


	public ArrayList<Integer> getTickets() {
		return tickets;
	}


	public void setTickets(ArrayList<Integer> tickets) {
		this.tickets = tickets;
	}
	
	

}
