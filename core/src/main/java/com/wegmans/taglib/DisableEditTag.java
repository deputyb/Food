package com.wegmans.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.sling.api.SlingHttpServletRequest;

import com.day.cq.wcm.api.WCMMode;

public class DisableEditTag extends BodyTagSupport {

    private static final long serialVersionUID = 3706972271721024179L;

    private WCMMode mode;

    @Override
    public int doStartTag() throws JspException {
        this.mode = WCMMode.DISABLED.toRequest(getRequest());
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {
        if (null != mode) {
            mode.toRequest(getRequest());
        }

        return EVAL_PAGE;
    }

    private SlingHttpServletRequest getRequest() {
        return (SlingHttpServletRequest) pageContext.getRequest();
    }

}
