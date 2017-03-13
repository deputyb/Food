package com.wegmans.components.multimedia;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ResponsiveImageModel{



    @Inject
    @Named("desktopImage")
    private String desktopImage;

    @Inject
    @Named("tabletImage")
    private String tabletImage;

    @Inject
    @Named("mobileImage")
    private String mobileImage;


    public void init() {

    }

    public String getDesktopImage() {
        return desktopImage;
    }

    public String getTabletImage() {
        return tabletImage;
    }

    public String getMobileImage() {
        return mobileImage;
    }

}