package com.exple.stray_care;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
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

public class userdonate extends AppCompatActivity {
    ListView listView;
    AdaptertuserdonateNgodatadonate adapter;

    String url = "https://stray-care.000webhostapp.com/userngodonatedata.php";
    public static ArrayList<userdonateNgodatadonate> userdonateNgodata = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdonate);
        listView = findViewById(R.id.myListView);
        ActionBar t=getSupportActionBar();
        t.setTitle("NGO NEAR YOU");
        adapter = new AdaptertuserdonateNgodatadonate(this, userdonateNgodata);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = {"DONATE","SEND ANANDOMED","REPORT FOR CREMATION"};
                builder.setTitle("......");
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){

                            case 0:

                                startActivity(new Intent(getApplicationContext(),USERDONATENGODetail.class)
                                        .putExtra("position",position));

                                break;
                            case 1:

                                startActivity(new Intent(getApplicationContext(), usersendabandomedngodetails.class)
                                        .putExtra("position", position));

                                break;
                            case 2:

                                startActivity(new Intent(getApplicationContext(),usersendforcremationngodetails.class)
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
                    userdonateNgodata.clear();
                        try{

                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if(sucess.equals("1")){


                                for(int i=0;i<jsonArray.length();i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String id = object.getString("ngo_id");
                                    String name = object.getString("name");
                                    String email = object.getString("email");
                                    String mobile= object.getString("mobile");
                                    String address = object.getString("address");

                                    userdonateNgodatadonate e = new userdonateNgodatadonate(id,name,email,mobile,address);
                                    userdonateNgodata.add(e);
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
                Toast.makeText(userdonate.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);




    }

    }