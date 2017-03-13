package com.wegmans.components;

import com.wegmans.components.util.Constants;
import com.wegmans.components.util.LinkTool;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import com.day.cq.wcm.api.Page;

@Model(adaptables = {SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class EditModel {

    @Inject
    private Page currentPage;

    private boolean configurable;

    @PostConstruct
    public void initFields() {
        String currentPath = currentPage.getPath();
        String language = LinkTool.getLanguageFromPath(currentPath);
        String languageRoot = String.format(Constants.LANGUAGE_ROOT_FORMAT, language);

        configurable = currentPath.equals(languageRoot);
    }

    public boolean isConfigurable() {
        return configurable;
    }
}
