package com.example.scott.login_register;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class ShowInformation extends ActionBarActivity implements View.OnClickListener {

    Button bLogout;
    EditText etUsername, etName, etAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_information);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        bLogout = (Button) findViewById(R.id.bLogout);

        bLogout.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        displayUserDetails();
    }

    private void displayUserDetails() {
        if(MainActivity.sessionUser.getUserType() == 1) {
            etUsername.setText(MainActivity.sessionUser.getUsername());
            etName.setText(MainActivity.sessionUser.getPassword());
            etAge.setText(MainActivity.sessionUser.getAge() + "");
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.bLogout:
                MainActivity.sessionUser.clear();
                //startActivity(new Intent(this, MainActivity.class));
                onBackPressed();
                break;

        }
    }
}
