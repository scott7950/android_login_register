package com.example.scott.login_register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    public static UserDB userDB;
    public static User sessionUser;

    Button bLogin;
    EditText etUsername, etPassword;
    TextView tvRegisterLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);

        bLogin.setOnClickListener(this);
        tvRegisterLink.setOnClickListener(this);

        userDB = new UserDB(this);
        sessionUser = new User();

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.bLogin:


                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                User user = new User(username, password);
                if(user.isValidUsernamePattern() && user.isValidPasswordPattern()) {
                    if(MainActivity.userDB.authUser(user) == 1) {
                        MainActivity.sessionUser.update(user);
                        startActivity(new Intent(this, ShowInformation.class));
                    }
                }
                break;

            case R.id.tvRegisterLink:
                startActivity(new Intent(this, Register.class));
                break;
        }
    }
}
