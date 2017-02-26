package com.example.babu.jobsandesh.employer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

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

/**
 * Created by Alok on 28/Jan/17.
 */
public class BackgroundEmployerRegister extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
    BackgroundEmployerRegister(Context ctx) {
        context = ctx;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Register Status");
        alertDialog.setCancelable(true);
    }

    @Override
    protected String doInBackground(String... params) {
        String register_url = "http://192.168.42.222/job_alert_app/employer/registerEmp.php";
        try {
            String  str_e_name = params[0],
                    str_e_company_name = params[1],
                    str_e_username = params[2],
                    str_e_password = params[3];

            URL url = new URL(register_url);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            String post_data = URLEncoder.encode("name_by_post", "UTF-8") + "=" + URLEncoder.encode(str_e_name, "UTF-8")
                    + "&"
                    + URLEncoder.encode("company_name_by_post", "UTF-8") + "=" + URLEncoder.encode(str_e_company_name, "UTF-8")
                    + "&"
                    + URLEncoder.encode("username_by_post", "UTF-8") + "=" + URLEncoder.encode(str_e_username, "UTF-8")
                    + "&"
                    + URLEncoder.encode("password_by_post", "UTF-8") + "=" + URLEncoder.encode(str_e_password, "UTF-8");

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            String result = "";
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {

                result += line;
            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            Log.d("RESULT",result);

            return result;

        } catch (IOException e) {
            return "Connection Problem";
        }

    }

    protected void onPostExecute(String result) {
Log.d("RESULT",result);
        if (result.equals("Successfully registered")) {
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(context.getApplicationContext(),Employer_LoginActivity.class);
            context.startActivity(intent);
        } else if(result.equals("Connection Problem")){
            alertDialog.setMessage(result);
            alertDialog.show();
        }else
        {
            alertDialog.setMessage(result);
            alertDialog.show();
        }
    }
}