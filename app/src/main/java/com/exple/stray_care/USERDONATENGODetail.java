package com.exple.stray_care;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.*;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class USERDONATENGODetail extends AppCompatActivity {
TextView TVID, tvname,tvemail,tvmobile,tvaddress,tvamount,tvpickloc;
String tvstringamount,tvstringpickuoloc;
String url="https://stray-care.000webhostapp.com/DONATION.php";
int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdonatengodetail);
        ActionBar t=getSupportActionBar();
        t.setTitle("FILL DETAILS OF DONATION");
        TVID=findViewById(R.id.txtid);
    tvname=findViewById(R.id.txtname);
    tvemail=findViewById(R.id.txtemail);
    tvmobile=findViewById(R.id.txcontact);
    tvaddress=findViewById(R.id.txtaddress);

        Intent intent=getIntent();
        position=intent.getExtras().getInt("position");
        TVID.setText("NGO ID"+userdonate.userdonateNgodata.get(position).getId());
        tvname.setText("NGO NAME: "+userdonate.userdonateNgodata.get(position).getName());
        tvemail.setText("NGO EMAIL: "+userdonate.userdonateNgodata.get(position).getEmail());
        tvmobile.setText("NGO MOBILE: "+userdonate.userdonateNgodata.get(position).getMobile());
        tvaddress.setText("NGO ADDRESS: "+userdonate.userdonateNgodata.get(position).getAddress());
    }
    public void inseruserdonate(View view) {

        tvamount=findViewById(R.id.txtamount);
        tvpickloc=findViewById(R.id.txtpickuploc);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");


        if (tvamount.getText().toString().equals("")) {
            Toast.makeText(this, "Enter amount", Toast.LENGTH_SHORT).show();
        } else if (tvpickloc.getText().toString().equals("")) {
            Toast.makeText(this, "Enter pickup location", Toast.LENGTH_SHORT).show();
        } else {

            progressDialog.show();
            tvstringamount = tvamount.getText().toString();
            tvstringpickuoloc = tvpickloc.getText().toString();


            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    tvamount.setText("");
                    tvpickloc.setText("");

                    Toast.makeText(USERDONATENGODetail.this, response, Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(USERDONATENGODetail.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("us_id", USERPROFILE.id );
                    params.put("ngo_id", userdonate.userdonateNgodata.get(position).getId());
                    params.put("amount", tvstringamount);
                    params.put("pickuplocation", tvstringpickuoloc);

                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(USERDONATENGODetail.this);
            requestQueue.add(request);


        }
    }
}