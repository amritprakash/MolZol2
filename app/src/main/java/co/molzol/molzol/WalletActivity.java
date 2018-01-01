package co.molzol.molzol;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import co.molzol.entity.UserDetail;

import static co.molzol.util.Constants.redeemThreshold;
import static co.molzol.util.Constants.rupeeSign;

public class WalletActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private RedeemNotSufficientBalanceFragment redeemNotSufficientBalanceFrgment;
    private Button redeemNowBtn;
    private UserDetail userDetail;
    private TextView walletVTotalBalanceTxt;
    private TextView walletVCnfBalanceTxt;
    private TextView walletVPendBalanceTxt;
    private RecyclerView transactionRecyclerView;
    private TransactionAdaptor transactionAdaptor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        walletVTotalBalanceTxt = findViewById(R.id.walletVTotalBalanceTxt);
        walletVCnfBalanceTxt = findViewById(R.id.walletVCnfBalanceTxt);
        walletVPendBalanceTxt = findViewById(R.id.walletVPendBalanceTxt);

        fragmentManager = getFragmentManager();
        redeemNotSufficientBalanceFrgment = new RedeemNotSufficientBalanceFragment();

        userDetail = (UserDetail) getIntent().getSerializableExtra("userDetail");
        if(null != userDetail){
            walletVTotalBalanceTxt.setText(rupeeSign + userDetail.getUserAccount().getTotalBalance());
            walletVCnfBalanceTxt.setText(rupeeSign + userDetail.getUserAccount().getConfirmedBalance());
            walletVPendBalanceTxt.setText(rupeeSign + userDetail.getUserAccount().getPendingBalance());
        }

        transactionRecyclerView = findViewById(R.id.transactionList);

        transactionAdaptor = new TransactionAdaptor();
        transactionRecyclerView.setAdapter(transactionAdaptor);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        transactionRecyclerView.setLayoutManager(layoutManager);
        transactionAdaptor.setUserTransactionList(new ArrayList<>(userDetail.getUserAccount().getUserTransactions().values()));

    }

    public void redeemNow(View v){

        int cnfBalance = userDetail.getUserAccount().getConfirmedBalance();
        if(cnfBalance >= redeemThreshold){
            //TODO add redeem fragment

        }else{
            addReedemNotSufficientBalanceFragment();
        }


    }

    public void addReedemNotSufficientBalanceFragment(){
        fragmentManager.beginTransaction()
                //.addToBackStack("levelFragment")
                .replace(R.id.ConfirmedCashFrame, redeemNotSufficientBalanceFrgment)
                .commit();
    }

    public void removeNotSuffiBalFrag(View v){
        removeReedemNotSufficientBalanceFragment();
    }

    public void removeReedemNotSufficientBalanceFragment(){
        if(fragmentManager.findFragmentById(R.id.ConfirmedCashFrame) != null){
            fragmentManager.beginTransaction()
                    .remove(redeemNotSufficientBalanceFrgment)
                    .commit();
            //fragmentManager.popBackStack();
        }
    }
}
