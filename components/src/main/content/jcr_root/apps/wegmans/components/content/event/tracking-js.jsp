<script type="text/javascript">
    function tracknav(target) {
            if (CQ_Analytics.Sitecatalyst) {
                CQ_Analytics.record({
                    event: 'eventClick',
                    values: {
                        eventTitle: '${eventTitle}'
                        eventImagePath: '${imageLocation}'

                    },
                    componentPath: '<%=resource.getResourceType()%>'
                });
            }
    }
</script>  
