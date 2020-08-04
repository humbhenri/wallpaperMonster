package com.humbertopinheiro.wallpaper.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RedditJson {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public List<ChildData> getChildrenData() {
        List<ChildData> childData = new ArrayList<>();
        for (Child c : getData().getChildren()) {
            childData.add(c.getData());
        }
        return childData;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {
        private List<Child> children;

        public List<Child> getChildren() {
            return children;
        }

        public void setChildren(List<Child> children) {
            this.children = children;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Child {
        private ChildData data;

        public ChildData getData() {
            return data;
        }

        public void setData(ChildData data) {
            this.data = data;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ChildData {
        private String url;
        private String title;

        public String getUrl() {
            return url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

}
