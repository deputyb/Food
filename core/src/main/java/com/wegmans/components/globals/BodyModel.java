package com.wegmans.components.globals;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BodyModel {

    private static final String DEFAULT_LAYOUT = "columnTwoA";

    @Inject
    private String bodyLayout;

    public String getBodyLayout() {
        return StringUtils.defaultIfEmpty(bodyLayout, DEFAULT_LAYOUT);
    }
}
