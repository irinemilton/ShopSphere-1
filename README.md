ShopSphere: A Full-Stack E-commerce Application
ShopSphere is a complete, full-stack e-commerce web application built from the ground up using Java and the Spring Boot framework. It demonstrates a modern, feature-rich online store, including secure user authentication, role-based access control (Users vs. Admins), a dynamic product catalog, a fully functional shopping cart, and a comprehensive admin dashboard for managing the platform.

The application is designed with a clean, three-tier architecture and features a futuristic, elegant, and fully responsive user interface built with Thymeleaf and custom CSS.

Core Features
Secure Authentication: Robust user registration and login system handled by Spring Security, with password encryption using BCrypt.

Role-Based Authorization:

User Role: Can browse products, manage their personal shopping cart, and proceed to checkout.

Admin Role: Can access a secure admin dashboard to manage all aspects of the store.

Secret Key for Admins: A secure method for creating admin accounts during registration using a secret key defined in the application's configuration.

Full Product Management (Admin): Admins have full CRUD (Create, Read, Update, Delete) capabilities over the product catalog.

Full User Management (Admin): Admins can view and delete user accounts.

Dynamic Shopping Cart: Users can add products to a persistent shopping cart, with quantities updated automatically.

Interactive API Documentation: Integrated Swagger UI allows for easy viewing, testing, and understanding of all backend RESTful endpoints.

Tech Stack
Backend:

Java 17

Spring Boot 3

Spring Security 6 (for authentication and authorization)

Spring Data JPA / Hibernate (for database interaction)

Maven (for dependency management)

Frontend:

Thymeleaf (for server-side templating)

HTML5

CSS3 (with a modern, responsive, and futuristic design)

Database:

MySQL

API Documentation:

SpringDoc (Swagger UI)

Getting Started
Follow these instructions to get a local copy up and running on your machine.

Prerequisites
JDK 17 or later

Apache Maven

A running instance of MySQL Server

Setup and Installation
Clone the Repository:

git clone [https://github.com/your-username/ShopSphere.git](https://github.com/your-username/ShopSphere.git)
cd ShopSphere

Configure the Database:

Open your MySQL client and create a new database. The application is configured to use shopsphere_db by default.

Open the configuration file located at src/main/resources/application.properties.

Update the spring.datasource.password property with your MySQL root (or user) password.

(Optional) Change the admin.secret.key to a custom secret of your choice.

# /src/main/resources/application.properties

spring.datasource.password=your_mysql_password
admin.secret.key=SHOPSPHERE_SECRET_KEY_123

Build and Run the Application:

Open a terminal in the project's root directory.

Run the application using the Maven wrapper, which will handle downloading all necessary dependencies.

./mvnw spring-boot:run

The application will start and be accessible at http://localhost:8080.

Usage Guide
Register an Admin User:

Navigate to http://localhost:8080/register.

Fill in your desired admin credentials.

Change the "Register as" dropdown to "Admin".

An "Admin Secret Key" field will appear. Enter the key from your application.properties file (the default is SHOPSPHERE_SECRET_KEY_123).

Click "Register".

Log in as Admin:

Go to http://localhost:8080/login and sign in with your new admin credentials.

You will now have access to the "Admin Dashboard" link in the navbar.

Manage the Store:

As an admin, you can navigate to the dashboard to manage users and products (add, edit, and delete).

Accessing API Documentation:

With the application running, go to:

http://localhost:8080/swagger-ui.html

This will open the interactive Swagger UI, which documents all the backend API endpoints.

Project Architecture
The project is structured in a clean, maintainable way, following the principles of a three-tier architecture:

com.shopsphere.config: Contains all Spring Security and Swagger (OpenAPI) configuration classes.

com.shopsphere.controller: The presentation layer, which handles all incoming HTTP requests.

com.shopsphere.dto: Data Transfer Objects used to pass data between the controller and service layers, especially for forms.

com.shopsphere.entity: JPA entities that directly map to the database tables.

com.shopsphere.repository: Spring Data JPA interfaces responsible for all database communication.

com.shopsphere.service: The business logic layer, which orchestrates all application operations.
