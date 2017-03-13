package com.wegmans.components.globals;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.wegmans.components.validation.AbstractValidatableModel;
import com.wegmans.components.util.LinkTool;

@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BreadcrumbsModel {

    @Inject
    private long absParent;
    
    @Inject
    private long relParent;
    
    @Inject
    private String delimiter;
    
    @Inject
    private String trail;


    public long getAbsParent() {
        return absParent;
    }

    public long getRelParent() {
        return relParent;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public String getTrail() {
        return trail;
    }

}
