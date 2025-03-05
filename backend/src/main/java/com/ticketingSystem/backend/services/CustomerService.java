package com.ticketingSystem.backend.services;

import com.ticketingSystem.backend.dto.request.CustomerSaveRequestDTO;
import com.ticketingSystem.backend.dto.response.CustomerResponseDTO;
import com.ticketingSystem.backend.dto.response.TicketPurchaseResponseDTO;
import com.ticketingSystem.backend.dto.response.VendorResponseDTO;
import com.ticketingSystem.backend.entity.TicketPurchase;

import java.util.List;

/**
 * The CustomerService interface defines methods for managing customers and their ticket purchases.
 */
public interface CustomerService {
    /**
     * Creates a new customer based on the provided request data.
     *
     * @param customerSaveRequestDTO The request data containing customer details.
     * @return A CustomerResponseDTO containing the created customer's details.
     */
    CustomerResponseDTO createCustomer(CustomerSaveRequestDTO customerSaveRequestDTO);

    /**
     * Updates an existing customer based on the provided request data.
     *
     * @param customerSaveRequestDTO The request data containing updated customer details.
     * @return A message indicating the result of the operation.
     */
    String updateCustomer(CustomerSaveRequestDTO customerSaveRequestDTO);

    /**
     * Retrieves a customer by their unique ID.
     *
     * @param customerId The ID of the customer to retrieve.
     * @return A CustomerResponseDTO containing the customer's details.
     */
    CustomerResponseDTO getCustomerById(int customerId);

    /**
     * Allows a customer to buy tickets from a vendor.
     *
     * @param customerId The ID of the customer.
     * @param vendorId The ID of the vendor.
     * @param noOfTickets The number of tickets to purchase.
     * @return True if the purchase is successful, false otherwise.
     */
    boolean buyTickets(int customerId, int vendorId, int noOfTickets);

    /**
     * Validates a customer's login credentials.
     *
     * @param customerEmail The email address of the customer.
     * @param customerPassword The password of the customer.
     * @return A CustomerResponseDTO containing the customer's details if the login is successful.
     */
    CustomerResponseDTO loginCustomer(String customerEmail, String customerPassword);

    /**
     * Retrieves a list of ticket purchases made by a specific customer.
     *
     * @param customerId The ID of the customer whose ticket purchases are to be retrieved.
     * @return A list of TicketPurchaseResponseDTO objects.
     */
    List<TicketPurchaseResponseDTO> getTicketsPurchasesByCustomer(int customerId);

    /**
     * Retrieves a list of all available events.
     *
     * @return A list of VendorResponseDTO objects containing event details.
     */
    List<VendorResponseDTO> getAllEvents();

    /**
     * Retrieves the event details for a specific vendor.
     *
     * @param vendorId The ID of the vendor whose event details are to be retrieved.
     * @return A VendorResponseDTO containing the vendor's event details.
     */
    VendorResponseDTO getVendorEventDetails(int vendorId);
}