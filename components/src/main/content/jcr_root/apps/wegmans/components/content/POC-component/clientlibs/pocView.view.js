/**
 * @author: Salvatore Randazzo
 * @email: salvatore.randazzo@valtech.com
 */

(function(win, doc, bb, we) {
    'use strict';

    we.views = we.views || {};

    we.views.pocView = bb.View.extend({

        /**
         *
         * @param options
         */
        initialize: function(options) {
            console.log('View: we.views.pocView -> loaded', this);
            this.rawTemplate = this.$('script').html();
            this.template = Handlebars.compile(this.rawTemplate);
            this.collection.hasFetched.promise.then(this.render.bind(this));
        },

        /**
         *
         */
        render: function () {
            this.$el.html(this.template(this.collection.toJSON()));
        }
    });
})(window, document, Backbone, wegmans = wegmans || {}, jQuery);