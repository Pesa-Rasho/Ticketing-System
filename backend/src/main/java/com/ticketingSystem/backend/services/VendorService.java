package com.ticketingSystem.backend.services;

import com.ticketingSystem.backend.dto.request.VendorSaveRequestDTO;
import com.ticketingSystem.backend.dto.response.TicketPurchaseResponseDTO;
import com.ticketingSystem.backend.dto.response.VendorResponseDTO;

import com.ticketingSystem.backend.entity.Vendor;

import java.util.List;

/**
 * The VendorService interface defines methods for managing vendors and their events.
 */
public interface VendorService {

    /**
     * Creates a new vendor based on the provided request data.
     *
     * @param vendorSaveRequestDTO The request data containing vendor details.
     * @return A message indicating the result of the operation.
     */
    String createVendor(VendorSaveRequestDTO vendorSaveRequestDTO);

    /**
     * Updates an existing vendor based on the provided request data.
     *
     * @param vendorSaveRequestDTO The request data containing updated vendor details.
     * @return A message indicating the result of the operation.
     */
    String updateVendor(VendorSaveRequestDTO vendorSaveRequestDTO);

    /**
     * Retrieves a vendor by their unique ID.
     *
     * @param vendorID The ID of the vendor to retrieve.
     * @return A VendorResponseDTO containing the vendor's details.
     */
    VendorResponseDTO getVendorByID(int vendorID);

    /**
     * Adds an event to an existing vendor.
     *
     * @param vendorId The ID of the vendor to whom the event will be added.
     * @param eventDetails The details of the event to add.
     * @return The updated Vendor entity.
     */
    Vendor addEventToVendor(int vendorId, Vendor eventDetails);

    /**
     * Retrieves the event details for a vendor by their unique ID.
     *
     * @param vendorID The ID of the vendor whose event details are to be retrieved.
     * @return A VendorResponseDTO containing the vendor's event details.
     */
    VendorResponseDTO getVendorEventDetails(int vendorID);

    /**
     * Retrieves a list of tickets purchased for a vendor by their unique ID.
     *
     * @param vendorID The ID of the vendor whose tickets are to be retrieved.
     * @return A list of TicketPurchaseResponseDTO objects.
     */
    List<TicketPurchaseResponseDTO> getTicketsByVendorId(int vendorID);

    /**
     * Validates a vendor's login credentials.
     *
     * @param vendorEmail The email address of the vendor.
     * @param vendorPassword The password of the vendor.
     * @return A VendorResponseDTO containing the vendor's details if the login is successful.
     */
    VendorResponseDTO validateVendorLogin(String vendorEmail, String vendorPassword);
}