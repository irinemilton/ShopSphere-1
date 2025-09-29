package com.shopsphere.dto;

public class UserDto {
    private String username;
    private String password;
    private String role;
    private String adminSecretKey;
    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getAdminSecretKey() { return adminSecretKey; }
    public void setAdminSecretKey(String adminSecretKey) { this.adminSecretKey = adminSecretKey; }
}