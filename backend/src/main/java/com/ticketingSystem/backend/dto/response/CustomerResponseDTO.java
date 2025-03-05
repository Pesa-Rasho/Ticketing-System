package com.ticketingSystem.backend.dto.response;

import java.util.List;

/**
 * The CustomerResponseDTO class represents a data transfer object (DTO) for customer responses.
 * It includes fields for customer details and a list of events associated with the customer, and provides methods to access and modify these fields.
 */
public class CustomerResponseDTO {

   private int customerId;
   private String customerName;
   private String customerEmail;
   private String customerPassword;
   private List<CustomerResponseDTO> customerEvents;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public List<CustomerResponseDTO> getCustomerEvents() {
        return customerEvents;
    }

    public void setCustomerEvents(List<CustomerResponseDTO> customerEvents) {
        this.customerEvents = customerEvents;
    }
}
