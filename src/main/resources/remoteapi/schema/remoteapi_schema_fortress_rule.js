// _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
// RemoteApiGen your rule settings as ECMAScript5 (related to RemoteApiRule.js in freegen)
// _/_/_/_/_/_/_/_/_/_/

// ======================================================================================
//                                                                                  Base
//                                                                                 ======
// @Override
remoteApiRule.target = function(api) { // you can select generated API
    // #for_now remove this condition after fixing Parameters headache by jflute (2022/01/20)
    if (api.httpMethod === 'parameters') {
        return false;
    }
    if (api.url.indexOf('/default/') >= 0 || api.url.indexOf('/class/') >= 0) {
        return false; // cannot generate now (Reservation Word)
    }
    if (api.httpMethod === 'get' && api.url.indexOf('/onbodyjson') >= 0) {
        return false; // cannot generate now (GET but JsonBody)
    }
    if (api.url.indexOf('/json/swagger') >= 0) {
        return false; // cannot generate now (Too many nested Part classes)
    }
    return true;
}


// ======================================================================================
//                                                                               Behavior
//                                                                               ========
// e.g. resources having many nests
var canBeRestful = function(api) {
    return api.url.indexOf('/ballet-dancers/') >= 0
        || api.url.indexOf('/products/') >= 0
        || api.url.indexOf('/members/') >= 0
        || api.url.indexOf('/restlike') >= 0;
};

// generate hierarchical behaviors if resources hanving many nests
// @Override
remoteApiRule.behaviorSubPackage = function(api) {
    if (canBeRestful(api)) {
        return this.subPackage(api).replace(/^([^.]*)\.(.+)/, '$1.$2'); // default is $1 only
    } else {
        return baseRule.behaviorSubPackage(api);
    }
}

// always HTTP Method on request method
// @Override
remoteApiRule.behaviorRequestMethodName = function(api) {
    if (canBeRestful(api)) {
        var methodPart = manager.camelize(this.subPackage(api).replace(this.behaviorSubPackage(api), '').replace(/\./g, '_'));
        return 'request' + manager.initCap(methodPart) + (api.httpMethod ? manager.initCap(api.httpMethod) : '');
    } else {
        return baseRule.behaviorRequestMethodName(api);
    }
}

// always HTTP Method on rule method
// @Override
remoteApiRule.behaviorRuleMethodName = function(api) {
    if (canBeRestful(api)) {
        var methodPart = manager.camelize(this.subPackage(api).replace(this.behaviorSubPackage(api), '').replace(/\./g, '_'));
        return 'ruleOf' + manager.initCap(methodPart) + (api.httpMethod ? manager.initCap(api.httpMethod) : '');
    } else {
        return baseRule.behaviorRuleMethodName(api);
    }
}



// =======================================================================================
//                                                                            Param/Return
//                                                                            ============
// extends common super class for e.g. HTTP headers
// @Override
remoteApiRule.returnExtendsClass = function(api, properties) {
    if (api.httpMethod === 'get' && !api.url.endsWith('}/')) { // means List GET
        return "org.docksidestage.bizfw.remoteapi.AbstractListGetReturn";
    } else {
        return baseRule.returnExtendsClass(api, properties);
    }
}



// =======================================================================================
//                                                                                  Option
//                                                                                  ======
// @Override
remoteApiRule.typeMap = function() {
    var typeMap = baseRule.typeMap();
    typeMap['array'] = 'org.eclipse.collections.api.list.ImmutableList';
    return typeMap;
}

// name and type mapping for e.g. classification
var manualMappingClassMap = {
    'memberStatus': 'org.docksidestage.dbflute.allcommon.CDef.MemberStatus',
    'productStatus': 'org.docksidestage.dbflute.allcommon.CDef.ProductStatus',
};

// @Override
remoteApiRule.pathVariableManualMappingClass = function(api, pathVariable) {
    return manualMappingClassMap[pathVariable.name];
}

// @Override
remoteApiRule.beanPropertyManualMappingClass = function(api, beanClassName, property) {
    return manualMappingClassMap[property.name];
}
