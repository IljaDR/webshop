package be.petshop.webshop.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productID;
    private String name;
    private int stock;
    private String description;
    private int primary_pictureID;
    private int locationID;
    private int manufacturerID;

    public Product(String name, int stock, String description, int primary_pictureID, int locationID, int manufacturerID) {
        this.name = name;
        this.stock = stock;
        this.description = description;
        this.primary_pictureID = primary_pictureID;
        this.locationID = locationID;
        this.manufacturerID = manufacturerID;
    }

    public Product() {
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int id) {
        this.productID = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrimary_pictureID() {
        return primary_pictureID;
    }

    public void setPrimary_pictureID(int primary_pictureID) {
        this.primary_pictureID = primary_pictureID;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public int getManufacturerID() {
        return manufacturerID;
    }

    public void setManufacturerID(int manufacturerID) {
        this.manufacturerID = manufacturerID;
    }
}
