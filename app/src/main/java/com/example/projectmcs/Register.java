package com.example.projectmcs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectmcs.DatabaseSQLlite.DBEditorHelper;
import com.example.projectmcs.Model.Users;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    public EditText email,username,phonenumber,password;
    public Button login,register;
    private DBEditorHelper editorHelper = new DBEditorHelper(this);

    public static final String passwordPattern = "(?=.*[0-9])"+".{3,20}";
    public static final Pattern pattern =Pattern.compile(passwordPattern);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_register);

        init();

        register.setOnClickListener(v-> {

                String EmailTxt = email.getText().toString();
                String UsernameTxt = username.getText().toString();
                String PhonenumberTxt = phonenumber.getText().toString();
                String PasswordTxt = password.getText().toString();
                boolean error = false;
                ArrayList<Users> users = new ArrayList<>();
                try {
                    editorHelper.open();
                    users = editorHelper.listUsers();
                    editorHelper.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                if (EmailTxt.isEmpty()) {
                    error = true;
                    email.setError("Email address cannot be Empty");
                } else if (!EmailTxt.endsWith(".com")) {
                    error = true;
                    email.setError("Invalid Email Address");
                }else{
                    for (Users users1:users){
                        if(email.equals(users1.getUserEmail())){
                            error = true;
                            email.setError("Email already registered");
                            break;
                        }
                    }
                }

                if (UsernameTxt.length() < 3 || UsernameTxt.length() > 20) {
                    error = true;
                    username.setError("Invalid Username");
                } else if (UsernameTxt.isEmpty()) {
                    error = true;
                    username.setError("Username cannot be Empty");
                }

                if (PhonenumberTxt.isEmpty()) {
                    error = true;
                    phonenumber.setError("Phonenumber cannot be Empty");
                }

                else if (PasswordTxt.isEmpty()) {
                    error = true;
                    password.setError("Password cannot be Empty");
                } else if (!pattern.matcher(PasswordTxt).matches()) {
                    error = true;
                    password.setError("invalid Password");
                }

                if(error){

                    return;
                }

                try{
                    editorHelper.open();
                    editorHelper.insertUsers(new Users(-1, EmailTxt, UsernameTxt, PhonenumberTxt, PasswordTxt));
                    editorHelper.close();
                    Toast.makeText(Register.this, "REGISTER SUCCESS", Toast.LENGTH_SHORT).show();

                }catch (SQLException e){
                    e.printStackTrace();
                }
            Intent intent = new Intent(Register.this, Login.class);
            startActivity(intent);

        });

        login.setOnClickListener(view -> {
            Intent intent2 = new Intent(Register.this,Login.class);
            startActivity(intent2);
        });
    }

    private void init(){
        email=findViewById(R.id.editTextEmailR);
        username=findViewById(R.id.editTextUsernameR);
        phonenumber=findViewById(R.id.editTextPhoneR);
        password=findViewById(R.id.editTextPasswordR);
        login=findViewById(R.id.btnLogin2);
        register=findViewById(R.id.btnRegister2);
    }
}