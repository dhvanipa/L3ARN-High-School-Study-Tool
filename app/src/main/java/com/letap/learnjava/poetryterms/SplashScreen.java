package com.letap.learnjava.poetryterms;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by dhvani on 2016-06-21.
 */
public class SplashScreen extends Activity {

    // Tag for identifying class
    private static final String TAG = MainActivity.class.getSimpleName();

    // Section urls to grab the json with all the data
    private static final String url_social = "https://l3arn-92fe6.firebaseapp.com/social.json";
    private static final String url_math = "https://l3arn-92fe6.firebaseapp.com/math.json";
    private static final String url_poet = "https://l3arn-92fe6.firebaseapp.com/poet.json";
    private static final String url_bio = "https://l3arn-92fe6.firebaseapp.com/bio.json";
    private static final String url_chem = "https://l3arn-92fe6.firebaseapp.com/chem.json";
    private static final String url_phy = "https://l3arn-92fe6.firebaseapp.com/phy.json";
    private static final String url_geo = "https://l3arn-92fe6.firebaseapp.com/geo.json";
    private static final String url_compsci = "https://l3arn-92fe6.firebaseapp.com/compsci.json";

   // Other variables
    int numDone = 0;
    private DatabaseHandler db;
    String path;
    private PrefManager prefManager;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        prefManager = new PrefManager(this);
        launchHomeScreen();

