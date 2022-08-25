package com.exple.stray_care;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
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
public class doctor extends AppCompatActivity {
    Button b1;

    EditText email,password;

    String doclogin_email,doclogin_password;
    String url = "https://stray-care.000webhostapp.com/DOCTORLOGIN.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        ActionBar title=getSupportActionBar();
        title.setTitle("DOCTOR");

        b1=findViewById(R.id.DOCSIGNUPBUTTONMAINPAGE);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(doctor.this, doctorsignup.class);
                startActivity(i);

            }
        });
    }
    public void Login(View view) {
        email=(EditText)findViewById(R.id.docidlogin);
        password=(EditText)findViewById(R.id.docpasslogin);
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

            doclogin_email = email.getText().toString().trim();
            doclogin_password = password.getText().toString().trim();


            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();

                    if(response.equalsIgnoreCase("user not found")){


                        Toast.makeText(doctor.this, response, Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Intent i=new Intent(getApplicationContext(), Docprofile.class);
                        i.putExtra("email",doclogin_email);
                        i.putExtra("pass",doclogin_password);
                        startActivity(i);
                        Toast.makeText(doctor.this, response, Toast.LENGTH_SHORT).show();
                    }

                }
            },new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(doctor.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("email",doclogin_email);
                    params.put("mobile",doclogin_password);



                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(doctor.this);
            requestQueue.add(request);




        }
    }


}
