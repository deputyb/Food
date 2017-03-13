package com.wegmans.components.globals;


import javax.inject.Inject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import java.io.Serializable;

@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BreadcrumbBean implements Serializable {

    @Inject
    private long absParent;
    
    @Inject
    private long relParent;
    
    @Inject
    private String delimiter;
    
    @Inject
    private String trail;
    
    public BreadcrumbBean() {}

    public long getAbsParent() {
        return absParent;
    }

    public void setAbsParent(long absParent) {
        this.absParent = absParent;
    }

    public long getRelParent() {
        return relParent;
    }

    public void setRelParent(long relParent) {
        this.relParent = relParent;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getTrail() {
        return trail;
    }

    public void setTrail(String trail) {
        this.trail = trail;
    }

}