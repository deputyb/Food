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

