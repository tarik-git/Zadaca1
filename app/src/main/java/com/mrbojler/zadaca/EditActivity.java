package com.mrbojler.zadaca;

import static com.mrbojler.zadaca.MainActivity.EXTRA_ABOUT_ME;
import static com.mrbojler.zadaca.MainActivity.EXTRA_ADDRESS;
import static com.mrbojler.zadaca.MainActivity.EXTRA_CITY;
import static com.mrbojler.zadaca.MainActivity.EXTRA_COUNTRY;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    EditText addressEditText;
    EditText cityEditText;
    EditText countryEditText;
    EditText aboutMeEditText;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        findViews();
        setViews(savedInstanceState);
    }

    private void findViews() {
        addressEditText = findViewById(R.id.activity_edit_address_edit_text);
        cityEditText = findViewById(R.id.activity_edit_city_edit_text);
        countryEditText = findViewById(R.id.activity_edit_country_edit_text);
        aboutMeEditText = findViewById(R.id.activity_edit_about_me_edit_text);
        saveButton = findViewById(R.id.save_button);
    }

    private void setViews(Bundle savedInstanceState) {
        String address="";
        String city="";
        String country="";
        String aboutMe="";
        if (savedInstanceState != null) {
            address = savedInstanceState.getString(EXTRA_ADDRESS);
            city = savedInstanceState.getString(EXTRA_CITY);
            country = savedInstanceState.getString(EXTRA_COUNTRY);
            aboutMe = savedInstanceState.getString(EXTRA_ABOUT_ME);
        } else {
            Intent intent = getIntent();
            address = intent.getStringExtra(EXTRA_ADDRESS);
            city = intent.getStringExtra(EXTRA_CITY);
            country = intent.getStringExtra(EXTRA_COUNTRY);
            aboutMe = intent.getStringExtra(EXTRA_ABOUT_ME);
        }
        addressEditText.setText(address);
        cityEditText.setText(city);
        countryEditText.setText(country);
        aboutMeEditText.setText(aboutMe);

        saveButton.setOnClickListener(this::onSaveButtonClick);
    }

    private void onSaveButtonClick(View view) {

        String address = addressEditText.getText().toString();
        String city = cityEditText.getText().toString();
        String country = countryEditText.getText().toString();
        String aboutMe = aboutMeEditText.getText().toString();

        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_ADDRESS, address);
        resultIntent.putExtra(EXTRA_CITY, city);
        resultIntent.putExtra(EXTRA_COUNTRY, country);
        resultIntent.putExtra(EXTRA_ABOUT_ME, aboutMe);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        String address = addressEditText.getText().toString();
        String city = cityEditText.getText().toString();
        String country = countryEditText.getText().toString();
        String aboutMe = aboutMeEditText.getText().toString();

        outState.putString(EXTRA_ADDRESS, address);
        outState.putString(EXTRA_CITY, city);
        outState.putString(EXTRA_COUNTRY, country);
        outState.putString(EXTRA_ABOUT_ME, aboutMe);
    }
}