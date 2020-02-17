package com.example.android.networkUtils;

import android.util.Log;

import com.example.android.constants.Constants;
import com.example.android.customarrayadapter.PopularMovies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParseJsonUtils {

    private static final String TAG = NetworkClientAPI.class.getSimpleName();

    public static List<PopularMovies> parseJsonData(String json){

        try {
            // Assign json object
            JSONObject popularMovieJson = new JSONObject(json);

            //Get Results array from Json
            JSONArray jsonResultsArray = popularMovieJson.optJSONArray(Constants.RESULTS);

            List<PopularMovies> popularMovies = new ArrayList<>();


            if(jsonResultsArray != null){
                for(int i = 0; i < jsonResultsArray.length(); i++){
                    PopularMovies popluarMovie = new PopularMovies();
                    JSONObject movieObj = jsonResultsArray.getJSONObject(i);
                    popluarMovie.setTitle(movieObj.getString(Constants.MOVIE_TITLE));
                    popluarMovie.setPosterImage(Constants.IMAGE_BASE_URL + Constants.IMAGE_SIZE + movieObj.getString(Constants.IMAGE_URL));
                    Log.d(TAG, "parseJsonData: thumb pic of movie.." + popluarMovie.getPosterImage());
                    popluarMovie.setReleaseDate(movieObj.getString(Constants.RELEASE_DATE));
                    popluarMovie.setUserRating(movieObj.getString(Constants.USER_RATING));
                    popluarMovie.setPlotSynopsis(movieObj.getString(Constants.POLT_SYNOPSIS));
                    popularMovies.add(popluarMovie);
                }

            }
            return popularMovies;

        }catch(JSONException jse){
            Log.d(TAG, "Parse Json", jse);
        }
    return null;
    }

}
