<%@include file="/apps/wegmans/components/global/global.jsp" %>

<%@page import="org.apache.sling.api.resource.Resource"%>

<sl:adaptTo adaptable="${resource}" adaptTo="com.wegmans.components.news.NewsBlockModel" var="model"/>

<h2>${model.title}</h2>
<w:placeholder/>
<div>
    <c:forEach var="headline" items="${model.headlines}">
        <li>${headline}</li>
    </c:forEach>
</div>
${placeholder}
