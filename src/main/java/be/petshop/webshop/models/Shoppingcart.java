package be.petshop.webshop.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Shoppingcart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int shoppingcartID;
    private int userID;
    private String status;

    public Shoppingcart(int userID, String status) {
        this.userID = userID;
        this.status = status;
    }

    public Shoppingcart() {
    }

    public int getShoppingcartID() {
        return shoppingcartID;
    }

    public void setShoppingcartID(int shoppingcartID) {
        this.shoppingcartID = shoppingcartID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
