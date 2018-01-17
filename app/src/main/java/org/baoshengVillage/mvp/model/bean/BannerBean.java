package org.baoshengVillage.mvp.model.bean;

import java.util.List;

/**
 * Created by www on 2018/1/9.
 */

public class BannerBean {

    /**
     * result : [{"id":1,"thumbnailUrl":"111"},{"id":2,"thumbnailUrl":null}]
     * msg : 操作成功！
     * code : 0
     */

    private String msg;
    private int code;
    private List<ResultBean> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 1
         * thumbnailUrl : 111
         */

        private int id;
        private String thumbnailUrl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getThumbnailUrl() {
            return thumbnailUrl;
        }

        public void setThumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
        }
    }
}
