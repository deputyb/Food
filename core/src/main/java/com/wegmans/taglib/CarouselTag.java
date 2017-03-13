package com.wegmans.taglib;

import javax.servlet.jsp.JspException;

import com.day.cq.commons.Doctype;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.WCMMode;
import com.day.cq.wcm.api.components.DropTarget;
import com.day.cq.wcm.foundation.List;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import com.wegmans.components.multimedia.SlideModel;

import org.apache.jackrabbit.util.Text;

public class CarouselTag extends CQBaseTag {

    private static final long serialVersionUID = 1L;

    @Override
    public int doStartTag() throws JspException {

        String xs = Doctype.isXHTML(request) ? "/" : "";
        String ddClassName = "";
        if (WCMMode.fromRequest(request) == WCMMode.EDIT) {
            ddClassName = DropTarget.CSS_CLASS_PREFIX + "pages";
        }

        List list = (List) request.getAttribute("list");
        int playDelay = properties.get("playSpeed", 6000);
        int transTime = properties.get("transTime", 1000);

        String controlsType = properties.get("controlsType", "bc");
        boolean showControls = "pn".equals(controlsType);
        if (showControls) {
            controlsType = "";
        } else {
            controlsType = "-" + controlsType;
        }

        Map<String, SlideModel> slides = new LinkedHashMap<String, SlideModel>();
        if (list != null) {
            Iterator<Page> items = list.getPages();

            String pfx = "cqc-" + Text.getName(resource.getPath()) + "-";
            while (items.hasNext()) {
                SlideModel slide = new SlideModel(items.next());
                String name = pfx + slide.name;
                int idx = 0;
                while (slides.containsKey(name)) {
                    name = pfx + slide.name + (idx++);
                }
                slide.name = xssAPI.encodeForHTMLAttr(name);
                slide.img = request.getContextPath() + slide.img;
                slides.put(name, slide);
            }
        }

        pageContext.setAttribute("carouselDdClassName", ddClassName);
        pageContext.setAttribute("playDelay", playDelay);
        pageContext.setAttribute("transTime", transTime);
        pageContext.setAttribute("carouselSlides", slides);
        pageContext.setAttribute("carouselXs", xs);
        pageContext.setAttribute("carouselShowControls", showControls);
        pageContext.setAttribute("slideshowControlsType", controlsType);

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}
