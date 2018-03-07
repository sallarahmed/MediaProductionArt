package com.mediaproductionart.mediaproductionart;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.mediaproductionart.mediaproductionart.services.DomainAndWebFragment;
import com.mediaproductionart.mediaproductionart.services.GraphicDesigningFragment;
import com.mediaproductionart.mediaproductionart.services.MobileAppFragment;
import com.mediaproductionart.mediaproductionart.services.OnlineMarketingFragment;
import com.mediaproductionart.mediaproductionart.services.PrintMediaFragment;
import com.mediaproductionart.mediaproductionart.services.SocialMediaFragment;
import com.mediaproductionart.mediaproductionart.services.WebDesigningFragment;
import com.mediaproductionart.mediaproductionart.services.WebDevelopmentFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,HomeFragment.OnFragmentInteractionListener,WebDesigningFragment.OnFragmentInteractionListener
,DomainAndWebFragment.OnFragmentInteractionListener,GraphicDesigningFragment.OnFragmentInteractionListener,MobileAppFragment.OnFragmentInteractionListener,
        OnlineMarketingFragment.OnFragmentInteractionListener,PrintMediaFragment.OnFragmentInteractionListener,SocialMediaFragment.OnFragmentInteractionListener
,WebDevelopmentFragment.OnFragmentInteractionListener ,AboutUsFragment.OnFragmentInteractionListener ,BlogFragment.OnFragmentInteractionListener
,ContUsFragment.OnFragmentInteractionListener,EducationFragment.OnFragmentInteractionListener,PackagesFragment.OnFragmentInteractionListener,
        CarrierFragment.OnFragmentInteractionListener,PortfolioFragment.OnFragmentInteractionListener {

    String services[]={"Select Service","Website Designing","Graphic Designing","Website Development","Online Marketing(SEO)",
            "Social Media Marketing","Print Media and Branding","Mobile app Development","Domain and Web Hosting"};

    Fragment f[] = {new HomeFragment(),new WebDesigningFragment(),new GraphicDesigningFragment(),new WebDevelopmentFragment(),
            new OnlineMarketingFragment(),new SocialMediaFragment(), new PrintMediaFragment(),new MobileAppFragment(),new DomainAndWebFragment(),
    new PortfolioFragment(),new CarrierFragment(),new CarrierFragment(),new PackagesFragment(),new AboutUsFragment(),new ContUsFragment()};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        Spinner spinner = (Spinner) navigationView.getMenu().findItem(R.id.navigation_drawer_item3).getActionView();
        spinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,services));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                getSupportFragmentManager().beginTransaction().
                        replace(R.id.content_main, f[position]).commit();

           //     Toast.makeText(MainActivity.this,services[position],Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });





    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
       // int id = item.getItemId();
        switch (item.getItemId()){

            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.content_main, f[0]).commit();
                break;
            case R.id.nav_portfolio:
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.content_main, f[9]).commit();
                break;
            case R.id.nav_carrier:
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.content_main, f[10]).commit();
                break;
            case R.id.nav_education:
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.content_main, f[11]).commit();
                break;
            case R.id.nav_packages:
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.content_main, f[12]).commit();
                break;
            case R.id.nav_about_us:
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.content_main, f[13]).commit();
                break;
            case R.id.nav_cont_us:
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.content_main, f[14]).commit();
                break;



        }



      /*  if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_portfolio) {

        } else if (id == R.id.nav_education) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
