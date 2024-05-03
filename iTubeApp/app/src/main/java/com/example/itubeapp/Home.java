package com.example.itubeapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    EditText urlEditText;
    Button playButton, atpButton, myPlistButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        urlEditText = findViewById(R.id.urlEditText);
        playButton = findViewById(R.id.playButton);
        myPlistButton = findViewById(R.id.myPlistButton);
        atpButton = findViewById(R.id.atpButton);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = urlEditText.getText().toString();
                if(url.isEmpty())
                {
                    Toast.makeText(Home.this, "Please enter the URL!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent i = new Intent(Home.this, Player.class);
                    i.putExtra("url", url);
                    startActivity(i);
                }
            }
        });

        atpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = urlEditText.getText().toString();
                if(url.isEmpty())
                {
                    Toast.makeText(Home.this, "Please enter the URL!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    addURL(url);
                }
            }
        });

        myPlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, MyPlaylist.class);
                startActivity(i);
            }
        });
    }

    public void addURL(String url) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_URL, url);
        db.insert(DatabaseHelper.TABLE_URLS, null, values);
        db.close();
        Toast.makeText(Home.this, "Added to playlist!", Toast.LENGTH_SHORT).show();
    }
}