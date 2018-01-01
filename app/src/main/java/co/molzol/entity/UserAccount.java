package co.molzol.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * User>UID>UserDetail>UserAccount
 * Created by hp on 19-12-2017.
 */

public class UserAccount implements Serializable {

    private int totalBalance;
    private int confirmedBalance;
    private int pendingBalance;
    //Map of transactionId and userTransaction
    private Map<String, UserTransaction> userTransactions;
    //Map of transactionId and giftVoucher
    private Map<String, UserGiftVoucher> GiftVouchers;
    private Date updatedDate;

    public int getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(int totalBalance) {
        this.totalBalance = totalBalance;
    }

    public int getConfirmedBalance() {
        return confirmedBalance;
    }

    public void setConfirmedBalance(int confirmedBalance) {
        this.confirmedBalance = confirmedBalance;
    }

    public int getPendingBalance() {
        return pendingBalance;
    }

    public void setPendingBalance(int pendingBalance) {
        this.pendingBalance = pendingBalance;
    }

    public Map<String, UserTransaction> getUserTransactions() {
        return userTransactions;
    }

    public void setUserTransactions(Map<String, UserTransaction> userTransactions) {
        this.userTransactions = userTransactions;
    }

    public Map<String, UserGiftVoucher> getGiftVouchers() {
        return GiftVouchers;
    }

    public void setGiftVouchers(Map<String, UserGiftVoucher> giftVouchers) {
        GiftVouchers = giftVouchers;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
