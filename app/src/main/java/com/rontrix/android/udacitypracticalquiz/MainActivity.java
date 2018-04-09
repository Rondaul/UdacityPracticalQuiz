package com.rontrix.android.udacitypracticalquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String ABOUT = "message";


    Toolbar mToolbar;
    EditText mNameEditText;
    EditText mEmailEditText;
    EditText mAboutEditText;
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mNameEditText = findViewById(R.id.main_username);
        mEmailEditText = findViewById(R.id.main_email);
        mAboutEditText = findViewById(R.id.main_message);
        mButton = findViewById(R.id.next_button);

        if(savedInstanceState != null) {
            mNameEditText.setText(savedInstanceState.getString(NAME));
            mEmailEditText.setText(savedInstanceState.getString(EMAIL));
            mAboutEditText.setText(savedInstanceState.getString(ABOUT));
        }

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mNameEditText.getText().toString();
                String email = mEmailEditText.getText().toString();
                String about = mAboutEditText.getText().toString();

                SharedPreferences sharedPreferences = getSharedPreferences("userdata", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(NAME , name);
                editor.putString(EMAIL, email);
                editor.putString(ABOUT, about);
                editor.apply();

                mNameEditText.setText(null);
                mEmailEditText.setText(null);
                mAboutEditText.setText(null);

                startActivity(new Intent(MainActivity.this, DetailsActivity.class));
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        String name = mNameEditText.getText().toString();
        String email = mEmailEditText.getText().toString();
        String about = mAboutEditText.getText().toString();

        outState.putString(NAME, name);
        outState.putString(EMAIL, email);
        outState.putString(ABOUT, about);

        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectedId = item.getItemId();

        if(selectedId == R.id.action_main) {
            startActivity(new Intent(this, DetailsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
