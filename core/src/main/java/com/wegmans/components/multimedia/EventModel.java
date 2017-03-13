package com.wegmans.components.multimedia;


import com.day.cq.wcm.foundation.Image;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.Value;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@Model(adaptables = {SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class EventModel {

    private static final Logger LOG = LoggerFactory.getLogger(EventModel.class);

    @SlingObject
    private SlingHttpServletRequest slingRequest;

    @Inject
    private Resource resource;

    @Inject
    private ResourceResolver resourceResolver;

    private static String TRUE = "true";
    private static String JCR_CONTENT = "jcr:content";
    private String eventTitle;
    private String imageLocation;
    private String eventSubTitle;
    private String eventDetail;
    private boolean global;
    private List<Integer> stores;

    /**
     * Default constructor
     */
    public EventModel() {
        // do nothing, used by adaptable injection
    }

    /**
     * This constructor is used by Test cases
     * @param resource
     */
    public EventModel(Resource resource, Resource assetResource) {
        this.resource = resource;
        try {
            initializeEventFromPath(assetResource);
        } catch (RepositoryException ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }

    @PostConstruct
    public void initialize() {
        ValueMap map = resource.adaptTo(ValueMap.class);
        if (map.containsKey("eventPath")) {
            try {
                LOG.debug("eventPath is not null");
                Resource assetResource = resourceResolver.getResource(map.get("eventPath", String.class) + "/" + JCR_CONTENT);
                initializeEventFromPath(assetResource);
            } catch (RepositoryException ex) {
                LOG.error(ex.getMessage(), ex);
            }
        } else {
            LOG.error("eventPath is null");
        }
    }

    /**
     * If Article is created using scaffholding, it stores data using classical structure.
     * This method extracts data from that path.
     * @throws RepositoryException
     * @param assetResource
     */
    private void initializeEventFromPath(Resource assetResource) throws RepositoryException {
        ValueMap map = assetResource.adaptTo(ValueMap.class);

        Image image = new Image(assetResource, "./image");
        imageLocation = image.getHref();
        eventTitle = map.get("./jcr:title", String.class);
        eventDetail = map.get("./detail", String.class);
        eventSubTitle = map.get("./subTitle", String.class);
        // if I don't check for this, and with test class this node is empty, it was throwing NPE
        if (map.containsKey("./globalEvent")) {
            global = map.get("./globalEvent", Boolean.class);
        }
        // only capture stores if global store is not checked
        if (!global && map.containsKey("./stores")) {
            String [] storeStrArray = map.get("./stores", String[].class);
            stores = new ArrayList<Integer>();
            for (String value : storeStrArray) {
                try {
                    stores.add(Integer.valueOf(value));
                } catch (NumberFormatException nfe) {
                    LOG.error("Store does not contain a valid number", nfe);
                }
            }
        }

    }



    public String getImageLocation() {
        return imageLocation;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public String getEventSubTitle() {
        return eventSubTitle;
    }

    public String getEventDetail() {
        return eventDetail;
    }

    public boolean isGlobal() {
        return global;
    }

    public List<Integer> getStores() {
        return stores;
    }


}
