package akanshaapp.com.geu.akanshaapp.akanshaapp;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;




        LatLng dehradun = new LatLng(30.33,70.00);
        mMap.addMarker(new MarkerOptions().position(dehradun).title("My location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(dehradun));



        if(TrainInfoFragment.ti!=null)
        {
            List<Route> routelist=TrainInfoFragment.ti.getRoute();

            for(int i=0;i<routelist.size();i++)
            {
                if(i==0) {
                    mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(routelist.get(i).getLat()), Double.parseDouble(routelist.get(i).getLng()))).title(routelist.get(i).getFullname()).snippet(routelist.get(i).getState()));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(routelist.get(i).getLat()), Double.parseDouble(routelist.get(i).getLng())),15));
                }
                else
                {
                    mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(routelist.get(i).getLat()), Double.parseDouble(routelist.get(i).getLng()))).title(routelist.get(i).getFullname()).snippet(routelist.get(i).getState()));

                }

            }

            for(int i=0;i<routelist.size()-1;i++)
            {
                mMap.addPolyline(new PolylineOptions().add(new LatLng(Double.parseDouble(routelist.get(i).getLat()), Double.parseDouble(routelist.get(i).getLng())),new LatLng(Double.parseDouble(routelist.get(i+1).getLat()), Double.parseDouble(routelist.get(i+1).getLng()))).color(Color.BLUE));
            }


        }


        if(TrainRouteFragment.ti!=null)
        {
            List<Route> routelist=TrainRouteFragment.ti.getRoute();

            for(int i=0;i<routelist.size();i++)
            {
                if(i==0) {
                    mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(routelist.get(i).getLat()), Double.parseDouble(routelist.get(i).getLng()))).title(routelist.get(i).getFullname()).snippet(routelist.get(i).getState()));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(routelist.get(i).getLat()), Double.parseDouble(routelist.get(i).getLng())),15));
                }
                else
                {
                    mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(routelist.get(i).getLat()), Double.parseDouble(routelist.get(i).getLng()))));

                }

            }

            for(int i=0;i<routelist.size()-1;i++)
            {
                mMap.addPolyline(new PolylineOptions().add(new LatLng(Double.parseDouble(routelist.get(i).getLat()), Double.parseDouble(routelist.get(i).getLng())), new LatLng(Double.parseDouble(routelist.get(i).getLat()), Double.parseDouble(routelist.get(i).getLng()))).color(Color.BLUE));
            }


        }

    }
}
