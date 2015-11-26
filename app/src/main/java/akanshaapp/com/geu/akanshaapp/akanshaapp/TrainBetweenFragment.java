package akanshaapp.com.geu.akanshaapp.akanshaapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class TrainBetweenFragment extends Fragment {


    AutoCompleteTextView actsource,actdestination;
    Button bsearch;
    TextView tgooglemap;
    RecyclerView rv;
    List<TrainBetweenInfo> trainbetweenlist;

ProgressDialog pdialog;
    public TrainBetweenFragment() {
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
       View v = inflater.inflate(R.layout.fragment_train_between, container, false);

        actsource=(AutoCompleteTextView)v.findViewById(R.id.actvfrom);
        actdestination=(AutoCompleteTextView)v.findViewById(R.id.actvdestination);
        bsearch=(Button)v.findViewById(R.id.bsearch);
        rv=(RecyclerView)v.findViewById(R.id.trainbetweenrecycler);




        bsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fetchinfo(actsource.getText().toString(), actdestination.getText().toString());

            }
        });
        return v;
    }


    public void fetchinfo(String source,String destination)
    {

        ApiCal apc = new ApiCal();
        try {






               apc.execute("http://api.railwayapi.com/between/source/" + source + "/dest/" + destination + "/apikey/iuspw9281/");







        }
        catch(Exception e)
        {

        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public class ApiCal extends AsyncTask<String, Void, String> {


        public void parsing(String response)
        {

try {

    trainbetweenlist=new ArrayList<TrainBetweenInfo>();
    JSONObject jsonObject = new JSONObject(response);

    JSONArray jsonArray = jsonObject.getJSONArray("train");

    for (int i = 0; i < jsonArray.length(); i++) {
        JSONObject jsonObject1 = jsonArray.getJSONObject(i);


        String arrival = jsonObject1.getString("dest_arrival_time");
        String departure = jsonObject1.getString("src_departure_time");
        String to = jsonObject1.getJSONObject("to").getString("name");
        String from = jsonObject1.getJSONObject("from").getString("name");
        String name = jsonObject1.getString("name");
        String number = jsonObject1.getString("number");

        TrainBetweenInfo tbi = new TrainBetweenInfo(name, number, arrival, departure, from, to);

        trainbetweenlist.add(tbi);
    }
}
    catch(Exception e)
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

            LinearLayoutManager llm=new LinearLayoutManager(getActivity());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            rv.setLayoutManager(llm);
            TrainBetweenAdapter tb=new TrainBetweenAdapter((HomeActivity)getActivity(),trainbetweenlist);
            rv.setAdapter(tb);


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
