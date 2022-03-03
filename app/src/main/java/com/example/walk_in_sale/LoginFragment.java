package com.example.walk_in_sale;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginFragment extends Fragment {

    TextInputLayout email, password;
    Button forgetPassword, loginBtn;
    RequestQueue requestQueue;
    float v = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        email = view.findViewById(R.id.login_email);
        password = view.findViewById(R.id.password);
        forgetPassword = view.findViewById(R.id.forget_btn);
        loginBtn = view.findViewById(R.id.login_btn);

        email.setTranslationX(800);
        password.setTranslationX(800);
        forgetPassword.setTranslationX(800);
        loginBtn.setTranslationX(800);

        email.setAlpha(v);
        password.setAlpha(v);
        forgetPassword.setAlpha(v);
        loginBtn.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetPassword.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        loginBtn.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        // RequestQueue For Handle Network Request
        requestQueue = Volley.newRequestQueue(requireContext());

        // Setting On Click Listener On Button
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Creating JSON Param Object
                JSONObject object = new JSONObject();
                try {
                    //input your API parameters
                    object.put("userEmail",email.getEditText().getText().toString());
                    object.put("userPassword",password.getEditText().getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Enter the correct url for your api service site
                String url = "http://192.168.0.6:8080/user/login/";
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, object,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    SharedPreferences sharedPreferences = requireContext().getSharedPreferences("tokenSharedPreferences", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();

                                    if(!(response.getString("token").length() <= 1)){
                                        editor.putString("token",response.getString("token"));
                                        editor.apply();
                                        String token =  sharedPreferences.getString("token","No Data");
                                        Log.d("Response",response.toString());
                                        Toast.makeText(requireContext(),"Login Successful",Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        Toast.makeText(requireContext(),"Login Failed",Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, error -> Toast.makeText(requireContext(),"Something Went Wrong!!",Toast.LENGTH_LONG).show());
                requestQueue.add(jsonObjectRequest);
            }
        });
        return view;
    }
}