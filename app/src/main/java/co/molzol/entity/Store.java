package co.molzol.entity;

/**
 *
 * Created by hp on 24-12-2017.
 */

public class Store {

    private String storeName;
    private String storeUrl;
    //private String storeAffliateId;
    //private String storeAffliateToken;

    public Store(){

    }

    public Store(String storeName, String storeUrl){
        this.storeName = storeName;
        this.storeUrl = storeUrl;
    }
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreUrl() {
        return storeUrl;
    }

    public void setStoreUrl(String storeUrl) {
        this.storeUrl = storeUrl;
    }

}
