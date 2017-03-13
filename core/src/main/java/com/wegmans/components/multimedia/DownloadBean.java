package com.wegmans.components.multimedia;

import com.day.cq.wcm.api.components.DropTarget;
import com.day.cq.wcm.foundation.Download;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.jackrabbit.util.Text;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class DownloadBean {

    @Inject
    private Resource resource;

    @Inject
    private String href;

    @Inject
    private String displayText;

    @Inject
    private String description;

    @Inject
    private String iconType;

    @Inject
    private StringBuilder attributes;

    private String ddClassName;
    private Download dld;

    @PostConstruct
    public void initFields() {
        this.ddClassName = DropTarget.CSS_CLASS_PREFIX + "file";
        this.dld = new Download(resource);
        dld.addCssClass(ddClassName);
    }

    public String getHref() {
        return Text.escape(dld.getHref(), '%', true);//dld.toString();//ddClassName;//dld.getTitle(true);
    }

    public String getDescription() {
        return dld.getDescription();
    }

    public String getDisplayText() {
        return dld.getInnerHtml() == null ? dld.getFileName() : dld.getInnerHtml().toString();
    }

    public String getIconType() {
        return dld.getIconType();
    }

    public String getAttributes() {
        attributes = new StringBuilder();
        Map<String, String> attrs = dld.getAttributes();
        if (attrs != null) {
            for (Map.Entry e : attrs.entrySet()) {
                attributes.append(StringEscapeUtils.escapeHtml4(e.getKey().toString()));
                attributes.append("=\"");
                attributes.append(StringEscapeUtils.escapeHtml4(e.getValue().toString()));
                attributes.append("\"");
            }
        }

        return attributes.toString();
    }

    public void setAttributes(StringBuilder attributes) {
        this.attributes = attributes;
    }

    public void setIconType(String iconType) {
        this.href = iconType;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }
}
