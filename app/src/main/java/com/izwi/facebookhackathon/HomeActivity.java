package com.izwi.facebookhackathon;
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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.izwi.facebookhackathon.Common.Common;
import com.izwi.facebookhackathon.Fragments.HomeFragment;
import com.izwi.facebookhackathon.Fragments.OrdersFragment;
import com.izwi.facebookhackathon.Fragments.ProfileFragment;
import com.izwi.facebookhackathon.Fragments.ShoppingCartFragment;
import com.izwi.facebookhackathon.Interface.WitAPI;
import com.izwi.facebookhackathon.Models.GET.MetricData;
import com.izwi.facebookhackathon.Models.GET.getSuccess;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    private BottomNavigationView bottomNavigationView;
    private Fragment selectedFragment = Common.selectedFragment;
    private TextView home_header_title;
    private ImageView logout_btn;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        home_header_title=findViewById(R.id.home_header_title);


        logout_btn = findViewById(R.id.logout_btn);

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.currentUser=null;
                finish();
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_container,selectedFragment).commit();


    }
    public void speak_2_text(){
        home_header_title.setText("Speaking...");
        //intent to show speech to text dialog
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"izwi Listening Command..");

        //start intent
        try{
            //in there was no error
            startActivityForResult(intent,REQUEST_CODE_SPEECH_INPUT);
        }catch (Exception e){
            home_header_title.setText(e.getMessage());

            //Toast.makeText(HomeActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    //RECEIVE VOICE INPUT AND HANDLE IT

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        home_header_title.setText("izwi");
        switch (requestCode){
            case REQUEST_CODE_SPEECH_INPUT:
                if(resultCode == RESULT_OK && null!=data){
                    //GET TEXT ARRAY FROM VOICE INTENT
                    ArrayList<String> result= data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    //SET TO TEXT
                    //text_speak.setText(result.get(0));
                    //getIntention(result.get(0).replaceAll(" ", "%20"));

                    getIntention(result.get(0));
                }
                break;
        }
    }
    public void getIntention(String q){
        home_header_title.setText(q);
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Common.apiUrl)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        WitAPI witAPI = retrofit.create(WitAPI.class);
        String token ="Bearer "+Common.token;

        Call<getSuccess> call =witAPI.getIntention(token,Common.v,q);
        call.enqueue(new Callback<getSuccess>() {
            @Override
            public void onResponse(Call<getSuccess> call, Response<getSuccess> response) {
                if(response.isSuccessful()){

                    if(response.body().getIntents().length>0){
                        Log.d("Home Activity","get message Successful "+response.body().getIntents()[0].getName());
                        Log.d("Home Activity","get message Successful 1"+response);
                        if(response.body().getintentNameEqual("intent_search_product")) {
                            selectedFragment = new HomeFragment();
                            bottomNavigationView.setSelectedItemId(R.id.navigation_home);
                            home_header_title.setText("Search Product Successful");
                            timer=new Timer();
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            home_header_title.setText("izwi");
                                        }
                                    });

                                }
                            },3000);

                            updateSearchFields(response.body());
                            if(getSupportFragmentManager().findFragmentById(R.id.home_fragment_container) instanceof HomeFragment){
                                HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.home_fragment_container);
                                homeFragment.displayProducts();
                            }
                        }
                        else if(response.body().getintentNameEqual("intent_navigate_page")){
                            home_header_title.setText("Navigate Page Successful");

                            timer=new Timer();
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            home_header_title.setText("izwi");
                                        }
                                    });

                                }
                            },3000);

                            if(response.body().isMetricExists("wit_page:wit_page")){
                                String page = response.body().getMetricByName("wit_page:wit_page").getValue();
                                if(page.equals("home")){
                                    selectedFragment = new HomeFragment();
                                    bottomNavigationView.setSelectedItemId(R.id.navigation_home);
                                }else if(page.equals("profile")){
                                    selectedFragment = new ProfileFragment();
                                    bottomNavigationView.setSelectedItemId(R.id.navigation_profile);

                                }/*else if(page.equals("shopping cart")){
                                    selectedFragment =  new ShoppingCartFragment();
                                    bottomNavigationView.setSelectedItemId(R.id.navigation_shopping_cart);

                                }else if(page.equals("orders")){
                                    selectedFragment =  new OrdersFragment();
                                    bottomNavigationView.setSelectedItemId(R.id.navigation_orders);

                                }*/
                                getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_container,selectedFragment).commit();


                            }
                        }
                        else{
                            home_header_title.setText("We can't identify command...");
                            timer=new Timer();
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            home_header_title.setText("izwi");
                                        }
                                    });

                                }
                            },3000);
                        }
                    }
                }else{
                    Log.d("Home Activity","get message Unsuccessful " );
                }
            }

            @Override
            public void onFailure(Call<getSuccess> call, Throwable t) {
                Toast.makeText(HomeActivity.this,""+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void updateSearchFields(getSuccess body){
        Log.d("Home Activity 0", "" + body.isMetricExists("wit_product_name:wit_product_name"));
        if(body.isMetricExists("wit_product_name:wit_product_name")){
            Common.search_product_name = body.getMetricByName("wit_product_name:wit_product_name").getValue();
        }else{
            Common.search_product_name = "";
        }
        Log.d("Home Activity 1", "" + body.isMetricExists("wit_product_category:wit_product_category"));
        if(body.isMetricExists("wit_product_category:wit_product_category")){
            Common.search_product_category = body.getMetricByName("wit_product_category:wit_product_category").getValue();
            Log.d("prod_category",Common.search_product_category);
        }else{
            Common.search_product_category = "";
        }
        Log.d("Home Activity 2", "" + body.isMetricExists("wit_product_color:wit_product_color"));
        if(body.isMetricExists("wit_product_color:wit_product_color")){
            Common.search_product_color = body.getMetricByName("wit_product_color:wit_product_color").getValue();
        }else{
            Common.search_product_color = "";
        }
        Log.d("Home Activity 3", "" +body.isMetricExists("wit_gender:wit_gender"));
        if(body.isMetricExists("wit_gender:wit_gender")){
            Common.search_product_gender = body.getMetricByName("wit_gender:wit_gender").getValue();
        }else{
            Common.search_product_gender = "";
        }
        Log.d("Home Activity 4", "" + body.isMetricExists("wit_function:wit_function"));
        if(body.isMetricExists("wit_function:wit_function")){
            Common.search_product_function_description = body.getMetricByName("wit_function:wit_function").getValue();
        }else{
            Common.search_product_function_description = "";
        }
        Log.d("Home Activity 5", "" + body.isMetricExists("wit$datetime:datetime"));
        if(body.isMetricExists("wit$datetime:datetime")){
            //
        }else{
            //
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.navigation_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.navigation_profile:
                    selectedFragment = new ProfileFragment();
                    break;
                    /*
                case R.id.navigation_shopping_cart:
                    selectedFragment = new ShoppingCartFragment();
                    break;
                case R.id.navigation_orders:
                    selectedFragment = new OrdersFragment();
                    break;

                     */

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_container,selectedFragment).commit();
            return true;
        }
    };
}