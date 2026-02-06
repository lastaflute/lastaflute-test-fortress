// Based on ECMAScript5. Because Nashorn of java 8 is ECMAScript5.
// =======================================================================================
//                                                                              Definition
//                                                                              ==========
/**
 * API Type.
 * @typedef {Object} Api
 * @property {string} - schema of the remote api. (NotNull, NotEmpty)
 * @property {string} url - target url of the remote api. (NotNull, NotEmpty)
 * @property {string} httpMethod - target httpMethod of the remote api. (NotNull, NotEmpty)
 * @property {string[]} consumes - target consumes of the remote api. (NotNull, EmptyAllowed)
 * @property {string[]} produces - target produces of the remote api. (NotNull, EmptyAllowed)
 */

/**
 * PathVariable Type.<br/>
 * see below for details.<br/>
 * <ul>
 *   <li>https://swagger.io/specification/v2/#parameter-object</li>
 *   <li>https://swagger.io/specification/v2/#data-types</li>
 * </ul>
 * @typedef {Object} PathVariable
 * @property {string} name - path variable name. (NotNull, NotEmpty)
 * @property {string} in - parameter location. 'path' fixed. (NotNull, NotEmpty)
 * @property {boolean} required - true if the parameter is required. (NotNull)
 * @property {string} description - parameter description. (NullAllowed)
 * @property {string} type - parameter type. e.g. string, integer, number, boolean, array (NotNull, NotEmpty)
 * @property {string} format - parameter format. e.g. int32, int64, float, double, byte, binary, date, date-time, password (NullAllowed)
 * @property {string} default - parameter default. (NullAllowed)
 * @property {Items} items - required if type is "array". describes the type of items in the array.. (NullAllowed)
 * @property {Schema} schema - property schema. (NullAllowed)
 * @property {string} enum - lists possible values. (NullAllowed)
 */

/**
 * Property(query、formData、body field) Type.<br/>
 * see below for details.<br/>
 * <ul>
 *   <li>https://swagger.io/specification/v2/#parameter-object</li>
 *   <li>https://swagger.io/specification/v2/#data-types</li>
 * </ul>
 * @typedef {Object} Property
 * @property {string} name - property(query、formData、body field) name. (NotNull, NotEmpty)
 * @property {string} in - parameter location. 'path' fixed. (NotNull, NotEmpty)
 * @property {boolean} required - true if the parameter is required. (NotNull)
 * @property {string} description - parameter description. (NullAllowed)
 * @property {string} type - parameter type. e.g. string, integer, array (NotNull, NotEmpty)
 * @property {string} format - parameter format. (NullAllowed)
 * @property {string} default - parameter default. (NullAllowed)
 * @property {Items} items - required if type is "array". describes the type of items in the array.. (NullAllowed)
 * @property {Schema} schema - property schema. (NullAllowed)
 * @property {string} enum - lists possible values. (NullAllowed)
 */

/**
 * Top Level Bean Type.
 * Information on the top class of beans in param and return. That is, it is not the nested part class.
 * About remoteApiExp.
 * Used in the description of javadoc.
 * The values that can be taken differ for each automatically generated class.
 * For Top Level Beans, the URL of the remote API on which this class is used is set.
 * @typedef {Object} TopLevelBean
 * @property {Api} api - API. (NotNull)
 * @property {string} package - Package of this class. (NotNull, NotEmpty)
 * @property {string} className - class name without package of this class.  (NotNull, NotEmpty)
 * @property {string} definitionKey - The definition key for the swagger specification. (NotNull, NotEmpty)
 * @property {string} extendsClass - Extends class with package of this class. (NullAllowed)
 * @property {string} implementsClasses - Implements classes (Interface) with package of this class. (NullAllowed)
 * @property {Property[]} properties - Properties of this class. (NotNull, EmptyAllowed)
 * @property {string} beanPurposeType - bean purpose type of this class. e.g. param, return (NotNull, NotEmpty)
 * @property {string} remoteApiExp - remote api expression. See the description of the Top Level Bean itself for details. (NotNull, NotEmpty)
 * @property {Object} definitionMap - All schema definitions for remote api. (NotNull)
 * @property {string} in - Type of this class. e.g. json, xml (NotNull, NotEmpty)
 */

