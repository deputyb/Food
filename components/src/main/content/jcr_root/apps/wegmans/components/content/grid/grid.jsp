<%@include file="/apps/wegmans/components/global/global.jsp"%>
<!-- ================================ Grid Start ================================ -->
<w:placeholder/>
<c:set var="numberOfColumns"><cq:text property="numberOfColumns" placeholder="1" /></c:set>
<fmt:parseNumber var="parsedNumber" type="number" value="${numberOfColumns}" scope="page"/>
<div class="row">
	<c:forEach var="i" begin="1" end="${parsedNumber}">
   		 <div class="${i}-parsys">
        	<cq:include path="${i}-parsys" resourceType="foundation/components/parsys" />
   		 </div>
	</c:forEach>
</div>
 ${placeholder}
<!-- ================================ Grid End ================================ -->