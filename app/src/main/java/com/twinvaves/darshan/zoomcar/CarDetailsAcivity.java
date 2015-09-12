package com.twinvaves.darshan.zoomcar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.Calendar;

public class CarDetailsAcivity extends AppCompatActivity {

    TextView Title, rate, seaterTv, acTv;
    ImageView imageView;
    RatingBar ratingBar;
    Intent intent;
    String name, rating, amount, seats, ac, imageUrl, lat, lon, type;
    ImageLoader imageLoader = ImageLoader.getInstance();
    FloatingActionButton fab;
    CardView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details_acivity);
        init();
        fab = (FloatingActionButton) findViewById(R.id.fabTick);
        final Calendar c = Calendar.getInstance();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                new DatePickerDialog(CarDetailsAcivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                Snackbar.make(v, "Booked the car succesfully for " + dayOfMonth + " - " + monthOfYear + " - " + year
                                        , Snackbar.LENGTH_SHORT)
                                        .setAction("action", null).show();
                            }
                        }
                        , c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE))
                        .show();

            }
        });


/*
        new DatePickerDialog(getApplicationContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Snackbar.make(v,"Booked the car succesfully for "+dayOfMonth+":"+monthOfYear+":"+year,Snackbar.LENGTH_SHORT)
                        .setAction("action",null).show();
            }
        }*/

    }

   /* DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            Snackbar.make(v,"Booked the car succesfully for "+arg3+":"+arg2+":"+arg1,Snackbar.LENGTH_SHORT)
                    .setAction("action",null).show();
        }
    };*/

    private void init() {
        Title = (TextView) findViewById(R.id.carTitle);
        rate = (TextView) findViewById(R.id.money);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        seaterTv = (TextView) findViewById(R.id.seaterTv);
        acTv = (TextView) findViewById(R.id.acTv);
        imageView = (ImageView) findViewById(R.id.carImageBig);
        intent = getIntent();
        name = intent.getStringExtra("NAME");
        rating =
                intent.getStringExtra("RATING");
        amount =
                intent.getStringExtra("RATE");
        seats =
                intent.getStringExtra("SEATS");
        ac =
                intent.getStringExtra("AC");
        imageUrl =
                intent.getStringExtra("IMAGE");
        lat =
                intent.getStringExtra("LAT");
        lon =
                intent.getStringExtra("LON");
        type =
                intent.getStringExtra("TYPE");

        mapView = (CardView) findViewById(R.id.cardView);

        Title.setText(name);
        rate.setText("RS : " + amount + " per hour");
        ratingBar.setRating(Float.parseFloat(rating));
        ratingBar.setEnabled(false);
        seaterTv.setText("Seats: " + seats + "\nClass : " + type);
        if (ac.equals("1")) {
            acTv.setText("YES AVAILABLE");
        } else
            acTv.setText("NOT AVAILABLE");
        imageLoader.displayImage(imageUrl, imageView, MainActivity.defaultOptions);

        mapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri location = Uri.parse("geo:"+ lat+","+ lon+"?z=14");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
                startActivity(mapIntent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_car_details_acivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
