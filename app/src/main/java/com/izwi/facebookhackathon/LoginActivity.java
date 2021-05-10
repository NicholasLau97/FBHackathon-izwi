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
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.izwi.facebookhackathon.Common.Common;
import com.izwi.facebookhackathon.Models.DataModels.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
public class LoginActivity extends AppCompatActivity {


    private LinearLayout btn_login;
    private EditText edt_login_username,edt_login_password;
    private ImageView login_toggle_password;
    private boolean is_show_password = false;
    private TextView error_login_email,error_login_password;
    private TextView signup_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login=findViewById(R.id.btn_login);
        edt_login_username=findViewById(R.id.edt_login_username);
        edt_login_password=findViewById(R.id.edt_login_password);
        error_login_email = findViewById(R.id.error_login_email);
        error_login_password=findViewById(R.id.error_login_password);
        login_toggle_password = findViewById(R.id.login_toggle_password);
        signup_text = findViewById(R.id.signup_text);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        //Method
        login_toggle_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                is_show_password=!is_show_password;
                updatePasswordShowHide();
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent home = new Intent(getApplicationContext(),HomeActivity.class);
                //startActivity(home);
                if(validation()) {
                    if (Common.isConnectedToInternet(getBaseContext())) {


                        final ProgressDialog mDialog = new ProgressDialog(LoginActivity.this);
                        mDialog.setMessage("Please Wating...");
                        mDialog.show();

                        table_user.addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                //check if user not exist in database
                                if (dataSnapshot.child(edt_login_username.getText().toString()).exists()) {
                                    //get user information
                                    mDialog.dismiss();
                                    User user = dataSnapshot.child(edt_login_username.getText().toString()).getValue(User.class);
                                    user.setUsername(edt_login_username.getText().toString());
                                    String passwordToHash = edt_login_password.getText().toString();
                                    String generatedPassword = null;
                                    try {
                                        // Create MessageDigest instance for MD5
                                        MessageDigest md = MessageDigest.getInstance("MD5");
                                        //Add password bytes to digest
                                        md.update(passwordToHash.getBytes());
                                        //Get the hash's bytes
                                        byte[] bytes = md.digest();
                                        //This bytes[] has bytes in decimal format;
                                        //Convert it to hexadecimal format
                                        StringBuilder sb = new StringBuilder();
                                        for(int i=0; i< bytes.length ;i++)
                                        {
                                            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                                        }
                                        //Get complete hashed password in hex format
                                        generatedPassword = sb.toString();
                                        if (user.getPassword().equals(generatedPassword)) {

                                            Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
                                            Common.currentUser = user;
                                            startActivity(homeIntent);
                                            finish();

                                        } else {
                                            error_login_email.setVisibility(View.GONE);
                                            error_login_password.setVisibility(View.VISIBLE);
                                            error_login_password.setText("Password incorrect. Try again!");
                                        }
                                    }
                                    catch (NoSuchAlgorithmException e)
                                    {
                                        e.printStackTrace();
                                    }

                                } else {
                                    mDialog.dismiss();
                                    error_login_email.setVisibility(View.VISIBLE);
                                    error_login_password.setVisibility(View.GONE);
                                    error_login_password.setText("Username doesn't sign up yet. Signup now!");
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    } else {

                        Toast.makeText(LoginActivity.this, "Internet disconnected!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
        });
        signup_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup=new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(signup);
            }
        });
        //Display
        updatePasswordShowHide();


    }
    private void updatePasswordShowHide(){
        if(is_show_password){
            login_toggle_password.setImageDrawable(getResources().getDrawable(R.drawable.ic_password_show));
            edt_login_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        }else{
            login_toggle_password.setImageDrawable(getResources().getDrawable(R.drawable.ic_password_hide));
            edt_login_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        }
    }
    private boolean validation(){
        boolean result = true;

        error_login_email.setVisibility(View.GONE);
        error_login_password.setVisibility(View.GONE);

        String username = edt_login_username.getText().toString();
        if(TextUtils.isEmpty(username.trim())){
            error_login_email.setText("Username can't be empty!");
            error_login_email.setVisibility(View.VISIBLE);
            result = false;

        }

        String password = edt_login_password.getText().toString();
        if(TextUtils.isEmpty(password.trim())){
            error_login_password.setText("Password can't be empty!");
            error_login_password.setVisibility(View.VISIBLE);
            result = false;

        }
        return result;
    }
}