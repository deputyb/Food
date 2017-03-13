package com.wegmans.components.multimedia;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.foundation.Image;
import java.io.Serializable;
import org.apache.sling.api.resource.Resource;

public class SlideModel implements Serializable {

    public final Page page;
    public String img = "";
    public String title = "";
    public String name = "";
    public String desc = "";
    public String path = "";

    public SlideModel(Page page) {
        this.page = page;
        title = page.getTitle();
        desc = page.getDescription();
        if (desc == null) {
            desc = "";
        }
        path = page.getPath();
        Resource r = page.getContentResource("image");
        if (r != null) {
            Image image = new Image(r);
            img = page.getPath() + ".img.png" + image.getSuffix();
        }
        name = page.getName();
    }

    public Page getPage() {
        return page;
    }

    public String getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getPath() {
        return path;
    }
}
