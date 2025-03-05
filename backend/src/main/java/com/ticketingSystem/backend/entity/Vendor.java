package com.ticketingSystem.backend.entity;

import jakarta.persistence.*;

/**
 * The Vendor class represents a vendor entity in the ticketing system.
 * It includes fields for vendor details and event details, and provides methods to access and modify these fields.
 */
@Entity
public class Vendor {

    @Id
    @Column(name = "vendor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vendorID;

    @Column(name = "vendor_name", nullable = false)
    private String vendorName;

    @Column(name = "vendor_email", nullable = false)
    private String vendorEmail;

    @Column(name = "vendor_password", nullable = false)
    private String vendorPassword;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "event_description")
    private String eventDescription;

    @Column(name = "event_location")
    private String eventLocation;

    @Column(name = "event_date")
    private String eventDate;

    @Column(name = "event_time")
    private String eventTime;

    @Column(name = "event_price")
    private int eventPrice;

    @Column(name = "event_tickets")
    private int eventTickets;

    /**
     * Default constructor for the Vendor entity.
     */
    public Vendor() {}

    /**
     * Constructor for the Vendor entity with all fields.
     *
     * @param vendorID The unique identifier for the vendor.
     * @param vendorName The name of the vendor.
     * @param vendorEmail The email address of the vendor.
     * @param vendorPassword The password of the vendor.
     * @param eventName The name of the event.
     * @param eventDescription The description of the event.
     * @param eventLocation The location of the event.
     * @param eventDate The date of the event.
     * @param eventTime The time of the event.
     * @param eventPrice The price of the event tickets.
     * @param eventTickets The number of tickets available for the event.
     */
    public Vendor(int vendorID, String vendorName, String vendorEmail, String vendorPassword, String eventName, String eventDescription, String eventLocation, String eventDate, String eventTime, int eventPrice, int eventTickets) {
        this.vendorID = vendorID;
        this.vendorName = vendorName;
        this.vendorEmail = vendorEmail;
        this.vendorPassword = vendorPassword;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventLocation = eventLocation;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventPrice = eventPrice;
        this.eventTickets = eventTickets;
    }

    /**
     * Constructor for the Vendor entity with basic vendor details.
     *
     * @param vendorID The unique identifier for the vendor.
     * @param vendorName The name of the vendor.
     * @param vendorEmail The email address of the vendor.
     * @param vendorPassword The password of the vendor.
     */
    public Vendor(int vendorID, String vendorName, String vendorEmail, String vendorPassword) {
        this.vendorID = vendorID;
        this.vendorName = vendorName;
        this.vendorEmail = vendorEmail;
        this.vendorPassword = vendorPassword;
    }

    /**
     * Constructor for the Vendor entity with just the vendor ID.
     *
     * @param vendorID The unique identifier for the vendor.
     */
    public Vendor(int vendorID) {
        this.vendorID = vendorID;
        this.eventTickets = 0;
    }

    public int getVendorID() {
        return vendorID;
    }

    public void setVendorID(int vendorID) {
        this.vendorID = vendorID;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorEmail() {
        return vendorEmail;
    }

    public void setVendorEmail(String vendorEmail) {
        this.vendorEmail = vendorEmail;
    }

    public String getVendorPassword() {
        return vendorPassword;
    }

    public void setVendorPassword(String vendorPassword) {
        this.vendorPassword = vendorPassword;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public int getEventPrice() {
        return eventPrice;
    }

    public void setEventPrice(int eventPrice) {
        this.eventPrice = eventPrice;
    }

    public int getEventTickets() {
        return eventTickets;
    }

    public void setEventTickets(int eventTickets) {
        this.eventTickets = eventTickets;
    }


}
