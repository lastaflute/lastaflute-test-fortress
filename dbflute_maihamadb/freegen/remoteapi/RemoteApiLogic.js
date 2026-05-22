/**
 * Defines the logical processing used when automatically generating Java code and configuration files in Velocity.
 * This is because Velocity's processing is devoted to auto-generated output.
 * Based on ECMAScript5. Because Nashorn of java 8 is ECMAScript5.
 */
// =======================================================================================
//                                                                              Definition
//                                                                              ==========
// {PathVariable}, {TopLevelBean} types are defined on RemoteApiRule.js
/**
 * ExBehavior Type.
 * Extended Behaviorに相当。Base Behaviorの情報も保持する。
 * 
 * You can search it on code by: #{ExBehavior}
 * 
 * @typedef {Object} ExBehavior
 * @property {string} package - The full package of this behavior class. (NotNull, NotEmpty)
 * @property {string} className - The class name without package of this class.  (NotNull, NotEmpty)
 * @property {string} remoteApiExp - The expression of the remote api used in JSDoc, e.g. lido.product (NotNull)
 * @property {BsBehavior} bsBehavior - The parent class of this behavior as generation gap pattern. (NotNull)
 */

/**
 * BsBehavior Type.
 * Base Behaviorに相当。requestメソッドなどのメソッド情報も保持する。
 * 
 * You can search it on code by: #{BsBehavior}
 * 
 * @typedef {Object} BsBehavior
 * @property {string} package - The full package of this behavior class. (NotNull, NotEmpty)
 * @property {string} className - The class name without package of this class.  (NotNull, NotEmpty)
 * @property {string} remoteApiExp - The expression of the remote api used in JSDoc, e.g. lido.product (NotNull)
 * @property {RequestMethodResource[]} methodResourceList - The array of resource for request method on base behavior. (NotNull)
 */

/**
 * RequestMethodResource Type.
 * Behaviorのrequestメソッド(one API)の基本情報(IN/OUTなどの外観)に相当。
 * 
 * You can search it on code by: #{RequestMethodResource}
 * 
 * @typedef {Object} RequestMethodResource
 * @property {Api} api - The API metadata corresponding to the bean. (NotNull)
 * @property {PathVariable[]} pathVariables - The array of path variables. (NotNull, EmptyAllowed)
 * @property {TopLevelBean} paramBean - The information of param bean as top level. (NotNull)
 * @property {boolean} paramBeanArray - true if the paramBean is array. (NotNull)
 * @property {TopLevelBean} returnBean - The information of return bean as top level. (NotNull)
 * @property {boolean} returnBeanArray - true if the returnBean is array. (NotNull)
 */

/**
 * MethodParameter Type.
 * requestメソッドの引数に相当。
 * 
 * You can search it on code by: #{MethodParameter}
 * 
 * @typedef {Object} MethodParameter
 * @property {string} name - The parameter name, requestメソッドの引数名. (NotEmpty)
 * @property {string} class - The class name without package of parameter. (NotEmpty)
 * @property {string} description - The description of parameter for e.g. JavaDoc. (NotEmpty)
 */

/**
 * BehaviorMethod Type.
 * Behavior の one API 対応のメソッドたち (request, ruleOf) に相当。
 * 
 * You can search it on code by: #{BehaviorMethod}
 * 
 * @typedef {Object} BehaviorMethod
 * @property {RequestMethodResource} exteriorResource - The resource of method exterior for one API. (NotNull).
 * @property {string} behaviorRequestMethodName - The behavior request method name. e.g. requestXxx, requestXxxGet (NotEmpty).
 * @property {string} callDoRequestMethodName - The call doRequest method name. e.g. doRequestGet, doRequestPost (NotEmpty).
 * @property {string} param - The Java code expression of "param" argument, e.g. param, query(param), noQuery(), noRequestBody() (NotEmpty).
 * @property {string} behaviorRuleMethodName - The behavior rule method name. e.g. ruleOfXxx, ruleOfXxxGet (NotEmpty).
 * @property {MethodParameter[]} parameterList - The list of parameter on behavior method. (NotNull, EmptyAllowed)
 * @property {string} parameterDefinition - The Java code expression of parameter definition for request method. e.g. "Consumer<...Param> paramLambda" (NotNull, EmptyAllowed)
 * @property {string} parameterDefinitionRule - The Java code expression of parameter definition for rule method. e.g. e.g. "Consumer<FlutyRemoteApiRule> ruleLambda" (NotNull, EmptyAllowed)
 * @property {string} moreUrl - The Java code expression of e.g. moreUrl(xxx) or noMoreUrl() (NotNull, NotEmpty)
 * @property {string} paramBeanClassName - The type expression of param bean class without package. e.g. ...Param, List<...Param> (NotNull, EmptyAllowed)
 * @property {string} returnBeanClassName - The type expression of return bean class without package.  e.g. void, ...Return, List<...Return> (NotNull, EmptyAllowed)
 */
