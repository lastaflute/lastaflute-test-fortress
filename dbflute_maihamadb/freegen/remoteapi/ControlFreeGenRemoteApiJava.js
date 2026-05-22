
// 自動生成種別、ログやディレクトリ名などで利用
var genType = 'remoteapi';

// 自動生成ファイルのパス一覧、自動生成処理ごとに保持して溜めていく、freegenディレクトリからの相対パス
var destFilePathList = [];

// =======================================================================================
//                                                                              Definition
//                                                                              ==========


// =======================================================================================
//                                                                              Controller
//                                                                              ==========
/**
 * ルートの入り口関数。ControlVMからすぐに呼ばれる想定。ここから自動生成の旅が始まる。
 * 
 * 1. dfpropに定義されているFreeGenリエクストたちループ
 *  1-1. rule.jsを読み込む
 *  1-2. メタデータをオブジェクト化
 *  1-3. Velocityでクラス自動生成
 * 
 * 2. もっかいループ
 *  2-1. rule.jsを読み込む // #hope jflute もっかい読むの回避できないかな？ (2026/03/08)
 *  2-2. 古いファイルを削除
 * 
 * @param {org.dbflute.logic.manage.freegen.DfFreeGenRequest[]} requestList - The list of freeGen requests. (NotNull, EmptyAllowed)
 */
function process(requestList) {
    // それぞれのリクエストごとに自動生成
    for each (var request in requestList) {
        if (!request.isResourceTypeSwagger()) {
            continue;
        }
        manager.info('...Generating ' + genType + ': ' + request.requestName);
        try {
            request.enableOutputDirectory();
            manager.makeDirectory(request.generateDirPath);
            var optionMap = request.optionMap;
            eval('load("./freegen/' + genType + '/' + 'RemoteApiRule.js");');
            eval('load("./freegen/' + genType + '/' + 'RemoteApiLogic.js");');
            if (optionMap.ruleJsPath && optionMap.ruleJsPath != '') {
                // load application rule settings if exists
                eval('load("' + optionMap.ruleJsPath + '");');
            }
            processHull(request);
        } catch (e) {
            var message = '/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n';
            message += 'Error in ' + genType + ' generation.\n';
            message += '\n';
            message += '[Advice]\n';
            message += '1. Upgrade version of dbflute engine to 1.1.6 or later.\n';
            message += '2. If you can not upgrade the version, you may be able to avoid errors by chang setting.\n';
            message += '3. Still if you do not improve it, there is a possibility of a bug.\n';
            message += '_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/\n';
            print(message);
            throw e;
        }
    }

    // 不要なファイルを削除
    for each (var request in requestList) {
        if (!request.isResourceTypeSwagger()) {
            continue;
        }
        var optionMap = request.optionMap;
        eval('load("./freegen/' + genType + '/' + 'RemoteApiRule.js");');
        eval('load("./freegen/' + genType + '/' + 'RemoteApiLogic.js");');
        if (optionMap.ruleJsPath && optionMap.ruleJsPath != '') {
            // load application rule settings if exists
            eval('load("' + optionMap.ruleJsPath + '");');
        }
        var rule = remoteApiRule;
        var schema = rule.schema(request);
        var schemaPackage = rule.schemaPackage(schema);
        var package = request.package + '.' + schemaPackage;
        clean(rule, request, package.replace(/\./g, '/'), destFilePathList);
        clean(rule, request, '../resources/' + genType + '/di', destFilePathList);
    }
}

/**
 * FreeGenリクエスト一つに対する処理。
 * @param {org.dbflute.logic.manage.freegen.DfFreeGenRequest} request - The current freeGen request. (NotNull)
 */
