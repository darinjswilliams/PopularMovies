package com.example.android.popularmovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.android.constants.Constants;
import com.example.android.customarrayadapter.MyPopularMovieAdapter;
import com.example.android.networkUtils.NetworkClientAPI;
import com.example.android.networkUtils.ParseJsonUtils;
import com.example.android.customarrayadapter.PopularMovies;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyPopularMovieAdapter.MyPopularMovieAdapterOnClickHandler{

    private  static  final String TAG = MainActivity.class.getSimpleName();

    //Initialize adapter
    private MyPopularMovieAdapter mPopularmoviesAdapter;
    private static MyPopularMovieAdapter.MyPopularMovieAdapterOnClickHandler myClickHandler;
    private RecyclerView myRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ToolBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myRecyclerView = (RecyclerView) findViewById(R.id.myRecycler);

        /**
         * SET TO GRIDLAYOUT WITH A SPAN OF 2 COLUMNS
         *
         */

        myRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        mPopularmoviesAdapter = new MyPopularMovieAdapter(this,
                (MyPopularMovieAdapter.MyPopularMovieAdapterOnClickHandler) this);

        myRecyclerView.setAdapter(mPopularmoviesAdapter);

        //runData on backround thread fist to get default data
        loadMovieData(Constants.POPULARITY_PARAM);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sortmenu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        Context context = MainActivity.this;

        switch(item.getItemId()){

            case R.id.action_sortPopular:
                loadMovieData(Constants.POPULARITY_PARAM);
                return true;

            case R.id.action_sortRating:
                loadMovieData(Constants.RATNGS_PARAM);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadMovieData(String sortParam){
        myRecyclerView.setVisibility(View.VISIBLE);

        if(checkInternetConnection(this)) {
            Log.i(TAG, "loadMovieData: Calling Aysnc");
            new FetchPopulaMovie().execute(sortParam);
        }
    }


    private boolean checkInternetConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // test for connection
        if (cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isAvailable()
                && cm.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            Log.v(TAG, "Internet Connection Not Present");
            Toast.makeText(context,"Internet Connection Not Present", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    @Override
    public void onClick(PopularMovies myMovie) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Constants.POPULAR_MOVIE, myMovie);
        startActivity(intent);
    }

    public class FetchPopulaMovie extends AsyncTask<String, Void, List<PopularMovies>> {

       @Override
       protected List<PopularMovies> doInBackground(String... params) {

           Log.i(TAG, "doInBackground: CALL NETWORKING ");

           Context context = MainActivity.this;


           if (params.length == 0) {
               return null;
           }


           String sortParam = params[0];

           //BUILD URL
           URL popularMovieRequestUrl = NetworkClientAPI.buildUrl(sortParam);

           try {

               String popularMovieResponseData = NetworkClientAPI.getResponseFromHttpUrl(popularMovieRequestUrl);

               //Guard against null value
               if (popularMovieResponseData != null && !popularMovieResponseData.equals("")) {
                   // Parse Json Data than set it on the Adapter
                   return ParseJsonUtils.parseJsonData(popularMovieResponseData);

               }

           } catch (Exception e) {
               e.printStackTrace();
               return null;
           }
                return null;
       }

       @Override
       protected void onPostExecute(List<PopularMovies> popularMovies) {

           mPopularmoviesAdapter.setmPopularMoviesData(popularMovies);
       }

   }
}
