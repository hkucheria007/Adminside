package com.example.adminside;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ViewOrder extends AppCompatActivity {
    TextView Apiz1,Apiz2,Ado1,Ado2,Asan1,Asan2,Aqpiz1,Aqpiz2,Aqdo1,Aqdo2,Aqsan1,Aqsan2;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    String userId;
    Button ASeeOrder;


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


        userId=getIntent().getStringExtra("UserID");
        DocumentReference dr=firestore.collection("Products").document(userId);
        dr.addSnapshotListener(ViewOrder.this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                Aqpiz1.setText("x"+value.getString("Pizza1"));
                Aqpiz2.setText("x"+value.getString("Pizza2"));
                Aqdo1.setText("x"+value.getString("Donut1"));
                Aqdo2.setText("x"+value.getString("Donut2"));
                Aqsan1.setText("x"+value.getString("Sandwitch1"));
                Aqsan2.setText("x"+value.getString("Sandwitch2"));
            }
        });

    }
}