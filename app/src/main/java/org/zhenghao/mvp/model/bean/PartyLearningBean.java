package org.zhenghao.mvp.model.bean;

import java.util.List;

/**
 * Created by www on 2018/1/10.
 */

public class PartyLearningBean {

    /**
     * result : [{"typeName":"11","typeCount":1,"typeId":1},{"typeName":"22","typeCount":3,"typeId":1}]
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
         * typeName : 11
         * typeCount : 1
         * typeId : 1
         */

        private String typeName;
        private int typeCount;
        private int typeId;

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public int getTypeCount() {
            return typeCount;
        }

        public void setTypeCount(int typeCount) {
            this.typeCount = typeCount;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }
    }
}
