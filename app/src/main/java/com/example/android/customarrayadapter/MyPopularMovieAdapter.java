package com.example.android.customarrayadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyPopularMovieAdapter extends RecyclerView.Adapter<MyPopularMovieAdapter.MyMovieHolder> {

    private static final String TAG = MyPopularMovieAdapter.class.getSimpleName();

    private Context ctx;

    //Define Arraylist of Movie Objects
    private List<PopularMovies> mMoviesData;
    private MyPopularMovieAdapterOnClickHandler myPopularMovieClickHandler;


    public MyPopularMovieAdapter(Context ctx, MyPopularMovieAdapterOnClickHandler myPopularMovieAdapterOnClickHandler) {
        this.ctx = ctx;
        this.myPopularMovieClickHandler = myPopularMovieAdapterOnClickHandler;
        this.mMoviesData = new ArrayList<>();
    }

    public interface MyPopularMovieAdapterOnClickHandler {
        void onClick(PopularMovies myMovie);
    }

    @NonNull
    @Override
    public MyMovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        LayoutInflater myInflater = LayoutInflater.from(ctx);
        View myOwnView = myInflater.inflate(R.layout.movie_image, parent, false);
        return new MyMovieHolder(myOwnView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyMovieHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder:  Call Piscasso lib");

        //Get movie object
        PopularMovies popularMovies = mMoviesData.get(position);

        //Bind Data
        Picasso.get()
                .load(popularMovies.getPosterImage())
                .into(holder.popularMovieImage);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + mMoviesData.size());
        return (mMoviesData != null ?  mMoviesData.size() : 0);
    }

    public class MyMovieHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView popularMovieImage;

        public MyMovieHolder(@NonNull View itemView) {
            super(itemView);

            popularMovieImage = (ImageView) itemView.findViewById(R.id.myImageView);
            //TODO ADD CLICK Listener
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int index = getLayoutPosition();
            PopularMovies myMovie = mMoviesData.get(index);
            myPopularMovieClickHandler.onClick(myMovie);
        }
    }

    public void setmPopularMoviesData(List<PopularMovies> mPopularMoviesData) {
        Log.d(TAG, "onPostExecute:Count of Data passed in.." + mPopularMoviesData.size());
        this.mMoviesData.clear();
        this.mMoviesData.addAll(mPopularMoviesData);
        notifyDataSetChanged();
    }
}