// ↑RequestMethodResourceがロジックの中で入り乱れるので、BehaviorMethodの変数名は exteriorResource (メソッドの外観リソース) にした。(2026/03/15)

/**
 * BeanProperty Type.
 * 自動生成されるBeanクラスの 1 Property情報に相当。主に自動生成コードの情報。
 * 
 * You can search it on code by: #{BeanProperty}
 * 
 * @typedef {Object} BeanProperty
 * @property {string} fieldName - The instance variable (field) name of the property. (NotNull, NotEmpty)
 * @property {string[]} annotationList - The Java code expression of annotation for the instance variable. e.g. "@Required" (NotNull)
 * @property {string} javadocComment - The whole expression of Javadoc for the instance variable. (NotNull, NotEmpty)
 * @property {string} fieldClass - The class name (without package if necessary) of the instance variable. (NotNull, NotEmpty)
 * @property {BeanNestBean} nestBean - The metadata of nest bean for the property. (NullAllowed: when no nest)
 */

/**
 * BeanNestBean Type.
 * BeanのネストクラスのBeanの総合情報に相当。主に自動生成コードの情報。
 * 
 * You can search it on code by: #{BeanNestBean}
 * 
 * @typedef {Object} BeanNestBean
 * @property {string} nestType - The class name with package of nest class. (NotNull, NotEmpty)
 * @property {BeanProperty} propertyList - The list of bean property for the nest bean. (NotNull, NotEmpty)
 */


