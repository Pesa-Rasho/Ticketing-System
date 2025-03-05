package com.ticketingSystem.backend.services.impl;

import com.ticketingSystem.backend.entity.TicketPurchase;
import com.ticketingSystem.backend.repository.TicketPurchaseRepository;
import com.ticketingSystem.backend.services.TicketPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The TicketPurchaseImpl class provides the implementation for managing ticket purchases.
 */
@Service
public class TicketPurchaseImpl implements TicketPurchaseService {

    @Autowired
    private TicketPurchaseRepository ticketPurchaseRepository;

    /**
     * Saves a ticket purchase to the database.
     *
     * @param ticketPurchase The ticket purchase object to be saved.
     */
    @Override
    public void saveTicketPurchase(TicketPurchase ticketPurchase) {
        ticketPurchaseRepository.save(ticketPurchase);
    }

    /**
     * Retrieves a list of ticket purchases made by a specific customer.
     *
     * @param customerId The ID of the customer whose ticket purchases are to be retrieved.
     * @return A list of TicketPurchase objects.
     */
    @Override
    public List<TicketPurchase> getTicketPurchasesByCustomerId(int customerId) {
        return ticketPurchaseRepository.findByCustomerId(customerId);
    }
}
