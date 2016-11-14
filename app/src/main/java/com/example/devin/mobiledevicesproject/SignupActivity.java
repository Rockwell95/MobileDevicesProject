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
    EditText editUserBirthdateDay;
    EditText editUserBirthdateMonth;
    EditText editUserBirthdateYear;
    boolean[] errors;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        // 0 = email, 1 = password, 2 = passwordConfirm
        errors = new boolean[6];

        editUserFirstName = (EditText) findViewById(R.id.editUserFirstName);
        editUserLastName = (EditText) findViewById(R.id.editUserLastName);
        editUserEmail = (EditText) findViewById(R.id.editUserEmail);
        editUserPassword = (EditText) findViewById(R.id.editUserPassword);
        editUserPasswordConfirm = (EditText) findViewById(R.id.editUserPasswordConfirm);
        editUserBirthdateDay = (EditText) findViewById(R.id.editUserBirthdateDay);
        editUserBirthdateMonth = (EditText) findViewById(R.id.editUserBirthdateMonth);
        editUserBirthdateYear = (EditText) findViewById(R.id.editUserBirthdateYear);

        // validates email
        editUserEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if (!editUserEmail.getText().toString().matches("^\\w+@\\w+\\.\\w+$")) {
                    editUserEmail.setError(getString(R.string.errorEmail));
                    errors[0] = true;
                } else {
                    errors[0] = false;
                }
            }
        });

        // validates initial password
        editUserPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                String passwordRegex = "^(?=.*[a-z]+)(?=.*[A-Z])(?=.*\\d).{8,}$";

                if (!editUserPassword.getText().toString().matches(passwordRegex)) {
                    editUserPassword.setError(getString(R.string.errorPasswordInvalid));
                    errors[1] = true;
                } else {
                    errors[1] = false;
                }
            }
        });

        // validates confirmed password
        editUserPasswordConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if (!editUserPasswordConfirm.getText().toString().equals(editUserPassword.getText().toString())) {
                    editUserPasswordConfirm.setError(getString(R.string.errorMatchingPassword));
                    errors[2] = true;
                } else {
                    errors[2] = false;
                }
            }
        });

        // validates birth day
        editUserBirthdateDay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if (!editUserBirthdateDay.getText().toString().matches("^[\\d]{1,2}$")) {
                    editUserBirthdateDay.setError(getString(R.string.errorBirthdateDay));
                    errors[3] = true;
                } else {
                    errors[3] = false;
                }
            }
        });

        // validates month
        editUserBirthdateMonth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if (!editUserBirthdateMonth.getText().toString().matches("^[\\d]{1,2}$")) {
                    editUserBirthdateMonth.setError(getString(R.string.errorBirthdateMonth));
                    errors[4] = true;
                } else {
                    errors[4] = false;
                }
            }
        });

        // validates birth year
        editUserBirthdateYear.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if (!editUserBirthdateYear.getText().toString().matches("^[\\d]{4}$")) {
                    editUserBirthdateYear.setError(getString(R.string.errorBirthdateYear));
                    errors[5] = true;
                } else {
                    errors[5] = false;
                }
            }
        });
    }
}
