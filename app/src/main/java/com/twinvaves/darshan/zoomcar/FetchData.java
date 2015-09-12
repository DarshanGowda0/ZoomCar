package com.twinvaves.darshan.zoomcar;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class FetchData extends AsyncTask<String, Void, Boolean> {

    Context context;

    FetchData(Context con) {
        context = con;
    }
    ProgressDialog progressDialog;


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
//        MainActivity.progressLayout.setVisibility(View.VISIBLE);

        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMessage("Loading....");
        progressDialog.setIndeterminate(true);
        progressDialog.show();

    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        MainActivity.myAdapter.notifyDataSetChanged();
        progressDialog.dismiss();
//        MainActivity.progressLayout.setVisibility(View.GONE);
    }

    public static ArrayList<Data> list = new ArrayList<>();

    @Override
    protected Boolean doInBackground(String... params) {
        //get the url of zoomcar api

        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            int responseCode = urlConnection.getResponseCode();

            if (responseCode == 200) {
                String responseString = readStream(urlConnection.getInputStream());
                Log.v("CatalogClient", responseString);
                parseJson(responseString);
            } else {
                Log.v("CatalogClient", "Response code:" + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }


        return false;
    }


    public static String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }


    void parseJson(String data) {
        try {
            JSONObject carObject;
            JSONObject mainObject = new JSONObject(data);
            JSONArray jsonArray = mainObject.getJSONArray("cars");
            for (int i = 0; i < jsonArray.length(); i++) {
                carObject = jsonArray.getJSONObject(i);
                Data da = new Data();
                da.name = carObject.getString("name");
                da.image = carObject.getString("image");
                da.type = carObject.getString("type");
                da.rate_hour = carObject.getString("hourly_rate");
                da.rating = carObject.getString("rating");
                da.seater = carObject.getString("seater");
                da.ac = carObject.getString("ac");
                da.lat = carObject.getJSONObject("location").getString("latitude");
                da.lon = carObject.getJSONObject("location").getString("longitude");
                list.add(da);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
