package com.example.projectmcs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.projectmcs.DatabaseSQLlite.DBEditorHelper;
import com.example.projectmcs.Model.ProductModel;
import com.example.projectmcs.Model.Users;


import java.util.ArrayList;


public class Home extends AppCompatActivity {
    TextView usernameH;
    ListView listView;
    private Users users;
    private DBEditorHelper dbEditorHelper = new DBEditorHelper(this);
    ArrayList<ProductModel> productModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listView = findViewById(R.id.listview1);

        int userId = getApplicationContext().getSharedPreferences("userId", MODE_PRIVATE).getInt("userId", -1);

        try {
            dbEditorHelper.open();
            users = dbEditorHelper.listUserById(userId);
            productModels = dbEditorHelper.listProduct();
            dbEditorHelper.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        usernameH = findViewById(R.id.UsernameGreet);
        usernameH.setText("Hello "+ users.getUserUsername());
        HomeAdapter homeAdapter = new HomeAdapter(this,R.layout.product_item,productModels);
        listView.setAdapter(homeAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Home.this, ProductDetail.class);
                intent.putExtra("product_Id", productModels.get(i).getId());
                startActivity(intent);
            }
        });
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