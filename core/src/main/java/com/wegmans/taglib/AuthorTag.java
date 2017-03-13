package com.wegmans.taglib;

import javax.servlet.jsp.JspException;

import java.util.logging.Level;
import javax.jcr.RepositoryException;
import org.apache.sling.api.resource.Resource;
import javax.jcr.Node;
import com.day.cq.wcm.foundation.Image;

public class AuthorTag extends CQBaseTag {

    private static final long serialVersionUID = 1L;

    @Override
    public int doStartTag() throws JspException {

        String firstName = "";
        String lastName = "";
        String email = "";
        String twitter = "";
        String bio = "";

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
                    if (assetNode.hasProperty("./jcr:content/jcr:firstName")) {
                        firstName = assetNode.getProperty("./jcr:content/jcr:firstName").getString();
                    }
                    if (assetNode.hasProperty("./jcr:content/jcr:lastName")) {
                        lastName = assetNode.getProperty("./jcr:content/jcr:lastName").getString();
                    }
                    if (assetNode.hasProperty("./jcr:content/jcr:email")) {
                        email = assetNode.getProperty("./jcr:content/jcr:email").getString();
                    }
                    if (assetNode.hasProperty("./jcr:content/jcr:twitter")) {
                        twitter = assetNode.getProperty("./jcr:content/jcr:twitter").getString();
                    }
                    if (assetNode.hasProperty("./jcr:content/jcr:bio")) {
                        bio = assetNode.getProperty("./jcr:content/jcr:bio").getString();
                    }

                } else {
                    if (currentNode.hasProperty("firstName")) {
                        firstName = currentNode.getProperty("firstName").getString();
                    }
                    if (currentNode.hasProperty("lastName")) {
                        lastName = currentNode.getProperty("lastName").getString();
                    }
                    if (currentNode.hasProperty("email")) {
                        email = currentNode.getProperty("email").getString();
                    }
                    if (currentNode.hasProperty("twitter")) {
                        twitter = currentNode.getProperty("twitter").getString();
                    }
                    if (currentNode.hasProperty("bio")) {
                        bio = currentNode.getProperty("bio").getString();
                    }

                    Node node = currentNode;
                    if (node.hasProperty("fileReference")) {
                        imageLocation = node.getProperty("fileReference").getString();
                    }

                }
            } catch (RepositoryException ex) {
                java.util.logging.Logger.getLogger(AuthorTag.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        pageContext.setAttribute("authorImageLocation", imageLocation);
        pageContext.setAttribute("authorFirstName", firstName);
        pageContext.setAttribute("authorLastName", lastName);
        pageContext.setAttribute("authorEmail", email);
        pageContext.setAttribute("authorTwitter", twitter);
        pageContext.setAttribute("authorBio", bio);

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}
