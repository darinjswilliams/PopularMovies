package com.example.android.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.constants.Constants;
import com.example.android.customarrayadapter.PopularMovies;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;


public class DetailActivity extends AppCompatActivity {

    private  static  final String TAG = DetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Set image
        ImageView imageOfMovie = findViewById(R.id.imageDetail);
        Toolbar toolbar = findViewById(R.id.toolbar);

        //Setup tool bar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        Intent intent = getIntent();
        PopularMovies popularMovie = intent.getParcelableExtra(Constants.POPULAR_MOVIE);

        //Get data from Parcelable Object
        String movieTitle = popularMovie.getTitle();
        String moviePosterUrl = popularMovie.getPosterImage();
        String moviePlot = popularMovie.getPlotSynopsis();
        String movieReleaseDate = popularMovie.getReleaseDate();
        String movieRating = popularMovie.getUserRating();


        int imageResourceId = getResources().getIdentifier(moviePosterUrl, "drawable", getPackageName());
        imageOfMovie.setImageResource(imageResourceId);


        Picasso.get()
                .load(popularMovie.getPosterImage())
                .into(imageOfMovie);

        TextView titleTextView = findViewById(R.id.titleDetail);
        titleTextView.setText(movieTitle);

        TextView releaseDateTextView = findViewById(R.id.relDateDetail);
        releaseDateTextView.setText(movieReleaseDate);

        TextView plotTextView = findViewById(R.id.plotDetail);
        plotTextView.setText(moviePlot);

        TextView ratingTextView = findViewById(R.id.ratingDetail);
        ratingTextView.setText(movieRating);

    }
}
