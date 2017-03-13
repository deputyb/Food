package com.wegmans.components.globals;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeaderModel {

    @Inject
    private String headerSize;

    @Inject
    private String headerText;

    public String getHeaderSize() {
        return headerSize;
    }

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderSize(String headerSize) {
        this.headerSize = headerSize;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }
}
