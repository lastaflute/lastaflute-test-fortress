/**
 * Defines the logical processing used when automatically generating Java code and configuration files in Velocity.
 * This is because Velocity's processing is devoted to auto-generated output.
 * Based on ECMAScript5. Because Nashorn of java 8 is ECMAScript5.
 */
// =======================================================================================
//                                                                              Definition
//                                                                              ==========
/**
 * Method Type.
 * @typedef {Object} Method
 * @property {Api} api - The information of api. (NotNull)
 * @property {PathVariable[]} pathVariables - The array of path variables. (NotNull, EmptyAllowed)
 * @property {TopLevelBean} paramBean - The information of param bean. (NotNull)
 * @property {boolean} paramBeanArray - true if the paramBea is array. (NotNull)
 * @property {TopLevelBean} returnBean - The information of return bean. (NotNull)
 * @property {boolean} returnBeanArray - true if the returnBean is array. (NotNull)
 */

/**
 * Parameter Type.
 * @typedef {Object} Parameter
 * @property {string} name - The parameter Name. (NotEmpty)
 * @property {string} class - The class name without package of parameter. (NotEmpty)
 * @property {string} description - The description of parameter. (NotEmpty)
 */

/**
 * BehaviorMethod Type.
 * @typedef {Object} BehaviorMethod
 * @property {Method} method - The information of method. (NotNull).
 * @property {string} behaviorRequestMethodName - The behavior request method name. e.g. requestXxx, requestXxxGet (NotEmpty).
 * @property {string} callDoRequestMethodName - The call doRequest method name. e.g. doRequestGet, doRequestPost (NotEmpty).
 * @property {string} param - one of param, query(param), noQuery(), noRequestBody() (NotEmpty).
 * @property {string} behaviorRuleMethodName - The behavior rule method name. e.g. ruleOfXxx, ruleOfXxxGet (NotEmpty).
 * @property {Parameter[]} parameterList - the list of parameter of behavior method. (NotNull, EmptyAllowed)
 * @property {string} parameterDefinition - the parameter definition of behavior request method. (NotNull, EmptyAllowed)
 * @property {string} parameterDefinitionRule - the parameter definition of behavior rule method. (NotNull, EmptyAllowed)
 * @property {string} moreUrl - one of moreUrl(xxx), noMoreUrl(). (NotNull, NotEmpty)
 * @property {string} paramBeanClassName - the param bean class without package of behavior method. (NotNull, EmptyAllowed)
 * @property {string} returnBeanClassName - the return bean class without package of behavior method. (NotNull, EmptyAllowed)
 */

