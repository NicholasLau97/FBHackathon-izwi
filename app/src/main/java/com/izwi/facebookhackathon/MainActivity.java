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

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer=new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Intent login = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(login);
                finish();
            }
        },3000);
    }
    @Override
    protected void onResume() {
        super.onResume();

        timer=new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Intent login = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(login);
                finish();
            }
        },0000);
    }
}