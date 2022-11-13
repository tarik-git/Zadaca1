package com.mrbojler.zadaca;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_EDIT = 123;

    public static final String EXTRA_ADDRESS = "EXTRA_NAME";
    public static final String EXTRA_CITY = "EXTRA_CITY";
    public static final String EXTRA_COUNTRY = "EXTRA_COUNTRY";
    public static final String EXTRA_ABOUT_ME = "EXTRA_ABOUT_ME";

    TextView addressTextView;
    TextView cityTextView;
    TextView countryTextView;
    TextView aboutMeTextView;
    Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewsById();
        setViews(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE_EDIT && data != null) {
                String address = data.getStringExtra(EXTRA_ADDRESS);
                String city = data.getStringExtra(EXTRA_CITY);
                String country = data.getStringExtra(EXTRA_COUNTRY);
                String aboutMe = data.getStringExtra(EXTRA_ABOUT_ME);

                addressTextView.setText(address);
                cityTextView.setText(city);
                countryTextView.setText(country);
                aboutMeTextView.setText(aboutMe);
            }
        }
    }

    private void findViewsById() {
        addressTextView = findViewById(R.id.address_text_view);
        cityTextView = findViewById(R.id.city_text_view);
        countryTextView = findViewById(R.id.country_text_view);
        aboutMeTextView = findViewById(R.id.about_me_text_view);
        editButton = findViewById(R.id.edit_button);
    }

    private void setViews(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            String address = savedInstanceState.getString(EXTRA_ADDRESS);
            String city = savedInstanceState.getString(EXTRA_CITY);
            String country = savedInstanceState.getString(EXTRA_COUNTRY);
            String aboutMe = savedInstanceState.getString(EXTRA_ABOUT_ME);
            addressTextView.setText(address);
            cityTextView.setText(city);
            countryTextView.setText(country);
            aboutMeTextView.setText(aboutMe);
        }
        editButton.setOnClickListener(this::onEditButtonClick);
    }

    private void onEditButtonClick(View view) {
        String address = addressTextView.getText().toString();
        String city = cityTextView.getText().toString();
        String country = countryTextView.getText().toString();
        String aboutMe = aboutMeTextView.getText().toString();

        Intent intent = new Intent(getBaseContext(), EditActivity.class);
        intent.putExtra(EXTRA_ADDRESS, address);
        intent.putExtra(EXTRA_CITY, city);
        intent.putExtra(EXTRA_COUNTRY, country);
        intent.putExtra(EXTRA_ABOUT_ME, aboutMe);
        startActivityForResult(intent, REQUEST_CODE_EDIT);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        String address = addressTextView.getText().toString();
        String city = cityTextView.getText().toString();
        String country = countryTextView.getText().toString();
        String aboutMe = aboutMeTextView.getText().toString();

        outState.putString(EXTRA_ADDRESS, address);
        outState.putString(EXTRA_CITY, city);
        outState.putString(EXTRA_COUNTRY, country);
        outState.putString(EXTRA_ABOUT_ME, aboutMe);
    }
}