// Based on ECMAScript5. Because Nashorn of java 8 is ECMAScript5.
// =======================================================================================
//                                                                              Definition
//                                                                              ==========
/**
 * API Type.
 * It means e.g. "/lido/product/list/{pageNumber}": { ... "get": { ...
 * swagger.json の 1 path の 1 httpMethod に相当する情報。
 * swagger.json の形ほぼそのままだがちょっと違うところもあり。
 * 
 * methodBean は、swagger.json の httpMethodパートの object そのまま。
 * #hope jflute MethodBean も typedef したいところ。swagger.json の仕様をなぞるだけではあるが... (2026/03/14)
 * You can search it on code by: #{Api}
 * 
 * @typedef {Object} Api
 * @property {string} schema - The schema name of the remoteApi server. e.g. Fortress (from RemoteApiFortress) (NotNull, NotEmpty)
 * @property {string} package - The base package of output classes e.g. org.docksidestage.remote (NotNull, NotEmpty)
 * @property {string} url - The relative path of the remote api. e.g. /lido/product/list, /lido/product/list/{pageNumber} (NotNull, NotEmpty)
 * @property {string} httpMethod - The http method of the remote api. e.g. get (NotNull, NotEmpty)
 * @property {string[]} consumes - The consumes of the remote api. e.g. ["application/json"] (NotNull, EmptyAllowed)
 * @property {string[]} produces - The produces of the remote api. e.g. ["application/json"] (NotNull, EmptyAllowed)
 * @property {Object} methodBean - The bean of the target http method, having e.g. summary, parameters (NotNull, EmptyAllowed)
 * @property {boolean} multipleHttpMethod - Does it have multiple httpMethods in the same url? (NotNull, EmptyAllowed)
 */

/**
 * PathVariable Type.
 * It means e.g. pageNumber of "/lido/product/list/{pageNumber}".
 * swagger.json の形そのまま。利用していない項目などは列挙されていない可能性あり。
 * 
 * see below for details.<br/>
 * <ul>
 *   <li>https://swagger.io/specification/v2/#parameter-object</li>
 *   <li>https://swagger.io/specification/v2/#data-types</li>
 * </ul>
 * 
 * #hope jflute PathVariableItem や Schema も typedef したいところ。swagger.json の仕様をなぞるだけではあるが... (2026/03/14)
 * You can search it on code by: #{PathVariable}
 * 
 * @typedef {Object} PathVariable
 * @property {string} name - The name of path variable. e.g. productId (NotNull, NotEmpty)
 * @property {string} in - The location expression of the variable. 'path' fixed. (NotNull, NotEmpty)
 * @property {boolean} required - true if the parameter is required. (NotNull)
 * @property {string} description - about the variable. (NullAllowed)
 * @property {string} type - The data type of swagger. e.g. string, integer, number, boolean, array (NotNull, NotEmpty)
 * @property {string} format - The data format of swagger. e.g. int32, int64, float, double, byte, binary, date, date-time, password (NullAllowed)
 * @property {string} default - The default value of the variable. (NullAllowed)
 * @property {ElementItem[]} items - The element information in the array, required if type is "array". (NullAllowed)
 * @property {BeanSchema} schema - The schema object for the variable, having "$ref". (NullAllowed)
 * @property {string} enum - The lists possible values for the property. (NullAllowed)
 */

