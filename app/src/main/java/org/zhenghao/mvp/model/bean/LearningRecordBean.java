package org.zhenghao.mvp.model.bean;

import java.util.List;

/**
 * Created by www on 2018/1/8.
 */

public class LearningRecordBean {

    /**
     * result : [{"title":"标题","createTime":1514964762000,"isEnd":1},{"title":"标题","createTime":1514964762000,"isEnd":1},{"title":"标题","createTime":1514964762000,"isEnd":1},{"title":"标题","createTime":1514964762000,"isEnd":1}]
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
         * title : 标题
         * createTime : 1514964762000
         * isEnd : 1
         */

        private String title;
        private long createTime;
        private int isEnd;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getIsEnd() {
            return isEnd;
        }

        public void setIsEnd(int isEnd) {
            this.isEnd = isEnd;
        }
    }
}
