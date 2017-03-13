<%@include file="/apps/wegmans/components/global/global.jsp"%>
<sl:adaptTo adaptable="${resource}" adaptTo="com.wegmans.components.multimedia.LandingPageHeroModel" var="model"/>
<!-- ================================ Landing Page Hero Start ============================== -->

<c:choose><c:when test="${not model.blank}">
<h3>${model.title}</h3>
<h4>${model.subtitle}</h4>
<img src="${model.image}" />
<a href="${model.linkButtonHref}">${model.linkButtonLabel}</a>
<p>${model.layout}</p>
</c:when><c:otherwise>
    <w:placeholder />
    ${placeholder}
</c:otherwise></c:choose>

<!-- ================================ Landing Page Hero End ================================ -->