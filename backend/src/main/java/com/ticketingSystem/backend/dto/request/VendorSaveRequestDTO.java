package com.ticketingSystem.backend.dto.request;

/**
 * The VendorSaveRequestDTO class represents a data transfer object (DTO) for vendor save requests.
 * It includes fields for vendor details and provides methods to access and modify these fields.
 */
public class VendorSaveRequestDTO {
    private int vendorID;

    private String vendorName;

    private String vendorEmail;

    private String vendorPassword;

    /**
     * Constructor for the VendorSaveRequestDTO with all vendor details.
     *
     * @param vendorID The unique identifier for the vendor.
     * @param vendorName The name of the vendor.
     * @param vendorEmail The email address of the vendor.
     * @param vendorPassword The password of the vendor.
     */
    public VendorSaveRequestDTO(int vendorID, String vendorName, String vendorEmail, String vendorPassword) {
        this.vendorID = vendorID;
        this.vendorName = vendorName;
        this.vendorEmail = vendorEmail;
        this.vendorPassword = vendorPassword;
    }

    /**
     * Constructor for the VendorSaveRequestDTO with vendor email and password.
     *
     * @param vendorEmail The email address of the vendor.
     * @param vendorPassword The password of the vendor.
     */
    public VendorSaveRequestDTO(String vendorEmail, String vendorPassword) {
        this.vendorEmail = vendorEmail;
        this.vendorPassword = vendorPassword;
    }

    /**
     * Default constructor for the VendorSaveRequestDTO.
     */
    public VendorSaveRequestDTO() {
    }

    public int getVendorID() {
        return vendorID;
    }

    public void setVendorID(int vendorID) {
        this.vendorID = vendorID;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorEmail() {
        return vendorEmail;
    }

    public void setVendorEmail(String vendorEmail) {
        this.vendorEmail = vendorEmail;
    }

    public String getVendorPassword() {
        return vendorPassword;
    }

    public void setVendorPassword(String vendorPassword) {
        this.vendorPassword = vendorPassword;
    }

}
