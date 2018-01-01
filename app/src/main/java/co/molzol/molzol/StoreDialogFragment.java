package co.molzol.molzol;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static co.molzol.util.Constants.store;

//import android.support.v4.app.DialogFragment;

/**
 * Created by hp on 01-06-2017.
 */

public class StoreDialogFragment extends DialogFragment {

    TextView dialog_store_name;
    TextView dialog_store_text1;
    TextView dialog_store_text2;
    TextView dialog_store_text3;
    ImageView dialog_store_img;

    String storeName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialog_store, container, false);

        Button visitStore = view.findViewById(R.id.visitStore);
        dialog_store_name = view.findViewById(R.id.dialog_store_name);
        dialog_store_text1 = view.findViewById(R.id.dialog_redeem_text1);
        dialog_store_text2 = view.findViewById(R.id.dialog_redeem_text2);
        dialog_store_text3 = view.findViewById(R.id.dialog_redeem_text3);
        dialog_store_img = view.findViewById(R.id.dialog_store_img);



        Bundle bundle = getArguments();
        int storeImg = bundle.getInt("storeImage");
        storeName = bundle.getString("storeName");
        String text1 = bundle.getString("text1");
        String text2 = bundle.getString("text2");
        String text3 = bundle.getString("text3");
        dialog_store_img.setImageResource(storeImg);
        dialog_store_name.setText(storeName);
        dialog_store_text1.setText(text1);
        dialog_store_text2.setText(text2);
        dialog_store_text3.setText(text3);

        visitStore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
                goToStore();
            }
        });

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

    private void goToStore(){

        String url = store.get(storeName).getStoreUrl();;
        if (null != url) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }
    }

    /*@Override
    public void onDetach() {
        super.onDetach();
        //updateLevelMilestoneMainView();
    }*/

}

