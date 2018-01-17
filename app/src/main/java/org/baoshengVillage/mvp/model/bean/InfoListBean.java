package org.baoshengVillage.mvp.model.bean;

import java.util.List;

/**
 * Created by www on 2018/1/9.
 */

public class InfoListBean {

    /**
     * count : 1
     * list : [{"id":164,"title":"23223232323","text":"32","thumbnailUrl":"http://192.168.5.120:8081/baoshengcun/resource/img/1511841573739.jpg","createDate":1514451393000}]
     */

    private int count;
    private List<ListBean> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 164
         * title : 23223232323
         * text : 32
         * thumbnailUrl : http://192.168.5.120:8081/baoshengcun/resource/img/1511841573739.jpg
         * createDate : 1514451393000
         */

        private int id;
        private String title;
        private String text;
        private String thumbnailUrl;
        private long createDate;

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

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
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
    }
}
