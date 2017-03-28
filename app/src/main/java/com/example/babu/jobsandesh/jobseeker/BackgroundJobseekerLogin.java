package com.example.babu.jobsandesh.jobseeker;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.example.babu.jobsandesh.jstabfragment.BackGroundFragmentActivity;

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

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Alok on 27/Jan/17.
 */

public class BackgroundJobseekerLogin extends AsyncTask<String,Void,String> {

    Context context;
    AlertDialog alertDialog;


    public BackgroundJobseekerLogin(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
       // String register_url = "http://192.168.42.222/job_alert_app/jobseeker/login_seeker.php";
        String login_url="https://www-jobalert-com.000webhostapp.com/job_alert_app/jobseeker/login_seeker.php";
        String result = "";
        String str_seeker_username = params[0],
                str_seeker_password = params[1];

        OkHttpClient client = new OkHttpClient();

        FormBody.Builder builder = new FormBody.Builder();
        builder.add("username_by_post", str_seeker_username);
        builder.add("password_by_post", str_seeker_password);

        RequestBody body = builder.build();

        Request request = new Request.Builder().url(login_url).post(body).build();

        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
            Log.d("RESULT->>>",result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return "Connection Problem";
        }
        /*try {
            String str_seeker_username = params[0],
                    str_seeker_password = params[1];
            URL url = new URL(register_url);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("username_by_post", "UTF-8") + "=" + URLEncoder.encode(str_seeker_username, "UTF-8")
                    + "&"
                    + URLEncoder.encode("password_by_post", "UTF-8") + "=" + URLEncoder.encode(str_seeker_password, "UTF-8");

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
            Log.d("RESULT->>", result);

            return result;
        } catch (IOException e) {
            return "Connection Problem";
        }*/

    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
        alertDialog.setCancelable(true);
    }

    protected void onPostExecute(String result) {
        String fname = null, lname = null;


        Log.d("result", result);

        if (result.equals("Invalid Email or Password")) {
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        } else {
            if (result.equals("Connection Problem")) {

                alertDialog.setMessage(result);
                alertDialog.show();
            } else {
                try {
                    Log.d("SERVER RESULT", result);
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray arr_res = jsonObject.getJSONArray("result");
                    JSONObject r = arr_res.getJSONObject(0);
                    fname = r.getString("js_firstname");
                    lname = r.getString("js_lastname");
                } catch (JSONException e) {
                    Toast.makeText(context, result, Toast.LENGTH_LONG).show();
                }

                Intent in = new Intent(context, JobseekNavActivity.class);

                if (fname != null) {
                    in.putExtra("fname", fname);
                    in.putExtra("lname", lname);
                    new BackGroundFragmentActivity(context).execute();

                    context.startActivity(in);
                }
            }
        }
    }
}