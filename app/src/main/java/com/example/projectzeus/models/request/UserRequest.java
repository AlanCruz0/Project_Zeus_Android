package com.example.projectzeus.models.request;

public class UserRequest {
    private String password;
    private String email;

    public String getPassword() { return password; }

    public UserRequest(String email, String password) {
        this.password = password;
        this.email = email;
    }

    public void setPassword(String value) { this.password = value; }

    public String getEmail() { return email; }
    public void setEmail(String value) { this.email = value; }
}
