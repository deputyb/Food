<%@include file="/apps/wegmans/components/global/global.jsp"%>
<sl:adaptTo adaptable="${resource}" adaptTo="com.wegmans.components.globals.EyebrowNavigationModel" var="model"/>

<c:choose><c:when test="${not empty model.items}">
    <div class="component-eyebrow-navigation">
        <nav>
        <i class="fa fa-map-marker"></i>
        <label for="eyebrow-nav-input"></label>
        <input type="text" id="eyebrow-nav-input" placeholder="MY STORE: PITTSFORD">
        <c:forEach items="${model.items}" var="link">
                <a href="${link.pageHref}" class="nav-item">${link.title}</a>
            </c:forEach>
        </nav>
    </div>
</c:when><c:otherwise>
    <w:placeholder />
    ${placeholder}
</c:otherwise></c:choose>
