package com.example.devin.mobiledevicesproject;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.AvoidType;
import com.akexorcist.googledirection.constant.Unit;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.model.Info;
import com.akexorcist.googledirection.model.Leg;
import com.akexorcist.googledirection.model.Route;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Directions extends AppCompatActivity implements PriceListener{

    private LatLng from;
    private LatLng to;

    private double tripDistance = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions);

        //=============== FROM LISTENER ===============================================\\

        PlaceAutocompleteFragment fromAutocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.fromSearch);

        fromAutocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: on place selected things
                from = place.getLatLng();
                String name = place.getName().toString();
            }

            @Override
            public void onError(Status status) {
                // TODO: handle error
            }
        });
        //=============== END FROM LISTENER ===========================================//


        //=============== TO LISTENER =================================================\\
        PlaceAutocompleteFragment toAutocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.toSearch);

        toAutocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: on place selected things
                to = place.getLatLng();
                String name = place.getName().toString();
            }

            @Override
            public void onError(Status status) {
                // TODO: handle error
            }
        });
        //============== END TO LISTENER ==============================================//

        DownloadPriceTask task = new DownloadPriceTask(this);
        task.execute("STUB");
        Log.i("DirectionsCreation", "Getting price");

    }

    @Override
    public void handlePrice(String price) {
        EditText priceField = (EditText)findViewById(R.id.priceOfGas);
        priceField.setText(price);
    }

    public void getDirections(View view){
        GoogleDirection.withServerKey("AIzaSyDzFwlffLplZKK22mxdIqUBj5M_bUSwgZI")
                .from(from)
                .to(to)
                .unit(Unit.METRIC)
                .avoid(AvoidType.FERRIES)
                .avoid(AvoidType.TOLLS)
                .execute(new DirectionCallback(){

                    @Override
                    public void onDirectionSuccess(Direction direction, String rawBody) {
                        if(direction.isOK()){
                            //TODO: Handle okay directions
                            Log.i("Directions", rawBody);
                            Route route = direction.getRouteList().get(0);
                            Leg leg = route.getLegList().get(0);
                            Info distanceInfo = leg.getDistance();
                            Log.i("Distance", distanceInfo.getValue());
                            //tripDistance = Double.parseDouble(distanceInfo.getValue());
                        }
                        else {
                            //TODO: Handle Invalid directions
                        }
                    }

                    @Override
                    public void onDirectionFailure(Throwable t) {

                    }
                });

        float cost = getCostOfTrip();
    }

    public float getCostOfTrip(){
        DBHelper dbHelper = new DBHelper(this);
        Spinner vClass = (Spinner)findViewById(R.id.vClass);
        String sVClass = vClass.getSelectedItem().toString();
        float efficiency = dbHelper.getEfficiency(sVClass);

        EditText price = (EditText)findViewById(R.id.priceOfGas);
        float gasPrice = Float.parseFloat(price.getText().toString());

        Spinner units = (Spinner)findViewById(R.id.units);
        String[] unitArray = getResources().getStringArray(R.array.units);
        if(units.getSelectedItem().toString().equals(unitArray[1])) {
            gasPrice = convertToMetric(gasPrice);
        }

        float costPerKm = (gasPrice * efficiency) / 100;
        return -1f;

    }

    private float convertToMetric(float priceInDollarPerGal) {
        //For simplicity's sake, we're going to assume currency based on region that the user is in.
        return 100*(priceInDollarPerGal*0.264172f);
    }

    class DownloadPriceTask extends AsyncTask<String, Void, String>{

        private String price;
        private PriceListener listener;
        private Exception exception = null;

        public DownloadPriceTask(PriceListener listener) {
            this.listener = listener;
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                Document doc = Jsoup.connect("https://www.gasbuddy.com/")
                        .maxBodySize(0)
                        .timeout(0)
                        .get();
                Elements prices = doc.getElementsByClass("gb-price-lg");
                Element cheapestPriceElement = prices.get(1);
                String cheapestPrice = cheapestPriceElement.html();
                price = cheapestPrice;
                Log.i("Element", cheapestPrice);
                Log.i("Price", price);
            } catch (IOException e) {
                e.printStackTrace();
                price = "0";
            }
            return price;
        }

        protected void onPostExecute(String result) {
            // handle any error
            if (exception != null) {
                exception.printStackTrace();
                return;
            }

            // show the data
            listener.handlePrice(result);
        }
    }
}
