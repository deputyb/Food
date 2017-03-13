package com.wegmans.components.news;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class RSSFeedModel {

    @Inject
    private String feedPath;

    @Inject
    private String noOfItems;

    @Inject
    private String showFullStory;

    private String resourceType;

    public RSSFeedModel(Resource resource) {
        this.resourceType = resource.getResourceType();
    }

    public String getTitle() {
        return "RSS Feed Element";
    }

    public String getFeedPath() {
        return feedPath;
    }

    public String getNoOfItems() {
        return noOfItems;
    }

    public String getShowFullStory() {
        return showFullStory;
    }

    public String getResourceType() {
        return resourceType;
    }
}
