package com.wegmans.taglib;

import com.day.cq.wcm.api.WCMMode;
import com.day.cq.wcm.api.components.DropTarget;
import javax.servlet.jsp.JspException;

import com.day.cq.wcm.foundation.Placeholder;

public class PlaceholderTag extends CQBaseTag {

    private static final long serialVersionUID = 1L;

    @Override
    public int doStartTag() throws JspException {

        String placeholder = "";
        String ddClassName = DropTarget.CSS_CLASS_PREFIX + "file";

        if (WCMMode.fromRequest(request) == WCMMode.EDIT) {
            String classicPlaceholder = "<img src=\"/libs/cq/ui/resources/0.gif\" class=\"cq-file-placeholder " + ddClassName + "\" alt=\"\">";
            placeholder = Placeholder.getDefaultPlaceholder(slingRequest, component, classicPlaceholder, ddClassName, null);
        }

        pageContext.setAttribute("placeholder", placeholder);

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}
