/*
 * Copyright 2015-2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.rakeshrohilla.demo.Fileshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.rakeshrohilla.demo.FileShare.R;
import com.rakeshrohilla.demo.FileShare.R;
import com.google.firebase.auth.FirebaseAuth;

/*
 * This is the beginning screen that lets the user select if they want to upload or download
 */


public class AwsListActivity extends Activity {

    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aws_list);
        initUI();
    }

    private void initUI() {
        FrameLayout download = findViewById(R.id.buttonDownloadMain);
        FrameLayout upload = findViewById(R.id.buttonUploadMain);
        FrameLayout logout = findViewById(R.id.logout);

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        Toast.makeText(this, "" + currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();

        download.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(AwsListActivity.this, DownloadActivity.class);
                startActivity(intent);
            }
        });

        upload.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(AwsListActivity.this, UploadActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent logout_intent = new Intent(AwsListActivity.this, MainActivity.class);
                startActivity(logout_intent);
            }
        });
    }

    @Override
    public void onBackPressed(){

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
