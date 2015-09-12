package com.twinvaves.darshan.zoomcar;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.method.CharacterPickerDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by darshan on 12/09/15.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    Context context;
    ArrayList<Data> mDataList;
    ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions defaultOptions;



    public Adapter(Context applicationContext, ArrayList<Data> list, DisplayImageOptions defaultOptions) {
        this.context = applicationContext;
        this.mDataList = list;
        this.defaultOptions = defaultOptions;
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_card, viewGroup, false);
        Holder viewHolder = new Holder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Holder holder, final int i) {

        try {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,CarDetailsAcivity.class);
                    intent.putExtra("NAME",mDataList.get(i).name);
                    intent.putExtra("RATING",mDataList.get(i).rating);
                    intent.putExtra("RATE",mDataList.get(i).rate_hour);
                    intent.putExtra("SEATS",mDataList.get(i).seater);
                    intent.putExtra("AC",mDataList.get(i).ac);
                    intent.putExtra("IMAGE",mDataList.get(i).image);
                    intent.putExtra("LAT",mDataList.get(i).lat);
                    intent.putExtra("LON",mDataList.get(i).lon);
                    intent.putExtra("TYPE",mDataList.get(i).type);
                    context.startActivity(intent);
                }
            });
            holder.carName.setText(mDataList.get(i).name);
            holder.carSeats.setText("Seater : "+mDataList.get(i).seater);
            holder.carRate.setText("RS."+mDataList.get(i).rate_hour+" per hour");
            imageLoader.displayImage(mDataList.get(i).image,holder.carImage,defaultOptions);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
//        return mDataList.size();
        return 10;
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView carName,carRate,carSeats;
        ImageView carImage;

        public Holder(View itemView) {
            super(itemView);

            carName = (TextView) itemView.findViewById(R.id.name);
            carRate = (TextView) itemView.findViewById(R.id.carRate);
            carSeats = (TextView) itemView.findViewById(R.id.carSeats);
            carImage = (ImageView) itemView.findViewById(R.id.carImage);



        }
    }
}