var remoteApiLogic = {

    // ===================================================================================
    //                                                                               Logic
    //                                                                               =====
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
    },

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
            preImportCategolizedPackageOrderIndex = Number.MAX_VALUE;
            currentImportCategolizedPackageOrderIndex = Number.MAX_VALUE;
            importCategolizedPackageOrderList.forEach(function(importCategolizedPackageOrder, importCategolizedPackageOrderIndex) {
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
            var prePackage = preImportClass.substring(0, preImportClass.lastIndexOf('.'));
            var currentPackage = currentImportClass.substring(0, currentImportClass.lastIndexOf('.'));
            var packageCompare = prePackage.localeCompare(currentPackage);
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

    /**
     * Derive the behavior method list.
     * @param {Object} rule remote api rule. (NotNull)
     * @param {Method[]} methodList The list of The method information. (NotNull, EmptyAllowed)
     * @return {BehaviorMethod[]} The list of behavior method information. (NotNull, EmptyAllowed)
     */
    deriveBehaviorMethodList: function(rule, methodList) {
        if (!rule.behaviorMethodGeneration) {
            return [];
        }

        var behaviorMethodList = [];
        var behaviorRequestMethodSignatureList = [];
        methodList.forEach(function(method) {

            var behaviorMethod = {
                method: method,
                behaviorRequestMethodName: rule.behaviorRequestMethodName(method.api),
                callDoRequestMethodName: null,
                param: null,
                behaviorRuleMethodName: rule.behaviorRuleMethodName(method.api),
                parameterList: [],
                parameterDefinition: '',
                parameterDefinitionRule: '',
                moreUrl: '',
                paramBeanClassName: null,
                returnBeanClassName: 'void',
            };
            behaviorMethodList.push(behaviorMethod);

            var typeMap = rule.typeMap();

            // Analyze pathVariables.
            method.pathVariables.entrySet().forEach(function(pathVariableEntry) {
                var pathVariableName = rule.fieldName(method.api, {'in': 'path'}, pathVariableEntry.key);
                behaviorMethod.behaviorRuleMethodName = behaviorMethod.behaviorRuleMethodName + manager.initCap(pathVariableName);

                var pathVariable = pathVariableEntry.value;
                var pathVariableManualMappingClass = rule.pathVariableManualMappingClass(method.api, pathVariable);
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

                // TODO p1us2er0 temporary for beanPropertyManualMappingDescription. (2017/10/10)
                pathVariable.name = pathVariableName;
                var enumValueComment = '';
                var nestType = '';
                if (pathVariable.enum) {
                    enumValueComment = '(enumValue=' + pathVariable.enum + ') ';
                } else if (pathVariable.items && pathVariable.items.enum) {
                    enumValueComment = '(enumValue=' + pathVariable.items.enum + ') ';
                }

                var pathVariableDescription = rule.pathVariableManualMappingDescription(method.api, pathVariable);
                if (!pathVariableDescription) {
                    pathVariableDescription = pathVariable.description;
                }
                behaviorMethod.parameterList.push({ 'name': pathVariableName, 'class': pathVariableClass, 'description': 'The value of path variable for ' + pathVariableName + '. ' + enumValueComment + (pathVariableDescription ? '(' + pathVariableDescription + ') ' : '') + '(NotNull)'});

                behaviorMethod.parameterDefinition = behaviorMethod.parameterDefinition + pathVariableClass + ' ' + pathVariableName + ', ';
                behaviorMethod.moreUrl = behaviorMethod.moreUrl + pathVariableName + ', ';
            });

            if (behaviorMethod.moreUrl) {
                behaviorMethod.moreUrl = behaviorMethod.moreUrl.replaceAll('^(.+), $', 'moreUrl($1)');
            } else {
                behaviorMethod.moreUrl = 'noMoreUrl()';
            }

            // Analyze paramBean.
            if (method.paramBean.className) {
                behaviorMethod.paramBeanClassName = method.paramBean.className;
                if (method.paramBeanArray) {
                    behaviorMethod.paramBeanClassName = 'java.util.List<' + behaviorMethod.paramBeanClassName + '>';
                }
                behaviorMethod.parameterDefinition = behaviorMethod.parameterDefinition + 'Consumer<' + behaviorMethod.paramBeanClassName + '> paramLambda';
                behaviorMethod.parameterList.push({ 'name': 'paramLambda', 'class': 'Consumer', 'description': 'The callback for ' + behaviorMethod.paramBeanClassName + '. (NotNull)'});
            }

            if (behaviorMethod.parameterDefinition) {
                behaviorMethod.parameterDefinition = behaviorMethod.parameterDefinition.replaceAll(', $', '');
            }

            behaviorMethod.parameterDefinitionRule = behaviorMethod.parameterDefinition;
            if (behaviorMethod.parameterDefinitionRule) {
                behaviorMethod.parameterDefinitionRule = behaviorMethod.parameterDefinitionRule + ', ';
            }
            behaviorMethod.parameterDefinitionRule = behaviorMethod.parameterDefinitionRule + 'Consumer<FlutyRemoteApiRule> ruleLambda';

            // Analyze returnBean.
            if (method.returnBean.className) {
                behaviorMethod.returnBeanClassName = method.returnBean.className;
                if (method.returnBeanArray) {
                    behaviorMethod.returnBeanClassName = typeMap['array'] + '<' + behaviorMethod.returnBeanClassName + '>';
                }
            }

            behaviorMethod.returnBeanClassName = rule.unDefinitionKey(behaviorMethod.returnBeanClassName);

            // Adjust method name.
            var parameterSignature = '';
            behaviorMethod.parameterList.forEach(function(parameter) {
                parameterSignature = parameterSignature + '|' + parameter['class'];
            });

            var behaviorRequestMethodSignature = behaviorMethod.behaviorRequestMethodName + parameterSignature;
            if (behaviorRequestMethodSignatureList.indexOf(behaviorRequestMethodSignature) >= 0) {
                method.pathVariables.entrySet().forEach(function(pathVariableEntry) {
                    var pathVariableName = rule.fieldName(method.api, {'in': 'path'}, pathVariableEntry.key);
                    behaviorMethod.behaviorRequestMethodName = behaviorMethod.behaviorRequestMethodName + manager.initCap(pathVariableName);
                });
                behaviorRequestMethodSignature = behaviorMethod.behaviorRequestMethodName + parameterSignature;
            }

            behaviorRequestMethodSignatureList.push(behaviorRequestMethodSignature)
            behaviorRequestMethodSignatureList.push('do' + behaviorRequestMethodSignature + '|Consumer');

            // Adjust call doRequestMethodName.
            var calloRequestSuffix = '';
            if (behaviorMethod.method.api.httpMethod === 'delete' && behaviorMethod.method.paramBean.in === 'json') {
                calloRequestSuffix = 'Enclosing';
            }

            behaviorMethod.callDoRequestMethodName = 'doRequest' + manager.initCap(behaviorMethod.method.api.httpMethod) + calloRequestSuffix;

            // Analyze param.
            if (behaviorMethod.method.api.httpMethod === 'get' || behaviorMethod.method.api.httpMethod === 'delete') {
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
            if (paramMap[behaviorMethod.method.paramBean.in]) {
                behaviorMethod.param = paramMap[behaviorMethod.method.paramBean.in];
            }
        });

        return behaviorMethodList;
    },

    /**
     * Derive the bean import list.
     * @param {Object} rule remote api rule. (NotNull)
     * @param {TopLevelBean} topLevelBean definition of bean where field is declared. (NotNull)
     * @param {Object} properties properties. (NotNull)
     * @param {Object} importList bean import list. (NotNull)
     * @param {Object} definitionMap All schema definitions for remote api. (NotNull)
     */
    deriveBeanImportList: function(rule, topLevelBean, properties, importList, definitionMap) {
        if (properties.size() === 0) {
            return;
        }

        var serializedNameTargetList = ['query', 'formData', 'json'];
        properties.entrySet().forEach(function(propertyEntry) {
            var property = propertyEntry.value;
            if (serializedNameTargetList.indexOf(topLevelBean.in) >= 0 && rule.isCustomFieldName(topLevelBean.api, topLevelBean, propertyEntry.key)) {
                importList.add('com.google.gson.annotations.SerializedName');
            } else if (topLevelBean.in === 'xml') {
                importList.add('javax.xml.bind.annotation.XmlElement');
            }

            if (property.required) {
                importList.add(property.type === 'array' ? 'javax.validation.constraints.NotNull' : 'org.lastaflute.web.validation.Required');
            }

            var nestType = null;
            if (property.items && property.items['$ref']) {
                nestType = java.net.URLDecoder.decode(property.items['$ref'].replace('#/definitions/', ''), 'UTF-8');
            } else if (property.items && property.items.allOf && property.items.allOf[0]['$ref']) {
                nestType = java.net.URLDecoder.decode(property.items.allOf[0]['$ref'].replace('#/definitions/', ''), 'UTF-8');
            } else if (property['$ref']) {
                nestType = java.net.URLDecoder.decode(property['$ref'].replace('#/definitions/', ''), 'UTF-8');
            } else if (property.allOf && property.allOf[0]['$ref']) {
                nestType = java.net.URLDecoder.decode(property.allOf[0]['$ref'].replace('#/definitions/', ''), 'UTF-8');
            }

            if (nestType && definitionMap[nestType] && definitionMap[nestType].properties.size() !== 0) {
                definitionMap[nestType].properties.entrySet().forEach(function(nestPropertyEntry) {
                    nestPropertyEntry.value.required = definitionMap[nestType].required && definitionMap[nestType].required.contains(nestPropertyEntry.key);
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
                    remoteApiLogic.deriveBeanImportList(rule, topLevelBean, definition.properties, importList, definitionMap);
                }
            }
        });
    },

    /**
     * Derive the bean property.
     * @param {Object} rule remote api rule. (NotNull)
     * @param {TopLevelBean} topLevelBean definition of bean where field is declared. (NotNull)
     * @param {Object} clazz top level bean class or nest bean class. (NotNull)
     * @param {Object} propertyEntry top level bean class or nest bean class property entry. (NotNull)
     * @param {Object} nestTypeFullNameList nest type full name list to avoid auto-generating duplicates. (NotNull)
     * @param {Object} nestTypeList nest type list to avoid auto-generating duplicates. (NotNull)
     */
    deriveBeanProperty: function(rule, topLevelBean, clazz, propertyEntry, nestTypeFullNameList, nestTypeList) {
        if (!rule.targetField(topLevelBean.api, topLevelBean, propertyEntry.key)) {
            return;
        }
    
        var propertyInfo = {
            fieldName: rule.fieldName(topLevelBean.api, topLevelBean, propertyEntry.key),
            annotationList: [],
            javadocComment: null,
            fieldClass: null,
            nestType: null,
        };

        var property = propertyEntry.value;
        // TODO p1us2er0 temporary for beanPropertyManualMappingDescription. (2017/10/10)
        property.name = propertyInfo.fieldName;

        // javadoc comment
        var enumValueComment = '';
        if (property.enum) {
            enumValueComment = '(enumValue=' + property.enum + ') ';
        } else if (property.items && property.items.enum) {
            enumValueComment = '(enumValue=' + property.items.enum + ') ';
        }
        
        var description = property.description;
        if (rule.beanPropertyManualMappingDescription(topLevelBean.api, clazz, property)) {
            description = rule.beanPropertyManualMappingDescription(topLevelBean.api, clazz, property);
        }
        propertyInfo.javadocComment = '/** The property of ' + propertyInfo.fieldName + '. ' + enumValueComment + (property.description ? '(' + property.description + ') ' : '') + (property.required ? '' : '(NullAllowed) ') + '*/';

        // annotation
        var serializedNameTargetList = ['query', 'formData', 'json'];
        if (serializedNameTargetList.indexOf(topLevelBean.in) >= 0 && rule.isCustomFieldName(topLevelBean.api, topLevelBean, propertyEntry.key)) {
            propertyInfo.annotationList.push('@SerializedName("' + propertyEntry.key + '")');
        } else if (topLevelBean.in == 'xml') {
            propertyInfo.annotationList.push('@XmlElement(name = "' + propertyEntry.key + '")');
        }
        if (property.required) {
            propertyInfo.annotationList.push(property.type == 'array' ? '@NotNull' : '@Required');
        }
        
        // field class
        var adjustNestType = function(rule, topLevelBean, nestType) {
            var index = nestType.lastIndexOf('.');
            if (index != -1) {
                nestType = nestType.substring(index + 1);
            }
            return rule.nestClassName(topLevelBean.api, nestType.replaceAll('^.*\\$', ''));
        };

        var nestType = '';
        var typeMap = rule.typeMap();
        if (property.type == 'array') {
            if (rule.beanPropertyManualMappingClass(topLevelBean.api, clazz, property)) {
                propertyInfo.fieldClass = typeMap[property.type] + '<' + rule.beanPropertyManualMappingClass(topLevelBean.api, clazz, property) + '>';
            } else if (typeMap[property.items.format]) {
                propertyInfo.fieldClass = typeMap[property.type] + '<' + typeMap[property.items.format] + '>';
            } else if (typeMap[property.items.type]) {
                propertyInfo.fieldClass = typeMap[property.type] + '<' + typeMap[property.items.type] + '>';
            } else if (property.items['$ref'] || (property.items && property.items.allOf[0]['$ref'])) {
                if (property.items['$ref']) {
                    nestType = java.net.URLDecoder.decode(property.items['$ref'].replace('#/definitions/', ''), 'UTF-8');
                } else if (property.items && property.items.allOf[0]['$ref']) {
                    nestType = java.net.URLDecoder.decode(property.items.allOf[0]['$ref'].replace('#/definitions/', ''), 'UTF-8');
                }

                propertyInfo.fieldClass = typeMap[property.type] + '<' + adjustNestType(rule, topLevelBean, nestType) + '>';
                propertyInfo.annotationList.push('@javax.validation.Valid');
            }
        } else if (rule.beanPropertyManualMappingClass(topLevelBean.api, clazz, property)) {
            propertyInfo.fieldClass = rule.beanPropertyManualMappingClass(topLevelBean.api, clazz, property);
        } else if (typeMap[property.format]) {
            propertyInfo.fieldClass = typeMap[property.format];
        } else if (typeMap[property.type]) {
            propertyInfo.fieldClass = typeMap[property.type];
        } else if (property['$ref'] || property.allOf[0]['$ref']) {
            if (property['$ref']) {
                nestType = java.net.URLDecoder.decode(property['$ref'].replace('#/definitions/', ''), 'UTF-8');
            } else if (property.allOf[0]['$ref']) {
                nestType = java.net.URLDecoder.decode(property.allOf[0]['$ref'].replace('#/definitions/', ''), 'UTF-8');
            }
            
            propertyInfo.fieldClass = adjustNestType(rule, topLevelBean, nestType);
            propertyInfo.annotationList.push('@javax.validation.Valid');
        }

        if (propertyInfo.fieldClass == '') {
            propertyInfo.fieldClass = typeMap[''];
        }

        var deriveNestType = function(rule, topLevelBean, nestType, nestTypeFullNameList, nestTypeList) {
            if (!nestType
                    || nestTypeList.contains(nestType)
                    || nestTypeFullNameList.contains(nestType)
                    || nestTypeFullNameList.contains(java.lang.String.join('_', nestTypeList) + '_' + nestType)) {
                return null;
            }
    
            nestTypeList.add(nestType);
            nestTypeFullNameList.add(java.lang.String.join('_', nestTypeList));
    
            var nestTypeInfo = {
                nestType: adjustNestType(rule, topLevelBean, nestType),
                propertyList: [],
            };
    
            if (topLevelBean.definitionMap[nestType] && topLevelBean.definitionMap[nestType].properties.size() !== 0) {
                topLevelBean.definitionMap[nestType].properties.entrySet().forEach(function(nestPropertyEntry) {
                    if (rule.targetField(topLevelBean.api, topLevelBean, nestPropertyEntry.key)) {
                        nestTypeInfo.propertyList.push(remoteApiLogic.deriveBeanProperty(rule, topLevelBean, nestType, nestPropertyEntry, nestTypeFullNameList, nestTypeList));
                    }
                });
            }
    
            nestTypeList.remove(nestTypeList.size() - 1);
    
            return nestTypeInfo;
        };

        propertyInfo.nestType = deriveNestType(rule, topLevelBean, nestType, nestTypeFullNameList, nestTypeList);

        return propertyInfo;
    }
};
