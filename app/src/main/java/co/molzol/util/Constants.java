package co.molzol.util;

import java.util.HashMap;
import java.util.Map;

import co.molzol.entity.Store;

/**
 *
 * Created by hp on 23-12-2017.
 */

public class Constants {

    public enum TRAN_MODE {EARN, REDEEM}
    public enum TRAN_STATUS {CONFIRM, PENDING}
    public enum REDEEEM_MODE {FLIPKART_GIFT_VOUCHER, AMAZON_GIFT_VOUCHER}
    public enum EARN_MODE {SHOPPING_CASHBACK, SIGN_UP_BONUS, REFERRAL_BONUS}
    public enum GIFT_VOUCHER_NAME {FLIPKART, AMAZON}

    public static final String SEARCH_PRODUCT = "https://affiliate-api.flipkart.net/affiliate/1.0/search.json";
    public static final String FETCH_DEALS = "https://affiliate-api.flipkart.net/affiliate/offers/v1/dotd/json";

    public static final String rupeeSign = "₹";
    public static final int redeemThreshold = 500;
    public static final Map<String, Store> store;
    public static final String shareText = "#MolZolKaroPaiseBachao#Rs100SignUpBonus#GetConfirmedCashbackOnEveryPurchase#FindBestDealsOffersCoupons";
    public static final String playStoreUri = "http://play.google.com/store/apps/details?id=co.molzol.molzol";
    static {
        store = new HashMap<String, Store>();

        store.put("Flipkart", new Store("Flipkart", "http://dl.flipkart.com/dl/?affid=careeramr"));
        //store.put("Amazon", new Store("Amazon", "amzn://apps/android?tag=mol02-21"));
        store.put("Amazon", new Store("Amazon", "http://www.amazon.in/?tag=mol02-21"));
        store.put("Snapdeal", new Store("Snapdeal", "http://www.snapdeal.com/?utm_source=aff_prog&utm_campaign=afts&offer_id=17&aff_id=71441"));
        store.put("Infibeam", new Store("Infibeam", "http://www.infibeam.com/?track=caree"));
        store.put("Ebay", new Store("Ebay", "http://www.ebay.in"));
        store.put("Myntra", new Store("Myntra", "http://www.myntra.com/"));
        store.put("Jabong", new Store("Jabong", "http://www.jabong.com/"));
        store.put("Shopclues", new Store("Shopclues", "http://www.shopclues.com/"));
        store.put("Paytm", new Store("Paytm", "http://www.paytm.com"));
        store.put("Croma", new Store("Croma", "http://www.croma.com/"));
        store.put("Lenskart", new Store("Lenskart", "http://www.lenskart.com/"));
        store.put("Indiatimes", new Store("Indiatimes", "http://www.indiatimes.com/"));
        store.put("Trending", new Store("Trending", "http://www.trendin.com/"));
        store.put("Shop19", new Store("Shop19", "http://www.shopnineteen.com/"));
        store.put("Homeshop", new Store("Homeshop", "http://www.homeshop18.com/"));
        store.put("Cilory", new Store("Cilory", "http://www.cilory.com/"));

    }
}