package org.baoshengVillage.mvp.model.bean;

import java.util.List;

/**
 * Created by www on 2018/1/8.
 */

public class PartyIntegrationBean {

    /**
     * dyName : 肖兴发
     * hyqd : [{"title":"学习十九大","integral":"5","createDate":1515138710000}]
     * grades : 9
     * fpjl : [{"explanation":"11","type":0,"integral":3,"createDate":1514863136000},{"explanation":"yui","type":0,"integral":1,"createDate":1514863174000}]
     */

    private String dyName;
    private String grades;
    private List<HyqdBean> hyqd;
    private List<FpjlBean> fpjl;

    public String getDyName() {
        return dyName;
    }

    public void setDyName(String dyName) {
        this.dyName = dyName;
    }

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }

    public List<HyqdBean> getHyqd() {
        return hyqd;
    }

    public void setHyqd(List<HyqdBean> hyqd) {
        this.hyqd = hyqd;
    }

    public List<FpjlBean> getFpjl() {
        return fpjl;
    }

    public void setFpjl(List<FpjlBean> fpjl) {
        this.fpjl = fpjl;
    }

    public static class HyqdBean {
        /**
         * title : 学习十九大
         * integral : 5
         * createDate : 1515138710000
         */

        private String title;
        private String integral;
        private long createDate;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }
    }

    public static class FpjlBean {
        /**
         * explanation : 11
         * type : 0
         * integral : 3
         * createDate : 1514863136000
         */

        private String explanation;
        private String type;
        private String integral;
        private long createDate;

        public String getExplanation() {
            return explanation;
        }

        public void setExplanation(String explanation) {
            this.explanation = explanation;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }
    }
}
