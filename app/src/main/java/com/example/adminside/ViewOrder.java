package com.example.adminside;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    String userId;
    Button ADelivered;


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

        ADelivered=findViewById(R.id.Delivered);
        cardView=findViewById(R.id.AViewOrderCardView);

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


        ADelivered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userId=getIntent().getStringExtra("UserID");
                DocumentReference ref=firestore.collection("CustomerDetails").document(userId);
                ref.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        DocumentReference reference=firestore.collection("Products").document(userId);
                        reference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                cardView.setVisibility(View.GONE);
                                Intent i=new Intent(ViewOrder.this,MainActivity.class);
                                startActivity(i);
                                Toast.makeText(ViewOrder.this, "Delivered", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ViewOrder.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ViewOrder.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
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