package com.example.babu.jobsandesh.jobseeker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

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

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Alok on 27/Jan/17.
 */

public class BackgroundJobseekerRegister extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
    BackgroundJobseekerRegister(Context ctx) {
        context = ctx;
    }


    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Registration Status");
        alertDialog.setCancelable(true);
    }

    @Override
    protected String doInBackground(String... params) {

        String result="";
        String str_seeker_fname = params[0],
                str_seeker_lname = params[1],
                str_seeker_username = params[2],
                str_seeker_password = params[3];

        FirebaseMessaging.getInstance().subscribeToTopic("test");
        String token=FirebaseInstanceId.getInstance().getToken();

        String register_url = "http://192.168.42.222/job_alert_app/jobseeker/register_seeker.php";

        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("firstname_by_post", str_seeker_fname);
        builder.add("lastname_by_post", str_seeker_lname);
        builder.add("username_by_post",str_seeker_username);
        builder.add("password_by_post",str_seeker_password);
        builder.add("token_by_post",token);
        RequestBody body = builder.build();

        Request request =  new Request.Builder().url(register_url).post(body).build();

        try{
            Response response = client.newCall(request).execute();
            result=response.body().string();
            return result;
        }catch (IOException e)
        {
            e.printStackTrace();
            return "Connection Problem";
        }

        /*String register_url = "http://192.168.42.222/job_alert_app/jobseeker/register_seeker.php";
        try {
            String str_seeker_fname = params[0],
                    str_seeker_lname = params[1],
                    str_seeker_username = params[2],
                    str_seeker_password = params[3];

            FirebaseMessaging.getInstance().subscribeToTopic("test");
            String token=FirebaseInstanceId.getInstance().getToken();

            URL url = new URL(register_url);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            String post_data = URLEncoder.encode("firstname_by_post", "UTF-8") + "=" + URLEncoder.encode(str_seeker_fname, "UTF-8")
                    + "&"
                    + URLEncoder.encode("lastname_by_post", "UTF-8") + "=" + URLEncoder.encode(str_seeker_lname, "UTF-8")
                    + "&"
                    + URLEncoder.encode("username_by_post", "UTF-8") + "=" + URLEncoder.encode(str_seeker_username, "UTF-8")
                    + "&"
                    + URLEncoder.encode("password_by_post", "UTF-8") + "=" + URLEncoder.encode(str_seeker_password, "UTF-8")
                    + "&"
                    + URLEncoder.encode("token_by_post", "UTF-8") + "=" + URLEncoder.encode(token, "UTF-8");

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

            //Log.d("RESULT",result);
            return result;

        } catch (IOException e) {
            return "Connection Problem";
        }*/


    }

    protected void onPostExecute(String result) {
        if (result.equals("Successfully registered")) {

            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(context.getApplicationContext(),JobseekerLoginActivity.class);
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