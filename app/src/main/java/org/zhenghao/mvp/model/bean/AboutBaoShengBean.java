package org.zhenghao.mvp.model.bean;

/**
 * Created by www on 2018/1/9.
 */

public class AboutBaoShengBean {

    /**
     * id : 1
     * contentUrl : rdasda
     * createTime : 1514884019000
     * creater : lgz
     * updateTime : 1514884025000
     */

    private int id;
    private String contentUrl;
    private long createTime;
    private String creater;
    private long updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
