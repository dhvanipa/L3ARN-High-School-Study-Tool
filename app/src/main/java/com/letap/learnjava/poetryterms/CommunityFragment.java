package com.letap.learnjava.poetryterms;

/**
 * Created by Dhvani on 31/10/2015.
 */

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class CommunityFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {



    public CommunityFragment() {}
    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();

    // Movies json url
    private static final String url = "https://sites.google.com/site/letapencrypt/home/files/social.json";
    // private ProgressDialog pDialog;
    private List<Movie> movieList = new ArrayList<Movie>();
    private ListView listView;
  //  private CustomListAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private DatabaseHandler db;
    private boolean newItem = false;
    private boolean contains = false;
    private int previousSize;
    String path;
    public static String[] names_list;
    String[] myImageList;
    String[] desc_final;
    // Listview Adapter
    ArrayAdapter<String> adapter;

    ArrayList<String> names_list_arr;
    ArrayList<String> desc;
    ArrayList<String> img;

    // Search EditText
    EditText inputSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        View rootView = inflater.inflate(R.layout.fragment_community, container, false);

       // names_list = new String[1];

        db = new DatabaseHandler(getActivity());

        previousSize = db.getAllContacts(DatabaseHandler.TABLE_SOCIAL).size();

        final ListView provincelist = (ListView) rootView.findViewById(R.id.lvProvinceNames);
        inputSearch = (EditText) rootView.findViewById(R.id.inputSearch);

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);

                                        fetchMovies();


                                    }
                                }
        );

        //add header to listview
        //LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.activity_listheader, provincelist, false);
        ImageView header2 = (ImageView)header.findViewById(R.id.imageHeader);
        header2.setImageResource(R.drawable.headers);

        provincelist.addHeaderView(header, null, false);
        provincelist.setTextFilterEnabled(true);

        movieList = db.getAllContacts(DatabaseHandler.TABLE_SOCIAL);
        names_list_arr = new ArrayList<String>();
        desc = new ArrayList<String>();
        img = new ArrayList<String>();

            System.out.println("MOVIE LIST SIZE: " + movieList.size());

        for(int i = 0; i < movieList.size(); i++){
            Movie m = movieList.get(i);

            names_list_arr.add(m.getTitle());
            desc.add(m.getDesc());
            img.add(m.getThumbnailUrl());
        }

        names_list = convertStrings(names_list_arr);
        desc_final = convertStrings(desc);
        myImageList = convertStrings(img);


       adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, names_list);
       // adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names_list);
        // adapter = new CustomListAdapter(getActivity(), movieList);
        provincelist.setAdapter(adapter);




      //  listView.setAdapter(adapter);


        /**
         * Enabling Search Filter
         * */
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                CommunityFragment.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub

            }
        });
        provincelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                //we use the items of the listview as title of the next activity

                String str=provincelist.getItemAtPosition(position).toString();
                int fIndex = Arrays.asList(names_list).indexOf(str);

                String province = names_list[fIndex];




                final String prvImg = myImageList[fIndex];

                //we retrieve the description of the juices from an array defined in arrays.xml
                //String[] provincedescription = getResources().getStringArray(R.array.social);
                final String provincedesclabel = desc_final[fIndex];

                //retrieve content for the dialog
                String[] dialogmessage = getResources().getStringArray(R.array.socialM);
                final String dialogmsg = dialogmessage[fIndex];


                Intent intent = new Intent(getActivity().getApplicationContext(), detailActivity.class);
                intent.putExtra("province", province);
                intent.putExtra("provincedesclabel", provincedesclabel);
                intent.putExtra("prvImg", prvImg);
                intent.putExtra("dialogmsg", dialogmsg);

                startActivity(intent);


            }


        });
        return rootView;
    }

    public static String[] convertStrings(List<String> integers)
    {
        String[] ret = new String[integers.size()];
        Iterator<String> iterator = integers.iterator();
        for (int i = 0; i < ret.length; i++)
        {
            ret[i] = iterator.next().toString();
        }
        return ret;
    }



    private void fetchMovies(){
        // showing refresh animation before making http call
        swipeRefreshLayout.setRefreshing(true);

        // Creating volley request obj
        // pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        // pDialog.setMessage("Loading...");
        // pDialog.show();
        HttpURLConnection.setFollowRedirects(true);
        JsonArrayRequest movieReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        //    hidePDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Movie movie = new Movie();
                                movie.setTitle(obj.getString("title"));
                                movie.setThumbnailUrl(obj.getString("image"));
                                movie.setDesc(obj.getString("desc"));



                                // movie.setRating(((Number) obj.get("rating"))
                                //    .doubleValue());
                                //  movie.setYear(obj.getInt("releaseYear"));

                                // Genre is json array
                           /* JSONArray genreArry = obj.getJSONArray("genre");
                            ArrayList<String> genre = new ArrayList<String>();
                            for (int j = 0; j < genreArry.length(); j++) {
                                genre.add((String) genreArry.get(j));
                            }*/
                                //movie.setGenre(genre);

                                // adding movie to movies array
                                System.out.println(movie.getThumbnailUrl());
                                if(movieList.size() <= 0){
                                    newItem = true;
                                    new AsyncCaller(movie.getTitle(), movie.getThumbnailUrl()).execute().get();
                                    movie.setThumbnailUrl(path);
                                    System.out.println(path);
                                    db.addContact(movie, DatabaseHandler.TABLE_SOCIAL);
                                }
                                else {
                                    for (int j = 0; j < movieList.size(); j++) {
                                        String title = movieList.get(j).getTitle();
                                        String image = movieList.get(j).getThumbnailUrl();
                                        String desc = movieList.get(j).getDesc();


                                        if (title.equals(movie.getTitle())) {
                                            contains = true;
                                            System.out.print("H");
                                            break;
                                        } else {
                                            contains = false;
                                        }
                                    }
                                    if(contains == false) {
                                        newItem = true;
                                        new AsyncCaller(movie.getTitle(), movie.getThumbnailUrl()).execute();
                                        movie.setThumbnailUrl(path);
                                        db.addContact(movie, DatabaseHandler.TABLE_SOCIAL);
                                    }
                                }


/*
                            for(int j = 0; j < db.getAllContacts().size(); j++){

                                Movie compare = db.getContact(j);
                                if(compare.getTitle().equals(movie.getTitle())){
                                    db.updateContact(movie);
                                }

                            }*/



                                // movieList.add(movie);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }

                        }

                        if(newItem == true){

                            if(movieList.size() <= 0){
                                for(int i = 0; i < db.getAllContacts(DatabaseHandler.TABLE_SOCIAL).size(); i++){
                                    String title = db.getAllContacts(DatabaseHandler.TABLE_SOCIAL).get(i).getTitle();
                                    String image = db.getAllContacts(DatabaseHandler.TABLE_SOCIAL).get(i).getThumbnailUrl();
                                    String desc = db.getAllContacts(DatabaseHandler.TABLE_SOCIAL).get(i).getDesc();






                                    movieList.add(new Movie(title, image, desc));
                                }
                                newItem = false;
                            }
                            else{

                                for(int i = previousSize; i < db.getAllContacts(DatabaseHandler.TABLE_SOCIAL).size(); i++){
                                    String title = db.getAllContacts(DatabaseHandler.TABLE_SOCIAL).get(i).getTitle();
                                    String image = db.getAllContacts(DatabaseHandler.TABLE_SOCIAL).get(i).getThumbnailUrl();
                                    String desc = db.getAllContacts(DatabaseHandler.TABLE_SOCIAL).get(i).getDesc();


                                    movieList.add(new Movie(title, image, desc));
                                }
                                newItem = false;

                            }


                        }

                        previousSize = db.getAllContacts(DatabaseHandler.TABLE_SOCIAL).size();
                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                        // stopping swipe refresh
                        swipeRefreshLayout.setRefreshing(false);
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                //hidePDialog();
                // stopping swipe refresh
                swipeRefreshLayout.setRefreshing(false);
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
            System.out.println("here");
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




    /**
     * This method is called when swipe refresh is pulled down
     */
    @Override
    public void onRefresh() {


        fetchMovies();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //hidePDialog();
    }

}