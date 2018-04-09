package com.rontrix.android.udacitypracticalquiz;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    Toolbar mToolbar;
    TextView  mNameTextView;
    TextView mEmailTextView;
    TextView mAboutTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mToolbar = (Toolbar) findViewById(R.id.details_toolbar);
        setSupportActionBar(mToolbar);

        ActionBar mActionBar = getSupportActionBar();

        if(mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }

        mNameTextView = findViewById(R.id.name_details);
        mEmailTextView = findViewById(R.id.email_details);
        mAboutTextView = findViewById(R.id.about_details);

        SharedPreferences sharedPreferences = getSharedPreferences("userdata", MODE_PRIVATE);
        String name = sharedPreferences.getString(MainActivity.NAME, "");
        String email = sharedPreferences.getString(MainActivity.EMAIL, "");
        String about = sharedPreferences.getString(MainActivity.ABOUT, "");

        mNameTextView.setText(name);
        mEmailTextView.setText(email);
        mAboutTextView.setText(about);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectedId = item.getItemId();
        if(selectedId == R.id.homeAsUp) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
