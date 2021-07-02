package models;

import enums.TicketStatus;
import enums.TicketType;

import java.util.Date;

public class Ticket {
    private String id;
    private Manifestation manifestation;
    private Date manifestationDateTime;
    private double price;
    private Buyer buyer;
    private TicketStatus ticketStatus;
    private TicketType ticketType;

    public Ticket() {
    }

    public Ticket(String id, Manifestation manifestation, Date manifestationDateTime, double price, Buyer buyer, TicketStatus ticketStatus, TicketType ticketType) {
        this.id = id;
        this.manifestation = manifestation;
        this.manifestationDateTime = manifestationDateTime;
        this.price = price;
        this.buyer = buyer;
        this.ticketStatus = ticketStatus;
        this.ticketType = ticketType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Manifestation getManifestation() {
        return manifestation;
    }

    public void setManifestation(Manifestation manifestation) {
        this.manifestation = manifestation;
    }

    public Date getManifestationDateTime() {
        return manifestationDateTime;
    }

    public void setManifestationDateTime(Date manifestationDateTime) {
        this.manifestationDateTime = manifestationDateTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }
}
