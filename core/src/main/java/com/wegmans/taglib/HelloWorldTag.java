package com.wegmans.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

public class HelloWorldTag extends CQBaseTag {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.print("Hello " + name + "'s world");
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return SKIP_BODY;
    }
}
