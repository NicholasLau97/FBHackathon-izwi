package com.izwi.facebookhackathon.ViewHolder;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.izwi.facebookhackathon.Common.Common;
import com.izwi.facebookhackathon.Models.DataModels.ProductDetails;
import com.izwi.facebookhackathon.ProductDetailsActivity;
import com.izwi.facebookhackathon.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {
    private String TAG = "Adapter";
    List<ProductDetails> productDetailsList;

    public ProductAdapter(List<ProductDetails> productDetailsList) {
        this.productDetailsList = productDetailsList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductDetails productDetails = productDetailsList.get(position);


        holder.product_name.setText(productDetails.getProduct().getName());
        Picasso.with(holder.itemView.getContext()).load(productDetails.getProduct().getImage()).into(holder.product_img);

        holder.fav_img.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.ic_favorite));
        holder.fav_img.setVisibility(View.GONE);
        //holder.fav_img.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.ic_favorite_border));

        holder.bg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"On clicked :"+productDetails.getProduct().getName());
                Common.product_details = productDetails.getProduct();

                Intent pd = new Intent(holder.itemView.getContext(), ProductDetailsActivity.class);
                holder.itemView.getContext().startActivity(pd);

            }
        });
    }

    @Override
    public int getItemCount() {
        return productDetailsList.size();
    }
}
