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

public class Register extends AppCompatActivity implements View.OnClickListener {

    TextView signinredirect;
    EditText usernametxt, passwordtxt, emailtxt, phonenumbertxt;
    Button registerbtn;
    public static Vector<Acc> accounts = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signinredirect = findViewById(R.id.loginRedirect);
        signinredirect.setOnClickListener(this);

        registerbtn = findViewById(R.id.registerbtn);
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validasi();
            }
        });
        usernametxt = findViewById(R.id.usernametxt);
        passwordtxt = findViewById(R.id.passwordtxt);
        emailtxt = findViewById(R.id.emailtxt);
        phonenumbertxt = findViewById(R.id.phoneNumbertxt);
    }


    @Override
    public void onClick(View view) {
        Intent Login = new Intent(this, MainActivity.class);
        startActivity(Login);
    }

    public void validasi(){
        String iusername,ipassword,iemail,iphonenumber;
        int count = 0;

        iusername = usernametxt.getText().toString();
        ipassword = passwordtxt.getText().toString();
        iemail = emailtxt.getText().toString();
        iphonenumber = phonenumbertxt.getText().toString();

        if(iphonenumber.equals("") || iphonenumber.length() < 11){
            Toast.makeText(Register.this, "Invalid phone number",Toast.LENGTH_SHORT).show();
        }
        else count++;

        if(ipassword.equals("") || !ipassword.matches("^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]+$")) {
            Toast.makeText(Register.this, "Invalid password", Toast.LENGTH_SHORT).show();
        }
        else count++;

        if(iemail.equals("") || !iemail.endsWith(".com")){
            Toast.makeText(Register.this, "Invalid email",Toast.LENGTH_SHORT).show();
            count++;
        }
        else count++;

        if(iusername.length() < 3 || iusername.length()>20 || iusername.equals("")){
            Toast.makeText(Register.this, "Invalid username",Toast.LENGTH_SHORT).show();
            count++;
        }
        else count++;

        if(count==4)
        {
//            accounts.add(new Acc(iusername,iemail,ipassword,iphonenumber));
            Toast.makeText(Register.this, "Welcome, please try to log in", Toast.LENGTH_SHORT).show();
            DB database = new DB(this);
            database.insert(iemail,iusername,iphonenumber,ipassword);
            Intent Login = new Intent(this, MainActivity.class);
            startActivity(Login);
        }
    }

}