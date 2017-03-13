package com.wegmans.taglib;

import javax.servlet.jsp.JspException;

import java.util.logging.Level;
import javax.jcr.RepositoryException;
import org.apache.sling.api.resource.Resource;
import javax.jcr.Node;
import com.day.cq.wcm.foundation.Image;

public class PressReleaseTag extends CQBaseTag {

    private static final long serialVersionUID = 1L;

    @Override
    public int doStartTag() throws JspException {

        String title = "";
        String headline = "";
        String abstractText = "";
        String company = "";
        String companyInformation = "";
        String body = "";

        String imageLocation = "";
        Image image = null;

        if (currentNode != null) {
            try {

                if (currentNode.hasProperty("assetPath")) {
                    String assetPath = currentNode.getProperty("assetPath").getString();
                    Resource assetResource = slingRequest.getResourceResolver().getResource(assetPath);

                    Node assetNode = slingRequest.getResourceResolver().getResource(assetPath).adaptTo(Node.class);

                    image = new Image(assetResource, "./jcr:content/image");
                    imageLocation = image.getHref();
                    if (assetNode.hasProperty("./jcr:content/jcr:title")) {
                        title = assetNode.getProperty("./jcr:content/jcr:title").getString();
                    }
                    if (assetNode.hasProperty("./jcr:content/jcr:headline")) {
                        headline = assetNode.getProperty("./jcr:content/jcr:headline").getString();
                    }
                    if (assetNode.hasProperty("./jcr:content/jcr:abstract")) {
                        abstractText = assetNode.getProperty("./jcr:content/jcr:abstract").getString();
                    }
                    if (assetNode.hasProperty("./jcr:content/jcr:company")) {
                        company = assetNode.getProperty("./jcr:content/jcr:company").getString();
                    }
                    if (assetNode.hasProperty("./jcr:content/jcr:companyInformation")) {
                        companyInformation = assetNode.getProperty("./jcr:content/jcr:companyInformation").getString();
                    }
                    if (assetNode.hasProperty("./jcr:content/jcr:body")) {
                        body = assetNode.getProperty("./jcr:content/jcr:body").getString();
                    }

                } else {
                    if (currentNode.hasProperty("title")) {
                        title = currentNode.getProperty("title").getString();
                    }
                    if (currentNode.hasProperty("headline")) {
                        headline = currentNode.getProperty("headline").getString();
                    }
                    if (currentNode.hasProperty("abstract")) {
                        abstractText = currentNode.getProperty("abstract").getString();
                    }
                    if (currentNode.hasProperty("company")) {
                        company = currentNode.getProperty("company").getString();
                    }
                    if (currentNode.hasProperty("companyInformation")) {
                        companyInformation = currentNode.getProperty("companyInformation").getString();
                    }
                    if (currentNode.hasProperty("body")) {
                        body = currentNode.getProperty("body").getString();
                    }
                    Node node = currentNode;
                    if (node.hasProperty("fileReference")) {
                        imageLocation = node.getProperty("fileReference").getString();
                    }

                }
            } catch (RepositoryException ex) {
                log.error(ex.getMessage());
            }
        }

        pageContext.setAttribute("pressReleaseImageLocation", imageLocation);
        pageContext.setAttribute("pressReleaseTitle", title);
        pageContext.setAttribute("pressReleaseBody", body);
        pageContext.setAttribute("pressReleaseHeadline", headline);
        pageContext.setAttribute("pressReleaseCompany", company);
        pageContext.setAttribute("pressReleaseCompanyInformation", companyInformation);
        pageContext.setAttribute("pressReleaseAbstract", abstractText);

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}
