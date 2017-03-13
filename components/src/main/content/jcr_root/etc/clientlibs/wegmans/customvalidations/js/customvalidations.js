(function($, Granite) {
    "use strict";
    
    /**
     * Testing custom validation. It's enough to add validation="wegmans.test" to widget inside dialog to trigger it.
     */
    $.validator.register({
        selector: '[data-validation="wegmans.test"]',
        validate: function(el) {
            var valid = el.val() == 'test';
            
            if (!valid) {
                return Granite.I18n.get("Value must be 'test'!");
            }
        }
    });
})(Granite.$, Granite);