var remoteApiLogic = {

    // ===================================================================================
    //                                                                      General Import
    //                                                                      ==============
    /**
     * Derive the java import class list separated by categolized package.
     * Unique and sort java import class list. And return the java import class list separated by categolized package.
     * @param {string[]} importClassList The list of java import class(with package). (NotNull, EmptyAllowed)
     * @param {string} currentPackage Package name of the class to declare import. (NotNull, EmptyAllowed)
     * @param {string[]} importCategolizedPackageOrderList The order list of categolized java package of import. (NotNull, EmptyAllowed)
     * @return {string[][]} java import class list separated by categolized package. It is a two-dimensional array. (NotNull, EmptyAllowed)
     *     <pre>
     *     e.g.
     *     importClassList = ["apppackage.xxx.Xxx", "apppackage.yyy.Yyy", "apppackage.zzzz.Zzz", "org.xxx.Xxx", "org.yyy.Yyy", "java.xxx.Xxx", "java.yyy.Yyy"]
     *     currentPackage = "apppackage.xxx.Xxx"
     *     importCategolizedPackageOrderList = ["java", "javax", "junit", "org", "com"],
     *     the return value will be
     *     [["java.xxx.Xxx", "java.yyy.Yyy"], ["org.xxx.Xxx", "org.xxx.Xxx"], ["apppackage.yyy.Yyy"], ["apppackage.zzzz.Zzz"]]
     *     </pre>
     */
    deriveCategolizedImportClassList: function(importClassList, currentPackage, importCategolizedPackageOrderList) {
        // Unique importClass.
        var uniqueImportClassList = new java.util.ArrayList(new java.util.HashSet(importClassList));

        // Sort importClass.
        uniqueImportClassList.sort(function(preImportClass, currentImportClass) {
            // Sort at the categolized of the importClass package name.
            // Use importCategolizedPackageOrderList. e.g. java, javax, junit, org, com ...
            var preImportCategolizedPackageOrderIndex = Number.MAX_VALUE;
            var currentImportCategolizedPackageOrderIndex = Number.MAX_VALUE;
            importCategolizedPackageOrderList.forEach(function(importCategolizedPackageOrder, importCategolizedPackageOrderIndex) {
                // packageなしクラスを取り扱うことは想定の必要もないくらいありえないので、indexOf()の-1の可能性はチェックなし (2026/05/16)
                if (importCategolizedPackageOrder === preImportClass.substring(0, preImportClass.indexOf('.'))) {
                    preImportCategolizedPackageOrderIndex = importCategolizedPackageOrderIndex;
                }
                if (importCategolizedPackageOrder === currentImportClass.substring(0, currentImportClass.indexOf('.'))) {
                    currentImportCategolizedPackageOrderIndex = importCategolizedPackageOrderIndex;
                }
            });

            if (preImportCategolizedPackageOrderIndex !== currentImportCategolizedPackageOrderIndex) {
                return preImportCategolizedPackageOrderIndex > currentImportCategolizedPackageOrderIndex ? 1 : -1;
            }

            // Sort at the importClass package name.
            var preImportPackage = preImportClass.substring(0, preImportClass.lastIndexOf('.'));
            var currentImportPackage = currentImportClass.substring(0, currentImportClass.lastIndexOf('.'));
            var packageCompare = preImportPackage.localeCompare(currentImportPackage);
            if (packageCompare !== 0) {
                return packageCompare;
            }

            // Sort at the importClass class name.
            var preClass = preImportClass.substring(preImportClass.lastIndexOf('.'));
            var currentClass = currentImportClass.substring(currentImportClass.lastIndexOf('.'));
            var classCompare = preClass.localeCompare(currentClass);
            return classCompare;
        });

        // Assemble an import declaration.
        var categolizedImportClassList = [];
        var keepCategolizedPackage = '';
        uniqueImportClassList.forEach(function(importClass) {
            // If importClass is the same as currentPackage, skip. (Because no import declaration is required).
            if (importClass.substring(0, importClass.lastIndexOf('.')) === currentPackage) {
                return;
            }

            // If the categolized Package changes, insert a blank line.
            if (importClass.substring(0, importClass.indexOf('.')) !== keepCategolizedPackage) {
                keepCategolizedPackage = importClass.substring(0, importClass.indexOf('.'));
                categolizedImportClassList.push([]);
            }
            categolizedImportClassList[categolizedImportClassList.length - 1].push(importClass);
        });

        return categolizedImportClassList;
    },

    // ===================================================================================
    //                                                                     Behavior Method
    //                                                                     ===============
    /**
     * Derive the behavior method list. (for one behavior)
     * @param {RemoteApiRule} rule - RemoteApiRule.js object. (NotNull)
     * @param {RequestMethodResource[]} methodResourceList The list of request method resources for one behavior. (NotNull, EmptyAllowed)
     * @return {BehaviorMethod[]} The list of behavior method information. (NotNull, EmptyAllowed)
     */
    deriveBehaviorMethodList: function(rule, methodResourceList) {
        if (!rule.behaviorMethodGeneration) {
            return [];
        }

        var behaviorMethodList = []; // #{BehaviorMethod}
        var behaviorRequestMethodSignatureList = [];
        methodResourceList.forEach(function(methodResource) {
            var behaviorApi = methodResource.api;
            var behaviorMethod = { // new #{BehaviorMethod}
                exteriorResource: methodResource,  // #{RequestMethodResource}
                behaviorRequestMethodName: rule.behaviorRequestMethodName(behaviorApi), // e.g. requestProductList
                callDoRequestMethodName: null,     // e.g. doRequestGet, doRequestPost
                param: null,                       // e.g. param, query(param), noQuery(), noRequestBody()
                behaviorRuleMethodName: rule.behaviorRuleMethodName(behaviorApi), // e.g. ruleOfProductList
                parameterList: [],                 // array of #{MethodParameter}
                parameterDefinition: '',           // e.g. Consumer<...Param> paramLambda
                parameterDefinitionRule: '',       // e.g. Consumer<FlutyRemoteApiRule> ruleLambda
                moreUrl: '',                       // e.g. moreUrl(xxx) or noMoreUrl()
                paramBeanClassName: null,          // e.g. ...Param, List<...Param>
                returnBeanClassName: 'void',       // e.g. void, ...Return, List<...Return>
            };
            behaviorMethodList.push(behaviorMethod);

            var typeMap = rule.typeMap();

            // Analyze pathVariables.
            methodResource.pathVariables.entrySet().forEach(function(pathVariableEntry) {
                var pathVariableName = rule.fieldName(behaviorApi, {'in': 'path'}, pathVariableEntry.key);
                behaviorMethod.behaviorRuleMethodName = behaviorMethod.behaviorRuleMethodName + manager.initCap(pathVariableName);

                var pathVariable = pathVariableEntry.value;
                var pathVariableManualMappingClass = rule.pathVariableManualMappingClass(behaviorApi, pathVariable);
                var pathVariableClass = '';
                if (pathVariable.type === 'array') {
                    if (pathVariableManualMappingClass) {
                        pathVariableClass = typeMap[pathVariable.type] + '<' + pathVariableManualMappingClass + '>';
                    } else if (typeMap[pathVariable.items.format]) {
                        pathVariableClass = typeMap[pathVariable.type] + '<' + typeMap[pathVariable.items.format] + '>';
                    } else if (typeMap[pathVariable.items.type]) {
                        pathVariableClass = typeMap[pathVariable.type] + '<' + typeMap[pathVariable.items.type] + '>';
                    }
                } else if (pathVariableManualMappingClass) {
                    pathVariableClass = pathVariableManualMappingClass;
                } else if (typeMap[pathVariable.format]) {
                    pathVariableClass = typeMap[pathVariable.format];
                } else if (typeMap[pathVariable.type]) {
                    pathVariableClass = typeMap[pathVariable.type];
                }

                // #thinking p1us2er0 temporary for beanPropertyManualMappingDescription. (2017/10/10)
                pathVariable.name = pathVariableName;
                var enumValueComment = '';
                if (pathVariable.enum) {
                    enumValueComment = '(enumValue=' + pathVariable.enum + ') ';
                } else if (pathVariable.items && pathVariable.items.enum) {
                    enumValueComment = '(enumValue=' + pathVariable.items.enum + ') ';
                }

                var pathVariableDescription = rule.pathVariableManualMappingDescription(behaviorApi, pathVariable);
                if (!pathVariableDescription) {
                    pathVariableDescription = pathVariable.description;
                }
                var pathVariableParameter = { // new #{MethodParameter}
                    'name': pathVariableName,
                    'class': pathVariableClass,
                    'description': 'The value of path variable for ' + pathVariableName + '. ' + enumValueComment
                                   + (pathVariableDescription ? '(' + pathVariableDescription + ') ' : '') + '(NotNull)'
                };
                behaviorMethod.parameterList.push(pathVariableParameter);

                behaviorMethod.parameterDefinition = behaviorMethod.parameterDefinition
                                                     + pathVariableClass + ' ' + pathVariableName + ', ';

                behaviorMethod.moreUrl = behaviorMethod.moreUrl + pathVariableName + ', ';
            });

            if (behaviorMethod.moreUrl) {
                behaviorMethod.moreUrl = behaviorMethod.moreUrl.replace(/^(.+), $/, 'moreUrl($1)');
            } else {
                behaviorMethod.moreUrl = 'noMoreUrl()';
            }

            // Analyze paramBean.
            if (methodResource.paramBean.className) {
                behaviorMethod.paramBeanClassName = methodResource.paramBean.className;
                if (methodResource.paramBeanArray) { // arrayならarray表現で囲う
                    // paramBeanの方はコールバックで落ちてくるだけなのでListはtypeMap参照せずに固定になっている (2026/03/19)
                    behaviorMethod.paramBeanClassName = 'java.util.List<' + behaviorMethod.paramBeanClassName + '>';
                }
                behaviorMethod.parameterDefinition = behaviorMethod.parameterDefinition
                                                     + 'Consumer<' + behaviorMethod.paramBeanClassName + '> paramLambda';
                var paramParameter = { // new #{MethodParameter}
                    'name': 'paramLambda',
                    'class': 'Consumer',
                    'description': 'The callback for ' + behaviorMethod.paramBeanClassName + '. (NotNull)'
                };
                behaviorMethod.parameterList.push(paramParameter);
            }

            if (behaviorMethod.parameterDefinition) {
                behaviorMethod.parameterDefinition = behaviorMethod.parameterDefinition.replace(/, $/, '');
            }

            behaviorMethod.parameterDefinitionRule = behaviorMethod.parameterDefinition;
            if (behaviorMethod.parameterDefinitionRule) { // すでに先の引数があったらカンマ付ける
                behaviorMethod.parameterDefinitionRule = behaviorMethod.parameterDefinitionRule + ', ';
            }
            behaviorMethod.parameterDefinitionRule = behaviorMethod.parameterDefinitionRule
                                                     + 'Consumer<FlutyRemoteApiRule> ruleLambda';

            // Analyze returnBean.
            if (methodResource.returnBean.className) {
                behaviorMethod.returnBeanClassName = methodResource.returnBean.className;
                if (methodResource.returnBeanArray) { // arrayならarray表現で囲う
                    behaviorMethod.returnBeanClassName = typeMap['array'] + '<' + behaviorMethod.returnBeanClassName + '>';
                }
            }

            behaviorMethod.returnBeanClassName = rule.unDefinitionKey(behaviorMethod.returnBeanClassName);

            // Adjust method name.
            var parameterSignature = ''; // 1メソッドの引数セットを一意に識別する文字列 (生成コードで使われるわけじゃない)
            behaviorMethod.parameterList.forEach(function(parameter) {
                parameterSignature = parameterSignature + '|' + parameter['class'];
            });

            // requestメソッドをメソッド名と引数で一意に識別する文字列 (生成コードで使われるわけじゃない)
            var behaviorRequestMethodSignature = behaviorMethod.behaviorRequestMethodName + parameterSignature;

            // 同じ引数セットのrequestメソッドがすでに存在していたら、メソッド名にパス変数名を入れる。
            // #thinking jflute でも、パス変数もparameterListに入ってるから引数だけで区別されるので、どういうケース想定？ (2026/03/19)
            // e.g. /lido/product/list/ と /lido/product/list/{pageNumber} は、どちらも requestProductList() になるので。
            // (続き)試しに、この Signature 関連の処理をまるごと削除して自動生成しても本家テストでは生成コード何も変わらなかった。 (2026/03/19)
            if (behaviorRequestMethodSignatureList.indexOf(behaviorRequestMethodSignature) >= 0) {
                methodResource.pathVariables.entrySet().forEach(function(pathVariableEntry) {
                    var pathVariableName = rule.fieldName(behaviorApi, {'in': 'path'}, pathVariableEntry.key);
                    behaviorMethod.behaviorRequestMethodName = behaviorMethod.behaviorRequestMethodName + manager.initCap(pathVariableName);
                });
                behaviorRequestMethodSignature = behaviorMethod.behaviorRequestMethodName + parameterSignature;
            }

            behaviorRequestMethodSignatureList.push(behaviorRequestMethodSignature)
            behaviorRequestMethodSignatureList.push('do' + behaviorRequestMethodSignature + '|Consumer');

            // Adjust call doRequestMethodName.
            var calloRequestSuffix = '';
            if (behaviorApi.httpMethod === 'delete' && methodResource.paramBean.in === 'json') {
                calloRequestSuffix = 'Enclosing';
            }

            behaviorMethod.callDoRequestMethodName = 'doRequest' + manager.initCap(behaviorApi.httpMethod) + calloRequestSuffix;

            // Analyze param.
            if (behaviorApi.httpMethod === 'get' || behaviorApi.httpMethod === 'delete') {
                behaviorMethod.param = 'noQuery()';
            } else {
                behaviorMethod.param = 'noRequestBody()';
            }

            var paramMap = {
                query: 'query(param)',
                formData: 'param',
                json: 'param',
                xml: 'param',
            };
            if (paramMap[methodResource.paramBean.in]) {
                behaviorMethod.param = paramMap[methodResource.paramBean.in];
            }
        });

        return behaviorMethodList;
    },

    // ===================================================================================
    //                                                                RemoteApiBean Import
    //                                                                ====================
    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // #hope jflute topLevelBean, ネスト呼び出しの時もtop(root)のbeanが引数で指定されるが... (2026/03/11)
    // rule.js内ではネストのbeanも区別したいので、topとcurrentを両方入れる、もしくは、stackでチェーン渡しするかしたいところ。
    // そして、rule.js の targetField も刷新したいところ。ただし、互換性のために別関数を用意することにはなる。
    // _/_/_/_/_/_/_/_/
    // {ApiProperty}, {TopLevelBean} types are defined on RemoteApiRule.js
    /**
     * Derive the bean import list for the specified properties.
     * @param {RemoteApiRule} rule - RemoteApiRule.js object. (NotNull)
     * @param {TopLevelBean} topLevelBean そのプロパティたち(properties)を定義しているbeanだが、ネストのときも常にtop(root)のBeanになる (NotNull)
     * @param {Map<String, ApiProperty>} properties API仕様の "properties" に相当するオブジェクト (NotNull)
     * @param {List<String>} importList The mutable list of import statement, added by this. (NotNull)
     * @param {Map<String, Object>} definitionMap The mutable map of all schema definitions for remote api. (NotNull)
     */
    deriveBeanImportList: function(rule, topLevelBean, properties, importList, definitionMap) {
        if (!properties.size) { // size() で not function というエラーが出るケースがあったので (2026/03/07)
            return;
        }
        if (properties.size() === 0) {
            return;
        }
        // #thinking jflute topLevelBean=PetDefinition のときだけ topLevelBean.api が null になるのなぜ？(しかもdefetcだけ) (2026/03/12)
        //if (!topLevelBean.api) {
        //    print('@@@1 topLevelBean=' + topLevelBean.className);
        //}

        var serializedNameTargetList = ['query', 'formData', 'json'];
        properties.entrySet().forEach(function(propertyEntry) {
            if (!remoteApiLogic.determineTargetBeanProperty(rule, topLevelBean, propertyEntry.key)) {
                return; // to avoid unused import statement (2026/03/12)
            }
            var property = propertyEntry.value; // #{ApiProperty}
            if (serializedNameTargetList.indexOf(topLevelBean.in) >= 0 && rule.isCustomFieldName(topLevelBean.api, topLevelBean, propertyEntry.key)) {
                importList.add('com.google.gson.annotations.SerializedName');
            } else if (topLevelBean.in === 'xml') {
                importList.add('javax.xml.bind.annotation.XmlElement');
            }

            if (property.required) {
                importList.add(property.type === 'array' ? 'javax.validation.constraints.NotNull' : 'org.lastaflute.web.validation.Required');
            }

            var nestType = null; // e.g. org.docksidestage.app.web.wx.request.json.WxRequestJsonBodyBody$ToscanaPart
            if (property.items && property.items['$ref']) { // #{ElementItem}
                nestType = java.net.URLDecoder.decode(property.items['$ref'].replace('#/definitions/', ''), 'UTF-8');
            } else if (property.items && property.items.allOf && property.items.allOf[0]['$ref']) {
                nestType = java.net.URLDecoder.decode(property.items.allOf[0]['$ref'].replace('#/definitions/', ''), 'UTF-8');
            } else if (property['$ref']) {
                nestType = java.net.URLDecoder.decode(property['$ref'].replace('#/definitions/', ''), 'UTF-8');
            } else if (property.allOf && property.allOf[0]['$ref']) {
                nestType = java.net.URLDecoder.decode(property.allOf[0]['$ref'].replace('#/definitions/', ''), 'UTF-8');
            }

            if (!nestType) {
                return;
            }
            var nestDefinition = definitionMap[nestType];
            if (!nestDefinition) {
                return;
            }
            var nestProperties = nestDefinition.properties;
            if (!nestProperties) { // no properties case
                // e.g.
                //  "org.docksidestage.app.web.wx.remogen.bean.suffix.NoSuffixCompletely$ResortPark": {
                //    "type": "object"
                //  },
                return;
            }
            if (nestProperties.size() !== 0) {
                nestProperties.entrySet().forEach(function(nestPropertyEntry) {
                    // #thinking jflute このrequiredの導出/反映、その後の処理に影響してるだろうか？ (2026/03/12)
                    // #for_now jflute targetField()でスキップで良いか迷ったので、ひとまずtargetField()は後にした (2026/03/12)
                    nestPropertyEntry.value.required = nestDefinition.required && nestDefinition.required.contains(nestPropertyEntry.key);
                    if (!remoteApiLogic.determineTargetBeanProperty(rule, topLevelBean, nestPropertyEntry.key)) {
                        return; // to avoid unused import statement (2026/03/12)
                    }
                    var nestProperty = nestPropertyEntry.value;
                    if (serializedNameTargetList.indexOf(topLevelBean.in) >= 0 && rule.isCustomFieldName(topLevelBean.api, topLevelBean, nestPropertyEntry.key)) {
                        importList.add('com.google.gson.annotations.SerializedName');
                    } else if (topLevelBean.in === 'xml') {
                        importList.add('javax.xml.bind.annotation.XmlElement');
                    }

                    if (nestProperty.required) {
                        importList.add(nestProperty.type === 'array' ? 'javax.validation.constraints.NotNull' : 'org.lastaflute.web.validation.Required');
                    }
                });

                if (definitionMap.containsKey(nestType)) {
                    var definition = definitionMap[nestType];
                    definitionMap.remove(nestType);

                    // recursive call here
                    remoteApiLogic.deriveBeanImportList(rule, topLevelBean, definition.properties, importList, definitionMap);
                }
            }
        });
    },

    // ===================================================================================
    //                                                              RemoteApiBean Property
    //                                                              ======================
    // {ApiProperty}, {TopLevelBean} types are defined on RemoteApiRule.js
    /**
     * Derive the bean property metadata for the specified property.
     * targetFieldの判定は含んでいる。対象外の場合は空オブジェクトが戻される。
     * @param {RemoteApiRule} rule - RemoteApiRule.js object. (NotNull)
     * @param {TopLevelBean} topLevelBean そのプロパティたち(properties)を定義しているbeanだが、ネストのときも常にtop(root)のBeanになる (NotNull)
     * @param {string} beanClassName The class name without package for the top level or nest bean. (NotNull)
     * @param {Map.Entry<String, ApiProperty>} propertyEntry top level bean class or nest bean class property entry of properties. (NotNull)
     * @param {List<String>} nestTypeFullNameList nest type full name list to avoid auto-generating duplicates. (NotNull)
     * @param {List<String>} nestTypeList nest type list to avoid auto-generating duplicates. (NotNull)
     * @return {BeanProperty} The metadata of the property, having e.g. fieldName, fieldClass. (NotNull, EmptyAllowed: if no target)
     */
    deriveBeanProperty: function(rule, topLevelBean, beanClassName, propertyEntry, nestTypeFullNameList, nestTypeList) {
        if (!remoteApiLogic.determineTargetBeanProperty(rule, topLevelBean, propertyEntry.key)) {
            // #for_now jflute nullだとvm側でうまく判定できなかったので、fieldName の有無などで判定してもらう (2026/03/09)
            return {};
        }

        var beanProperty = { // new #{BeanProperty}
            fieldName: rule.fieldName(topLevelBean.api, topLevelBean, propertyEntry.key),
            annotationList: [], // e.g. @Required, @javax.validation.Valid
            javadocComment: null, // e.g. /** ... */
            fieldClass: null, // e.g. String, Integer, ToscanaPart, java.util.List<ToscanaPart>
            nestBean: null, // #{BeanNestBean}
        };
    
        var property = propertyEntry.value; // #{ApiProperty}

        // #thinking p1us2er0 temporary for beanPropertyManualMappingDescription. (2017/10/10)
        // #thinking jflute rule.js の fieldName() で微調整された名前を rule.js の別の関数で参照させるためにってこと？ (2026/03/22)
        property.name = beanProperty.fieldName;

        // javadoc comment
        var enumValueComment = '';
        if (property.enum) {
            enumValueComment = '(enumValue=' + property.enum + ') ';
        } else if (property.items && property.items.enum) {
            enumValueComment = '(enumValue=' + property.items.enum + ') ';
        }

        var description = property.description;
        if (rule.beanPropertyManualMappingDescription(topLevelBean.api, beanClassName, property)) {
            description = rule.beanPropertyManualMappingDescription(topLevelBean.api, beanClassName, property);
        }
        if (description) {
            // enumのプロパティの不要な空白 e.g. "( *" が "(*" になる。(2026/05/15) 
            description = description.replace(/\n+/g, ' ').trim();
        }
        beanProperty.javadocComment = '/** The property of ' + beanProperty.fieldName + '. ' + enumValueComment
                                      + (description ? '(' + description + ') ' : '')
                                      + (property.required ? '' : '(NullAllowed) ') + '*/';

        // annotation
        var serializedNameTargetList = ['query', 'formData', 'json'];
        if (serializedNameTargetList.indexOf(topLevelBean.in) >= 0 && rule.isCustomFieldName(topLevelBean.api, topLevelBean, propertyEntry.key)) {
            beanProperty.annotationList.push('@SerializedName("' + propertyEntry.key + '")');
        } else if (topLevelBean.in == 'xml') {
            beanProperty.annotationList.push('@XmlElement(name = "' + propertyEntry.key + '")');
        }
        if (property.required) {
            beanProperty.annotationList.push(property.type == 'array' ? '@NotNull' : '@Required');
        }

        // field class
        var adjustNestType = function(rule, topLevelBean, nestType) {
            var index = nestType.lastIndexOf('.');
            if (index != -1) { // basically true
                // e.g. org.docksidestage.app.web.wx.request.json.WxRequestJsonBodyBody$ToscanaPart
                //      => WxRequestJsonBodyBody$ToscanaPart
                nestType = nestType.substring(index + 1);
            }
            var pureNestClassName = nestType.replace(/^.*[\$]/, ''); // e.g. WxRequestJsonBodyBody$ToscanaPart => ToscanaPart
            return rule.nestClassName(topLevelBean.api, pureNestClassName); // e.g. ProductRowBean => ProductRowPart
        };

        var nestType = ''; // e.g. org.docksidestage.app.web.wx.request.json.WxRequestJsonBodyBody$ToscanaPart
        var typeMap = rule.typeMap(); // e.g. { ..., 'int32': 'Integer', ..., 'string': 'String', ... }
        if (property.type == 'array') {
            if (rule.beanPropertyManualMappingClass(topLevelBean.api, beanClassName, property)) {
                beanProperty.fieldClass = typeMap[property.type] + '<' + rule.beanPropertyManualMappingClass(topLevelBean.api, beanClassName, property) + '>';
            } else if (typeMap[property.items.format]) {
                beanProperty.fieldClass = typeMap[property.type] + '<' + typeMap[property.items.format] + '>';
            } else if (typeMap[property.items.type]) {
                beanProperty.fieldClass = typeMap[property.type] + '<' + typeMap[property.items.type] + '>';
            } else if (property.items['$ref'] || (property.items && property.items.allOf[0]['$ref'])) {
                if (property.items['$ref']) {
                    nestType = java.net.URLDecoder.decode(property.items['$ref'].replace('#/definitions/', ''), 'UTF-8');
                } else if (property.items && property.items.allOf[0]['$ref']) {
                    nestType = java.net.URLDecoder.decode(property.items.allOf[0]['$ref'].replace('#/definitions/', ''), 'UTF-8');
                }

                beanProperty.fieldClass = typeMap[property.type] + '<' + adjustNestType(rule, topLevelBean, nestType) + '>';
                beanProperty.annotationList.push('@javax.validation.Valid');
            }
        } else if (rule.beanPropertyManualMappingClass(topLevelBean.api, beanClassName, property)) {
            beanProperty.fieldClass = rule.beanPropertyManualMappingClass(topLevelBean.api, beanClassName, property);
        } else if (typeMap[property.format]) {
            beanProperty.fieldClass = typeMap[property.format];
        } else if (typeMap[property.type]) {
            beanProperty.fieldClass = typeMap[property.type];
        } else if (property['$ref'] || (property.allOf && property.allOf[0]['$ref'])) {
            if (property['$ref']) {
                nestType = java.net.URLDecoder.decode(property['$ref'].replace('#/definitions/', ''), 'UTF-8');
            } else if (property.allOf[0]['$ref']) {
                nestType = java.net.URLDecoder.decode(property.allOf[0]['$ref'].replace('#/definitions/', ''), 'UTF-8');
            } // logically else no way here
            beanProperty.fieldClass = adjustNestType(rule, topLevelBean, nestType);
            beanProperty.annotationList.push('@javax.validation.Valid');
        } else { // type も ref も何も指定がないプロパティのとき
            // 古い RemoteApiGen に合わせて、デフォルト String にする。
            // https://github.com/dbflute-example/dbflute-example-with-remoteapi-gen/issues/34
            beanProperty.fieldClass = java.lang.String.class.getSimpleName();
        }

        if (beanProperty.fieldClass == '') {
            beanProperty.fieldClass = typeMap[''];
        }

        // @return The metadata of nest type, having e.g. nestType, propertyList. (NullAllowed: when no nest)
        var deriveNestBean = function(rule, topLevelBean, nestType, nestTypeFullNameList, nestTypeList) {
            if (!nestType
                    || nestTypeList.contains(nestType)
                    || nestTypeFullNameList.contains(nestType)
                    || nestTypeFullNameList.contains(java.lang.String.join('_', nestTypeList) + '_' + nestType)) {
                return null;
            }
    
            nestTypeList.add(nestType);
            nestTypeFullNameList.add(java.lang.String.join('_', nestTypeList));
    
            var beanNestBean = { // new #{BeanNestBean}
                nestType: adjustNestType(rule, topLevelBean, nestType), // e.g. ToscanaPart
                propertyList: [], // #{BeanProperty}
            };

            var nestDefinition = topLevelBean.definitionMap[nestType];
            if (nestDefinition) {
                var nestProperties = nestDefinition.properties; // may be no properties case
                if (nestProperties && nestProperties.size() !== 0) {
                    nestProperties.entrySet().forEach(function(nestPropertyEntry) {
                        if (remoteApiLogic.determineTargetBeanProperty(rule, topLevelBean, nestPropertyEntry.key)) {
                            // recursive call here
                            var nestBeanProperty = remoteApiLogic.deriveBeanProperty(rule // #{BeanProperty}
                                                                                   , topLevelBean
                                                                                   , nestType
                                                                                   , nestPropertyEntry
                                                                                   , nestTypeFullNameList
                                                                                   , nestTypeList);
                            beanNestBean.propertyList.push(nestBeanProperty);
                        }
                    });
                }
            }
            nestTypeList.remove(nestTypeList.size() - 1);
            return beanNestBean;
        };
        beanProperty.nestBean = deriveNestBean(rule, topLevelBean, nestType, nestTypeFullNameList, nestTypeList);

        return beanProperty;
    },

    // {ApiProperty}, {TopLevelBean} types are defined on RemoteApiRule.js
    /**
     * Determine whether the bean property is target field.
     * targetFieldの呼び出しを一元管理するために作った関数。
     * 将来、ruleのtargetField()をリファクタリングするかもしれないというのもあるし、
     * 重要な関数なので何かとあれこれしたいかもしれないのでラップしておく。
     * @param {RemoteApiRule} rule - RemoteApiRule.js object. (NotNull)
     * @param {TopLevelBean} topLevelBean そのプロパティたち(properties)を定義しているbeanだが、ネストのときも常にtop(root)のBeanになる (NotNull)
     * @param {string} propertyName The name of the bean property. (NotNull)
     * @return {boolean} true if target. (NotNull)
     */
    determineTargetBeanProperty: function(rule, topLevelBean, propertyName) {
        return rule.targetField(topLevelBean.api, topLevelBean, propertyName);
    },

    // ===================================================================================
    //                                                                      General Helper
    //                                                                      ==============
    /**
     * Returns the indent.
     * This indent is used in the auto-generated Java code.
     * Fixed indentation is written directly in the Velocity template, but is used when the indentation needs to be calculated.
     * Fixed indentation of Velocity template It is unified with 4 spaces.
     * @param {number} indentSize indent size. (NotNull)
     * @return {string} indent. e.g. if size is 2, the string is 8 spaces. (NotNull)
     */
    indent: function(indentSize) {
        // #for_now I want to set the initial value of indent to 0 size. by p1us2er0 (2022/05/04)
        // but it is not possible at this time due to a processing problem on the vm side.
        // Organize when refactoring the vm.
        var indent = '    ';
        for (var index = 0; index < indentSize; index++) {
            indent += '    ';
        }
        return indent;
    }
};
