package com.example.insorma_kelompok.Account;

import static com.example.insorma_kelompok.Activity.MainActivity.emailCocoklogi;
import static com.example.insorma_kelompok.Activity.Register.accounts;
import static com.example.insorma_kelompok.Database.DB.COL_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.insorma_kelompok.Activity.Catalog;
import com.example.insorma_kelompok.Activity.Homepage;
import com.example.insorma_kelompok.Activity.MainActivity;
import com.example.insorma_kelompok.Database.DB;
import com.example.insorma_kelompok.R;

import java.util.Vector;

public class Profile extends AppCompatActivity {
    Button edit,save,logout,delete;
    EditText username,email,phonenumber;
    TextView homepage,catalog;
    String iusername,iemail ;
    DB database = new DB(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        database.getData();

        edit = findViewById(R.id.editProfileButton);
        save = findViewById(R.id.saveProfileButton);
        logout = findViewById(R.id.logOutButton);
        delete = findViewById(R.id.deleteAccountButton);

        username = findViewById(R.id.usernameText);
        username.setEnabled(false);

        email = findViewById(R.id.emailText);
        email.setEnabled(false);

        phonenumber = findViewById(R.id.phoneNumberText);
        phonenumber.setEnabled(false);

        iemail = email.getText().toString();

        homepage = findViewById(R.id.HomepageBtn);
        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, Homepage.class);
                startActivity(intent);
            }
        });

        catalog = findViewById(R.id.CatalogBtn);
        catalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, Catalog.class);
                startActivity(intent);
            }
        });

        int index = 0;
        for(Acc i : accounts) {
            if (i.getEmail().equals(emailCocoklogi)) {
                username.setText(i.getUsername());
                email.setText(i.getEmail());
                phonenumber.setText(i.getPhonenumber());
                iusername = accounts.get(index).getUsername();
            }
            index++;
        }


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("iusernamex",iusername);
                edit.setVisibility(View.INVISIBLE);
                save.setVisibility(View.VISIBLE);
                username.setEnabled(true);
                //asd12
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernamebaru = username.getText().toString();
                save.setVisibility(View.INVISIBLE);
                edit.setVisibility(View.VISIBLE);
                username.setEnabled(false);
                Log.d("iusername",iusername);
                //asd12
                for(Acc i : accounts) {
                    if (i.getEmail().equals(emailCocoklogi)) {
                        Log.d("sebelum",iusername);
                        i.setUsername(usernamebaru); //asd12 -> ucok
                        database.update(usernamebaru,i.getEmail()); // asd -> ucok
                        Log.d("sesudah",usernamebaru);
                        iusername = usernamebaru;
                        Log.d("sesudah iusername",usernamebaru);
                    }
                }
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exit();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAcc();
            }
        });
    }
    public void exit(){
        Intent intent = new Intent(Profile.this, MainActivity.class);
        emailCocoklogi = "";
        startActivity(intent);
    }

    public void deleteAcc(){
        int c = 0;

        for(Acc i : accounts) {
            if (i.getEmail().equals(emailCocoklogi)) {
                accounts.remove(c);
                exit();
                Toast.makeText(Profile.this, "Account deleted", Toast.LENGTH_SHORT).show();
            }
            else c++;
        }
    }
}