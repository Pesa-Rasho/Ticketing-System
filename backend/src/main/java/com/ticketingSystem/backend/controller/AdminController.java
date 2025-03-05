package com.ticketingSystem.backend.controller;

import com.ticketingSystem.backend.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * The AdminController class handles HTTP requests related to administrative operations.
 * It provides endpoints to start and stop the system.
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    /**
     * Constructor for the AdminController.
     *
     * @param adminService The AdminService instance to inject.
     */
    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * Endpoint to start the system.
     *
     * @return A message indicating that the system has started successfully.
     */
    @PostMapping("/start")
    public String start() {
        adminService.startSystem();
        return "System started successfully";
    }

    /**
     * Endpoint to stop the system.
     *
     * @return A message indicating that the system has stopped successfully.
     */
    @PostMapping("/stop")
    public String stop() {
        adminService.stopSystem();
        return "System stopped successfully";
    }
}
