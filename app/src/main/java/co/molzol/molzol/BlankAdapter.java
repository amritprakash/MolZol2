package co.molzol.molzol;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class BlankAdapter extends PagerAdapter {

    int[] mResources = {
            R.drawable.molzol_banner,
            /*R.drawable.cilory,
            R.drawable.flipkart1,
            R.drawable.cilory,
            R.drawable.amazon,
            R.drawable.flipkart1*/
    };
    Context mContext;
    LayoutInflater mLayoutInflater;

    public BlankAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.blank_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.blankImageView);
        imageView.setImageResource(mResources[position]);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}