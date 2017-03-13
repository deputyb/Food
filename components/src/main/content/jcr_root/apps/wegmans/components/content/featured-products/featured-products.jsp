<%@include file="/apps/wegmans/components/global/global.jsp"%>
<!-- ================================ Event Start ================================ -->
<%@page import="com.wegmans.components.products.FeaturedProductsBean"%>
<sl:adaptTo adaptable="${resource}" adaptTo="com.wegmans.components.products.FeaturedProductsBean" var="featuredproducts"/>
<w:placeholder/>

<div>
<c:choose>
    <c:when test="${not empty featuredproducts.featuredProductIds}">
    	<c:choose>
            <c:when test="${featuredproducts.valid}">
       			<c:if test="${not empty featuredproducts.featuredProductIds}">
            		<p>Featured Product Ids:</p>
            			<c:forEach var="featuredProductId" items="${featuredproducts.featuredProductIds}">
               				<p>${featuredProductId}</p>
                    	</c:forEach>
            	</c:if>
  			 </c:when>
        </c:choose>
    </c:when>
    <c:otherwise>
        <c:if test="${not empty featuredproducts.errorMessages}">
            <h3>Featured Product Dialog errors:</h3>
            <c:forEach var="errorMessage" items="${featuredproducts.errorMessages}">
                <p>${errorMessage}</p>
            </c:forEach>
        </c:if>
    </c:otherwise>
</c:choose>
</div>
