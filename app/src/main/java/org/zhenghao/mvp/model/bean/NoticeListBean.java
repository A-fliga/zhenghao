package org.zhenghao.mvp.model.bean;

import java.util.List;

/**
 * Created by www on 2018/1/9.
 */

public class NoticeListBean {

    /**
     * result : [{"informFor":1,"publishTime":1514872270000,"id":1,"synopsis":"简介1","title":"标题1"}]
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
         * informFor : 1
         * publishTime : 1514872270000
         * id : 1
         * synopsis : 简介1
         * title : 标题1
         */

        private int informFor;
        private long publishTime;
        private int id;
        private String synopsis;
        private String title;

        public int getInformFor() {
            return informFor;
        }

        public void setInformFor(int informFor) {
            this.informFor = informFor;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSynopsis() {
            return synopsis;
        }

        public void setSynopsis(String synopsis) {
            this.synopsis = synopsis;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
