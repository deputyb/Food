<%@include file="/apps/wegmans/components/global/global.jsp"%>
<sl:adaptTo adaptable="${slingRequest}" adaptTo="com.wegmans.components.EditModel" var="model"/>

<footer>
    <div class="row">
        <div class="large-12 columns">
            <c:choose><c:when test="${model.configurable}">
                <cq:include path="footer" resourceType="wegmans/components/content/navigation/footer" />
            </c:when><c:otherwise>
                <w:disableEdit>
                    <cq:include path="footer" resourceType="wegmans/components/content/navigation/footer" />
                </w:disableEdit>
            </c:otherwise></c:choose>
        </div>
    </div>
</footer>

<script src="/etc/clientlibs/wegmans/clientlibs-all/js/ui.bundle.js"></script>