/**
 * ApiProperty (query、formData、body field) Type.
 * It means e.g. "parameters": [ { "name": "productId", "type": "integer", ... } ]
 * swagger.json の形そのまま。利用していない項目などは列挙されていない可能性あり。
 * 自動生成情報のPropertyと区別するために、Apiを接頭辞として付けている。
 * 
 * see below for details.<br/>
 * <ul>
 *   <li>https://swagger.io/specification/v2/#parameter-object</li>
 *   <li>https://swagger.io/specification/v2/#data-types</li>
 * </ul>
 * 
 * You can search it on code by: #{ApiProperty}
 * 
 * @typedef {Object} ApiProperty
 * @property {string} name - name of property(query、formData、body field). (NotNull, NotEmpty)
 * @property {string} in - The location expression of the property. e.g. query, body. (NotNull, NotEmpty)
 * @property {boolean} required - true if the parameter is required. (NotNull)
 * @property {string} description - about the property. (NullAllowed)
 * @property {string} type - The data type of swagger. e.g. string, integer, array (NotNull, NotEmpty)
 * @property {string} format - The data format of swagger. e.g. int32, int64, float, double, byte, binary, date, date-time, password (NullAllowed)
 * @property {string} default - The default value of the property. (NullAllowed)
 * @property {ElementItem} items - The element information in the array, required if type is "array". (NullAllowed)
 * @property {BeanSchema} schema - The schema object of nest class for the property, having "$ref". (NullAllowed)
 * @property {string} enum - The lists possible values for the property. (NullAllowed)
 */
// ↑ "items" と言ってるけど、別に複数形というわけじゃない。配列の要素たちというニュアンスで複数形だけど、要素の定義は一つ。(2026/03/15)

/**
 * ElementItem (of Array) Type.
 * It means e.g. "items": { "type": "string" }
 * swagger.json の形そのまま。利用していない項目などは列挙されていない可能性あり。
 * 
 * You can search it on code by: #{ElementItem}
 * 
 * @typedef {Object} ElementItem
 * @property {string} type - The scalar data type of the array element. e.g. string, integer, array (NullAllowed: but required if scalar type)
 * @property {string} format - The data format of swagger. e.g. int32, int64, float, double, byte, binary, date, date-time, password (NullAllowed: but required if e.g. integer type)
 * @property {string} enum - The lists possible values for the property. (NullAllowed)
 * @property {string} $ref - The definition key of bean as the array element. e.g. #/definitions/org.docksi...roduct.ProductSearchBody (NullAllowed: but required if bean type)
 */

/**
 * BeanSchema Type.
 * It means e.g. "schema": { "$ref": "#/definitions/org.docksi...ncers.BalletDancersPostBody" }
 * swagger.json の形そのまま。利用していない項目などは列挙されていない可能性あり。
 * ApiPropertyのネストBeanや、responsesのBeanにて利用される。
 * {Api}のschemaとは全く別物なので注意。
 * 
 * ほとんど以下のパターン;
 * Bean: $ref=...
 * List<Bean>: type=array, $ref=...
 * String: type=string // e.g. HtmlResponse
 * 
 * You can search it on code by: #{BeanSchema}
 * 
 * @typedef {Object} BeanSchema
 * @property {string} type - The data type of the schema. e.g. string, integer, array (NullAllowed: but required if scalar type and array)
 * @property {string} $ref - The definition key of bean as the array element. e.g. #/definitions/org.docksi...roduct.ProductSearchBody (NullAllowed: but required if bean type)
 */

