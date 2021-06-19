package models;

import java.util.ArrayList;

public class Seller {
	
	private ArrayList<Integer> manifestations;  // Manifestation IDs
	private ArrayList<Integer> tickets;  // ticket IDs
	
	
	public Seller(ArrayList<Integer> manifestations, ArrayList<Integer> tickets) {
		super();
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
