package com.cubico.donationtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.cubico.donationtracker.POJOs.DonationItem;

public class DonationAdapter extends BaseAdapter implements View.OnClickListener, Filterable {

    private ArrayList<DonationItem> donations;
    private List<DonationItem> donationsCopy;
    private Context mContext;
    private ValueFilter valueFilter;

    private TextView msg;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
    }

    public DonationAdapter(ArrayList<DonationItem> donations, Context context) {
        this.donations = donations;
        donationsCopy = donations;
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
    public int getCount() {
        return donations.size();
    }

    @Override
    public Object getItem(int position) {
        return donations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DonationItem donation = (DonationItem) getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(mContext);
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

    public ValueFilter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    public class ValueFilter extends Filter {
        private boolean name = true;
        private boolean empty = false;

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList<DonationItem> filterList = new ArrayList<DonationItem>();
                constraint = constraint.toString().toUpperCase();
                empty = false;
                for (int i = 0; i < donationsCopy.size(); i++) {
                    DonationItem item = donationsCopy.get(i);
                    if ((name && item.getName().toUpperCase().contains(constraint)) || (!name && item.getItemType().toString().toUpperCase().contains(constraint))) {



                        DonationItem curr = donationsCopy.get(i);
                        System.out.println(curr.getName());

                        DonationItem donation = new DonationItem(curr.getName(),
                                curr.getTimeStamp(),
                                curr.getLocation(),
                                curr.getFullDescription(),
                                curr.getValue(),
                                curr.getItemType());

                        filterList.add(donation);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
                if (filterList.size() == 0) {
                    empty = true;
                }
            } else {
                results.count = donationsCopy.size();
                results.values = donationsCopy;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            donations = (ArrayList<DonationItem>) results.values;
            notifyDataSetChanged();

        }

        public void setMode(boolean nameMode) {
            name = nameMode;
        }

        public boolean isEmpty() { return empty; }

    }
}