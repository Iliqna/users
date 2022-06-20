package com.users.entities;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_people", schema = "users_db")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "pin")
    private String pin;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "t_people_id", nullable = false)
    private final List<Address> addressList = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "t_people_id", nullable = false)
    private final List<Email> emailList = new ArrayList<>();

    public User() {
    }

    public User(String fullName, String pin) {
        this.fullName = fullName;
        this.pin = pin;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public List<Email> getEmailList() {
        return emailList;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name=" + fullName + "}";
    }
}