function processHull(request) {
    var rule = remoteApiRule;

    // load java property type mapping
    var typeMap = rule.typeMap();

    // schema name is from part of FreeGen request name e.g. RemoteApiSeaLand => SeaLand
    var schema = rule.schema(request);
    var schemaPackage = rule.schemaPackage(schema);

    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // swagger.json's key
    // _/_/_/_/_/_/_/_/_/_/
    var optionMap = request.optionMap;
    var pathMap = optionMap.jsonMap['paths'];
    var definitionMap = optionMap.jsonMap['definitions'];

    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // APIオブジェクト一覧の作成。
    //
    // loop paths's elements, per one API URL: e.g. /sea/land
    // _/_/_/_/_/_/_/_/_/_/
    var apiList = [];
    for (var pathKey in pathMap) {
        var path = pathMap[pathKey];
        var commonParameters = pathMap[pathKey].parameters;
        for (var methodKey in pathMap[pathKey]) {
            var method = path[methodKey];
            var api = new java.util.LinkedHashMap(); // new #{Api}
            api.schema = schema;
            api.package = request.package;
            api.url = pathKey;
            api.httpMethod = methodKey;
            api.consumes = method.consumes || optionMap.jsonMap['consumes'];
            api.produces = method.produces || optionMap.jsonMap['produces'];
            api.methodBean = method;
            if (commonParameters && !commonParameters.equals(api.methodBean)) {
                if (api.methodBean.parameters) {
                    api.methodBean.parameters.addAll(commonParameters);
                } else {
                    api.methodBean.parameters = commonParameters;
                }
            }
            if (rule.target(api)) {
                api.url = rule.url(api);
                apiList.push(api);
            }
        }
    }
    // 横断的に multipleHttpMethod の判定
    for (var apiIndex in apiList) {
        var api = apiList[apiIndex];
        for (var comparisonApiIndex in apiList) {
            var comparisonApi = apiList[comparisonApiIndex];
            if (!api.equals(comparisonApi) && api.url === comparisonApi.url) {
                api.multipleHttpMethod = true;
                break;
            }
        }
    }

    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // APIごとに remoteApiBean (e.g. Param, Return) や Behavior のメタデータをセットアップ。
    // _/_/_/_/_/_/_/_/_/_/
    var remoteApiBeanList = []; // all beans (e.g. Param, Return) in the current request, Array<Map>
    var exBehaviorMap = new java.util.LinkedHashMap(); // all behaviors in the current request
    for (var apiIndex in apiList) {
        var api = apiList[apiIndex];

        // +------------------------+
        // |                        |
        // | Prepare 'Param' beans  |
        // |                        |
        // +------------------------+
        var pathVariables = new java.util.LinkedHashMap(); // new #{PathVariable}
        var queryProperties = new java.util.LinkedHashMap(); // new #{ApiProperty}
        var formDataProperties = new java.util.LinkedHashMap(); // me too
        var bodyProperties = new java.util.LinkedHashMap(); // me too
        var paramBean = new java.util.LinkedHashMap();
        var paramBeanArray = false;

        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // loop parameters's elements, e.g. in, name, description, required, schema, ...
        // _/_/_/_/_/_/_/_/_/_/
        for (parameterIndex in api.methodBean['parameters']) {
            var parameter = api.methodBean['parameters'][parameterIndex];
            if (parameter.in === 'path') {
                pathVariables[parameter.name] = parameter;
            } else if (parameter.in === 'query') {
                queryProperties[parameter.name] = parameter;
            } else if (parameter.in === 'formData') {
                formDataProperties[parameter.name] = parameter;
            } else if (parameter.in === 'body') {
                var definitionKey = '';
                if (parameter.schema.type && parameter.schema.type === 'array') { // #{BeanSchema}
                    definitionKey = java.net.URLDecoder.decode(parameter.schema.items['$ref'].replace('#/definitions/', ''), "UTF-8");
                } else if (parameter.schema['$ref']) { // #{BeanSchema}
                    definitionKey = java.net.URLDecoder.decode(parameter.schema['$ref'].replace('#/definitions/', ''), "UTF-8");
                }
                bodyProperties = definitionMap[rule.definitionKey(definitionKey)].properties;
                if (bodyProperties === null) {
                    // dummy object for no properties case (2026/03/11)
                    // e.g. 
                    //  "org.docksidestage.app.web.wx.lastadoc.WxLastadocEmptyBody": {
                    //    "type": "object"
                    //    // no properties here
                    //    //"properties": {
                    //  },
                    bodyProperties = new java.util.LinkedHashMap()
                }
                for (bodyPropertyKey in bodyProperties) {
                    var required = definitionMap[rule.definitionKey(definitionKey)].required;
                    if (required) {
                        bodyProperties[bodyPropertyKey].required = required.contains(bodyPropertyKey);
                    }
                }
                var remoteApiBean = createBean(rule, 'param', api, bodyProperties, definitionMap, definitionKey);
                remoteApiBean.in = api.consumes && api.consumes.indexOf('application/xml') !== -1 ? 'xml' : 'json';
                paramBeanArray = parameter.schema.type === 'array';
                paramBean = remoteApiBean;
                remoteApiBeanList.push(paramBean);
            }
        }

        if (bodyProperties.isEmpty()) {
            if (!queryProperties.isEmpty()) {
                var remoteApiBean = createBean(rule, 'param', api, queryProperties, definitionMap);
                remoteApiBean.in = 'query';
                paramBean = remoteApiBean;
                remoteApiBeanList.push(paramBean);
            }
            if (!formDataProperties.isEmpty()) {
                var remoteApiBean = createBean(rule, 'param', api, formDataProperties, definitionMap);
                remoteApiBean.in = 'formData';
                paramBean = remoteApiBean;
                remoteApiBeanList.push(paramBean);
            }
        }

        // +-------------------------+
        // |                         |
        // | Prepare 'Return' beans  |
        // |                         |
        // +-------------------------+
        var returnBean = new java.util.LinkedHashMap();
        var returnBeanArray = false;

        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // loop responses's elements, e.g. in, name, description, required, schema, ...
        // _/_/_/_/_/_/_/_/_/_/
        for (responseKey in api.methodBean['responses']) {
            var response = api.methodBean['responses'][responseKey];
            // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
            // only one return bean is targeted.
            // _/_/_/_/_/_/_/_/_/_/
            if (!returnBean.className && response.schema) { // #{BeanSchema}
                var array = response.schema.type === 'array';
                var responseSchema = array ? response.schema.items : response.schema; // #{ElementItem} if array

                if (typeMap[responseSchema.format]) {
                    returnBean.className = typeMap[responseSchema.format];
                    returnBean.array = array;
                } else if (typeMap[responseSchema.type]) {
                    returnBean.className = typeMap[responseSchema.type];
                    returnBean.array = array;
                } else {
                    // e.g. "#/definitions/org.docksidestage.app.web.ballet.dancers.BalletDancersResult"
                    //  definitionKey is "org.docksidestage.app.web.ballet.dancers.BalletDancersResult"
                    var definitionKey = java.net.URLDecoder.decode(responseSchema['$ref'].replace('#/definitions/', ''), "UTF-8");
                    definitionKey = rule.definitionKey(definitionKey);
                    var definition = definitionMap[definitionKey];
                    if (definition) {
                        var returnProperties = definition.properties; // may be null (2026/03/22)
                        if (returnProperties != null && !returnProperties.isEmpty()) {
                            for (returnPropertyKey in returnProperties) {
                                var returnPropertyValue = returnProperties[returnPropertyKey];
                                var required = definitionMap[rule.definitionKey(definitionKey)].required;
                                if (required) {
                                    returnPropertyValue.required = required.contains(returnPropertyKey);
                                }
                            }
                            var remoteApiBean = createBean(rule, 'return', api, returnProperties, definitionMap, definitionKey);
                            returnBeanArray = array;
                            returnBean = remoteApiBean;
                            remoteApiBeanList.push(returnBean);
                        }
                    }
                }
                returnBean.in = api.produces && api.produces.indexOf('application/xml') !== -1 ? 'xml' : 'json';
            }
        } // end of responses loop

        keepBehavior(rule, api, pathVariables, paramBean, paramBeanArray, returnBean, returnBeanArray, exBehaviorMap);
    } // end of apiList loop

    if (rule['beanExtendsDefinitionGeneration']) {
        for each (var remoteApiBean in remoteApiBeanList) {
            if (!remoteApiBean.definitionKey) {
                continue;
            }
            for each (var comparisonRemoteApiBean in remoteApiBeanList) {
                if (remoteApiBean !== comparisonRemoteApiBean
                        && remoteApiBean.definitionKey === comparisonRemoteApiBean.definitionKey
                        && remoteApiBean.in === comparisonRemoteApiBean.in) {
    
                    var definitionRemoteApiBean = new java.util.LinkedHashMap();
                    definitionRemoteApiBean.api = null;
                    var schemaPackage = rule.schemaPackage(api.schema);
                    var subPackage = rule.beanExtendsDefinitionSubPackage(request, remoteApiBean.definitionKey);
                    var package = api.package + '.' + schemaPackage;
                    if (subPackage) {
                        package = package + '.' + subPackage;
                    }
                    definitionRemoteApiBean.package = package;
                    definitionRemoteApiBean.className = rule.beanExtendsDefinitionClassName(request, remoteApiBean.definitionKey);
                    definitionRemoteApiBean.extendsClass = remoteApiBean.extendsClass;
                    definitionRemoteApiBean.implementsClasses = remoteApiBean.implementsClasses;
                    definitionRemoteApiBean.properties = remoteApiBean.properties;
                    definitionRemoteApiBean.beanPurposeType = remoteApiBean.definitionKey;
                    definitionRemoteApiBean.remoteApiExp = 'definition';
                    definitionRemoteApiBean.definitionMap = definitionMap;
                    definitionRemoteApiBean.in = remoteApiBean.in;
    
                    remoteApiBeanList.push(definitionRemoteApiBean);
    
                    remoteApiBean.extendsClass = definitionRemoteApiBean.package + '.' + definitionRemoteApiBean.className;
                    remoteApiBean.implementsClasses = null;
                    remoteApiBean.properties = [];
                    remoteApiBean.definitionRemoteApiBean = definitionRemoteApiBean;
                    break;
                 }
            }
        }
    }

    // +-------------------------+
    // |                         |
    // | Generate class files    |
    // |                         |
    // +-------------------------+
    generateBean(rule, remoteApiBeanList);
    generateBhv(rule, request, exBehaviorMap);
    generateDoc(rule, request, exBehaviorMap);
}

