package com.planetsystems.tela;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.planetsystems.tela.Models.SystemUsers;

import java.util.ArrayList;


public class TaskAdapter extends ArrayAdapter<SystemUsers> {
    ArrayList<SystemUsers> proList;
    LayoutInflater vi;
    int Resource;

    ViewHolder holder;

    public TaskAdapter(Context context, int resource, ArrayList<SystemUsers> objects) {
        super(context, resource, objects);
        vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        proList = objects;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // convert view = design
        View v = convertView;
        if (v == null) {
            holder = new ViewHolder();

            v = vi.inflate(Resource, null);
            holder.itemName = (TextView) v.findViewById(R.id.item_name);

            //holder.imageView=(ImageView)v.findViewById(R.id.imgSync);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.itemName.setText(proList.get(position).getSchoolName());

//        int sync_status = proList.get(position).getSync_status();
//        if (sync_status == DbContact.SYNC_STATUS_OK){
//
//            holder.imageView.setImageResource(R.drawable.ic_action_done);
//
//        }else {
//
//            holder.imageView.setImageResource(R.drawable.ic_action_sync);
//
//
//        }

        return v;

    }

    static class ViewHolder {
        public TextView itemName;
        public ImageView imageView;

        }

}


