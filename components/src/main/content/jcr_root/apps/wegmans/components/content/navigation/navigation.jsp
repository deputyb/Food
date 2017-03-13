<%@include file="/apps/wegmans/components/global/global.jsp"%>
<sl:adaptTo adaptable="${resource}" adaptTo="com.wegmans.components.globals.NavigationModel" var="model"/>

<ul>
    <c:forEach items="${model.items}" var="link">
        <li><a href="${link.pageHref}">${link.title}</a></li>
    </c:forEach>
</ul>

<w:placeholder />
${placeholder}
