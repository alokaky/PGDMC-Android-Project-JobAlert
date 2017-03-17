package com.example.babu.jobsandesh.jobseeker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import java.io.IOException;
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

        //String register_url = "http://192.168.42.222/job_alert_app/jobseeker/register_seeker.php";
        String register_url="https://www-jobalert-com.000webhostapp.com/job_alert_app/jobseeker/register_seeker.php";
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