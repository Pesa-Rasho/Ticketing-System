package com.ticketingSystem.backend.services;

import com.ticketingSystem.backend.entity.TicketPurchase;

import java.util.List;

/**
 * The TicketPurchaseService interface defines methods for managing ticket purchases.
 */
public interface TicketPurchaseService {
    /**
     * Saves a ticket purchase to the database.
     *
     * @param ticketPurchase The ticket purchase object to be saved.
     */
    void saveTicketPurchase(TicketPurchase ticketPurchase);

    /**
     * Retrieves a list of ticket purchases made by a specific customer.
     *
     * @param customerId The ID of the customer whose ticket purchases are to be retrieved.
     * @return A list of TicketPurchase objects.
     */
    List<TicketPurchase> getTicketPurchasesByCustomerId(int customerId);
}