// {Api}, {ApiProperty}, {TopLevelBean} types are defined on RemoteApiRule.js
/**
 * Create information of remoteApi bean. (e.g. Param, Return)
 * @param {RemoteApiRule} rule - RemoteApiRule.js object. (NotNull)
 * @param {string} beanPurposeType - The type of bean purpose. e.g. param, return (NotNull)
 * @param {Api} api - The API object corresponding to the bean. (NotNull)
 * @param {Map<String, ApiProperty>} properties - The information of property for the bean. (NotNull)
 * @param {Map<String, Map<String, Object>>} definitionMap - The map of all "definitions" on swagger.json containing other bean's. (NotNull)
 * @param {string} definitionKey - The identifier of the definition e.g. org.docksidestage.app.web.lido.product.ProductSearchBody (NotNull)
 * @return {TopLevelBean} The map of the remoteApi bean information. e.g. className, properties (NotNull)
 */
function createBean(rule, beanPurposeType, api, properties, definitionMap, definitionKey) {
    var schemaPackage = rule.schemaPackage(api.schema);
    var subPackage = rule.beanSubPackage(api);
    var package = api.package + '.' + schemaPackage;
    if (subPackage) {
        package = package + '.' + subPackage;
    }

    var remoteApiBean = new java.util.LinkedHashMap(); // new #{TopLevelBean}
    remoteApiBean.api = api;
    remoteApiBean.package = package;

    // detailモードの制御: detailの場合、/lido/product/list/{pagenumber} のとき、pagenumber もクラス名に入る。
    // デフォルト false だが、ruleで固定化することもできる。(五月雨追加による既存コード差分防止のため) (2026/05/16)
    var defaultDetail = false;
    var beanNamingOption = { detail: defaultDetail }; // option増えたら追加していく想定
    var fixedDetail = rule['isAvailableFixedDetailNamingOfBeanClassName'](api, beanNamingOption);

    // call e.g. rule.paramClassName(), rule.returnClassName()
    // #hope jflute 現場でオーバーライドがなくなったら、第二引数をoptionに変更したい (2026/05/16)
    var finalDetail = fixedDetail || defaultDetail;
    remoteApiBean.className = rule[beanPurposeType + 'ClassName'](api, /*detail*/finalDetail);

    remoteApiBean.definitionKey = definitionKey;

    // call e.g. rule.paramExtendsClass(), rule.returnExtendsClass()
    remoteApiBean.extendsClass = rule[beanPurposeType + 'ExtendsClass'](api, properties);

    // call e.g. rule.paramImplementsClasses(), rule.returnImplementsClasses()
    remoteApiBean.implementsClasses = rule[beanPurposeType + 'ImplementsClasses'](api, properties);

    remoteApiBean.properties = properties;
    remoteApiBean.beanPurposeType = beanPurposeType;

    // e.g. GET /lido/product/list
    remoteApiBean.remoteApiExp = api.httpMethod.toUpperCase() + ' ' + api.url;

    remoteApiBean.definitionMap = definitionMap;
    return remoteApiBean;
}

