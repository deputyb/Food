<script type="text/javascript">
    function tracknav(target) {
            if (CQ_Analytics.Sitecatalyst) {
                CQ_Analytics.record({
                    event: 'articleClick',
                    values: {
                        articleTitle: '${articleTitle}'
                        articleImagePath: '${imageLocation}'

                    },
                    componentPath: '<%=resource.getResourceType()%>'
                });
            }
    }
</script>  
