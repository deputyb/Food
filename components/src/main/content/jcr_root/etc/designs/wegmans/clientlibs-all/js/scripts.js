/**
 * Globals namespace
 * @type {globals|*|{}}
 */
var globals = globals || {};
var wegmans = wegmans || {};

/**
 * Backbone globals
 */
wegmans.views = wegmans.views || {};
wegmans.models = wegmans.models || {};
wegmans.collections = wegmans.collections || {};
wegmans.routers = wegmans.routers || {};


/**
 * Underscore JSP override
 */
_.templateSettings = {
    evaluate:    /\{\{(.+?)\}\}/g,
    interpolate: /\{\{=(.+?)\}\}/g,
    escape:      /\{\{-(.+?)\}\}/g
};


/**
 * Example usage for globals
 * this global util returning currency number with comma
 * example: 1200 = 1,200
 */
globals.formatCurrency = function (number) {
    'use strict';

    return number.toLocaleString();
};

/*
 * Copyright 1997-2010 Day Management AG
 * Barfuesserplatz 6, 4001 Basel, Switzerland
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Day Management AG, ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Day.
 */
(function($) {
    $(function () {

        function removeHash(context) {
            try {
                if (window.location.hash.length > 0 && $(window.location.hash, context).length > 0) {
                    window.location = (window.location+'').replace(window.location.hash, '');
                }
            } catch (e) {
                errorLog(e, 'Could not remove hash');
            }
        }


        // Used to output caught errors
        function errorLog(error, message) {
            try {
                if ($.cq.isAuthor() || window.location.hash == '#debug') {
                    if (typeof console != 'undefined' && typeof console.log  != 'undefined') {
                        console.log(error);
                        console.log(message);
                    }
                    alert(error.name+':\n'+error.message+'.\n'+message+'.');
                }
            } catch (e) { }
        }

        try {
            // Opacity fading conflicts in IE8 with the PNG fix and text anti-aliasing
            var fadingSpeed = $.browser.msie ? 0 : 250;

            // Removes the URL hash if it corresponds to the id of an element in the given context


            // carousel code
            try {
                $('.cq-carousel').each(function () {
                    var carousel = $(this);
                    var playDelay = +$("var[title='play-delay']", this).text();
                    if (!playDelay) {
                        playDelay = 6000;
                    }
                    var slidingSpeed = +$("var[title='transition-time']", this).text();
                    if (!slidingSpeed) {
                        slidingSpeed = 1000;
                    }
                    var banners = $('.cq-carousel-banners', this);
                    //do not why, but
                    // var links = $('.cq-carousel-banner-switch a', this);
                    //returns more links than expected after component reload. Changed to "find" = works......
                    var switcher = $('.cq-carousel-banner-switch', this);
                    var links = switcher.find('a');
                    var items = $('.cq-carousel-banner-item', this);
                    var width = items.outerWidth();
                    var itemActive = items.filter(':first');
                    var itemPrevious = null;
                    var interval = null;
                    var i = 0;

                    var ctlPrev = $('a.cq-carousel-control-prev', this);
                    ctlPrev.click(function() {
                        if (ctlPrev.is('.cq-carousel-active')) {
                            $(links[(i+links.length-1)%links.length]).click();
                        }
                        return false;
                    });
                    var ctlNext = $('a.cq-carousel-control-next', this);
                    ctlNext.click(function() {
                        if (ctlNext.is('.cq-carousel-active')) {
                            $(links[(i+1)%links.length]).click();
                        }
                        return false;
                    });
                    if (links.length > 1) {
                        ctlNext.addClass('cq-carousel-active');
                    }
                    function play() {
                        stop();
                        if( playDelay > 0) {
                            interval = setInterval(function () {
                                $(links[(i+1)%links.length]).click();
                            }, playDelay);
                        }
                    }
                    function stop() {
                        if (interval !== null) {
                            clearInterval(interval);
                            interval = null;
                        }
                    }

                    // Show first item (needed for browsers that don't support CSS3 selector :first-of-type)
                    if (fadingSpeed || $.browser.version > 6) {
                        itemActive.css('left', 0);
                    } else {
                        itemActive.show();
                    }

                    links
                        .click(function () {
                            var link = $(this);
                            var itemNew = items.filter(link.attr('href'));
                            var j = itemNew.prevAll().length;
                            var direction = (j > i || interval !== null) ? 1 : -1;

                            if (!link.is('.cq-carousel-active')) {
                                links.removeClass('cq-carousel-active');
                                link.addClass('cq-carousel-active');

                                if (itemActive.is(':animated')) {
                                    itemActive.stop(true, true);
                                    itemPrevious.stop(true, true);
                                }

                                if (fadingSpeed) {
                                    itemNew.css({'left': direction*width}).animate({'left': 0, 'opacity': 1}, slidingSpeed);
                                    itemActive.animate({'left': -direction*width, 'opacity': 0}, slidingSpeed);
                                } else if ($.browser.version > 6) {
                                    itemNew.css({'left': direction*width, opacity: 1}).animate({'left': 0}, slidingSpeed);
                                    itemActive.animate({'left': -direction*width}, slidingSpeed);
                                } else {
                                    itemNew.fadeIn();
                                    itemActive.fadeOut();
                                }

                                itemPrevious = itemActive;
                                itemActive = itemNew;
                                i = j;
                                if (i > 0) {
                                    ctlPrev.addClass('cq-carousel-active');
                                } else {
                                    ctlPrev.removeClass('cq-carousel-active');
                                }
                                if (i < links.length-1) {
                                    ctlNext.addClass('cq-carousel-active');
                                } else {
                                    ctlNext.removeClass('cq-carousel-active');
                                }
                            }

                            return false;
                        })
                        .each(function () {
                            var link = $(this);

                            link.attr('title', link.text());
                        })
                        .filter(':first').addClass('cq-carousel-active');

                    play();
                    carousel.hover(
                            function() {
                                stop();
                                ctlPrev.fadeIn();
                                ctlNext.fadeIn();
                            },
                            function() {
                                play();
                                ctlPrev.fadeOut();
                                ctlNext.fadeOut();
                            }
                    );

                    // Accessing the page with the anchor of a banner in the URL can break the layout
                    removeHash(this);
                });
            } catch (e) {
                errorLog(e, 'Could not initialize the banners');
            }
        } catch (e) {
            errorLog(e, 'Init failed');
        }
    });
})($CQ || $);

