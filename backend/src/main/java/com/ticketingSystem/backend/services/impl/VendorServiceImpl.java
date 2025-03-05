package com.ticketingSystem.backend.services.impl;

import com.ticketingSystem.backend.dto.request.VendorSaveRequestDTO;
import com.ticketingSystem.backend.dto.response.TicketPurchaseResponseDTO;
import com.ticketingSystem.backend.dto.response.VendorResponseDTO;
import com.ticketingSystem.backend.entity.TicketPurchase;
import com.ticketingSystem.backend.entity.Vendor;
import com.ticketingSystem.backend.model.TicketPool;
import com.ticketingSystem.backend.repository.TicketPurchaseRepository;
import com.ticketingSystem.backend.repository.VendorRepository;
import com.ticketingSystem.backend.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The VendorServiceImpl class provides the implementation for managing vendors and their events.
 */
@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private TicketPool ticketPool;

    @Autowired
    private TicketPurchaseRepository ticketPurchaseRepository;

    /**
     * Creates a new vendor based on the provided request data.
     *
     * @param vendorSaveRequestDTO The request data containing vendor details.
     * @return A message indicating the result of the operation.
     */
    @Override
    public String createVendor(VendorSaveRequestDTO vendorSaveRequestDTO) {
        Vendor vendor = new Vendor(
                vendorSaveRequestDTO.getVendorID(),
                vendorSaveRequestDTO.getVendorName(),
                vendorSaveRequestDTO.getVendorEmail(),
                vendorSaveRequestDTO.getVendorPassword()
        );

        vendorRepository.save(vendor);
        return "saved";
    }

    /**
     * Updates an existing vendor based on the provided request data.
     *
     * @param vendorSaveRequestDTO The request data containing updated vendor details.
     * @return A message indicating the result of the operation.
     */
    @Override
    public String updateVendor(VendorSaveRequestDTO vendorSaveRequestDTO) {
        if (vendorRepository.existsById(vendorSaveRequestDTO.getVendorID())) {
            Vendor vendor = vendorRepository.getReferenceById(vendorSaveRequestDTO.getVendorID());

            vendor.setVendorName(vendorSaveRequestDTO.getVendorName());
            vendor.setVendorEmail(vendorSaveRequestDTO.getVendorEmail());
            vendor.setVendorPassword(vendorSaveRequestDTO.getVendorPassword());
            vendorRepository.save(vendor);
            return vendorSaveRequestDTO.getVendorID() + " updated";
        }else {
            return "vendor not found";
        }
    }

    /**
     * Retrieves a vendor by their unique ID.
     *
     * @param vendorID The ID of the vendor to retrieve.
     * @return A VendorResponseDTO containing the vendor's details, or null if the vendor is not found.
     */
    @Override
    public VendorResponseDTO getVendorByID(int vendorID) {
        if (vendorRepository.existsById(vendorID)) {
            Vendor vendor = vendorRepository.getReferenceById(vendorID);

            VendorResponseDTO vendorResponseDTO = new VendorResponseDTO(
                    vendor.getVendorName(),
                    vendor.getVendorEmail(),
                    vendor.getVendorPassword()
            );

            return vendorResponseDTO;
        } else {
            return null;
        }
    }

    /**
     * Adds an event to an existing vendor.
     *
     * @param vendorId The ID of the vendor to whom the event will be added.
     * @param eventDetails The details of the event to add.
     * @return The updated Vendor entity.
     */
    @Override
    public Vendor addEventToVendor(int vendorId, Vendor eventDetails) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        vendor.setEventName(eventDetails.getEventName());
        vendor.setEventDate(eventDetails.getEventDate());
        vendor.setEventTime(eventDetails.getEventTime());
        vendor.setEventLocation(eventDetails.getEventLocation());
        vendor.setEventDescription(eventDetails.getEventDescription());
        vendor.setEventPrice(eventDetails.getEventPrice());
        vendor.setEventTickets(eventDetails.getEventTickets());

        ticketPool.addVendorFromRepository(vendorId);

        ticketPool.addTickets(vendorId, eventDetails.getEventTickets());

        vendorRepository.save(vendor);

        return vendor;
    }

    /**
     * Retrieves the event details for a vendor by their unique ID.
     *
     * @param vendorID The ID of the vendor whose event details are to be retrieved.
     * @return A VendorResponseDTO containing the vendor's event details, or null if the vendor is not found.
     */
    @Override
    public VendorResponseDTO getVendorEventDetails(int vendorID) {
        Optional<Vendor> vendorOptional = vendorRepository.findById(vendorID);
        if (vendorOptional.isPresent()) {
            Vendor vendor = vendorOptional.get();
            VendorResponseDTO vendorResponseDTO = new VendorResponseDTO(
                    vendor.getEventName(),
                    vendor.getEventDescription(),
                    vendor.getEventLocation(),
                    vendor.getEventDate(),
                    vendor.getEventTime(),
                    vendor.getEventPrice(),
                    vendor.getEventTickets()
            );
            return vendorResponseDTO;
        } else {
            return null;
        }
    }

    /**
     * Retrieves a list of ticket purchases made for a specific vendor.
     *
     * @param vendorID The ID of the vendor whose ticket purchases are to be retrieved.
     * @return A list of TicketPurchaseResponseDTO objects.
     */
    @Override
    public List<TicketPurchaseResponseDTO> getTicketsByVendorId(int vendorID) {
        List<TicketPurchase> ticketPurchases = ticketPurchaseRepository.findByVendorId(vendorID);
        List<TicketPurchaseResponseDTO> ticketPurchaseResponseDTOList = new ArrayList<>();

        for(TicketPurchase ticketPurchase: ticketPurchases){
            TicketPurchaseResponseDTO ticketPurchaseResponseDTO = new TicketPurchaseResponseDTO();
            ticketPurchaseResponseDTO.setTicketId(ticketPurchase.getId());
            ticketPurchaseResponseDTO.setEventName(ticketPurchase.getEventName());
            ticketPurchaseResponseDTO.setNoOfTickets(ticketPurchase.getNoOfTickets());
            ticketPurchaseResponseDTO.setVendorId(ticketPurchase.getVendorId());
            ticketPurchaseResponseDTO.setVendorEmail(ticketPurchase.getVendor().getVendorEmail());
            ticketPurchaseResponseDTO.setCustomerId(ticketPurchase.getCustomerId());
            ticketPurchaseResponseDTO.setCustomerName(ticketPurchase.getCustomer().getCustomerName());

            ticketPurchaseResponseDTOList.add(ticketPurchaseResponseDTO);
        }

        return ticketPurchaseResponseDTOList;
    }

    /**
     * Validates a vendor's login credentials.
     *
     * @param vendorEmail The email address of the vendor.
     * @param vendorPassword The password of the vendor.
     * @return A VendorResponseDTO containing the vendor's details if the login is successful, or null if the credentials are invalid.
     */
    @Override
    public VendorResponseDTO validateVendorLogin(String vendorEmail, String vendorPassword) {
        Vendor vendor = vendorRepository.findByVendorEmailAndVendorPassword(vendorEmail, vendorPassword);

        if (vendor != null) {
            VendorResponseDTO responseDTO = new VendorResponseDTO();
            responseDTO.setVendorId(vendor.getVendorID());
            responseDTO.setVendorName(vendor.getVendorName());
            return responseDTO;
        }
        return null;
    }
}