/**
 * Keep information of behavior.
 * まだexBehaviorMapに登録されていないBehaviorを登録して、requestメソッドのリソースオブジェクトを設定する。
 * すでに登録されているBehaviorでも、リソースオブジェクトを設定だけは行われる。
 * @param {RemoteApiRule} rule - RemoteApiRule.js object. (NotNull)
 * @param {Api} api - The information of api, having "schema", "url", ... (NotNull)
 * @param {PathVariable[]} pathVariables - The array of path variables. (NotNull)
 * @param {TopLevelBean} paramBean - The information of param bean. (NotNull)
 * @param {boolean} paramBeanArray - true if param bean is array. (NotNull)
 * @param {TopLevelBean} returnBean - The information of return bean. (NotNull)
 * @param {boolean} returnBeanArray - true if return bean is array. (NotNull)
 * @param {ExBehavior} exBehaviorMap - The map of behavior information, contains bsBehavior. (NotNull)
 */
function keepBehavior(rule, api, pathVariables, paramBean, paramBeanArray, returnBean, returnBeanArray, exBehaviorMap) {
    var schemaPackage = rule.schemaPackage(api.schema);
    var subPackage = rule.behaviorSubPackage(api);
    var package = api.package + '.' + schemaPackage;
    if (subPackage) {
        package = package + '.' + subPackage;
    }
    var className = rule.exBehaviorClassName(api);
    if (!exBehaviorMap[package + '.' + className]) { // 新しく登録されるBehaviorなら
        var exBehavior = new java.util.LinkedHashMap(); // new #{ExBehavior}
        exBehavior.package = package;
        exBehavior.className = className;
        exBehavior.remoteApiExp = subPackage;
        exBehaviorMap[package + '.' + className] = exBehavior;

        var bsBehavior = new java.util.LinkedHashMap(); //  new #{BsBehavior}
        bsBehavior.package = package; // base behaviorも同じパッケージ
        bsBehavior.className = rule.bsBehaviorClassName(api);
        bsBehavior.remoteApiExp = subPackage;
        bsBehavior.methodResourceList = []; // #{RequestMethodResource}
        exBehavior.bsBehavior = bsBehavior;
    }
    // 新しく登録されたBehaviorでも、すでに登録されているBehaviorでも
    var requestMethodResource = { // new #{RequestMethodResource}
        'api': api,
        'pathVariables': pathVariables, 
        'paramBean': paramBean, 'paramBeanArray': paramBeanArray,
        'returnBean': returnBean, 'returnBeanArray': returnBeanArray
    };
    exBehaviorMap[package + '.' + className].bsBehavior.methodResourceList.push(requestMethodResource);
}

