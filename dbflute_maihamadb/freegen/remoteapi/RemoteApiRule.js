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

/**
 * PathVariable Type.
 * @typedef {Object} PathVariable
 */

/**
 * Property Type.
 * @typedef {Object} Property
 */

var baseRemoteApiRule = {

    // ===================================================================================
    //                                                                               Base
    //                                                                              ======
    /**
     * Return true if target.
     * @param {Api} api - API.
     * @return {boolean} true if target.
     */
    target : function(api) {
        var contentTypes = [];
        Array.prototype.push.apply(contentTypes, api.consumes ? api.consumes : []);
        Array.prototype.push.apply(contentTypes, api.produces ? api.produces : []);
        return (contentTypes.indexOf('application/json') != -1) && api.url.indexOf('/swagger/json') != 0;
    },

    /**
     * Return filtered URL.
     * @param {Api} api - API.
     * @return {boolean} filtered URL.
     */
    url : function(api) { return api.url; },

    /**
     * Return scheme package.
     * @param {Api} api - API.
     * @return {string} scheme package.
     */
    schemePackage : function(scheme) {
        return manager.decamelize(scheme).replace(/_/g, '.').toLowerCase();
    },

    /**
     * Return sub package.
     * @param {Api} api - API.
     * @return {string} sub package.
     */
    subPackage : function(api) {
        return api.url.replace(/(_|^\/|\/$)/g, '').replace(/\/\{.*?\}/g, '').replace(/\//g, '.').toLowerCase();
    },

    // ===================================================================================
    //                                                                               DiXml
    //                                                                               =====
    diXmlPath : function(scheme, resourceFilePath) {
        return '../resources/remoteapi/di/remoteapi_' + this.schemePackage(scheme).replace(/\./g, '-') + '.xml';
    },

    diconPath : function(scheme, resourceFilePath) {
        return '../resources/remoteapi/di/remoteapi_' + this.schemePackage(scheme).replace(/\./g, '-') + '.dicon';
    },

    // ===================================================================================
    //                                                                            Behavior
    //                                                                            ========
    behaviorClassGeneration : true,
    behaviorMethodGeneration : true,
    behaviorMethodAccessModifier : 'public',
    frameworkBehaviorClass : 'org.lastaflute.remoteapi.LastaRemoteBehavior',

    /**
     * Return abstractBehaviorClassName.
     * @param {string} scheme - scheme.
     * @return {string} abstractBehaviorClassName.
     */
    abstractBehaviorClassName : function(scheme) {
        return 'AbstractRemote' + scheme + 'Bhv';
    },

    /**
     * Return filtered Behavior SubPackage.
     * @param {Api} api - API.
     * @return {string} filtered Behavior SubPackage.
     */
    behaviorSubPackage : function(api) {
        return this.subPackage(api).replace(/^([^.]*)\.(.+)/, '$1');
    },

    /**
     * Return bsBehaviorClassName.
     * @param {Api} api - API.
     * @return {string} bsBehaviorClassName.
     */
    bsBehaviorClassName : function(api) {
        return 'BsRemote' + api.scheme + manager.initCap(manager.camelize(this.behaviorSubPackage(api).replace(/\./g, '_'))) + 'Bhv';
    },

    /**
     * Return exBehaviorClassName.
     * @param {Api} api - API.
     * @return {string} exBehaviorClassName.
     */
    exBehaviorClassName : function(api) {
        return 'Remote' + api.scheme + manager.initCap(manager.camelize(this.behaviorSubPackage(api).replace(/\./g, '_'))) + 'Bhv';
    },

    /**
     * Return behaviorRequestMethodName.
     * @param {Api} api - API.
     * @return {string} behaviorRequestMethodName.
     */
    behaviorRequestMethodName : function(api) {
        var methodPart = manager.camelize(this.subPackage(api).replace(this.behaviorSubPackage(api), '').replace(/\./g, '_'));
        return 'request' + manager.initCap(methodPart) + (api.multipleHttpMethod ? manager.initCap(api.httpMethod) : '');
    },

    /**
     * Return behaviorRuleMethodName.
     * @param {Api} api - API.
     * @return {string} behaviorRuleMethodName.
     */
    behaviorRuleMethodName : function(api) {
        var methodPart = manager.camelize(this.subPackage(api).replace(this.behaviorSubPackage(api), '').replace(/\./g, '_'));
        return 'ruleOf' + manager.initCap(methodPart) + (api.multipleHttpMethod ? manager.initCap(api.httpMethod) : '');
    },

    // ===================================================================================
    //                                                                        Param/Return
    //                                                                        ============
    /**
     * Return filtered Bean SubPackage.
     * @param {Api} api - API.
     * @return {string} filtered Bean SubPackage.
     */
    beanSubPackage : function(api) {
        var package = this.subPackage(api);
        if (package === this.behaviorSubPackage(api)) {
            package += '.index';
        }
        return package;
    },
    definitionKey : function(definitionKey) { return definitionKey; },
    unDefinitionKey : function(definitionKey) { return definitionKey; },

    paramExtendsClass : null,
    paramImplementsClasses : null,

    /**
     * Return paramClassName.
     * @param {Api} api - API.
     * @return {string} paramClassName.
     */
    paramClassName : function(api) {
        return 'Remote' + manager.initCap(manager.camelize(this.subPackage(api).replace(/\./g, '_'))) + (api.multipleHttpMethod ? manager.initCap(api.httpMethod) : '') + 'Param';
    },

    returnExtendsClass : null,
    returnImplementsClasses : null,

    /**
     * Return returnClassName.
     * @param {Api} api - API.
     * @return {string} returnClassName.
     */
    returnClassName : function(api) {
        return 'Remote' + manager.initCap(manager.camelize(this.subPackage(api).replace(/\./g, '_'))) + (api.multipleHttpMethod ? manager.initCap(api.httpMethod) : '') + 'Return';
    },

    /**
     * Return nestClassName.
     * @param {Api} api - API.
     * @param {string} className - className.
     * @return {string} nestClassName.
     */
    nestClassName : function(api, className) {
        return className.replace(/(Part|Result|Model|Bean)$/, '') + 'Part';
    },

    /**
     * Return fieldName.
     * @param {Api} api - API.
     * @param {string} fieldName - fieldName.
     * @return {string} fieldName.
     */
    fieldName : function(api, fieldName) {
        return manager.initUncap(manager.camelize(fieldName));
    },

    // ===================================================================================
    //                                                                              Option
    //                                                                              ======
    /**
     * Return java property type mapping.
     * e.g. java.util.List -> org.eclipse.collections.api.list.ImmutableList, java.time.LocalDate -> String etc.
     * @return typeMap The map of type conversion, swagger type to java type. (NotNull)
     */
    typeMap: function() {
        return {
            'object': 'Object',
            'int32': 'Integer',
            'int64': 'Long',
            'float': 'Float',
            'double': 'Double',
            'string': 'String',
            'byte': 'byte[]',
            'binary': 'org.lastaflute.web.ruts.multipart.MultipartFormFile',
            'file': 'org.lastaflute.web.ruts.multipart.MultipartFormFile',
            'boolean': 'Boolean',
            'date': 'java.time.LocalDate',
            'date-time': 'java.time.LocalDateTime',
            'array': 'java.util.List'
        };
    },

    /**
     * Return pathVariableManualMappingClass.
     * @param {Api} api - API.
     * @param {PathVariable} pathVariable - pathVariable.
     * @return {string} pathVariableManualMappingClass.
     */
    pathVariableManualMappingClass : function(api, pathVariable) {
        return null;
    },

    /**
     * Return pathVariableManualMappingClass.
     * @param {Api} api - API.
     * @param {string} beanClassName - beanClassName.
     * @param {Property} property - property.
     * @return {string} pathVariableManualMappingClass.
     */
    beanPropertyManualMappingClass : function(api, beanClassName, property) {
        return null;
    },

    /**
     * Return pathVariableManualMappingDescription.
     * @param {Api} api - API.
     * @param {PathVariable} pathVariable - pathVariable.
     * @return {string} pathVariableManualMappingClass.
     */
    pathVariableManualMappingDescription : function(api, pathVariable) {
        return null;
    },

    /**
     * Return beanPropertyManualMappingDescription.
     * @param {Api} api - API.
     * @param {string} beanClassName - beanClassName.
     * @param {Property} property - property.
     * @return {string} beanPropertyManualMappingDescription.
     */
    beanPropertyManualMappingDescription : function(api, beanClassName, property) {
        return null;
    }
};

var remoteApiRule = {};
for (var i in baseRemoteApiRule) {
    remoteApiRule[i] = baseRemoteApiRule[i];
}
