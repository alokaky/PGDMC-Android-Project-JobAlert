package com.example.babu.jobsandesh.employer;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.babu.jobsandesh.R;
import com.example.babu.jobsandesh.emptabfragment.EmpHomeFragment;

public class EmpNavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout empmDrawerLayout;
    NavigationView empmNavigationView;
    FragmentManager empmFragmentManager;
    FragmentTransaction empmFragmentTransaction;
    TextView emp_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_navigation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.emp_toolbar);
        setSupportActionBar(toolbar);

        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */

        empmFragmentManager = getSupportFragmentManager();
        empmFragmentTransaction = empmFragmentManager.beginTransaction();
        empmFragmentTransaction.replace(R.id.emp_containerView,new EmpHomeFragment()).commit();

        empmDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_emp_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, empmDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        empmDrawerLayout.addDrawerListener(toggle);//setDrawerListener(toggle);
        toggle.syncState();

        empmNavigationView = (NavigationView) findViewById(R.id.nav_emp_view);

        View header=empmNavigationView.getHeaderView(0);
        emp_name=(TextView)header.findViewById(R.id.tv_emp_name);
        emp_name.setText(getIntent().getStringExtra("name"));

        empmNavigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_emp_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.emp_navigation, menu);
        return true;
    }

    //
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.emp_action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.emp_nav_Home) {
            FragmentTransaction fragmentTransaction = empmFragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.emp_containerView,new EmpHomeFragment()).commit();
            // Handle the camera action
        } else if (id == R.id.emp_nav_Notifiaction) {
            FragmentTransaction fragmentTransaction = empmFragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.emp_containerView,new Emp_Notification_fragment()).commit();

        } else if (id == R.id.emp_nav_Setting) {
            FragmentTransaction fragmentTransaction = empmFragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.emp_containerView,new Emp_Setting_fragment()).commit();


//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }
        }

        empmDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}