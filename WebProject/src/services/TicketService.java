package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import enums.Role;
import enums.TicketStatus;
import models.Ticket;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicketService {
    private ArrayList<Ticket> tickets;
    private String path;

    public TicketService(String path) {
        tickets = new ArrayList<>();
        loadTickets(path);
    }

    private void loadTickets(String path) {
        ObjectMapper mapper = new ObjectMapper();
        String data = path + File.separator + "data" + File.separator;

        path = data;
        try {
            List<Ticket> ticketList = Arrays.asList(mapper.readValue(Paths.get(data + "tickets.json").toFile(), Ticket[].class));

            for (Ticket t : ticketList) {
                tickets.add(t);
            }

        } catch (IOException e) {
            System.out.println("Error while loading tickets!");
        }
    }

    public List<Ticket> getTickets(Role role, String username){
        ArrayList<Ticket> ticketArrayList = new ArrayList<Ticket>();

        if(role == Role.ADMIN )
        {
            return tickets;
        }

        else if(role == Role.SELLER)
        {
            for(Ticket t : tickets)
            {
                if(t.getTicketStatus() == TicketStatus.RESERVED)
                {
                    ticketArrayList.add(t);
                }
            }

        }
        else {
            for(Ticket t : tickets)
            {
                if(t.getBuyer().getUsername().equals(username))
                {
                    ticketArrayList.add(t);
                }
            }
        }

        return ticketArrayList;

    }
}
