package com.izwi.facebookhackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
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
public class SignUpActivity extends AppCompatActivity {

    EditText edt_signup_name,edt_signup_username,edt_signup_password;
    TextView error_signup_name,error_signup_username,error_signup_password;
    ImageView signup_toggle_password;
    LinearLayout btn_signup;
    TextView login_text;
    private boolean is_show_password = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edt_signup_name = findViewById(R.id.edt_signup_name);
        edt_signup_username = findViewById(R.id.edt_signup_username);
        edt_signup_password = findViewById(R.id.edt_signup_password);
        error_signup_name = findViewById(R.id.error_signup_name);
        error_signup_username = findViewById(R.id.error_signup_username);
        error_signup_password = findViewById(R.id.error_signup_password);
        signup_toggle_password = findViewById(R.id.signup_toggle_password);
        btn_signup = findViewById(R.id.btn_signup);
        login_text = findViewById(R.id.login_text);

        error_signup_name.setVisibility(View.GONE);
        error_signup_username.setVisibility(View.GONE);
        error_signup_password.setVisibility(View.GONE);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");


        signup_toggle_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                is_show_password=!is_show_password;
                updatePasswordShowHide();
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validation()) {
                    if (Common.isConnectedToInternet(getBaseContext())) {
                        final ProgressDialog mDialog = new ProgressDialog(SignUpActivity.this);
                        mDialog.setMessage("Loading...");
                        mDialog.show();

                        table_user.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                //check if user not exist in database
                                if (dataSnapshot.child(edt_signup_username.getText().toString()).exists()) {
                                    //get user information
                                    mDialog.dismiss();
                                    User user = dataSnapshot.child(edt_signup_username.getText().toString()).getValue(User.class);

                                    error_signup_username.setText("Username registered. Try another!");
                                    error_signup_name.setVisibility(View.GONE);
                                    error_signup_username.setVisibility(View.VISIBLE);
                                    error_signup_password.setVisibility(View.GONE);

                                } else {
                                    mDialog.dismiss();
                                    String passwordToHash = edt_signup_password.getText().toString();
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
                                        User user = new User(edt_signup_name.getText().toString(), generatedPassword, true, true);
                                        table_user.child(edt_signup_username.getText().toString()).setValue(user);
                                        Toast.makeText(SignUpActivity.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                    catch (NoSuchAlgorithmException e)
                                    {
                                        e.printStackTrace();
                                        Toast.makeText(SignUpActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                        finish();
                                    }

                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    } else {
                        Toast.makeText(SignUpActivity.this, "Internet disconnected!", Toast.LENGTH_SHORT).show();
                        return;

                    }
                }
            }
        });

        login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        updatePasswordShowHide();

    }
    private void updatePasswordShowHide(){
        if(is_show_password){
            signup_toggle_password.setImageDrawable(getResources().getDrawable(R.drawable.ic_password_show));
            edt_signup_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        }else{
            signup_toggle_password.setImageDrawable(getResources().getDrawable(R.drawable.ic_password_hide));
            edt_signup_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        }
    }
    private boolean validation(){
        boolean result = true;

        error_signup_name.setVisibility(View.GONE);
        error_signup_username.setVisibility(View.GONE);
        error_signup_password.setVisibility(View.GONE);

        String name = edt_signup_name.getText().toString();
        if(TextUtils.isEmpty(name.trim())){
            error_signup_name.setText("Name can't be empty!");
            error_signup_name.setVisibility(View.VISIBLE);
            result = false;
        }

        String username = edt_signup_username.getText().toString();
        if(TextUtils.isEmpty(username.trim())){
            error_signup_username.setText("Username can't be empty!");
            error_signup_username.setVisibility(View.VISIBLE);
            result = false;

        }

        String password = edt_signup_password.getText().toString();
        if(TextUtils.isEmpty(password.trim())){
            error_signup_password.setText("Password can't be empty!");
            error_signup_password.setVisibility(View.VISIBLE);
            result = false;

        }
        return result;
    }
}