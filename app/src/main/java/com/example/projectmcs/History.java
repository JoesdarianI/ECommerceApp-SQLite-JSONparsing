package com.example.projectmcs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.projectmcs.DatabaseSQLlite.DBEditorHelper;
import com.example.projectmcs.Model.Transaction;


import java.util.ArrayList;

public class History extends AppCompatActivity {

    ListView listViewH;
    private DBEditorHelper dbEditorHelper = new DBEditorHelper(this);
    ArrayList<Transaction> transactions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listViewH = findViewById(R.id.historyListview);

        try {
            dbEditorHelper.open();
            transactions = dbEditorHelper.listTransaction();
            dbEditorHelper.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        HistoryAdapter historyAdapter = new HistoryAdapter(this,R.layout.histroy_detail,transactions);
        listViewH.setAdapter(historyAdapter);
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