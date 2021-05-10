package com.izwi.facebookhackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.izwi.facebookhackathon.Common.Common;
import com.izwi.facebookhackathon.Models.DataModels.Product;
import com.squareup.picasso.Picasso;
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
public class ProductDetailsActivity extends AppCompatActivity {

    ImageView img_product_pd;
    TextView txt_product_name_pd,txt_product_price_pd,txt_product_quantity_pd;
    ImageView img_quantity_minus,img_quantity_add;
    TextView txt_product_description_pd,txt_product_search_tag_pd;
    Button btn_add_to_cart_pd;

    int quantity =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        if(Common.product_details==null){
            finish();
        }

        img_product_pd=findViewById(R.id.img_product_pd);
        txt_product_name_pd=findViewById(R.id.txt_product_name_pd);
        txt_product_price_pd=findViewById(R.id.txt_product_price_pd);
        txt_product_quantity_pd =findViewById(R.id.txt_product_quantity_pd);
        txt_product_quantity_pd.setText(String.valueOf(quantity));
        img_quantity_minus=findViewById(R.id.img_quantity_minus);
        img_quantity_add=findViewById(R.id.img_quantity_add);
        txt_product_description_pd=findViewById(R.id.txt_product_description_pd);
        txt_product_search_tag_pd = findViewById(R.id.txt_product_search_tag_pd);
        btn_add_to_cart_pd=findViewById(R.id.btn_add_to_cart_pd);

        img_quantity_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity--;
                if(quantity<1){
                    quantity=1;
                }
                txt_product_quantity_pd.setText(String.valueOf(quantity));
            }
        });

        img_quantity_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                if(quantity>20){
                    quantity=20;
                }
                txt_product_quantity_pd.setText(String.valueOf(quantity));

            }
        });

        btn_add_to_cart_pd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setDisplay(Common.product_details);
    }
    private void setDisplay(Product product){
        Picasso.with(this.getBaseContext()).load(product.getImage()).into(img_product_pd);
        txt_product_name_pd.setText(product.getName());
        txt_product_price_pd.setText(product.getPrice());
        txt_product_description_pd.setText(product.getDescription());
        txt_product_search_tag_pd.setText("Tag: "+product.getSearchTag());
    }
}