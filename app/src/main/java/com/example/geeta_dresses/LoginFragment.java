package com.example.geeta_dresses;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.geeta_dresses.constants.Constant;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginFragment extends Fragment {

    TextInputLayout email, password;
    Button forgetPassword, loginBtn;
    RequestQueue requestQueue;
    SharedPreferences tokenSp, loginSp, userSP;
    JSONObject object;
    Constant constant;
    JsonObjectRequest jsonObjectRequest;
    final float v = 0;

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
        loginSp = requireContext().getSharedPreferences("login",Context.MODE_PRIVATE);

        if(loginSp.getBoolean("isLogged",false)){
            Intent intent = new Intent(requireContext(),Dashboard.class);
            startActivity(intent);
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                constant = new Constant();
                // URL
                String url = constant.getURL()+constant.getPORT()+constant.getUSER_LOGIN();
                //String url  = "http://  :8080/user/login/";

                // Creating Json Object For Post Request
                try {
                    object = new JSONObject();
                    object.put("userEmail",email.getEditText().getText().toString().trim());
                    object.put("userPassword",password.getEditText().getText().toString().trim());
                }
                catch (JSONException e){
                    e.printStackTrace();
                }

                // Creating a request
                jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("VolleyResponse",response.toString());

                        try {
                            Boolean result = response.getString("status").equals("OK");
                            if(result){

                                // Storing Token Into Shared Preference
                                tokenSp = requireContext().getSharedPreferences("tokenSharedPreferences",Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = tokenSp.edit();
                                editor.putString("token",response.getString("token"));
                                editor.apply();

                                // Saving User Data In Shared Preferences
                                JSONArray dataArray = response.getJSONArray("data");
                                JSONObject data = (JSONObject) dataArray.get(0);
                                userSP = requireContext().getSharedPreferences("userMetadata",Context.MODE_PRIVATE);
                                SharedPreferences.Editor userEditor = userSP.edit();
                                userEditor.putString("userName", data.getString("userName"));
                                userEditor.putString("userEmail", data.getString("userEmail"));
                                userEditor.putString("userId",data.getString("id"));
                                userEditor.apply();
                                Log.d("User Name",data.getString("userName"));
                                Log.d("User Email",data.getString("userEmail"));

                                // Saving Login Session
                                loginSp.edit().putBoolean("isLogged", true).apply();

                                // Success Message and Redirecting
                                Toast.makeText(getContext(),"Login Successful",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getActivity(),Dashboard.class));
                            }
                            else{
                                Toast.makeText(getContext(),"Login Unsuccessful",Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(requireContext(),"Something Went Wrong",Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                });
                requestQueue.add(jsonObjectRequest);
            }
        });
        return view;
    }
}