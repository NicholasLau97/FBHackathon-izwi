package com.izwi.facebookhackathon.Fragments;
/*
 * Lau Hui Sheng CONFIDENTIAL
 * __________________
 *  Date : 8/5/2021 20:00 MYT
 *  [2021] - [2021] Lau Hui Sheng email: huisheng97.lhs.business@gmail.com
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Lau Hui Sheng and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Lau Hui Sheng
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Lau Hui Sheng.
 */
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.izwi.facebookhackathon.Common.Common;
import com.izwi.facebookhackathon.HomeActivity;
import com.izwi.facebookhackathon.Interface.ItemClickListener;
import com.izwi.facebookhackathon.Models.DataModels.Product;
import com.izwi.facebookhackathon.Models.DataModels.ProductDetails;
import com.izwi.facebookhackathon.R;
import com.izwi.facebookhackathon.ViewHolder.ProductAdapter;
import com.izwi.facebookhackathon.ViewHolder.ProductViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment {


    private FloatingActionButton fab_voice_home;

    FirebaseDatabase database;
    DatabaseReference productList;
    RecyclerView recycler_product;
    RecyclerView.LayoutManager layoutManager;

    RecyclerView.Adapter<ProductViewHolder> adapter;

    boolean productsLoad =false;

    TextView text_search;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        fab_voice_home = view.findViewById(R.id.fab_voice_home);

        layoutManager =new LinearLayoutManager(view.getContext());
        recycler_product = view.findViewById(R.id.recycler_product);
        recycler_product.setHasFixedSize(true);
        recycler_product.setLayoutManager(layoutManager);

        text_search=view.findViewById(R.id.text_search);



        database = FirebaseDatabase.getInstance();
        productList = database.getReference("Products");

        productList.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Common.productDetailsList = new ArrayList<>();
                for (DataSnapshot d: dataSnapshot.getChildren())
                {
                    ProductDetails prod=new ProductDetails(d.getKey(),(d.getValue(Product.class)));
                    Common.productDetailsList.add(prod);
                    Log.d("Home Fragment",prod.getProduct().getName());



                }
                productsLoad=true;
                Log.d("Home Fragment","productsLoad > "+productsLoad);
                displayProducts();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //displayProducts();









        fab_voice_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity)getActivity()).speak_2_text();
                //((HomeActivity)getActivity()).getIntention("search%20shoe%20today");
                //((HomeActivity)getActivity()).getIntention("search%20toy");
                //((HomeActivity)getActivity()).getIntention("search shoes");
                //((HomeActivity)getActivity()).getIntention("navigate to profile");
            }
        });

        return view;
    }
    public void displayProducts(){

        text_search.setText("Tag: "+Common.getSearchText());
        adapter = new ProductAdapter(Common.getProductDetailsDisplay());
        recycler_product.setAdapter(adapter);

    }
}