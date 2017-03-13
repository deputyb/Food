<%@include file="/apps/wegmans/components/global/global.jsp"%>
<sl:adaptTo adaptable="${resource}" adaptTo="com.wegmans.components.globals.PrimaryNavigationModel" var="model"/>
<!-- ================================ Primary Navigation Start ============================== -->

<c:choose><c:when test="${model.configurable}">
    <cq:include path="navigationItems" resourceType="foundation/components/parsys" />
</c:when><c:otherwise>
    <w:disableEdit>
        <cq:include path="${model.parsysPath}" resourceType="foundation/components/parsys" />
    </w:disableEdit>
</c:otherwise></c:choose>

<!-- ================================ Primary Navigation End ================================ -->