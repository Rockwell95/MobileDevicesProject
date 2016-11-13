package com.example.devin.mobiledevicesproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class SignupActivity extends AppCompatActivity {
    EditText editUserFirstName;
    EditText editUserLastName;
    EditText editUserEmail;
    EditText editUserPassword;
    EditText editUserPasswordConfirm;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        editUserFirstName = (EditText) findViewById(R.id.editUserFirstName);
        editUserLastName = (EditText) findViewById(R.id.editUserLastName);
        editUserEmail = (EditText) findViewById(R.id.editUserEmail);
        editUserPassword = (EditText) findViewById(R.id.editUserPassword);
        editUserPasswordConfirm = (EditText) findViewById(R.id.editUserPasswordConfirm);

        editUserEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                // TODO: add proper input authentication
                if (!editUserEmail.getText().toString().matches("@+")) {
                    editUserEmail.setError("Invalid email");
                }
            }
        });

        editUserPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