// =======================================================================================
//                                                                               Generator
//                                                                               =========
/**
 * Process bean. (generating class)
 * @param {RemoteApiRule} rule - RemoteApiRule.js object. (NotNull)
 * @param {Array<Map>} remoteApiBeanList - The map list of all remoteApiBeans in the request. (NotNull)
 */
function generateBean(rule, remoteApiBeanList) {
    var sortRemoteApiBeanMap = new java.util.TreeMap();
    for (remoteApiBeanListIndex in remoteApiBeanList) {
        var remoteApiBean = remoteApiBeanList[remoteApiBeanListIndex];
        var path = remoteApiBean.package.replace(/\./g, '/') +'/' + remoteApiBean.className + '.java';
        sortRemoteApiBeanMap[path + '@@@@@' + (remoteApiBean.api ? remoteApiBean.api.url : '')] = remoteApiBean;
    }

    var uniqueRemoteApiBeanMap = {};
    for (remoteApiBeanKey in sortRemoteApiBeanMap) {
        var remoteApiBean = sortRemoteApiBeanMap[remoteApiBeanKey];
        var path = remoteApiBean.package.replace(/\./g, '/') + '/' + remoteApiBean.className + '.java';
        if (uniqueRemoteApiBeanMap[path] && uniqueRemoteApiBeanMap[path].properties != remoteApiBean.properties) {
            // beanClassName(), paramClassName(), returnClassName() are called here as 'detail' true
            print('warning duplication! try change path');
            remoteApiBean.className = rule[remoteApiBean.beanPurposeType + 'ClassName'](remoteApiBean.api, /*detail*/true);
            var path = remoteApiBean.package.replace(/\./g, '/') + '/' + remoteApiBean.className + '.java';
        }
        if (uniqueRemoteApiBeanMap[path] && uniqueRemoteApiBeanMap[path].properties != remoteApiBean.properties) {
            print('warning duplication! skip path');
        } else {
            uniqueRemoteApiBeanMap[path] = remoteApiBean;
        }
    }

    for (remoteApiBeanKey in uniqueRemoteApiBeanMap) {
        var remoteApiBean = uniqueRemoteApiBeanMap[remoteApiBeanKey];
        var path = remoteApiBean.package.replace(/\./g, '/') + '/' + remoteApiBean.className + '.java';
        generate('./remoteapi/RemoteApiBean.vm', path, remoteApiBean, remoteApiBean.definitionRemoteApiBean === null);
    }
}

