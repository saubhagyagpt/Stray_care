package com.exple.stray_care;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
public class usersignup extends AppCompatActivity {
    EditText name,email,mobile,address;
    String user_name,user_email,user_mobile,user_address;
    String url="https://stray-care.000webhostapp.com/USER.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersignup);
    }
        public void Register(View view) {
            name=(EditText) findViewById(R.id.SIGNUPuserNAMEINPUT);
            email=(EditText) findViewById(R.id.SIGNUPuserEMAILINPUT);
            mobile=(EditText) findViewById(R.id.SIGNUPuserMOBILEINPUT);
            address=(EditText) findViewById(R.id.SIGNUPuserADDRESSINPUT);
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please Wait..");


            if(name.getText().toString().equals("")){
                Toast.makeText(this, "Enter Username", Toast.LENGTH_SHORT).show();
            }
            else if(email.getText().toString().equals("")){
                Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
            }
            else if(mobile.getText().toString().equals("")){
                Toast.makeText(this, "Enter mobile", Toast.LENGTH_SHORT).show();
            }
            else if(address.getText().toString().equals("")){
                Toast.makeText(this, "Enter address", Toast.LENGTH_SHORT).show();
            }
            else{

                progressDialog.show();
                user_name = name.getText().toString().trim();
                user_email = email.getText().toString().trim();
                user_mobile = mobile.getText().toString().trim();
                user_address = address.getText().toString().trim();

                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        name.setText("");
                        email.setText("");
                        mobile.setText("");
                        address.setText("");
                        Toast.makeText(usersignup.this, response, Toast.LENGTH_SHORT).show();
                    }
                },new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(usersignup.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                }

                ){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();

                        params.put("name",user_name);
                        params.put("email",user_email);
                        params.put("mobile",user_mobile);
                        params.put("address",user_address);
                        return params;

                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(usersignup.this);
                requestQueue.add(request);


            }
    }
}