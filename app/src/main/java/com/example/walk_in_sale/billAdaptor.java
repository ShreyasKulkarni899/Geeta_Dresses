package com.example.walk_in_sale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class billAdaptor extends RecyclerView.Adapter<billAdaptor.tokenViewHolder> {

    private final Context context;
    private final ArrayList<productsModel> productModelArrayList;

    // Constructor
    public billAdaptor(Context context, ArrayList<productsModel> productModelArrayList) {
        this.context = context;
        this.productModelArrayList = productModelArrayList;
    }

    @NonNull
    @Override
    public tokenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_bill_cardview, parent, false);
        return new tokenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull tokenViewHolder holder, int position) {
        // to set data to textview and imageview of each card layout
        productsModel model = productModelArrayList.get(position);
        holder.idProductName.setText(model.getproduct_name());
        holder.idProductQTY.setText(model.getproduct_qty());
        holder.idProductPrice.setText(model.getProduct_prize());
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return productModelArrayList.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public static class tokenViewHolder extends RecyclerView.ViewHolder {
        private final TextView idProductName;
        private final TextView idProductQTY;
        private final TextView idProductPrice;


        public tokenViewHolder(@NonNull View itemView) {
            super(itemView);
            idProductName= itemView.findViewById(R.id.idProductsBillName);
            idProductQTY = itemView.findViewById(R.id.idProductsBillQTY);
            idProductPrice = itemView.findViewById(R.id.idProductsBillPrice);

        }
    }
}
