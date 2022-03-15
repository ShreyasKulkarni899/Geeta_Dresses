package com.example.walk_in_sale;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Objects;

import io.realm.Realm;


public class AddOrder extends AppCompatActivity {
//    TextInputLayout storeName,order_date,supplierName,supplier_address;
//    TextInputLayout order_number,status,delivery_date,transport,order_by,description;
//    Button add,cancel;
//    Realm realm;
    DatePickerDialog picker;
    //TextInputLayout eText;
    Button order_date, delivery_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);
        Window window = AddOrder.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(AddOrder.this, R.color.black));

        Toolbar toolbar = findViewById(R.id.toolbarHere);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getString(R.string.Add_Order));
        toolbar.setNavigationIcon(R.drawable.ic_back_btn);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                onBackPressed();
            }
        });



//        storeName = findViewById(R.id.storeName);
            order_date = findViewById(R.id.orderDate);
//        supplierName = findViewById(R.id.supplierName);
//        supplier_address = findViewById(R.id.supplier_address);
//        order_number = findViewById(R.id.order_number);
//        status = findViewById(R.id.status);
            delivery_date = findViewById(R.id.DeliveryDate);
//        transport = findViewById(R.id.transport);
//        order_by = findViewById(R.id.order_by);
//        description = findViewById(R.id.description);
//        add = findViewById(R.id.add);
//        cancel = findViewById(R.id.cancel);
//
//        realm = Realm.getDefaultInstance();

//        realm.executeTransactionAsync(new Realm.Transaction() {
//            @Override
//            public void execute(Realm bgRealm) {
//                User user = bgRealm.createObject(User.class);
//                user.setName("John");
//                user.setEmail("john@corporation.com");
//            }
//        }, new Realm.Transaction.OnSuccess() {
//            @Override
//            public void onSuccess() {
//                // Transaction was a success.
//            }
//        }, new Realm.Transaction.OnError() {
//            @Override
//            public void onError(Throwable error) {
//                // Transaction failed and was automatically canceled.
//            }
//        });

        order_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AddOrder.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                order_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        delivery_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AddOrder.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                delivery_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });


    }
}