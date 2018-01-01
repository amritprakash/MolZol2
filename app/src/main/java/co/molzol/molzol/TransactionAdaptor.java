package co.molzol.molzol;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import co.molzol.util.Constants;
import co.molzol.entity.UserTransaction;

/**
 *
 * Created by hp on 13-02-2016.
 */
public class TransactionAdaptor extends RecyclerView.Adapter<TransactionAdaptor.TransactionViewHolder> {

    private LayoutInflater inflater;
    private List<UserTransaction> userTransactionList = new ArrayList<>();

    public void setUserTransactionList(List<UserTransaction> userTransactionList) {
        this.userTransactionList = userTransactionList;
        notifyDataSetChanged();
    }

    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.transaction_item, parent, false);
        TransactionViewHolder transactionViewHolder = new TransactionViewHolder(view);
        return transactionViewHolder;
    }

    @Override
    public void onBindViewHolder(TransactionViewHolder holder, int position) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        try{
            UserTransaction userTransaction = userTransactionList.get(position);

            holder.transactionDate.setText(sdf.format(userTransaction.getCreatedDate()));
            if(userTransaction.getTranMode().equals(Constants.TRAN_MODE.EARN)){
                holder.earnRedeemMode.setText(userTransaction.getEarnMode().name());
                holder.transactionAmount.setText("+ "+Constants.rupeeSign+userTransaction.getTranAmount());
            }else if(userTransaction.getTranMode().equals(Constants.TRAN_MODE.REDEEM)){
                holder.earnRedeemMode.setText(userTransaction.getRedeemMode().name());
                holder.transactionAmount.setText("- "+Constants.rupeeSign+userTransaction.getTranAmount());
            }
        }catch(Exception e){
            System.out.println(e);
        }

    }

    @Override
    public int getItemCount() {
        try {
            return userTransactionList.size();
        } catch(Exception e) {
            return 0;
        }
    }

    public static class TransactionViewHolder extends RecyclerView.ViewHolder {
        public TextView transactionAmount;
        public TextView transactionDate;
        public TextView earnRedeemMode;

        public TransactionViewHolder(View tranView) {
            super(tranView);
            transactionAmount = tranView.findViewById(R.id.transactionAmount);
            transactionDate = tranView.findViewById(R.id.transactionDate);
            earnRedeemMode = tranView.findViewById(R.id.earnRedeemMode);
        }

    }

}