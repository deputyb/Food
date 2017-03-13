<%@include file="/apps/wegmans/components/global/global.jsp"%>
<!-- ================================ Slideshow Start ================================ -->

<w:slideshow />
<w:placeholder />
<div class="${ddClassName}">
    <div id="${id}">
      <p></p>
    </div>
</div>

<script type="text/javascript">
    function redraw(id) {
        document.getElementById(id).redraw();
    }
    function getSlides(url) {
        var noCacheUrl = url + "?cq_ck=" + new Date().valueOf();
        return $CQ.parseJSON($CQ.ajax({
            "url" : noCacheUrl,
            "async" : false,
            "dataType" : "json"
        }).responseText);
    }
</script>

<script type="text/javascript">
    var flashvars = {
        contentPath: "${contentPath}",
        backgroundColor : "${backgroundColor}",
        webAppContextPath : "${contextPath}"
    };
    var params = {
        menu: "false",
        wmode: "opaque"
    };
    var attributes = {};
    if( window.CQ_swfobject) CQ_swfobject.embedSWF("${slideshowURL}", "${encodeForId}", "${SlideShowWidth}", "${SlideShowHeight}", "9.0.0", "${expressInstallURL}", flashvars, params, attributes);
</script>
${placeholder}
<!-- ================================ Slideshow End ================================ -->
