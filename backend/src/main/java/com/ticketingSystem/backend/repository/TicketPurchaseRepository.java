package com.ticketingSystem.backend.repository;

import com.ticketingSystem.backend.entity.TicketPurchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The TicketPurchaseRepository interface provides methods for managing ticket purchase data in the database.
 * It extends JpaRepository to inherit standard CRUD operations and adds custom query methods.
 */
public interface TicketPurchaseRepository extends JpaRepository<TicketPurchase, Integer> {

    /**
     * Retrieves a list of ticket purchases made by a specific customer.
     *
     * @param customerId The ID of the customer whose ticket purchases are to be retrieved.
     * @return A list of TicketPurchase entities.
     */
    List<TicketPurchase> findByCustomerId(int customerId);

    /**
     * Retrieves a list of ticket purchases for a specific vendor.
     *
     * @param vendorID The ID of the vendor whose ticket purchases are to be retrieved.
     * @return A list of TicketPurchase entities.
     */
    List<TicketPurchase> findByVendorId(int vendorID);


}