/**
 * TopLevelBean Type.
 * 自動生成クラス意識の情報メイン。Param や Return などに相当。
 * ネストの Part などは対象外で、あくまで一つのAPIのルートに対応するクラス。
 * (ネストクラスは同じファイル内のインナークラスで表現されるので、自動生成の途中処理で解析される)
 * 
 * About remoteApiExp.
 * Used in the description of javadoc.
 * The values that can be taken differ for each automatically generated class.
 * For TopLevelBeans, the URL of the remote API on which this class is used is set.
 * 
 * You can search it on code by: #{TopLevelBean}
 * 
 * @typedef {Object} TopLevelBean
 * @property {Api} api - The API metadata corresponding to the bean. (NotNull)
 * @property {string} package - The full package of this class. (NotNull, NotEmpty)
 * @property {string} className - The class name without package of this class.  (NotNull, NotEmpty)
 * @property {string} definitionKey - The key identifying definition of the bean on the swagger specification. (NotNull, NotEmpty)
 * @property {string} extendsClass - The extends-class with package of this class. (NullAllowed)
 * @property {string} implementsClasses - The implements-classes (interface) with package of this class. (NullAllowed)
 * @property {ApiProperty[]} properties - The properties of this bean class. (NotNull, EmptyAllowed)
 * @property {string} beanPurposeType - The type of bean purpose. e.g. param, return (NotNull, NotEmpty)
 * @property {string} remoteApiExp - The expression of the remote api. See the description of the TopLevelBean itself for details. (NotNull, NotEmpty)
 * @property {Map<String, Map<String, Object>>} definitionMap - The map of all "definitions" on swagger.json containing other bean's. (NotNull)
 * @property {string} in - The location expression of the bean. e.g. query, formData, json, xml (NotNull, NotEmpty)
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
     * Build the schema name of the remote api.<br/>
     * This schema is used for packages, class name prefixes, and so on.
     * @param {org.dbflute.logic.manage.freegen.DfFreeGenRequest} request - The freeGen request as current. (NotNull)
     * @return {string} e.g. Fortress (from RemoteApiFortress) (NotNull, NotEmpty)
     */
    schema: function(request) {
        return request.requestName.replace(/^RemoteApi/g, '');
    },

    /**
     * Return the java schema package of the remote api.
     * @param {string} schema - The schema name of the remote api. e.g. Fortress (from RemoteApiFortress) (NotNull, NotEmpty)
     * @return {string} e.g. org.docksidestage.remote (NotNull, NotEmpty)
     */
    schemaPackage: function(schema) {
        return manager.decamelize(schema).replace(/_/g, '.').replace(/:/g, '').toLowerCase();
    },

    /**
     * Return true if the api is to be generated.<br/>
     * Suppresses the generation of unused api.
     * @param {Api} api - The API metadata as current. (NotNull)
     * @return {boolean} true if the api is to be generated. (NotNull)
     */
    target: function(api) {
        // p1us2er0 fixedly all target (2019/08/31)
        // previously, it was TARGET when the request body or response body was json or xml content.
        return true;
    },

    /**
     * Filter URL of the API.<br/>
     * Exclude prefixes and version numbers contained in URLs. e.g. /api/xxx, /v1/xxx<br/>
     * If excluded, you must include the excluded URL in AbstractRemoteFortress${Schema}Bhv#getUrlBase.
     * @param {Api} api - The API metadata as current, having URL e.g. /lido/product/list (NotNull)
     * @return {string} e.g. /lido/product/list, /api/lido/product/list, ... (NotNull)
     */
    url: function(api) {
        return api.url;
    },

    /**
     * Build the sub package for the API. (without base package)
     * @param {Api} api - The API metadata as current. (NotNull)
     * @return {string} e.g. lido.product.list (from /lido/product/list) (NotNull, NotEmpty)
     */
    subPackage: function(api) {
        // 1. Remove symbols that cannot be used in java package name. And remove leading and trailing slashes.
        // 2. Remove path variable.
        // 3. Replace slashes with dots.
        return api.url.replace(/(_|-|\.|^\/|:|\/$)/g, '').replace(/\/\{.*?\}/g, '').replace(/\//g, '.').toLowerCase();
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
     * Build abstract behavior class name for the schema. (without package)
     * @param {string} schema - The schema name of the remote api. e.g. Fortress (from RemoteApiFortress) (NotNull, NotEmpty)
     * @return {string} e.g. AbstractRemoteFortressBhv (NotNull, NotEmpty)
     */
    abstractBehaviorClassName: function(schema) {
        return 'AbstractRemote' + schema + 'Bhv';
    },

    /**
     * Build the behavior sub package for the API. (without base package and schema package)
     * @param {Api} api - The API metadata as current. (NotNull)
     * @return {string} e.g. lido.product (NotNull, NotEmpty)
     */
    behaviorSubPackage: function(api) {
        return this.subPackage(api).replace(/^([^.]*)\.(.+)/, '$1');
    },

    /**
     * Build base behavior class name for the API. (without package)
     * @param {Api} api - The API metadata as current. (NotNull)
     * @return {string} e.g. BsRemoteFortressLidoBhv (for /lido/...) (NotNull, NotEmpty)
     */
    bsBehaviorClassName: function(api) {
        return 'BsRemote' + api.schema + manager.initCap(manager.camelize(this.behaviorSubPackage(api).replace(/\./g, '_').replace(/:/g, ''))) + 'Bhv';
    },

    /**
     * Build extended behavior class name for the API. (without package)
     * @param {Api} api - The API metadata as current. (NotNull)
     * @return {string} e.g. RemoteFortressLidoBhv (for /lido/...) (NotNull, NotEmpty)
     */
    exBehaviorClassName: function(api) {
        return 'Remote' + api.schema + manager.initCap(manager.camelize(this.behaviorSubPackage(api).replace(/\./g, '_').replace(/:/g, ''))) + 'Bhv';
    },

    /**
     * Build method name of the behavior request.
     * @param {Api} api - The API metadata as current. (NotNull)
     * @return {string} e.g. requestProductList (for /lido/product/list) (NotNull, NotEmpty)
     */
    behaviorRequestMethodName: function(api) {
        var methodPart = this.resourceNameOfBehaviorMethod(api, {}); // camelized
        var suffix = this.suffixOfBehaviorMethodName(api, {}); // dummy option for now
        return 'request' + manager.initCap(methodPart) + suffix;
    },

    /**
     * Build method name of the behavior rule.
     * @param {Api} api - The API metadata as current. (NotNull)
     * @return {string} e.g. ruleOfProductList (for /lido/product/list) (NotNull, NotEmpty)
     */
    behaviorRuleMethodName: function(api) {
        var methodPart = this.resourceNameOfBehaviorMethod(api, {}); // camelized
        var suffix = this.suffixOfBehaviorMethodName(api, {}); // dummy option for now
        return 'ruleOf' + manager.initCap(methodPart) + suffix;
    },

    // #hope jflute the 'option' is reservation argument for future (2026/05/08)
    /**
     * Build resource name of the behavior method. (camelized, initUncap)
     * @param {Api} api - The API metadata as current. (NotNull)
     * @param {Object} option - The option of behavior naming. e.g. { detail: false } (NotNull)
     * @return {string} e.g. ruleOfProductList (for /lido/product/list) (NotNull, NotEmpty)
     */
    resourceNameOfBehaviorMethod: function(api, option) {
        return manager.camelize(this.subPackage(api).replace(this.behaviorSubPackage(api), '').replace(/\./g, '_').replace(/:/g, ''));
    },

    /**
     * Build suffix (basically HTTP method keyword) of behavior method for e.g. request/ruleOf...().
     * @param {Api} api - The API metadata as current. (NotNull)
     * @param {Object} option - The option of behavior naming. e.g. { detail: false } (NotNull)
     * @return {string} e.g. '', 'Get', 'Post' (NotNull)
     */
    suffixOfBehaviorMethodName: function(api, option) {
        // multiple only as default
        var fixed = this.isAvailableFixedHttpMethodSuffixOfBehaviorMethodName(api, option); // false as default
        return (fixed || api.multipleHttpMethod ? manager.initCap(api.httpMethod): '');
    },

    /**
     * Is available HTTP method suffix of behavior methods (e.g. request.../ruleOf...) ?
     * @param {Api} api - The API metadata as current. (NotNull)
     * @param {Object} option - The option of behavior naming. e.g. {} (NotNull)
     * @return {boolean} true if always HTTP method as suffix e.g. requestLidoProductListGet (NotNull)
     */
    isAvailableFixedHttpMethodSuffixOfBehaviorMethodName: function(api, option) {
        return this.isAvailableFixedHttpMethodSuffixOfIdentityName(api, option);
    },

    /**
     * Is available HTTP method suffix of identity name (behavior methods/Param/Return) ?
     * @param {Api} api - The API metadata as current. (NotNull)
     * @param {Object} option - The option of behavior naming. e.g. {} (NotNull)
     * @return {boolean} true if always HTTP method as suffix e.g. RemoteLidoProductListGet... (NotNull)
     */
    isAvailableFixedHttpMethodSuffixOfIdentityName: function(api, option) {
        return false; // as default
    },

    // ===================================================================================
    //                                                                  Param/Return(base)
    //                                                                  ==================
    // -----------------------------------------------------
    //                                            Basic Bean
    //                                            ----------
    /**
     * Build sub package of the bean (param/return). (without base package and schema package)
     * @param {Api} api - The API metadata as current. (NotNull)
     * @return {string} e.g. lido.product.list (NotNull, NotEmpty)
     */
    beanSubPackage: function(api) {
        var package = this.subPackage(api);
        if (package === this.behaviorSubPackage(api)) {
            package += '.index';
        }
        return package;
    },

    // #hope jflute change parameter 'detail' to 'option' object at future (2026/05/08)
    /**
     * Build base name of bean class without package for e.g. Param/Return.
     * @param {Api} api - The API metadata as current. (NotNull)
     * @param {boolean} detail - true if duplicate path, then using e.g. path variables. (NotNull)
     * @return {string} e.g. RemoteLidoProductList, RemoteLidoProductListGet, RemoteLidoProductListPagenumber (NotNull)
     */
    beanClassName: function(api, detail) {
        var option = { detail: detail }; // as adapter
        var namePart;
        if (detail) { // if having duplicate with other API path
            namePart = this.resourceNameOfDetailBeanClassName(api, option);
        } else { // normally here
            namePart = this.resourceNameOfBasicBeanClassName(api, option);
        }
        var camelized = manager.initCap(manager.camelize(namePart.replace(/\./g, '_').replace(/:/g, '')));
        var suffix = this.suffixOfBeanClassName(api, option);
        return 'Remote' + camelized + suffix;
    },

    /**
     * Build resource name of basic bean class without Remote/camel/package/ for e.g. Param/Return.
     * @param {Api} api - The API metadata as current. (NotNull)
     * @param {Object} option - The option of bean naming. e.g. { detail: false } (NotNull)
     * @return {string} e.g. lido.product.list (NotNull)
     */
    resourceNameOfBasicBeanClassName: function(api, option) {
        return this.subPackage(api);
    },

    /**
     * Build resource name of detail bean class without Remote/camel/package/ for e.g. Param/Return.
     * @param {Api} api - The API metadata as current. (NotNull)
     * @param {Object} option - The option of bean naming. e.g. { detail: true } (NotNull)
     * @return {string} e.g. lido_product_list_pagenumber (NotNull)
     */
    resourceNameOfDetailBeanClassName: function(api, option) {
        // use PathVariables to be unique path (different from subPackage())
        // e.g.
        //  subPackage() :: lido.product.list
        //  this method  :: lido_product_list_pagenumber
        return api.url.replace(/(_|-|^\/|\/$|\{|\})/g, '').replace(/\//g, '_').toLowerCase();
    },

    /**
     * Build suffix (basically HTTP method keyword) of bean class for e.g. Param/Return.
     * @param {Api} api - The API metadata as current. (NotNull)
     * @param {Object} option - The option of bean naming. e.g. { detail: false } (NotNull)
     * @return {string} e.g. '', 'Get', 'Post' (NotNull)
     */
    suffixOfBeanClassName: function(api, option) {
        // multiple only as default
        var fixed = this.isAvailableFixedHttpMethodSuffixOfBeanClassName(api, option); // false as default
        return (fixed || api.multipleHttpMethod ? manager.initCap(api.httpMethod): '');
    },

    /**
     * Is available fixed HTTP method suffix of bean class for e.g. Param/Return.
     * @param {Api} api - The API metadata as current. (NotNull)
     * @param {Object} option - The option of bean naming. e.g. { detail: false } (NotNull)
     * @return {boolean} true if always HTTP method as suffix e.g. RemoteLidoProductListGet... (NotNull)
     */
    isAvailableFixedHttpMethodSuffixOfBeanClassName: function(api, option) {
        return this.isAvailableFixedHttpMethodSuffixOfIdentityName(api, option);
    },

    /**
     * Is available fixed detail naming of bean class for e.g. Param/Return.
     * @param {Api} api - The API metadata as current. (NotNull)
     * @param {Object} option - The option of bean naming. e.g. { detail: false } (NotNull)
     * @return {boolean} true if always detail e.g. RemoteLidoProductListPagenumber... (NotNull)
     */
    isAvailableFixedDetailNamingOfBeanClassName: function(api, option) {
        return false; // as default
    },

    // -----------------------------------------------------
    //                                            Param Bean
    //                                            ----------
    /**
     * Derive param extends-class (parent class) with package.
     * @param {Api} api - The API metadata as current. (NotNull)
     * @param {Map<String, ApiProperty>} properties - The information of property for the bean. (NotNull)
     * @return {string} e.g. org.docksidestage.remote.AbstractSea (NullAllowed: then no extending)
     */
    paramExtendsClass: function(api, properties) {
        return null;
    },

    /**
     * Derive param implements-classes (Interface) with package.
     * You can implements plural interfaces by comma-separeted string.
     * @param {Api} api - The API metadata as current. (NotNull)
     * @param {Map<String, ApiProperty>} properties - The information of property for the bean. (NotNull)
     * @return {string} e.g. "org.docksidestage.remote.Sea, org.docksidestage.remote.Land" (NullAllowed: then no implementing)
     */
    paramImplementsClasses: function(api, properties) {
        return null;
    },

    /**
     * Return param class name without package.
     * @param {Api} api - The API metadata as current. (NotNull)
     * @param {boolean} detail - ???. (NotNull)
     * @return {string} e.g. RemoteLidoProductListParam (NotNull)
     */
    paramClassName: function(api, detail) {
        return this.beanClassName(api, detail) + 'Param';
    },

    // -----------------------------------------------------
    //                                           Return Bean
    //                                           -----------
    /**
     * Derive return extends-class (parent class) with package.
     * @param {Api} api - The API metadata as current. (NotNull)
     * @param {Map<String, ApiProperty>} properties - The information of property for the bean. (NotNull)
     * @return {string} e.g. org.docksidestage.remote.AbstractSea (NullAllowed: then no extending)
     */
    returnExtendsClass: function(api, properties) {
        return null;
    },

    /**
     * Derive return implements-classes (Interface) with package.
     * You can implements plural interfaces by comma-separeted string.
     * @param {Api} api - The API metadata as current. (NotNull)
     * @param {Map<String, ApiProperty>} properties - The information of property for the bean. (NotNull)
     * @return {string} e.g. "org.docksidestage.remote.Sea, org.docksidestage.remote.Land" (NullAllowed: then no implementing)
     */
    returnImplementsClasses: function(api, properties) {
        return null;
    },

    /**
     * Build return class name without package.
     * @param {Api} api - The API metadata as current. (NotNull)
     * @param {boolean} detail - ???. (NotNull)
     * @return {string} e.g. RemoteLidoProductListReturn (NotNull)
     */
    returnClassName: function(api, detail) {
        return this.beanClassName(api, detail) + 'Return';
    },

    // -----------------------------------------------------
    //                                             Nest Bean
    //                                             ---------
    /**
     * Build generated class name as nest class without package.
     * @param {Api} api - The API metadata as current. (NotNull)
     * @param {string} nestClassName - The remote-side name of nest class without package, e.g. ProductRowBean, ProductRowPart (NotNull)
     * @return {string} e.g. ProductRowPart (NotNull)
     */
    nestClassName: function(api, nestClassName) {
        return nestClassName.replace(/(Part|Result|Model|Bean)$/, '') + 'Part';
    },

    // -----------------------------------------------------
    //                                            Bean Field
    //                                            ----------
    /**
     * Determine whether the property is to be generated.
     * @param {Api} api - The API metadata as current. (NotNull)
     * @param {TopLevelBean} topLevelBean - The top-level (root) bean of the field, same when nest field. (NotNull)
     * @param {string} jsonFieldName - The plain field name on json, e.g. product_id (NotNull)
     * @return {boolean} true if target. (NotNull)
     */
    targetField: function(api, topLevelBean, fieldName) {
        return true;
    },

    /**
     * Filter field name for Java.
     * @param {Api} api - The API metadata as current. (NotNull)
     * @param {TopLevelBean} topLevelBean - The top-level (root) bean of the field, same when nest field. (NotNull)
     * @param {string} jsonFieldName - The plain field name on json, e.g. product_id (NotNull)
     * @return {string} e.g. productId (from product_id) (NotNull)
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
     * Determine whether the field name is custom.
     * 
     * If neither of the following is true, it is considered custom.
     * 1. Swagger field name and java field name are an exact match.
     * 2. Swagger field name and java field name are the simple conversion of camel case -> snake case.
     *    Judgment of simple conversion of camel case-> snake case,
     *    After camel case -> snake case, reverse conversion is done to match the original name (reversible).
     *
     * If it is determined to be custom, @SerializedName will be added
     * for serialization / deserialization at the time of automatic generation.
     * 
     * @param {Api} api - The API metadata as current. (NotNull)
     * @param {TopLevelBean} topLevelBean - The top-level (root) bean of the field, same when nest field. (NotNull)
     * @param {string} jsonFieldName - The plain field name on json, e.g. product_id (NotNull)
     * @return {boolean} true if custom. (NotNull)
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
     * Derive sub package for bean definition (gap pattern extends-class) from bean package.
     * @param {org.dbflute.logic.manage.freegen.DfFreeGenRequest} request - The freeGen request as current. (NotNull)
     * @param {string} definitionKey - The identifier of the definition e.g. org.docksidestage.app.web.lido.product.ProductRowResult (NotNull, NotEmpty)
     * @return {string} e.g. definition (org.docksidestage.remote.fortress.lido.product.list.definition) (NotNull, NotEmpty)
     */
    beanExtendsDefinitionSubPackage: function(request, definitionKey) {
        return 'definition';
    },

    /**
     * Build class name of bean definition (gap pattern extends-class) without package.
     * @param {org.dbflute.logic.manage.freegen.DfFreeGenRequest} request - The freeGen request as current. (NotNull)
     * @param {string} definitionKey - The identifier of the definition e.g. org.docksidestage.app.web.lido.product.ProductRowResult (NotNull, NotEmpty)
     * @return {string} e.g. ProductRowResultDefinition (NotNull, NotEmpty)
     */
    beanExtendsDefinitionClassName: function(request, definitionKey) {
        return definitionKey.replace(/.*\./g, '').replace(/(<|>)/g, '') + 'Definition';
    },

    // ===================================================================================
    //                                                             Param/Return(irregular)
    //                                                             =======================
    /**
     * Derive definition key for the swagger specification.
     * e.g. Filter common header pattern class.
     * http://dbflute.seasar.org/ja/lastaflute/howto/impldesign/jsondesign.html#jsonerrorexp
     * @param {string} definitionKey - The definition key for the swagger specification. (NotNull, NotEmpty)
     * @return {string} The identifier of the definition e.g. org.docksidestage.app.web.lido.product.ProductRowResult (NotNull, NotEmpty)
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
     * Build di xml path for target container lasta di.
     * @param {string} schema - The schema name of the remote api. e.g. Fortress (from RemoteApiFortress) (NotNull, NotEmpty)
     * @return {string} e.g. ../resources/remoteapi/di/remoteapi_fortress.xml (NotNull, NotEmpty)
     */
    diXmlPath: function(schema, resourceFilePath) {
        return '../resources/remoteapi/di/remoteapi_' + this.schemaPackage(schema).replace(/\./g, '-') + '.xml';
    },

    /**
     * Return java config class name without package for target container spring.
     * @param {string} schema - The schema name of the remote api. e.g. Fortress (from RemoteApiFortress) (NotNull, NotEmpty)
     * @return {string} e.g. RemoteFortressBeansJavaConfig (NotNull, NotEmpty)
     */
    javaConfigClassName: function(schema) {
        return 'Remote' + schema + 'BeansJavaConfig';
    },

    // ===================================================================================
    //                                                                                Doc
    //                                                                               =====
    /** true for automatically generating doc. */
    docGeneration: true,

    // ===================================================================================
    //                                                                               Test
    //                                                                              ======
    /**
     * true for automatically generating behavior test.
     */
    testClassGeneration: false,

    /**
     * A super class FQCN of behavior test. (NullAllowed: if null, compile error)
     */
    behaviorTestSuperClass: null,

    // ===================================================================================
    //                                                                              Option
    //                                                                              ======
    /**
     * Define the order list of categolized package of import.<br/>
     * Specify top packages to categorize.
     * @return {string[]} e.g. [java, javax, ...] (NotNull, EmptyAllowed)
     */
    importOrderList: function() {
        return ['java', 'javax', 'junit', 'org', 'com', 'net', 'ognl', 'mockit', 'jp'];
    },

    /**
     * Define java field naming rule mapping for OpenAPI parameter type.<br/>
     * OpenAPI parameter types are path, query, formData, json(body and consumes not contains 'application/xml'), xml(body and consumes contains 'application/xml').<br/>
     * If this.FIELD_NAMING.CAMEL_TO_LOWER_SNAKE is specified, field names in Java will be camel case and snake case when sending API.
     * @return {Map<String, String>} The map of field naming, keyed by e.g. path, query (NotNull)
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
     * Define java field type mapping for OpenAPI parameter data type.
     * e.g. java.util.List -> org.eclipse.collections.api.list.ImmutableList, java.time.LocalDate -> String etc.
     * @return {Map<String, String>} The map of type conversion, swagger type to java type. (NotNull)
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
     * Manually map classes for the path variable.
     * @param {Api} api - The API metadata as current. (NotNull)
     * @param {PathVariable} pathVariable - The information of path variable, having e.g. name, in, required. (NotNull)
     * @return {string} The class name (without package if necessary) for the path variable, e.g. String, Integer, java.time.LocalDate (NullAllowed: then as default)
     */
    pathVariableManualMappingClass: function(api, pathVariable) {
        return null;
    },

    /**
     * Manually map classes for the bean property.
     * @param {Api} api - The API metadata as current. (NotNull)
     * @param {string} beanClassName - The class name (without package) of the bean for the property, may be nest class. (NotNull)
     * @param {ApiProperty} property - The information of bean property, having e.g. name, in, required. (NotNull)
     * @return {string} The class name (without package if necessary) for the property, e.g. String, Integer, java.time.LocalDate (NullAllowed: then as default)
     */
    beanPropertyManualMappingClass: function(api, beanClassName, property) {
        return null;
    },

    /**
     * Manually map description for the path variables. (actually switched)
     * @param {Api} api - The API metadata as current. (NotNull)
     * @param {PathVariable} pathVariable - path variable. (NotNull)
     * @return {string} The description about the path variable. (NullAllowed: then as default)
     */
    pathVariableManualMappingDescription: function(api, pathVariable) {
        return null;
    },

    /**
     * Manually map description for the bean property.
     * @param {Api} api - The API metadata as current. (NotNull)
     * @param {string} beanClassName - The class name (without package) of the bean for the property, may be nest class. (NotNull)
     * @param {ApiProperty} property - The information of API property, having e.g. name, in, required. (NotNull)
     * @return {string} The description about the property. (NullAllowed: then as default)
     */
    beanPropertyManualMappingDescription: function(api, beanClassName, property) {
        return null;
    },

    /**
     * Determines whether files are eligible for deletion during cleanup after auto-generation.
     * By default, files with ' @author FreeGen' are eligible for deletion.
     * @param {org.dbflute.logic.manage.freegen.DfFreeGenRequest} request - The freeGen request as current. (NotNull)
     * @return {java.io.File} The existing file on auto-generate directory. (NotNull)
     * @return {boolean} Is the file delete target? (NotNull)
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
