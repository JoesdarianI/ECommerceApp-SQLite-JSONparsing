package com.example.projectmcs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.projectmcs.DatabaseSQLlite.DBEditorHelper;
import com.example.projectmcs.Model.ProductModel;
import com.example.projectmcs.Model.Users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Login extends AppCompatActivity {

    EditText email, password;
    private Button login, register;
    SharedPreferences sp;
    private DBEditorHelper dbHelper = new DBEditorHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        sp = getApplicationContext().getSharedPreferences("userId", MODE_PRIVATE);
        init();

        login.setOnClickListener(v -> {

            String emailTxt = email.getText().toString();
            String passwordTxt = password.getText().toString();
            ArrayList<Users> users = new ArrayList<>();

            if (emailTxt.isEmpty()) {
                email.setError("Email cannot be Empty");
                return;
            }
            try {
                dbHelper.open();
                users = dbHelper.listUsers();
                for (Users user : users) {
                    if (user.getUserEmail().equals(emailTxt) && user.getUserPassword().equals(passwordTxt)) {
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putInt("userId", user.getUserID());
                        editor.apply();
                    }
                }
                dbHelper.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (sp.getInt("userId", -1) == -1) {
                Toast.makeText(Login.this, "Email or Password is Wrong", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                dbHelper.open();
                ArrayList<ProductModel> productModels = dbHelper.listProduct();
                if(productModels.size() == 0){
                    retrieveJSON();
                }
                dbHelper.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }
            Intent intent = new Intent(Login.this, Home.class);
            startActivity(intent);
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rRegister();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        sp = getApplicationContext().getSharedPreferences("userId", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("userId", -1);
        editor.apply();
        email.setText("");
        password.setText("");
        super.onResume();
    }

    private void init() {
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        login = findViewById(R.id.loginbtn);
        register = findViewById(R.id.buttonRegister);
    }

    public void rRegister() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void retrieveJSON() {
        String url = "https://bit.ly/InSOrmaJSON";
        RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dbHelper = new DBEditorHelper(Login.this);
                        try {
                                JSONArray jsonArray = response.getJSONArray("furnitures");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject furnitureJson = jsonArray.getJSONObject(i);
                                String productName = furnitureJson.getString("product_name");
                                int productPrice = furnitureJson.getInt("price");
                                String productRating = furnitureJson.getString("rating");
                                String productImg = furnitureJson.getString("image");
                                String productDescription = furnitureJson.getString("description");

                                ProductModel product = new ProductModel(-1, productImg, productName, productDescription, productRating, productPrice);
                                dbHelper.open();
                                dbHelper.insertProduct(product);
                                dbHelper.close();
                            }
                        } catch (JSONException | SQLException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }
}