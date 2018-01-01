package co.molzol.molzol;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class StoresActivity extends AppCompatActivity {

    StoreDialogFragment storeDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        storeDialogFragment = new StoreDialogFragment();
    }

    public void goToStore(View v){

        int id = v.getId();

        if(id == R.id.store_flipkart){
            addStoreDialogFragment(R.drawable.flipkart1, "Flipkart", "#UpTo5%CashBack", "abcd2", "abcd3");
        }else if(id == R.id.store_amazon){
            addStoreDialogFragment(R.drawable.amazon, "Amazon", "#UpTo5%CashBack", "abcd2", "abcd3");
        }else if(id == R.id.store_snapdeal){
            addStoreDialogFragment(R.drawable.snapdeal, "Snapdeal", "#ComingSoon", "abcd2", "abcd3");
        }else if(id == R.id.store_infibeam){
            addStoreDialogFragment(R.drawable.infibeam2, "Infibeam", "#ComingSoon", "abcd2", "abcd3");
        }else if(id == R.id.store_ebay){
            addStoreDialogFragment(R.drawable.ebay2, "Ebay", "#ComingSoon", "abcd2", "abcd3");
        }else if(id == R.id.store_jabong){
            addStoreDialogFragment(R.drawable.jabong1, "Jabong", "#ComingSoon", "abcd2", "abcd3");
        }else if(id == R.id.store_myntra){
            addStoreDialogFragment(R.drawable.myntra1, "Myntra", "#ComingSoon", "abcd2", "abcd3");
        }else if(id == R.id.store_shopclues){
            addStoreDialogFragment(R.drawable.shopclues, "Shopclues", "#ComingSoon", "abcd2", "abcd3");
        }else if(id == R.id.store_paytm){
            addStoreDialogFragment(R.drawable.paytm, "Paytm", "#ComingSoon", "abcd2", "abcd3");
        }else if(id == R.id.store_croma){
            addStoreDialogFragment(R.drawable.croma, "Croma", "#ComingSoon", "abcd2", "abcd3");
        }else if(id == R.id.store_indiatimes){
            addStoreDialogFragment(R.drawable.indtimesshopping, "Indiatimes", "#ComingSoon", "abcd2", "abcd3");
        }else if(id == R.id.store_trending){
            addStoreDialogFragment(R.drawable.trendin, "Trending", "#ComingSoon", "abcd2", "abcd3");
        }else if(id == R.id.store_shop19){
            addStoreDialogFragment(R.drawable.shop19, "Shop19", "#ComingSoon", "abcd2", "abcd3");
        }else if(id == R.id.store_homeshop){
            addStoreDialogFragment(R.drawable.homeshop181, "Homeshop", "#ComingSoon", "abcd2", "abcd3");
        }else if(id == R.id.store_cilory){
            addStoreDialogFragment(R.drawable.cilory, "Cilory", "#ComingSoon", "abcd2", "abcd3");
        }else{
            //TODO
        }

    }

    public void addStoreDialogFragment(int storeImg, String storeName, String text1, String text2, String text3){

        Bundle args = new Bundle();
        args.putInt("storeImage", storeImg);
        args.putString("storeName", storeName);
        args.putString("text1", text1);
        args.putString("text2", text2);
        args.putString("text3", text3);
        //storeDialogFragment = new StoreDialogFragment();
        storeDialogFragment.setArguments(args);
        storeDialogFragment.show(getFragmentManager(), "Store Dialog Fragment");

    }
}
