package com.wegmans.taglib;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.logging.Level;
import javax.jcr.RepositoryException;
import org.apache.sling.api.resource.Resource;
import javax.jcr.Node;
import com.day.cq.wcm.foundation.Image;

public class ArticleTag extends CQBaseTag {

    private static final long serialVersionUID = 1L;
    protected static Logger log = LoggerFactory.getLogger(ArticleTag.class);

    @Override
    public int doStartTag() throws JspException {

        String title = "";
        String body = "";
        String articleAbstract = "";
        String imageLocation = "";
        String fullName = "";
        String pressReleaseTitle = "";
        String bio = "";
        Image image = null;

        if (currentNode != null) {
            try {

                if (currentNode.hasProperty("articlePath")) {
                    String articlePath = currentNode.getProperty("articlePath").getString();
                    Resource assetResource = slingRequest.getResourceResolver().getResource(articlePath);

                    Node articleAssetNode = slingRequest.getResourceResolver().getResource(articlePath).adaptTo(Node.class);

                    image = new Image(assetResource, "./jcr:content/image");
                    imageLocation = image.getHref();
                    if (articleAssetNode.hasProperty("./jcr:content/jcr:title")) {
                        title = articleAssetNode.getProperty("./jcr:content/jcr:title").getString();
                    }
                    if (articleAssetNode.hasProperty("./jcr:content/body")) {
                        body = articleAssetNode.getProperty("./jcr:content/body").getString();
                    }
                    if (articleAssetNode.hasProperty("./jcr:content/abstract")) {
                        articleAbstract = articleAssetNode.getProperty("./jcr:content/abstract").getString();
                    }

                    if (articleAssetNode.hasProperty("./jcr:content/jcr:author")) {
                        String authorAssetPath = articleAssetNode.getProperty("./jcr:content/jcr:author").getString();
                        Node authorAssetNode = slingRequest.getResourceResolver().getResource(authorAssetPath).adaptTo(Node.class);
                        if (authorAssetNode.hasProperty("./jcr:content/jcr:firstName")
                                && authorAssetNode.hasProperty("./jcr:content/jcr:lastName")) {
                            String firstName = authorAssetNode.getProperty("./jcr:content/jcr:firstName").getString();
                            String lastName = authorAssetNode.getProperty("./jcr:content/jcr:lastName").getString();
                            fullName = firstName + " " + lastName;
                        }

                        if (authorAssetNode.hasProperty("./jcr:content/jcr:bio")) {
                            bio = authorAssetNode.getProperty("./jcr:content/jcr:bio").getString();
                        }

                    }

                    if (articleAssetNode.hasProperty("./jcr:content/pressReleaseURL")) {
                        String pressReleasePath = articleAssetNode.getProperty("./jcr:content/pressReleaseURL").getString();
                        Node pressReleaseNode = slingRequest.getResourceResolver().getResource(pressReleasePath).adaptTo(Node.class);
                        if (pressReleaseNode.hasProperty("./jcr:content/jcr:title")) {
                            pressReleaseTitle = pressReleaseNode.getProperty("./jcr:content/jcr:title").getString();
                        }
                    }

                } else {
                    if (currentNode.hasProperty("title")) {
                        title = currentNode.getProperty("title").getString();
                    }
                    if (currentNode.hasProperty("articleAbstract")) {
                        articleAbstract = currentNode.getProperty("articleAbstract").getString();
                    }
                    if (currentNode.hasProperty("description")) {
                        body = currentNode.getProperty("description").getString();
                    }
                    Node node = currentNode;

                    if (currentNode.hasProperty("pressReleasePath")) {
                        String pressReleasePath = currentNode.getProperty("pressReleasePath").getString();
                        Node pressReleaseNode = slingRequest.getResourceResolver().getResource(pressReleasePath).adaptTo(Node.class);
                        pressReleaseTitle = pressReleaseNode.getProperty("./jcr:content/jcr:title").getString();
                    }
                    if (node.hasProperty("fileReference")) {
                        imageLocation = node.getProperty("fileReference").getString();
                    }

                }
            } catch (RepositoryException ex) {
                java.util.logging.Logger.getLogger(ArticleTag.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        pageContext.setAttribute("articleTitle", title);
        pageContext.setAttribute("fullName", fullName);
        pageContext.setAttribute("pressReleaseTitle", pressReleaseTitle);
        pageContext.setAttribute("imageLocation", imageLocation);
        pageContext.setAttribute("articleAbstract", articleAbstract);
        pageContext.setAttribute("articleDescription", body);
        pageContext.setAttribute("bio", bio);

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}
