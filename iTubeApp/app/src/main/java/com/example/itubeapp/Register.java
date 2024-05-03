package com.example.itubeapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    EditText fullNameEditText, usernameEditText, passwordEditText, confirmPassEditText;
    Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        fullNameEditText = findViewById(R.id.fullNameEditText);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPassEditText = findViewById(R.id.confirmPassEditText);
        createAccountButton = findViewById(R.id.createAccountButton);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String name = fullNameEditText.getText().toString();
                String confirmPass = confirmPassEditText.getText().toString();

                if(!password.equals(confirmPass))
                {
                    Toast.makeText(Register.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (username.isEmpty() || password.isEmpty() || name.isEmpty())
                {
                    Toast.makeText(Register.this, "Please fill all the required fields!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    insertUser(username, password, name);
                }
            }
        });
    }

    public void insertUser(String fullName, String username, String password) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.COLUMN_FULL_NAME, fullName);
        values.put(DatabaseHelper.COLUMN_USERNAME, username);
        values.put(DatabaseHelper.COLUMN_PASSWORD, password);
        db.insert(DatabaseHelper.TABLE_USERS, null, values);
        db.close();
        Toast.makeText(Register.this, "SignUp Successful!", Toast.LENGTH_SHORT).show();
        finish();
    }
}