// ===================================================================================
//                                                                          Definition
//                                                                          ==========
/**
 * API Type.
 * @typedef {Object} Api
 * @property {string} api.scheme - Scheme.
 * @property {string} api.url - URL.
 * @property {string} api.httpMethod - HttpMethod.
 * @property {string[]} api.consumes - Consumes.
 * @property {string[]} api.produces - Produces.
 */

// ===================================================================================
//                                                                                Util
//                                                                                ====
var _package = function(api) {
    return api.url.replace(/(_|^\/|\/$)/g, '').replace(/\/\{.*?\}/g, '').replace(/\//g, '.').toLowerCase();
};

var _capitalize = function(value) {
    return value.replace(/(_|\.)./g, function(s) { return s.charAt(1).toUpperCase(); });
};
var _initCap= function(value) {
    return value.charAt(0).toUpperCase() + value.slice(1);
};

// ===================================================================================
//                                                                                Base
//                                                                                ====
/**
 * Return true if target.
 * @param {Api} api - API.
 * @return {boolean} true if target.
 */
var target = function(api) {
    return api.produces.indexOf('application/json') != -1 && api.url.indexOf('/swagger/json') != 0;
};

/**
 * Return filtered URL.
 * @param {Api} api - API.
 * @return {boolean} filtered URL.
 */
var url = function(api) { return api.url; };

// ===================================================================================
//                                                                            Behavior
//                                                                            ========
var behaviorClassGeneration = true;
var behaviorMethodGeneration = true;
var behaviorMethodAccessModifier = 'public';
var frameworkBehaviorClass = 'org.lastaflute.remoteapi.LastaRemoteBehavior';
var abstractBehaviorClassName = function(scheme) {
    return 'RemoteAbstract' + scheme + 'Bhv';
};

/**
 * Return filtered Behavior SubPackage.
 * @param {Api} api - API.
 * @return {string} filtered Behavior SubPackage.
 */
var behaviorSubPackage = function(api) {
    return _package(api).replace(/^([^.]*)\.(.+)/, '$1');
};
var bsBehaviorClassName = function(api) {
    return 'Remote' + api.scheme + 'Bs' + _initCap(_capitalize(behaviorSubPackage(api))) + 'Bhv';
};
var exBehaviorClassName = function(api) {
    return 'Remote' + api.scheme + _initCap(_capitalize(behaviorSubPackage(api))) + 'Bhv';
};
var behaviorRequestMethodName = function(api) {
    var methodPart = _capitalize(_package(api).replace(/^([^.]*)\.(.+)/, '$2'));
    return 'request' + _initCap(methodPart) + (api.multipleHttpMethod ? _initCap(api.httpMethod) : '');
};
var behaviorRuleMethodName = function(api) {
    var methodPart = _capitalize(_package(api).replace(/^([^.]*)\.(.+)/, '$2'));
    return 'ruleOf' + _initCap(methodPart) + (api.multipleHttpMethod ? _initCap(api.httpMethod) : '');
};

// ===================================================================================
//                                                                                Bean
//                                                                                ====
/**
 * Return filtered Bean SubPackage.
 * @param {Api} api - API.
 * @return {string} filtered Bean SubPackage.
 */
var beanSubPackage = function(api) {
    return _package(api);
};
var definitionKey = function(definitionKey) { return definitionKey; };
var unDefinitionKey = function(definitionKey) { return definitionKey; };

var requestExtendsClass = null;
var requestImplementsClasses = null;
var requestClassName = function(api) {
    return 'Remote' + _initCap(_capitalize(beanSubPackage(api))) + (api.multipleHttpMethod ? _initCap(api.httpMethod) : '') + 'Param';
};
var responseExtendsClass = null;
var responseImplementsClasses = null;
var responseClassName = function(api) {
    return 'Remote' + _initCap(_capitalize(beanSubPackage(api))) + (api.multipleHttpMethod ? _initCap(api.httpMethod) : '') + 'Return';
};
var nestClassName = function(scheme, className) { return className; };
var fieldName = function(scheme, fieldName) {
    return fieldName.replace(/_./g, function(s) { return s.charAt(1).toUpperCase(); });
};

// ===================================================================================
//                                                                              Option
//                                                                              ======
// when using Eclipse Collections. var yourCollections = 'org.eclipse.collections.api.list.ImmutableList';
var yourCollections = null;
