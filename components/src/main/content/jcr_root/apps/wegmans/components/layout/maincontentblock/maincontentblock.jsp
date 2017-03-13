<%@include file="/apps/wegmans/components/global/global.jsp"%>

<sl:adaptTo adaptable="${resource}" adaptTo="com.wegmans.components.globals.BodyModel" var="bodymodel"/>


<!--need to do a editmode check -->

<c:if test="${enableEdit}" >

<div>

    <h2>Select a layout</h2>

</div>

</c:if>

<!--end edit mode check-->

<cq:include script="${bodymodel.bodyLayout}.jsp" />
