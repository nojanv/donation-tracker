package com.cubico.donationtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.cubico.donationtracker.POJOs.Location;

import java.util.List;

class LocationAdapter extends ArrayAdapter<Location> implements View.OnClickListener{

    private final Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
    }

    public LocationAdapter(List<Location> data, Context context) {
        super(context, R.layout.list_item_destination, data);
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object = getItem(position);
        Location loc= (Location) object;

        Toast.makeText(mContext, loc.toString(), Toast.LENGTH_LONG).show();
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        View convertView1 = convertView;
        Location location = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView1 == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView1 = inflater.inflate(R.layout.list_item_destination, parent, false);
            viewHolder.txtName = (TextView) convertView1.findViewById(R.id.name);

            result= convertView1;

            convertView1.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView1.getTag();
            result= convertView1;
        }

        Animation animation = AnimationUtils.loadAnimation(
                mContext,
                (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
//        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtName.setText(location.getName());
        // Return the completed view to render on screen
        return convertView1;
    }
}
