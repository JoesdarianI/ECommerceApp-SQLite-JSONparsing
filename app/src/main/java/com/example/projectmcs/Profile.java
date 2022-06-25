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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectmcs.DatabaseSQLlite.DBEditorHelper;
import com.example.projectmcs.Model.Users;

public class Profile extends AppCompatActivity {

    TextView emailP,usernameP,phoneP;
    EditText usernameChange;
    Button save,logout,delete;
    private Users users;
    private DBEditorHelper dbEditorHelper = new DBEditorHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        init();

        int userId = getApplicationContext().getSharedPreferences("userId",MODE_PRIVATE).getInt("userId",-1);

        try{
            dbEditorHelper.open();
            users = dbEditorHelper.listUserById(userId);
            dbEditorHelper.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        emailP.setText(users.getUserEmail());
        usernameP.setText(users.getUserUsername());
        phoneP.setText(users.getUserPhone());

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usernameP.getVisibility() == View.INVISIBLE)
                {
                    usernameP.setVisibility(View.VISIBLE);
                } else
                {
                    usernameP.setVisibility(View.INVISIBLE);
                }

                String newUsername = usernameChange.getText().toString();

                try {
                    dbEditorHelper.open();
                    dbEditorHelper.updateUsername(userId,newUsername);
                    dbEditorHelper.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    dbEditorHelper.open();
                    dbEditorHelper.deleteUser(userId);
                    dbEditorHelper.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }

                Toast.makeText(Profile.this, "Account has been deleted", Toast.LENGTH_SHORT).show();
                Intent intentD = new Intent(Profile.this,Login.class);
                startActivity(intentD);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentL = new Intent(Profile.this,Login.class);
                startActivity(intentL);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
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

    private void init(){
        emailP = findViewById(R.id.emailP);
        usernameP = findViewById(R.id.usernameP);
        phoneP = findViewById(R.id.phoneP);
        usernameChange = findViewById(R.id.usernameETchange);
        save = findViewById(R.id.saveButton);
        logout = findViewById(R.id.logoutBtn);
        delete = findViewById(R.id.deleteBtn);
    }
}