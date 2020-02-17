package com.example.android.networkUtils;


import android.net.Uri;
import android.util.Log;

import com.example.android.constants.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


/**
 * These utilities will be used to communicate with the weather servers.
 */
public final class NetworkClientAPI {

    private static final String TAG = NetworkClientAPI.class.getSimpleName();


    /**
     * Builds the URL used to talk to the weather server using a location. This location is based
     * on the query capabilities of the weather provider that we are using.
     *
     * @param sortByQuery The sort by popular, or rating that will be queried for.
     * @return The URL to use to query the weather server.
     */
    public static URL buildUrl(String sortByQuery) {
        Log.i(TAG, "buildUrl: ");
        Uri builtUri = Uri.parse(Constants.MOVIEDB_BASE_URL).buildUpon()
                .appendQueryParameter(Constants.API_KEY, Constants.API_KEY_VALUE)
                .appendQueryParameter(Constants.SORT_BY, sortByQuery)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }

    /**
     * Builds the URL used to talk to the weather server using latitude and longitude of a
     * location.
     *
     * @param lat The latitude of the location
     * @param lon The longitude of the location
     * @return The Url to use to query the weather server.
     */
    public static URL buildUrl(Double lat, Double lon) {
        /** This will be implemented in a future lesson **/
        return null;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }


}