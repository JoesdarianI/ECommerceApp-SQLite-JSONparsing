package com.example.projectmcs.DatabaseSQLlite;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.projectmcs.Model.Transaction;
import com.example.projectmcs.Model.Users;
import com.example.projectmcs.Model.ProductModel;

import java.util.ArrayList;

public class DBEditorHelper {
    Context context;
    DBhelper dBhelper;
    SQLiteDatabase db;


    public DBEditorHelper(Context context) {
        this.context = context;
    }

    public void open() throws SQLException{
        dBhelper = new DBhelper(context);
        db = dBhelper.getWritableDatabase();
    }

    public void close() throws SQLException{
        dBhelper.close();
    }

    public void insertUsers(Users users){
        String query = String.format("INSERT INTO users ( userEmail, userUsername, userPhonenumber, userPassword ) VALUES ('%s','%s','%s','%s')",
                users.getUserEmail(),users.getUserUsername(),users.getUserPhone(),users.getUserPassword());
        db.execSQL(query);
    }

    public ArrayList<Users> listUsers(){
        ArrayList<Users> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();

        Users tempU;
        int UserId;
        String UserEmail, UserUsername, UserPhoneNumber, UserPassword;
        if(cursor.getCount() > 0){
            do {
                UserId = cursor.getInt(cursor.getColumnIndexOrThrow("userId"));
                UserEmail = cursor.getString(cursor.getColumnIndexOrThrow("userEmail"));
                UserUsername = cursor.getString(cursor.getColumnIndexOrThrow("userUsername"));
                UserPhoneNumber = cursor.getString(cursor.getColumnIndexOrThrow("userPhonenumber"));
                UserPassword = cursor.getString(cursor.getColumnIndexOrThrow("userPassword"));

                tempU = new Users(UserId, UserEmail, UserUsername, UserPhoneNumber, UserPassword);
                users.add(tempU);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return users;
    }

    public Users listUserById(int id){
        String query = "SELECT * FROM users WHERE userId = " + id;
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();

        int UserId;
        String UserEmail, UserUsername, UserPhoneNumber, UserPassword;

        UserId = cursor.getInt(cursor.getColumnIndexOrThrow("userId"));
        UserEmail = cursor.getString(cursor.getColumnIndexOrThrow("userEmail"));
        UserUsername = cursor.getString(cursor.getColumnIndexOrThrow("userUsername"));
        UserPhoneNumber = cursor.getString(cursor.getColumnIndexOrThrow("userPhonenumber"));
        UserPassword = cursor.getString(cursor.getColumnIndexOrThrow("userPassword"));
        cursor.close();

        return new Users(UserId,UserEmail,UserUsername,UserPhoneNumber,UserPassword);
    }

    public void updateUsername(int userId,String newUsername){
        String query = String.format("UPDATE users SET userUsername = '%s' WHERE userId = %d ",newUsername,userId);
        db.execSQL(query);
    }

    public void deleteUser(int userId){
        String query = String.format("DELETE FROM users WHERE userId = %d",userId);
        db.execSQL(query);
    }

    public void insertProduct(ProductModel product){
        String query = String.format("INSERT INTO furnitures (product_name, price , rating , description,image) VALUES ('%s','%d','%s','%s','%s')",
               product.getName(),product.getPrice(),product.getRating(),product.getDecription(),product.getImg());
        db.execSQL(query);
    }

    public ArrayList<ProductModel> listProduct(){
        ArrayList<ProductModel> productModels = new ArrayList<>();
        String query = "SELECT * FROM furnitures";
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();

        ProductModel temp;
        int productId,productPrice;
        String productName, productRating, productDescription, productImage;
        if(cursor.getCount() > 0){
            do {
                productId = cursor.getInt(cursor.getColumnIndexOrThrow("product_Id"));
                productName = cursor.getString(cursor.getColumnIndexOrThrow("product_name"));
                productPrice = cursor.getInt(cursor.getColumnIndexOrThrow("price"));
                productRating = cursor.getString(cursor.getColumnIndexOrThrow("rating"));
                productDescription = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                productImage = cursor.getString(cursor.getColumnIndexOrThrow("image"));

                temp = new ProductModel(productId, productImage, productName, productDescription, productRating, productPrice);
                productModels.add(temp);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return productModels;
    }

    public ProductModel listProductbyId(int id){
        String query = "SELECT * FROM furnitures WHERE product_Id = " + id;
        Cursor cursor = db.rawQuery(query,null);

        cursor.moveToFirst();
        int pId,pPrice;
        String pName, pRating, pDescription, pImage;

                pId = cursor.getInt(cursor.getColumnIndexOrThrow("product_Id"));
                pName = cursor.getString(cursor.getColumnIndexOrThrow("product_name"));
                pPrice = cursor.getInt(cursor.getColumnIndexOrThrow("price"));
                pRating = cursor.getString(cursor.getColumnIndexOrThrow("rating"));
                pDescription = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                pImage = cursor.getString(cursor.getColumnIndexOrThrow("image"));
                cursor.close();

        return new ProductModel(pId, pImage, pName, pDescription, pRating, pPrice);
    }


    public void insertHistory(Transaction transaction){
        String query = String.format("INSERT INTO history ( tQuantity, tPrice, tName, transaction_Date ) VALUES ('%d', '%d', '%s', '%s')",
                transaction.getQuantity(),transaction.getPrice(),transaction.getName(),transaction.getDate());
        db.execSQL(query);
    }

    public ArrayList<Transaction> listTransaction(){
        ArrayList<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM history";
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();

        Transaction temp;
        int transactionId,usersId,productId,quantity,price;
        String name,date;
        if(cursor.getCount() > 0){
            do {
                transactionId = cursor.getInt(cursor.getColumnIndexOrThrow("transaction_Id"));
                productId = cursor.getInt(cursor.getColumnIndexOrThrow("product_Id"));
                date = cursor.getString(cursor.getColumnIndexOrThrow("transaction_Date"));
                name = cursor.getString(cursor.getColumnIndexOrThrow("tName"));
                usersId = cursor.getInt(cursor.getColumnIndexOrThrow("userId"));
                quantity = cursor.getInt(cursor.getColumnIndexOrThrow("tQuantity"));
                price = cursor.getInt(cursor.getColumnIndexOrThrow("tPrice"));

                temp = new Transaction(transactionId,productId,usersId,quantity,price,name,date);
                transactions.add(temp);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return transactions;
    }

}
