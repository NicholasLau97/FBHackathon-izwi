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
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.izwi.facebookhackathon.Interface.ItemClickListener;
import com.izwi.facebookhackathon.R;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    public TextView product_name;
    public ImageView fav_img,product_img;
    public RelativeLayout bg_btn;

    public ProductViewHolder(@NonNull View itemView){
        super(itemView);
        product_name = itemView.findViewById(R.id.product_name);
        fav_img = itemView.findViewById(R.id.fav_img);
        product_img = itemView.findViewById(R.id.product_img);
        bg_btn = itemView.findViewById(R.id.bg_btn);

    }
}
