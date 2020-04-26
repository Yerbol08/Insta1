package com.smart.insta1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText editPassword;
    private EditText editTextName;
    private Button signin;
    private TextView textView;


    String name = "admin", password= "admin";


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextName  = (EditText) findViewById(R.id.editTextName);
        editPassword =  (EditText) findViewById(R.id.editTextPassword);
        editPassword.addTextChangedListener(loginTextWatcher);
        editTextName.addTextChangedListener(loginTextWatcher);
        signin = (Button)findViewById(R.id.singnbtn);
        textView = (TextView) findViewById(R.id.textViewInfo);



        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Click singnin", Toast.LENGTH_LONG).show();

                if (name.equals(editTextName.getText().toString()) || password.equals(editPassword.getText().toString()))
                {
                    saveData(name, password);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

                }
                else {
                    textView.setText(R.string.loginError);
                }
            }
        });
    }


    private void saveData( String log, String pass){
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("login", log);
        editor.putString("password",pass);

        editor.apply();
        Log.d("SaveData","Success");

    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String login = editTextName.getText().toString().trim();
            String password = editPassword.getText().toString().trim();

            signin.setEnabled(login.length() >=3 && password.length()>=3);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
