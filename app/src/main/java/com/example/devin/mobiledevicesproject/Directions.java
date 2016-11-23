package com.example.devin.mobiledevicesproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.AvoidType;
import com.akexorcist.googledirection.model.Direction;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Directions extends AppCompatActivity {

    private LatLng from;
    private LatLng to;

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
    }

    public void getDirections(View view){
        GoogleDirection.withServerKey("AIzaSyDzFwlffLplZKK22mxdIqUBj5M_bUSwgZI")
                .from(from)
                .to(to)
                .avoid(AvoidType.FERRIES)
                .avoid(AvoidType.TOLLS)
                .execute(new DirectionCallback(){

                    @Override
                    public void onDirectionSuccess(Direction direction, String rawBody) {
                        if(direction.isOK()){
                            //TODO: Handle okay directions
                            Log.i("Directions", rawBody);
                        }
                        else {
                            //TODO: Handle Invalid directions
                        }
                    }

                    @Override
                    public void onDirectionFailure(Throwable t) {

                    }
                });
    }
}
