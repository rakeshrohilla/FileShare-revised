package com.rakeshrohilla.demo.Fileshare;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.rakeshrohilla.demo.FileShare.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    //declare object
    EditText editTextPhone;

    FirebaseAuth mAuth;
    Button btnGetVerficationCode;

    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialization
        mAuth = FirebaseAuth.getInstance();
        editTextPhone = findViewById(R.id.editTextPhone);
        btnGetVerficationCode=findViewById(R.id.btn_get_verfication);



        btnGetVerficationCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = editTextPhone.getText().toString();

                if(phone.isEmpty()){
                    editTextPhone.setError("Phone number is required");
                    editTextPhone.requestFocus();
                    return;
                }

                if(phone.length() < 10 ){
                    editTextPhone.setError("Please enter a valid phone");
                    editTextPhone.requestFocus();
                    return;
                }
                Intent intent = new Intent(MainActivity.this, VerifyActivity.class);
                intent.putExtra("phone", phone);
                startActivity(intent);
                finish();
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