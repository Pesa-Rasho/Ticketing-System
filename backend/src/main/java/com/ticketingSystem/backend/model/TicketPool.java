package com.ticketingSystem.backend.model;

import com.ticketingSystem.backend.entity.Vendor;
import com.ticketingSystem.backend.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

/**
 * The TicketPool class manages the ticket distribution and purchasing operations for multiple vendors.
 */
@Component
public class TicketPool {
    private final ConcurrentHashMap<Integer, Vendor> vendors = new ConcurrentHashMap<>();
    private final Map<Integer, LinkedList<Integer>> vendorTicketMap = new HashMap<>(); // Ticket pool for each vendor
    private final Map<Integer, LinkedList<Integer>> vendorOverflowMap = new HashMap<>(); // Overflow pool for each vendor

    private final ReentrantLock lock = new ReentrantLock();
    private boolean isOpen = false;


    private  int customerRetrievalRate;
    private int ticketReleaseRate;
    private int maxTicketCapacity;


    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);


    @Autowired
    private VendorRepository vendorRepository;

    /**
     * Constructor to initialize the TicketPool and load configuration.
     */
    public TicketPool() {
       loadConfig();
    }

    /**
     * Starts the ticket pool operations.
     */
    public void startOperations() {
        if (isOpen) {
            System.out.println("Ticket pool is already open");
            return;
        }
        isOpen = true;
        System.out.println("Ticket pool is now open");
    }

    /**
     * Stops the ticket pool operations.
     */
    public void stopOperations() {
        if (!isOpen) {
            System.out.println("Ticket pool is not open");
            return;
        }
        isOpen = false;
        scheduler.shutdown();
        System.out.println("Ticket pool is now closed");
    }

    /**
     * Adds a vendor from the repository to the ticket pool.
     *
     * @param vendorId The ID of the vendor to add.
     */
    public void addVendorFromRepository(int vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId).orElseThrow(() -> new RuntimeException("Vendor with ID " + vendorId + " not found"));

        vendors.put(vendor.getVendorID(), vendor);

        System.out.println("Vendor " + vendor.getVendorID() + " added to the ticket pool with max ticket capacity: " + maxTicketCapacity);
    }

    /**
     * Adds tickets to a vendor's pool.
     *
     * @param vendorId The ID of the vendor.
     * @param noOfTicketsToAdd The number of tickets to add.
     * @return True if the operation is successful, false otherwise.
     */
    public boolean addTickets(int vendorId, int noOfTicketsToAdd) {
        if(!isOpen){
        System.out.println("Ticket Pool is not open");
        return false;
        }
            lock.lock();
            try {
                Vendor vendor = vendors.get(vendorId);
                if (vendor == null) {
                    System.out.println("Vendor " + vendorId + " not found");
                    return false;
                }

                LinkedList<Integer> ticketPool = vendorTicketMap.computeIfAbsent(vendorId, k -> new LinkedList<>());
                LinkedList<Integer> overflowPool = vendorOverflowMap.computeIfAbsent(vendorId, k -> new LinkedList<>());

                int spaceAvailable = maxTicketCapacity - ticketPool.size();

                if (noOfTicketsToAdd <= spaceAvailable) {
                    for (int i = 0; i < noOfTicketsToAdd; i++) {
                        ticketPool.add(1);
                        try {
                            Thread.sleep(ticketReleaseRate);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        System.out.println("Ticket added to queue for Vendor " + vendorId);

                    }
                    System.out.println(noOfTicketsToAdd + " tickets added to Vendor " + vendorId + "'s ticket pool.");
                    logToFile(noOfTicketsToAdd + " tickets added to Vendor " + vendorId + "'s ticket pool.", vendorId, "vendor");
                } else {
                    for (int i = 0; i < spaceAvailable; i++) {
                        ticketPool.add(1);
                        try {
                            Thread.sleep(ticketReleaseRate);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        System.out.println("Ticket added to queue for Vendor " + vendorId);
                    }
                    int overflowTickets = noOfTicketsToAdd - spaceAvailable;
                    for (int i = 0; i < overflowTickets; i++) {
                        overflowPool.add(1);
                        try {
                            Thread.sleep(ticketReleaseRate);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        System.out.println("Ticket added to Over flow queue for Vendor " + vendorId);
                    }
                    System.out.println(spaceAvailable + " tickets added to Vendor " + vendorId + "'s ticket pool.");
                    System.out.println(overflowTickets + " tickets added to Vendor " + vendorId + "'s overflow pool.");
                    logToFile(noOfTicketsToAdd + " tickets added to Vendor " + vendorId + "'s ticket pool. " + overflowTickets + " tickets added to Vendor " + vendorId + "'s overflow pool.", vendorId, "vendor");
                }
                return true;
            } finally {
                lock.unlock();
            }
    }

    /**
     * Buys tickets from a vendor's pool.
     *
     * @param customerId The ID of the customer.
     * @param vendorId The ID of the vendor.
     * @param noOfTicketsToBuy The number of tickets to buy.
     * @return True if the operation is successful, false otherwise.
     */
    public boolean buyTickets(int customerId, int vendorId, int noOfTicketsToBuy) {
        if(!isOpen){
            System.out.println("Ticket Pool is not open");
            return false;
        }
        lock.lock();
        try {
            Vendor vendor = vendors.get(vendorId);
            if (vendor == null) {
                System.out.println("Vendor " + vendorId + " not found");
                return false;
            }

            LinkedList<Integer> ticketPool = vendorTicketMap.get(vendorId);
            LinkedList<Integer> overflowPool = vendorOverflowMap.get(vendorId);

            if (ticketPool == null || ticketPool.size() < noOfTicketsToBuy) {
                System.out.println("Not enough tickets available in Vendor " + vendorId + "'s ticket pool.");
                return false;
            }

            for (int i = 0; i < noOfTicketsToBuy; i++) {
                ticketPool.removeFirst();
                try {
                    Thread.sleep(customerRetrievalRate);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Customer : " + customerId + " purchased ticket " + i + "from for Vendor " + vendorId);
            }
            System.out.println(noOfTicketsToBuy + " tickets purchased by Customer " + customerId + " from Vendor " + vendorId);
            logToFile(noOfTicketsToBuy + " tickets purchased by Customer " + customerId + " from Vendor " + vendorId, customerId, "customer");

            int spaceAvailable = maxTicketCapacity - ticketPool.size();

            int ticketsToMove = Math.min(spaceAvailable, overflowPool.size());
            for (int i = 0; i < ticketsToMove; i++) {
                ticketPool.add(overflowPool.removeFirst());
                try {
                    Thread.sleep(ticketReleaseRate);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Ticket added from Over flow queue to Vendor " + vendorId);
            }
            System.out.println(ticketsToMove + " tickets moved from overflow to Vendor " + vendorId + "'s ticket pool.");
            return true;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Gets the number of available tickets for a vendor.
     *
     * @param vendorId The ID of the vendor.
     * @return The number of available tickets.
     */
    public int getAvailableTickets(int vendorId) {
        Vendor vendor = vendors.get(vendorId);
        if (vendor == null) {
            System.out.println("Vendor " + vendorId + " not found");
        }

        return vendor.getEventTickets();
    }

    /**
     * Logs a message to a file.
     *
     * @param logMessage The message to log.
     * @param id The ID of the entity (vendor or customer).
     * @param type The type of entity (vendor or customer).
     */
    private void logToFile(String logMessage, int id, String type) {
        try{
            File logFolder = new File("logs/" + type);
            if (!logFolder.exists()) {
                logFolder.mkdirs();
            }

            File logFile = new File(logFolder, id + ".txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
                writer.write(logMessage);
                writer.newLine();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads configuration settings from a file.
     */
    private void loadConfig() {
        File configFile = new File("C:\\Users\\User\\Desktop\\OOP FINAL CW\\cli\\system_config.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] config = line.split(": ");
                if (config.length == 2) {
                    String key = config[0].trim();
                    String value = config[1].trim();

                    switch (key) {
                        case "Ticket Release Rate":
                            this.ticketReleaseRate = Integer.parseInt(value);
                            break;
                        case "Customer Retrieval Rate":
                            this.customerRetrievalRate = Integer.parseInt(value);
                            break;
                        case "Max Ticket Capacity":
                            this.maxTicketCapacity = Integer.parseInt(value);
                            break;
                        default:
                            System.out.println("Unknown config key: " + key);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if the ticket pool is open.
     *
     * @return True if the ticket pool is open, false otherwise.
     */
    public boolean isOpen() {
        return isOpen;
    }
}
