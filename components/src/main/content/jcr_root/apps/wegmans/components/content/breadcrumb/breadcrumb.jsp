
<%@include file="/apps/wegmans/components/global/global.jsp"%>
<!-- ================================ Breadcrumb Start ================================ -->

<%@page import="org.apache.sling.api.SlingHttpServletRequest"%>
<%@page import="org.apache.sling.api.resource.Resource"%>

<%@page import="com.wegmans.components.globals.BreadcrumbModel"%>

<%@taglib prefix="sl" uri="http://sling.apache.org/taglibs/sling" %>

<sl:adaptTo adaptable="${resource}" adaptTo="com.wegmans.components.globals.BreadcrumbModel" var="breadcrumb"/>

<cq:defineObjects />

${breadcrumb.absParent} -- ${breadcrumb.relParent} -- ${breadcrumb.delimiter} -- ${breadcrumb.trail}

<!-- ================================ Breadcrumb End ================================ -->