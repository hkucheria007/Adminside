package com.example.adminside;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.firestoreList);
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        //Query
        Query query = firestore.collection("CustomerDetails");

        //RecyclerOptions
        FirestoreRecyclerOptions<ProductsModel> options = new FirestoreRecyclerOptions.Builder<ProductsModel>()
                .setQuery(query, ProductsModel.class)
                .build();


        adapter = new FirestoreRecyclerAdapter<ProductsModel, ProductsViewHolder>(options) {
            @NonNull
            @Override
            public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_products, parent, false);
                return new ProductsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ProductsViewHolder holder, int position, @NonNull ProductsModel model) {
                holder.CName.setText("Name:" + model.getFirstName() + " " + model.getLastName());
                holder.CAdd.setText("Address:" + model.getAddress1() + "," + model.getAddress2() + "," + model.getAddress3()+","+model.getPincode());
                holder.CCity.setText("City:" + model.getCity());
                holder.CMobile.setText("Mobile:" + model.getMobile());

                holder.Order.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String Id=model.getID();
                        Intent i=new Intent(MainActivity.this,ViewOrder.class);
                        i.putExtra("UserID",Id);
                        startActivity(i);
                    }
                });
            }
        };
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private class ProductsViewHolder extends RecyclerView.ViewHolder {

        TextView CName, CAdd, CCity, CMobile;
        CardView cardView;
        Button Order;

        public ProductsViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardview);
            CName = itemView.findViewById(R.id.NameTV);
            CAdd = itemView.findViewById(R.id.AddressTV);
            CCity = itemView.findViewById(R.id.CityTV);
            CMobile = itemView.findViewById(R.id.MobileTV);
            Order=itemView.findViewById(R.id.OrderBtn);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
        finish();
    }
}
