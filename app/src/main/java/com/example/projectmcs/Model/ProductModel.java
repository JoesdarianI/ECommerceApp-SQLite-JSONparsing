package com.example.projectmcs.Model;

public class ProductModel {

    int id;
    String img;
    String name;
    String decription;
    String rating;
    int price;

    public ProductModel(int id, String img, String name, String decription, String rating, int price) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.decription = decription;
        this.rating = rating;
        this.price = price;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
