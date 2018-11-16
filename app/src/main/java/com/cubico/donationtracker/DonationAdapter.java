package com.cubico.donationtracker;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.cubico.donationtracker.POJOs.DonationItem;

/**
 * Adapter for donation list view
 */
public class DonationAdapter extends BaseAdapter implements View.OnClickListener, Filterable {

    private List<DonationItem> donations;
    private final List<DonationItem> donationsCopy;
    private final Context mContext;
    private ValueFilter valueFilter;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
    }

    /**
     * Public adapter constructor for donation
     * @param donations is a list of donation items
     * @param context in which method is called
     */
    public DonationAdapter(List<DonationItem> donations, Context context) {
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
        View convertView1 = convertView;
        DonationItem donation = (DonationItem) getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView1 == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView1 = inflater.inflate(R.layout.list_item_donation, parent, false);
            viewHolder.txtName = convertView1.findViewById(R.id.donation_name);

            convertView1.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView1.getTag();
        }

        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
//        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtName.setText(donation.getName());
        // Return the completed view to render on screen
        return convertView1;
    }

    @Override
    public ValueFilter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    public class ValueFilter extends Filter {
        private boolean name = true;
        private boolean empty;

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            CharSequence constraint1 = constraint;
            FilterResults results = new FilterResults();

            if ((constraint1 != null) && (constraint1.length() > 0)) {
                Collection<DonationItem> filterList = new ArrayList<DonationItem>();
                constraint1 = constraint1.toString().toUpperCase();
                empty = false;
                for (int i = 0; i < donationsCopy.size(); i++) {
                    DonationItem item = donationsCopy.get(i);
                    if ((name && item.getName().toUpperCase().contains(constraint1)) ||
                            (!name && item.getItemType()
                                          .toString()
                                          .toUpperCase()
                                          .contains(constraint1))) {

                        DonationItem curr = donationsCopy.get(i);
                        Log.d("item matches", curr.getName());

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
                if (filterList.isEmpty()) {
                    empty = true;
                }
            } else {
                results.count = donationsCopy.size();
                results.values = donationsCopy;
            }
            return results;

        }

        @Override
        @SuppressWarnings("unchecked")
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            donations = (ArrayList<DonationItem>) results.values;
            notifyDataSetChanged();

        }

        /**
         * set mode function for donation adapter
         * @param nameMode that we want to set mode to
         */
        public void setMode(boolean nameMode) {
            name = nameMode;
        }

        /**
         * Returns if adapter is empty
         * @return boolean representation of empty
         */
        public boolean isEmpty() {
            return empty;
        }
    }
}