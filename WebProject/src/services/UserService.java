package services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.*;

import enums.Role;
import enums.UserTypeName;
import models.Buyer;
import models.Seller;
import models.User;

public class UserService {
	private ArrayList<User> users;
	private String path;
	private ArrayList<User> allUsers;
	private Comparator<User> sorterPoints, sorterFName, sorterLName, sorterUsername;
	
	public Collection<User> getAllUsers() {
		return users;
	}
	
	
	public UserService() {
		super();
	}
	
	public UserService(String path) {
		users = new ArrayList<User>();
		allUsers = new ArrayList<User>();
		initSorters();
		loadUsers(path);
	}

	private void loadUsers(String filePath){
		ObjectMapper mapper = new ObjectMapper();
		path = filePath + File.separator;

		List<User> admins = null;
		try {
			admins = Arrays.asList(mapper.readValue(Paths.get(path + "admins.json").toFile(), User[].class));
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
				e.printStackTrace();
		} catch (IOException e) {
		e.printStackTrace();
		}
		
		for (User u : admins) {
			if (!u.getDeleted()) {
				users.add(u);
			}
			allUsers.add(u);
		}
		
		
		try {
			List<Seller> sellers = Arrays.asList(mapper.readValue(Paths.get(path + "sellers.json").toFile(), Seller[].class));
			for (Seller s : sellers) {
				if (!s.getDeleted()) {
					users.add(s);
				}
				allUsers.add(s);
			}
			List<Buyer> buyers = Arrays.asList(mapper.readValue(Paths.get(path + "buyers.json").toFile(), Buyer[].class));
			for (Buyer b : buyers) {
				if (!b.getDeleted()) {
					users.add(b);
				}
				allUsers.add(b);
			}
		}
		catch (Exception e) {
			System.out.println("Error while loading");
		}

	}
	
	public User getByUsername(String username) {
		for (User u : users) {
			if (u.getUsername() == username){
				return u;
			}
		}
		return null;
	}
	
	public Collection<User> getByFirstName(Collection<User> users, String name) {
		ArrayList<User> filteredList = new ArrayList<User>();
		for (User u : users) {
			if (u.getFirstName().toLowerCase().contains(name)) {
				filteredList.add(u);
			}
		}
		return filteredList;
	}

	public Collection<User> getByLastName(Collection<User> users, String prezime) {
		ArrayList<User> filteredList = new ArrayList<User>();
		for (User u : users) {
			if (u.getLastName().toLowerCase().contains(prezime)) {
				filteredList.add(u);
			}
		}
		return filteredList;
	}
	
	private ArrayList<User> getAllAdmins() {
		ArrayList<User> admins = new ArrayList<User>();
		for (User u : users) {
			if (u.getRole() == Role.ADMIN) {
				admins.add(u);
			}
		}
		return admins;
	}
	
	private ArrayList<Buyer> getAllBuyers() {
		ArrayList<Buyer> buyers = new ArrayList<Buyer>();
		for (User u : users) {
			if (u.getRole() == Role.BUYER) {
				buyers.add((Buyer)u);
			}
		}
		return buyers;
	}
	
	private ArrayList<Seller> getAllSellers() {
		ArrayList<Seller> sellers = new ArrayList<Seller>();
		for (User u : users) {
			if (u.getRole() == Role.SELLER) {
				sellers.add((Seller)u);
			}
		}
		return sellers;
	}
	
	
	public void deleteUser(String username) {
		User u = getByUsername(username);
		u.setDeleted(true);
		ObjectMapper mapper = new ObjectMapper();
		if (u.getRole() == Role.BUYER) {
			try {
				mapper.writeValue(Paths.get(path + "buyers.json").toFile(), getAllBuyers());
			} catch (IOException e) {
				System.out.println("Error! Writing to file was unsuccessful.");
			}
		} else if (u.getRole() == Role.SELLER) {
			try {
				mapper.writeValue(Paths.get(path + "sellers.json").toFile(), getAllSellers());
			} catch (IOException e) {
				System.out.println("Error! Writing to file was unsuccessful.");
			}
		}
	}
	
	public void modifyUser(User u) {
		User modified = getByUsername(u.getUsername());
		modified.setFirstName(u.getFirstName());
		modified.setLastName(u.getLastName());
		modified.setPassword(u.getPassword());
		ObjectMapper mapper = new ObjectMapper();
		if (modified.getRole() == Role.BUYER) {
			try {
				mapper.writeValue(Paths.get(path + "buyers.json").toFile(), getAllBuyers());
			} catch (IOException e) {
				System.out.println("Error! Writing to file was unsuccessful.");
			}
		} else if (modified.getRole() == Role.SELLER) {
			try {
				mapper.writeValue(Paths.get(path + "sellers.json").toFile(), getAllSellers());
			} catch (IOException e) {
				System.out.println("Error! Writing to file was unsuccessful.");
			}
		} else {
			try {
				mapper.writeValue(Paths.get(path + "admins.json").toFile(), getAllAdmins());
			} catch (IOException e) {
				System.out.println("Error! Writing to file was unsuccessful.");
			}
		}
	}
	
