package akanshaapp.com.geu.akanshaapp.akanshaapp;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
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




        LatLng dehradun = new LatLng(30.3180,78.0290);
        mMap.addMarker(new MarkerOptions().position(dehradun).title("My location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(dehradun));


      String from =  getIntent().getExtras().getString("from");

        if (from.contains("traininfo"))
        {
        if(TrainInfoFragment.ti!=null) {
            List<Route> routelist = TrainInfoFragment.ti.getRoute();

            for (int i = 0; i < routelist.size(); i++) {

                if(Double.parseDouble(TrainInfoFragment.ti.getRoute().get(i).getLat())!=0.0&&!TrainInfoFragment.ti.getRoute().get(i).getFullname().contains("NEW DELHI")) {
                    if (i == 0) {
                        mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(routelist.get(i).getLat()), Double.parseDouble(routelist.get(i).getLng()))).title(routelist.get(i).getFullname()).snippet(routelist.get(i).getState()));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(routelist.get(i).getLat()), Double.parseDouble(routelist.get(i).getLng())), 15));
                    } else {
                        mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(routelist.get(i).getLat()), Double.parseDouble(routelist.get(i).getLng()))).title(routelist.get(i).getFullname()).snippet(routelist.get(i).getState()));

                    }
                }
                else
                {
                    TrainInfoFragment.ti.getRoute().get(i).setLat("28.643");
                    TrainInfoFragment.ti.getRoute().get(i).setLng("77.222");
                    TrainInfoFragment.ti.getRoute().get(i).setState("Delhi");
                    mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(TrainInfoFragment.ti.getRoute().get(i).getLat()), Double.parseDouble(TrainInfoFragment.ti.getRoute().get(i).getLng()))).title(TrainInfoFragment.ti.getRoute().get(i).getFullname()).snippet(TrainInfoFragment.ti.getRoute().get(i).getState()));


                }
            }
routelist=TrainInfoFragment.ti.getRoute();
            for (int i = 0; i < TrainInfoFragment.ti.getRoute().size() - 1;) {

                int x=i;

                int y;
                if(Double.parseDouble(TrainInfoFragment.ti.getRoute().get(i+1).getLng())!=0.0)
                {
                    y=i+1;
                }
                else
                {
                    y=i+2;
                }


                mMap.addPolyline(new PolylineOptions().add(new LatLng(Double.parseDouble(routelist.get(x).getLat()), Double.parseDouble(routelist.get(x).getLng())), new LatLng(Double.parseDouble(routelist.get(y).getLat()), Double.parseDouble(routelist.get(y).getLng()))).color(Color.BLUE));

            i=y;

            }
        }

        }
        else if(from.contains("trainbetween"))
        {


        if(TrainRouteFragment.ti!=null) {
            List<Route> routelist = TrainRouteFragment.ti.getRoute();

            for (int i = 0; i < routelist.size(); i++) {

                if(Double.parseDouble(TrainRouteFragment.ti.getRoute().get(i).getLat())!=0.0&&!TrainRouteFragment.ti.getRoute().get(i).getFullname().contains("NEW DELHI")) {

                        if (i == 0) {
                            mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(routelist.get(i).getLat()), Double.parseDouble(routelist.get(i).getLng()))).title(routelist.get(i).getFullname()).snippet(routelist.get(i).getState()));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(routelist.get(i).getLat()), Double.parseDouble(routelist.get(i).getLng())), 15));
                        } else {
                            mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(routelist.get(i).getLat()), Double.parseDouble(routelist.get(i).getLng()))));

                        }
                    }
                    else
                    {
                        TrainRouteFragment.ti.getRoute().get(i).setLat("28.643");
                        TrainRouteFragment.ti.getRoute().get(i).setLat("77.222");
                        TrainRouteFragment.ti.getRoute().get(i).setState("Delhi");
                        mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(TrainRouteFragment.ti.getRoute().get(i).getLat()), Double.parseDouble(TrainRouteFragment.ti.getRoute().get(i).getLng()))).title(TrainRouteFragment.ti.getRoute().get(i).getFullname()).snippet(TrainRouteFragment.ti.getRoute().get(i).getState()));

                    }

            }
routelist=TrainRouteFragment.ti.getRoute();
            for (int i = 0; i < routelist.size() - 1; i++) {

                int x=i;

                int y;
                if(Double.parseDouble(TrainRouteFragment.ti.getRoute().get(i+1).getLng())!=0.0)
                {
                    y=i+1;
                }
                else
                {
                    y=i+2;
                }


                mMap.addPolyline(new PolylineOptions().add(new LatLng(Double.parseDouble(routelist.get(x).getLat()), Double.parseDouble(routelist.get(x).getLng())), new LatLng(Double.parseDouble(routelist.get(y).getLat()), Double.parseDouble(routelist.get(y).getLng()))).color(Color.BLUE));

          i=y;

            }

        }
        }


    }
}
