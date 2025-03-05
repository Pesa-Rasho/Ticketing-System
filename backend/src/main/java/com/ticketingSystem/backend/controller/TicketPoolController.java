package com.ticketingSystem.backend.controller;

import com.ticketingSystem.backend.model.TicketPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The TicketPoolController class handles HTTP requests related to ticket pool operations.
 * It provides an endpoint to check the status of the ticket pool.
 */
@RestController
@CrossOrigin
@RequestMapping("/api/v1/ticketpool")
public class TicketPoolController {
    @Autowired
    private TicketPool ticketPool;

    /**
     * Endpoint to get the status of the ticket pool.
     *
     * @return True if the ticket pool is open,     * False otherwise.
     */
    @GetMapping("/status")
    public boolean getTicketPoolStatus() {
        return ticketPool.isOpen();
    }
}
