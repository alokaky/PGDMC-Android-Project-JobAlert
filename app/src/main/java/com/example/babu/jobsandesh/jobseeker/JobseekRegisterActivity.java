package com.example.babu.jobsandesh.jobseeker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.babu.jobsandesh.R;

public class JobseekRegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText seeker_fname,
            seeker_lname,
            seeker_password,
            seeker_username,
            seeker_confirm_password;

    Button register_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobseek_register);

        seeker_fname = (EditText) findViewById(R.id.et_seeker_fname);
        seeker_lname = (EditText) findViewById(R.id.et_seeker_lname);
        seeker_username = (EditText) findViewById(R.id.et_seeker_username);
        seeker_password = (EditText) findViewById(R.id.et_seeker_password);
        seeker_confirm_password = (EditText) findViewById(R.id.et_seeker_confirm_password);


        register_button = (Button) findViewById(R.id.btn_seek_register);

        register_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String str_seeker_fname = seeker_fname.getText().toString(),
                str_seeker_lname = seeker_lname.getText().toString(),
                str_seeker_username = seeker_username.getText().toString(),
                str_seeker_password = seeker_password.getText().toString(),
                str_seeker_confirm_password = seeker_confirm_password.getText().toString();

        if( !str_seeker_fname.matches("") && !str_seeker_lname.matches("")
                && !str_seeker_username.matches("") && !str_seeker_password.matches("")
                && !str_seeker_confirm_password.matches(""))
        {

            if (str_seeker_password.equals(str_seeker_confirm_password)) {

                new BackgroundJobseekerRegister(this).execute(
                        str_seeker_fname,
                        str_seeker_lname,
                        str_seeker_username,
                        str_seeker_password);
            }

        }
        else
        {
            Toast.makeText(JobseekRegisterActivity.this,"Empty Field",Toast.LENGTH_SHORT).show();
        }
    }
}