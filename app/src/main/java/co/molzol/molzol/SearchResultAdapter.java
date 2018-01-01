package co.molzol.molzol;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Map;

import co.molzol.model.flipkart.Product;
import co.molzol.model.flipkart.ProductInfoList;

/**
 * Created by hp on 13-02-2016.
 */
public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.SearchViewHolder> {

    private LayoutInflater inflater;
    private ProductInfoList productInfoList = new ProductInfoList();
    private OnItemClickListener onItemClickListener;

    public SearchResultAdapter(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public void setProductInfoList(ProductInfoList productInfoList) {
        this.productInfoList = productInfoList;
        notifyDataSetChanged();
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.search_item, parent, false);
        SearchViewHolder searchViewHolder = new SearchViewHolder(view, onItemClickListener);
        return searchViewHolder;
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {

        try{
            Product product = productInfoList.getProductList().get(position);

            holder.prodTitle.setText(product.getProductBaseInfoV1().getTitle());
            holder.prodMRP.setText(product.getProductBaseInfoV1().getMaximumRetailPrice().getAmount().toString());
            holder.prodCurrPrice.setText(product.getProductBaseInfoV1().getFlipkartSellingPrice().getAmount().toString());
            holder.prodUrl = product.getProductBaseInfoV1().getProductUrl();
            holder.prodStore.setImageResource(R.drawable.flipkart1);
            Map<String, String> images = product.getProductBaseInfoV1().getImageUrls();

            String prodImage = null;
            if(images.containsKey("200x200")){
                prodImage = images.get("200x200");
            }else if(images.containsKey("400x400")){
                prodImage = images.get("400x400");
            }else if(images.containsKey("800x800")){
                prodImage = images.get("800x800");
            }else if(images.containsKey("unknown")){
                prodImage = images.get("unknown");
            }else {
                //TODO
            }
            Uri prodImageUri = Uri.parse(prodImage);
            Context context = holder.prodImage.getContext();
            Picasso.with(context).load(prodImageUri).fit().centerInside().into(holder.prodImage);
        }catch(Exception e){

        }

    }

    @Override
    public int getItemCount() {
        try {
            return productInfoList.getProductList().size();
        } catch(Exception e) {
            return 0;
        }
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView prodImage;
        public ImageView prodStore;
        public TextView prodTitle;
        public TextView prodMRP;
        public TextView prodCurrPrice;
        public String prodUrl;
        private OnItemClickListener onItemClickListener;

        public SearchViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            prodImage = (ImageView) itemView.findViewById(R.id.prodImage);
            prodStore = (ImageView) itemView.findViewById(R.id.prodStore);
            prodTitle = (TextView) itemView.findViewById(R.id.prodTitle);
            prodMRP = (TextView) itemView.findViewById(R.id.prodMRP);
            prodMRP.setPaintFlags(prodMRP.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            prodCurrPrice = (TextView) itemView.findViewById(R.id.prodCurrPrice);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.onItemClickListener.onItemClick(prodUrl);

        }
    }

    public interface OnItemClickListener
    {
        void onItemClick(String s);
    }

}
