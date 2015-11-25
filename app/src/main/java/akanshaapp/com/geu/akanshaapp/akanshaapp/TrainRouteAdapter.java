package akanshaapp.com.geu.akanshaapp.akanshaapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by GB on 11/25/2015.
 */
public class TrainRouteAdapter  extends RecyclerView.Adapter<TrainRouteAdapter.ViewHolder> {

    TrainInfo mItems;
    Context context;
    List<Route> routes;

    public TrainRouteAdapter(Context context,TrainInfo pc) {
        super();
        mItems = pc;
        routes=pc.getRoute();
        this.context=context;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.customtrainroute, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final Route tb = routes.get(i);

        viewHolder.tvday.setText(tb.getDay());
        viewHolder.tvhalt.setText(tb.getHalt());
        viewHolder.tvcode.setText(tb.getCode());
        viewHolder.tvfullname.setText(tb.getFullname());
        viewHolder.tvarrival.setText(tb.getScharr());
        viewHolder.tvdeparture.setText(tb.getDeparture());
        viewHolder.tvdistance.setText(tb.getDistance());


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent j=new Intent(context,DetailsActivity.class);




                // context.startActivity(j);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.getRoute().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {



        public TextView tvday;
        public TextView tvhalt;
        public TextView tvcode;
        public TextView tvfullname;
        public TextView tvarrival;
        public TextView tvdeparture;
        public TextView tvdistance;


        public ViewHolder(View itemView) {
            super(itemView);
            tvday= (TextView) itemView.findViewById(R.id.tvday);
            tvhalt= (TextView) itemView.findViewById(R.id.tvhalt);
            tvcode= (TextView) itemView.findViewById(R.id.tvcode);
            tvfullname= (TextView) itemView.findViewById(R.id.tvfullname);
            tvarrival= (TextView) itemView.findViewById(R.id.tvarrival);
            tvdistance= (TextView) itemView.findViewById(R.id.tvdistance);

            tvdeparture= (TextView) itemView.findViewById(R.id.tvdeparture);


        }
    }
}
