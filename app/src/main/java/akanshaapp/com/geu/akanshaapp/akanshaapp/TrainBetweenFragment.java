package akanshaapp.com.geu.akanshaapp.akanshaapp;

import android.content.Context;
import android.content.Intent;
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
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class TrainBetweenFragment extends Fragment {


    AutoCompleteTextView actsource,actdestination;
    Button bsearch;
    TextView tgooglemap;
    RecyclerView rv;
    List<TrainBetweenInfo> trainbetweenlist;


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


            trainbetweenlist=new ArrayList<TrainBetweenInfo>();


              String res = apc.execute("http://api.railwayapi.com/between/source/" + source + "/dest/" + destination + "/apikey/iuspw9281/").get();




            JSONObject jsonObject = new JSONObject(res);

            JSONArray jsonArray = jsonObject.getJSONArray("train");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);



                String arrival=jsonObject1.getString("dest_arrival_time");
                String departure=jsonObject1.getString("src_departure_time");
                String to=jsonObject1.getJSONObject("to").getString("name");
                String from=jsonObject1.getJSONObject("from").getString("name");
                String name=jsonObject1.getString("name");
                String number=jsonObject1.getString("number");

                TrainBetweenInfo tbi=new TrainBetweenInfo(name,number,arrival,departure,from,to);

                trainbetweenlist.add(tbi);

            }
            LinearLayoutManager llm=new LinearLayoutManager(getActivity());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            rv.setLayoutManager(llm);
            TrainBetweenAdapter tb=new TrainBetweenAdapter((HomeActivity)getActivity(),trainbetweenlist);
            rv.setAdapter(tb);
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
}
