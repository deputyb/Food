<%@include file="/libs/foundation/global.jsp" %>
<%
    final I18n i18n = new I18n(slingRequest);
%>
<%@page session="false"
    import="
    com.adobe.granite.xss.XSSAPI,
    com.day.text.Text,
    com.day.cq.commons.Doctype,
    com.day.cq.wcm.api.components.DropTarget,
    com.day.cq.wcm.api.WCMMode,
    com.day.cq.wcm.foundation.Image,
    com.day.cq.wcm.foundation.List,
    com.day.cq.i18n.I18n,
    org.apache.commons.lang3.StringEscapeUtils,
    org.apache.sling.api.resource.ResourceResolver,
    org.apache.sling.api.SlingHttpServletRequest"%>
<%
    boolean enableEdit = WCMMode.fromRequest(request) == WCMMode.EDIT;
    request.setAttribute("enableEdit", enableEdit);
%>
<%@ taglib uri="http://www.wegmans.com/taglibs/aem/wegmans" prefix="w" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sl" uri="http://sling.apache.org/taglibs/sling" %>

<cq:defineObjects />
<sl:defineObjects />
