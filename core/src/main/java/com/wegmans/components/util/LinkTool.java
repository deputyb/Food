package com.wegmans.components.util;

import org.apache.commons.lang3.StringUtils;

public class LinkTool {

    private static final String HTML_EXTENSION = ".html";

    public static String getPageLink(String path) {
        return addHtmlExtension(path);
    }

    public static boolean isInternalLink(String path) {
        return !path.matches("^http(s)?://.+") && path.matches("/content/.+");
    }

    public static boolean isExternalLink(String path) {
        return !isInternalLink(path);
    }

    public static String addHtmlExtension(String path) {
        if (StringUtils.isBlank(path) || isExternalLink(path) || path.matches(".+\\.html(|\\?.*|#.*)$")) {
            return path;
        }

        return path + HTML_EXTENSION;
    }

    public static String getLanguageFromPath(String path) {
        if (StringUtils.isBlank(path)) {
            return null;
        }

        String[] parts = StringUtils.split(path, '/');
        if (parts.length < 3) {
            return null;
        }

        return parts[2];
    }

    private LinkTool() {}
}
