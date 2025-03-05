package com.ticketingSystem.backend.repository;

import com.ticketingSystem.backend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The CustomerRepository interface provides methods for managing customer data in the database.
 * It extends JpaRepository to inherit standard CRUD operations and adds a custom query method.
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    /**
     * Finds a customer by their email and password.
     *
     * @param customerEmail The email address of the customer.
     * @param customerPassword The password of the customer.
     * @return The Customer entity if found, otherwise null.
     */
    Customer findByCustomerEmailAndCustomerPassword(String customerEmail, String customerPassword);
}
