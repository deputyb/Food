package com.wegmans.components.account;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import com.wegmans.components.util.LinkTool;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PersonalInformationModel {
    @Inject
    private String editPagePath;

    public String getEditPageLink() {
        return LinkTool.getPageLink(editPagePath);
    }
}
