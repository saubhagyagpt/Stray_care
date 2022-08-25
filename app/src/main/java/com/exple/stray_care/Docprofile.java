package com.exple.stray_care;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Docprofile extends AppCompatActivity {
    ListView listView;
    Adaptertshorofiledoctor adapter;
    public static ArrayList<doctorshowprofiledata> doctorshowprofileList = new ArrayList<>();
    String url = "https://stray-care.000webhostapp.com/doctorshowprofile.php";


    Button  v2;

    public static String id,name,emaill,mobile,address;
    String pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docprofile);
        listView = findViewById(R.id.myListDOCSHOWPROFILE);
        ActionBar t=getSupportActionBar();
        t.setTitle("DOCTOR PROFILE");
        adapter = new  Adaptertshorofiledoctor(this,doctorshowprofileList);
        listView.setAdapter(adapter);
        Intent i = getIntent();
        emaill = i.getStringExtra("email");
        pass = i.getStringExtra("pass");
        v2 = findViewById(R.id.docprofilelistinjuredbutton);


        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), DOCTORSHOWINJUREDPET.class);
                startActivity(i);

            }
        });
        retrievedata();
    }


    public void retrievedata() {

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        doctorshowprofileList.clear();
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if (sucess.equals("1")) {


                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String id = object.getString("doc_id");
                                    String name = object.getString("name");
                                    String email = object.getString("email");
                                    String mobile = object.getString("mobile");
                                    String address = object.getString("address");
                                    doctorshowprofiledata e = new doctorshowprofiledata(id,name, email,mobile,address);
                                    doctorshowprofileList.add(e);
                                    adapter.notifyDataSetChanged();


                                }


                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Docprofile.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("email",emaill);
                params.put("mobile",pass);



                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }
    class doctorshowprofiledata {
        public String id,name, email, mobile,address;

        public doctorshowprofiledata() {
        }

        public doctorshowprofiledata(String id,String name, String email,String mobile,String address) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.mobile = mobile;
            this.address = address;
        }
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;

        }
        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address= address;


        }
    }

    class  Adaptertshorofiledoctor  extends ArrayAdapter<doctorshowprofiledata> {
        Context context;
        public List<doctorshowprofiledata> arrayListprofiledata;

        public   Adaptertshorofiledoctor (@NonNull Context context, List<doctorshowprofiledata> arrayListprofiledata) {
            super(context, R.layout.custom_list_itemdoctorshowprofile, doctorshowprofileList);
            this.context = context;
            this.arrayListprofiledata = arrayListprofiledata;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_itemdoctorshowprofile, null, true);
            TextView tvId = view.findViewById(R.id.txt_ID);
            TextView tvname = view.findViewById(R.id.txt_name);
            TextView tvemail = view.findViewById(R.id.txt_email);
            TextView tvmobile = view.findViewById(R.id.txt_mobile);
            TextView tvaddress = view.findViewById(R.id.txt_ADDRESS);
            id=arrayListprofiledata.get(position).getId();
            name=arrayListprofiledata.get(position).getName();
            emaill=arrayListprofiledata.get(position).getEmail();
            mobile=arrayListprofiledata.get(position).getMobile();
            address=arrayListprofiledata.get(position).getAddress();

            tvId.setText("DOCTOR_ID: "+arrayListprofiledata.get(position).getId());
            tvname.setText("DOCTOR NAME: "+arrayListprofiledata.get(position).getName());
            tvemail.setText("DOCTOR EMAIL: "+arrayListprofiledata.get(position).getEmail());
            tvmobile.setText("DOCTOR CONTACT: "+arrayListprofiledata.get(position).getMobile());
            tvaddress.setText("DOCTOR ADDRESS: "+arrayListprofiledata.get(position).getAddress());
            return view;
        }
    }
}