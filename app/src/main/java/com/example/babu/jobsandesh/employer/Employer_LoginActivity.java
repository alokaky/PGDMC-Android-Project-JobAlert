package com.example.babu.jobsandesh.employer;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.babu.jobsandesh.R;
import com.example.babu.jobsandesh.jobseeker.JobseekNavActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Employer_LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText emp_log_username,emp_log_password;
    Button emp_login,emp_btn_to_register_activty;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer__login);

        emp_log_username = (EditText)findViewById(R.id.et_log_emp_username);
        emp_log_password = (EditText)findViewById(R.id.et_log_emp_password);

        emp_login = (Button)findViewById(R.id.btn_emp_login);
        emp_btn_to_register_activty = (Button)findViewById(R.id.btn_to_register_activity);

        emp_login.setOnClickListener(this);
        emp_btn_to_register_activty.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId()== R.id.btn_emp_login)
        {
            String e_log_username=emp_log_username.getText().toString(),
                    e_log_password=emp_log_password.getText().toString();
                if(!e_log_username.equals("") && !e_log_password.equals(""))
                {
                    new BackgroundEmployerLogin(this).execute(e_log_username,e_log_password);
                }
                else
                {
                    if(e_log_username.equals("")) {
                        emp_log_username.setError("Empty");
                    }
                    if(e_log_password.equals(""))
                    {
                        emp_log_password.setError("Empty");
                    }
                }

        }else{
            Intent intent = new Intent(Employer_LoginActivity.this, EmployerRegisterActivity.class);
            startActivity(intent);
        }
    }
}