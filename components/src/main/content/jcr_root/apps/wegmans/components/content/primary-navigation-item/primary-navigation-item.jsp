<%@include file="/apps/wegmans/components/global/global.jsp"%>
<sl:adaptTo adaptable="${resource}" adaptTo="com.wegmans.components.globals.NavigationItemModel" var="model"/>
<!-- ================================ Primary Navigation Item Start ============================== -->

<c:choose><c:when test="${not empty model.title or not empty model.pagePath or not empty model.items}">
    <c:choose><c:when test="${model.valid}">
        <h3>${model.title} | ${model.pagePath}</h3>
        <c:if test="${not empty model.items}">
            <p>Items:</p>
            <c:forEach var="item" items="${model.items}">
               <p>${item.title} | ${item.pagePath}</p>
            </c:forEach>
        </c:if>
    </c:when><c:otherwise>
        <c:if test="${not empty model.errorMessages}">
            <h3>Top Level Item errors:</h3>
            <c:forEach var="errorMessage" items="${model.errorMessages}">
                <p>${errorMessage}</p>
            </c:forEach>
        </c:if>
        <c:forEach var="item" varStatus="itemStatus" items="${model.items}">
            <c:if test="${not item.valid}">
                <h3>Secondary Navigation Item ${itemStatus.count} errors:</h3>
                <c:forEach var="errorMessage" items="${item.errorMessages}">
                    <p>${errorMessage}</p>
                </c:forEach>
            </c:if>
        </c:forEach>
    </c:otherwise></c:choose>
</c:when><c:otherwise>
    <w:placeholder />
    ${placeholder}
</c:otherwise></c:choose>

<!-- ================================ Primary Navigation Item End ================================ -->