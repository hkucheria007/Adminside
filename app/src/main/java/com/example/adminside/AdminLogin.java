package com.example.adminside;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminLogin extends AppCompatActivity {

    TextInputEditText textInputEditText;
    TextInputLayout textInputLayout;
    Button Acode;
    String adminmobile;
    FirebaseUser CurrentUser;
    String mb="8788681243";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        textInputLayout=findViewById(R.id.AdminTIL1);
        textInputEditText=findViewById(R.id.AdminmobileNo);
        Acode=findViewById(R.id.AdmingetOTP);


        CurrentUser= FirebaseAuth.getInstance().getCurrentUser();

        if(CurrentUser!=null){
            Intent i=new Intent(AdminLogin.this,MainActivity.class);
            startActivity(i);
            finish();
        }

        Acode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adminmobile=textInputEditText.getText().toString().trim();
                Toast.makeText(AdminLogin.this, adminmobile, Toast.LENGTH_SHORT).show();
                Toast.makeText(AdminLogin.this, mb, Toast.LENGTH_SHORT).show();

                if (adminmobile.equals(mb)){
                    Intent i=new Intent(AdminLogin.this,VerifyAdmin.class);
                    i.putExtra("AdminMobile",adminmobile);
                    startActivity(i);
                    finish();
                }

                else{
                    textInputEditText.setError("Enter valid mobile number");
                    textInputEditText.requestFocus();
                    return;
                }


            }
        });

    }
}