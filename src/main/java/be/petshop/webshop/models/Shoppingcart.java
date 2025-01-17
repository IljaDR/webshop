package be.petshop.webshop.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Shoppingcart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int shoppingcartID;
    private int userID;
    private String status;

    @ManyToMany
    @JoinTable(name = "shoppingcart_product", joinColumns = @JoinColumn(name = "productID"),
            inverseJoinColumns = @JoinColumn(name = "shoppingcartID"))
    private List<Product> products;

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
