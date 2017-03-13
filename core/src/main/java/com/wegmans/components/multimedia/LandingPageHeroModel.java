package com.wegmans.components.multimedia;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import com.wegmans.components.util.LinkTool;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class LandingPageHeroModel {

    @Inject
    private String title;

    @Inject
    private String subtitle;

    @Inject
    private String image;

    @Inject
    private String linkButtonLabel;

    @Inject
    private String linkButtonUrl;

    @Inject
    private String layout;

    public boolean isBlank() {
        return title == null && subtitle == null && image == null && linkButtonLabel == null && linkButtonUrl == null
                && layout == null;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getImage() {
        return image;
    }

    public String getLinkButtonLabel() {
        return linkButtonLabel;
    }

    public String getLinkButtonUrl() {
        return linkButtonUrl;
    }

    public String getLinkButtonHref() {
        return LinkTool.getPageLink(linkButtonUrl);
    }

    public String getLayout() {
        return layout;
    }
}
