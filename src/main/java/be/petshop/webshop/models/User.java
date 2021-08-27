package be.petshop.webshop.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userID;
    private String hash;

    @Transient
    private String password;

    @NotBlank(message = "Name cannot be empty.")
    private String first_name;

    @NotBlank(message = "Family name cannot be empty.")
    private String family_name;

    @NotBlank(message = "Username cannot be empty.")
    private String username;
    private String address;
    private String payment_info;

    public User() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPayment_info() {
        return payment_info;
    }

    public void setPayment_info(String payment_info) {
        this.payment_info = payment_info;
    }
}
