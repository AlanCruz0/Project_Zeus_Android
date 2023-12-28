package com.example.projectzeus.models.request;

public class RegisterRequest {
    private String password;
    private String name;
    private String email;

    public RegisterRequest(String name, String email, String password) {
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getPassword() { return password; }
    public void setPassword(String value) { this.password = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getEmail() { return email; }
    public void setEmail(String value) { this.email = value; }
}
