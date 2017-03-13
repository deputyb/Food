package com.wegmans.components.news;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NewsBlockModel {
    private static final Logger LOG = LoggerFactory.getLogger(NewsBlockModel.class);

    public static final String NEWS_JCR_PATH = "/etc/assets/scaffolding/news";

    private Resource resource;

    @Inject
    @Named("number")
    @Default(intValues = 3)
    int maxElements;

    public NewsBlockModel(Resource resource) {
        this.resource = resource;
    }

    @PostConstruct
    protected void init() {
        LOG.info("Initializing News Block, max size " + maxElements);
    }

    public String getTitle() {
        return "News Block";
    }

    public List<String> getHeadlines() {
        if (resource != null) {
            try {
                Node parentNode = getNode(resource.getResourceResolver(), NEWS_JCR_PATH);
                if (parentNode != null && parentNode.hasNodes()) {
                    NodeIterator childNodes = parentNode.getNodes();
                    return getTitles(childNodes, maxElements);
                } else {
                    LOG.info("No news node at path " + NEWS_JCR_PATH + " was found");
                }

            } catch (RepositoryException e) {
                LOG.error("Failed to load headlines from " + NEWS_JCR_PATH, e);
            }
        }
        return Collections.emptyList();
    }

    private Node getNode(ResourceResolver resourceResolver, String path) {
        Resource r = resourceResolver.getResource(path);
        if (r == null) {
            return null;
        }
        return r.adaptTo(Node.class);
    }

    private List<String> getTitles(NodeIterator childNodes, int max) throws RepositoryException {
        if (childNodes == null) {
            LOG.error("Child nodes are unexpectedly null");
            return Collections.emptyList();
        }

        List<String> ret = new ArrayList<>(max);
        while (childNodes.hasNext() && max-- > 0) {
            Node childNode = (Node) childNodes.next();
            String contentTitle = getContentTitle(childNode);
            if (contentTitle != null) {
                ret.add(contentTitle);
            }
        }
        return ret;
    }

    private String getContentTitle(Node node) throws RepositoryException {
        if (node.hasNode("jcr:content")) {
            Node jcrNode = node.getNode("jcr:content");
            if (jcrNode.hasProperty("jcr:title")) {
                return jcrNode.getProperty("jcr:title").getString();
            }
        }
        return null;
    }
}
