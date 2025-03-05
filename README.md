# Real-Time Event Ticketing System

## Project Description

The **Real-Time Event Ticketing System** is a robust platform designed to streamline the management of event tickets by handling concurrent ticket releases and purchases efficiently. By utilizing advanced multi-threading and synchronization techniques, the system ensures high performance and data integrity in a dynamic environment. It offers distinct dashboards for vendors and customers, allowing seamless interaction and management of events and tickets. The entire system workflow is managed by an admin through a Command Line Interface (CLI), ensuring a centralized control mechanism.

This project aims to address the complexities of real-time ticketing by incorporating advanced concurrency handling, maintaining transactional accuracy, and reducing common issues that arise in multi-threaded systems. With its user-friendly interfaces and backend reliability, the system is tailored for both event organizers and attendees.

### Key Features
- **Admin Features:**
  - Configure the system using a CLI interface to set parameters such as ticket release rate, customer retrieval rate, and maximum ticket capacity.
  - Initiate or stop the ticket pool, enabling or disabling ticket addition and purchase functionality.

- **Vendor Features:**
  - Login/Register through the user-friendly frontend interface.
  - Access a dedicated dashboard to:
    - Update and manage personal account details.
    - Create and manage a single event.
    - View details of tickets purchased by customers.

- **Customer Features:**
  - Login/Register through the intuitive frontend interface.
  - Access a personalized dashboard to:
    - View a list of available events in a structured table format.
    - Purchase tickets using a simple and secure purchase form.
    - View a detailed history of their purchased transactions.
    - Update and manage personal account information.

- **Concurrency Handling:**
  - Multi-threaded environment supporting simultaneous activities of producers (vendors) and consumers (customers).
  - Utilizes synchronization to minimize concurrency issues and ensure smooth operations.

### Technology Stack
The system is built with a modern and efficient technology stack to ensure scalability, maintainability, and user satisfaction:
- **Frontend:** React.js - For creating responsive and dynamic user interfaces.
- **Backend:** Spring Boot - For handling business logic and backend operations.

## Installation

### Prerequisites
Before setting up the project, ensure your system meets the following requirements:
- **Node.js** and **npm** installed for the frontend.
- **Java JDK** and **Maven** installed for the backend.

### Steps

**Admin Configuration:**
   Use the CLI to configure the system parameters and start the ticket pool. This step is crucial to enabling ticket-related functionalities for vendors and customers.
   Then Cutomers and Vendors can add buy tickets through the ticketpool. When the Admin stop the ticketpool ticket transactions also stop.

## Usage
1. **Admin:**
   - Configure and manage the system using the CLI.
   - Set the ticket release rate, customer retrieval rate, and maximum ticket capacity.
   - Start or stop the ticket pool to control system functionality.

2. **Vendors:**
   - Log in to access their dashboard.
   - Add and manage a single event, view customer purchases, and update account details.

3. **Customers:**
   - Log in to view available events, purchase tickets, and manage their transaction history.

## Credits
This project was made possible with the support and guidance of friends and lecturers who provided valuable insights and encouragement throughout the development process.

# Contact Information
For inquiries or support, reach out via:
- **Email:** pesandurashodya@gmail.com
- **Phone:** +94 71-3466 180
- **LinkedIn:** www.linkedin.com/in/pesandu-subhasinghe