        /*
        // Checking for first time launch

        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        // Following code will execute if not the first time

        db = new DatabaseHandler(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading social content for the first time...");
        fetchSocial();


        //new PrefetchSocialData(this).execute();
        */

    }

    /**
     * Async Task to make http call
     */
    private class PrefetchSocialData extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        Activity activity;

        public PrefetchSocialData(Activity activity){
            this.activity = activity;
            dialog = new ProgressDialog(this.activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // before making http calls
            pDialog.setMessage("Loading Social...");
           // dialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            /*
             * Will make http call here This call will download required data
             * before launching the app
             * example:
             * 1. Downloading and storing in SQLite
             * 2. Downloading images
             * 3. Fetching and parsing the xml / json
             * 4. Sending device information to server
             * 5. etc.,
             */
            System.out.println("OK HERE");
           fetchSocial();

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if(dialog.isShowing()){
                dialog.dismiss();
            }
            System.out.println("YAY DONE");
            // new PrefetchMathData(activity).execute();
        }

    }

    /**
     * Async Task to make http call
     */
    private class PrefetchMathData extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        Activity activity;

        public PrefetchMathData(Activity activity){
            this.activity = activity;
            dialog = new ProgressDialog(this.activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // before making http calls
            dialog.setMessage("Loading Math...");
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            /*
             * Will make http call here This call will download required data
             * before launching the app
             * example:
             * 1. Downloading and storing in SQLite
             * 2. Downloading images
             * 3. Fetching and parsing the xml / json
             * 4. Sending device information to server
             * 5. etc.,
             */
            fetchMath();

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if(dialog.isShowing()){
                dialog.dismiss();
            }
            new PrefetchPoetData(activity).execute();
        }

    }

    /**
     * Async Task to make http call
     */
    private class PrefetchPoetData extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        Activity activity;

        public PrefetchPoetData(Activity activity){
            this.activity = activity;
            dialog = new ProgressDialog(this.activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // before making http calls
            dialog.setMessage("Loading Language Arts...");
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            /*
             * Will make http call here This call will download required data
             * before launching the app
             * example:
             * 1. Downloading and storing in SQLite
             * 2. Downloading images
             * 3. Fetching and parsing the xml / json
             * 4. Sending device information to server
             * 5. etc.,
             */
            fetchPoet();

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if(dialog.isShowing()){
                dialog.dismiss();
            }

            new PrefetchPhyData(activity).execute();
        }

    }

    /**
     * Async Task to make http call
     */
    private class PrefetchPhyData extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        Activity activity;

        public PrefetchPhyData(Activity activity){
            this.activity = activity;
            dialog = new ProgressDialog(this.activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // before making http calls
            dialog.setMessage("Loading Physics...");
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            /*
             * Will make http call here This call will download required data
             * before launching the app
             * example:
             * 1. Downloading and storing in SQLite
             * 2. Downloading images
             * 3. Fetching and parsing the xml / json
             * 4. Sending device information to server
             * 5. etc.,
             */
            fetchPhy();

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if(dialog.isShowing()){
                dialog.dismiss();
            }

            new PrefetchChemData(activity).execute();

        }

    }

    /**
     * Async Task to make http call
     */
    private class PrefetchChemData extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        Activity activity;

        public PrefetchChemData(Activity activity){
            this.activity = activity;
            dialog = new ProgressDialog(this.activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // before making http calls
            dialog.setMessage("Loading Chemistry...");
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            /*
             * Will make http call here This call will download required data
             * before launching the app
             * example:
             * 1. Downloading and storing in SQLite
             * 2. Downloading images
             * 3. Fetching and parsing the xml / json
             * 4. Sending device information to server
             * 5. etc.,
             */
            fetchChem();

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if(dialog.isShowing()){
                dialog.dismiss();
            }
            new PrefetchBioData(activity).execute();

        }

    }

    /**
     * Async Task to make http call
     */
    private class PrefetchBioData extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        Activity activity;

        public PrefetchBioData(Activity activity){
            this.activity = activity;
            dialog  = new ProgressDialog(this.activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // before making http calls
            dialog.setMessage("Loading Biology...");
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            /*
             * Will make http call here This call will download required data
             * before launching the app
             * example:
             * 1. Downloading and storing in SQLite
             * 2. Downloading images
             * 3. Fetching and parsing the xml / json
             * 4. Sending device information to server
             * 5. etc.,
             */
            fetchBio();

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if(dialog.isShowing()){
                dialog.dismiss();
            }
            new PrefetchGeoData(activity).execute();

        }

    }

    /**
     * Async Task to make http call
     */
    private class PrefetchGeoData extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        Activity activity;

        public PrefetchGeoData(Activity activity){
            this.activity = activity;
            dialog = new ProgressDialog(this.activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // before making http calls
            dialog.setMessage("Loading Geology...");
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            /*
             * Will make http call here This call will download required data
             * before launching the app
             * example:
             * 1. Downloading and storing in SQLite
             * 2. Downloading images
             * 3. Fetching and parsing the xml / json
             * 4. Sending device information to server
             * 5. etc.,
             */
            fetchGeo();

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if(dialog.isShowing()){
                dialog.dismiss();
            }

            new PrefetchCompsciData(activity).execute();

        }

    }

    /**
     * Async Task to make http call
     */
    private class PrefetchCompsciData extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        Activity activity;

        public PrefetchCompsciData(Activity activity){
            this.activity = activity;
            dialog = new ProgressDialog(this.activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // before making http calls
            dialog.setMessage("Loading Computer Science...");
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            /*
             * Will make http call here This call will download required data
             * before launching the app
             * example:
             * 1. Downloading and storing in SQLite
             * 2. Downloading images
             * 3. Fetching and parsing the xml / json
             * 4. Sending device information to server
             * 5. etc.,
             */
            fetchCompsci();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if(dialog.isShowing()){
                dialog.dismiss();
            }

            Intent i = new Intent(SplashScreen.this, WelcomeActivity.class);
            startActivity(i);

        }

    }











    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(SplashScreen.this, WelcomeActivity.class));
        finish();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }


    private void fetchSocial(){

        pDialog.show();
        // Creating volley request obj
       // pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request


    // pDialog.setMessage("Loading social...");
    //    if(!pDialog.isShowing()){
      //      pDialog.show();
       // }
        System.out.println("social");
        // HttpURLConnection.setFollowRedirects(true);
        JsonArrayRequest movieReq = new JsonArrayRequest(url_social,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println("bruhh");
                        Log.d(TAG, response.toString());
                       // hidePDialog();
                        System.out.println("bruh");
                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Movie movie = new Movie();
                                movie.setTitle(obj.getString("title"));
                                movie.setThumbnailUrl(obj.getString("image"));
                                movie.setDesc(obj.getString("desc"));

                                System.out.println("before" + path);
                                new AsyncCaller(movie.getTitle(), movie.getThumbnailUrl()).execute().get();
                                System.out.println("after" + path);
                                movie.setThumbnailUrl(path);
                                db.addContact(movie, DatabaseHandler.TABLE_SOCIAL);


                                /*
                                numDone++;
                                if(numDone == 8){
                                    Intent start = new Intent(SplashScreen.this, WelcomeActivity.class);
                                    startActivity(start);
                                   // hidePDialog();
                                }
                                */

                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }

                        }

                        pDialog.setMessage("Now fetching Math...");
                        fetchMath();

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("rad");
                System.out.println(error.getLocalizedMessage());
                System.out.println(error.getCause());
                System.out.println(error.getStackTrace());
                VolleyLog.d(TAG, "Error: " + error.getMessage());
               // hidePDialog();
            }



        });
        System.out.println("lets gooo");
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);


    }

    private void fetchMath(){

        // Creating volley request obj
       // pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
       // pDialog.setMessage("Loading math...");
       // if(!pDialog.isShowing()){
       //     pDialog.show();
      //  }
        System.out.println("math");
        HttpURLConnection.setFollowRedirects(true);
        JsonArrayRequest movieReq = new JsonArrayRequest(url_math,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        //hidePDialog();
                        System.out.println("maths");
                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Movie movie = new Movie();
                                movie.setTitle(obj.getString("title"));
                                movie.setThumbnailUrl(obj.getString("image"));
                                movie.setDesc(obj.getString("desc"));

                                new AsyncCaller(movie.getTitle(), movie.getThumbnailUrl()).execute().get();
                                movie.setThumbnailUrl(path);
                                db.addContact(movie, DatabaseHandler.TABLE_MATH);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }

                        }
                        pDialog.setMessage("Now fetching language arts...");
                        fetchPoet();


                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("rad2");
                System.out.println(error.getLocalizedMessage());
                System.out.println(error.getCause());
                System.out.println(error.getStackTrace());
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);


    }

    private void fetchPoet(){

        // Creating volley request obj
       // pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
      // pDialog.setMessage("Loading language arts...");
      //  if(!pDialog.isShowing()){
      //      pDialog.show();
       // }
        HttpURLConnection.setFollowRedirects(true);
        JsonArrayRequest movieReq = new JsonArrayRequest(url_poet,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        //hidePDialog();
                        // Parsing json
                        System.out.println("poet");
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Movie movie = new Movie();
                                movie.setTitle(obj.getString("title"));
                                movie.setThumbnailUrl(obj.getString("image"));
                                movie.setDesc(obj.getString("desc"));

                                new AsyncCaller(movie.getTitle(), movie.getThumbnailUrl()).execute().get();
                                movie.setThumbnailUrl(path);
                                db.addContact(movie, DatabaseHandler.TABLE_POET);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }

                        }
                        pDialog.setMessage("Now fetching physics...");
                        fetchPhy();

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);


    }

    private void fetchPhy(){

        // Creating volley request obj
       // pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
       //pDialog.setMessage("Loading Physics...");
      //  if(!pDialog.isShowing()){
      //      pDialog.show();
     //   }

        HttpURLConnection.setFollowRedirects(true);
        JsonArrayRequest movieReq = new JsonArrayRequest(url_phy,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                       // hidePDialog();
                        // Parsing json
                        System.out.println("physics");
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Movie movie = new Movie();
                                movie.setTitle(obj.getString("title"));
                                movie.setThumbnailUrl(obj.getString("image"));
                                movie.setDesc(obj.getString("desc"));

                                new AsyncCaller(movie.getTitle(), movie.getThumbnailUrl()).execute().get();
                                movie.setThumbnailUrl(path);
                                db.addContact(movie, DatabaseHandler.TABLE_PHY);



                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }

                        }
                        pDialog.setMessage("Now fetching biology...");
                        fetchBio();


                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);


    }

    private void fetchBio(){

        // Creating volley request obj
        //pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
       // pDialog.setMessage("Loading Biology...");
       // if(!pDialog.isShowing()){
       //     pDialog.show();
      //  }
        HttpURLConnection.setFollowRedirects(true);
        JsonArrayRequest movieReq = new JsonArrayRequest(url_bio,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                     //   hidePDialog();
                        System.out.println("bio");
                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Movie movie = new Movie();
                                movie.setTitle(obj.getString("title"));
                                movie.setThumbnailUrl(obj.getString("image"));
                                movie.setDesc(obj.getString("desc"));

                                new AsyncCaller(movie.getTitle(), movie.getThumbnailUrl()).execute().get();
                                movie.setThumbnailUrl(path);
                                db.addContact(movie, DatabaseHandler.TABLE_BIO);




                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }

                        }

                        pDialog.setMessage("Now fetching chemistry...");
                        fetchChem();

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);


    }

    private void fetchChem(){

        // Creating volley request obj
      //  pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
      // pDialog.setMessage("Loading Chemistry...");
      //  if(!pDialog.isShowing()){
      //      pDialog.show();
      //  }
        HttpURLConnection.setFollowRedirects(true);
        JsonArrayRequest movieReq = new JsonArrayRequest(url_chem,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                       // hidePDialog();
                        // Parsing json
                        System.out.println("chem");
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Movie movie = new Movie();
                                movie.setTitle(obj.getString("title"));
                                movie.setThumbnailUrl(obj.getString("image"));
                                movie.setDesc(obj.getString("desc"));

                                new AsyncCaller(movie.getTitle(), movie.getThumbnailUrl()).execute().get();
                                movie.setThumbnailUrl(path);
                                db.addContact(movie, DatabaseHandler.TABLE_CHEM);




                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }

                        }
                        pDialog.setMessage("Now fetching geology...");
                        fetchGeo();

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);


    }

    private void fetchGeo(){

        // Creating volley request obj
        //pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
      //  pDialog.setMessage("Loading Geology...");

     //   if(!pDialog.isShowing()){
     //       pDialog.show();
     //   }
        HttpURLConnection.setFollowRedirects(true);
        JsonArrayRequest movieReq = new JsonArrayRequest(url_geo,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                      //  hidePDialog();
                        // Parsing json
                        System.out.println("geo");
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Movie movie = new Movie();
                                movie.setTitle(obj.getString("title"));
                                movie.setThumbnailUrl(obj.getString("image"));
                                movie.setDesc(obj.getString("desc"));

                                new AsyncCaller(movie.getTitle(), movie.getThumbnailUrl()).execute().get();
                                movie.setThumbnailUrl(path);
                                db.addContact(movie, DatabaseHandler.TABLE_GEO);




                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }

                        }

                        pDialog.setMessage("Now fetching computer science...");
                        fetchCompsci();

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
              //  hidePDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);


    }

    private void fetchCompsci(){

        // Creating volley request obj
      //  pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
     //   pDialog.setMessage("Loading Computer Science...");
     //   if(!pDialog.isShowing()){
      //      pDialog.show();
        //}
        HttpURLConnection.setFollowRedirects(true);
        JsonArrayRequest movieReq = new JsonArrayRequest(url_compsci,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                       // hidePDialog();

                        System.out.println("compsci");
                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Movie movie = new Movie();
                                movie.setTitle(obj.getString("title"));
                                movie.setThumbnailUrl(obj.getString("image"));
                                movie.setDesc(obj.getString("desc"));

                                System.out.println("before" + path);
                                new AsyncCaller(movie.getTitle(), movie.getThumbnailUrl()).execute().get();
                                System.out.println("after" + path);
                                movie.setThumbnailUrl(path);
                                db.addContact(movie, DatabaseHandler.TABLE_COMPSCI);



                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }

                        }

                        pDialog.setMessage("Done fetcing! Loading application :)");
                        pDialog.dismiss();
                        Intent i = new Intent(SplashScreen.this, WelcomeActivity.class);
                        startActivity(i);

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);



    }

    private class AsyncCaller extends AsyncTask<Void, Void, Void>
    {
        //ProgressDialog pdLoading = new ProgressDialog(MainActivity.this);

        String mTitle;
        String mUrl;

        public AsyncCaller(String title, String url) {
            super();
            this.mTitle = title;
            this.mUrl = url;

            // do stuff
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            // pdLoading.setMessage("\tLoading...");
            //  pdLoading.show();
        }
        @Override
        protected Void doInBackground(Void... params) {

            //this method will be running on background thread so don't update UI frome here
            //do your long running http tasks here,you dont want to pass argument and u can access the parent class' variable url over here

            Bitmap bit = getBitmapFromURL(mUrl);
            if(bit == null){
                System.out.print("NULL");
            }
            path = SaveImage(bit);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            //this method will be running on UI thread

            //pdLoading.dismiss();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }


    private String SaveImage(Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().getAbsolutePath();
        File myDir = new File(root + "/saved_images");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-"+ n +".jpg";
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return file.getAbsolutePath();
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception

            return null;
        }
    }

}
