package com.example.projectmcs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.SQLException;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectmcs.DatabaseSQLlite.DBEditorHelper;
import com.example.projectmcs.Model.ProductModel;
import com.example.projectmcs.Model.Transaction;
import com.example.projectmcs.Model.Users;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ProductDetail extends AppCompatActivity {
    public ImageView furnitureIMG;
    public TextView furnName,furnPrice,furnRating;
    public Calendar calendar;
    public SimpleDateFormat simpleDateFormat;
    public String date;
    public EditText quantity;
    public Button buyF;
    private Users users;
    DBEditorHelper dbEditorHelper = new DBEditorHelper(this);
    public int userId;
    private ProductModel product;
    public int furnitureId;
    int SmsPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        date = simpleDateFormat.format(calendar.getTime());
        SmsManager smsManager = SmsManager.getDefault();
        SmsPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        furnitureId = getIntent().getIntExtra("product_Id",-1);
        userId = getApplicationContext().getSharedPreferences("userId",MODE_PRIVATE).getInt("userId",-1);

        if(SmsPermission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);
        }

        init();

        try {
            dbEditorHelper.open();
            product = dbEditorHelper.listProductbyId(furnitureId);
            users = dbEditorHelper.listUserById(userId);
            dbEditorHelper.close();
        }catch (SQLException e){
            e.printStackTrace();
            return;
        }
        try {
            Picasso.get().load(product.getImg()).into(furnitureIMG);
            furnName.setText(product.getName());
            furnPrice.setText(Integer.toString(product.getPrice()));
            furnRating.setText(product.getRating());
        }catch (SQLException e){
            e.printStackTrace();
        }

        buyF.setOnClickListener(view -> {
            String quantityTxt = quantity.getText().toString();

            int quantityVal =Integer.parseInt(quantityTxt);
            int totalPrice = quantityVal * product.getPrice();

            ArrayList<Transaction> transactions= new ArrayList<>();

            if(quantityVal<0){
                Toast.makeText(ProductDetail.this, "Must be greater than 0", Toast.LENGTH_SHORT).show();
                return;
            }

            try{
                dbEditorHelper.open();
                dbEditorHelper.insertHistory(new Transaction(-1,-1, -1, quantityVal,totalPrice, product.getName(), date));
                dbEditorHelper.close();
                smsManager.sendTextMessage(users.getUserPhone(),null,"Transaction Success, Thank You",null,null);
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
    }

    public void init(){
        furnitureIMG = findViewById(R.id.imgDetail);
        furnName = findViewById(R.id.nameD);
        furnPrice = findViewById(R.id.priceD);
        furnRating = findViewById(R.id.ratingD);
        quantity = findViewById(R.id.quantity);
        buyF = findViewById(R.id.buybtn);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.mymenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent intent = new Intent(this,Home.class);
                startActivity(intent);
                break;
            case R.id.profile:
                Intent intent1 = new Intent(this,Profile.class);
                startActivity(intent1);
                break;
            case R.id.history:
                Intent intent2 = new Intent(this,History.class);
                startActivity(intent2);
                break;
            case R.id.aboutus:
                Intent intent3 = new Intent(this,AboutUs.class);
                startActivity(intent3);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}