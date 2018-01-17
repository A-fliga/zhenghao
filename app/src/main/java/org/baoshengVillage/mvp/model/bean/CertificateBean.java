package org.baoshengVillage.mvp.model.bean;

import java.util.List;

/**
 * Created by www on 2018/1/11.
 */

public class CertificateBean {
    public List<CertificateItemBean> beanList;
    public String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CertificateItemBean> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<CertificateItemBean> beanList) {
        this.beanList = beanList;
    }

    public static class CertificateItemBean {
        public int imgId;
        public String url;
        public String name;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getImgId() {
            return imgId;
        }

        public void setImgId(int imgId) {
            this.imgId = imgId;
        }
    }
}