/**
 * Generate behavior class, also DI xml.
 * @param {RemoteApiRule} rule - RemoteApiRule.js object. (NotNull)
 * @param {org.dbflute.logic.manage.freegen.DfFreeGenRequest} request - The current freeGen request. (NotNull)
 * @param {Map} exBehaviorMap - The map of behavior information. (NotNull)
 */
function generateBhv(rule, request, exBehaviorMap) {
    if (!rule['behaviorClassGeneration']) {
        return [];
    }
    var schema = rule.schema(request);
    var schemaPackage = rule.schemaPackage(schema);
    var className = rule.abstractBehaviorClassName(schema);
    var abstractBehavior = new java.util.LinkedHashMap();
    abstractBehavior.package = request.package + '.' + schemaPackage;
    abstractBehavior.className = className;
    abstractBehavior.remoteApiExp = request.requestName;
    abstractBehavior.frameworkBehaviorClass = rule['frameworkBehaviorClass'];
    var path = abstractBehavior.package.replace(/\./g, '/') + '/' + abstractBehavior.className + '.java';
    generate('./remoteapi/RemoteApiAbstractBehavior.vm', path, abstractBehavior, false);

    for (exBehaviorKey in exBehaviorMap) {
        var exBehavior = exBehaviorMap[exBehaviorKey];

        var bsBehavior = exBehavior.bsBehavior;
        bsBehavior.abstractBehavior = abstractBehavior;
        path = bsBehavior.package.replace(/\./g, '/') + '/' + bsBehavior.className + '.java';
        generate('./remoteapi/RemoteApiBsBehavior.vm', path, bsBehavior, true);

        path = exBehavior.package.replace(/\./g, '/') + '/' + exBehavior.className + '.java';
        generate('./remoteapi/RemoteApiExBehavior.vm', path, exBehavior, false);

        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // テストクラスの自動生成。既存ファイルは上書されず、その後手動で育てていく。
        // MockHttpClientを使って実行するUnitTestで、全項目を列挙して使用するコードを生成する。
        // (きっかけは、RemoteApiGen自身のテストで使うものとして作られた)
        // 
        // done jflute 環境変数技のままで良いのか？とりあえずoption的なものだからこれでいいかなというところで。 (2026/03/14)
        // 一時的に export FREE_GEN_REMOTEAPI_TESTZ=true ってやってから FreeGen を実行するスタイルでもいいし、
        // manage.sh に組み込んで常に生成するスタイルでもいい。
        //
        // 環境変数での制御は削除した。どう考えてもユーザーには使いづらいので。 (2026/05/16)
        // 全体制御とかは実際に使われ初めてフィードバックもらってから考える。
        //if (java.lang.System.getenv("FREE_GEN_REMOTEAPI_TEST") === 'true') {        
        //
        // #thinking jflute 既存ファイルを自動生成し直して見ると、ネスト周りのReturnのコードが消えたりとかで全体見直しが必要 (2026/03/14)
        //
        // #for_now jflute Remoteサーバーごとに自動生成の制御をできないと使いづらいと思ったのでruleで制御boolean。(2026/05/14)
        // スーパークラスの名前もruleで指定。さすがに環境変数にスーパークラスの名前を定義するのは微妙ということもあり。
        // _/_/_/_/_/_/_/_/
        if (rule.testClassGeneration) {
            path = '../../test/java/' + exBehavior.package.replace(/\./g, '/') + '/' + exBehavior.className + 'Test.java';
            generate('./remoteapi/RemoteApiExBehaviorTest.vm', path, exBehavior, false);
        }
    }

    var container = new java.util.LinkedHashMap();
    container.schemaPackage = schemaPackage;
    container.exBehaviorMap = exBehaviorMap;
    if (manager.isTargetContainerLastaDi()) {
        var path = rule.diXmlPath(schema, request.resourceFile);
        generate('./remoteapi/container/lastadi/RemoteApiDiXml.vm', path, container, true);
    }
    if (manager.isTargetContainerSpring()) {
        var javaConfigClass = new java.util.LinkedHashMap();
        javaConfigClass.package = request.package + '.' + schemaPackage;
        javaConfigClass.className = rule.javaConfigClassName(schema);;
        var path = abstractBehavior.package.replace(/\./g, '/') + '/' + javaConfigClass.className + '.java';
        generate('./remoteapi/container/spring/RemoteApiBeansJavaConfig.vm', path, javaConfigClass, true);

        path = 'org/lastaflute/spring/LastafluteBeansJavaConfig.java';
        generate('./remoteapi/container/spring/LastafluteBeansJavaConfig.vm', path, javaConfigClass, true);
    }
}

