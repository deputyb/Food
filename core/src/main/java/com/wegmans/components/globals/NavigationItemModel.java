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

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NavigationItemModel extends AbstractValidatableModel {

    private static final String PATH_EMPTY_VALIDATION_MESSAGE = "Page path must not be empty, please fill it in the dialog";
    private static final String INTERNAL_TITLE_EMPTY_VALIDATION_MESSAGE =
            "Page title must not be empty - please set it on linked page or manually in the dialog";
    private static final String EXTERNAL_TITLE_EMPTY_VALIDATION_MESSAGE =
            "Page title must not be empty, please fill it in the dialog";

    @Inject
    private String pageTitle;

    @Inject
    private String pagePath;

    @Inject
    private List<NavigationItemModel> items;

    @Inject
    private PageManager pageManager;

    private String title;

    @PostConstruct
    public void initTitle() {
        title = pageTitle;
        if (StringUtils.isBlank(pageTitle) && StringUtils.isNotBlank(pagePath) && LinkTool.isInternalLink(pagePath)) {
            Page page = pageManager.getPage(pagePath);
            if (page != null && StringUtils.isNotBlank(page.getTitle())) {
                title = page.getTitle();
            }
        }

        validate();
    }

    @Override
    protected void validate() {
        if (StringUtils.isBlank(pagePath)) {
            addErrorMessage(PATH_EMPTY_VALIDATION_MESSAGE);
            return;
        }

        if (StringUtils.isBlank(title)) {
            if (LinkTool.isInternalLink(pagePath)) {
                addErrorMessage(INTERNAL_TITLE_EMPTY_VALIDATION_MESSAGE);
            } else {
                addErrorMessage(EXTERNAL_TITLE_EMPTY_VALIDATION_MESSAGE);
            }
        }
    }

    @Override
    public boolean isValid() {
        if (items != null) {
            for (NavigationItemModel item : items) {
                if (!item.isValid()) {
                    return false;
                }
            }
        }

        return super.isValid();
    }

    public String getPagePath() {
        return pagePath;
    }

    public String getPageHref() {
        return LinkTool.getPageLink(pagePath);
    }

    public String getTitle() {
        return title;
    }

    public List<NavigationItemModel> getItems() {
        return items;
    }
}
