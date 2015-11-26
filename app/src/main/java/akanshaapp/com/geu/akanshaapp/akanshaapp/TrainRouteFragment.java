package akanshaapp.com.geu.akanshaapp.akanshaapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.AsyncTask;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class TrainRouteFragment extends Fragment {


    AutoCompleteTextView actvsource;
    RecyclerView rv;
    Button bsearch;
 public  static TrainInfo ti;
    TextView trainroot;

    ProgressDialog pdialog;

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
i.putExtra("from","trainbetween");
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


             new ApiCal().execute("http://api.railwayapi.com/route/train/" + trainnumber + "/apikey/iuspw9281/");







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


    public class ApiCal extends AsyncTask<String, Void, String> {


public void parsing(String response)
{
    JSONObject jsonObject = null;
    try {
        jsonObject = new JSONObject(response);


        JSONArray jsonArray = jsonObject.getJSONArray("route");
        JSONObject jsonObject2 = jsonObject.getJSONObject("train");

        List<Route> listroute = new ArrayList<Route>();

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

            Route routes = new Route(number, distance, day, halt, route, code, fullname, lat, lng, state, arrival, departure);
            listroute.add(routes);
        }


        ti = new TrainInfo(null, listroute, null);

    }catch(Exception e)
    {

    }

}

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pdialog=new ProgressDialog(getActivity());
            pdialog.setMessage("Searching....");
            pdialog.show();

        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            pdialog.dismiss();

            // setting values in project
            if(ti!=null)
            {
                trainroot.setVisibility(View.VISIBLE);
            }

            LinearLayoutManager llm=new LinearLayoutManager(getActivity());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            rv.setLayoutManager(llm);
            TrainRouteAdapter tb=new TrainRouteAdapter(getActivity(),ti);
            rv.setAdapter(tb);



        }

        @Override
        protected String doInBackground(String... params) {
            String response = getTrainInfoResponse(params[0]);
try {
    parsing(response);
}
catch (Exception e)
{

}


            return response;
        }



        public String getTrainInfoResponse(String... args) {

            //Your API key

            String endpoint = args[0];

            HttpURLConnection request = null;
            BufferedReader rd = null;
            StringBuilder response = null;

            try {
                URL endpointUrl = new URL(endpoint);
                request = (HttpURLConnection) endpointUrl.openConnection();
                request.setRequestMethod("GET");
                request.connect();

                rd = new BufferedReader(new InputStreamReader(request.getInputStream()));
                response = new StringBuilder();
                String line = null;
                while ((line = rd.readLine()) != null) {
                    response.append(line + "\n");
                }
            } catch (MalformedURLException e) {
                System.out.println("Exception: " + e.getMessage());
                //e.printStackTrace();
            } catch (ProtocolException e) {
                System.out.println("Exception: " + e.getMessage());
                //e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Exception: " + e.getMessage());
                //e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
                //e.printStackTrace();
            } finally {
                try {
                    request.disconnect();
                } catch (Exception e) {
                }

                if (rd != null) {
                    try {
                        rd.close();
                    } catch (IOException ex) {
                    }
                    rd = null;
                }
            }

            return response != null ? response.toString() : "No Response";
        }
    }





}
