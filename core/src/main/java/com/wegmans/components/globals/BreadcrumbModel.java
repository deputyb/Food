package com.wegmans.components.globals;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import com.wegmans.components.validation.ValidatableModel;
import com.wegmans.components.util.LinkTool;

import com.wegmans.components.util.Constants;

@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BreadcrumbModel {
    
    

    @Inject
    private ResourceResolver resolver;

    @Inject
    private Resource resource;

    private BreadcrumbsModel model;

    private boolean configurable;

    @PostConstruct
    public BreadcrumbsModel initConfig() {
        String resourcePath = resource.getPath();
        String configPath = String.format(getConfigurationFormat(), LinkTool.getLanguageFromPath(resourcePath));
        configurable = resourcePath.equals(configPath);
        Resource configResource = resolver.getResource(configPath);

        if (configResource != null) {
            model = configResource.adaptTo(BreadcrumbsModel.class);
        } else {
            model = new BreadcrumbsModel();
        }
        return model;
    }

    public boolean isConfigurable() {
        return configurable;
    }

    public String getConfigurationFormat() {
        return Constants.EYEBROW_NAVIGATION_CONFIGURATION_FORMAT;
    }
    
    public static void main(String[] args) {
        BreadcrumbModel bc = new BreadcrumbModel();
        BreadcrumbsModel breadcrumbs = bc.initConfig();
     }
    
}
