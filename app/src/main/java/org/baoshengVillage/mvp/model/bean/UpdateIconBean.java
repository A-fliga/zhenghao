package org.baoshengVillage.mvp.model.bean;

import java.util.List;

/**
 * Created by www on 2018/1/12.
 */

public class UpdateIconBean {

    /**
     * headPic : xxxxx
     */

    private String headPic;
    /**
     * result : [{"url":"resource/img/1515762493556.jpg"}]
     * msg : 操作成功！
     * code : 0
     */

    private String msg;
    private int code;
    private List<ResultBean> result;

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

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
         * url : resource/img/1515762493556.jpg
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
