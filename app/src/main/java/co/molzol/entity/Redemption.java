package co.molzol.entity;

import java.io.Serializable;
import java.util.Map;

/**
 * Main table
 * All Redemption Mapped with UID
 * Created by hp on 23-12-2017.
 */

public class Redemption implements Serializable {

    private Map<String, RedemptionRequest> allRedemptionRequests;

    public Map<String, RedemptionRequest> getAllRedemptionRequests() {
        return allRedemptionRequests;
    }

    public void setAllRedemptionRequests(Map<String, RedemptionRequest> allRedemptionRequests) {
        this.allRedemptionRequests = allRedemptionRequests;
    }
}
