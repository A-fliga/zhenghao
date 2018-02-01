package org.zhenghao.mvp.model.bean;

import java.util.List;

/**
 * Created by www on 2018/1/10.
 */

public class LearningListBean {

    /**
     * learnEndCount : 2
     * count : 3
     * learnList : [{"publishTime":1514962144000,"id":4,"title":"标题","isEnd":1},{"publishTime":1514962143000,"id":3,"title":"标题","isEnd":0},{"publishTime":1514962141000,"id":1,"title":"标题","isEnd":1}]
     */

    private int learnEndCount;
    private int count;
    private List<LearnListBean> learnList;

    public int getLearnEndCount() {
        return learnEndCount;
    }

    public void setLearnEndCount(int learnEndCount) {
        this.learnEndCount = learnEndCount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<LearnListBean> getLearnList() {
        return learnList;
    }

    public void setLearnList(List<LearnListBean> learnList) {
        this.learnList = learnList;
    }

    public static class LearnListBean {
        /**
         * publishTime : 1514962144000
         * id : 4
         * title : 标题
         * isEnd : 1
         */

        private long publishTime;
        private int id;
        private String title;
        private int isEnd;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getIsEnd() {
            return isEnd;
        }

        public void setIsEnd(int isEnd) {
            this.isEnd = isEnd;
        }
    }
}