	public void addUser(User u) {
		if (getByUsername(u.getUsername()) == null) {
			System.out.println("User already exists");
			return;
		}

		if (u.getRole() == Role.BUYER) {
			Buyer buyer = new Buyer(u.getUsername(), u.getPassword(), u.getFirstName(), u.getLastName(), u.getGender(),
					u.getDateOfBirth(), u.getRole(), u.getDeleted(), new ArrayList<String>(), 0, UserTypeName.BRONZE);

			
			users.add(buyer);
			allUsers.add(buyer);
			ObjectMapper mapper = new ObjectMapper();
			try {
				mapper.writeValue(Paths.get(path + "buyers.json").toFile(), getAllBuyers());
			} catch (IOException e) {
				System.out.println("Error while writing!");
			}
		}

		else if (u.getRole() == Role.SELLER) {
			Seller seller = new Seller(u.getUsername(), u.getPassword(), u.getFirstName(), u.getLastName(), u.getGender(),
					u.getDateOfBirth(), u.getRole(), u.getDeleted(), new ArrayList<>(), new ArrayList<>());
			
			users.add(seller);
			allUsers.add(seller);;
			ObjectMapper mapper = new ObjectMapper();
			try {
				mapper.writeValue(Paths.get(path + "sellers.json").toFile(), getAllSellers());
			} catch (IOException e) {
				System.out.println("Error while writing!");
			}
		}
	}
	
	public User login(String username, String password) {
		for (User u : users) {
			if (u.getUsername().equals(username)){
				if(u.getPassword().equals(password) && u.getDeleted() == false) {
					return u;
				}
			}
		}
		return null;
	}
	
	
	public User register(User u) {
		for (User usr : users) {
			if (usr.getUsername() == u.getUsername()){
				return null;
			}
		}
		

		if (u.getRole() == Role.BUYER) {
			Buyer buyer = new Buyer(u.getUsername(), u.getPassword(), u.getFirstName(), u.getLastName(), u.getGender(),
					u.getDateOfBirth(), u.getRole(), u.getDeleted(), new ArrayList<String>(), 0, UserTypeName.BRONZE);

			users.add(buyer);
			allUsers.add(buyer);
			ObjectMapper mapper = new ObjectMapper();
			try {
				mapper.writeValue(Paths.get(path + "buyers.json").toFile(), getAllBuyers());
			} catch (IOException e) {
				System.out.println("Error while writing!");
				return null;
			}
			return buyer;
		}
		else if (u.getRole() == Role.SELLER) {
			Seller seller = new Seller(u.getUsername(), u.getPassword(), u.getFirstName(), u.getLastName(), u.getGender(),
					u.getDateOfBirth(), u.getRole(), u.getDeleted(), new ArrayList<>(), new ArrayList<>());
			
			users.add(seller);
			allUsers.add(seller);
			ObjectMapper mapper = new ObjectMapper();
			try {
				mapper.writeValue(Paths.get(path + "sellers.json").toFile(), getAllSellers());
			} catch (IOException e) {
				System.out.println("Error while writing!");
				return null;
			}
			return seller;
		}
		return null;
	}
	
	public List<User> sortByFName(List<User> result, boolean opadajuce) {

		Collections.sort(result, sorterFName);
		if (opadajuce) {
			Collections.reverse(result);
		}
		return result;
	}

	public List<User> sortByLName(List<User> result, boolean opadajuce) {
		Collections.sort(result, sorterLName);
		if (opadajuce) {
			Collections.reverse(result);
		}
		return result;
	}
	
	public List<User> sortByUsername(List<User> result, boolean opadajuce) {
		Collections.sort(result, sorterUsername);
		if (opadajuce) {
			Collections.reverse(result);
		}
		return result;
	}
	
	public List<User> sortByPoints(List<User> result, boolean opadajuce) {
		result.removeIf(k -> k.getRole() != Role.BUYER);
		Collections.sort(result, sorterPoints);
		if (opadajuce) {
			Collections.reverse(result);
		}
		return result;
	}
	
	private void initSorters() {
		sorterPoints = new Comparator<User>() {

			@Override
			public int compare(User o1, User o2) {
				Buyer k1 = (Buyer) o1;
				Buyer k2 = (Buyer) o2;
				return Double.compare(k1.getPoints(), k2.getPoints());
			}

		};

		sorterFName = new Comparator<User>() {

			@Override
			public int compare(User o1, User o2) {
				return o1.getFirstName().toLowerCase().compareTo(o2.getFirstName().toLowerCase());
			}

		};

		sorterLName = new Comparator<User>() {

			@Override
			public int compare(User o1, User o2) {
				return o1.getLastName().toLowerCase().compareTo(o2.getLastName().toLowerCase());
			}

		};

		sorterUsername = new Comparator<User>() {

			@Override
			public int compare(User o1, User o2) {
				return o1.getUsername().toLowerCase().compareTo(o2.getUsername().toLowerCase());
			}

		};
	}
	
}
