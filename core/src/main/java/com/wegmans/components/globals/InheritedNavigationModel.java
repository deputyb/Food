package com.wegmans.components.globals;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import com.wegmans.components.validation.ValidatableModel;
import com.wegmans.components.util.LinkTool;

@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public abstract class InheritedNavigationModel implements ValidatableModel {

    @Inject
    private ResourceResolver resolver;

    @Inject
    private Resource resource;

    private NavigationModel model;

    private boolean configurable;

    @PostConstruct
    public void initConfig() {
        String resourcePath = resource.getPath();
        String configPath = String.format(getConfigurationFormat(), LinkTool.getLanguageFromPath(resourcePath));
        configurable = resourcePath.equals(configPath);
        Resource configResource = resolver.getResource(configPath);

        if (configResource != null) {
            model = configResource.adaptTo(NavigationModel.class);
        } else {
            model = new NavigationModel();
        }
    }

    public List<NavigationItemModel> getItems() {
        return model.getItems();
    }

    @Override
    public boolean isValid() {
        return model.isValid();
    }

    public boolean isConfigurable() {
        return configurable;
    }

    protected abstract String getConfigurationFormat();
}
