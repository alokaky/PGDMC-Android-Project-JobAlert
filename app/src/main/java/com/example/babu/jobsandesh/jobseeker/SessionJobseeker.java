package com.example.babu.jobsandesh.jobseeker;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Shader;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Set;

/**
 * Created by Alok on 28/Jan/17.
 */

public class SessionJobseeker {
    private SharedPreferences prefs;

    public SessionJobseeker(Context cntx)
    {
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setSess(int s)
    {
        prefs.edit().putInt("ses",s).commit();

    }
   public  int getSesss(){
       int onoff=prefs.getInt("ses",0);
       Log.d("GETSession->>>", String.valueOf(onoff));
       return onoff;
   }
}
