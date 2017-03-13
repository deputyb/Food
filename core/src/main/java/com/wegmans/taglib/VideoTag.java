package com.wegmans.taglib;
import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Set;
import java.util.logging.Level;
import javax.jcr.RepositoryException;
import org.apache.sling.api.resource.Resource;
import com.day.cq.dam.api.Asset;
import com.day.cq.dam.video.VideoProfile;
import com.day.cq.dam.api.Rendition;
import javax.jcr.Node;


public class VideoTag extends CQBaseTag {

    private static final long serialVersionUID = 1L;
    protected static Logger log = LoggerFactory.getLogger(VideoTag.class);

    @Override
    public int doStartTag() throws JspException {
        // Local variable's are decleried
        String assetPath = "";
        String height = "320";
        String width = "480";
        String ddClassName = "";
        String classPlaceHolder = "";
        String videoID = "";
        String videoClass = "";
        String mediaName = "";
        String mediaFile = "";
        String mediaPath = "";
        String id = "";
        Asset asset = null;
        String flashClass = "";
        String wmode = "";
        String flashPlayer = "";
        String resourcePath = "";
        VideoProfile videoProfile = null;
        String videoProfileHTML = "";
		String videoProfileSource = "";
        boolean noControls = false;
        boolean autoplay = false;
        boolean loop = false;
        final String DEFAULT_H264_PROFILE = "hq";
        final String DEFAULT_FLV_PROFILE = "flv";
        StringBuilder attributes = new StringBuilder();
        StringBuilder sourceAttributes = new StringBuilder();

        log.info("currentNode:: -----------------  " + currentNode);
        if (currentNode != null) {

            try {

                if (currentNode.hasProperty("asset")) {
                    asset = slingRequest
                            .getResourceResolver()
                            .getResource(
                                    currentNode.getProperty("asset")
                                    .getString()).adaptTo(Asset.class);
                    resourcePath = slingRequest.getResourceResolver()
                            .resolve(slingRequest).getResourceType();
                    log.info("resourPath-----------------  "
                            + resourcePath);
                    mediaName = xssAPI.encodeForJSString(asset
                            .getMetadataValue("dc:title") != "" ? asset
                                    .getMetadataValue("dc:title") : asset.getName());
                    mediaFile = asset.getName();
                    mediaPath = asset.getPath();
                    flashClass = currentStyle.get("flashClass", "");
                    wmode = currentStyle.get("wmode", "opaque");
                    noControls = currentStyle.get("noControls", false);
                    autoplay = currentStyle.get("autoplay", false);
                    loop = currentStyle.get("loop", false);
                    flashPlayer = currentStyle
                            .get("flashPlayer", "flvfallback");

                    
                    assetPath = asset.getPath();
                }
               for (String profile : currentStyle.get("profiles",
							new String[0])) {
						videoProfile = VideoProfile.get(resourceResolver,
								profile);

						if (videoProfile != null) {
							Rendition rendition = videoProfile
									.getRendition(asset);
							if (rendition != null) {
								sourceAttributes.append("<source src=\"")
										.append(rendition.getPath());
								sourceAttributes.append("\"").append(
										videoProfile.getHtmlType());
								sourceAttributes.append(">");
								System.out.println("rendition 2"
										+ rendition.getPath());

								// videoProfileSource=videoProfile.getHtmlSource(rendition)
								// ;
								videoProfileHTML = videoProfile.getHtmlType();
							}
						}
					}
                if (currentNode.hasProperty("height")) {
                    height = currentNode.getProperty("height").getString();

                }
                if (currentNode.hasProperty("width")) {
                    height = currentNode.getProperty("width").getString();

                }

                if (currentStyle != null) {
                    videoClass = currentStyle.get("videoClass", "");
                    if (videoClass.length() > 0) {
                        attributes.append(" class=\"")
                                .append(xssAPI.encodeForHTMLAttr(videoClass))
                                .append("\"");
                    }
                    if (!currentStyle.get("noControls", false)) {
                        attributes.append(" controls=\"controls\"");
                    }
                    if (currentStyle.get("autoplay", false)) {
                        attributes.append(" autoplay=\"autoplay\"");
                    }
                    if (currentStyle.get("loop", false)) {
                        attributes.append(" loop=\"loop\"");
                    }
                    String preload = currentStyle.get("preload", "");
                    if (preload.length() > 0) {
                        attributes.append(" preload=\"")
                                .append(xssAPI.encodeForHTMLAttr(preload))
                                .append("\"");
                    }
                    id = "cq-video-html5-" + System.currentTimeMillis();
                }

            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(VideoTag.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }

        pageContext.setAttribute("assetLocation", assetPath);
        pageContext.setAttribute("assetHight", height);
        pageContext.setAttribute("assetWidth", width);
        pageContext.setAttribute("assetddClassName", ddClassName);
        pageContext.setAttribute("assetclassPlaceHolder", classPlaceHolder);
        pageContext.setAttribute("assetVideoID", videoID);
        pageContext.setAttribute("assetvideoClass", videoClass);
        pageContext.setAttribute("assetAttribute", attributes.toString());
        pageContext.setAttribute("id", id);
        pageContext.setAttribute("preload", id);
        pageContext.setAttribute("mediaName", mediaName);
        pageContext.setAttribute("mediaFile", mediaFile);
        pageContext.setAttribute("mediaPath", mediaPath);
        pageContext.setAttribute("videoProfileSource",
                sourceAttributes.toString());
        pageContext.setAttribute("resourcePath", resourcePath);

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}