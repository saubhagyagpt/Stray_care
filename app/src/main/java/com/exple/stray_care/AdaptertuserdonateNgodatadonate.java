package com.exple.stray_care;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.*;
public class AdaptertuserdonateNgodatadonate extends ArrayAdapter<userdonateNgodatadonate> {
    Context context;
    List<userdonateNgodatadonate> arrayListngodata;
    public AdaptertuserdonateNgodatadonate(@NonNull Context context, List<userdonateNgodatadonate> arrayListngodata) {
        super(context,R.layout.custom_list_itemngodatadonate,arrayListngodata);
        this.context=context;
        this.arrayListngodata=arrayListngodata;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_itemngodatadonate,null,true);
       TextView tvId=view.findViewById(R.id.txt_id);
        TextView tvname=view.findViewById(R.id.txt_name);
        TextView tvmobile=view.findViewById(R.id.txt_mobile);
        tvId.setText(arrayListngodata.get(position).getId());
        tvname.setText(arrayListngodata.get(position).getName());
        tvmobile.setText(arrayListngodata.get(position).getMobile());
        return view;
    }
}
