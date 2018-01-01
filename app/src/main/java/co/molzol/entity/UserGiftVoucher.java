package co.molzol.entity;

import java.io.Serializable;

import co.molzol.util.Constants.GIFT_VOUCHER_NAME;
/**
 * User>UID>UserDetail>UserAccount>TransactionId>UserGiftVoucher
 * Created by hp on 23-12-2017.
 */

public class UserGiftVoucher implements Serializable {

    private int amount;
    private GIFT_VOUCHER_NAME giftVoucherName;
    private String voucherRef1;
    private String voucherRef2;
    private String voucherRef3;
    private String voucherref4;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public GIFT_VOUCHER_NAME getGiftVoucherName() {
        return giftVoucherName;
    }

    public void setGiftVoucherName(GIFT_VOUCHER_NAME giftVoucherName) {
        this.giftVoucherName = giftVoucherName;
    }

    public String getVoucherRef1() {
        return voucherRef1;
    }

    public void setVoucherRef1(String voucherRef1) {
        this.voucherRef1 = voucherRef1;
    }

    public String getVoucherRef2() {
        return voucherRef2;
    }

    public void setVoucherRef2(String voucherRef2) {
        this.voucherRef2 = voucherRef2;
    }

    public String getVoucherRef3() {
        return voucherRef3;
    }

    public void setVoucherRef3(String voucherRef3) {
        this.voucherRef3 = voucherRef3;
    }

    public String getVoucherref4() {
        return voucherref4;
    }

    public void setVoucherref4(String voucherref4) {
        this.voucherref4 = voucherref4;
    }
}
