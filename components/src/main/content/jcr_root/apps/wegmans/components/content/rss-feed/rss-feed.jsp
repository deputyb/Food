<%@include file="/apps/wegmans/components/global/global.jsp" %>
<%@taglib prefix="sl" uri="http://sling.apache.org/taglibs/sling" %>

<sl:adaptTo adaptable="${resource}" adaptTo="com.wegmans.components.news.RSSFeedModel" var="feed"/>

<%-- Note: RSS Element does not support multiple instances on the same page. --%>
<h2>${feed.title}</h2>

<div class="" id="rssfeed"
    data-feed-url="${feed.feedPath}"
    data-nbr-items="${feed.noOfItems}"
    data-full-articles="${feed.showFullStory}"
    data-component-path="${feed.resourceType}">
</div>

<w:placeholder/>
${placeholder}