/**
 * Generate document of generated remoteApi classes on LastaDoc.
 * @param {RemoteApiRule} rule - RemoteApiRule.js object. (NotNull)
 * @param {org.dbflute.logic.manage.freegen.DfFreeGenRequest} request - The current freeGen request. (NotNull)
 * @param {Map} exBehaviorMap - The map of behavior information. (NotNull)
 */
function generateDoc(rule, request, exBehaviorMap) {
    if (!rule['docGeneration']) {
        return;
    }
    var doc = new java.util.LinkedHashMap();
    doc.schema = rule.schema(request);
    doc.schemaPackage = rule.schemaPackage(doc.schema);
    doc.exBehaviorMap = exBehaviorMap;
    var docHtml = generate('./' + genType + '/doc/RemoteApiDocHtml.vm', null, doc, true);
    var lastaDocHtmlPathList = manager.getLastaDocHtmlPathList();
    var markNaviLink = manager.getLastaDocHtmlMarkFreeGenDocNaviLink();
    var markBody = manager.getLastaDocHtmlMarkFreeGenDocBody();
    var naviLinkHtml = '    | <a href="#' + genType + '">to ' + genType + '</a>';
    var naviLinkDestinationHtml = '<span id="' + genType + '"></span>';
    for (var lastaDocHtmlPathIndex in lastaDocHtmlPathList) {
        var lastaDocHtmlPath = java.nio.file.Paths.get(lastaDocHtmlPathList[lastaDocHtmlPathIndex]);
        var lastaDocHtml = Java.type('java.lang.String').join('\n', java.nio.file.Files.readAllLines(lastaDocHtmlPath));
        if (!lastaDocHtml.contains(naviLinkHtml)) {
            lastaDocHtml = lastaDocHtml.replace(markNaviLink, naviLinkHtml + '\n' + markNaviLink);
        }
        if (!lastaDocHtml.contains(naviLinkDestinationHtml)) {
            docHtml = naviLinkDestinationHtml + docHtml;
        }
        java.nio.file.Files.write(lastaDocHtmlPath, lastaDocHtml.replace(markBody, docHtml + '\n' + markBody).getBytes('UTF-8'));
    }
}

