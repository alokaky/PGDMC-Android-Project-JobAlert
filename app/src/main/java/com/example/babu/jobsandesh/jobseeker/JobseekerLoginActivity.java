package com.example.babu.jobsandesh.jobseeker;


import android.app.AlertDialog;
import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.babu.jobsandesh.R;
import com.example.babu.jobsandesh.jstabfragment.BackGroundFragmentActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class JobseekerLoginActivity extends AppCompatActivity implements View.OnClickListener{


    EditText seeker_username,seeker_password;
    Button login_button,button_throw_registerActivity;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_jobseeker_login);

            seeker_username = (EditText) findViewById(R.id.et_seek_username);
            seeker_password = (EditText) findViewById(R.id.et_seek_password);

            login_button = (Button) findViewById(R.id.bt_seek_login);

            button_throw_registerActivity = (Button) findViewById(R.id.bt_reg_activity);

            login_button.setOnClickListener(this);
            button_throw_registerActivity.setOnClickListener(this);
        }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.bt_seek_login)
        {
            String log_username=seeker_username.getText().toString(),
                    log_password=seeker_password.getText().toString(),result="";
            if(!log_username.equals("") && !log_password.equals(""))
            {
                new BackgroundJobseekerLogin(this).execute(log_username, log_password);

            }
            else
            {
                if(log_username.equals("")) {
                    seeker_username.setError("Empty");
                }
                if(log_password.equals(""))
                {
                    seeker_password.setError("Empty");
                }
            }
        }
        else
        {
            Intent intent=new Intent(this,JobseekRegisterActivity.class);
            startActivity(intent);
        }

    }

    public void skip(View view)
    {
        Intent intent=new Intent(JobseekerLoginActivity.this,JobseekNavActivity.class);
        startActivity(intent);
    }
}