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


public class MainMenu extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        addListenerOnButton();
    }

    public void addListenerOnButton() {


        Button setDoctorButton, enterSymptomsButton, editPersonalDetails, logoutButton;

        final Context context = this;

        setDoctorButton = (Button) findViewById(R.id.setDoctorButton);
        enterSymptomsButton = (Button) findViewById(R.id.symptomButton);
        editPersonalDetails = (Button) findViewById(R.id.editDetailsButton);
        logoutButton = (Button) findViewById(R.id.logOutButton);


        setDoctorButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick (View arg0){

                Intent myIntent = new Intent(context, SetDoctor.class);
                startActivity(myIntent);
            }
        });

        enterSymptomsButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick (View arg0){

                Intent myIntent = new Intent(context, EnterSymptoms.class);
                startActivity(myIntent);
            }
        });

        editPersonalDetails.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick (View arg0){

                Intent myIntent = new Intent(context, EditPersonalDetails.class);
                startActivity(myIntent);
            }
        });

        logoutButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick (View arg0){

                Intent myIntent = new Intent(context, Login.class);
                startActivity(myIntent);
            }
        });

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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
