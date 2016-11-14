package com.example.devin.mobiledevicesproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private static final int RESULT_SIGNUP_ACTIVITY = 1;
    boolean[] errors;

    EditText editLoginEmail;
    EditText editLoginPassword;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        errors = new boolean[] {true, true}; // 0 = email, 1 = password

        editLoginEmail = (EditText) findViewById(R.id.editLoginEmail);
        editLoginPassword = (EditText) findViewById(R.id.editLoginPassword);

        final RegexHelper rh = new RegexHelper(); // new instance of RegexHelper

        // validates email
        editLoginEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if (!editLoginEmail.getText().toString().matches(rh.email)) {
                    editLoginEmail.setError(getString(R.string.errorEmail));
                    errors[0] = true;
                } else {
                    errors[0] = false;
                }
            }
        });

        // validates initial password
        editLoginPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if (!editLoginPassword.getText().toString().matches(rh.password)) {
                    editLoginPassword.setError(getString(R.string.errorPasswordInvalid));
                    errors[1] = true;
                } else {
                    errors[1] = false;
                }
            }
        });
    }

    protected void login (View view) {
        // TODO: attempt to login
    }

    protected void signup (View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivityForResult(intent, RESULT_SIGNUP_ACTIVITY);
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == RESULT_SIGNUP_ACTIVITY) {
                // TODO: try to login
                DBHelper dbHelper = new DBHelper(this);

                // get input fields
                EditText editEmail = (EditText) findViewById(R.id.editLoginEmail);
                EditText editPassword = (EditText) findViewById(R.id.editLoginPassword);

                // get input from data fields
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();

                User user = dbHelper.login(email, password);
            }
        }
    }
}
