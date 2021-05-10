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
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.izwi.facebookhackathon.Common.Common;
import com.izwi.facebookhackathon.HomeActivity;
import com.izwi.facebookhackathon.R;

public class ProfileFragment extends Fragment {

    private FloatingActionButton fab_voice_profile;

    private  TextView txt_prodile_name,txt_profile_username;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        txt_prodile_name =view.findViewById(R.id.txt_prodile_name);
        txt_profile_username =view.findViewById(R.id.txt_profile_username);

        Log.d("Profile",Common.currentUser.toString());
        txt_prodile_name.setText("Full Name : "+Common.currentUser.getName());
        txt_profile_username.setText("Username : "+Common.currentUser.getUsername());

        fab_voice_profile = view.findViewById(R.id.fab_voice_profile);
        fab_voice_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity)getActivity()).speak_2_text();
            }
        });
        return view;
    }
}