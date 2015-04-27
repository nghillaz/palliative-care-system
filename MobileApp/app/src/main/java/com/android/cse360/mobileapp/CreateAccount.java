package com.android.cse360.mobileapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class CreateAccount extends ActionBarActivity {


    Button createAccountButton;
    EditText emailText;
    EditText passwordText;
    EditText firstNameText;
    EditText lastNameText;
    EditText phoneText;
    EditText addressText;
    Context context = this;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        createAccountButton = (Button) findViewById(R.id.newAccountButton);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                emailText = (EditText)findViewById(R.id.newEmail);
                passwordText = (EditText)findViewById(R.id.newPassword);
                firstNameText = (EditText)findViewById(R.id.newFirstName);
                lastNameText = (EditText)findViewById(R.id.newLastName);
                phoneText = (EditText) findViewById(R.id.newPhoneNumber);
                addressText = (EditText)findViewById(R.id.newAddress);

                try {
                    FileOutputStream fOut = openFileOutput("sampleFile.csv", MODE_WORLD_WRITEABLE);

                    OutputStreamWriter outStream = new OutputStreamWriter(fOut);

                    outStream.write(emailText.getText().toString());

                    outStream.flush();
                    outStream.close();

                }
                catch  (IOException e)
                {
                    e.printStackTrace();
                }

                Intent myIntent = new Intent(context, MainMenu.class);
                startActivity(myIntent);
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_account, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
