<%@include file="/apps/wegmans/components/global/global.jsp"%>
<!-- ================================ Top Navigation Start ================================ -->

<cq:setContentBundle source="page"/>
<w:top-nav />
<w:placeholder />
<ul class="nav navbar-nav">
    <c:set var="currentPagePath" value="${currentPage.path}.html" />
    <c:set var="navPlaceholder" value="RIGHT CLICK TO EDIT" />
    <c:forEach var="firstLevelBean" items="${topNavigationBean.listOfTabs}" varStatus="forEachCount">
        <c:set var="activeClass" value="${firstLevelBean.path == currentPagePath ? 'active' : ''}" />
        <c:choose>
            <c:when test="${not empty firstLevelBean.listOfTabs}">
                <li class="dropdown ${activeClass}">
                    <a href="${firstLevelBean.path}" class="dropdown-toggle" data-toggle="dropdown">${firstLevelBean.title} <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <c:if test="${not empty firstLevelBean.listOfTabs }">
                            <c:forEach var="secondLevel" items="${firstLevelBean.listOfTabs}">
                                <li>
                                    <a href="${secondLevel.path}">${secondLevel.title}</a>
                                </li>
                            </c:forEach>
                        </c:if>
                    </ul>
                </li>
            </c:when>
            <c:otherwise>
                <li class="${activeClass}"><a href="${firstLevelBean.path}">${firstLevelBean.title}</a></li>
            </c:otherwise>
        </c:choose>
    <c:set var="navPlaceholder" value="" />
    </c:forEach>
</ul>
${placeholder}
<!-- ================================ Top Navigation End ================================ -->
