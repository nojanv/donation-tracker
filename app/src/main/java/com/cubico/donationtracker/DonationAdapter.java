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

import com.cubico.donationtracker.POJOs.DonationItem;
import com.cubico.donationtracker.POJOs.Location;

import java.util.ArrayList;

public class DonationAdapter extends ArrayAdapter<DonationItem> implements View.OnClickListener{

    private ArrayList<DonationItem> donations;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
    }

    public DonationAdapter(ArrayList<DonationItem> donations, Context context) {
        super(context, R.layout.list_item_donation, donations);
        this.donations = donations;
        this.mContext = context;

    }

    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object = getItem(position);
        DonationItem donation = (DonationItem) object;

        Toast.makeText(mContext, donation.getName(), Toast.LENGTH_LONG).show();
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DonationItem donation = (DonationItem) getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item_donation, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.donation_name);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
//        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtName.setText(donation.getName());
        // Return the completed view to render on screen
        return convertView;
    }
}
