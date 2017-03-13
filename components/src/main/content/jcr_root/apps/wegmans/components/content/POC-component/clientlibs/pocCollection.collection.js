/**
 * @author: Salvatore Randazzo
 * @email: salvatore.randazzo@valtech.com
 */

(function(win, doc, bb, we) {
    'use strict';

    we.collections = we.collections || {};

    we.collections.pocCollection = bb.Collection.extend({
        url: 'http://default-environment-zq4zycj7uz.elasticbeanstalk.com/v1/products',
        model: we.models.pocModel,
        /**
         * Method used to change the rood of the collection object as the current product object is not only a list or products
         * but contains more elements.
         *
         * @param resp
         * @param xhr
         * @returns {*}
         */
        parse: function(resp, xhr) {
            return resp.resources;
        }
    });
})(window, document, Backbone, wegmans = wegmans || {});