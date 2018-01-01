package co.molzol.molzol;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

//import android.support.v4.app.DialogFragment;

/**
 *
 * Created by hp on 01-06-2017.
 */

public class RedeemDialogFragment extends DialogFragment {

    Button redeem_btn;
    TextView redeem_login_txt;
    TextView redeem_mols;
    Spinner dialog_redeem_cnf_spn1;
    Spinner dialog_redeem_cnf_spn2;
    TextView dialog_redeem_cnf_txt1;
    TextView dialog_redeem_cnf_txt2;
    TextView dialog_redeem_cnf_txt3;
    TextView dialog_redeem_500_txt;

    String storeName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialog_redeem, container, false);

        redeem_btn = view.findViewById(R.id.dialog_redeem_redeem_btn);
        redeem_login_txt = view.findViewById(R.id.dialog_redeem_login_txt);
        redeem_mols = view.findViewById(R.id.dialog_redeem_mols);
        dialog_redeem_500_txt = view.findViewById(R.id.dialog_redeem_500_txt);

        dialog_redeem_cnf_spn1 = view.findViewById(R.id.dialog_redeem_cnf_spn1);
        dialog_redeem_cnf_spn2 = view.findViewById(R.id.dialog_redeem_cnf_spn2);
        dialog_redeem_cnf_txt1 = view.findViewById(R.id.dialog_redeem_cnf_txt1);
        dialog_redeem_cnf_txt2 = view.findViewById(R.id.dialog_redeem_cnf_txt2);
        dialog_redeem_cnf_txt3 = view.findViewById(R.id.dialog_redeem_cnf_txt3);

        List<String> abc = new ArrayList<String>();
        String[] mols_items = {"500", "1000", "5000"};
        String[] giftVoucher_items = {"Flipkart Voucher", "Amazon Voucher"};
        ArrayAdapter<String> mols_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, mols_items);
        ArrayAdapter<String> giftVoucher_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, giftVoucher_items);
        dialog_redeem_cnf_spn1.setAdapter(mols_adapter);
        dialog_redeem_cnf_spn2.setAdapter(giftVoucher_adapter);


        Bundle bundle = getArguments();
        boolean loggedIn = bundle.getBoolean("loggedIn");
        int mols = bundle.getInt("mols");
        if(!loggedIn){
            redeem_mols.setText("...");
            redeem_login_txt.setVisibility(View.VISIBLE);

            dialog_redeem_500_txt.setVisibility(View.INVISIBLE);
            redeem_btn.setVisibility(View.INVISIBLE);
            dialog_redeem_cnf_spn1.setVisibility(View.INVISIBLE);
            dialog_redeem_cnf_spn2.setVisibility(View.INVISIBLE);
            dialog_redeem_cnf_txt1.setVisibility(View.INVISIBLE);
            dialog_redeem_cnf_txt2.setVisibility(View.INVISIBLE);
            dialog_redeem_cnf_txt3.setVisibility(View.INVISIBLE);
        }else{

            if(mols<500){
                redeem_mols.setText(""+mols);
                dialog_redeem_500_txt.setVisibility(View.VISIBLE);

                redeem_login_txt.setVisibility(View.INVISIBLE);
                redeem_btn.setVisibility(View.INVISIBLE);
                dialog_redeem_cnf_spn1.setVisibility(View.INVISIBLE);
                dialog_redeem_cnf_spn2.setVisibility(View.INVISIBLE);
                dialog_redeem_cnf_txt1.setVisibility(View.INVISIBLE);
                dialog_redeem_cnf_txt2.setVisibility(View.INVISIBLE);
                dialog_redeem_cnf_txt3.setVisibility(View.INVISIBLE);
            }else{
                redeem_mols.setText(""+mols);
                redeem_btn.setVisibility(View.VISIBLE);
                dialog_redeem_cnf_spn1.setVisibility(View.VISIBLE);
                dialog_redeem_cnf_spn2.setVisibility(View.VISIBLE);
                dialog_redeem_cnf_txt1.setVisibility(View.VISIBLE);
                dialog_redeem_cnf_txt2.setVisibility(View.VISIBLE);
                dialog_redeem_cnf_txt3.setVisibility(View.VISIBLE);

                redeem_login_txt.setVisibility(View.INVISIBLE);
                dialog_redeem_500_txt.setVisibility(View.INVISIBLE);
            }

        }


        redeem_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
                //Todo
                redeem(100);
            }
        });

        ////TODO logged in - Get Mols from database if it's
        //less than 500 - Minimum redeemable Mol is 500.

        //else present option to select flipkart or amazon gift vouchar
        //then take the request to server \\ on successfull tell him that
        //we are processing your request you will get your amazon/flipkart coupon worth RS. 500
        //within 48 hours.

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    private void redeem(int mols){
        //TODO goto backend and take request;
    }

    /*@Override
    public void onDetach() {
        super.onDetach();
        //updateLevelMilestoneMainView();
    }*/

}

