// _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
// RemoteApiGen your rule settings as ECMAScript5 (related to RemoteApiRule.js in freegen)
// _/_/_/_/_/_/_/_/_/_/

// ======================================================================================
//                                                                                  Base
//                                                                                 ======
// @Override
remoteApiRule.target = function(api) { // you can select generated API 
    if (baseRule.target(api)) { // don't forget calling super's
        // and define your original selecting (except)
        if (api.url.indexOf('/method/onbodyjson') !== -1 && api.httpMethod === 'get') { // get$onbodyjson()
            return false; // unsupported at RemoteApiGen for now (you can request by your manual method)
        }
        return true;
    } else { // already no way here since (2019/08/31) 
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // logic of RemoteApiGen's rule.js was already changed so this is old code
        // _/_/_/_/_/_/_/_/_/_/
        // no param/return is not generated as default, so specify it
        // HTTP METHOD determination is for excepting "parameters"
        return api.url.indexOf('/wx/remogen/tricky/allnone') !== -1 && api.httpMethod === 'post';
        // general way however also needs undefined determination? (api.consumes is undefined in other project)
        //return (api.consumes === null || api.consumes.length === 0) && (api.produces === null || api.produces.length === 0);
    }
}


// ======================================================================================
//                                                                               Behavior
//                                                                               ========
// generate hierarchical behaviors if resources hanving many nests
// @Override
remoteApiRule.behaviorSubPackage = function(api) {
    if (api.url.indexOf('/ballet-dancers/') >= 0 || api.url.indexOf('/products/') >= 0) { // e.g. resources having many nests
        return this.subPackage(api).replace(/^([^.]*)\.(.+)/, '$1.$2'); // default is $1 only
    } else {
        return baseRule.behaviorSubPackage(api);
    }
}

// always HTTP Method on request method
// @Override
remoteApiRule.behaviorRequestMethodName = function(api) {
    if (api.url.indexOf('/ballet-dancers/') >= 0 || api.url.indexOf('/products/') >= 0) {
        var methodPart = manager.camelize(this.subPackage(api).replace(this.behaviorSubPackage(api), '').replace(/\./g, '_'));
        return 'request' + manager.initCap(methodPart) + (api.httpMethod ? manager.initCap(api.httpMethod) : '');
    } else {
        return baseRule.behaviorRequestMethodName(api);
    }
}

// always HTTP Method on rule method
// @Override
remoteApiRule.behaviorRuleMethodName = function(api) {
    if (api.url.indexOf('/ballet-dancers/') >= 0 || api.url.indexOf('/products/') >= 0) {
        var methodPart = manager.camelize(this.subPackage(api).replace(this.behaviorSubPackage(api), '').replace(/\./g, '_'));
        return 'ruleOf' + manager.initCap(methodPart) + (api.httpMethod ? manager.initCap(api.httpMethod) : '');
    } else {
        return baseRule.behaviorRuleMethodName(api);
    }
}



// =======================================================================================
//                                                                            Param/Return
//                                                                            ============
// @Override
remoteApiRule.paramClassName = function(api, detail) {
    if (api.url.indexOf('/payments/') >= 0) {
        return this.beanClassName(api, detail) + 'Body';
    } else {
        return baseRule.paramClassName(api, detail);
    }
}

// @Override
remoteApiRule.returnClassName = function(api, detail) {
  if (api.url.indexOf('/payments/') >= 0) {
    return this.beanClassName(api, detail) + 'Result';
  } else {
    return baseRule.returnClassName(api, detail);
  }
}

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
    'selectedReason': 'org.docksidestage.dbflute.allcommon.CDef.WithdrawalReason',
    'productStatus': 'org.docksidestage.dbflute.allcommon.CDef.ProductStatus',
    'pastProductStatuses': 'org.docksidestage.dbflute.allcommon.CDef.ProductStatus'
};

// @Override
remoteApiRule.pathVariableManualMappingClass = function(api, pathVariable) {
    return manualMappingClassMap[pathVariable.name];
}

// @Override
remoteApiRule.beanPropertyManualMappingClass = function(api, beanClassName, property) {
    return manualMappingClassMap[property.name];
}
