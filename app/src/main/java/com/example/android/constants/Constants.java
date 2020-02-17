package com.example.android.constants;

public final class Constants {

    /*** Main Discovery Screen, Details View,
    * original title
    * movie poster image thumbnail
    * A plot synopsis (called overview in the api)
    * user rating (called vote_average in the api)
    * release date
    */

    public final static String MOVIE_TITLE = "original_title";
    public final static String IMAGE_URL = "poster_path";
    public final static String POLT_SYNOPSIS = "overview";
    public final static String USER_RATING = "vote_average";
    public final static String RELEASE_DATE = "release_date";
    public final static String POPULARTIY = "popularity";
    public final static String RESULTS = "results";
    public final static String POPULAR_MOVIE    = "PopularMovies";


    //Network constants
    public static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/";
    public static final String IMAGE_SIZE = "w500";
    public static final String MOVIEDB_BASE_URL= "https://api.themoviedb.org/3/discover/movie";
    public static final String format = "json";
    public static final String SORT_BY = "sort_by";
    public final static String RATNGS_PARAM = "vote_average.asc";
    public final static String POPULARITY_PARAM = "popularity.asc";
    public final static String TITLE_PARAM = "original_title.asc";
    public final static String API_KEY = "api_key";
    public final static String API_KEY_VALUE = "";




}
