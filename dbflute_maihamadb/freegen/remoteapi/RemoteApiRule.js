// Based on ECMAScript5. Because Nashorn of java 8 is ECMAScript5.
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
/**
 * Return capitalize value.
 * @param {string} value - value.
 * @return {string} capitalize value.
 */
var _capitalize = function(value) {
    return value.replace(/(_|\.)./g, function(s) { return s.charAt(1).toUpperCase(); });
};

/**
 * Return capitalize value.
 * @param {string} value - value.
 * @return {string} capitalize value.
 */
var _decamelize = function(value) {
    return value.replace(/([A-Z][^A-Z])/g, function(s) { return '_' + s; }).toUpperCase().replace(/^_/, '');
};

/**
 * Return init cap value.
 * @param {string} value - value.
 * @return {string} init cap value.
 */
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

/**
 * Return scheme package.
 * @param {Api} api - API.
 * @return {string} scheme package.
 */
var schemePackage = function(scheme) {
    return _decamelize(scheme).replace(/_/g, '.').toLowerCase();
};

/**
 * Return sub package.
 * @param {Api} api - API.
 * @return {string} sub package.
 */
var subPackage = function(api) {
    return api.url.replace(/(_|^\/|\/$)/g, '').replace(/\/\{.*?\}/g, '').replace(/\//g, '.').toLowerCase();
};

// ===================================================================================
//                                                                               DiXml
//                                                                               =====
var diXmlPath = function(scheme, resourceFilePath) {
    return '../resources/remoteapi/di/remoteapi_' + schemePackage(scheme).replace(/\./g, '-') + '.xml';
}

var diconPath = function(scheme, resourceFilePath) {
    return '../resources/remoteapi/di/remoteapi_' + schemePackage(scheme).replace(/\./g, '-') + '.dicon';
}

// ===================================================================================
//                                                                            Behavior
//                                                                            ========
var behaviorClassGeneration = true;
var behaviorMethodGeneration = true;
var behaviorMethodAccessModifier = 'public';
var frameworkBehaviorClass = 'org.lastaflute.remoteapi.LastaRemoteBehavior';

/**
 * Return abstractBehaviorClassName.
 * @param {string} scheme - scheme.
 * @return {string} abstractBehaviorClassName.
 */
var abstractBehaviorClassName = function(scheme) {
    return 'AbstractRemote' + scheme + 'Bhv';
};

/**
 * Return filtered Behavior SubPackage.
 * @param {Api} api - API.
 * @return {string} filtered Behavior SubPackage.
 */
var behaviorSubPackage = function(api) {
    return subPackage(api).replace(/^([^.]*)\.(.+)/, '$1');
};

/**
 * Return bsBehaviorClassName.
 * @param {Api} api - API.
 * @return {string} bsBehaviorClassName.
 */
var bsBehaviorClassName = function(api) {
    return 'BsRemote' + api.scheme + _initCap(_capitalize(behaviorSubPackage(api))) + 'Bhv';
};

/**
 * Return exBehaviorClassName.
 * @param {Api} api - API.
 * @return {string} exBehaviorClassName.
 */
var exBehaviorClassName = function(api) {
    return 'Remote' + api.scheme + _initCap(_capitalize(behaviorSubPackage(api))) + 'Bhv';
};

/**
 * Return behaviorRequestMethodName.
 * @param {Api} api - API.
 * @return {string} behaviorRequestMethodName.
 */
var behaviorRequestMethodName = function(api) {
    var methodPart = _capitalize(subPackage(api).replace(behaviorSubPackage(api), ''));
    return 'request' + _initCap(methodPart) + (api.multipleHttpMethod ? _initCap(api.httpMethod) : '');
};

/**
 * Return behaviorRuleMethodName.
 * @param {Api} api - API.
 * @return {string} behaviorRuleMethodName.
 */
var behaviorRuleMethodName = function(api) {
    var methodPart = _capitalize(subPackage(api).replace(behaviorSubPackage(api), ''));
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
    var package = subPackage(api);
    if (package === behaviorSubPackage(api)) {
        package += '.index';
    }
    return package;
};
var definitionKey = function(definitionKey) { return definitionKey; };
var unDefinitionKey = function(definitionKey) { return definitionKey; };

var paramExtendsClass = null;
var paramImplementsClasses = null;

/**
 * Return paramClassName.
 * @param {Api} api - API.
 * @return {string} paramClassName.
 */
var paramClassName = function(api) {
    return 'Remote' + _initCap(_capitalize(subPackage(api))) + (api.multipleHttpMethod ? _initCap(api.httpMethod) : '') + 'Param';
};

var returnExtendsClass = null;
var returnImplementsClasses = null;

/**
 * Return returnClassName.
 * @param {Api} api - API.
 * @return {string} returnClassName.
 */
var returnClassName = function(api) {
    return 'Remote' + _initCap(_capitalize(subPackage(api))) + (api.multipleHttpMethod ? _initCap(api.httpMethod) : '') + 'Return';
};

/**
 * Return nestClassName.
 * @param {Api} api - API.
 * @param {string} className - className.
 * @return {string} nestClassName.
 */
var nestClassName = function(api, className) {
    return className;
};

/**
 * Return fieldName.
 * @param {Api} api - API.
 * @param {string} fieldName - fieldName.
 * @return {string} fieldName.
 */
var fieldName = function(api, fieldName) {
    return _capitalize(fieldName);
};

// ===================================================================================
//                                                                              Option
//                                                                              ======
/** yourCollections. e.g. org.eclipse.collections.api.list.ImmutableList */
var yourCollections = null;

/**
 * Return pathVariableManualMappingClass.
 * @param {Api} api - API.
 * @param {string} pathVariable - pathVariable.
 * @return {string} pathVariableManualMappingClass.
 */
var pathVariableManualMappingClass = function(api, pathVariable) {
    return null;
}

/**
 * Return pathVariableManualMappingClass.
 * @param {Api} api - API.
 * @param {string} beanClassName - beanClassName.
 * @param {string} property - property.
 * @return {string} pathVariableManualMappingClass.
 */
var beanPropertyManualMappingClass = function(api, beanClassName, property) {
    return null;
}

/**
 * Return pathVariableManualMappingDescription.
 * @param {Api} api - API.
 * @param {string} pathVariable - pathVariable.
 * @return {string} pathVariableManualMappingClass.
 */
var pathVariableManualMappingDescription = function(api, pathVariable) {
    return null;
}

/**
 * Return beanPropertyManualMappingDescription.
 * @param {Api} api - API.
 * @param {string} beanClassName - beanClassName.
 * @param {string} property - property.
 * @return {string} beanPropertyManualMappingDescription.
 */
var beanPropertyManualMappingDescription = function(api, beanClassName, property) {
    return null;
}
