/**
 * @author Salvatore Randazzo
 */

// Resetting $ to jquery, if we decide to remove jquery dependency w can do it from here
Backbone.$ = jQuery;

(function ($, wegmans) {
    'use strict';

    /**
     * Loader
     * @returns {{init: Function}}
     * @constructor
     */

    var Loader = function () {
        var components = null;
        var view = '';
        var model = '';
        var collection = '';
        var viewsInstances = [];
        var tempCollection = null;

        /**
         * Initializer
         */
        var init = function () {

            Backbone.$ = $;

            components = $('[data-view]');


            _.each(components, function (component) {
                view =  $(component).attr('data-view');
                model = $(component).attr('data-model');
                collection = $(component).attr('data-collection');

                if (wegmans.views[view] !== undefined) {

                    if (collection === undefined) {
                        viewsInstances.push(new wegmans.views[view]({
                            el: $(component),
                            model: wegmans.models[model] !== undefined ? new wegmans.models[model]() : null
                        }));
                    } else {
                        tempCollection = _.find(wegmans.dataLoader.collections, (dataLoaderCollection) => {
                            return dataLoaderCollection.name === collection;
                        });

                        if(!tempCollection) {
                            console.error('Probably caused by not found collection with name ' + collection +
                                ' please check that the name matches an actual package. Available packages:', wegmans.dataLoader.collections);
                        }

                        viewsInstances.push(new wegmans.views[view]({
                            el: $(component),
                            collection: tempCollection.object
                        }));
                    }

                } else {
                    throw new Error ('No view found for ' + view );
                }

            });


        };

        // Public
        return {
            init: init,
            viewInstances: viewsInstances
        };
    };

    // Starting the application
    window.loader = new Loader();
    window.loader.init();

})(jQuery, wegmans);
