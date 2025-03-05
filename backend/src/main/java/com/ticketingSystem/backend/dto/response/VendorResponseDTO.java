package com.ticketingSystem.backend.dto.response;

/**
 * The VendorResponseDTO class represents a data transfer object (DTO) for vendor responses.
 * It includes fields for vendor and event details and provides methods to access and modify these fields.
 */
public class VendorResponseDTO {
    private int vendorId;

    private String vendorName;

    private String vendorEmail;

    private String vendorPassword;

    private String eventName;

    private String eventDescription;

    private String eventLocation;

    private String eventDate;

    private String eventTime;

    private int eventPrice;

    private int eventTickets;

    /**
     * Constructor for the VendorResponseDTO with vendor details.
     *
     * @param vendorName The name of the vendor.
     * @param vendorEmail The email address of the vendor.
     * @param vendorPassword The password of the vendor.
     */
    public VendorResponseDTO( String vendorName, String vendorEmail, String vendorPassword) {
        this.vendorName = vendorName;
        this.vendorEmail = vendorEmail;
        this.vendorPassword = vendorPassword;
    }

    /**
     * Constructor for the VendorResponseDTO with event details.
     *
     * @param eventName The name of the event.
     * @param eventDescription The description of the event.
     * @param eventLocation The location of the event.
     * @param eventDate The date of the event.
     * @param eventTime The time of the event.
     * @param eventPrice The price of the event tickets.
     * @param eventTickets The number of tickets available for the event.
     */
    public VendorResponseDTO(String eventName, String eventDescription, String eventLocation, String eventDate, String eventTime, int eventPrice, int eventTickets) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventLocation = eventLocation;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventPrice = eventPrice;
        this.eventTickets = eventTickets;
    }

    /**
     * Constructor for the VendorResponseDTO with vendor email and password.
     *
     * @param vendorEmail The email address of the vendor.
     * @param vendorPassword The password of the vendor.
     */
    public VendorResponseDTO(String vendorEmail, String vendorPassword) {
        this.vendorEmail = vendorEmail;
        this.vendorPassword = vendorPassword;
    }

    /**
     * Default constructor for the VendorResponseDTO.
     */
    public VendorResponseDTO() {
    }

    /**
     * Constructor for the VendorResponseDTO with event details (excluding event tickets).
     *
     * @param eventName The name of the event.
     * @param eventDescription The description of the event.
     * @param eventLocation The location of the event.
     * @param eventDate The date of the event.
     * @param eventTime The time of the event.
     * @param eventPrice The price of the event tickets.
     */
    public VendorResponseDTO(String eventName, String eventDescription, String eventLocation, String eventDate, String eventTime, int eventPrice) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventLocation = eventLocation;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventPrice = eventPrice;
    }

    /**
     * Constructor for the VendorResponseDTO with vendor and event details.
     *
     * @param vendorId The unique identifier for the vendor.
     * @param eventName The name of the event.
     * @param eventDescription The description of the event.
     * @param eventLocation The location of the event.
     * @param eventDate The date of the event.
     * @param eventTime The time of the event.
     * @param eventPrice The price of the event tickets.
     * @param eventTickets The number of tickets available for the event.
     */
    public VendorResponseDTO(int vendorId, String eventName, String eventDescription, String eventLocation,
                             String eventDate, String eventTime, int eventPrice, int eventTickets) {
        this.vendorId = vendorId;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventLocation = eventLocation;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventPrice = eventPrice;
        this.eventTickets = eventTickets;
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

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }
}
