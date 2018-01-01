package co.molzol.molzol;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import co.molzol.model.flipkart.Deal;
import co.molzol.model.flipkart.DealsOfTheDay;
import co.molzol.util.Constants;

/**
 *
 * Created by hp on 13-02-2016.
 */
public class DealsAdapter extends RecyclerView.Adapter<DealsAdapter.DealsViewHolder> {

    private LayoutInflater inflater;
    private DealsOfTheDay deals = new DealsOfTheDay();
    private OnItemClickListener onItemClickListener;

    public DealsAdapter(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public void setDeals(DealsOfTheDay deals) {
        this.deals = deals;
        notifyDataSetChanged();
    }

    @Override
    public DealsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.deal_item, parent, false);
        DealsViewHolder dealsViewHolder = new DealsViewHolder(view, onItemClickListener);
        return dealsViewHolder;
    }

    @Override
    public void onBindViewHolder(DealsViewHolder holder, int position) {

        Deal deal = deals.getDotdList().get(position);
        Context context = holder.dealImage.getContext();
        try{
            holder.dealTitle.setText(deal.getTitle());
            holder.dealDescription.setText(deal.getDescription());
            Uri imageUri = Uri.parse(deal.getImageUrls().get(1).getUrl());
            Picasso.with(context).load(imageUri).fit().centerInside().into(holder.dealImage);
            holder.dealUrl = deal.getUrl();
        }catch(Exception e){
            ColorDrawable colorAccent = new ColorDrawable(Color.parseColor("#ff4081"));
            /*holder.dealTitle.setBackground(colorAccent);
            holder.dealDescription.setBackground(colorAccent);*/
            //holder.dealImage.setBackground(colorAccent);
            holder.dealUrl = Constants.store.get("Flipkart").getStoreUrl();
        }

    }

    @Override
    public int getItemCount() {
        return deals.getDotdList().size();
    }

    public static class DealsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView dealImage;
        private TextView dealTitle;
        private TextView dealDescription;
        private String dealUrl;
        private OnItemClickListener onItemClickListener;

        public DealsViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            dealImage = (ImageView) itemView.findViewById(R.id.dealImage);
            dealTitle = (TextView) itemView.findViewById(R.id.dealTitle);
            dealDescription = (TextView) itemView.findViewById(R.id.dealDescription);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.onItemClickListener.onItemClick(dealUrl);

        }
    }

    public interface OnItemClickListener
    {
        void onItemClick(String s);
    }

}