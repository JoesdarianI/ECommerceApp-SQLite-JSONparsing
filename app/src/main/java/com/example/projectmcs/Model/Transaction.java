package com.example.projectmcs.Model;

public class Transaction {


     int transactionId;
     int productId;
     int userId;
     int quantity,price;
     String date,name;


    public Transaction(int transactionId, int productId, int userId, int quantity,int price,String name, String date) {
        this.transactionId = transactionId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.name =name;
        this.userId = userId;
        this.date = date;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
