package org.zhenghao.mvp.model.bean;

/**
 * Created by www on 2018/1/4.
 */

public class VerifyCodeBean {
    /**
     * verifyCode : null
     * phone : 18428344832
     * state : 1
     */

    private String verifyCode;
    private long phone;
    private int state;

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
