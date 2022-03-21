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

import com.example.walk_in_sale.models.Order;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Objects;

import io.realm.Realm;


public class AddOrder extends AppCompatActivity {
    TextInputLayout storeName,supplierName,supplier_address;
    TextInputLayout order_number,status,transport,order_by,description;
    Button add,cancel,order_date, delivery_date;
    Realm realm;
    DatePickerDialog picker;
    //TextInputLayout eText;
   String order_date_str,delivery_date_str;

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


        storeName = findViewById(R.id.storeName);
        order_date = findViewById(R.id.orderDate);
        supplierName = findViewById(R.id.supplierName);
        supplier_address = findViewById(R.id.supplier_address);
        order_number = findViewById(R.id.order_number);
        status = findViewById(R.id.status);
        delivery_date = findViewById(R.id.DeliveryDate);
        transport = findViewById(R.id.transport);
        order_by = findViewById(R.id.order_by);
        description = findViewById(R.id.description);
        add = findViewById(R.id.add);
        //cancel = findViewById(R.id.cancel);


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
                                String str = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                setOrder_date_str(str);

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
                                String str = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                setDelivery_date_str(str);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        realm = Realm.getDefaultInstance();

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
//                TextInputLayout storeName,supplierName,supplier_address;
//                TextInputLayout order_number,status,transport,order_by,description;
                Order order = bgRealm.createObject(Order.class);

                order.setStoreName(storeName.getEditText().getText().toString());
                order.setSupplierName(supplierName.getEditText().getText().toString());
                order.setOrderNo(Long.parseLong(order_number.getEditText().getText().toString()));
                order.setStatus(status.getEditText().getText().toString());
                order.setTransportId(transport.getEditText().getText().toString());
                order.setOrderedByName(order_by.getEditText().getText().toString());
                order.setDescription(description.getEditText().getText().toString());
                order.setSupplierAddress(supplier_address.getEditText().getText().toString());
                

            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // Transaction was a success.
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // Transaction failed and was automatically canceled.
            }
        });




    }
    public TextInputLayout getStoreName() {
        return storeName;
    }

    public void setStoreName(TextInputLayout storeName) {
        this.storeName = storeName;
    }

    public TextInputLayout getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(TextInputLayout supplierName) {
        this.supplierName = supplierName;
    }

    public TextInputLayout getSupplier_address() {
        return supplier_address;
    }

    public void setSupplier_address(TextInputLayout supplier_address) {
        this.supplier_address = supplier_address;
    }

    public TextInputLayout getOrder_number() {
        return order_number;
    }

    public void setOrder_number(TextInputLayout order_number) {
        this.order_number = order_number;
    }

    public TextInputLayout getStatus() {
        return status;
    }

    public void setStatus(TextInputLayout status) {
        this.status = status;
    }

    public TextInputLayout getTransport() {
        return transport;
    }

    public void setTransport(TextInputLayout transport) {
        this.transport = transport;
    }

    public TextInputLayout getOrder_by() {
        return order_by;
    }

    public void setOrder_by(TextInputLayout order_by) {
        this.order_by = order_by;
    }

    public TextInputLayout getDescription() {
        return description;
    }

    public void setDescription(TextInputLayout description) {
        this.description = description;
    }

    public Button getAdd() {
        return add;
    }

    public void setAdd(Button add) {
        this.add = add;
    }

    public Button getCancel() {
        return cancel;
    }

    public void setCancel(Button cancel) {
        this.cancel = cancel;
    }

    public Button getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Button order_date) {
        this.order_date = order_date;
    }

    public Button getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(Button delivery_date) {
        this.delivery_date = delivery_date;
    }

    public Realm getRealm() {
        return realm;
    }

    public void setRealm(Realm realm) {
        this.realm = realm;
    }

    public DatePickerDialog getPicker() {
        return picker;
    }

    public void setPicker(DatePickerDialog picker) {
        this.picker = picker;
    }

    public String getOrder_date_str() {
        return order_date_str;
    }

    public void setOrder_date_str(String order_date_str) {
        this.order_date_str = order_date_str;
    }

    public String getDelivery_date_str() {
        return delivery_date_str;
    }

    public void setDelivery_date_str(String delivery_date_str) {
        this.delivery_date_str = delivery_date_str;
    }
}