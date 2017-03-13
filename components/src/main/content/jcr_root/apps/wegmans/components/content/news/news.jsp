<%@include file="/apps/wegmans/components/global/global.jsp"%>
<!-- ================================ News Start ================================ -->
<%@page import="org.apache.sling.api.resource.Resource"%>
<sl:adaptTo adaptable="${resource}" adaptTo="com.wegmans.components.globals.NewsModel" var="news"/>
<w:placeholder />
<div>
    <c:if test="${not empty news.imageLocation}">
        <img  src="${news.imageLocation}" alt="">
    </c:if> 

    <h2><c:out value="${news.title}" /></h2>

    <p><c:out value="${news.date}" /></p>

    <p><c:out value="${news.tags}" /></p>

    <p><c:out value="${news.body}" /></p>
    
</div>
${placeholder}
<!-- ================================ News End ================================ -->
