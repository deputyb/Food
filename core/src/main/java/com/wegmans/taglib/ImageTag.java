package com.wegmans.taglib;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.jcr.RepositoryException;
import javax.jcr.Node;

public class ImageTag extends CQBaseTag {

    private static final long serialVersionUID = 1L;
    protected static Logger log = LoggerFactory.getLogger(ImageTag.class);

    @Override
    public int doStartTag() throws JspException {

        String imageLocation = "";

        if (currentNode != null) {
            try {

                Node node = currentNode;

                if (node.hasProperty("fileReference")) {
                    imageLocation = node.getProperty("fileReference").getString();
                }

            } catch (RepositoryException ex) {
                log.error(ex.getMessage());
            }
        }

        pageContext.setAttribute("imageLocation", imageLocation);

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}
