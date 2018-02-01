package org.zhenghao.mvp.model.bean;

/**
 * Created by www on 2018/1/11.
 */

public class LearningDetailBean {

    /**
     * partyMemberLearning : {"id":3,"title":"标题","contentUrl":"111","contentSource":"来源","learningTime":5,"createTime":1514962140000,"createBy":"222","publishTime":1514962145000,"status":2}
     * isEnd : 1
     */

    private PartyMemberLearningBean partyMemberLearning;
    private int isEnd;

    public PartyMemberLearningBean getPartyMemberLearning() {
        return partyMemberLearning;
    }

    public void setPartyMemberLearning(PartyMemberLearningBean partyMemberLearning) {
        this.partyMemberLearning = partyMemberLearning;
    }

    public int getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(int isEnd) {
        this.isEnd = isEnd;
    }

    public static class PartyMemberLearningBean {
        /**
         * id : 3
         * title : 标题
         * contentUrl : 111
         * contentSource : 来源
         * learningTime : 5
         * createTime : 1514962140000
         * createBy : 222
         * publishTime : 1514962145000
         * status : 2
         */

        private int id;
        private String title;
        private String contentUrl;
        private String contentSource;
        private int learningTime;
        private long createTime;
        private String createBy;
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

        public String getContentSource() {
            return contentSource;
        }

        public void setContentSource(String contentSource) {
            this.contentSource = contentSource;
        }

        public int getLearningTime() {
            return learningTime;
        }

        public void setLearningTime(int learningTime) {
            this.learningTime = learningTime;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
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
}
