package com.ticketingSystem.backend.services.impl;

import com.ticketingSystem.backend.dto.request.CustomerSaveRequestDTO;
import com.ticketingSystem.backend.dto.response.CustomerResponseDTO;
import com.ticketingSystem.backend.dto.response.TicketPurchaseResponseDTO;
import com.ticketingSystem.backend.dto.response.VendorResponseDTO;
import com.ticketingSystem.backend.entity.Customer;
import com.ticketingSystem.backend.entity.TicketPurchase;
import com.ticketingSystem.backend.entity.Vendor;
import com.ticketingSystem.backend.model.TicketPool;
import com.ticketingSystem.backend.repository.CustomerRepository;
import com.ticketingSystem.backend.repository.VendorRepository;
import com.ticketingSystem.backend.services.CustomerService;
import com.ticketingSystem.backend.services.TicketPurchaseService;
import com.ticketingSystem.backend.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The CustomerServiceImpl class provides the implementation for managing customers and their ticket purchases.
 */
@Service
public class  CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    VendorService vendorService;

    @Autowired
    TicketPool ticketPool;

    @Autowired
    TicketPurchaseService ticketPurchaseService;
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private VendorServiceImpl vendorServiceImpl;

    /**
     * Creates a new customer based on the provided request data.
     *
     * @param customerSaveRequestDTO The request data containing customer details.
     * @return A CustomerResponseDTO containing the created customer's details.
     */
    @Override
    public CustomerResponseDTO createCustomer(CustomerSaveRequestDTO customerSaveRequestDTO) {
        Customer customer = new Customer();
        customer.setCustomerName(customerSaveRequestDTO.getCustomerName());
        customer.setCustomerEmail(customerSaveRequestDTO.getCustomerEmail());
        customer.setCustomerPassword(customerSaveRequestDTO.getCustomerPassword());
        customerRepository.save(customer);

        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setCustomerId(customer.getCustomerId());
        customerResponseDTO.setCustomerName(customer.getCustomerName());
        customerResponseDTO.setCustomerEmail(customer.getCustomerEmail());

        return customerResponseDTO;
    }

    /**
     * Updates an existing customer based on the provided request data.
     *
     * @param customerSaveRequestDTO The request data containing updated customer details.
     * @return A message indicating the result of the operation.
     */
    @Override
    public String updateCustomer(CustomerSaveRequestDTO customerSaveRequestDTO) {
        if(customerRepository.existsById(customerSaveRequestDTO.getCustomerId())) {
            Customer existingCustomer = customerRepository.getReferenceById(customerSaveRequestDTO.getCustomerId());

            existingCustomer.setCustomerName(customerSaveRequestDTO.getCustomerName());
            existingCustomer.setCustomerEmail(customerSaveRequestDTO.getCustomerEmail());
            existingCustomer.setCustomerPassword(customerSaveRequestDTO.getCustomerPassword());
            customerRepository.save(existingCustomer);

            return customerSaveRequestDTO.getCustomerId() + "Customer updated";
        } else {
            return customerSaveRequestDTO.getCustomerId() + "Customer not found";
        }
    }

    /**
     * Retrieves a customer by their unique ID.
     *
     * @param customerId The ID of the customer to retrieve.
     * @return A CustomerResponseDTO containing the customer's details, or null if the customer is not found.
     */
    @Override
    public CustomerResponseDTO getCustomerById(int customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
            customerResponseDTO.setCustomerId(customer.getCustomerId());
            customerResponseDTO.setCustomerName(customer.getCustomerName());
            customerResponseDTO.setCustomerEmail(customer.getCustomerEmail());
            return customerResponseDTO;
        } else {
            return null;
        }
    }

    /**
     * Allows a customer to buy tickets from a vendor.
     *
     * @param customerId The ID of the customer.
     * @param vendorId The ID of the vendor.
     * @param noOfTickets The number of tickets to purchase.
     * @return True if the purchase is successful, false otherwise.
     */
    @Override
    public boolean buyTickets(int customerId, int vendorId, int noOfTickets) {
        if (noOfTickets <= 0) {
            return false;
        }

        VendorResponseDTO vendorResponseDTO = vendorService.getVendorByID(vendorId);
        if (vendorResponseDTO == null) {
            return false;
        }

        int availableTickets = ticketPool.getAvailableTickets(vendorId);

        if (availableTickets < noOfTickets) {
            return false;
        }

        boolean purchased = ticketPool.buyTickets(customerId, vendorId, noOfTickets);

        if (purchased) {
            TicketPurchase ticketPurchase = new TicketPurchase();
            ticketPurchase.setCustomerId(customerId);
            ticketPurchase.setVendorId(vendorId);

            ticketPurchase.setNoOfTickets(noOfTickets);

            ticketPurchaseService.saveTicketPurchase(ticketPurchase);
        }

        return purchased;
    }

    /**
     * Validates a customer's login credentials.
     *
     * @param customerEmail The email address of the customer.
     * @param customerPassword The password of the customer.
     * @return A CustomerResponseDTO containing the customer's details if the login is successful, or null if the credentials are invalid.
     */
    @Override
    public CustomerResponseDTO loginCustomer(String customerEmail, String customerPassword) {
        Customer customer = customerRepository.findByCustomerEmailAndCustomerPassword(customerEmail, customerPassword);

        if (customer != null) {
            CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
            customerResponseDTO.setCustomerId(customer.getCustomerId());
            customerResponseDTO.setCustomerName(customer.getCustomerName());
            return customerResponseDTO;
        }

        return null;
    }

    /**
     * Retrieves a list of ticket purchases made by a specific customer.
     *
     * @param customerId The ID of the customer whose ticket purchases are to be retrieved.
     * @return A list of TicketPurchaseResponseDTO objects.
     */
    @Override
    public List<TicketPurchaseResponseDTO> getTicketsPurchasesByCustomer(int customerId) {
        List<TicketPurchase> ticketPurchases = ticketPurchaseService.getTicketPurchasesByCustomerId(customerId);

        List<TicketPurchaseResponseDTO> responseDTOs = new ArrayList<>();

        for(TicketPurchase ticketPurchase : ticketPurchases){
            TicketPurchaseResponseDTO ticketPurchaseResponseDTO = new TicketPurchaseResponseDTO();

            ticketPurchaseResponseDTO.setTicketId(ticketPurchase.getId());
            ticketPurchaseResponseDTO.setVendorId(ticketPurchase.getVendorId());
            ticketPurchaseResponseDTO.setNoOfTickets(ticketPurchase.getNoOfTickets());

            Vendor vendor = ticketPurchase.getVendor();
            if (vendor != null) {
                ticketPurchaseResponseDTO.setVendorId(vendor.getVendorID());
                ticketPurchaseResponseDTO.setVendorEmail(vendor.getVendorEmail());
            }

            responseDTOs.add(ticketPurchaseResponseDTO);
        }

        return responseDTOs;
    }

    /**
     * Retrieves a list of all available events.
     *
     * @return A list of VendorResponseDTO objects containing event details.
     */
    @Override
    public List<VendorResponseDTO> getAllEvents() {
       List<Vendor> vendors = vendorRepository.findAll();
       List<VendorResponseDTO> responseDTOs = new ArrayList<>();

       for(Vendor vendor : vendors){
           VendorResponseDTO vendorResponseDTO = new VendorResponseDTO(
                   vendor.getVendorID(),
                   vendor.getEventName(),
                   vendor.getEventDescription(),
                   vendor.getEventLocation(),
                   vendor.getEventDate(),
                   vendor.getEventTime(),
                   vendor.getEventPrice(),
                   vendor.getEventTickets()
           );
           responseDTOs.add(vendorResponseDTO);
       }
       return responseDTOs;
    }

    /**
     * Retrieves the event details for a specific vendor.
     *
     * @param vendorId The ID of the vendor whose event details are to be retrieved.
     * @return A VendorResponseDTO containing the vendor's event details, or null if the vendor is not found.
     */
    @Override
    public VendorResponseDTO getVendorEventDetails(int vendorId) {
        Optional<Vendor> vendor = vendorRepository.findByVendorID(vendorId);

        if (vendor.isPresent()) {
            Vendor vendorDetails = vendor.get();
            return  new VendorResponseDTO(
                    vendorDetails.getEventName(),
                    vendorDetails.getEventDescription(),
                    vendorDetails.getEventLocation(),
                    vendorDetails.getEventDate(),
                    vendorDetails.getEventTime(),
                    vendorDetails.getEventPrice()
            );
        } else {
            return null;
        }
    }


}
