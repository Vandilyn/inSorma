package com.example.insorma_kelompok.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.insorma_kelompok.Account.Acc;
import com.example.insorma_kelompok.Database.DB;
import com.example.insorma_kelompok.R;

import java.util.Vector;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView signupredirect;
    EditText emailtxt, passwordtxt;
    Button loginbtn;
    public static String emailCocoklogi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signupredirect = findViewById(R.id.registerRedirect);
        signupredirect.setOnClickListener(this);

        emailtxt = findViewById(R.id.emailtxt);
        passwordtxt = findViewById(R.id.passwordtxt);
        loginbtn = findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validasi();
            }
        });

    }

    @Override
    public void onClick(View view) {
        Intent register = new Intent(this, Register.class);
        startActivity(register);
    }

    public void validasi() {
        String iemail, ipassword;
        int count = 0;

        iemail = emailtxt.getText().toString();
        ipassword = passwordtxt.getText().toString();

        if (ipassword.equals("") || !ipassword.matches("^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]+$")) {
            Toast.makeText(MainActivity.this, "Invalid password", Toast.LENGTH_SHORT).show();
        } else count++;

        if (iemail.equals("") || !iemail.endsWith(".com")) {
            Toast.makeText(MainActivity.this, "Invalid email", Toast.LENGTH_SHORT).show();
        } else count++;

        DB database = new DB(this);
        Boolean emailCheckResult = database.check(iemail,ipassword);
        emailCocoklogi = iemail;
        if (emailCheckResult) {
            Intent list = new Intent(this, Homepage.class);
            startActivity(list);
        }
        else {
            Toast.makeText(this, "Wrong info", Toast.LENGTH_SHORT)
                    .show();
        }
        Intent list = new Intent(this, Homepage.class);
        startActivity(list);

    }
}