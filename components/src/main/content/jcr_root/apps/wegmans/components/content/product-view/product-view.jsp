<%@include file="/apps/wegmans/components/global/global.jsp"%>
<sl:adaptTo adaptable="${resource}" adaptTo="com.wegmans.components.products.ProductViewModel" var="model"/>
<!-- ================================ Product View Start ============================== -->

<h1>Product View</h1>

<c:if test="${not empty model.productPath}">
    <div id="product-view"></div>
    
    <script id="product-template" type="text/x-handlebars-template">
    <div class="product">
        <h2>{{name}}</h2>
        <div class="body">
            <p>{{details}}</p>
        </div>
    </div>
    </script>
    
    <script type="text/javascript">
        // define model for a single product
        var Product = Backbone.Model.extend({ 
        });

        // define view for a single product
        var ProductView = Backbone.View.extend({
            render: function() {
                var source = $('#product-template').html();
                var template = Handlebars.compile(source);
                var html = template(this.model.toJSON());
                
                this.el.html(html);
            }
        });

        $(document).ready(function() {
            $.ajax({
                type : "GET",
                url : "${model.productPath}",
                async : false,
                success : function(response) {
                    var product = new Product(response);
                    var productView = new ProductView({
                        model: product
                    });

                    productView.el = $('#product-view');
                    productView.render();
                }
            });
        });

    </script>
</c:if>

<!-- ================================ Product View End ================================ -->