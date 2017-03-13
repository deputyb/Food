<%@include file="/apps/wegmans/components/global/global.jsp"%>
<sl:adaptTo adaptable="${resource}" adaptTo="com.wegmans.components.globals.FooterModel" var="model"/>

<c:choose><c:when test="${not empty model.items}">
    <c:choose><c:when test="${model.valid}">
            <span>Copyright 2015 Wegmans Food Markets. All rights reserved</span>
            <div style="float:right">
                <c:forEach items="${model.items}" var="link">
                    <a href="${link.pageHref}">${link.title}</a>
                </c:forEach>
            </div>
        </div>
    </c:when><c:otherwise>
        <c:forEach var="item" varStatus="itemStatus" items="${model.items}">
            <c:if test="${not item.valid}">
                <h3>Navigation Item ${itemStatus.count} errors:</h3>
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

<div class="row ">
</div>
