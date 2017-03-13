<%@page session="false"%>
<%@include file="/apps/wegmans/components/global/global.jsp"%>
<%@ page import="com.day.cq.commons.Doctype" %>
<%
    String xs = Doctype.isXHTML(request) ? "/" : "";
    String favIcon = currentDesign.getPath() + "/favicon.ico";
    if (resourceResolver.getResource(favIcon) == null) {
        favIcon = null;
    }
%>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta name="keywords" content="<%= xssAPI.encodeForHTMLAttr(WCMUtils.getKeywords(currentPage, false)) %>"<%=xs%>>
        <meta name="description" content="<%= xssAPI.encodeForHTMLAttr(properties.get("jcr:description", "")) %>"<%=xs%>>
        <title><%= currentPage.getTitle() == null ? xssAPI.encodeForHTML(currentPage.getName()) : xssAPI.encodeForHTML(currentPage.getTitle()) %></title>

        <cq:include script="/libs/wcm/core/components/init/init.jsp"/>
        <cq:include script="stats.jsp"/>

        <link href="http://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="/etc/designs/wegmans/clientlibs-all/css/libs.css" type="text/css">
        <link rel="stylesheet" href="/etc/designs/wegmans/clientlibs-all/css/styles.css" type="text/css">
        <link rel="stylesheet" href="/etc/designs/wegmans/clientlibs-all/css/components.css" type="text/css">

        <!--/* Reason we are not using the cq:includeClientLib is because */-->
        <!--/* we want to be able to refresh the script without having to -->
        <!--/* deploy the release app                                     -->
        <!--/* <cq:includeClientLib categories="wegmans.data-bundle"/> */-->
        <script
                id="api-script"
                src="/etc/clientlibs/wegmans/clientlibs-all/js/data.bundle.js"
                data-api='[{
                                "collectionName": "pocCollection",
                                "params": ""
                            }]'>
        </script>
    </head>