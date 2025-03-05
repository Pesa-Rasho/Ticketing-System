package com.ticketingSystem.backend.controller;

import com.ticketingSystem.backend.dto.request.VendorSaveRequestDTO;
import com.ticketingSystem.backend.dto.response.TicketPurchaseResponseDTO;
import com.ticketingSystem.backend.dto.response.VendorResponseDTO;
import com.ticketingSystem.backend.entity.TicketPurchase;
import com.ticketingSystem.backend.entity.Vendor;
import com.ticketingSystem.backend.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * The VendorController class handles HTTP requests related to vendor operations.
 * It provides endpoints for creating, updating, and retrieving vendor and event details, as well as vendor login.
 */
@RestController
@CrossOrigin
@RequestMapping("/api/v1/vendor")
public class VendorController {


    @Autowired
    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    /**
     * Creates a new vendor based on the provided request data.
     *
     * @param vendorSaveRequestDTO The request data containing vendor details.
     * @return A message indicating the result of the operation.
     */
    @PostMapping(path = "/create-vendor")
    public String createVendor(@RequestBody VendorSaveRequestDTO vendorSaveRequestDTO) {
        String message = vendorService.createVendor(vendorSaveRequestDTO);
        return message;
    }

    /**
     * Updates an existing vendor based on the provided request data.
     *
     * @param vendorSaveRequestDTO The request data containing updated vendor details.
     * @return A message indicating the result of the operation.
     */
    @PutMapping(path = "/update-vendor")
    public String updateVendor(@RequestBody VendorSaveRequestDTO vendorSaveRequestDTO) {
        String message = vendorService.updateVendor(vendorSaveRequestDTO);
        return message;
    }

    /**
     * Retrieves a vendor by their unique ID.
     *
     * @param vendorID The ID of the vendor to retrieve.
     * @return A VendorResponseDTO containing the vendor's details.
     */
    @GetMapping(path = "/get-vendor-by-id", params = "id")
    public VendorResponseDTO getVendorById(@RequestParam (value = "id") int vendorID) {
        VendorResponseDTO vendorResponseDTO = vendorService.getVendorByID(vendorID);
        return vendorResponseDTO; 
    }

    /**
     * Adds an event to an existing vendor.
     *
     * @param vendorId The ID of the vendor to whom the event will be added.
     * @param eventDetails The details of the event to add.
     * @return A message indicating the result of the operation.
     */
    @PostMapping(path = "/create-event")
    public String addEventToVendor(
            @RequestParam int vendorId,
            @RequestBody Vendor eventDetails) {
        Vendor addedEvent = vendorService.addEventToVendor(vendorId, eventDetails);

        if (addedEvent != null) {
            return "successful";
        } else {
            return "failed";
        }
    }

    /**
     * Retrieves the event details for a specific vendor.
     *
     * @param vendorID The ID of the vendor whose event details are to be retrieved.
     * @return A VendorResponseDTO containing the vendor's event details.
     * @throws ResponseStatusException If the vendor is not found.
     */
    @GetMapping(path = "/get-vendor-event", params = "id")
    public VendorResponseDTO getVendorEventById(@RequestParam(value = "id") int vendorID) {
        VendorResponseDTO vendorResponseDTO = vendorService.getVendorEventDetails(vendorID);
        if (vendorResponseDTO == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vendor Not Found");
        }

        return vendorResponseDTO;
    }

    /**
     * Retrieves a list of ticket purchases for a specific vendor.
     *
     * @param vendorID The ID of the vendor whose ticket purchases are to be retrieved.
     * @return A list of TicketPurchaseResponseDTO objects.
     */
    @GetMapping(path = "/get-vendor-tickets")
    public List<TicketPurchaseResponseDTO> getTicketsForVendor(@RequestParam int vendorID) {
        return vendorService.getTicketsByVendorId(vendorID);
    }

    /**
     * Validates a vendor's login credentials.
     *
     * @param vendorEmail The email address of the vendor.
     * @param vendorPassword The password of the vendor.
     * @return A VendorResponseDTO containing the vendor's details if the login is successful.
     * @throws ResponseStatusException If the vendor login fails.
     */
    @PostMapping(path = "/vendor-login")
    public VendorResponseDTO vendorLogin (@RequestParam String vendorEmail, @RequestParam String vendorPassword) {
        VendorResponseDTO loginResponse = vendorService.validateVendorLogin(vendorEmail, vendorPassword);
        if (loginResponse != null) {
            return loginResponse;
        } else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Vendor Login Failed");
        }
    }
}
