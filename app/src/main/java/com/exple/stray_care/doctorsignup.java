package com.exple.stray_care;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class doctorsignup extends AppCompatActivity {
    EditText name,email,mobile,address;
    String DOC_name,DOC_email,DOC_mobile,DOC_address;
    String url="https://stray-care.000webhostapp.com/DOCTOR.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorsignup);
    }

    public void Register(View view) {
        name = (EditText) findViewById(R.id.SIGNUPDOCNAMEINPUT);
        email = (EditText) findViewById(R.id.SIGNUPDOCEMAILINPUT);
        mobile = (EditText) findViewById(R.id.SIGNUPDOCMOBILEINPUT);
        address = (EditText) findViewById(R.id.SIGNUPDOCADDRESSINPUT);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");


        if (name.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Username", Toast.LENGTH_SHORT).show();
        } else if (email.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
        } else if (mobile.getText().toString().equals("")) {
            Toast.makeText(this, "Enter mobile", Toast.LENGTH_SHORT).show();
        } else if (address.getText().toString().equals("")) {
            Toast.makeText(this, "Enter address", Toast.LENGTH_SHORT).show();
        } else {

            progressDialog.show();
            DOC_name = name.getText().toString();
            DOC_email = email.getText().toString();
            DOC_mobile = mobile.getText().toString();
            DOC_address = address.getText().toString();

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    name.setText("");
                    email.setText("");
                    mobile.setText("");
                    address.setText("");
                    Toast.makeText(doctorsignup.this, response, Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(doctorsignup.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();

                    params.put("name", DOC_name);
                    params.put("email", DOC_email);
                    params.put("mobile", DOC_mobile);
                    params.put("address", DOC_address);
                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(doctorsignup.this);
            requestQueue.add(request);


        }
    }
}