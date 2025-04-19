package com.example.catify;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String imageName;
    private String description;
    private double price;
    private int quantity;

    private String category;
    private String subcategory;

    public Product(String name, String imageName, String description,
                   double price, int quantity, String category, String subcategory) {
        this.name = name;
        this.imageName = imageName;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.subcategory = subcategory;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getImageName() {
        return imageName;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }
}
