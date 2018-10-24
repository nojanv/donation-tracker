package com.cubico.donationtracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DonationsFragment.DonationAddListener} interface
 * to handle interaction events.
 * Use the {@link DonationsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DonationsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "donations";
    static final int CREATE_DONATION_REQUEST = 1;

    // TODO: Rename and change types of parameters
    public List<DonationItem> donations;

    private DonationAddListener mListener;

    public DonationsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param donations Parameter 1.
     * @return A new instance of fragment DonationsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DonationsFragment newInstance(ArrayList<DonationItem> donations) {
        DonationsFragment fragment = new DonationsFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM1, donations);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            donations = getArguments().getParcelableArrayList(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_donations, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        TextView donationMessage = view.findViewById(R.id.donations);
        Button button = view.findViewById(R.id.faddDonation);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddDonationActivity.class);
                startActivityForResult(intent, CREATE_DONATION_REQUEST);
            }
        });
        String message = "";
        for (DonationItem item : donations) {
            message += String.format("%s: %s, %s \n", item.getName(), item.getQuantity(), item.getItemType().getName());
        }
        donationMessage.setText(message);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_DONATION_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle bundle = data.getExtras();
                DonationItem item = bundle.getParcelable("donation");
                Log.d("debug", String.format("%s: %s, %s", item.getName(), item.getQuantity(), item.getItemType().getName()));
                donationAdded(item);
//                TextView itemText = getView().findViewById(R.id.donationText);
//                itemText.setText(String.format("%s, %s, %s", item.getName(), item.getQuantity(), item.getItemType().getName()));
            }
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void donationAdded(DonationItem item) {
        if (mListener != null) {
            mListener.onDonationAdd(item);
        }
    }

    public void updateDonations(ArrayList<DonationItem> donations) {
        TextView donationMessage = getView().findViewById(R.id.donations);
        String message = "";
        for (DonationItem item : donations) {
            message += String.format("%s: %s, %s \n", item.getName(), item.getQuantity(), item.getItemType().getName());
        }
        donationMessage.setText(message);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DonationAddListener) {
            mListener = (DonationAddListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface DonationAddListener {
        // TODO: Update argument type and name
        void onDonationAdd(DonationItem item);
    }
}
