package com.wegmans.components.news;


import com.day.cq.wcm.foundation.Image;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import javax.jcr.Node;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.RepositoryException;
import java.util.logging.Level;

@Model(adaptables = {SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ArticleBean {

    @SlingObject
    private SlingHttpServletRequest slingRequest;

    @Inject
    private Resource resource;

    private Node currentNode;

    private String articleTitle;
    private String fullName;
    private String imageLocation;
    private String articleAbstract;
    private String articleDescription;


    @PostConstruct
    public void initialize() {
        currentNode = resource.adaptTo(Node.class);
        if (currentNode != null) {
            try {
                if (currentNode.hasProperty("articlePath")) {
                    initializeArticleFromPath();
                } else {
                    initializeArticleFromNode();
                }
            } catch (RepositoryException ex) {
                java.util.logging.Logger.getLogger(ArticleBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * If Article is created using scaffholding, it stores data using classical structure.
     * This method extracts data from that path.
     * @throws RepositoryException
     */
    private void initializeArticleFromPath() throws RepositoryException {
        String articlePath = currentNode.getProperty("articlePath").getString();
        Resource assetResource = slingRequest.getResourceResolver().getResource(articlePath);

        Node articleAssetNode = slingRequest.getResourceResolver().getResource(articlePath).adaptTo(Node.class);

        Image image = new Image(assetResource, "./jcr:content/image");
        imageLocation = image.getHref();
        if (articleAssetNode.hasProperty("./jcr:content/jcr:title")) {
            articleTitle = articleAssetNode.getProperty("./jcr:content/jcr:title").getString();
        }
        if (articleAssetNode.hasProperty("./jcr:content/body")) {
            articleDescription = articleAssetNode.getProperty("./jcr:content/body").getString();
        }
        if (articleAssetNode.hasProperty("./jcr:content/abstract")) {
            articleAbstract = articleAssetNode.getProperty("./jcr:content/abstract").getString();
        }

    }

    /**
     * If Article is created using Granite UI, it stores data in current node structure.
     * This method extracts data from currentNode.
     * @throws RepositoryException
     */
    private void initializeArticleFromNode() throws RepositoryException {
        if (currentNode.hasProperty("title")) {
            articleTitle = currentNode.getProperty("title").getString();
        }
        if (currentNode.hasProperty("articleAbstract")) {
            articleAbstract = currentNode.getProperty("articleAbstract").getString();
        }
        if (currentNode.hasProperty("description")) {
            articleDescription = currentNode.getProperty("description").getString();
        }
        if (currentNode.hasProperty("imageLocation")) {
            imageLocation = currentNode.getProperty("imageLocation").getString();
        }
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public String getArticleAbstract() {
        return articleAbstract;
    }

    public void setArticleAbstract(String articleAbstract) {
        this.articleAbstract = articleAbstract;
    }

    public String getArticleDescription() {
        return articleDescription;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
    }
}
