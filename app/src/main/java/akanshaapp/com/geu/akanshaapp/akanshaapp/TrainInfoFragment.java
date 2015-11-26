package akanshaapp.com.geu.akanshaapp.akanshaapp;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
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


public class TrainInfoFragment extends Fragment {

AutoCompleteTextView actvtrainnumber;
    Button bsearch;
    TextView linkgooglemap,tvtrainno,tvtrainname,tvlat,tvlong,tvstate,tvsource,tvdestination,tvarrival,tvdeparture;
    TextView dsun,dmon,dtue,dwed,dthur,dfri,dsat;
    LinearLayout layout;


    String trainno=null;

    ProgressDialog pdialog;

   public static TrainInfo ti;

    public TrainInfoFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {


            trainno = getArguments().getString("trainno");


        }


    }

    @Override
    public void onResume() {
        super.onResume();
        if(trainno!=null)
        {
            actvtrainnumber.setText(trainno);
            fetchtraininfo(trainno);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_train_info, container, false);
actvtrainnumber=(AutoCompleteTextView)v.findViewById(R.id.actsource);
        bsearch=(Button)v.findViewById(R.id.bsearch);
        tvtrainno=(TextView)v.findViewById(R.id.tvtrainno);
        tvtrainname=(TextView)v.findViewById(R.id.tvtrainname);
        tvsource=(TextView)v.findViewById(R.id.tvsource);
        tvdestination=(TextView)v.findViewById(R.id.tvdestination);
        tvarrival=(TextView)v.findViewById(R.id.tvarrival);
        tvdeparture=(TextView)v.findViewById(R.id.tvdeparture);
        tvlat=(TextView)v.findViewById(R.id.tvlatitude);
        tvlong=(TextView)v.findViewById(R.id.tvlongitude);
        tvstate=(TextView)v.findViewById(R.id.tvstate);

        linkgooglemap=(TextView)v.findViewById(R.id.linkgoogle);

        dsun=(TextView)v.findViewById(R.id.dsun);
        dmon=(TextView)v.findViewById(R.id.dmon);
        dtue=(TextView)v.findViewById(R.id.dtue);
      dwed=(TextView)v.findViewById(R.id.dwed);
        dthur=(TextView)v.findViewById(R.id.dthu);
        dfri=(TextView)v.findViewById(R.id.dfri);
        dsat=(TextView)v.findViewById(R.id.dsat);

        layout=(LinearLayout)v.findViewById(R.id.layouttraininfo);

        layout.setVisibility(View.INVISIBLE);


        bsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


fetchtraininfo(actvtrainnumber.getText().toString());


            }
        });

        linkgooglemap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(getActivity(),MapsActivity.class);
i.putExtra("from","traininfo");
                startActivity(i);
            }
        });








        return v;
    }




    public void fetchtraininfo(String trainnumber) {




            new ApiCal().execute("http://api.railwayapi.com/route/train/" + trainnumber + "/apikey/iuspw9281/");



    }


        public void parsing(String response)
    {
        try
        {
        JSONObject jsonObject = new JSONObject(response);

        JSONArray jsonArray = jsonObject.getJSONArray("route");
        JSONObject jsonObject2 = jsonObject.getJSONObject("train");
        JSONArray jsonArray2=jsonObject2.getJSONArray("days");

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

        Days days=new Days();

        for (int r=0;r<jsonArray2.length();r++) {

            JSONObject jsonObject3=jsonArray2.getJSONObject(r);

            switch(r)
            {
                case 0:
                    days.SUN= jsonObject3.getString("runs");
                    break;
                case 1:
                    days.MON= jsonObject3.getString("runs");
                    break;
                case 2:
                    days.TUE= jsonObject3.getString("runs");
                    break;
                case 3:
                    days.WED= jsonObject3.getString("runs");
                    break;
                case 4:
                    days.THU= jsonObject3.getString("runs");
                    break;
                case 5:
                    days.FRI= jsonObject3.getString("runs");
                    break;
                case 6:
                    days.SAT= jsonObject3.getString("runs");
                    break;



            }

        }

        days.name=jsonObject2.getString("name");
        days.number=jsonObject2.getString("number");


        ti=new TrainInfo(days,listroute,null);




    } catch (Exception e) {

    }

}


    public class ApiCal extends AsyncTask<String, Void, String> {




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


            tvtrainno.setText(ti.getDays().number);
            tvtrainname.setText(ti.getDays().name);
            tvlong.setText(ti.getRoute().get(0).getLng());
            tvlat.setText(ti.getRoute().get(0).getLat());
            tvstate.setText(ti.getRoute().get(0).getState());
            tvarrival.setText(ti.getRoute().get(0).getScharr());
            tvdeparture.setText(ti.getRoute().get(0).getDeparture());
            tvsource.setText(ti.getRoute().get(0).getFullname());
            tvdestination.setText(ti.getRoute().get(ti.getRoute().size()-1).getFullname());

            dsun.setText(ti.getDays().SUN);
            dmon.setText(ti.getDays().MON);
            dtue.setText(ti.getDays().TUE);
            dwed.setText(ti.getDays().WED);
            dthur.setText(ti.getDays().THU);
            dfri.setText(ti.getDays().FRI);
            dsat.setText(ti.getDays().SAT);


            layout.setVisibility(View.VISIBLE);



        }

        @Override
        protected String doInBackground(String... params) {
            String response = getTrainInfoResponse(params[0]);

parsing(response);

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
