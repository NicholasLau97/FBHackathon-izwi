package com.izwi.facebookhackathon.Common;
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
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.izwi.facebookhackathon.Fragments.HomeFragment;
import com.izwi.facebookhackathon.Fragments.OrdersFragment;
import com.izwi.facebookhackathon.Fragments.ProfileFragment;
import com.izwi.facebookhackathon.Fragments.ShoppingCartFragment;
import com.izwi.facebookhackathon.Models.DataModels.Product;
import com.izwi.facebookhackathon.Models.DataModels.ProductDetails;
import com.izwi.facebookhackathon.Models.DataModels.User;

import java.util.ArrayList;
import java.util.List;

public class Common {
    public final static String apiUrl ="https://api.wit.ai/";
    public static Fragment selectedFragment = new HomeFragment();
    public static final String token = "NYBIHAFTS263PQONO65A6JPQULGN73T4";
    public static final String v= "20210508";

    public static User currentUser;

    public static String search_product_name = "";
    public static String search_product_function_description="";
    public static String search_product_color="";
    public static String search_product_category ="";
    public static String search_product_gender="";
    public static String search_product_target_user="";

    public static List<ProductDetails> productDetailsList = new ArrayList<>();

    public static Product product_details=null;

    public static String getSearchText(){
        List<String> result = new ArrayList<>();
        boolean is_add = true;

        if(!search_product_name.equals("")){
            result.add(search_product_name);

            is_add = false;
        }
        if(!search_product_function_description.equals("")){
            result.add(search_product_function_description);

            is_add = false;

        }
        if(!search_product_color.equals("")){
            result.add(search_product_color);

            is_add = false;

        }
        if(!search_product_category.equals("")){
            result.add(search_product_category);

            is_add = false;
        }
        if(!search_product_gender.equals("")){
            result.add(search_product_gender);

            is_add = false;

        }
        if(!search_product_target_user.equals("")){
            result.add(search_product_target_user);
            is_add = false;

        }

        if(is_add) {
            result.add("All");
        }

        String return_result = "";
        for(int i=0;i<result.size();i++){
            return_result += result.get(i);
            if(!(result.size()-1==i)){
                return_result+=", ";
            }
        }
        return return_result;
    }

    public static boolean isConnectedToInternet(Context context){
        ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
        if(connectivityManager!=null){
            NetworkInfo[] info=connectivityManager.getAllNetworkInfo();
            if(info!=null){
                for(int i=0;i<info.length;i++){
                    if(info[i].getState()==NetworkInfo.State.CONNECTED){
                        return true;

                    }

                }

            }

        }return false;

    }

    public static List<ProductDetails> getProductDetailsDisplay (){
        List<ProductDetails> result = new ArrayList<>();
        for(ProductDetails productDetails_check:productDetailsList){
            Log.d("Common",""+productDetails_check.getProduct().toString());
            boolean is_add = true;
            if(!search_product_name.equals("")){
                if(!search_product_name.equals(productDetails_check.getProduct().getName())){
                    is_add = false;
                }
            }
            if(!search_product_function_description.equals("")){
                if(!search_product_function_description.equals(productDetails_check.getProduct().getDescription())){
                    is_add = false;
                }
            }
            if(!search_product_color.equals("")){
                if(!search_product_color.equals(productDetails_check.getProduct().getColor())){
                    is_add = false;
                }
            }
            if(!search_product_category.equals("")){
                if(!search_product_category.equals(productDetails_check.getProduct().getCategory())){
                    is_add = false;
                }
            }
            if(!search_product_gender.equals("")){
                if(!search_product_gender.equals(productDetails_check.getProduct().getGender())){
                    is_add = false;
                }
            }
            if(!search_product_target_user.equals("")){
                if(!search_product_target_user.equals(productDetails_check.getProduct().getTarget_user())){
                    is_add = false;
                }
            }
            if(is_add) {
                result.add(productDetails_check);
            }


        }
        return result;
    }

}
