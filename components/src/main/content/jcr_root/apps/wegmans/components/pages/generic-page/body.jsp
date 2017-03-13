<%@include file="/apps/wegmans/components/global/global.jsp"%>

<body>
    <div id="CQ"><div class="content">
    <!-- DO Not Change Above DIV Structure -->

        <!-- TODO: remove after first real implementation -->
        <div class="component cp-poc-test" data-view="pocView" data-collection="pocCollection">
            <script type="text/x-handlebars-template">
                {{#each this}}
                    <h1>Product: {{name}}</h1>
                {{/each}}
            </script>
        </div>
        <!-- /TODO -->


        <div class="row">
            <div class="large-12 columns">
                <!-- Header Include -->
                <cq:include script="header.jsp" />
            </div>
        </div>

        <cq:include script="content.jsp" />

        <div class="row">
            <div class="large-12 columns">
                <!-- Footer Include -->
                <cq:include script="footer.jsp" />
            </div>
        </div>



    <!-- DO Not Change Below DIV Structure -->
    </div></div>
</body>