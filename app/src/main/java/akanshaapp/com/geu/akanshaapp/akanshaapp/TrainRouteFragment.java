package akanshaapp.com.geu.akanshaapp.akanshaapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class TrainRouteFragment extends Fragment {


    AutoCompleteTextView actvsource;
    RecyclerView rv;
    Button bsearch;
 public  static TrainInfo ti;
    TextView trainroot;

    public TrainRouteFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_train_route, container, false);

        actvsource=(AutoCompleteTextView)v.findViewById(R.id.actsource);
        bsearch=(Button)v.findViewById(R.id.bsearch);
        rv=(RecyclerView)v.findViewById(R.id.trainrouterecycler);
        trainroot=(TextView)v.findViewById(R.id.linkgoogle);
        trainroot.setVisibility(View.INVISIBLE);

        trainroot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),MapsActivity.class);

                startActivity(i);
            }
        });

bsearch.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        fetchdetails(actvsource.getText().toString());
    }
});
        return v;

    }


    public void fetchdetails(String trainnumber)
    {

        try {


            String response = new ApiCal().execute("http://api.railwayapi.com/route/train/" + trainnumber + "/apikey/iuspw9281/").get();
            JSONObject jsonObject = new JSONObject(response);

            JSONArray jsonArray = jsonObject.getJSONArray("route");
            JSONObject jsonObject2 = jsonObject.getJSONObject("train");

            List<Route> listroute=new ArrayList<Route>();

            for (int x = 0; x < jsonArray.length(); x++) {


                String halt = jsonArray.getJSONObject(x).getString("halt");
                String lat = jsonArray.getJSONObject(x).getString("lat");
                String distance = jsonArray.getJSONObject(x).getString("distance");
                String route = jsonArray.getJSONObject(x).getString("route");
                String number = jsonArray.getJSONObject(x).getString("no");
                String code = jsonArray.getJSONObject(x).getString("code");
                String state = jsonArray.getJSONObject(x).getString("state");
                String day = jsonArray.getJSONObject(x).getString("day");

                String lng = jsonArray.getJSONObject(x).getString("lng");
                String fullname = jsonArray.getJSONObject(x).getString("fullname");
                String departure = jsonArray.getJSONObject(x).getString("schdep");
                String arrival = jsonArray.getJSONObject(x).getString("scharr");

                Route routes=new Route(number,distance,day,halt,route,code,fullname,lat,lng,state,arrival,departure);
                listroute.add(routes);
            }



            ti=new TrainInfo(null,listroute,null);

            if(ti!=null)
            {
                trainroot.setVisibility(View.VISIBLE);
            }

            LinearLayoutManager llm=new LinearLayoutManager(getActivity());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            rv.setLayoutManager(llm);
            TrainRouteAdapter tb=new TrainRouteAdapter(getActivity(),ti);
            rv.setAdapter(tb);





        } catch (Exception e) {

        }

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
