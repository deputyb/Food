<%@include file="/apps/wegmans/components/global/global.jsp" %>


<sl:adaptTo adaptable="${slingRequest}" adaptTo="com.wegmans.components.EditModel" var="model"/>

<header>
    <div class="row">
        <div class="large-12 columns">
            <c:choose><c:when test="${model.configurable}">

                <div>
                    <div>STORE LOCATOR</div>
                    <div>EYEBROW NAVIGATION <BR><cq:include path="eyebrowNavigation" resourceType="wegmans/components/content/navigation/eyebrow" /></div>
                </div>
                <div>
                    <div>Logo</div>
                    <div>PRIMARY NAVIGATION <BR><cq:include path="primaryNavigation" resourceType="wegmans/components/content/primary-navigation" /></div>
                </div>
                
            </c:when><c:otherwise>

            <w:disableEdit>
                <div>
                    <div>STORE LOCATOR</div>
                    <div>EYEBROW NAVIGATION <BR><cq:include path="eyebrowNavigation" resourceType="wegmans/components/content/navigation/eyebrow" /></div>
                </div>
                <div>
                    <div>Logo</div>
                    <div>PRIMARY NAVIGATION <BR><cq:include path="primaryNavigation" resourceType="wegmans/components/content/primary-navigation" /></div>
                </div>
            </w:disableEdit>

            </c:otherwise></c:choose>
        </div>
    </div>
</header>
