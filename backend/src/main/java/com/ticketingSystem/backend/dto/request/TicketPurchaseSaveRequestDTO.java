package com.ticketingSystem.backend.dto.request;

import com.ticketingSystem.backend.entity.Customer;
import com.ticketingSystem.backend.entity.Vendor;

/**
 * The TicketPurchaseSaveRequestDTO class represents a data transfer object (DTO) for ticket purchase save requests.
 * It includes fields for ticket purchase details and provides methods to access and modify these fields.
 */
public class TicketPurchaseSaveRequestDTO {
    private int id;

    private int customerId;

    private int vendorId;

    private String eventName;

    private int noOfTickets;

    private Customer customer;

    private Vendor vendor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getNoOfTickets() {
        return noOfTickets;
    }

    public void setNoOfTickets(int noOfTickets) {
        this.noOfTickets = noOfTickets;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
}
