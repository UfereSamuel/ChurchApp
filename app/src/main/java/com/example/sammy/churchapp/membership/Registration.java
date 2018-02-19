package com.example.sammy.churchapp.membership;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sammy.churchapp.R;
import com.example.sammy.churchapp.WelcomePage;

public class Registration extends AppCompatActivity {

    EditText etFirstName, etSurName, etAddress, etPhone, etEmail;
    Spinner spGender;
    Button btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.regToolbar);

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etSurName = (EditText) findViewById(R.id.etSurName);
        etAddress = (EditText) findViewById(R.id.etAddress);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etEmail = (EditText) findViewById(R.id.etEmail);
        spGender = (Spinner) findViewById(R.id.spGender);
        btnReg = (Button) findViewById(R.id.btnReg);


        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fName = etFirstName.getText().toString().trim();
                String sName = etSurName.getText().toString().trim();
                String address = etAddress.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String gender = spGender.getItemAtPosition(spGender.getSelectedItemPosition()).toString();

                if (fName.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Name cannot be empty", Toast.LENGTH_LONG).show();
                }else if (sName.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Enter the Surname", Toast.LENGTH_LONG).show();
                }else if (address.isEmpty()){
                    Toast.makeText(getApplicationContext(), "enter the email", Toast.LENGTH_LONG);
                }else if (phone.length()>11){
                    Toast.makeText(getApplicationContext(), "enter the right phone", Toast.LENGTH_LONG).show();
                }else if (gender.equalsIgnoreCase("Select Gender")){
                    Toast.makeText(getApplicationContext(), "select a gender", Toast.LENGTH_LONG);
                } else {

                    Toast.makeText(getApplicationContext(), "Registration Successful!!", Toast.LENGTH_LONG);
                }

                Intent intent = new Intent(Registration.this, WelcomePage.class);
                startActivity(intent);
                finish();
            }
        });



    }

}
