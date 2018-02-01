package org.zhenghao.mvp.model.bean;

/**
 * Created by www on 2018/1/9.
 */

public class NoticeDetailBean {

    /**
     * id : 1
     * title : 标题1
     * contentUrl : http://192.168.5.120:8081/baoshengcun/11
     * synopsis : 简介1
     * informFor : 1
     * createTime : 1514872266000
     * creater : lgz
     * publishTime : 1514872270000
     * status : 2
     */

    private int id;
    private String title;
    private String contentUrl;
    private String synopsis;
    private int informFor;
    private long createTime;
    private String creater;
    private long publishTime;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public int getInformFor() {
        return informFor;
    }

    public void setInformFor(int informFor) {
        this.informFor = informFor;
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

    public long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
