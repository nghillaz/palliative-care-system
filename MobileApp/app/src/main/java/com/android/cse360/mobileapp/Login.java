package com.android.cse360.mobileapp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Login extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addListenerOnButton();
    }

    public void addListenerOnButton() {

        Button createAccountButton, forgotPasswordEmailButton, loginButton;

        final Context context = this;

        createAccountButton = (Button) findViewById(R.id.createAccountButton);
        forgotPasswordEmailButton = (Button) findViewById(R.id.forgotButton);
        loginButton = (Button) findViewById(R.id.loginButton);


        loginButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(context, MainMenu.class);
                startActivity(myIntent);
                finish();
            }

        });

        forgotPasswordEmailButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(context, ForgotPasswordEmail.class);
                startActivity(myIntent);
            }

        });

        createAccountButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(context, CreateAccount.class);
                startActivity(myIntent);
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
