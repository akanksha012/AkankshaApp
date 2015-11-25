package akanshaapp.com.geu.akanshaapp.akanshaapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by GB on 11/25/2015.
 */
public class TrainBetweenAdapter extends RecyclerView.Adapter<TrainBetweenAdapter.ViewHolder> {

    List<TrainBetweenInfo> mItems;
    HomeActivity context;

    public TrainBetweenAdapter(HomeActivity context,List<TrainBetweenInfo> pc) {
        super();
        mItems = pc;
        this.context=context;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.customtrainbetween, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        final TrainBetweenInfo tb = mItems.get(i);
        viewHolder.tvtrainno.setText(tb.getNumber());
        viewHolder.tvtrainname.setText(tb.getName());
        viewHolder.tvtrainsource.setText(tb.getFrom());
        viewHolder.tvtraindestination.setText(tb.getTo());
        viewHolder.tvtrainarrival.setText(tb.getArrivaltime());
        viewHolder.tvtraindeparture.setText(tb.getDeparturetime());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction tx =context.getSupportFragmentManager().beginTransaction();

                TrainInfoFragment t=new TrainInfoFragment();
                Bundle args = new Bundle();
                args.putString("trainno",mItems.get(i).getNumber());
                t.setArguments(args);
                tx.replace(R.id.main, t);
                tx.commit();




            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        public TextView tvtrainno;
        public TextView tvtrainname;
        public TextView tvtrainsource;
        public TextView tvtraindestination;
        public TextView tvtrainarrival;
        public TextView tvtraindeparture;


        public ViewHolder(View itemView) {
            super(itemView);
            tvtrainno = (TextView) itemView.findViewById(R.id.tvtrainno);
            tvtrainname= (TextView) itemView.findViewById(R.id.tvtrainname);
            tvtrainsource= (TextView) itemView.findViewById(R.id.tvfrom);
            tvtraindestination= (TextView) itemView.findViewById(R.id.tvto);
            tvtrainarrival= (TextView) itemView.findViewById(R.id.tvarrival);
            tvtraindeparture= (TextView) itemView.findViewById(R.id.tvdeparture);

        }
    }
}
