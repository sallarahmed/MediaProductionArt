package com.mediaproductionart.mediaproductionart;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.telephony.PhoneNumberUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
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

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,HomeFragment.OnFragmentInteractionListener,WebDesigningFragment.OnFragmentInteractionListener
,DomainAndWebFragment.OnFragmentInteractionListener,GraphicDesigningFragment.OnFragmentInteractionListener,MobileAppFragment.OnFragmentInteractionListener,
        OnlineMarketingFragment.OnFragmentInteractionListener,PrintMediaFragment.OnFragmentInteractionListener,SocialMediaFragment.OnFragmentInteractionListener
,WebDevelopmentFragment.OnFragmentInteractionListener ,AboutUsFragment.OnFragmentInteractionListener ,BlogFragment.OnFragmentInteractionListener
,ContUsFragment.OnFragmentInteractionListener,EducationFragment.OnFragmentInteractionListener,PackagesFragment.OnFragmentInteractionListener,
        CarrierFragment.OnFragmentInteractionListener,PortfolioFragment.OnFragmentInteractionListener ,View.OnClickListener {


    public static String FACEBOOK_URL = "https://www.facebook.com/sallarahmed2";
    public static String FACEBOOK_PAGE_ID = "sallarahmed2";
    private boolean fabExpanded = false;
    private FloatingActionButton fabChat;
    private LinearLayout layoutFabWhatsapp;
    private LinearLayout layoutFabMessanger;
    private DrawerLayout drawer;
    private String TAG = "MainActivity";
    Context ctx;
    String path;

   // Uri obj "sallar";
    private static final String BACK_STACK_ROOT_TAG = "root_fragment";
  //  private LinearLayout layoutFabPhoto;

    public static final int requestCode = 1;
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
        ctx = this;

        fabChat =  findViewById(R.id.fabChat);

        layoutFabMessanger =  this.findViewById(R.id.layoutFabMessanger);
        layoutFabWhatsapp =  this.findViewById(R.id.layoutFabWhatsApp);
        if (!isStoragePermissionGranted()){
            int REQUEST_CODE =1;
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        }


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











        drawer = findViewById(R.id.drawer_layout);
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

                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });





    }



    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }



    //method to get the right URL to use in the intent
    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }







    private void openMessanger(){

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent
                .putExtra(Intent.ACTION_SENDTO,
                        "https://www.facebook.com/sallarahmed2");

        sendIntent.setPackage("com.facebook.orca");
        try {
            startActivity(sendIntent);
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this,"Please Install Facebook Messenger", Toast.LENGTH_LONG).show();
        }

    }


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
        drawer = findViewById(R.id.drawer_layout);
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

               Intent browserActivity = new Intent(this,BrowserActivity.class);
               browserActivity.putExtra("title","Packages");
               browserActivity.putExtra("url","https://mediaproductionart.com/packages");
               startActivity(browserActivity);

                /*getSupportFragmentManager().beginTransaction().
                        replace(R.id.content_main, f[12]).commit();*/
                break;

            case R.id.nav_blog:

                Intent bActivity = new Intent(this,BrowserActivity.class);
                bActivity.putExtra("title","Blog");
                bActivity.putExtra("url","https://www.mediaproductionart.com/Blog/");
                startActivity(bActivity);
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





    public void popupImage(final int img){

        GifImageView image = new GifImageView(this);
        image.setImageResource(img);

        AlertDialog.Builder builder =
                new AlertDialog.Builder(this).
                  //      setMessage("Message above the image").
                        setPositiveButton("Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).
                        setView(image);
        builder.create().show();



    }





    public void changeFragment(Fragment f) {
        // Pop off everything up to and including the current tab
        //this block of code saves fragment to backstack so don't call it if u don't want to save previous fragment to backstack
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        fragmentManager.beginTransaction()
                .replace(R.id.content_main, f)
                .addToBackStack(BACK_STACK_ROOT_TAG)
                .commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            if (!isStoragePermissionGranted()){
                int REQUEST_CODE =1;
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            }

            Uri uri = data.getData();
            assert uri != null;
            path = uri.getPath();
            try {
              //  Toast.makeText(this, encodeFileToBase64Binary(path), Toast.LENGTH_SHORT).show();
                encodeFileToBase64Binary(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
           /* String uriString = uri.toString();
            File myFile = new File(uriString);
            String path = myFile.getAbsolutePath();
            String encodedText = null;
            try {
               encodedText = encodeFileToBase64Binary(path);
                Log.e("TAG", "Encoded Text :"+encodedText );
                Log.e("TAG", "Path :"+path);
                Toast.makeText(this, encodedText, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.e("TAG", "Outside Encoded Text :"+encodedText );
            Log.e("TAG", "Outside Path :"+path);*/
        }
    }

    private String encodeFileToBase64Binary(String fileName)
            throws IOException {

        File file = new File(fileName);
        byte[] bytes = loadFile(file);
        String encoded = Base64.encodeToString(bytes,Base64.DEFAULT);
      //  String encodedString = new String(encoded);

        return encoded;
    }
    private static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        byte[] bytes = new byte[(int)length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }

        is.close();
        return bytes;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){

            case R.id.btn_view_packages:
                Intent browsePkgIntent = new Intent(MainActivity.this,BrowserActivity.class);
                browsePkgIntent.putExtra("title","Packages");
                browsePkgIntent.putExtra("url","https://mediaproductionart.com/packages");
                startActivity(browsePkgIntent);
                break;


            case R.id.tv_web_design_home:
                changeFragment(f[1]);
                break;

            case R.id.tv_web_development_home:
                changeFragment(f[2]);
                break;

            case R.id.tv_graphic_design_home:
                changeFragment(f[3]);
                break;

            case R.id.tv_online_marketing_home:
                changeFragment(f[4]);
                break;

            case R.id.tv_social_media_marketing_home:
                changeFragment(f[5]);
                break;

            case R.id.tv_print_media_branding_home:
                changeFragment(f[6]);
                break;

            case R.id.tv_mobile_app_dev_home:
                changeFragment(f[7]);
                break;

            case R.id.tv_domain_and_web_home:
                changeFragment(f[8]);
                break;



            case R.id.img_1_3d_max:
                popupImage(R.drawable.education_g_design_suite);
                break;

            case R.id.img_2_3d_max:
                popupImage(R.drawable.education_w_design);
                break;

            case R.id.img_3_3d_max:
                popupImage(R.drawable.education_av_fx_suite);
                break;

            case R.id.img_4_3d_max:
                popupImage(R.drawable.design_3_max);
                break;




            case R.id.web_portfolio_1:
                popupImage(R.drawable.web_portfolio);
                break;

            case R.id.web_portfolio_2:
                popupImage(R.drawable.web_portfolio_2);
                break;

            case R.id.web_portfolio_3:
                popupImage(R.drawable.web_portfolio_3);
                break;

            case R.id.web_portfolio_4:
                popupImage(R.drawable.web_portfolio_4);
                break;


            case R.id.logo_portfolio_1:
                popupImage(R.drawable.logo_portfolio_1);
                break;

            case R.id.logo_portfolio_2:
                popupImage(R.drawable.logo_portfolio_2);
                break;

            case R.id.logo_portfolio_3:
                popupImage(R.drawable.logo_portfolio_3);
                break;

            case R.id.logo_portfolio_4:
                popupImage(R.drawable.logo_portfolio_4);
                break;


            case R.id.a_logo_portfolio_1:
                popupImage(R.drawable.a_logo_portfolio);
                break;

            case R.id.a_logo_portfolio_2:
                popupImage(R.drawable.a_logo_portfolio_2);
                break;

            case R.id.a_logo_portfolio_3:
                popupImage(R.drawable.a_logo_portfolio_3);
                break;

            case R.id.a_logo_portfolio_4:
                popupImage(R.drawable.a_logo_portfolio_4);
                break;


            case R.id.b_card_portfolio_1:
                popupImage(R.drawable.b_card_portfolio_1);
                break;

            case R.id.b_card_portfolio_2:
                popupImage(R.drawable.b_card_portfolio_2);
                break;

            case R.id.b_card_portfolio_3:
                popupImage(R.drawable.b_card_portfolio_3);
                break;

            case R.id.b_card_portfolio_4:
                popupImage(R.drawable.b_card_portfolio_4);
                break;


            case R.id.p_card_portfolio_1:
                popupImage(R.drawable.p_card_portfolio);
                break;

            case R.id.p_card_portfolio_2:
                popupImage(R.drawable.p_card_portfolio_2);
                break;

            case R.id.p_card_portfolio_3:
                popupImage(R.drawable.p_card_portfolio_3);
                break;

            case R.id.p_card_portfolio_4:
                popupImage(R.drawable.p_card_portfolio_4);
                break;


            case R.id.brouchers_portfolio_1:
                popupImage(R.drawable.broucher_portfolio_1);
                break;

            case R.id.brouchers_portfolio_2:
                popupImage(R.drawable.broucher_portfolio_2);
                break;

            case R.id.brouchers_portfolio_3:
                popupImage(R.drawable.broucher_portfolio_3);
                break;


            case R.id.flyer_portfolio_1:
                popupImage(R.drawable.flyer_portfolio_1);
                break;

            case R.id.flyer_portfolio_2:
                popupImage(R.drawable.flyer_portfolio_2);
                break;

            case R.id.flyer_portfolio_3:
                popupImage(R.drawable.flyer_portfolio_3);
                break;

            case R.id.flyer_portfolio_4:
                popupImage(R.drawable.flyer_portfolio_4);
                break;

            case R.id.portfolio_3d_archicture_1:
                popupImage(R.drawable.portfolio_3d_1);
                break;

            case R.id.portfolio_3d_archicture_2:
                popupImage(R.drawable.portfolio_3d_2);
                break;

            case R.id.portfolio_3d_archicture_3:
                popupImage(R.drawable.portfolio_3d_3);
                break;

            case R.id.portfolio_3d_archicture_4:
                popupImage(R.drawable.portfolio_3d_4);
                break;




            case R.id.btnUploadCV_Carrier:
                Intent i=new Intent();
                i.setType("image/*|application/pdf");
                i.setAction(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(i, "abc"),requestCode);
                break;

            case R.id.btnSubmitCarrier:
                new UploadFileAsync().execute("");
                //Toast.makeText(this, "natho submit kayan cha kandy", Toast.LENGTH_SHORT).show();
                break;




        }



        if (id == R.id.btn_view_more_portfolio_1 || id == R.id.btn_view_more_portfolio_2 || id == R.id.btn_view_more_portfolio_3
                || id == R.id.btn_view_more_portfolio_4 || id == R.id.btn_view_more_portfolio_5 ||
                id == R.id.btn_view_more_portfolio_6 || id == R.id.btn_view_more_portfolio_7 ||
                id == R.id.btn_view_more_portfolio_8){

            Intent browseViewMoreIntent = new Intent(MainActivity.this,BrowserActivity.class);
            browseViewMoreIntent.putExtra("title","Portfolio");
            browseViewMoreIntent.putExtra("url","https://mediaproductionart.com/portfolio");
            startActivity(browseViewMoreIntent);
        }

    }




    @SuppressLint("StaticFieldLeak")
    private class UploadFileAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            try {
                String sourceFileUri = path;

                HttpURLConnection conn = null;
                DataOutputStream dos = null;
                String lineEnd = "\r\n";
                String twoHyphens = "--";
                String boundary = "*****";
                int serverResponseCode = 1;
                int bytesRead, bytesAvailable, bufferSize;
                byte[] buffer;
                int maxBufferSize = 1 * 1024 * 1024;
                File sourceFile = new File(sourceFileUri);

                if (sourceFile.isFile()) {

                    try {
                        String upLoadServerUri = "https://mediaproductionart.000webhostapp.com/upload_file.php/";

                        // open a URL connection to the Servlet
                        FileInputStream fileInputStream = new FileInputStream(
                                sourceFile);
                        URL url = new URL(upLoadServerUri);

                        // Open a HTTP connection to the URL
                        conn = (HttpURLConnection) url.openConnection();
                        conn.setDoInput(true); // Allow Inputs
                        conn.setDoOutput(true); // Allow Outputs
                        conn.setUseCaches(false); // Don't use a Cached Copy
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Connection", "Keep-Alive");
                        conn.setRequestProperty("ENCTYPE",
                                "multipart/form-data");
                        conn.setRequestProperty("Content-Type",
                                "multipart/form-data;boundary=" + boundary);
                        conn.setRequestProperty("bill", sourceFileUri);

                        dos = new DataOutputStream(conn.getOutputStream());

                        dos.writeBytes(twoHyphens + boundary + lineEnd);
                        dos.writeBytes("Content-Disposition: form-data; name=\"bill\";filename=\""
                                + sourceFileUri + "\"" + lineEnd);

                        dos.writeBytes(lineEnd);




                        // Append parameters to URL
                        Uri.Builder builder = new Uri.Builder()
                                .appendQueryParameter("username", params[0])
                                .appendQueryParameter("password", params[1]);
                        String query = builder.build().getEncodedQuery();



                        // Open connection for sending data
                        OutputStream os = conn.getOutputStream();
                        BufferedWriter writer = new BufferedWriter(
                                new OutputStreamWriter(os, "UTF-8"));
                        writer.write(query);
                        writer.flush();
                        writer.close();
                        os.close();
                        conn.connect();


                        // create a buffer of maximum size
                        bytesAvailable = fileInputStream.available();

                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        buffer = new byte[bufferSize];

                        // read file and write it into form...
                        bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                        while (bytesRead > 0) {

                            dos.write(buffer, 0, bufferSize);
                            bytesAvailable = fileInputStream.available();
                            bufferSize = Math
                                    .min(bytesAvailable, maxBufferSize);
                            bytesRead = fileInputStream.read(buffer, 0,
                                    bufferSize);

                        }

                        // send multipart form data necesssary after file
                        // data...
                        dos.writeBytes(lineEnd);
                        dos.writeBytes(twoHyphens + boundary + twoHyphens
                                + lineEnd);



                        // Responses from the server (code and message)
                        serverResponseCode = conn.getResponseCode();
                        String serverResponseMessage = conn
                                .getResponseMessage();

                        if (serverResponseCode == 200) {
                           /*  messageText.setText(msg);
                            Toast.makeText(ctx, "File Upload Complete.",
                                Toast.LENGTH_SHORT).show();
                            recursiveDelete(mDirectory1);*/
                        }

                        // close the streams //
                        fileInputStream.close();
                        dos.flush();
                        dos.close();

                    } catch (Exception e) {

                        // dialog.dismiss();
                        e.printStackTrace();

                    }
                    // dialog.dismiss();

                } // End else block


            } catch (Exception ex) {
                // dialog.dismiss();

                ex.printStackTrace();
            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {

        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }



}


