package com.wegmans.components.social;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FacebookFeedBean {

    @Inject
    private String pageName;

    @Inject
    private String width;

    @Inject
    private String height;

    @Inject
    private String colorScheme;

    @Inject
    private String showFaces;

    @Inject
    private String showHeader;

    @Inject
    private String showPosts;

    @Inject
    private String showBorder;

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getColorScheme() {
        return colorScheme;
    }

    public void setColorScheme(String colorScheme) {
        this.colorScheme = colorScheme;
    }

    public String getShowFaces() {
        return showFaces;
    }

    public void setShowFaces(String showFaces) {
        this.showFaces = showFaces;
    }

    public String getShowHeader() {
        return showHeader;
    }

    public void setShowHeader(String showHeader) {
        this.showHeader = showHeader;
    }

    public String getShowPosts() {
        return showPosts;
    }

    public void setShowPosts(String showPosts) {
        this.showPosts = showPosts;
    }

    public String getShowBorder() {
        return showBorder;
    }

    public void setShowBorder(String showBorder) {
        this.showBorder = showBorder;
    }

}
