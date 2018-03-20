package com.mediaproductionart.mediaproductionart;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.telephony.PhoneNumberUtils;
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
import android.widget.LinearLayout;
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



    private boolean fabExpanded = false;
    private FloatingActionButton fabChat;
    private LinearLayout layoutFabWhatsapp;
    private LinearLayout layoutFabMessanger;
  //  private LinearLayout layoutFabPhoto;


    String services[]={"Select Service","Website Designing","Graphic Designing","Website Development","Online Marketing(SEO)",
            "Social Media Marketing","Print Media and Branding","Mobile app Development","Domain and Web Hosting"};

    Fragment f[] = {new HomeFragment(),new WebDesigningFragment(),new GraphicDesigningFragment(),new WebDevelopmentFragment(),
            new OnlineMarketingFragment(),new SocialMediaFragment(), new PrintMediaFragment(),new MobileAppFragment(),new DomainAndWebFragment(),
    new PortfolioFragment(),new CarrierFragment(),new EducationFragment(),new PackagesFragment(),new AboutUsFragment(),new ContUsFragment()};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fabChat =  findViewById(R.id.fabChat);

        layoutFabMessanger =  this.findViewById(R.id.layoutFabMessanger);
        layoutFabWhatsapp =  this.findViewById(R.id.layoutFabWhatsApp);


        fabChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fabExpanded == true){
                    closeSubMenusFab();
                } else {
                    openSubMenusFab();
                }
            }
        });

        layoutFabWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openWhatsApp();
                //Toast.makeText(MainActivity.this, "WhatsApp", Toast.LENGTH_SHORT).show();
            }
        });

        layoutFabMessanger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              openMessanger();
                //  Toast.makeText(MainActivity.this, "Messanger", Toast.LENGTH_SHORT).show();
            }
        });



        //Only main FAB is visible in the beginning
        closeSubMenusFab();



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




    private void openMessanger(){

        String smsNumber = "923133876798";
        boolean isWhatsappInstalled = whatsappInstalledOrNot("com.facebook.orca");
        if (isWhatsappInstalled) {

            Intent sendIntent = new Intent("android.intent.action.MAIN");
            sendIntent.setComponent(new ComponentName("com.facebook.orca", "com.facebook.orca.Conversation"));
            sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(smsNumber) + "@s.whatsapp.net");//phone number without "+" prefix

            startActivity(sendIntent);
        } else {
            Uri uri = Uri.parse("market://details?id=com.whatsapp");
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            Toast.makeText(this, "WhatsApp not Installed",
                    Toast.LENGTH_SHORT).show();
            startActivity(goToMarket);
        }




        /*   Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "My message to send");
        sendIntent.setType("text/plain");
        sendIntent.setPackage("com.facebook.orca");

        try {
            startActivity(sendIntent);
        } catch (android.content.ActivityNotFoundException ex) {
         //   ToastHelper.show(this, "Please Install Facebook Messenger");

        }*/
    }


   // It lets you open WhatsApp conversation screen for that specific user you are trying to communicate with:

    private void openWhatsApp() {
        String smsNumber = "923133876798";
        boolean isWhatsappInstalled = whatsappInstalledOrNot("com.whatsapp");
        if (isWhatsappInstalled) {

            Intent sendIntent = new Intent("android.intent.action.MAIN");
            sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
            sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(smsNumber) + "@s.whatsapp.net");//phone number without "+" prefix

            startActivity(sendIntent);
        } else {
            Uri uri = Uri.parse("market://details?id=com.whatsapp");
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            Toast.makeText(this, "WhatsApp not Installed",
                    Toast.LENGTH_SHORT).show();
            startActivity(goToMarket);
        }
    }

    private boolean whatsappInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }









/*
    private void openWhatsApp() {
        String smsNumber = "00923133876789"; //without '+'
        Intent sendIntent = null;
        try {

                sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net"); //phone number without "+" prefix
                sendIntent.setPackage("com.whatsapp");

                startActivity(sendIntent);

        } catch(Exception e) {

            if (sendIntent.resolveActivity(this.getPackageManager()) == null) {
                Toast.makeText(this, "Error/n" + e.toString(), Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Error\n" + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }*/


    //closes FAB submenus
    private void closeSubMenusFab(){
        layoutFabMessanger.setVisibility(View.INVISIBLE);
        layoutFabWhatsapp.setVisibility(View.INVISIBLE);
     //   layoutFabPhoto.setVisibility(View.INVISIBLE);
        fabChat.setImageResource(R.drawable.ic_chat_32);
        fabExpanded = false;
    }

    //Opens FAB submenus
    private void openSubMenusFab(){
        layoutFabMessanger.setVisibility(View.VISIBLE);
        layoutFabWhatsapp.setVisibility(View.VISIBLE);
     //   layoutFabPhoto.setVisibility(View.VISIBLE);
        //Change settings icon to 'X' icon
        fabChat.setImageResource(R.drawable.ic_close_white);
        fabExpanded = true;
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



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