var baseRule = {

    // ===================================================================================
    //                                                                               Const
    //                                                                               =====
    /**
     * Field naming rules.<br/>
     * Generally, the API Field is a snake case, and Java is a camel case, so it is used to fill the gap.
     */
    FIELD_NAMING: {
        CAMEL_TO_LOWER_SNAKE: 'CAMEL_TO_LOWER_SNAKE'
    },

    // ===================================================================================
    //                                                                               Base
    //                                                                              ======
    /**
     * Return the schema of the remote api.<br/>
     * This schema is used for packages, class name prefixes, and so on.
     * @param {org.dbflute.logic.manage.freegen.DfFreeGenRequest} request - freeGen request settings. (NotNull)
     * @return {string} the schema of the remote api. (NotNull)
     */
    schema: function(request) {
        return request.requestName.replace(/^RemoteApi/g, '');
    },

    /**
     * Return the java schema package of the remote api.
     * @param {string} schema - schema of the remote api. (NotNull, NotEmpty)
     * @return {string} the java schema package of the remote api. (NotNull, NotEmpty)
     */
    schemaPackage: function(schema) {
        return manager.decamelize(schema).replace(/_/g, '.').toLowerCase();
    },

    /**
     * Return true if the api is to be generated.<br/>
     * Suppresses the generation of unused api.
     * @param {Api} api - API. (NotNull)
     * @return {boolean} true if the api is to be generated. (NotNull)
     */
    target: function(api) {
        // p1us2er0 fixedly all target (2019/08/31)
        // previously, it was TARGET when the request body or response body was json or xml content.
        return true;
    },

    /**
     * Return filtered URL.<br/>
     * Exclude prefixes and version numbers contained in URLs. e.g. /api/xxx, /v1/xxx<br/>
     * If excluded, you must include the excluded URL in AbstractRemoteFortress${Schema}Bhv#getUrlBase.
     * @param {Api} api - API. (NotNull)
     * @return {string} filtered URL. (NotNull)
     */
    url: function(api) {
        return api.url;
    },

    /**
     * Return the java sub schema package of the remote api.
     * @param {Api} api - API. (NotNull)
     * @return {string} the java sub package of the remote api. (NotNull, NotEmpty)
     */
    subPackage: function(api) {
        // 1. Remove symbols that cannot be used in java package name. And remove leading and trailing slashes.
        // 2. Remove path variable.
        // 3. Replace slashes with dots.
        return api.url.replace(/(_|-|\.|^\/|\/$)/g, '').replace(/\/\{.*?\}/g, '').replace(/\//g, '.').toLowerCase();
    },

    // ===================================================================================
    //                                                                            Behavior
    //                                                                            ========
    /** true to generate behavior classes. */
    behaviorClassGeneration: true,
    /** true to generate behavior class methods. */
    behaviorMethodGeneration: true,
    /** behavior class method access modifier. */
    behaviorMethodAccessModifier: 'public',

    /**
     * A class provided by Framework inherited by the parent class of behavior class.<br/>
     * If there is an extended class for the entire project, specify the extended class.
     */
    frameworkBehaviorClass: 'org.lastaflute.remoteapi.LastaRemoteBehavior',

    /**
     * Return abstract behavior class name without package.
     * @param {string} schema - schema of the remote api. (NotNull, NotEmpty)
     * @return {string} the abstract behavior class name without package. (NotNull, NotEmpty)
     */
    abstractBehaviorClassName: function(schema) {
        return 'AbstractRemote' + schema + 'Bhv';
    },

    /**
     * Return the java behavior sub package.
     * @param {Api} api - API. (NotNull)
     * @return {string} the java behavior sub package. (NotNull, NotEmpty)
     */
    behaviorSubPackage: function(api) {
        return this.subPackage(api).replace(/^([^.]*)\.(.+)/, '$1');
    },

    /**
     * Return bsBehavior class name without package.
     * @param {Api} api - API. (NotNull)
     * @return {string} bsBehavior class name without package. (NotNull, NotEmpty)
     */
    bsBehaviorClassName: function(api) {
        return 'BsRemote' + api.schema + manager.initCap(manager.camelize(this.behaviorSubPackage(api).replace(/\./g, '_'))) + 'Bhv';
    },

    /**
     * Return exBehavior class name without package.
     * @param {Api} api - API. (NotNull)
     * @return {string} exBehavior class name without package. (NotNull, NotEmpty)
     */
    exBehaviorClassName: function(api) {
        return 'Remote' + api.schema + manager.initCap(manager.camelize(this.behaviorSubPackage(api).replace(/\./g, '_'))) + 'Bhv';
    },

    /**
     * Return the behavior request method name.
     * @param {Api} api - API. (NotNull)
     * @return {string} the behavior request method name. (NotNull, NotEmpty)
     */
    behaviorRequestMethodName: function(api) {
        var methodPart = manager.camelize(this.subPackage(api).replace(this.behaviorSubPackage(api), '').replace(/\./g, '_'));
        return 'request' + manager.initCap(methodPart) + (api.multipleHttpMethod ? manager.initCap(api.httpMethod): '');
    },

    /**
     * Return the behavior rule method name.
     * @param {Api} api - API. (NotNull)
     * @return {string} the behavior rule method name. (NotNull, NotEmpty)
     */
    behaviorRuleMethodName: function(api) {
        var methodPart = manager.camelize(this.subPackage(api).replace(this.behaviorSubPackage(api), '').replace(/\./g, '_'));
        return 'ruleOf' + manager.initCap(methodPart) + (api.multipleHttpMethod ? manager.initCap(api.httpMethod): '');
    },

    // ===================================================================================
    //                                                                  Param/Return(base)
    //                                                                  ==================
    /**
     * Return the java bean sub package.
     * @param {Api} api - API. (NotNull)
     * @return {string} the java bean sub package. (NotNull, NotEmpty)
     */
    beanSubPackage: function(api) {
        var package = this.subPackage(api);
        if (package === this.behaviorSubPackage(api)) {
            package += '.index';
        }
        return package;
    },

    /**
     * Return bean class name without package.
     * @param {Api} api - API. (NotNull)
     * @param {boolean} detail - detail. (NotNull)
     * @return {string} bean class name without package. (NotNull)
     */
    beanClassName: function(api, detail) {
        var namePart = detail ? api.url.replace(/(_|-|^\/|\/$|\{|\})/g, '').replace(/\//g, '_').toLowerCase(): this.subPackage(api);
        return 'Remote' + manager.initCap(manager.camelize(namePart.replace(/\./g, '_'))) + (api.multipleHttpMethod ? manager.initCap(api.httpMethod): '');
    },

    /**
     * Return param extends class with package.
     * @param {Api} api - API. (NotNull)
     * @param {Object} properties - properties. (NotNull)
     * @return {string} param extends class with package. (NullAllowed)
     */
    paramExtendsClass: function(api, properties) {
        return null;
    },

    /**
     * Return param implements classes (Interface) with package.
     * @param {Api} api - API. (NotNull)
     * @param {Object} properties - properties. (NotNull)
     * @return {string} param Implements classes (Interface) with package. (NullAllowed)
     */
    paramImplementsClasses: function(api, properties) {
        return null;
    },

    /**
     * Return param class name without package.
     * @param {Api} api - API. (NotNull)
     * @param {boolean} detail - detail. (NotNull)
     * @return {string} param class name without package. (NotNull)
     */
    paramClassName: function(api, detail) {
        return this.beanClassName(api, detail) + 'Param';
    },

    /**
     * Return return extends class with package.
     * @param {Api} api - API. (NotNull)
     * @param {Object} properties - properties. (NotNull)
     * @return {string} return extends class with package. (NullAllowed)
     */
    returnExtendsClass: function(api, properties) {
        return null;
    },

    /**
     * Return return implements classes (Interface) with package.
     * @param {Api} api - API. (NotNull)
     * @param {Object} properties - properties. (NotNull)
     * @return {string} return implements classes (Interface) with package. (NullAllowed)
     */
    returnImplementsClasses: function(api, properties) {
        return null;
    },

    /**
     * Return returnClassName without package.
     * @param {Api} api - API. (NotNull)
     * @param {boolean} detail - detail. (NotNull)
     * @return {string} return class name without package. (NotNull)
     */
    returnClassName: function(api, detail) {
        return this.beanClassName(api, detail) + 'Return';
    },

    /**
     * Return nest class name without package.
     * @param {Api} api - API. (NotNull)
     * @param {string} nestClassName - Nest class name without package. (NotNull)
     * @return {string} Nest class name without package. (NotNull)
     */
    nestClassName: function(api, nestClassName) {
        return nestClassName.replace(/(Part|Result|Model|Bean)$/, '') + 'Part';
    },

    /**
     * Return true if the property is to be generated.
     * @param {Api} api - API. (NotNull)
     * @param {TopLevelBean} topLevelBean - definition of bean where field is declared. (NotNull)
     * @param {string} jsonFieldName - json field name. (NotNull)
     * @return {boolean} true if target. (NotNull)
     */
    targetField: function(api, topLevelBean, fieldName) {
        return true;
    },

    /**
     * Return java field name.
     * @param {Api} api - API. (NotNull)
     * @param {TopLevelBean} topLevelBean - definition of bean where field is declared. (NotNull)
     * @param {string} jsonFieldName - json field name. (NotNull)
     * @return {string} java field name. (NotNull)
     */
    fieldName: function(api, topLevelBean, jsonFieldName) {
        var fieldNaming = this.fieldNamingMapping()[topLevelBean.in];
        // #for_now Add a branch when the types of FIELD_NAMING increase. I want to be able to solve it with a loop if possible by p1us2er0 (2022/05/04)
        // In addition, it is necessary to add conversion processing to the provided class of lasta-remoteapi.
        if (fieldNaming === this.FIELD_NAMING.CAMEL_TO_LOWER_SNAKE) {
            return manager.initUncap(manager.camelize(jsonFieldName));
        }
        return jsonFieldName;
    },

    /**
     * Return true for custom java field name.
     * If neither of the following is true, it is considered custom.
     * 1. Swagger field name and java field name are an exact match.
     * 2. Swagger field name and java field name are the simple conversion of camel case -> snake case.
     *    Judgment of simple conversion of camel case-> snake case,
     *    After camel case -> snake case, reverse conversion is done to match the original name (reversible).
     *
     * If it is determined to be custom, @SerializedName will be added for serialization / deserialization at the time of automatic generation.
     * @param {Api} api - API. (NotNull)
     * @param {TopLevelBean} topLevelBean - definition of bean where field is declared. (NotNull)
     * @param {string} jsonFieldName - json field name. (NotNull)
     * @return {boolean} Return true for custom java field name. (NotNull)
     */
    isCustomFieldName: function(api, topLevelBean, jsonFieldName) {
        var adjustedFieldName = this.fieldName(api, topLevelBean, jsonFieldName);
        if (adjustedFieldName.equals(jsonFieldName)) {
            return false;
        }
        var fieldNaming = this.fieldNamingMapping()[topLevelBean.in];
        // #for_now Add a branch when the types of FIELD_NAMING increase. I want to be able to solve it with a loop if possible by p1us2er0 (2022/05/04)
        // In addition, it is necessary to add conversion processing to the provided class of lasta-remoteapi.
        if (fieldNaming === this.FIELD_NAMING.CAMEL_TO_LOWER_SNAKE) {
            return !manager.decamelize(adjustedFieldName).toLowerCase().equals(jsonFieldName);
        }
        return true;
    },

    // ===================================================================================
    //                                                           Param/Return(gap pattern)
    //                                                           =========================
    /** true if the raram/return bean is a gap pattern. */
    beanExtendsDefinitionGeneration: false,

    /**
     * Return filtered bean definition(gap pattern Extends class) subPackage.
     * @param {org.dbflute.logic.manage.freegen.DfFreeGenRequest} request - freeGen request settings. (NotNull)
     * @param {string} definitionKey - The definition key for the swagger specification. (NotNull, NotEmpty)
     * @return {string} filtered bean subPackage. (NotNull, NotEmpty)
     */
    beanExtendsDefinitionSubPackage: function(request, definitionKey) {
        return 'definition';
    },

    /**
     * Return bean definition(gap pattern Extends class) class name without package.
     * @param {org.dbflute.logic.manage.freegen.DfFreeGenRequest} request - freeGen request settings. (NotNull)
     * @param {string} definitionKey - The definition key for the swagger specification. (NotNull, NotEmpty)
     * @return {string} bean definition class name without package. (NotNull, NotEmpty)
     */
    beanExtendsDefinitionClassName: function(request, definitionKey) {
        return definitionKey.replace(/.*\./g, '').replace(/(<|>)/g, '') + 'Definition';
    },

    // ===================================================================================
    //                                                             Param/Return(irregular)
    //                                                             =======================
    /**
     * Return filterd definition key for the swagger specification.
     * e.g. Filter common header pattern class.
     * http://dbflute.seasar.org/ja/lastaflute/howto/impldesign/jsondesign.html#jsonerrorexp
     * @param {string} definitionKey - The definition key for the swagger specification. (NotNull, NotEmpty)
     * @return {string} filterd definition key for the swagger specification. (NotNull, NotEmpty)
     */
    definitionKey: function(definitionKey) {
        return definitionKey;
    },

    /**
     * Return definition key for the swagger specification before filtering from the filtered definition key for the swagger specification.
     * e.g. Restore the class of the filtered common header pattern.
     * @param {string} definitionKey - filterd definition key for the swagger specification. (NotNull, NotEmpty)
     * @return {string} definition key for the swagger specification before filtering from the filtered definition key for the swagger specification. (NotNull, NotEmpty)
     */
    unDefinitionKey: function(definitionKey) {
        return definitionKey;
    },

    // ===================================================================================
    //                                                                              Di xml
    //                                                                              ======
    /**
     * Return di xml path for target container lasta di.
     * @param {string} schema - schema of the remote api. (NotNull, NotEmpty)
     * @return {string} di xml path for target container lasta di. (NotNull, NotEmpty)
     */
    diXmlPath: function(schema, resourceFilePath) {
        return '../resources/remoteapi/di/remoteapi_' + this.schemaPackage(schema).replace(/\./g, '-') + '.xml';
    },

    /**
     * Return java config class name without package for target container spring.
     * @param {string} schema - schema of the remote api. (NotNull, NotEmpty)
     * @return {string} java config class name without package for target container spring. (NotNull, NotEmpty)
     */
    javaConfigClassName: function(schema) {
        return 'Remote' + schema + 'BeansJavaConfig';
    },

    // ===================================================================================
    //                                                                                 Doc
    //                                                                                 ===
    /** true for automatically generating doc. */
    docGeneration: true,

    // ===================================================================================
    //                                                                              Option
    //                                                                              ======
    /**
     * Return the order list of categolized package of import.<br/>
     * Specify top packages to categorize.
     * @return {string[]} The order list of categolized package of import. (NotNull, EmptyAllowed)
     */
    importOrderList: function() {
        return ['java', 'javax', 'junit', 'org', 'com', 'net', 'ognl', 'mockit', 'jp'];
    },

    /**
     * Return java field naming rule mapping for OpenAPI parameter type.<br/>
     * OpenAPI parameter types are path, query, formData, json(body and consumes not contains 'application/xml'), xml(body and consumes contains 'application/xml').<br/>
     * If this.FIELD_NAMING.CAMEL_TO_LOWER_SNAKE is specified, field names in Java will be camel case and snake case when sending API.
     * @return field naming mapping. (NotNull)
     */
    fieldNamingMapping: function() {
        return {
            'path': this.FIELD_NAMING.CAMEL_TO_LOWER_SNAKE,
            'query': this.FIELD_NAMING.CAMEL_TO_LOWER_SNAKE,
            'formData': this.FIELD_NAMING.CAMEL_TO_LOWER_SNAKE,
            'json': this.FIELD_NAMING.CAMEL_TO_LOWER_SNAKE,
            'xml': this.FIELD_NAMING.CAMEL_TO_LOWER_SNAKE
        };
    },

    /**
     * Return java field type mapping for OpenAPI parameter data type.
     * e.g. java.util.List -> org.eclipse.collections.api.list.ImmutableList, java.time.LocalDate -> String etc.
     * @return typeMap The map of type conversion, swagger type to java type. (NotNull)
     */
    typeMap: function() {
        return {
            'object': 'java.util.Map<String, Object>',
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
            'array': 'java.util.List',
            '': 'String'
        };
    },

    /**
     * Manually map classes for path variables.
     * @param {Api} api - API. (NotNull)
     * @param {PathVariable} pathVariable - path variable. (NotNull)
     * @return {string} path variable manual mapping class. (NullAllowed)
     */
    pathVariableManualMappingClass: function(api, pathVariable) {
        return null;
    },

    /**
     * Manually map classes for bean property.
     * @param {Api} api - API. (NotNull)
     * @param {string} beanClassName - bean class name. (NotNull)
     * @param {Property} property - property. (NotNull)
     * @return {string} bean property manual mapping class. (NullAllowed)
     */
    beanPropertyManualMappingClass: function(api, beanClassName, property) {
        return null;
    },

    /**
     * Manually description for path variables.
     * @param {Api} api - API. (NotNull)
     * @param {PathVariable} pathVariable - path variable. (NotNull)
     * @return {string} path variable manual mapping description. (NullAllowed)
     */
    pathVariableManualMappingDescription: function(api, pathVariable) {
        return null;
    },

    /**
     * Manually description for bean property.
     * @param {Api} api - API. (NotNull)
     * @param {string} beanClassName - bean class name. (NotNull)
     * @param {Property} property - property. (NotNull)
     * @return {string} bean property manual mapping description. (NullAllowed)
     */
    beanPropertyManualMappingDescription: function(api, beanClassName, property) {
        return null;
    },

    /**
     * Determines whether files are eligible for deletion during cleanup after auto-generation.
     * By default, files with ' @author FreeGen' are eligible for deletion.
     * @param {org.dbflute.logic.manage.freegen.DfFreeGenRequest} request - freeGen request settings. (NotNull)
     * @return {File} file. (NotNull)
     * @return {boolean} delete target. (NotNull)
     */
    deleteTarget: function(request, file) {
        try {
            return new java.lang.String(java.nio.file.Files.readAllBytes(file.toPath()), 'UTF-8').contains(' @author FreeGen');
        } catch (e) {
            return false;
        }
    }
};

var remoteApiRule = {};
for (var i in baseRule) {
    remoteApiRule[i] = baseRule[i];
}
