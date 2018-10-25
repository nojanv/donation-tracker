package com.cubico.donationtracker.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cubico.donationtracker.AddDonationActivity;
import com.cubico.donationtracker.POJOs.AccountType;
import com.cubico.donationtracker.POJOs.DonationItem;
import com.cubico.donationtracker.R;

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
    private static final String DONATION_ARG = "donations";
    private static final String ACCOUNT_ARG = "accountType";
    static final int CREATE_DONATION_REQUEST = 1;

    public List<DonationItem> donations;
    public AccountType accountType;

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
    public static DonationsFragment newInstance(ArrayList<DonationItem> donations, AccountType accountType) {
        DonationsFragment fragment = new DonationsFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(DONATION_ARG, donations);
        args.putParcelable(ACCOUNT_ARG, accountType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            donations = getArguments().getParcelableArrayList(DONATION_ARG);
            accountType = getArguments().getParcelable(ACCOUNT_ARG);
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
        Button button = view.findViewById(R.id.addDonation);
        if (accountType.equals(AccountType.LOCATION_EMPLOYEE) || accountType.equals(AccountType.ADMIN)) {
            button.setVisibility(View.VISIBLE);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddDonationActivity.class);
                startActivityForResult(intent, CREATE_DONATION_REQUEST);
            }
        });
        String message = donations.size() > 0 ? "" : "No donations yet!";
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
                donationAdded(item);
            }
        }
    }

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
        void onDonationAdd(DonationItem item);
    }
}
