<!--TODO: REMOVE ALL JAVA CODE INTO A CUSTOM TAG -->
<%@include file="/apps/wegmans/components/global/global.jsp"%>
<!-- ================================ Sitemap Start ================================ -->
<w:placeholder />
<%@ page import="com.day.cq.wcm.foundation.Sitemap,
                     com.day.cq.wcm.api.PageFilter,
                     com.day.cq.wcm.api.PageManager"%><%

    String rootPath = properties.get("rootPath", "");
    if (rootPath.length() > 0) {
        if (rootPath.startsWith("path:")) {
            rootPath = rootPath.substring(5,rootPath.length());
        }
    } else {
        long absParent = currentStyle.get("absParent", 2L);
        rootPath = currentPage.getAbsoluteParent((int) absParent).getPath();
    }

    %><div class="text"><%
    Page rootPage = slingRequest.getResourceResolver().adaptTo(PageManager.class).getPage(rootPath);
    Sitemap stm = new Sitemap(rootPage);
    stm.draw(out);
    %>

</div>
${placeholder}
<!-- ================================ Sitemap End ================================ -->
