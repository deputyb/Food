package com.wegmans.components.util;

public interface Constants {

    public static final String AUTHOR_ASSETS_PATH = "/etc/assets/scaffolding/authors";
    public static final String ARTICLE_ASSETS_PATH = "/etc/assets/scaffolding/articles";
    public static final String PRESS_RELEASE_ASSETS_PATH = "/etc/assets/scaffolding/press-releases";
    public static final String PARENTPAGE = "parentPage";
    public static final String HTML = ".html";
    public static final String JCRCONTENT = "jcr:content";
    public static final String HIDEINNAV = "hideInNav";

    public static final String LANGUAGE_ROOT_FORMAT = "/content/wegmans/%s";

    // Configuration constants
    public static final String FOOTER_CONFIGURATION_FORMAT = LANGUAGE_ROOT_FORMAT + "/jcr:content/footer";
    public static final String EYEBROW_NAVIGATION_CONFIGURATION_FORMAT = LANGUAGE_ROOT_FORMAT + "/jcr:content/eyebrowNavigation";
    public static final String PRIMARY_NAVIGATION_CONFIGURATION_FORMAT = LANGUAGE_ROOT_FORMAT + "/jcr:content/primaryNavigation";
    public static final String BREADCRUMB_CONFIGURATION_FORMAT = LANGUAGE_ROOT_FORMAT + "/jcr:content/breadcrumb";
}
