package com.example.babu.jobsandesh.jstabfragment;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.babu.jobsandesh.jstabfragment.OffCampus_Fragment.arrayList;

/**
 * Created by Alok on 26/Jan/17.
 */

public class BackGroundFragmentActivity extends AsyncTask<String,Void,String> {

    Context context;

    public BackGroundFragmentActivity(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String register_url = "http://192.168.42.222/job_alert_app/jobseeker/job_list.php";
        try {
            URL url = new URL(register_url);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

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
            Log.d("RESULTFROMSERVER", result);
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void onPostExecute(String result) {
        Log.d("result FROM SERVER", result);
        String emp = null, compname = null, post = null;
        //Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        if (result != null) {
            try {

                JSONObject jsonObject = new JSONObject(result);
                JSONArray arr_res = jsonObject.getJSONArray("result");

                if (arr_res.length() > 0) {

                    for (int i = 0; i < arr_res.length(); i++) {

                        JSONObject r = arr_res.getJSONObject(i);

                        emp = r.getString("emp");
                        compname = r.getString("compname");
                        post = r.getString("post");

                        arrayList.add(new JobseekerOffCampus_Cards(emp, compname, post));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            Log.d("RESULT = ",result);
        }
    }
}