package com.wegmans.components.globals;

import java.io.Serializable;
import java.util.List;

public class TopNavigationModel implements Serializable {

    private static final long serialVersionUID = -6754854163578338367L;
    private List<Object> listOfTabs;
    private String title;
    private String path;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Object> getListOfTabs() {
        return listOfTabs;
    }

    public void setListOfTabs(List<Object> listOfTabs) {
        this.listOfTabs = listOfTabs;
    }

    @Override
    public String toString() {
        return "TopNavigationBean [listOfTabs=" + listOfTabs + ", title=" + title + ", path=" + path + "]";
    }
}
