package com.example.adminside;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ViewOrder extends AppCompatActivity {

    CardView cardView;
    TextView Apiz1,Apiz2,Ado1,Ado2,Asan1,Asan2,Aqpiz1,Aqpiz2,Aqdo1,Aqdo2,Aqsan1,Aqsan2;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    String userId,quantity="0";
    ImageView vp1,vp2,vd1,vd2,vs1,vs2;
    TextView total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        auth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();

        Apiz1=findViewById(R.id.Ap1);
        Apiz2=findViewById(R.id.Ap2);
        Ado1=findViewById(R.id.Ad1);
        Ado2=findViewById(R.id.Ad2);
        Asan1=findViewById(R.id.As1);
        Asan2=findViewById(R.id.As2);

        Aqpiz1=findViewById(R.id.Aqp1);
        Aqpiz2=findViewById(R.id.Aqp2);
        Aqdo1=findViewById(R.id.Aqd1);
        Aqdo2=findViewById(R.id.Aqd2);
        Aqsan1=findViewById(R.id.Aqs1);
        Aqsan2=findViewById(R.id.Aqs2);

        vp1=findViewById(R.id.vegP1);
        vp2=findViewById(R.id.vegP2);
        vd1=findViewById(R.id.vegD1);
        vd2=findViewById(R.id.vegD2);
        vs1=findViewById(R.id.vegS1);
        vs2=findViewById(R.id.vegS2);

        total=findViewById(R.id.TotalHeading);
        cardView=findViewById(R.id.AViewOrderCardView);

        userId=getIntent().getStringExtra("UserID");
        DocumentReference dr=firestore.collection("Products").document(userId);
        dr.addSnapshotListener(ViewOrder.this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                String p1=value.getString("Pizza1");
                String p2=value.getString("Pizza2");
                String d1=value.getString("Donut1");
                String d2=value.getString("Donut2");
                String s1=value.getString("Sandwitch1");
                String s2=value.getString("Sandwitch2");
                String price=value.getString("Total");

                if (!p1.equals(quantity)){
                    vp1.setVisibility(View.VISIBLE);
                    Apiz1.setVisibility(View.VISIBLE);
                    Aqpiz1.setText(p1);
                    Aqpiz1.setVisibility(View.VISIBLE);
                }

                if (!p2.equals(quantity)){
                    vp2.setVisibility(View.VISIBLE);
                    Apiz2.setVisibility(View.VISIBLE);
                    Aqpiz2.setText(p2);
                    Aqpiz2.setVisibility(View.VISIBLE);
                }

                 if (!d1.equals(quantity)){
                    vd1.setVisibility(View.VISIBLE);
                    Ado1.setVisibility(View.VISIBLE);
                    Aqdo1.setText(d1);
                    Aqdo1.setVisibility(View.VISIBLE);
                }

                 if (!d2.equals(quantity)){
                    vd2.setVisibility(View.VISIBLE);
                    Ado2.setVisibility(View.VISIBLE);
                    Aqdo2.setText(d2);
                    Aqdo2.setVisibility(View.VISIBLE);
                }

                 if (!s1.equals(quantity)){
                     vs1.setVisibility(View.VISIBLE);
                     Asan1.setVisibility(View.VISIBLE);
                     Aqsan1.setText(s1);
                     Aqsan1.setVisibility(View.VISIBLE);
                 }

                 if (!s2.equals(quantity)){
                     vs2.setVisibility(View.VISIBLE);
                     Asan2.setVisibility(View.VISIBLE);
                     Aqsan2.setText(s2);
                     Aqsan2.setVisibility(View.VISIBLE);
                 }

                 total.setText("Grand Total:   "+price);

            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ViewOrder.this,MainActivity.class));
        finish();
    }
}