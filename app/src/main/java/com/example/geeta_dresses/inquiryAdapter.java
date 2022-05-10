package com.example.geeta_dresses;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class inquiryAdapter extends RecyclerView.Adapter<inquiryAdapter.Viewfinder> {

    private Context context;
    ArrayList<inquiryModel> inquiryModelArrayList;
    // Constructor
    public inquiryAdapter(Context context, ArrayList<inquiryModel> inquiryModelArrayList) {
        this.context = context;
        this.inquiryModelArrayList = inquiryModelArrayList;
    }

    @NonNull
    @Override
    public Viewfinder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_inquiry_cardview, parent, false);
        return new Viewfinder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull Viewfinder holder, int position) {
        // to set data to textview and imageview of each card layout
        inquiryModel model = inquiryModelArrayList.get(position);
        holder.idInquiryNo.setText(model.getInquiry_no());
        holder.idInquiryUser.setText(model.getInquiry_user());
        holder.idInquiryDay.setText(model.getInquiry_day());
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return inquiryModelArrayList.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public class Viewfinder extends RecyclerView.ViewHolder {
        private final TextView idInquiryNo;
        private final TextView idInquiryUser;
        private final TextView idInquiryDay;
        private Context context;

        public Viewfinder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            idInquiryNo = itemView.findViewById(R.id.idInquiryNo);
            idInquiryUser = itemView.findViewById(R.id.idInquiryUser);
            idInquiryDay =itemView.findViewById(R.id.idInquiryDay);


        }
    }
}
