/**
 * @author: Salvatore Randazzo
 * @email: salvatore.randazzo@valtech.com
 */

(function(win, doc, bb, we) {
    'use strict';

    we.models = we.models || {};

    we.models.pocModel = bb.Model.extend({
        rootUrl: 'http://default-environment-zq4zycj7uz.elasticbeanstalk.com/v1/products'
    });
})(window, document, Backbone, wegmans = wegmans || {});