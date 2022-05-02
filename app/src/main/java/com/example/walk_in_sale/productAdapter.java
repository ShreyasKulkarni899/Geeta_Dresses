package com.example.walk_in_sale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class productAdapter extends RecyclerView.Adapter<productAdapter.Viewholder> {

    private final Context context;
    private final ArrayList<productsModel> productModelArrayList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //public final TextView tvFishName;
        public TextView tvQuantity;
        public int tvTotalPur;
        public int tvSoldQuantity;
        public String tvDealerName;
        private Context context;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            context = view.getContext();
//            tvFishName = (TextView) view.findViewById(R.id.tvFishName);
//            tvQuantity = (TextView) view.findViewById(R.id.tvQuantity);

            //after clicking the button dead on the get stock screen on any next card in the recyclerView
            view.findViewById(R.id.reduceBTN).setOnClickListener(new View.OnClickListener() {
//                @SuppressLint("ShowToast")
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"Reduces",Toast.LENGTH_LONG);
//                    //defination for the dynamic dialog box
//                    AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
//                    View view=LayoutInflater.from(context).inflate(R.layout.dead_card_dialog,null);
//                    TextInputEditText deadQuantityTextView = view.findViewById(R.id.deadQuantity);
//                    TextInputEditText deadReasonTextView  = view.findViewById(R.id.deadReason);
//                    builder.setView(view);
//
//                    //code for the positive button
//                    builder.setPositiveButton("Dead", (dialog, which) -> {
//                        if ((TextUtils.isEmpty(deadQuantityTextView.getText())) && (TextUtils.isEmpty(deadReasonTextView.getText()))) {
//                            Toast.makeText(context,"Complete the formality", Toast.LENGTH_SHORT).show();
//                        }
//                        else{
//                            Toast.makeText(context,deadQuantityTextView.getText().toString() + "KG is Dead because of" + deadReasonTextView.getText().toString(),Toast.LENGTH_LONG);
//                        }
//                    });
//                    //code for the negative button
//                    builder.setNegativeButton("back", (dialog, which) -> dialog.dismiss());
//
//                    //output line for the calling
//                    final AlertDialog alertDialog=builder.create();
//                    alertDialog.show();
//
//                    //designing for buttons
//                    //positive
//                    Button button_positive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
//                    button_positive.setBackgroundColor(Color.GRAY);
//                    button_positive.setPadding(20, 5, 20, 5);
//                    button_positive.setTextColor(Color.BLACK);
//                    //negative
//                    Button button_negative = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
//                    button_negative.setBackgroundColor(Color.GRAY);
//                    button_negative.setPadding(20, 5, 20, 5);
//                    button_negative.setTextColor(Color.BLACK);
                }


            });
            view.findViewById(R.id.increaseBTN).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"Increase",Toast.LENGTH_LONG);
                }
            });
            view.findViewById(R.id.productsDeleteBTN).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"Reduces",Toast.LENGTH_LONG);
                }
            });
        }

    }

    // Constructor
    public productAdapter(Context context, ArrayList<productsModel> productModelArrayList) {
        this.context = context;
        this.productModelArrayList = productModelArrayList;
    }

    @NonNull
    @Override
    public productAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_products_cardview, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull productAdapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        productsModel model = productModelArrayList.get(position);
        holder.idProductName.setText(model.getproduct_name());
        holder.idProductQTY.setText(model.getproduct_qty());
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return productModelArrayList.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public static class Viewholder extends RecyclerView.ViewHolder {
        private final TextView idProductName;
        private final TextView idProductQTY;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            idProductName= itemView.findViewById(R.id.idProductsName);
            idProductQTY = itemView.findViewById(R.id.idProductsQTY);
        }
    }
}
