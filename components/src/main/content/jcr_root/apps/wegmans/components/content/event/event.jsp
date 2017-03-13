<%@include file="/apps/wegmans/components/global/global.jsp"%>
<!-- ================================ Event Start ================================ -->
<%@page import="com.wegmans.components.multimedia.EventModel"%>
<%@taglib prefix="sl" uri="http://sling.apache.org/taglibs/sling" %>
<sl:adaptTo adaptable="${slingRequest}" adaptTo="com.wegmans.components.multimedia.EventModel" var="event"/>
<cq:defineObjects />
<w:placeholder/>

<div>

	<h2>${event.eventTitle}</h2>
	<h2>${event.eventSubTitle}</h2>

    <c:if test="${not empty event.imageLocation}">
        <img  src="${event.imageLocation}" alt="" style="width:100%;">
    </c:if>

	<p>${event.eventDetail}</p>
	<p>Is Global: ${event.global}</p>
	<c:if test="${not event.global}">
	    <p>Stores: ${event.stores}</p>
	</c:if>

</div>
 ${placeholder}
<%@include file="/apps/wegmans/components/content/event/tracking-js.jsp"%>
<!-- ================================ Event End ================================ -->