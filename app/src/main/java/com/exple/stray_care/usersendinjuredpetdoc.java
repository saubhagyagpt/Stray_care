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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.AdapterView;
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
import java.util.List;

public class usersendinjuredpetdoc extends AppCompatActivity {
    ListView listView;
    Adaptertusersendinjured adapter;

    String url = "https://stray-care.000webhostapp.com/usersendinjureddata.php";
    public static ArrayList<usersendinjuredpetdata> usersendinjureddocdata = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersendinjuredpetdoc);
        listView = findViewById(R.id.myListViewusersinjured);
        ActionBar t=getSupportActionBar();
        t.setTitle("DOCTOR'S NEAR YOU");
        adapter = new Adaptertusersendinjured(this,usersendinjureddocdata);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = {"SEND INJURED"};
                builder.setTitle(".......");
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i) {
                            case 0:

                                startActivity(new Intent(getApplicationContext(), usersendinjuredpettodoctordetails.class)
                                        .putExtra("position", position));

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
                        usersendinjureddocdata.clear();
                        try{

                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if(sucess.equals("1")){


                                for(int i=0;i<jsonArray.length();i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String id = object.getString("id");
                                    String name = object.getString("name");
                                    String email = object.getString("email");
                                    String mobile=object.getString("mobile");
                              String address  = object.getString("address");

                                    usersendinjuredpetdata e = new  usersendinjuredpetdata(id,name,email,mobile,address);
                                    usersendinjureddocdata.add(e);
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
                Toast.makeText(usersendinjuredpetdoc.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);




    }

}
class usersendinjuredpetdata {

    private String id,name, email,mobile,address;

    public usersendinjuredpetdata() {
    }

    public usersendinjuredpetdata(String id,String name, String email,String mobile,String address) {
         this.id=id;
        this.name = name;
        this.email = email;
        this.mobile= mobile;
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
        this.mobile= mobile;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address= address;
    }

}

class Adaptertusersendinjured extends ArrayAdapter<usersendinjuredpetdata> {
    Context context;
    List<usersendinjuredpetdata> arrayListusersendinjureddata;
    public Adaptertusersendinjured(@NonNull Context context, List<usersendinjuredpetdata> arrayListusersendinjureddata) {
        super(context,R.layout.custom_list_itemsendinjuredpet,arrayListusersendinjureddata);
        this.context=context;
        this.arrayListusersendinjureddata=arrayListusersendinjureddata;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_itemsendinjuredpet,null,true);
        TextView tvId=view.findViewById(R.id.txt_id);
        TextView tvname=view.findViewById(R.id.txt_name);
        TextView tvmobile=view.findViewById(R.id.txt_mobile);
        tvId.setText(arrayListusersendinjureddata.get(position).getId());
        tvname.setText(arrayListusersendinjureddata.get(position).getName());
        tvmobile.setText(arrayListusersendinjureddata.get(position).getMobile());
        return view;
    }
}
