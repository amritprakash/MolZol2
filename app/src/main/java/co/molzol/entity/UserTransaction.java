package co.molzol.entity;

import java.io.Serializable;
import java.util.Date;

import co.molzol.util.Constants;
import co.molzol.util.Constants.EARN_MODE;
import co.molzol.util.Constants.REDEEEM_MODE;
import co.molzol.util.Constants.TRAN_MODE;
import co.molzol.util.Constants.TRAN_STATUS;

/**
 * User>UID>UserDetail>UserAccount>TransactionId>UserTransaction
 * Mapped with TransactionId
 * Created by hp on 19-12-2017.
 */

public class UserTransaction implements Serializable {

    private TRAN_MODE tranMode;
    private Constants.TRAN_STATUS tranStatus = TRAN_STATUS.PENDING;
    private EARN_MODE earnMode;
    private REDEEEM_MODE redeemMode;
    private int tranAmount;
    private String transactionRef;
    private Date createdDate;
    private Date updatedDate;

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public TRAN_MODE getTranMode() {
        return tranMode;
    }

    public void setTranMode(TRAN_MODE tranMode) {
        this.tranMode = tranMode;
    }

    public TRAN_STATUS getTranStatus() {
        return tranStatus;
    }

    public void setTranStatus(TRAN_STATUS tranStatus) {
        this.tranStatus = tranStatus;
    }

    public EARN_MODE getEarnMode() {
        return earnMode;
    }

    public void setEarnMode(EARN_MODE earnMode) {
        this.earnMode = earnMode;
    }

    public REDEEEM_MODE getRedeemMode() {
        return redeemMode;
    }

    public void setRedeemMode(REDEEEM_MODE redeemMode) {
        this.redeemMode = redeemMode;
    }

    public int getTranAmount() {
        return tranAmount;
    }

    public void setTranAmount(int tranAmount) {
        this.tranAmount = tranAmount;
    }

    public String getTransactionRef() {
        return transactionRef;
    }

    public void setTransactionRef(String transactionRef) {
        this.transactionRef = transactionRef;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
