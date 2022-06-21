package com.users.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserModel {

    private String id = "";

    @NotBlank(message = "Full name is required")
    @Pattern(regexp = "(^[a-zA-Z\\-\\s]+$)|(^[а-яА-Я\\-\\s]+$)", message = "Full name must contain only latin or cyrillic letters")
    private String name;

    @NotBlank(message = "Pin is required")
    @Pattern(regexp = "[0-9]{10}", message = "Pin must be 10 digit long")
    private String pin;

    @NotBlank(message = "Address type is required")
    private String addrType;

    private String addrInfo;

    @NotBlank(message = "Email type is required")
    private String emailType;

    @Email(message = "Email is not valid")
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getAddrType() {
        return addrType;
    }

    public void setAddrType(String addrType) {
        this.addrType = addrType;
    }

    public String getAddrInfo() {
        return addrInfo;
    }

    public void setAddrInfo(String addrInfo) {
        this.addrInfo = addrInfo;
    }

    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
