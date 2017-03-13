package com.wegmans.taglib;

import javax.jcr.RepositoryException;
import javax.servlet.jsp.JspException;


import com.day.cq.commons.jcr.JcrUtil;
import com.day.cq.wcm.api.components.DropTarget;

public class SlideshowTag extends CQBaseTag {

    private static final long serialVersionUID = 1L;

    String sample = "test message - updated!!";
    String ddClassName = "";
    String name = "";
    String id = "";
    String slideshowURL = "";
    String expressInstallURL = "";
    String contentPath = "";
    String backgroundColor = "";
    String encodeForId = "";
    String width = "";
    String height = "";

    String slideshowWidth = "";
    String slideshowHeight = "";
    String propertiesEntrySet = "";

    @Override
    public int doStartTag() throws JspException {
        log.debug("sdghsdgh");

        try {

            ddClassName = DropTarget.CSS_CLASS_PREFIX + "slideshow";
            name = currentNode.getPath();
            id = JcrUtil.createValidName(name) + "_swf";
            slideshowURL = contextPath + "/etc/clientlibs/foundation/shared/endorsed/swf/slideshow.swf";
            expressInstallURL = contextPath + "/etc/clientlibs/foundation/swfobject/swf/expressInstall.swf";

            contentPath = xssAPI.getValidHref(contextPath + currentNode.getPath());
            backgroundColor = xssAPI.encodeForJSString(properties.get("backgroundColor", "ffffff"));
            encodeForId = xssAPI.encodeForJSString(id);
            width = currentNode.getProperty("slideshowWidth").getString();//properties.get("slideshowWidth", String.class) != null ? xssAPI.encodeForJSString(properties.get("slideshowWidth", String.class)) : "100%";
            log.error("WIDTH: " + width);
            height = properties.get("slideshowHeight", String.class) != null ? xssAPI.encodeForJSString(properties.get("slideshowHeight", String.class)) : "300";

            slideshowWidth = properties.get("slideshowWidth", String.class);
            slideshowHeight = properties.get("slideshowHeight", String.class);
            propertiesEntrySet = properties.entrySet().toString();

        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }

        pageContext.setAttribute("sample", sample);
        pageContext.setAttribute("ddClassName", ddClassName);
        pageContext.setAttribute("name", name);
        pageContext.setAttribute("id", id);
        pageContext.setAttribute("slideshowURL", slideshowURL);
        pageContext.setAttribute("expressInstallURL", expressInstallURL);
        pageContext.setAttribute("contextPath", contextPath);
        pageContext.setAttribute("backgroundColor", backgroundColor);
        pageContext.setAttribute("encodeForId", encodeForId);
        pageContext.setAttribute("width", width);
        pageContext.setAttribute("height", height);

        pageContext.setAttribute("slideshowWidth", slideshowWidth);
        pageContext.setAttribute("slideshowHeight", slideshowHeight);
        pageContext.setAttribute("propertiesEntrySet", propertiesEntrySet);

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}
