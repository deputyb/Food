<%@include file="/apps/wegmans/components/global/global.jsp"%>
<%@taglib prefix="sl" uri="http://sling.apache.org/taglibs/sling" %>
<%@page import="org.apache.sling.api.resource.Resource"%>
<!-- ================================ Responsive Image Start ================================ -->
<sl:adaptTo adaptable="${resource}" adaptTo="com.wegmans.components.multimedia.ResponsiveImageModel" var="model"/>
<w:placeholder />
<cq:includeClientLib categories="wegmans.custom.responsive-image" />

<div desktopImagePath="${model.desktopImage}" tabletImagePath="${model.tabletImage}"
	mobileImagePath="${model.mobileImage}">
	<img src="${model.desktopImage}" />
</div>
${placeholder}
<%@include file="/apps/wegmans/components/content/responsive-image/tracking-js.jsp"%> 
<!-- ================================ Responsive Image End ================================ -->