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

public class usersendinjuredpettodoctordetails extends AppCompatActivity {
    TextView tvid, tvname, tvemail, tvmobile, tvaddress, tvUSERNAME, tvUSEREMAIL, tvinjurytype, tvlocation, tvanitype;
    String tvstringinjurytype, tvstringlocation, Tvstringanitype;
    int position;
    String url = "https://stray-care.000webhostapp.com/insertUsersendinjuredpetdetails.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar t=getSupportActionBar();
        t.setTitle("FILL DETAILS OF INJURED ANIMAL");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersendinjuredpettodoctordetails);
        tvid = findViewById(R.id.txtID);
        tvname = findViewById(R.id.txtname);
        tvemail = findViewById(R.id.txtemail);
        tvmobile = findViewById(R.id.txcontact);
        tvaddress = findViewById(R.id.txtaddress);
        tvUSEREMAIL = findViewById(R.id.txtUSERNAME);
        tvUSERNAME = findViewById(R.id.txtUSEREMAIL);


        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");
        tvid.setText("DOCTOR ID: "+usersendinjuredpetdoc.usersendinjureddocdata.get(position).getId());
        tvname.setText("DOCTOR NAME: " + usersendinjuredpetdoc.usersendinjureddocdata.get(position).getName());
        tvemail.setText("DOCTOR EMAIL: " + usersendinjuredpetdoc.usersendinjureddocdata.get(position).getEmail());
        tvmobile.setText("DOCTOR MOBILE: " + usersendinjuredpetdoc.usersendinjureddocdata.get(position).getMobile());
        tvaddress.setText("DOCTOR ADDRESS: " + usersendinjuredpetdoc.usersendinjureddocdata.get(position).getAddress());

        tvUSERNAME.setText("USER NAME: " + USERPROFILE.name);
        tvUSEREMAIL.setText("USER EMAIL ID " + USERPROFILE.emaill);
    }
   public void insertinjurepetdata(View view){
        tvanitype = findViewById(R.id.txtanitype);
        tvinjurytype = findViewById(R.id.textinjurytype);
        tvlocation = findViewById(R.id.textViewLOCATION);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");


        if (tvinjurytype.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Animals Condition", Toast.LENGTH_SHORT).show();
        } else if (tvanitype.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Animal type", Toast.LENGTH_SHORT).show();
        } else if (tvlocation.getText().toString().equals("")) {
            Toast.makeText(this, "Enter location", Toast.LENGTH_SHORT).show();
        } else {

            progressDialog.show();
            Tvstringanitype = tvanitype.getText().toString();
            tvstringlocation = tvlocation.getText().toString();
            tvstringinjurytype = tvinjurytype.getText().toString();

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    tvanitype.setText("");
                    tvlocation.setText("");
                    tvinjurytype.setText("");
                    Toast.makeText(usersendinjuredpettodoctordetails.this, response, Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(usersendinjuredpettodoctordetails.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("us_id", USERPROFILE.id );
                    params.put("doc_id", usersendinjuredpetdoc.usersendinjureddocdata.get(position).getId());
                    params.put("animal_type", Tvstringanitype);
                    params.put("location", tvstringlocation);
                    params.put("injurytype", tvstringinjurytype);
                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(usersendinjuredpettodoctordetails.this);
            requestQueue.add(request);


        }

    }

}