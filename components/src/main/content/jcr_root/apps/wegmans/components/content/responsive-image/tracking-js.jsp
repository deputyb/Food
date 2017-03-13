<script type="text/javascript">
    function tracknav(target) {
            if (CQ_Analytics.Sitecatalyst) {
                CQ_Analytics.record({
                    event: 'imageResponse',
                    values: {
                        tabletImage: '${tabletImage}',
                        desktopImage:  '${desktopImage}',
                            mobileImage: '${mobileImage}'    

                    },
                    componentPath: '<%=resource.getResourceType()%>'
                });
            }
    }
</script>  
