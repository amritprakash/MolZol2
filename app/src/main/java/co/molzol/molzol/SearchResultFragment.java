package co.molzol.molzol;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import co.molzol.model.flipkart.ProductInfoList;
import co.molzol.service.Flipkart;
import co.molzol.util.NetworkUtil;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchResultFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchResultFragment extends Fragment {

    private ProgressBar spinner;
    private RecyclerView searchRecyclerView;
    private SearchResultAdapter searchResultAdapter;
    private ProductInfoList productInfoList = new ProductInfoList();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "query";
    private static final String ARG_PARAM2 = "count";

    // TODO: Rename and change types of parameters
    private String query;
    private int count;

    //private OnFragmentInteractionListener mListener;

    public SearchResultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param query Parameter 1.
     * @param count Parameter 2.
     * @return A new instance of fragment SearchResultFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchResultFragment newInstance(String query, int count, ProgressBar spinner) {
        SearchResultFragment fragment = new SearchResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, query);
        args.putInt(ARG_PARAM2, count);
        fragment.setArguments(args);
        fragment.spinner = spinner;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            query = getArguments().getString(ARG_PARAM1);
            count = getArguments().getInt(ARG_PARAM2);
        }

        if(!NetworkUtil.isNetworkAvailable(this.getActivity())){
            Toast.makeText(this.getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
        new AsyncRest().execute();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View layout = inflater.inflate(R.layout.fragment_search_result, container, false);
        searchRecyclerView = (RecyclerView) layout.findViewById(R.id.searchList);

        searchResultAdapter = new SearchResultAdapter(new SearchResultAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String s) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(s));
                startActivity(intent);
            }
        });
        searchRecyclerView.setAdapter(searchResultAdapter);

        LinearLayoutManager layoutManager= new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        searchRecyclerView.setLayoutManager(layoutManager);

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

    private class AsyncRest extends AsyncTask<Void, Void, ProductInfoList> {

        @Override
        protected ProductInfoList doInBackground(Void... params) {

            ProductInfoList productInfoList = Flipkart.searchProducts(query, count);

            return productInfoList;
        }

        protected void onPostExecute(ProductInfoList productInfoList) {

            searchResultAdapter.setProductInfoList(productInfoList);
            spinner.setVisibility(View.GONE);
        }

    }
}
