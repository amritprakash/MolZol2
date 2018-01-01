package co.molzol.entity;

import java.io.Serializable;

import co.molzol.util.Constants.REDEEEM_MODE;
import co.molzol.util.Constants.TRAN_STATUS;

/**
 * User>Redemption
 * Mapped with UID
 * Created by hp on 23-12-2017.
 */

public class RedemptionRequest implements Serializable {

    private int requestedAmount;
    private REDEEEM_MODE redeemMode;
    private TRAN_STATUS status;

    public int getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(int requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public REDEEEM_MODE getRedeemMode() {
        return redeemMode;
    }

    public void setRedeemMode(REDEEEM_MODE redeemMode) {
        this.redeemMode = redeemMode;
    }

    public TRAN_STATUS getStatus() {
        return status;
    }

    public void setStatus(TRAN_STATUS status) {
        this.status = status;
    }
}
