package com.example.xyzhotelddd.http.dto;

import com.example.xyzhotelddd.domain.payment.Wallet;
import jakarta.persistence.*;

public class ClientResponseDTO {

    private Integer id;

    private String email;

    private String randomPassword;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    public ClientResponseDTO(Integer id, String email, String randomPassword, String firstName, String lastName, String phoneNumber) {
        this.id = id;
        this.email = email;
        this.randomPassword = randomPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRandomPassword() {
        return randomPassword;
    }

    public void setRandomPassword(String randomPassword) {
        this.randomPassword = randomPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
