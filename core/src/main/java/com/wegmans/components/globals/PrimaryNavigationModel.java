package com.wegmans.components.globals;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import com.wegmans.components.util.Constants;
import com.wegmans.components.util.LinkTool;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PrimaryNavigationModel {

    private static final String CONFIG_PARSYS_PATH = "/navigationItems";

    @Inject
    private ResourceResolver resolver;

    @Inject
    private Resource resource;

    private String parsysPath;

    private boolean configurable;

    @PostConstruct
    public void initConfig() {
        String resourcePath = resource.getPath();
        String configPath =
                String.format(Constants.PRIMARY_NAVIGATION_CONFIGURATION_FORMAT, LinkTool.getLanguageFromPath(resourcePath));
        parsysPath = configPath + CONFIG_PARSYS_PATH;
        Resource configResource = resolver.getResource(parsysPath);

        configurable = resourcePath.equals(configPath);
        if (configurable || configResource == null) {
            return;
        }
    }

    public String getParsysPath() {
        return parsysPath;
    }

    public boolean isConfigurable() {
        return configurable;
    }
}
