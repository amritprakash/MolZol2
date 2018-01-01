package co.molzol.molzol;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import co.molzol.model.flipkart.DealsOfTheDay;
import co.molzol.service.Flipkart;
import co.molzol.util.NetworkChangeReceiver;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DealsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DealsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DealsFragment extends Fragment implements NetworkChangeReceiver.NetworkChangeReceiverListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private RecyclerView dealsRecyclerView;
    private DealsAdapter dealsAdapter;
    private DealsOfTheDay deals = new DealsOfTheDay();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private boolean dealsExecuted = false;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private NetworkChangeReceiver networkChangeReceiver;



    //private OnFragmentInteractionListener mListener;

    public DealsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DealsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DealsFragment newInstance(String param1, String param2) {
        DealsFragment fragment = new DealsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        networkChangeReceiver = new NetworkChangeReceiver();
        networkChangeReceiver.addListener(this);
        this.getActivity().registerReceiver(networkChangeReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));

        new AsyncRest().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_deals, container, false);
        dealsRecyclerView = (RecyclerView) layout.findViewById(R.id.dealsList);

        dealsAdapter = new DealsAdapter(new DealsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String s) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(s));
                startActivity(intent);
            }
        });
        dealsRecyclerView.setAdapter(dealsAdapter);

        LinearLayoutManager layoutManager= new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        dealsRecyclerView.setLayoutManager(layoutManager);

        dealsRecyclerView.setNestedScrollingEnabled(false);

        return layout;

    }

    /*// TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    @Override
    public void networkAvailable() {
        if(!dealsExecuted){
            new AsyncRest().execute();
            dealsExecuted = true;
        }

    }

    @Override
    public void networkUnavailable() {
        Toast.makeText(this.getActivity(), "No Internet Connection", Toast.LENGTH_LONG).show();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public class AsyncRest extends AsyncTask<Void, Void, DealsOfTheDay> {

        @Override
        protected DealsOfTheDay doInBackground(Void... params) {

            DealsOfTheDay deals = Flipkart.fetchDeals();

            return deals;
        }

        protected void onPostExecute(DealsOfTheDay deals) {

            dealsAdapter.setDeals(deals);

        }

    }

}