package com.exple.stray_care;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
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

public class usersendabandomedngodetails extends AppCompatActivity {
    TextView tvid, tvname, tvemail, tvmobile, tvaddress, tvUSERNAME, tvUSEREMAIL, tvcondition, tvlocation, tvanitype;
    String tvstringcondition, tvstringlocation, Tvstringanitype;
    int position;
    String url="https://stray-care.000webhostapp.com/usersendabandonedngodata.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersendabandomedngodetails);
        ActionBar t=getSupportActionBar();
        t.setTitle("FILL DETAILS OF ABANDONED ANIMAL");
        tvid = findViewById(R.id.txtID);
        tvname = findViewById(R.id.txtname);
        tvemail = findViewById(R.id.txtemail);
        tvmobile = findViewById(R.id.txcontact);
        tvaddress = findViewById(R.id.txtaddress);
        tvUSEREMAIL = findViewById(R.id.txtUSERNAME);
        tvUSERNAME = findViewById(R.id.txtUSEREMAIL);


        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");
        tvid.setText("NGO ID: " + userdonate.userdonateNgodata.get(position).getId());
        tvname.setText("NGO NAME: " + userdonate.userdonateNgodata.get(position).getName());
        tvemail.setText("NGO EMAIL: " + userdonate.userdonateNgodata.get(position).getEmail());
        tvmobile.setText("NGO MOBILE: " + userdonate.userdonateNgodata.get(position).getMobile());
        tvaddress.setText("NGO ADDRESS: " + userdonate.userdonateNgodata.get(position).getAddress());
        tvUSERNAME.setText("USER NAME: " + USERPROFILE.name);
        tvUSEREMAIL.setText("USER EMAIL " + USERPROFILE.emaill);
    }

    public void insertabandonedanidata(View view) {

        tvanitype = findViewById(R.id.txtanitype);
        tvcondition = findViewById(R.id.textcondition);
        tvlocation = findViewById(R.id.textViewLOCATION);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");


        if (tvcondition.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Animals Condition", Toast.LENGTH_SHORT).show();
        } else if (tvanitype.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Animal type", Toast.LENGTH_SHORT).show();
        } else if (tvlocation.getText().toString().equals("")) {
            Toast.makeText(this, "Enter location", Toast.LENGTH_SHORT).show();
        } else {

            progressDialog.show();
            Tvstringanitype = tvanitype.getText().toString();
            tvstringlocation = tvlocation.getText().toString();
            tvstringcondition = tvcondition.getText().toString();

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    tvanitype.setText("");
                    tvlocation.setText("");
                    tvcondition.setText("");
                    Toast.makeText(usersendabandomedngodetails.this, response, Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(usersendabandomedngodetails.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("us_id", USERPROFILE.id );
                    params.put("ngo_id", userdonate.userdonateNgodata.get(position).getId());
                    params.put("animal_type", Tvstringanitype);
                    params.put("location", tvstringlocation);
                    params.put("condition", tvstringcondition);
                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(usersendabandomedngodetails.this);
            requestQueue.add(request);


        }
    }
}