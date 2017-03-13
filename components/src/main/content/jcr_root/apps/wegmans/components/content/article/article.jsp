<%@include file="/apps/wegmans/components/global/global.jsp"%>
<!-- ================================ Article Start ================================ -->
<%@page import="com.wegmans.components.news.ArticleBean"%>
<sl:adaptTo adaptable="${slingRequest}" adaptTo="com.wegmans.components.news.ArticleBean" var="article"/>
<w:placeholder/>

<div>

	<h2>${article.articleTitle}</h2>

    <c:if test="${not empty article.imageLocation}">
        <img  src="${article.imageLocation}" alt="" style="width:100%;">
    </c:if>
    
    ${article.articleAbstract}

	<p>${article.articleDescription}</p>

</div>
 ${placeholder}
<%@include file="/apps/wegmans/components/content/article/tracking-js.jsp"%> 
<!-- ================================ Article End ================================ -->