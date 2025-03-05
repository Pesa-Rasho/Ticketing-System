package com.ticketingSystem.backend.repository;

import com.ticketingSystem.backend.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * The VendorRepository interface provides methods for managing vendor data in the database.
 * It extends JpaRepository to inherit standard CRUD operations and adds custom query methods.
 */
@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer> {

    /**
     * Finds a vendor by their email and password.
     *
     * @param vendorEmail The email address of the vendor.
     * @param vendorPassword The password of the vendor.
     * @return The Vendor entity if found, otherwise null.
     */
    Vendor findByVendorEmailAndVendorPassword(String vendorEmail, String vendorPassword);

    /**
     * Retrieves all vendors from the database.
     *
     * @return A list of Vendor entities.
     */
    List<Vendor> findAll();

    /**
     * Finds a vendor by their unique ID.
     *
     * @param vendorID The ID of the vendor to retrieve.
     * @return An Optional containing the Vendor entity if found, otherwise an empty Optional.
     */
    Optional<Vendor> findByVendorID(int vendorID);

    /**
     * Retrieves a vendor by their unique ID.
     *
     * @param vendorId The ID of the vendor to retrieve.
     * @return The Vendor entity if found, otherwise null.
     */
    Vendor getVendorByVendorID(int vendorId);
}
