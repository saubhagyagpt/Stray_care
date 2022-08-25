package com.exple.stray_care;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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


public class ngo extends AppCompatActivity {
    Button b1;

    EditText email,password;

    String ngologin_email,ngologin_pass;
    String url = "https://stray-care.000webhostapp.com/NGOLOGIN.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo);
        ActionBar title=getSupportActionBar();
        title.setTitle("NGO");
        b1=findViewById(R.id.NGOSIGNUPBUTTONMAINPAGE);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ngo.this, ngosignup.class);
                startActivity(i);

            }
        });

    }

    public void Login(View view) {
        email=(EditText)findViewById(R.id.ngologinid);
        password=(EditText)findViewById(R.id.ngologinpass);
        if(email.getText().toString().equals("")){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
        }
        else if(password.getText().toString().equals("")){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }
        else{


            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please Wait..");

            progressDialog.show();
            ngologin_email= email.getText().toString().trim();
            ngologin_pass=password.getText().toString().trim();


            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();

                    if(response.equalsIgnoreCase("user not found")){


                        Toast.makeText(ngo.this, response, Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Intent i=new Intent(getApplicationContext(), ngoprofile.class);
                        i.putExtra("email",ngologin_email);
                        i.putExtra("pass",ngologin_pass);
                        startActivity(i);
                        Toast.makeText(ngo.this, response, Toast.LENGTH_SHORT).show();
                    }

                }
            },new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(ngo.this,error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("email",ngologin_email);
                    params.put("mobile",ngologin_pass);



                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(ngo.this);
            requestQueue.add(request);




        }

    }
}

