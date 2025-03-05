package com.ticketingSystem.backend.controller;
import com.ticketingSystem.backend.dto.request.CustomerSaveRequestDTO;
import com.ticketingSystem.backend.dto.request.TicketPurchaseSaveRequestDTO;
import com.ticketingSystem.backend.dto.response.CustomerResponseDTO;
import com.ticketingSystem.backend.dto.response.TicketPurchaseResponseDTO;
import com.ticketingSystem.backend.dto.response.VendorResponseDTO;
import com.ticketingSystem.backend.entity.TicketPurchase;
import com.ticketingSystem.backend.model.TicketPool;
import com.ticketingSystem.backend.services.CustomerService;
import com.ticketingSystem.backend.services.TicketPurchaseService;
import com.ticketingSystem.backend.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * The CustomerController class handles HTTP requests related to customer operations.
 * It provides endpoints for customer login, creation, update, ticket purchase, and event retrieval.
 */
@RestController
@CrossOrigin
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    @Autowired
    TicketPurchaseService ticketPurchaseService;

    @Autowired
    private TicketPool ticketPool;

    @Autowired
    private VendorService vendorService;

    /**
     * Constructor for the CustomerController.
     *
     * @param customerService The CustomerService instance to inject.
     * @param ticketPurchaseService The TicketPurchaseService instance to inject.
     */
    public CustomerController(CustomerService customerService, TicketPurchaseService ticketPurchaseService) {
        this.customerService = customerService;
        this.ticketPurchaseService = ticketPurchaseService;
    }

    /**
     * Validates a customer's login credentials.
     *
     * @param customerEmail The email address of the customer.
     * @param customerPassword The password of the customer.
     * @return A CustomerResponseDTO containing the customer's details if the login is successful.
     * @throws ResponseStatusException If the login credentials are invalid.
     */
    @PostMapping("/customer-login")
    public CustomerResponseDTO login(@RequestParam String customerEmail, @RequestParam String customerPassword) {
        CustomerResponseDTO customerResponseDTO = customerService.loginCustomer(customerEmail, customerPassword);
        if (customerResponseDTO != null) {
            return customerResponseDTO;
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
    }

    /**
     * Creates a new customer based on the provided request data.
     *
     * @param customerSaveRequestDTO The request data containing customer details.
     * @return A CustomerResponseDTO containing the created customer's details.
     */
    @PostMapping(path = "/create-customer")
    public CustomerResponseDTO createCustomer(@RequestBody CustomerSaveRequestDTO customerSaveRequestDTO) {
        return customerService.createCustomer(customerSaveRequestDTO);
    }

    /**
     * Updates an existing customer based on the provided request data.
     *
     * @param customerSaveRequestDTO The request data containing updated customer details.
     * @return A message indicating the result of the operation.
     */
    @PutMapping(path = "/update-customer")
    public String updateCustomer(@RequestBody CustomerSaveRequestDTO customerSaveRequestDTO) {
        String message = customerService.updateCustomer(customerSaveRequestDTO);
        return message;
    }

    /**
     * Retrieves a customer by their unique ID.
     *
     * @param customerId The ID of the customer to retrieve.
     * @return A CustomerResponseDTO containing the customer's details.
     */
    @GetMapping(path = "/view-customer", params = "id")
    public CustomerResponseDTO getCustomerById(@RequestParam(value = "id") int customerId) {
        CustomerResponseDTO customerResponseDTO = customerService.getCustomerById(customerId);
        return customerResponseDTO;
    }

    /**
     * Allows a customer to buy tickets from a vendor.
     *
     * @param customerId The ID of the customer.
     * @param vendorId The ID of the vendor.
     * @param noOfTickets The number of tickets to purchase.
     * @return A message indicating the result of the ticket purchase.
     */
    @PostMapping("/buy-tickets")
    public String buyTicket(
            @RequestParam int customerId,
            @RequestParam int vendorId,
            @RequestParam int noOfTickets)
    {

        boolean purchaseSuccessful = customerService.buyTickets(customerId, vendorId, noOfTickets);

        if (purchaseSuccessful) {
            return "Ticket purchase successful";
        } else {
            return "Ticket purchase failed ";
        }
    }

    /**
     * Retrieves a list of ticket purchases made by a specific customer.
     *
     * @param customerId The ID of the customer whose ticket purchases are to be retrieved.
     * @return A list of TicketPurchaseResponseDTO objects.
     */
    @GetMapping(path = "/ticket-purchases", params = "customerId")
    public List<TicketPurchaseResponseDTO> getTicketPurchasesByCustomerId(int customerId) {
        return customerService.getTicketsPurchasesByCustomer(customerId);
    }

    /**
     * Retrieves a list of all available events.
     *
     * @return A list of VendorResponseDTO objects containing event details.
     */
    @GetMapping("/view-all-events")
    public List<VendorResponseDTO> getAllEvents() {
        return customerService.getAllEvents();
    }

    /**
     * Retrieves the event details for a specific vendor.
     *
     * @param vendorId The ID of the vendor whose event details are to be retrieved.
     * @return A VendorResponseDTO containing the vendor's event details.
     * @throws ResponseStatusException If the vendor ID is invalid.
     */
    @GetMapping("/view-vendor-events")
    public VendorResponseDTO getEventDetails(@RequestParam int vendorId) {
        VendorResponseDTO vendorDetails = customerService.getVendorEventDetails(vendorId);

        if (vendorDetails != null) {
            return vendorDetails;
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid vendor id");
    }


}
