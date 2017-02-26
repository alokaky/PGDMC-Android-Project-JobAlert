package com.example.babu.jobsandesh.employer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.babu.jobsandesh.R;

public class EmployerRegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText emp_name, emp_company_name, emp_username, emp_password, emp_cnf_password;
    Button btn_emp_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_register);

        emp_name = (EditText) findViewById(R.id.et_emp_name);
        emp_company_name = (EditText) findViewById(R.id.et_emp_company_name);
        emp_username = (EditText) findViewById(R.id.et_emp_username);
        emp_password = (EditText) findViewById(R.id.et_emp_password);
        emp_cnf_password = (EditText) findViewById(R.id.et_emp_cnfpassword);

        btn_emp_register = (Button) findViewById(R.id.btn_emp_register);

        btn_emp_register.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        String e_name = emp_name.getText().toString(),
                e_company_name = emp_company_name.getText().toString(),
                e_username = emp_username.getText().toString(),
                e_password = emp_password.getText().toString(),
                e_cnf_password = emp_cnf_password.getText().toString();

        if (!e_name.matches("") && !e_company_name.matches("")
                && !e_username.matches("") && !e_password.matches("")
                && !e_cnf_password.matches(""))
        {

            if (e_password.equals(e_cnf_password)) {

                new BackgroundEmployerRegister(this).execute(e_name,
                        e_company_name,
                        e_username,
                        e_password,
                        e_cnf_password);
            } else {
                Toast.makeText(this, "PASSWORD should be Same", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(EmployerRegisterActivity.this, "Empty Field", Toast.LENGTH_SHORT).show();
        }

    }
}