// =======================================================================================
//                                                                            Assist Logic
//                                                                            ============
/**
 * Generate files such as java and html from vm files and meta data.
 * @param {string} src - The relative path (from freegen directory) of source template file. (NotNull)
 * @param {string} dest - The relative path (from freegen directory) of destination generated class file. (NotNull)
 * @param {Map} data - metadata for generation, e.g. remoteApiBean, exBehavior, ... (NotNull)
 * @param {boolean} overwite - true to overwrite even if the file already exists (NotNull)
 * @returns String generated output from velocity. (NotNull)
 */
function generate(src, dest, data, overwite) {
    if (dest === null) {
        return generator.parse(src, dest, 'data', data);
    }
    destFilePathList.push(dest);
    if (!java.nio.file.Files.exists(java.nio.file.Paths.get(generator.outputPath, dest)) || overwite) {
        manager.makeDirectory(dest);
        print('generate("' + dest + '")');
        return generator.parse(src, dest, 'data', data);
    }
    return '';
}

/**
 * Clean up generate files. Delete unnecessary files.
 * @param {RemoteApiRule} rule - RemoteApiRule.js object. (NotNull)
 * @param {org.dbflute.logic.manage.freegen.DfFreeGenRequest} request - The current freeGen request. (NotNull)
 * @param {string} genDir - generate directory (NotNull)
 * @param {string[]} destFilePathList - The list of destination generated file path. (NotNull)
 */
function clean(rule, request, genDir, destFilePathList) {
    var generateAbsolutePathList = [];
    for (var srcPathIndex in destFilePathList) {
        generateAbsolutePathList.push(new java.io.File(generator.outputPath, destFilePathList[srcPathIndex]).getAbsolutePath());
    }
    var list = listFiles(new java.io.File(generator.outputPath, genDir));
    for (var index in list) {
        var file = list[index];
        if (generateAbsolutePathList.indexOf(file.getAbsolutePath()) === -1
                && (rule == null || rule.deleteTarget(request, file))) {
            print('delete(' + file + ')');
            file.delete();
        }
    }
}

/**
 * Get files recursively from a directory.
 * @param {string} dir - directory (NotNull)
 */
function listFiles(dir) {
    var list = [];
    var fileList = dir.listFiles();
    for (var fileIndex in fileList) {
        var file = fileList[fileIndex];
        if (file.isDirectory()) {
            list = list.concat(this.listFiles(file));
        } else if (file.isFile()) {
            list.push(file);
        }
    }
    return list;
}
