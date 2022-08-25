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
public class User extends AppCompatActivity {
    Button b1;

    EditText email,password;

    String uslogin_email,uslogin_password;
    String url = "https://stray-care.000webhostapp.com/USERLOGIN.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ActionBar title=getSupportActionBar();
        title.setTitle("USER");

        b1=findViewById(R.id.USERSIGNUPBUTTONMAINPAGE);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(User.this, usersignup.class);

                startActivity(i);

            }
        });
    }
    public void Login(View view) {
        email=(EditText)findViewById(R.id.usidlogin);
        password=(EditText)findViewById(R.id.uspasslogin);
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

            uslogin_email = email.getText().toString().trim();
            uslogin_password = password.getText().toString().trim();


            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();

                    if(response.equalsIgnoreCase("user not found")){


                        Toast.makeText(User.this, response, Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Intent i=new Intent(getApplicationContext(), USERPROFILE.class);
                          i.putExtra("email",uslogin_email);
                        i.putExtra("pass",uslogin_password);
                        startActivity(i);
                        Toast.makeText(User.this, response, Toast.LENGTH_SHORT).show();
                    }

                }
            },new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(User.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("email",uslogin_email);
                    params.put("mobile",uslogin_password);



                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(User.this);
            requestQueue.add(request);




        }
    }


}
