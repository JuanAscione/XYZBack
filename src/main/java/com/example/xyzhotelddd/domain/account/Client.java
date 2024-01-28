package com.example.xyzhotelddd.domain.account;

import com.example.xyzhotelddd.domain.payment.Wallet;
import com.example.xyzhotelddd.utils.Validators;
import jakarta.persistence.*;

import java.util.Random;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "randomPassword",nullable = false)
    private String randomPassword;

    @Column(name = "firstname",nullable = false)
    private String firstName;

    @Column(name = "lastname",nullable = false)
    private String lastName;

    @Column(name = "phone_number",nullable = false)
    private String phoneNumber;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private Wallet wallet;

    public Client(String email, String firstName, String lastName, String phoneNumber) throws Exception {
        Validators validators = new Validators();
        if(validators.validateName(firstName) && validators.validateName(lastName) && validators.validateEmail(email) && validators.validatePhoneNumber(phoneNumber)){
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
            this.phoneNumber = phoneNumber;
        }
        else{
            throw new Exception();
        }
    }

    public Client() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public void createEURWallet() {
        this.wallet = new Wallet(this, "EUR");
    }

    public void newRandomPassword() {
        this.randomPassword = String.valueOf(getRandomNumberUsingNextInt(100, 999));
    }

    public int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    public String getRandomPassword() {
        return randomPassword;
    }

    public void setRandomPassword(String randomPassword) {
        this.randomPassword = randomPassword;
    }
}
