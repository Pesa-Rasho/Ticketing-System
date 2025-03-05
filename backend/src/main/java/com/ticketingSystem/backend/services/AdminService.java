package com.ticketingSystem.backend.services;

import com.ticketingSystem.backend.model.TicketPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The AdminService class provides methods for managing the ticket pool system.
 * It allows starting and stopping the system, which controls the ticket pool operations.
 */
@Service
public class AdminService {

    @Autowired
    private TicketPool ticketPool;

    /**
     * Starts the ticket pool system.
     *
     * @return True if the system starts successfully, false otherwise.
     */
    public boolean startSystem() {
        try{
            System.out.println("System started");
            ticketPool.startOperations();
            return true;
        } catch (Exception e) {
            System.out.println("Error starting System : " + e.getMessage());
            return false;
        }
    }

    /**
     * Stops the ticket pool system.
     *
     * @return True if the system stops successfully, false otherwise.
     */
    public boolean stopSystem() {
        try {
            System.out.println("System stopped");
            ticketPool.stopOperations();
            return true;
        } catch (Exception e) {
            System.out.println("Error stopping System : " + e.getMessage());
            return false;
        }
    }
}
