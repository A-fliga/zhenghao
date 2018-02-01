package org.zhenghao.mvp.model.bean;

import java.util.List;

/**
 * Created by www on 2018/1/10.
 */

public class BannerAndInfoDetailBean {

    /**
     * id : 1
     * title : 111
     * contentUrl : http://localhost:8081/baoshengcun/11
     * thumbnailUrl : http://localhost:8081/baoshengcun/111
     * createDate : 1515161676000
     * publishDate : 1515161680000
     * content : 《征求意见稿》规定失业人员的失业保险待遇共七项，分别是：失业保险金、代缴基本养老保险费、代缴基本医疗保险费、死亡丧葬补助金和抚恤金、职业培训补贴、职业技能鉴定补贴、创业补贴。与现行条例相比，新增了为失业人员代缴基本养老保险费、职业技能鉴定补贴和创业补贴，将医疗补助金调整为代缴基本医疗保险费
     * text : 《征求意见稿》取消了农民合同制工人参保和享受待遇的特殊规定，统一了农民合同制工人和城镇职工的失业保险政策。统一政策后，农民工失业后不仅可以按月领取失业保险金，由失业保险基金代缴基本养老和医疗保险费，还可根据自身情况享受职业培训、职业技能鉴定和创业补贴。
     * state : 1
     * articlePictures : []
     */

    private int id;
    private String title;
    private String contentUrl;
    private String thumbnailUrl;
    private long createDate;
    private long publishDate;
    private String content;
    private String text;
    private int state;
    private List<?> articlePictures;

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

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public long getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(long publishDate) {
        this.publishDate = publishDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<?> getArticlePictures() {
        return articlePictures;
    }

    public void setArticlePictures(List<?> articlePictures) {
        this.articlePictures = articlePictures;
    }
}
