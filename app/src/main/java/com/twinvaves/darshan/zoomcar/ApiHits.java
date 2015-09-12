package com.twinvaves.darshan.zoomcar;

import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by darshan on 12/09/15.
 */
public class ApiHits extends AsyncTask<String, Void, String> {

    View view;

    public ApiHits(View view) {
        this.view = view;
    }

    @Override
    protected String doInBackground(String... params) {

        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            int responseCode = urlConnection.getResponseCode();

            if (responseCode == 200) {
                String responseString = FetchData.readStream(urlConnection.getInputStream());
                Log.v("CatalogClient", responseString);
                String hits = parseJson(responseString);
                return hits;
            } else {
                Log.v("CatalogClient", "Response code:" + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }


        return "Error in connecting to web api ";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Snackbar.make(view,"CARS: "+FetchData.list.size()+ " | API hits : "+s, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Snackbar.make(view, "Loading API hits", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    String parseJson(String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            String hits = jsonObject.getString("api_hits");
            return hits;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "Error in accessing api hits";
    }
}
