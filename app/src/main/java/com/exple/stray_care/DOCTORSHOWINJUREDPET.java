package com.exple.stray_care;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.AdapterView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DOCTORSHOWINJUREDPET extends AppCompatActivity {
    ListView listView;
    Adapterdoctorshowinjuredpet adapter;
    int position;
    String url = "https://stray-care.000webhostapp.com/LISTINJUREDPETTODOCTOR.php";
    public static ArrayList<doctorshowinjuredpetdata> doctorshowlistinjuredpet = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorshowinjuredpet);
        listView = findViewById(R.id.myListViewdoctorshowinjuredpet);
        adapter = new Adapterdoctorshowinjuredpet(this,doctorshowlistinjuredpet  );

        ActionBar t=getSupportActionBar();
        t.setTitle("LIST OF INJURED ANIMAL");
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = {"SEND ACKNOWLEDGEMENT MAIL"};
                builder.setTitle("MORE");
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i) {
                            case 0:

                                 Intent emailintent=new Intent(Intent.ACTION_SEND);
                                 emailintent.setData(Uri.parse("mailto:"));
                                 emailintent.setType("text/plain");
                                 emailintent.putExtra(Intent.EXTRA_EMAIL,doctorshowlistinjuredpet.get(position).getEmail());
                                 startActivity(Intent.createChooser(emailintent,"SEND EMAIL"));

                                break;




                        }
                    }
                });


                builder.create().show();


            }
        });

        retrievedata();
    }
    public void retrievedata(){

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){
                        doctorshowlistinjuredpet .clear();
                        try{

                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if(sucess.equals("1")){


                                for(int i=0;i<jsonArray.length();i++){

                                    JSONObject object = jsonArray.getJSONObject(i);


                                    String animaltype = object.getString("animaltype");
                                    String location = object.getString("location");
                                    String name = object.getString("name");
                                    String email = object.getString("email");
                                    String mobile = object.getString("mobile");
                                    doctorshowinjuredpetdata e = new  doctorshowinjuredpetdata(animaltype,location,name,email,mobile);
                                    doctorshowlistinjuredpet .add(e);
                                    adapter.notifyDataSetChanged();



                                }



                            }




                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }






                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DOCTORSHOWINJUREDPET.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("doc_id",Docprofile.doctorshowprofileList.get(position).getId());




                return params;

            }
        };;

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);



    }

}
class doctorshowinjuredpetdata {
    private String animaltype, location;

    private String name, email,mobile;

    public doctorshowinjuredpetdata() {
    }

    public doctorshowinjuredpetdata(String animaltype, String location,String name, String email,String mobile) {
        this.animaltype=animaltype;
        this.location=location;
        this.name = name;
        this.email = email;
        this.mobile=mobile;

    }
    public String getAnimaltype() {
        return animaltype;
    }

    public void setAnimaltype(String animaltype) {
        this.animaltype = animaltype;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

}

class Adapterdoctorshowinjuredpet extends ArrayAdapter<doctorshowinjuredpetdata> {
    Context context;
    List< doctorshowinjuredpetdata> arrayListdoctorshowinjuredpet;
    public   Adapterdoctorshowinjuredpet(@NonNull Context context, List<doctorshowinjuredpetdata>  arrayListdoctorshowinjuredpet) {
        super(context,R.layout.custom_list_itemdoctorshowinjuredpet,arrayListdoctorshowinjuredpet);
        this.context=context;
        this.arrayListdoctorshowinjuredpet=arrayListdoctorshowinjuredpet;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_itemdoctorshowinjuredpet,null,true);
        TextView tvname=view.findViewById(R.id.txt_name);
        TextView tvmobile=view.findViewById(R.id.txt_mobile);
        TextView tvemail=view.findViewById(R.id.txt_email);
        TextView tvanitype=view.findViewById(R.id.txt_animaltype);
        TextView tvloc=view.findViewById(R.id.txt_location);
        tvname.setText("USER'S NAME :"+arrayListdoctorshowinjuredpet.get(position).getName());
        tvmobile.setText("USER'S MOBILE: "+arrayListdoctorshowinjuredpet.get(position).getMobile());
        tvemail.setText("USER'S EMAIL: "+arrayListdoctorshowinjuredpet.get(position).getEmail());
        tvanitype.setText("ANIMAL TYPE: "+arrayListdoctorshowinjuredpet.get(position).getAnimaltype());
        tvloc.setText("LOCATION: "+arrayListdoctorshowinjuredpet.get(position).getLocation());
        return view;
    }
}
