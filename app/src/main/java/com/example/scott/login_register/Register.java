package com.example.scott.login_register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Register extends ActionBarActivity implements View.OnClickListener {

    Button bRegister;
    EditText etName, etAge, etUsername, etPassword;
    TextView tvLoginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bRegister = (Button) findViewById(R.id.bRegister);
        tvLoginLink = (TextView) findViewById(R.id.tvLoginLink);

        bRegister.setOnClickListener(this);
        tvLoginLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.bRegister:
                User user = new User();

                String name = etName.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if(user.isValidAgePattern(etAge.getText().toString())) {
                    int age = Integer.parseInt(etAge.getText().toString());

                    user.update(username, password, name, age);
                    if(user.isValidUsernamePattern() && user.isValidPasswordPattern() && user.isValidNamePattern()) {
                        if(MainActivity.userDB.addUser(user) == 1) {
                            MainActivity.sessionUser.update(user);
                            startActivity(new Intent(this, ShowInformation.class));
                        }
                    }
                }

                break;

            case R.id.tvLoginLink:
                onBackPressed();
                break;
        }
    }
}