(function($) {
    'use strict';
    /**
     * @private
     */
    function deepCss(who, css, ps) {
        var sty, dv = document.defaultView;
        if (document.body.currentStyle) {
            sty = css.replace(/\-([a-z])/g, function(a, b) {
                return b.toUpperCase();
            });
            return who.currentStyle[sty];
        }
        if (dv) {
            dv = document.defaultView.getComputedStyle(who, ps);
            return dv.getPropertyValue(css);
        }
        return '';
    }
    /**
     * @private
     */
    function getWindowDimension(d) {
        var firstChar = d.charAt(0).toUpperCase();
        var chars = d.split('');
        chars[0] = firstChar;
        d = chars.join('');
        if (typeof window.innerWidth != 'undefined') {
            return window['inner' + d];
        } else {
            if (typeof document.documentElement != 'undefined' && typeof document.documentElement.clientWidth != 'undefined' && document.documentElement.clientWidth !== 0) {
                return document.documentElement['client' + d];
            }
        }
        return 0;
    }
    /**
     * @private
     */
    function getWindowSizeEx(unitOfMeasurement) {
        if (unitOfMeasurement === 'px') {
            return getWindowDimension('width');
        }
        var fontSizeInPx = deepCss(document.body, 'font-size');
        if (fontSizeInPx.indexOf('px') === -1) {
            var fontSizeValue = parseInt(fontSizeInPx, 10) / 100;
            var numericValue = 16 * fontSizeValue;
            fontSizeInPx = numericValue + 'px';
        }
        var fontSize = parseInt(fontSizeInPx, 10);
        var width = getWindowDimension('width');
        var widthInEms = Math.floor(width / fontSize);
        return widthInEms;
    }

    /**
     * The handler used by each component when the window changes size. Be sure
     * to remove this when you are done!
     *
     * @private
     */
    function resizeHandler(evt) {
        var self = evt.data;
        var resizeTimeout = null;
        var period = 250;
        if (resizeTimeout !== null) {
            window.clearTimeout(resizeTimeout);
            resizeTimeout = window.setTimeout(function() {
                self.render();
                resizeTimeout = null;
            }, period);
        } else {
            self.render();
            resizeTimeout = window.setTimeout(function() {
                resizeTimeout = null;
            }, period);
        }
    }

    /**
     * @constructor
     */
    function ResponsiveImage() {
        this.units = 'px';
        if (arguments.length === 3) {
            this.init.apply(this, arguments);
        }
    }
    /**
     * @param {String|DOMElement|jQueryElement} el
     * @param {Array<Number>} breakpoints Should be 2 breakpoints, but may work with more.
     * @param {Array<String>} srcs Array of image path strings. The length of this array should breakpoints.length + 1.
     */
    ResponsiveImage.prototype.init = function(el, breakpoints, srcs) {
        breakpoints.unshift(0);
        this.el = $(el);
        this.breakpoints = breakpoints;
        this.srcs = srcs;
        this.breakpointsLen = this.breakpoints.length;
        this.render();
        $(window).on('resize', this, resizeHandler);
    };
    /**
     * Clean up the component, especially the handler.
     */
    ResponsiveImage.prototype.dispose = function() {
        $(window).off('resize', resizeHandler);
        this.el = null;
        this.breakpoints = null;
        this.srcs = null;
        this.breakpointsLen = null;
    };
    /**
     * Renders the image according to the rules.
     */
    ResponsiveImage.prototype.render = function() {
        var winSize = getWindowSizeEx(this.units);
        var src = '';
        for (var index = 0; index < this.breakpointsLen; index++) {
            var breakpoint = this.breakpoints[index];
            if (winSize >= breakpoint) {
                src = this.srcs[index];
            } else {
                break;
            }
        }
        this.el.attr('src', src);
    };

    $.ResponsiveImage = ResponsiveImage;

    // This will happen later.
    $(function() {

        /**
         * Description
         * @param {String} path A path passed in by jcr.
         * @return {Boolean} If path is valid.
         */
        function validImagePath(path) {
            return (path.length > 0) && (path !== 'Placeholder');
        }

        /**
         * Description
         * @param {DOMElement} parentDiv Wrapper for a "responsive-image" component. This wrapper contains the div declared in JSP.
         */
        function initResponsiveImage(parentDiv) {
            parentDiv = $($(parentDiv).find('div')[0]);
            // TODO: add params for breakpoint array
            var mobileImage = parentDiv.attr('mobileImagePath');
            var tabletImage = parentDiv.attr('tabletImagePath');
            var desktopImage = parentDiv.attr('desktopImagePath');
            var selector = $(parentDiv.find('img')[0]);
            if (validImagePath(mobileImage) && validImagePath(tabletImage) && validImagePath(desktopImage)) {
                if (!selector.data('initialized')) {
                    var instance = new $.ResponsiveImage(selector, [480, 768], [mobileImage, tabletImage,
                        desktopImage
                    ]);
                    instance.render();
                    selector.data('initialized', true);
                }
            } else {
                selector.css('min-height', '64px');
                selector.css('min-width', '64px');
            }
        }
        var components = $(".responsive-image");
        // Loop through every component of class 'responsive-image'.
        for (var index = 0; index < components.length; index++) {
            var component = components[index];
            initResponsiveImage(component);
        }
    });

    return ResponsiveImage;
}(window.jQuery));
/*global CQ_Analytics, CQ_Analytics.Sitecatalyst */
$(document).ready(function () {
    "use strict";

    var rssfeedElement = $('#rssfeed');
    var feedUrl = rssfeedElement.data('feed-url');
    var noOfItems = rssfeedElement.data('nbr-items');
    var showFullStory = rssfeedElement.data('full-articles');


    $.ajax({
        url: document.location.protocol + '//ajax.googleapis.com/ajax/services/feed/load?v=1.0&num=' + noOfItems +
             '&callback=?&q=' + encodeURIComponent(feedUrl),
        dataType: 'json',
        success: function (data) {
            /** @namespace data.responseData.feed */
            var channel = data.responseData.feed.entries;
            $.each(channel, function (index, item) {
                if (item.title) {
                    rssfeedElement.append($('<h3/>').html(item.title));
                }
                if (item.author && showFullStory) {
                    rssfeedElement.append($('<h4/>').html(item.author));
                }
                if (item.title && showFullStory) {
                    rssfeedElement.append($('<p/>').html(item.content));
                }
            });
            var componentPath = rssfeedElement.data('component-path');
            tracking(componentPath, feedUrl, noOfItems, showFullStory);
        }
    });

    function tracking(componentPath, url, items, showFull) {
        /** @namespace CQ_Analytics.Sitecatalyst */
        if (CQ_Analytics.Sitecatalyst) {
            CQ_Analytics.record({
                event: 'feed',
                values: {
                    rssFeedURL: url,
                    noOfItems: items,
                    showFullStory: showFull
                },
                componentPath: componentPath
            });
        }
    }
});

/**
 * @author Salvatore Randazzo
 */

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


        /**
         * Initializer
         */
        var init = function () {

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
                        viewsInstances.push(new wegmans.views[view]({
                            el: $(component),
                            collection: wegmans.collections[collection] !== undefined ? new wegmans.collections[collection]() : null
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

//console.log('---> INIT Welcome');
//$(document).foundation();
//# sourceMappingURL=scripts.js.map
